var EarnestMoney = artifacts.require("./EarnestMoney.sol");

contract('EarnestMoney', function(accounts) {
  it("buyer deposit test", function() {

    var _uuid = '4863e144-fa22-11e7-8c3f-9a214cf093ae';
    console.log('     - uuid: ' + _uuid);
    console.log('     - owner address: ' + accounts[4]);
    console.log('     - realtor address: ' + accounts[6]);
    console.log('     - buyer address: ' + accounts[5]);
    var _instance;

    var _depositAmount;
    var _retrievedDepositAmount;

    var _beforeDepositContractBalance;
    var _afterDepositContractBalance;
    var _beforeDepositBuyerBalance; 
    var _afterDepositBuyerBalance;
 
    return EarnestMoney.new(accounts[4])
    .then(function(instance) {
        _instance = instance;
        return _instance.register(_uuid, {value: 50000, from: accounts[6]});
    })
    .then(function(result) {
        return _instance.getContractBalance.call({from: accounts[4]});
    })
    .then(function(beforeDepositContractBalance) {
        _beforeDepositContractBalance = beforeDepositContractBalance.toNumber();
        console.log('     - before deposit contract balance: ' +  _beforeDepositContractBalance);
        return _instance.getAccountBalance.call(accounts[5],{from: accounts[4]});
    })
    .then(function(beforeDepositBuyerBalance) {
        _beforeDepositBuyerBalance = beforeDepositBuyerBalance.toNumber();
        console.log('     - before deposit buyer balance: ' +  _beforeDepositBuyerBalance);
        _depositAmount = 2500000; //2.5M wei
        return _instance.deposit(_uuid, {value: _depositAmount, from: accounts[5]}); 
    })   
    .then(function(result) {
        var found = false;
       for (var i = 0; i < result.logs.length; i++) {
           var log = result.logs[i];
            if (log.event == "Deposited") {
             found = true;
             break;
           }
       }
       assert.isOk(found, "Deposited event did not occur");
       return _instance.getAccountBalance.call(accounts[5],{from: accounts[4]});
    })
    .then(function(afterDepositBuyerBalance) {
        _afterDepositBuyerBalance = afterDepositBuyerBalance.toNumber();
        console.log('     - after deposit buyer balance: ' +  _afterDepositBuyerBalance);
        assert.isOk((_beforeDepositBuyerBalance > _afterDepositBuyerBalance), 'buyer balance did not decrease');
        return _instance.getContractBalance.call({from: accounts[4]});
    }) 
    .then(function(afterDepositContractBalance) {
        _afterDepositContractBalance = afterDepositContractBalance.toNumber();
        console.log('     - after deposit contract balance: ' +  _afterDepositContractBalance);
        assert.isOk((_afterDepositContractBalance > _beforeDepositContractBalance), 'contract balance did not increase');
        return _instance.getDepositAmount.call(_uuid, {from: accounts[6]});
    })  
    .then(function(retrievedDepositAmount) {
        _retrievedDepositAmount = retrievedDepositAmount.toNumber();
        console.log('     - after deposit agreement deposit amount: ' +  _retrievedDepositAmount);
        assert.equal(_retrievedDepositAmount, _depositAmount, 'Agreement deposit amount does not equal deposit amount');
    });
  });
});