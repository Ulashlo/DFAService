const Factory = artifacts.require("Factory");
const DFA = artifacts.require("DFA");
const Exchanger = artifacts.require("Exchanger");

function timestamp() {
    return Math.floor(Date.now() / 1000);
}

async function assertBalance(dfa, user, amount, text) {
    let balance =
        (await dfa.balanceOf.call(user))
            .toNumber();
    assert.equal(balance, amount, text);
}

contract('Exchange', accounts => {
    let factory;

    let firstUser;
    let secondUser;

    let firstDfaAddress;
    let secondDfaAddress;
    let firstDfa;
    let secondDfa;

    let firstExchangerAddress;
    let secondExchangerAddress;
    let firstExchanger;
    let secondExchanger;

    let INDIVISIBLE = 0;
    let DIVISIBLE = 1;

    async function checkBalances(
        firstBalanceFirstDfa,
        secondBalanceFirstDfa,
        firstBalanceSecondDfa,
        secondBalanceSecondDfa,
        firstExchangerBalanceFirstDfa,
        secondExchangerBalanceSecondDfa
    ) {
        await assertBalance(firstDfa, firstUser, firstBalanceFirstDfa, 'Wrong balance at firstBalanceFirstDfa');
        await assertBalance(firstDfa, secondUser, secondBalanceFirstDfa, 'Wrong balance at secondBalanceFirstDfa');
        await assertBalance(secondDfa, firstUser, firstBalanceSecondDfa, 'Wrong balance at firstBalanceSecondDfa');
        await assertBalance(secondDfa, secondUser, secondBalanceSecondDfa, 'Wrong balance at secondBalanceSecondDfa');
        await assertBalance(firstDfa, firstExchangerAddress, firstExchangerBalanceFirstDfa, 'Wrong balance at firstExchangerBalanceFirstDfa');
        await assertBalance(secondDfa, secondExchangerAddress, secondExchangerBalanceSecondDfa, 'Wrong balance at secondExchangerBalanceSecondDfa');
    }

    async function addFirstUserRequest(amountToGet, amountToGive, type, isOvertime) {
        await firstDfa.approve(firstExchangerAddress, amountToGive, {from: firstUser});
        await firstExchanger.addRequest(
            {
                exchangeType: type,
                dfaToGet: secondDfaAddress,
                amountToGet: amountToGet,
                amountToGive: amountToGive,
                endTime: isOvertime ? timestamp() - 1000 : timestamp() + 1000
            },
            {from: firstUser});
    }

    async function addSecondUserRequest(amountToGet, amountToGive, type, isOvertime) {
        await secondDfa.approve(secondExchangerAddress, amountToGive, {from: secondUser});
        await secondExchanger.addRequest(
            {
                exchangeType: type,
                dfaToGet: firstDfaAddress,
                amountToGet: amountToGet,
                amountToGive: amountToGive,
                endTime: isOvertime ? timestamp() - 1000 : timestamp() + 1000
            },
            {from: secondUser});
    }

    beforeEach('Setup Factory', async () => {
        firstUser = accounts[0];
        secondUser = accounts[1];
        factory = await Factory.new();
        await factory.verifyUser(firstUser);
        await factory.verifyUser(secondUser);
        await factory.createDfa(1000, 'first', 'f', {from: firstUser});
        await factory.createDfa(2000, 'second', 's', {from: secondUser});
        let dfaList = await factory.getAllDfa();
        firstDfaAddress = dfaList[0][0];
        secondDfaAddress = dfaList[0][1];
        firstDfa = await DFA.at(firstDfaAddress);
        secondDfa = await DFA.at(secondDfaAddress);
        firstExchangerAddress = await factory.getExchanger(firstDfaAddress);
        secondExchangerAddress = await factory.getExchanger(secondDfaAddress);
        firstExchanger = await Exchanger.at(firstExchangerAddress);
        secondExchanger = await Exchanger.at(secondExchangerAddress);
    });

    describe('#addIndivisibleRequest', () => {
        it('Should create correct indivisible exchange', async () => {
            await addFirstUserRequest(200, 100, INDIVISIBLE, false);
            await addFirstUserRequest(200, 100, INDIVISIBLE, false);
            await addSecondUserRequest(100, 100, INDIVISIBLE, false);
            await addSecondUserRequest(100, 200, INDIVISIBLE, false);

            await checkBalances(800,100, 200,
                1700, 100, 100);
        });
    });

    describe('#addDivisibleRequest', () => {
        it('Should create correct divisible exchange', async () => {
            await addFirstUserRequest(200, 100, DIVISIBLE, false);
            await addSecondUserRequest(100, 100, DIVISIBLE, false);
            await addSecondUserRequest(50, 100, DIVISIBLE, false);

            await checkBalances(900,50, 100,
                1800, 50, 100);
        });
    });

    describe('#addDivisibleAndIndivisibleRequest', () => {
        it('Should create correct divisible and indivisible exchange', async () => {
            await addFirstUserRequest(200, 100, DIVISIBLE, false);
            await addSecondUserRequest(101, 202, INDIVISIBLE, false);
            await addSecondUserRequest(50, 100, INDIVISIBLE, false);
            await addFirstUserRequest(200, 200, INDIVISIBLE, false);
            await addSecondUserRequest(300, 300, DIVISIBLE, false);

            await checkBalances(700,250, 300,
                1398, 50, 302);
        });
    });

    describe('#getRequestsByDfa', () => {
        it('Should return correct requests', async () => {
            await addFirstUserRequest(200, 100, INDIVISIBLE, false);
            await addFirstUserRequest(200, 100, INDIVISIBLE, false);
            await addFirstUserRequest(100, 100, INDIVISIBLE, false);
            await addFirstUserRequest(150, 100, INDIVISIBLE, false);
            await addSecondUserRequest(100, 100, INDIVISIBLE, false);
            await addSecondUserRequest(100, 200, INDIVISIBLE, false);


            let firstRequests = await firstExchanger.getRequestsByDfa(secondDfaAddress);
            assert.equal(firstRequests["0"][0], firstUser);
            assert.equal(firstRequests["0"][1], firstUser);
            assert.equal(firstRequests["1"][0].toNumber(), 200);
            assert.equal(firstRequests["1"][1].toNumber(), 150);
            assert.equal(firstRequests["2"][0].toNumber(), 100);
            assert.equal(firstRequests["2"][1].toNumber(), 100);
            let secondRequests = await secondExchanger.getRequestsByDfa(firstDfaAddress);
            assert.equal(secondRequests["0"].length, 0);
            assert.equal(secondRequests["1"].length, 0);
            assert.equal(secondRequests["2"].length, 0);
        });
    });
});
