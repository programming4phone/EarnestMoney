var EarnestMoney = artifacts.require("./EarnestMoney.sol");

module.exports = function(deployer) {
    deployer.deploy(EarnestMoney);
};
