const DFA = artifacts.require("DFA");

contract('DFA', accounts => {
    let dfa;
    let owner;

    beforeEach('Setup DFA', async () => {
        owner = accounts[0];

        dfa = await DFA.new(
            1000,
            'first',
            'f',
            owner
        );
    });

    describe('#owner', () => {
        it('Should have correct owner', async () => {
            const expected_owner = await dfa.owner.call();

            assert.equal(expected_owner, owner, 'Wrong owner');
        });
    });

    describe('#mint', () => {
        it('Should has correct initial balance', async () => {
            const balance = (await dfa.balanceOf.call(owner)).toNumber();

            assert.equal(1000, balance, 'Wrong initial balance');
        });

        it('Should mint new dfa successfully', async () => {
            await dfa.mint(100);
            const minted = (await dfa.balanceOf.call(owner)).toNumber();

            assert.equal(1100, minted, 'Wrong amount of minted dfa');
        });
    });
});
