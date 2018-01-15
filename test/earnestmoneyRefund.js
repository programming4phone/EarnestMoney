var EarnestMoney = artifacts.require("./EarnestMoney.sol");

contract('EarnestMoney', function(accounts) {
  it("realtor refund test", function() {

    var _uuid = '55bb8f04-fa22-11e7-8c3f-9a214cf093ae';
    console.log('     - uuid: ' + _uuid);
    console.log('     - owner address: ' + accounts[4]);
    console.log('     - realtor address: ' + accounts[6]);
    console.log('     - buyer address: ' + accounts[5]);
    var _instance;

    var _depositAmount;
    var _retrievedDepositAmount;

    var _beforeRefundContractBalance;
    var _afterRefundContractBalance;
    var _beforeRefundBuyerBalance;
    var _afterRefundBuyerBalance;
 
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
    .then(function(beforeRefundContractBalance) {
        _beforeRefundContractBalance = beforeRefundContractBalance.toNumber();
        console.log('     - before refund contract balance: ' +  _beforeRefundContractBalance);
        return _instance.getAccountBalance.call(accounts[5],{from: accounts[4]});
    })
    .then(function(beforeRefundBuyerBalance) {
        _beforeRefundBuyerBalance = beforeRefundBuyerBalance.toNumber();
        console.log('     - before refund buyer balance: ' +  _beforeRefundBuyerBalance);
        return _instance.refund(_uuid, {from: accounts[6]}); 
    })   
    .then(function(result) {
        var found = false;
       for (var i = 0; i < result.logs.length; i++) {
           var log = result.logs[i];
            if (log.event == "Refunded") {
             found = true;
             break;
           }
       }
       assert.isOk(found, "Refunded event did not occur");
       return _instance.getAccountBalance.call(accounts[5],{from: accounts[4]});
    })
    .then(function(afterRefundBuyerBalance) {
        _afterRefundBuyerBalance = afterRefundBuyerBalance.toNumber();
        console.log('     - after refund buyer balance: ' +  _afterRefundBuyerBalance);
        assert.isOk((_afterRefundBuyerBalance > _beforeRefundBuyerBalance), 'buyer balance did not increase');
        return _instance.getContractBalance.call({from: accounts[4]});
    }) 
    .then(function(afterRefundContractBalance) {
        _afterRefundContractBalance = afterRefundContractBalance.toNumber();
        console.log('     - after refund contract balance: ' +  _afterRefundContractBalance);
        assert.isOk((_beforeRefundContractBalance > _afterRefundContractBalance), 'contract balance did not decrease');
     });
  });
});