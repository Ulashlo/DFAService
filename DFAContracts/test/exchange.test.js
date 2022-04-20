const Factory = artifacts.require("Factory");
const DFA = artifacts.require("DFA");
const Exchanger = artifacts.require("Exchanger");

contract('Exchange', accounts => {
    let factory;
    let firstUser;
    let secondUser;
    let firstDfaAddress;
    let secondDfaAddress;

    beforeEach('Setup Factory', async () => {
        firstUser = accounts[0];
        secondUser = accounts[1];
        factory = await Factory.new();
        await factory.createDfa(1000, 'first', 'f', {from: firstUser});
        await factory.createDfa(2000, 'second', 's', {from: secondUser});
        let dfaList = await factory.getAllDfa();
        firstDfaAddress = dfaList[0][0];
        secondDfaAddress = dfaList[0][1];
    });

    describe('#addRequest', () => {
        it('Should create correct exchange', async () => {
            let firstDfa = await DFA.at(firstDfaAddress);
            let secondDfa = await DFA.at(secondDfaAddress);

            let firstBalanceFirstDfa =
                (await firstDfa.balanceOf.call(firstUser))
                    .toNumber();
            let secondBalanceFirstDfa =
                (await firstDfa.balanceOf.call(secondUser))
                    .toNumber();
            let firstBalanceSecondDfa =
                (await secondDfa.balanceOf.call(firstUser))
                    .toNumber();
            let secondBalanceSecondDfa =
                (await secondDfa.balanceOf.call(secondUser))
                    .toNumber();
            assert.equal(firstBalanceFirstDfa, 1000, 'Wrong balance at firstBalanceFirstDfa');
            assert.equal(firstBalanceSecondDfa, 0, 'Wrong balance at firstBalanceSecondDfa');
            assert.equal(secondBalanceSecondDfa, 2000, 'Wrong balance at secondBalanceSecondDfa');
            assert.equal(secondBalanceFirstDfa, 0, 'Wrong balance at secondBalanceFirstDfa');

            let firstExchangerAddress = await factory.getExchanger(firstDfaAddress);
            let secondExchangerAddress = await factory.getExchanger(secondDfaAddress);
            let firstExchanger = await Exchanger.at(firstExchangerAddress);
            let secondExchanger = await Exchanger.at(secondExchangerAddress);

            await firstDfa.approve(firstExchangerAddress, 100, {from: firstUser});
            await firstExchanger.addRequest(secondDfaAddress, 200, 100, {from: firstUser});
            await firstDfa.approve(firstExchangerAddress, 100, {from: firstUser});
            await firstExchanger.addRequest(secondDfaAddress, 200, 100, {from: firstUser});
            await secondDfa.approve(secondExchangerAddress, 100, {from: secondUser});
            await secondExchanger.addRequest(firstDfaAddress, 100, 100, {from: secondUser});
            await secondDfa.approve(secondExchangerAddress, 200, {from: secondUser});
            await secondExchanger.addRequest(firstDfaAddress, 100, 200, {from: secondUser});

            firstBalanceFirstDfa =
                (await firstDfa.balanceOf.call(firstUser))
                    .toNumber();
            secondBalanceFirstDfa =
                (await firstDfa.balanceOf.call(secondUser))
                    .toNumber();
            let firstExchangerBalanceFirstDfa =
                (await firstDfa.balanceOf.call(firstExchangerAddress))
                    .toNumber();
            firstBalanceSecondDfa =
                (await secondDfa.balanceOf.call(firstUser))
                    .toNumber();
            secondBalanceSecondDfa =
                (await secondDfa.balanceOf.call(secondUser))
                    .toNumber();
            let secondExchangerBalanceSecondDfa =
                (await secondDfa.balanceOf.call(secondExchangerAddress))
                    .toNumber();
            assert.equal(firstBalanceFirstDfa, 800, 'Wrong balance at firstBalanceFirstDfa');
            assert.equal(firstBalanceSecondDfa, 200, 'Wrong balance at firstBalanceSecondDfa');
            assert.equal(secondBalanceSecondDfa, 1700, 'Wrong balance at secondBalanceSecondDfa');
            assert.equal(secondBalanceFirstDfa, 100, 'Wrong balance at secondBalanceFirstDfa');
            assert.equal(firstExchangerBalanceFirstDfa, 100, 'Wrong balance at firstExchangerBalanceFirstDfa');
            assert.equal(secondExchangerBalanceSecondDfa, 100, 'Wrong balance at secondExchangerBalanceSecondDfa');

            let firstRequests = await firstExchanger.getRequestsByDfa(secondDfaAddress);
            let secondRequests = await secondExchanger.getRequestsByDfa(firstDfaAddress);
            console.log(JSON.stringify(firstRequests))
            console.log(JSON.stringify(secondRequests))
        });
    });
});
