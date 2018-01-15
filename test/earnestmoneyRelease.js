var EarnestMoney = artifacts.require("./EarnestMoney.sol");

contract('EarnestMoney', function(accounts) {
  it("realtor release test", function() {

    var _uuid = '6ab9752e-fa22-11e7-8c3f-9a214cf093ae';
    console.log('     - uuid: ' + _uuid);
    console.log('     - owner address: ' + accounts[4]);
    console.log('     - realtor address: ' + accounts[6]);
    console.log('     - buyer address: ' + accounts[5]);
    var _instance;

    var _depositAmount;
    var _retrievedDepositAmount;

    var _beforeReleaseContractBalance;
    var _afterReleaseContractBalance;
    var _beforeReleaseClosingAgentBalance;
    var _afterReleaseClosingAgentBalance;
 
    return EarnestMoney.new(accounts[4])
    .then(function(instance) {
        _instance = instance;
        return _instance.register(_uuid, {value: 50000, from: accounts[6]});
    })
    .then(function(result) {
        _depositAmount = 2500000; //2.5M wei
        return _instance.deposit(_uuid, {value: _depositAmount, from: accounts[5]}); 
    })
    .then(function(result) {
        return _instance.getContractBalance.call({from: accounts[4]});
    })
    .then(function(beforeReleaseContractBalance) {
        _beforeReleaseContractBalance = beforeReleaseContractBalance.toNumber();
        console.log('     - before release contract balance: ' +  _beforeReleaseContractBalance);
        return _instance.getAccountBalance.call(accounts[7],{from: accounts[4]});
    })
    .then(function(beforeReleaseClosingAgentBalance) {
        _beforeReleaseClosingAgentBalance = beforeReleaseClosingAgentBalance.toNumber();
        console.log('     - before release closing agent balance: ' +  _beforeReleaseClosingAgentBalance);
        return _instance.release(_uuid, accounts[7], {from: accounts[6]}); 
    })   
    .then(function(result) {
        var found = false;
       for (var i = 0; i < result.logs.length; i++) {
           var log = result.logs[i];
            if (log.event == "Released") {
             found = true;
             break;
           }
       }
       assert.isOk(found, "Released event did not occur");
       return _instance.getAccountBalance.call(accounts[7],{from: accounts[4]});
    })
    .then(function(afterReleaseClosingAgentBalance) {
        _afterReleaseClosingAgentBalance = afterReleaseClosingAgentBalance.toNumber();
        console.log('     - after release closing agent balance: ' +  _afterReleaseClosingAgentBalance);
        assert.isOk((_afterReleaseClosingAgentBalance > _beforeReleaseClosingAgentBalance), 'closing agent balance did not increase');
        return _instance.getContractBalance.call({from: accounts[4]});
    }) 
    .then(function(afterReleaseContractBalance) {
        _afterReleaseContractBalance = afterReleaseContractBalance.toNumber();
        console.log('     - after release contract balance: ' +  _afterReleaseContractBalance);
        assert.isOk((_beforeReleaseContractBalance > _afterReleaseContractBalance), 'contract balance did not decrease');
     });
  });
});