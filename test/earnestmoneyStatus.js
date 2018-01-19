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
        return _instance.realtorStatus.call(_uuid, {from: accounts[6]}); 
    })
    .then(function(value) {
        console.log('     - Realtor status uuid: ' + value[0]);
        assert.isOk((value[0] == _uuid), "Realtor Status incorrect uuid");
        console.log('     - Realtor status _buyer: ' + value[1]);
        assert.isOk((value[1] == accounts[5]), "RealtorStatus incorrect buyer");
        console.log('     - Realtor status _realtor: ' + value[2]);
        assert.isOk((value[2] == accounts[6]), "Realtor status incorrect realtor");
        console.log('     - Realtor status _closingAgent: ' + value[3]);
        assert.isOk((value[3] == accounts[7]), "Realtor status incorrect closing agent");
        console.log('     - Realtor status _stage: ' + value[4]);
        assert.isOk((value[4] == 3), "Realtor status incorrect stage");
        console.log('     - Realtor status _amount: ' + value[5]);
        assert.isOk((value[5] == _depositAmount), "Realtor status incorrect amount");
       return _instance.buyerStatus(_uuid, {from: accounts[5]});
    })
    .then(function(value) {
        console.log('     - Buyer status uuid: ' + value[0]);
        assert.isOk((value[0] == _uuid), "BuyerStatus event incorrect uuid");
        console.log('     - Buyer status _buyer: ' + value[1]);
        assert.isOk((value[1] == accounts[5]), "Buyer status incorrect buyer");
        console.log('     - Buyer status _realtor: ' + value[2]);
        assert.isOk((value[2] == accounts[6]), "Buyer status incorrect realtor");
        console.log('     - Buyer status _closingAgent: ' + value[3]);
        assert.isOk((value[3] == accounts[7]), "Buyer status incorrect closing agent");
        console.log('     - Buyer status _stage: ' + value[4]);
        assert.isOk((value[4] == 3), "Buyer status incorrect stage");
        console.log('     - Buyer status _amount: ' + value[5]);
        assert.isOk((value[5] == _depositAmount), "Buyer status incorrect amount");
    });
  });
});