var EarnestMoney = artifacts.require("./EarnestMoney.sol");

module.exports = function(deployer) {
    const _owner = '0x627306090abaB3A6e1400e9345bC60c78a8BEf57';
    deployer.deploy(EarnestMoney, _owner);
};
