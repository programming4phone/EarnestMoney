var EarnestMoney = artifacts.require("./EarnestMoney.sol");

contract('EarnestMoney', function(accounts) {
  it('realtor register test', function() {

    var _uuid = 'cefdae12-f944-11e7-8c3f-9a214cf093ae';
    console.log('     - uuid: ' + _uuid);
    console.log('     - owner address: ' + accounts[0]);
    console.log('     - realtor address: ' + accounts[2]);
    var _instance;
    var _initialContractBalance;
    var _initialOwnerBalance;
    var _afterRegisterOwnerBalance;
    var _initialRealtorBalance;
    var _afterRegisterRealtorBalance;
    var _afterRegisterContractBalance;

    return EarnestMoney.new(accounts[0])
    .then(function(instance) {
        _instance = instance;
        return _instance.getContractBalance.call({from: accounts[0]});
    })
    .then(function(initialContractBalance) {
        _initialContractBalance = initialContractBalance.toNumber()
        console.log('     - initial contract balance: ' + _initialContractBalance);
        return _instance.getAccountBalance.call(accounts[0],{from: accounts[0]});
    })
    .then(function(initialOwnerBalance) {
        _initialOwnerBalance = initialOwnerBalance.toNumber();
        console.log('     - initialOwnerBalance: ' + _initialOwnerBalance);
        return _instance.getAccountBalance.call(accounts[2],{from: accounts[0]});
    })
    .then(function(initialRealtorBalance) {
        _initialRealtorBalance = initialRealtorBalance.toNumber();
        console.log('     - initialRealtorBalance: ' + _initialRealtorBalance);
        return _instance.register(_uuid, {value: 50000, from: accounts[2]});
     })
    .then(function(result) {
         var found = false;
        for (var i = 0; i < result.logs.length; i++) {
            var log = result.logs[i];
             if (log.event == "Registered") {
              found = true;
              break;
            }
        }
        assert.isOk(found, "Registered event did not occur");
        return _instance.getAccountBalance.call(accounts[0],{from: accounts[0]});
    })
    .then(function(afterRegisterOwnerBalance) {
        _afterRegisterOwnerBalance = afterRegisterOwnerBalance.toNumber();
        console.log('     - afterRegisterOwnerBalance: ' + _afterRegisterOwnerBalance);
        assert.isOk((_afterRegisterOwnerBalance > _initialOwnerBalance), 'owner balance did not increase');
        return _instance.getAccountBalance.call(accounts[2],{from: accounts[0]});
    })
    .then(function(afterRegisterRealtorBalance) {
        _afterRegisterRealtorBalance = afterRegisterRealtorBalance.toNumber();
        console.log('     - afterRegisterRealtorBalance: ' + _afterRegisterRealtorBalance);
        assert.isOk((_initialRealtorBalance > _afterRegisterRealtorBalance), 'realtor balance did not decrease');
        return _instance.getContractBalance.call({from: accounts[0]});
    })
    .then(function(afterRegisterContractBalance) {
        _afterRegisterContractBalance = afterRegisterContractBalance.toNumber();
        console.log('     - after register contract balance: ' +  _afterRegisterContractBalance);
    });
  });
});