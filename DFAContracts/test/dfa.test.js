const DFA = artifacts.require("DFA");

contract("DFA", function (accounts) {
    let dfa;
    let owner = accounts[0];

    beforeEach('Setup DFA', async () => {
        dfa = await DFA.deployed();
    });

    describe('#owner', () => {
        it('Should have correct owner', async () => {
            const expected_owner = await dfa.owner.call();

            assert.equal(expected_owner, owner, "Wrong owner");
        });
    });

    describe('#mint', () => {
        it('Should mint new dfa successfully', async () => {
            console.log(await dfa.mint.call(1500));

            const minted = (await dfa.balances.call(owner)).toNumber();
            console.log(minted)
            assert.equal(1500, minted, "Wrong amount of minted dfa");
        });
    });
});
