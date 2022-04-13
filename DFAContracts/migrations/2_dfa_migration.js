const Exchanger = artifacts.require("Exchanger");

module.exports = function(deployer) {
    deployer.deploy(Exchanger);
};
