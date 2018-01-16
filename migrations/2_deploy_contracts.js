var EarnestMoney = artifacts.require("./EarnestMoney.sol");

module.exports = function(deployer) {
    const _owner = '0x5AEDA56215b167893e80B4fE645BA6d5Bab767DE';
    deployer.deploy(EarnestMoney, _owner);
};
