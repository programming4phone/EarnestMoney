var EarnestMoney = artifacts.require("./EarnestMoney.sol");

contract('EarnestMoney', function(accounts) {
  it("realtor cancel test", function() {

    var _uuid = '3760edc2-fa01-11e7-8c3f-9a214cf093ae';
    console.log('     - uuid: ' + _uuid);
    console.log('     - owner address: ' + accounts[4]);
    console.log('     - realtor address: ' + accounts[6]);
    console.log('     - buyer address: ' + accounts[5]);
    var _instance;
    var _depositAmount;
 
    return EarnestMoney.new(accounts[4])
    .then(function(instance) {
        _instance = instance;
        console.log('     - before register ');
        return _instance.register(_uuid, {value: 50000, from: accounts[6]});
    })
    .then(function(result) {
        _depositAmount = 2500000; //2.5M wei
        console.log('     - before deposit ');
        return _instance.deposit(_uuid, {value: _depositAmount, from: accounts[5]}); 
    })
    .then(function(result) {
        console.log('     - before release ');
        return _instance.release(_uuid, accounts[7], {from: accounts[6]}); 
    })   
    .then(function(result) {
        console.log('     - before cancel ');
        return _instance.cancel(_uuid, {from: accounts[6]});
    })
    .then(function(result) {
        console.log('     - iterate events ');
        var found = false;
       for (var i = 0; i < result.logs.length; i++) {
           var log = result.logs[i];
            if (log.event == "Cancelled") {
             found = true;
             break;
           }
       }
       assert.isOk(found, "Cancelled event did not occur");
    });
  });
});