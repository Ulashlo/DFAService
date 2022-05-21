const Factory = artifacts.require("./Factory.sol");

module.exports = async function(deployer, network, accounts) {
    await deployer.deploy(Factory);
    const instance = await Factory.deployed();
    await instance.verifyUser(accounts[0])
    await instance.createDfa(0, 'Условная единица', 'уе', {from: accounts[0]});
};
