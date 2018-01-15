package com.programming4phone;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class EarnestMoney extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b60405160208061147683398101604052808051906020019091905050806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506113fb8061007b6000396000f300606060405260043610610099576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630b4f3f3d1461009e57806341c0e1b5146100fb578063470495b21461011057806356fed7541461018c5780636f9fb98a146101fd57806393423e9c14610226578063a26e118614610273578063f2c298be146102c5578063fe5f2e8814610317575b600080fd5b34156100a957600080fd5b6100f9600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610374565b005b341561010657600080fd5b61010e610695565b005b341561011b57600080fd5b61018a600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061072a565b005b341561019757600080fd5b6101e7600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610a6b565b6040518082815260200191505060405180910390f35b341561020857600080fd5b610210610b69565b6040518082815260200191505060405180910390f35b341561023157600080fd5b61025d600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610be3565b6040518082815260200191505060405180910390f35b6102c3600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610c5f565b005b610315600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610ed2565b005b341561032257600080fd5b610372600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050611106565b005b6000806000339250836040518082805190602001908083835b6020831015156103b2578051825260208201915060208101905060208303925061038d565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020915060016000836000191660001916815260200190815260200160002090508273ffffffffffffffffffffffffffffffffffffffff168160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561045c57600080fd5b6002600381111561046957fe5b8160000160009054906101000a900460ff16600381111561048657fe5b14806104b8575060038081111561049957fe5b8160000160009054906101000a900460ff1660038111156104b657fe5b145b15156104c357600080fd5b60008160000160006101000a81548160ff021916908360038111156104e457fe5b021790555060008160000160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600081600301819055507fd5ef821d514b781472d5e9526a6d9447cc4f14776aa4f0720d9470628d778dfa848460405180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610654578082015181840152602081019050610639565b50505050905090810190601f1680156106815780820380516001836020036101000a031916815260200191505b50935050505060405180910390a150505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156106f057600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b6000806000806000339450866040518082805190602001908083835b60208310151561076b5780518252602082019150602081019050602083039250610746565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020935060016000856000191660001916815260200190815260200160002092508473ffffffffffffffffffffffffffffffffffffffff168360010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561081557600080fd5b6001600381111561082257fe5b8360000160009054906101000a900460ff16600381111561083f57fe5b14151561084b57600080fd5b8260000160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915060028360000160006101000a81548160ff0219169083600381111561089357fe5b0217905550858360020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550826003015490508573ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f19350505050151561092257600080fd5b7fec85ddbdf1488e1d91efd9b5d411fce4ab2cc2ee72772ab9a1950f881a3f5095878387898560405180806020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828103825287818151815260200191508051906020019080838360005b83811015610a24578082015181840152602081019050610a09565b50505050905090810190601f168015610a515780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a150505050505050565b6000806000806000339350856040518082805190602001908083835b602083101515610aac5780518252602082019150602081019050602083039250610a87565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020925060016000846000191660001916815260200190815260200160002091508373ffffffffffffffffffffffffffffffffffffffff168260010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610b5657600080fd5b8160030154905080945050505050919050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610bc657600080fd5b3073ffffffffffffffffffffffffffffffffffffffff1631905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610c4057600080fd5b8173ffffffffffffffffffffffffffffffffffffffff16319050919050565b6000806000806000339450349350856040518082805190602001908083835b602083101515610ca35780518252602082019150602081019050602083039250610c7e565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390209250600160008460001916600019168152602001908152602001600020915060006003811115610cfc57fe5b8260000160009054906101000a900460ff166003811115610d1957fe5b141515610d2557600080fd5b8160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905060018260000160006101000a81548160ff02191690836003811115610d6d57fe5b0217905550848260000160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508382600301819055507fa348b54485f1d38cacb4b427a2dbc5bf2067cb3ba1024d6a4213a018939984848686838760405180806020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828103825286818151815260200191508051906020019080838360005b83811015610e8d578082015181840152602081019050610e72565b50505050905090810190601f168015610eba5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a1505050505050565b6000806000339250836040518082805190602001908083835b602083101515610f105780518252602082019150602081019050602083039250610eeb565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390209150600160008360001916600019168152602001908152602001600020905060008160000160006101000a81548160ff02191690836003811115610f7d57fe5b0217905550828160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600081600301819055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050151561103057600080fd5b7f50f74ca45caac8020b8d891bd13ea5a2d79564986ee6a839f0d914896388322d848460405180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156110c55780820151818401526020810190506110aa565b50505050905090810190601f1680156110f25780820380516001836020036101000a031916815260200191505b50935050505060405180910390a150505050565b6000806000806000339450856040518082805190602001908083835b6020831015156111475780518252602082019150602081019050602083039250611122565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020935060016000856000191660001916815260200190815260200160002092508473ffffffffffffffffffffffffffffffffffffffff168360010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156111f157600080fd5b600160038111156111fe57fe5b8360000160009054906101000a900460ff16600381111561121b57fe5b14151561122757600080fd5b8260000160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915060038360000160006101000a81548160ff0219169083600381111561126f57fe5b0217905550826003015490508173ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f1935050505015156112bb57600080fd5b7fdc718f88216244ce6f5aa6e9d6645d1e1363a9abf3fa7f56d210ff26dd6a88b88683878460405180806020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828103825286818151815260200191508051906020019080838360005b8381101561138a57808201518184015260208101905061136f565b50505050905090810190601f1680156113b75780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a15050505050505600a165627a7a7230582032727407622025bfc450d31ab2f32a199955c27c141ee3ce693a553a180e216e0029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
        _addresses.put("5777", "0x345ca3e014aaf5dca488057592ee47305d9b3e10");
    }

    protected EarnestMoney(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EarnestMoney(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<RegisteredEventResponse> getRegisteredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Registered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<RegisteredEventResponse> responses = new ArrayList<RegisteredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            RegisteredEventResponse typedResponse = new RegisteredEventResponse();
            typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RegisteredEventResponse> registeredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Registered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, RegisteredEventResponse>() {
            @Override
            public RegisteredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                RegisteredEventResponse typedResponse = new RegisteredEventResponse();
                typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<DepositedEventResponse> getDepositedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Deposited", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<DepositedEventResponse> responses = new ArrayList<DepositedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            DepositedEventResponse typedResponse = new DepositedEventResponse();
            typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<DepositedEventResponse> depositedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Deposited", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, DepositedEventResponse>() {
            @Override
            public DepositedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                DepositedEventResponse typedResponse = new DepositedEventResponse();
                typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public List<ReleasedEventResponse> getReleasedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Released", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ReleasedEventResponse> responses = new ArrayList<ReleasedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ReleasedEventResponse typedResponse = new ReleasedEventResponse();
            typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._closingAgent = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ReleasedEventResponse> releasedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Released", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ReleasedEventResponse>() {
            @Override
            public ReleasedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ReleasedEventResponse typedResponse = new ReleasedEventResponse();
                typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._closingAgent = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public List<RefundedEventResponse> getRefundedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Refunded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<RefundedEventResponse> responses = new ArrayList<RefundedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            RefundedEventResponse typedResponse = new RefundedEventResponse();
            typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RefundedEventResponse> refundedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Refunded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, RefundedEventResponse>() {
            @Override
            public RefundedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                RefundedEventResponse typedResponse = new RefundedEventResponse();
                typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public List<CancelledEventResponse> getCancelledEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Cancelled", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<CancelledEventResponse> responses = new ArrayList<CancelledEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            CancelledEventResponse typedResponse = new CancelledEventResponse();
            typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CancelledEventResponse> cancelledEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Cancelled", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, CancelledEventResponse>() {
            @Override
            public CancelledEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                CancelledEventResponse typedResponse = new CancelledEventResponse();
                typedResponse._uuid = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._realtor = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> cancel(String uuid) {
        Function function = new Function(
                "cancel", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> kill() {
        Function function = new Function(
                "kill", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> release(String uuid, String _closingAgent) {
        Function function = new Function(
                "release", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid), 
                new org.web3j.abi.datatypes.Address(_closingAgent)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getDepositAmount(String uuid) {
        Function function = new Function("getDepositAmount", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getContractBalance() {
        Function function = new Function("getContractBalance", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getAccountBalance(String account) {
        Function function = new Function("getAccountBalance", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> deposit(String uuid, BigInteger weiValue) {
        Function function = new Function(
                "deposit", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> register(String uuid, BigInteger weiValue) {
        Function function = new Function(
                "register", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> refund(String uuid) {
        Function function = new Function(
                "refund", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<EarnestMoney> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)));
        return deployRemoteCall(EarnestMoney.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<EarnestMoney> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)));
        return deployRemoteCall(EarnestMoney.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static EarnestMoney load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EarnestMoney(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static EarnestMoney load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EarnestMoney(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class RegisteredEventResponse {
        public String _uuid;

        public String _realtor;
    }

    public static class DepositedEventResponse {
        public String _uuid;

        public String _buyer;

        public String _realtor;

        public BigInteger _amount;
    }

    public static class ReleasedEventResponse {
        public String _uuid;

        public String _buyer;

        public String _realtor;

        public String _closingAgent;

        public BigInteger _amount;
    }

    public static class RefundedEventResponse {
        public String _uuid;

        public String _buyer;

        public String _realtor;

        public BigInteger _amount;
    }

    public static class CancelledEventResponse {
        public String _uuid;

        public String _realtor;
    }
}
