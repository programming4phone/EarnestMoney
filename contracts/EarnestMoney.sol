pragma solidity ^0.4.18;

/// @title This contract manages `good-faith` earnest money contracts used in real estate transactions.
/// When a buyer enters into a real estate contract, the realtor will typically request that the buyer 
/// deposit a sum of money as good faith (typically 1% of the sales price) to help convince the seller 
/// that the buyer is serious about the purchase. Currently, this means that:
/// 1) The buyer writes a check to the realtor
/// 2) The realtor holds the check until the seller agrees to the terms of the real estate contract.
/// 3) The realtor deposits the check into a bank account.
/// 4) The realtor writes a check to the closing agent (typically an attorney) and brings it to closing.
/// 5) The closing agent deposits the check into a bank account.
/// If, for some reason, the real estate contract becomes invalid, the realtor needs to refund the 
/// money to the buyer either by returning the buyer's check or by writing a new check to the buyer.
/// This manual movement of writing and depositing checks can be easily be automated using a smart contract.
contract EarnestMoney{

    enum Stages {
        Empty,
        Registered,
        Funded,
        Released,
        Refunded,
        Cancelled
    }

    struct Agreement {
        Stages stage;
        address buyer;
        address realtor;
        address closingAgent;
        uint256 amount;
    }

    address internal owner;
    mapping(bytes32 => Agreement) internal agreements;

    event Registered(string _uuid, address _realtor);
    event Deposited(string _uuid, address _buyer, address _realtor, uint256 _amount);
    event Released(string _uuid, address _buyer, address _realtor, address _closingAgent, uint256 _amount);
    event Refunded(string _uuid, address _buyer, address _realtor, uint256 _amount);
    event Cancelled(string _uuid, address _realtor);
 
    modifier onlyOwner() { require(msg.sender == owner); _; }

    /// @notice Creates instance of EarnestMoney smart contract on the blockchain.
    /// @param _owner Address of the contract owner
    function EarnestMoney(address _owner) public {
            owner = _owner;
    }

    /// @notice Register an agreement. This action identifies the realtor
    /// who will manage the earnest money for a specific real estate contract.
    /// The realtor is charged an initial fee as part of the registration   
    /// process to cover the owner's expenses.
    /// This function fires the `Registered` event upon successful completion. 
    /// Agreement state is changed to `Registered` allowing funds to be deposited.
    /// This function will terminate if an attempt is made to register an 
    /// existing agreement whose state is either `Funded`, `Released`, or `Refunded`.
    /// @param uuid Unique string that identifies this earnest money agreement to the outside world
    /// @dev uuid format is string `123e4567-e89b-12d3-a456-556642440000`
    function register(string uuid) public payable {
        address _realtor = msg.sender;
        bytes32 agreementKey = keccak256(uuid);
        Agreement storage agreement = agreements[agreementKey];
        Stages _stage = agreement.stage;
        require(_stage != Stages.Funded && _stage != Stages.Released && _stage != Stages.Refunded);
        agreement.stage = Stages.Registered;
        agreement.realtor = _realtor;
        agreement.amount = 0;
        agreement.buyer = 0x00;
        agreement.closingAgent = 0x00;
        owner.transfer(msg.value);
        Registered(uuid, _realtor);
    }

    /// @notice Deposit the earnest money sent by the buyer with this transaction   
    /// into the balance of the agreement. Actual funds remain in the contract balance until  
    /// the realtor either releases them to the closing agent or refunds them to the buyer.
    /// The agreement state must be `Registered` in order to deposit.
    /// This function fires the `Deposited` event upon successful completion. 
    /// Agreement state is changed to `Funded` restricting further activity to either release or refund.
    /// @param uuid Unique string that identifies this earnest money agreement to the outside world
    /// @dev uuid format is string `123e4567-e89b-12d3-a456-556642440000`
    function deposit(string uuid) public payable {
        address _buyer = msg.sender;
        uint256 _amount = msg.value;
        bytes32 agreementKey = keccak256(uuid);
        Agreement storage agreement = agreements[agreementKey];
        require(agreement.stage == Stages.Registered);
        address _realtor = agreement.realtor;
        agreement.stage = Stages.Funded;
        agreement.buyer = _buyer;
        agreement.amount = _amount;
        Deposited(uuid, _buyer, _realtor, _amount);
    }

    /// @notice Query the deposit amount held by the agreement. This function can  
    /// only be executed by the Realtor. 
    /// @param uuid Unique string that identifies this earnest money agreement to the outside world
    /// @dev uuid format is string `123e4567-e89b-12d3-a456-556642440000`
    /// @return The amount of earnest money deposited
    function getDepositAmount(string uuid) public view returns (uint256) {
        address _realtor = msg.sender;
        bytes32 agreementKey = keccak256(uuid);
        Agreement storage agreement = agreements[agreementKey];
        require(agreement.realtor == _realtor);
        uint256 _amount = agreement.amount;
        return _amount;
    }

    /// @notice Release the buyer's earnest money to the closing agent. This action transfers 
    /// funds held in the contract balance to the closing agent. The agreement state must be 
    /// `Funded` in order to release. This function can only be executed by the Realtor and
    ///  fires the `Released` event upon successful completion. Agreement state is changed to 
    /// `Released` effectively prohibiting any future unauthorized fund transfers.
    /// @param uuid Unique string that identifies this earnest money agreement to the outside world
    /// @dev uuid format is string `123e4567-e89b-12d3-a456-556642440000`
    /// @param _closingAgent Address of the closing agent
    function release(string uuid, address _closingAgent) public {
        address _realtor = msg.sender;
        bytes32 agreementKey = keccak256(uuid);
        Agreement storage agreement = agreements[agreementKey];
        require(agreement.realtor == _realtor);
        require(agreement.stage == Stages.Funded);
        address _buyer = agreement.buyer;
        agreement.stage = Stages.Released;
        agreement.closingAgent = _closingAgent;
        uint256 _amount = agreement.amount;
        _closingAgent.transfer(_amount);
        Released(uuid, _buyer, _realtor, _closingAgent, _amount);
    }

    /// @notice Refund the buyer's earnest money. This action 
    /// transfers funds held in the contract balance to the buyer. 
    /// The agreement state must be `Funded` in order to refund. 
    /// This function can only be executed by the Realtor and fires the `Refunded`  
    /// event upon successful completion. Agreement state is changed to `Finished`
    /// effectively prohibiting any future unauthorized fund transfers.
    /// @param uuid Unique string that identifies this earnest money agreement to the outside world
    /// @dev uuid format is string `123e4567-e89b-12d3-a456-556642440000`
    function refund(string uuid) public {
        address _realtor = msg.sender;
        bytes32 agreementKey = keccak256(uuid);
        Agreement storage agreement = agreements[agreementKey];
        require(agreement.realtor == _realtor);
        require(agreement.stage == Stages.Funded);
        address _buyer = agreement.buyer;
        agreement.stage = Stages.Refunded;
        uint256 _amount = agreement.amount;
         _buyer.transfer(_amount);
        Refunded(uuid, _buyer, _realtor, _amount);
    }

    /// @notice Cancel an agreement. This function can only be executed by the Realtor
    /// and fires the `Cancelled` event upon successful completion..
    /// The agreement state must be either `Released` or `Refunded` in order to cancel.
    /// Agreement state is changed to `Cancelled`.
    /// The agreement is reset to default values and could possibly (although unlikely) be reused.
    /// @param uuid Unique string that identifies this earnest money agreement to the outside world
    /// @dev uuid format is string `123e4567-e89b-12d3-a456-556642440000`
    function cancel(string uuid) public {
        address _realtor = msg.sender;
        bytes32 agreementKey = keccak256(uuid);
        Agreement storage agreement = agreements[agreementKey];
        require(agreement.realtor == _realtor);
        require(agreement.stage == Stages.Released || agreement.stage == Stages.Refunded);
        agreement.stage = Stages.Cancelled;
        agreement.buyer = 0x00;
        agreement.realtor = 0x00;
        agreement.closingAgent = 0x00;
        agreement.amount = 0;
        Cancelled(uuid, _realtor);
    }

    /// @notice Kill the EarnestMoney contract instance which removes it from the blockchain. 
    /// This function can only be executed by the owner.
    function kill() public onlyOwner {
        selfdestruct(owner);
    }

    ///  @notice Query an account balance. 
    /// This function can only be executed by the owner.
    /// @param account Address of account
    /// @return The amount of ether in the account balance
    function getAccountBalance(address account) public view onlyOwner returns (uint256) {
        return account.balance;
    }

    /// @notice Query the contract balance. 
    /// This function can only be executed by the owner.
    /// @return The amount of ether in the contract balance
    function getContractBalance() public view onlyOwner returns (uint256) {
        return this.balance;
    }

    /// @notice Query the status of an agreement. This function can only be executed by
    /// a Realtor and fires the `RealtorStatus` event upon successful completion. 
    /// The realtor must also be the one who registered the agreement identified by uuid.
    /// Uninitialized and cancelled agreements may also be returned.
    /// @param uuid Unique string that identifies this earnest money agreement to the outside world
    /// @dev uuid format is string `123e4567-e89b-12d3-a456-556642440000`
    /// @return Status of the agreement which includes uuid, buyer address, realtor address, closing agent address, stage, and deposit amount
    function realtorStatus(string uuid) public view 
        returns (string, address, address, address, Stages, uint256) { 
        address _realtor = msg.sender;
        bytes32 agreementKey = keccak256(uuid);
        Agreement memory agreement = agreements[agreementKey];
        address _agreementRealtor = agreement.realtor;
        Stages _stage = agreement.stage;
        require(_stage == Stages.Empty || _stage == Stages.Cancelled || _agreementRealtor == _realtor);
        address _buyer = agreement.buyer;
        address _closingAgent = agreement.closingAgent;
        uint256 _amount = agreement.amount;
        return (uuid, _buyer, _agreementRealtor, _closingAgent, _stage, _amount);
    }

    /// @notice Query the status of an agreement. This function can only be executed by
    /// a Buyer and fires the `BuyerStatus` event upon successful completion. 
    /// The buyer must also be the one who deposited funds for the agreement identified by uuid.
    /// Uninitialized and cancelled agreements may also be returned.
    /// @param uuid Unique string that identifies this earnest money agreement to the outside world
    /// @dev uuid format is string `123e4567-e89b-12d3-a456-556642440000`
    /// @return Status of the agreement which includes uuid, buyer address, realtor address, closing agent address, stage, and deposit amount
    function buyerStatus(string uuid) public view 
        returns (string, address, address, address, Stages, uint256) {
        address _buyer = msg.sender;
        bytes32 agreementKey = keccak256(uuid);
        Agreement memory agreement = agreements[agreementKey];
        address _agreementBuyer = agreement.buyer;
        Stages _stage = agreement.stage;
        require(_stage == Stages.Empty || _stage == Stages.Cancelled || _agreementBuyer == _buyer);
        address _realtor = agreement.realtor;
        address _closingAgent = agreement.closingAgent;
        uint256 _amount = agreement.amount;
        return (uuid, _agreementBuyer, _realtor, _closingAgent, _stage, _amount);
    }
}