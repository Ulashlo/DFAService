package com.hse.dfa.backend.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Exchanger extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b50604051620020183803806200201883398181016040528101906200003791906200019e565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415620000ab576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401620000a29062000231565b60405180910390fd5b816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505062000253565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620001668262000139565b9050919050565b620001788162000159565b81146200018457600080fd5b50565b60008151905062000198816200016d565b92915050565b600060208284031215620001b757620001b662000134565b5b6000620001c78482850162000187565b91505092915050565b600082825260208201905092915050565b7f496e76616c696420616464726573730000000000000000000000000000000000600082015250565b600062000219600f83620001d0565b91506200022682620001e1565b602082019050919050565b600060208201905081810360008301526200024c816200020a565b9050919050565b611db580620002636000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c8063873deb041161005b578063873deb04146100ea5780638c75d7b414610108578063966dae0e146101395780639ecebe2a146101575761007d565b80630c884bde1461008257806354dbc21e146100b25780635f1b9c28146100ce575b600080fd5b61009c60048036038101906100979190611487565b610189565b6040516100a991906114e9565b60405180910390f35b6100cc60048036038101906100c79190611487565b610401565b005b6100e860048036038101906100e3919061160d565b610c3e565b005b6100f2611089565b6040516100ff91906114e9565b60405180910390f35b610122600480360381019061011d919061163a565b6110ad565b604051610130929190611725565b60405180910390f35b610141611357565b60405161014e91906114e9565b60405180910390f35b610171600480360381019061016c919061175c565b61137d565b604051610180939291906117ab565b60405180910390f35b60008282600082116101d0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101c79061183f565b60405180910390fd5b60008111610213576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161020a9061183f565b60405180910390fd5b85600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610284576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027b906118ab565b60405180910390fd5b6000600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b8282101561038157838290600052602060002090600302016040518060600160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200160018201548152602001600282015481525050815260200190600101906102e5565b50505050905060005b81518110156103f05760008282815181106103a8576103a76118cb565b5b602002602001015190508881602001511480156103c85750878160400151145b156103dc57806000015196505050506103f7565b5080806103e890611929565b91505061038a565b5060009450505b5050509392505050565b808260008211610446576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161043d9061183f565b60405180910390fd5b60008111610489576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104809061183f565b60405180910390fd5b84600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156104fa576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104f1906118ab565b60405180910390fd5b85600073ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f1d7fa7836040518263ffffffff1660e01b815260040161056e91906114e9565b602060405180830381865afa15801561058b573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105af9190611987565b73ffffffffffffffffffffffffffffffffffffffff161415610606576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105fd90611a00565b60405180910390fd5b8460008054906101000a900473ffffffffffffffffffffffffffffffffffffffff16818173ffffffffffffffffffffffffffffffffffffffff166370a08231336040518263ffffffff1660e01b815260040161066291906114e9565b602060405180830381865afa15801561067f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106a39190611a35565b10156106e4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106db90611aae565b60405180910390fd5b818173ffffffffffffffffffffffffffffffffffffffff1663dd62ed3e33306040518363ffffffff1660e01b8152600401610720929190611ace565b602060405180830381865afa15801561073d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107619190611a35565b10156107a2576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161079990611b43565b60405180910390fd5b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f1d7fa78b6040518263ffffffff1660e01b81526004016107ff91906114e9565b602060405180830381865afa15801561081c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108409190611987565b9050600081905060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd33308c6040518463ffffffff1660e01b81526004016108a493929190611b63565b6020604051808303816000875af11580156108c3573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108e79190611bd2565b5060008173ffffffffffffffffffffffffffffffffffffffff16630c884bde60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff168c8e6040518463ffffffff1660e01b8152600401610947939291906117ab565b602060405180830381865afa158015610964573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109889190611987565b9050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614610b395760008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb828c6040518363ffffffff1660e01b8152600401610a19929190611bff565b6020604051808303816000875af1158015610a38573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610a5c9190611bd2565b508173ffffffffffffffffffffffffffffffffffffffff16635f1b9c2860405180608001604052803373ffffffffffffffffffffffffffffffffffffffff16815260200160008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018d81526020018e8152506040518263ffffffff1660e01b8152600401610b029190611c8c565b600060405180830381600087803b158015610b1c57600080fd5b505af1158015610b30573d6000803e3d6000fd5b50505050610c30565b600260008d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060405180606001604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018d81526020018c815250908060018154018082558091505060019003906000526020600020906003020160009091909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550602082015181600101556040820151816002015550505b505050505050505050505050565b8060400151816060015160008211610c8b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c829061183f565b60405180910390fd5b60008111610cce576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610cc59061183f565b60405180910390fd5b8260200151600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610d43576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d3a906118ab565b60405180910390fd5b83602001513373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f1d7fa7836040518263ffffffff1660e01b8152600401610dba91906114e9565b602060405180830381865afa158015610dd7573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610dfb9190611987565b73ffffffffffffffffffffffffffffffffffffffff1614610e51576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610e4890611cf3565b60405180910390fd5b8460000151600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610ec6576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ebd906118ab565b60405180910390fd5b600060026000886020015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090506000805b828054905081101561103e576000838281548110610f3257610f316118cb565b5b9060005260206000209060030201905089604001518160010154148015610f60575089606001518160020154145b1561102a57600081600101819055506000816002018190555060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb8b600001518c606001516040518363ffffffff1660e01b8152600401610fdc929190611bff565b6020604051808303816000875af1158015610ffb573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061101f9190611bd2565b50600192505061103e565b50808061103690611929565b915050610f11565b508061107f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161107690611d5f565b60405180910390fd5b5050505050505050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60608082600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415611121576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611118906118ab565b60405180910390fd5b6000600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b8282101561121e57838290600052602060002090600302016040518060600160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820154815260200160028201548152505081526020019060010190611182565b5050505090506000815167ffffffffffffffff8111156112415761124061151a565b5b60405190808252806020026020018201604052801561126f5781602001602082028036833780820191505090505b5090506000825167ffffffffffffffff81111561128f5761128e61151a565b5b6040519080825280602002602001820160405280156112bd5781602001602082028036833780820191505090505b50905060005b83518110156113475760008482815181106112e1576112e06118cb565b5b602002602001015190508060200151848381518110611303576113026118cb565b5b6020026020010181815250508060400151838381518110611327576113266118cb565b5b60200260200101818152505050808061133f90611929565b9150506112c3565b5081819550955050505050915091565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6002602052816000526040600020818154811061139957600080fd5b9060005260206000209060030201600091509150508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010154908060020154905083565b6000604051905090565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061141e826113f3565b9050919050565b61142e81611413565b811461143957600080fd5b50565b60008135905061144b81611425565b92915050565b6000819050919050565b61146481611451565b811461146f57600080fd5b50565b6000813590506114818161145b565b92915050565b6000806000606084860312156114a05761149f6113ee565b5b60006114ae8682870161143c565b93505060206114bf86828701611472565b92505060406114d086828701611472565b9150509250925092565b6114e381611413565b82525050565b60006020820190506114fe60008301846114da565b92915050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b61155282611509565b810181811067ffffffffffffffff821117156115715761157061151a565b5b80604052505050565b60006115846113e4565b90506115908282611549565b919050565b6000608082840312156115ab576115aa611504565b5b6115b5608061157a565b905060006115c58482850161143c565b60008301525060206115d98482850161143c565b60208301525060406115ed84828501611472565b604083015250606061160184828501611472565b60608301525092915050565b600060808284031215611623576116226113ee565b5b600061163184828501611595565b91505092915050565b6000602082840312156116505761164f6113ee565b5b600061165e8482850161143c565b91505092915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b61169c81611451565b82525050565b60006116ae8383611693565b60208301905092915050565b6000602082019050919050565b60006116d282611667565b6116dc8185611672565b93506116e783611683565b8060005b838110156117185781516116ff88826116a2565b975061170a836116ba565b9250506001810190506116eb565b5085935050505092915050565b6000604082019050818103600083015261173f81856116c7565b9050818103602083015261175381846116c7565b90509392505050565b60008060408385031215611773576117726113ee565b5b60006117818582860161143c565b925050602061179285828601611472565b9150509250929050565b6117a581611451565b82525050565b60006060820190506117c060008301866114da565b6117cd602083018561179c565b6117da604083018461179c565b949350505050565b600082825260208201905092915050565b7f4e756d62657220697320657175616c20746f207a65726f000000000000000000600082015250565b60006118296017836117e2565b9150611834826117f3565b602082019050919050565b600060208201905081810360008301526118588161181c565b9050919050565b7f496e76616c696420616464726573730000000000000000000000000000000000600082015250565b6000611895600f836117e2565b91506118a08261185f565b602082019050919050565b600060208201905081810360008301526118c481611888565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061193482611451565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415611967576119666118fa565b5b600182019050919050565b60008151905061198181611425565b92915050565b60006020828403121561199d5761199c6113ee565b5b60006119ab84828501611972565b91505092915050565b7f45786368616e67657220666f7220646661206e6f7420666f756e640000000000600082015250565b60006119ea601b836117e2565b91506119f5826119b4565b602082019050919050565b60006020820190508181036000830152611a19816119dd565b9050919050565b600081519050611a2f8161145b565b92915050565b600060208284031215611a4b57611a4a6113ee565b5b6000611a5984828501611a20565b91505092915050565b7f53656e64657220646f6573206e6f74206861766520656e6f7567682064666100600082015250565b6000611a98601f836117e2565b9150611aa382611a62565b602082019050919050565b60006020820190508181036000830152611ac781611a8b565b9050919050565b6000604082019050611ae360008301856114da565b611af060208301846114da565b9392505050565b7f416d6f756e74206f6620444641206973206e6f7420616c6c6f77656400000000600082015250565b6000611b2d601c836117e2565b9150611b3882611af7565b602082019050919050565b60006020820190508181036000830152611b5c81611b20565b9050919050565b6000606082019050611b7860008301866114da565b611b8560208301856114da565b611b92604083018461179c565b949350505050565b60008115159050919050565b611baf81611b9a565b8114611bba57600080fd5b50565b600081519050611bcc81611ba6565b92915050565b600060208284031215611be857611be76113ee565b5b6000611bf684828501611bbd565b91505092915050565b6000604082019050611c1460008301856114da565b611c21602083018461179c565b9392505050565b611c3181611413565b82525050565b608082016000820151611c4d6000850182611c28565b506020820151611c606020850182611c28565b506040820151611c736040850182611693565b506060820151611c866060850182611693565b50505050565b6000608082019050611ca16000830184611c37565b92915050565b7f53656e646572206973206e6f742065786368616e676572000000000000000000600082015250565b6000611cdd6017836117e2565b9150611ce882611ca7565b602082019050919050565b60006020820190508181036000830152611d0c81611cd0565b9050919050565b7f56616c69642072657175657374206e6f7420666f756e64000000000000000000600082015250565b6000611d496017836117e2565b9150611d5482611d13565b602082019050919050565b60006020820190508181036000830152611d7881611d3c565b905091905056fea264697066735822122023e1d5de9505b0626ec4aaebc9718cb44a144fedd6843cefb80cd351ebad486d64736f6c634300080b0033";

    public static final String FUNC_DFAADDRESS = "dfaAddress";

    public static final String FUNC_FACTORYADDRESS = "factoryAddress";

    public static final String FUNC_REQUESTS = "requests";

    public static final String FUNC_GETCORRECTREQUESTUSER = "getCorrectRequestUser";

    public static final String FUNC_ADDREQUEST = "addRequest";

    public static final String FUNC_TRYTOEXCHANGE = "tryToExchange";

    public static final String FUNC_GETREQUESTSBYDFA = "getRequestsByDfa";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0xae8F718CC3A233f8025979eB5143A55aDc45Adc0");
    }

    @Deprecated
    protected Exchanger(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Exchanger(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Exchanger(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Exchanger(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> dfaAddress() {
        final Function function = new Function(FUNC_DFAADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> factoryAddress() {
        final Function function = new Function(FUNC_FACTORYADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>> requests(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_REQUESTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> getCorrectRequestUser(String dfaToGive, BigInteger amountToGive, BigInteger amountToGet) {
        final Function function = new Function(FUNC_GETCORRECTREQUESTUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfaToGive), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGive), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGet)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addRequest(String dfaToGet, BigInteger amountToGet, BigInteger amountToGive) {
        final Function function = new Function(
                FUNC_ADDREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfaToGet), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGet), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGive)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> tryToExchange(InnerExchangerRequestInfo info) {
        final Function function = new Function(
                FUNC_TRYTOEXCHANGE, 
                Arrays.<Type>asList(info), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<List<BigInteger>, List<BigInteger>>> getRequestsByDfa(String dfa) {
        final Function function = new Function(FUNC_GETREQUESTSBYDFA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfa)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<Tuple2<List<BigInteger>, List<BigInteger>>>(function,
                new Callable<Tuple2<List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple2<List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<BigInteger>, List<BigInteger>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()));
                    }
                });
    }

    @Deprecated
    public static Exchanger load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Exchanger(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Exchanger load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Exchanger(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Exchanger load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Exchanger(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Exchanger load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Exchanger(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Exchanger> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String dfaAddressToLink) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfaAddressToLink)));
        return deployRemoteCall(Exchanger.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Exchanger> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String dfaAddressToLink) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfaAddressToLink)));
        return deployRemoteCall(Exchanger.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Exchanger> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String dfaAddressToLink) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfaAddressToLink)));
        return deployRemoteCall(Exchanger.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Exchanger> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String dfaAddressToLink) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfaAddressToLink)));
        return deployRemoteCall(Exchanger.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class InnerExchangerRequestInfo extends StaticStruct {
        public String user;

        public String dfaToGive;

        public BigInteger amountToGive;

        public BigInteger amountToGet;

        public InnerExchangerRequestInfo(String user, String dfaToGive, BigInteger amountToGive, BigInteger amountToGet) {
            super(new org.web3j.abi.datatypes.Address(user),new org.web3j.abi.datatypes.Address(dfaToGive),new org.web3j.abi.datatypes.generated.Uint256(amountToGive),new org.web3j.abi.datatypes.generated.Uint256(amountToGet));
            this.user = user;
            this.dfaToGive = dfaToGive;
            this.amountToGive = amountToGive;
            this.amountToGet = amountToGet;
        }

        public InnerExchangerRequestInfo(Address user, Address dfaToGive, Uint256 amountToGive, Uint256 amountToGet) {
            super(user,dfaToGive,amountToGive,amountToGet);
            this.user = user.getValue();
            this.dfaToGive = dfaToGive.getValue();
            this.amountToGive = amountToGive.getValue();
            this.amountToGet = amountToGet.getValue();
        }
    }
}
