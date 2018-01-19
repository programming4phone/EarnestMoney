package com.programming4phone;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
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
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
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
    private static final String BINARY = "0x6060604052341561000f57600080fd5b604051602080611e9a83398101604052808051906020019091905050806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050611e1f8061007b6000396000f3006060604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630b4f3f3d146100b457806341c0e1b514610111578063470495b21461012657806356fed754146101a25780636f9fb98a1461021357806393423e9c1461023c578063a26e118614610289578063da4dc135146102db578063f2c298be14610466578063f33d7e4b146104b8578063fe5f2e8814610643575b600080fd5b34156100bf57600080fd5b61010f600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506106a0565b005b341561011c57600080fd5b6101246109c2565b005b341561013157600080fd5b6101a0600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610a57565b005b34156101ad57600080fd5b6101fd600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610d98565b6040518082815260200191505060405180910390f35b341561021e57600080fd5b610226610e96565b6040518082815260200191505060405180910390f35b341561024757600080fd5b610273600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610f10565b6040518082815260200191505060405180910390f35b6102d9600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610f8c565b005b34156102e657600080fd5b610336600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506111ff565b60405180806020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018460058111156103e057fe5b60ff168152602001838152602001828103825288818151815260200191508051906020019080838360005b8381101561042657808201518184015260208101905061040b565b50505050905090810190601f1680156104535780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b6104b6600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506114ad565b005b34156104c357600080fd5b610513600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506117eb565b60405180806020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018460058111156105bd57fe5b60ff168152602001838152602001828103825288818151815260200191508051906020019080838360005b838110156106035780820151818401526020810190506105e8565b50505050905090810190601f1680156106305780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b341561064e57600080fd5b61069e600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050611a99565b005b6000806000339250836040518082805190602001908083835b6020831015156106de57805182526020820191506020810190506020830392506106b9565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020915060016000836000191660001916815260200190815260200160002090508273ffffffffffffffffffffffffffffffffffffffff168160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561078857600080fd5b6003600581111561079557fe5b8160000160009054906101000a900460ff1660058111156107b257fe5b14806107e55750600460058111156107c657fe5b8160000160009054906101000a900460ff1660058111156107e357fe5b145b15156107f057600080fd5b60058160000160006101000a81548160ff0219169083600581111561081157fe5b021790555060008160000160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600081600301819055507fd5ef821d514b781472d5e9526a6d9447cc4f14776aa4f0720d9470628d778dfa848460405180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610981578082015181840152602081019050610966565b50505050905090810190601f1680156109ae5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a150505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610a1d57600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b6000806000806000339450866040518082805190602001908083835b602083101515610a985780518252602082019150602081019050602083039250610a73565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020935060016000856000191660001916815260200190815260200160002092508473ffffffffffffffffffffffffffffffffffffffff168360010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610b4257600080fd5b60026005811115610b4f57fe5b8360000160009054906101000a900460ff166005811115610b6c57fe5b141515610b7857600080fd5b8260000160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915060038360000160006101000a81548160ff02191690836005811115610bc057fe5b0217905550858360020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550826003015490508573ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f193505050501515610c4f57600080fd5b7fec85ddbdf1488e1d91efd9b5d411fce4ab2cc2ee72772ab9a1950f881a3f5095878387898560405180806020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828103825287818151815260200191508051906020019080838360005b83811015610d51578082015181840152602081019050610d36565b50505050905090810190601f168015610d7e5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a150505050505050565b6000806000806000339350856040518082805190602001908083835b602083101515610dd95780518252602082019150602081019050602083039250610db4565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020925060016000846000191660001916815260200190815260200160002091508373ffffffffffffffffffffffffffffffffffffffff168260010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610e8357600080fd5b8160030154905080945050505050919050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610ef357600080fd5b3073ffffffffffffffffffffffffffffffffffffffff1631905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610f6d57600080fd5b8173ffffffffffffffffffffffffffffffffffffffff16319050919050565b6000806000806000339450349350856040518082805190602001908083835b602083101515610fd05780518252602082019150602081019050602083039250610fab565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020925060016000846000191660001916815260200190815260200160002091506001600581111561102957fe5b8260000160009054906101000a900460ff16600581111561104657fe5b14151561105257600080fd5b8160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905060028260000160006101000a81548160ff0219169083600581111561109a57fe5b0217905550848260000160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508382600301819055507fa348b54485f1d38cacb4b427a2dbc5bf2067cb3ba1024d6a4213a018939984848686838760405180806020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828103825286818151815260200191508051906020019080838360005b838110156111ba57808201518184015260208101905061119f565b50505050905090810190601f1680156111e75780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a1505050505050565b611207611d62565b600080600080600080600061121a611d76565b60008060008060003397508e6040518082805190602001908083835b60208310151561125b5780518252602082019150602081019050602083039250611236565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020965060016000886000191660001916815260200190815260200160002060a060405190810160405290816000820160009054906101000a900460ff1660058111156112cf57fe5b60058111156112da57fe5b81526020016000820160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600382015481525050955085604001519450856000015193506000600581111561140757fe5b84600581111561141357fe5b1480611434575060058081111561142657fe5b84600581111561143257fe5b145b8061146a57508773ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff16145b151561147557600080fd5b8560200151925085606001519150856080015190508e83868487859d509d509d509d509d509d50505050505050505091939550919395565b600080600080339350846040518082805190602001908083835b6020831015156114ec57805182526020820191506020810190506020830392506114c7565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020925060016000846000191660001916815260200190815260200160002091508160000160009054906101000a900460ff1690506002600581111561155957fe5b81600581111561156557fe5b1415801561158a57506003600581111561157b57fe5b81600581111561158757fe5b14155b80156115ad57506004600581111561159e57fe5b8160058111156115aa57fe5b14155b15156115b857600080fd5b60018260000160006101000a81548160ff021916908360058111156115d957fe5b0217905550838260010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000826003018190555060008260000160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008260020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050151561171457600080fd5b7f50f74ca45caac8020b8d891bd13ea5a2d79564986ee6a839f0d914896388322d858560405180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156117a957808201518184015260208101905061178e565b50505050905090810190601f1680156117d65780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15050505050565b6117f3611d62565b6000806000806000806000611806611d76565b60008060008060003397508e6040518082805190602001908083835b6020831015156118475780518252602082019150602081019050602083039250611822565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020965060016000886000191660001916815260200190815260200160002060a060405190810160405290816000820160009054906101000a900460ff1660058111156118bb57fe5b60058111156118c657fe5b81526020016000820160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200160038201548152505095508560200151945085600001519350600060058111156119f357fe5b8460058111156119ff57fe5b1480611a205750600580811115611a1257fe5b846005811115611a1e57fe5b145b80611a5657508773ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff16145b1515611a6157600080fd5b8560400151925085606001519150856080015190508e85848487859d509d509d509d509d509d50505050505050505091939550919395565b6000806000806000339450856040518082805190602001908083835b602083101515611ada5780518252602082019150602081019050602083039250611ab5565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020935060016000856000191660001916815260200190815260200160002092508473ffffffffffffffffffffffffffffffffffffffff168360010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515611b8457600080fd5b60026005811115611b9157fe5b8360000160009054906101000a900460ff166005811115611bae57fe5b141515611bba57600080fd5b8260000160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915060048360000160006101000a81548160ff02191690836005811115611c0257fe5b0217905550826003015490508173ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f193505050501515611c4e57600080fd5b7fdc718f88216244ce6f5aa6e9d6645d1e1363a9abf3fa7f56d210ff26dd6a88b88683878460405180806020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828103825286818151815260200191508051906020019080838360005b83811015611d1d578082015181840152602081019050611d02565b50505050905090810190601f168015611d4a5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a1505050505050565b602060405190810160405280600081525090565b60a06040519081016040528060006005811115611d8f57fe5b8152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff1681526020016000815250905600a165627a7a72305820a3e76e753279469038f4ba7ad07b87c7bde4bb39efc2d656649812eb0fd111cb0029";

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

    public RemoteCall<Tuple6<String, String, String, String, BigInteger, BigInteger>> realtorStatus(String uuid) {
        final Function function = new Function("realtorStatus", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple6<String, String, String, String, BigInteger, BigInteger>>(
                new Callable<Tuple6<String, String, String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<String, String, String, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple6<String, String, String, String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> register(String uuid, BigInteger weiValue) {
        Function function = new Function(
                "register", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple6<String, String, String, String, BigInteger, BigInteger>> buyerStatus(String uuid) {
        final Function function = new Function("buyerStatus", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple6<String, String, String, String, BigInteger, BigInteger>>(
                new Callable<Tuple6<String, String, String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<String, String, String, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple6<String, String, String, String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
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
