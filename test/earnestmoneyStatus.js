var EarnestMoney = artifacts.require("./EarnestMoney.sol");

contract('EarnestMoney', function(accounts) {
  it("status test", function() {

    var _uuid = '6ab9752e-fa22-11e7-8c3f-9a214cf093ae';
    console.log('     - uuid: ' + _uuid);
    console.log('     - owner address: ' + accounts[4]);
    console.log('     - realtor address: ' + accounts[6]);
    console.log('     - buyer address: ' + accounts[5]);
    console.log('     - closing agent address: ' + accounts[7]);

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
        return _instance.release(_uuid, accounts[7], {from: accounts[6]}); 
    })
    .then(function(result) {
        return _instance.realtorStatus(_uuid, {from: accounts[6]}); 
    })
    .then(function(result) {
        var found = false;
       for (var i = 0; i < result.logs.length; i++) {
           var log = result.logs[i];
            if (log.event == "RealtorStatus") {
                found = true;

                console.log('     - RealtorStatus event log.args.uuid: ' + log.args.uuid);
                assert.isOk((log.args.uuid == _uuid), "RealtorStatus event incorrect uuid");
                console.log('     - RealtorStatus event log.args._buyer: ' + log.args._buyer);
                assert.isOk((log.args._buyer == accounts[5]), "RealtorStatus event incorrect buyer");
                console.log('     - RealtorStatus event log.args._realtor: ' + log.args._realtor);
                assert.isOk((log.args._realtor == accounts[6]), "RealtorStatus event incorrect realtor");
                console.log('     - RealtorStatus event log.args._closingAgent: ' + log.args._closingAgent);
                assert.isOk((log.args._closingAgent == accounts[7]), "RealtorStatus event incorrect closing agent");
                console.log('     - RealtorStatus event log.args._stage: ' + log.args._stage);
                assert.isOk((log.args._stage == 3), "RealtorStatus event incorrect stage");
                console.log('     - RealtorStatus event log.args._amount: ' + log.args._amount);
                assert.isOk((log.args._amount == _depositAmount), "RealtorStatus event incorrect amount");

                break;
           }
       }
       assert.isOk(found, "RealtorStatus event did not occur");
       return _instance.buyerStatus(_uuid, {from: accounts[5]});
    })
    .then(function(result) {
        var found = false;
       for (var i = 0; i < result.logs.length; i++) {
           var log = result.logs[i];
            if (log.event == "BuyerStatus") {
             found = true;

             console.log('     - BuyerStatus event log.args.uuid: ' + log.args.uuid);
             assert.isOk((log.args.uuid == _uuid), "BuyerStatus event incorrect uuid");
             console.log('     - BuyerStatus event log.args._buyer: ' + log.args._buyer);
             assert.isOk((log.args._buyer == accounts[5]), "BuyerStatus event incorrect buyer");
             console.log('     - BuyerStatus event log.args._realtor: ' + log.args._realtor);
             assert.isOk((log.args._realtor == accounts[6]), "BuyerStatus event incorrect realtor");
             console.log('     - BuyerStatus event log.args._closingAgent: ' + log.args._closingAgent);
             assert.isOk((log.args._closingAgent == accounts[7]), "BuyerStatus event incorrect closing agent");
             console.log('     - BuyerStatus event log.args._stage: ' + log.args._stage);
             assert.isOk((log.args._stage == 3), "BuyerStatus event incorrect stage");
             console.log('     - BuyerStatus event log.args._amount: ' + log.args._amount);
             assert.isOk((log.args._amount == _depositAmount), "BuyerStatus event incorrect amount");

             break;
           }
       }
       assert.isOk(found, "BuyerStatus event did not occur");
    });
  });
});