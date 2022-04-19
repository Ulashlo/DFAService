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
    public static final String BINARY = "0x60806040523480156200001157600080fd5b5060405162001f1438038062001f1483398181016040528101906200003791906200019e565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415620000ab576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401620000a29062000231565b60405180910390fd5b816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505062000253565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620001668262000139565b9050919050565b620001788162000159565b81146200018457600080fd5b50565b60008151905062000198816200016d565b92915050565b600060208284031215620001b757620001b662000134565b5b6000620001c78482850162000187565b91505092915050565b600082825260208201905092915050565b7f496e76616c696420616464726573730000000000000000000000000000000000600082015250565b600062000219600f83620001d0565b91506200022682620001e1565b602082019050919050565b600060208201905081810360008301526200024c816200020a565b9050919050565b611cb180620002636000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c8063873deb041161005b578063873deb04146100ea5780638c75d7b414610108578063966dae0e146101385780639ecebe2a146101565761007d565b80630c884bde1461008257806354dbc21e146100b25780635f1b9c28146100ce575b600080fd5b61009c60048036038101906100979190611356565b610188565b6040516100a991906113b8565b60405180910390f35b6100cc60048036038101906100c79190611356565b610400565b005b6100e860048036038101906100e391906114dc565b610c3d565b005b6100f2611088565b6040516100ff91906113b8565b60405180910390f35b610122600480360381019061011d9190611509565b6110ac565b60405161012f9190611645565b60405180910390f35b610140611226565b60405161014d91906113b8565b60405180910390f35b610170600480360381019061016b9190611667565b61124c565b60405161017f939291906116b6565b60405180910390f35b60008282600082116101cf576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101c69061174a565b60405180910390fd5b60008111610212576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102099061174a565b60405180910390fd5b85600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610283576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027a906117b6565b60405180910390fd5b6000600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b8282101561038057838290600052602060002090600302016040518060600160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200160018201548152602001600282015481525050815260200190600101906102e4565b50505050905060005b81518110156103ef5760008282815181106103a7576103a66117d6565b5b602002602001015190508881602001511480156103c75750878160400151145b156103db57806000015196505050506103f6565b5080806103e790611834565b915050610389565b5060009450505b5050509392505050565b808260008211610445576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161043c9061174a565b60405180910390fd5b60008111610488576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161047f9061174a565b60405180910390fd5b84600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156104f9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104f0906117b6565b60405180910390fd5b85600073ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f1d7fa7836040518263ffffffff1660e01b815260040161056d91906113b8565b602060405180830381865afa15801561058a573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105ae9190611892565b73ffffffffffffffffffffffffffffffffffffffff161415610605576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105fc9061190b565b60405180910390fd5b8460008054906101000a900473ffffffffffffffffffffffffffffffffffffffff16818173ffffffffffffffffffffffffffffffffffffffff166370a08231336040518263ffffffff1660e01b815260040161066191906113b8565b602060405180830381865afa15801561067e573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106a29190611940565b10156106e3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106da906119b9565b60405180910390fd5b818173ffffffffffffffffffffffffffffffffffffffff1663dd62ed3e33306040518363ffffffff1660e01b815260040161071f9291906119d9565b602060405180830381865afa15801561073c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107609190611940565b10156107a1576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161079890611a4e565b60405180910390fd5b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f1d7fa78b6040518263ffffffff1660e01b81526004016107fe91906113b8565b602060405180830381865afa15801561081b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061083f9190611892565b9050600081905060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd33308c6040518463ffffffff1660e01b81526004016108a393929190611a6e565b6020604051808303816000875af11580156108c2573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108e69190611add565b5060008173ffffffffffffffffffffffffffffffffffffffff16630c884bde60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff168c8e6040518463ffffffff1660e01b8152600401610946939291906116b6565b602060405180830381865afa158015610963573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109879190611892565b9050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614610b385760008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb828c6040518363ffffffff1660e01b8152600401610a18929190611b0a565b6020604051808303816000875af1158015610a37573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610a5b9190611add565b508173ffffffffffffffffffffffffffffffffffffffff16635f1b9c2860405180608001604052803373ffffffffffffffffffffffffffffffffffffffff16815260200160008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018d81526020018e8152506040518263ffffffff1660e01b8152600401610b019190611b88565b600060405180830381600087803b158015610b1b57600080fd5b505af1158015610b2f573d6000803e3d6000fd5b50505050610c2f565b600260008d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060405180606001604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018d81526020018c815250908060018154018082558091505060019003906000526020600020906003020160009091909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550602082015181600101556040820151816002015550505b505050505050505050505050565b8060400151816060015160008211610c8a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c819061174a565b60405180910390fd5b60008111610ccd576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610cc49061174a565b60405180910390fd5b8260200151600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610d42576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d39906117b6565b60405180910390fd5b83602001513373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f1d7fa7836040518263ffffffff1660e01b8152600401610db991906113b8565b602060405180830381865afa158015610dd6573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610dfa9190611892565b73ffffffffffffffffffffffffffffffffffffffff1614610e50576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610e4790611bef565b60405180910390fd5b8460000151600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610ec5576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ebc906117b6565b60405180910390fd5b600060026000886020015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090506000805b828054905081101561103d576000838281548110610f3157610f306117d6565b5b9060005260206000209060030201905089604001518160010154148015610f5f575089606001518160020154145b1561102957600081600101819055506000816002018190555060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb8b600001518c606001516040518363ffffffff1660e01b8152600401610fdb929190611b0a565b6020604051808303816000875af1158015610ffa573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061101e9190611add565b50600192505061103d565b50808061103590611834565b915050610f10565b508061107e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161107590611c5b565b60405180910390fd5b5050505050505050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b606081600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16141561111f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611116906117b6565b60405180910390fd5b600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b8282101561121a57838290600052602060002090600302016040518060600160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600182015481526020016002820154815250508152602001906001019061117e565b50505050915050919050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6002602052816000526040600020818154811061126857600080fd5b9060005260206000209060030201600091509150508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010154908060020154905083565b6000604051905090565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006112ed826112c2565b9050919050565b6112fd816112e2565b811461130857600080fd5b50565b60008135905061131a816112f4565b92915050565b6000819050919050565b61133381611320565b811461133e57600080fd5b50565b6000813590506113508161132a565b92915050565b60008060006060848603121561136f5761136e6112bd565b5b600061137d8682870161130b565b935050602061138e86828701611341565b925050604061139f86828701611341565b9150509250925092565b6113b2816112e2565b82525050565b60006020820190506113cd60008301846113a9565b92915050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b611421826113d8565b810181811067ffffffffffffffff821117156114405761143f6113e9565b5b80604052505050565b60006114536112b3565b905061145f8282611418565b919050565b60006080828403121561147a576114796113d3565b5b6114846080611449565b905060006114948482850161130b565b60008301525060206114a88482850161130b565b60208301525060406114bc84828501611341565b60408301525060606114d084828501611341565b60608301525092915050565b6000608082840312156114f2576114f16112bd565b5b600061150084828501611464565b91505092915050565b60006020828403121561151f5761151e6112bd565b5b600061152d8482850161130b565b91505092915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b61156b816112e2565b82525050565b61157a81611320565b82525050565b6060820160008201516115966000850182611562565b5060208201516115a96020850182611571565b5060408201516115bc6040850182611571565b50505050565b60006115ce8383611580565b60608301905092915050565b6000602082019050919050565b60006115f282611536565b6115fc8185611541565b935061160783611552565b8060005b8381101561163857815161161f88826115c2565b975061162a836115da565b92505060018101905061160b565b5085935050505092915050565b6000602082019050818103600083015261165f81846115e7565b905092915050565b6000806040838503121561167e5761167d6112bd565b5b600061168c8582860161130b565b925050602061169d85828601611341565b9150509250929050565b6116b081611320565b82525050565b60006060820190506116cb60008301866113a9565b6116d860208301856116a7565b6116e560408301846116a7565b949350505050565b600082825260208201905092915050565b7f4e756d62657220697320657175616c20746f207a65726f000000000000000000600082015250565b60006117346017836116ed565b915061173f826116fe565b602082019050919050565b6000602082019050818103600083015261176381611727565b9050919050565b7f496e76616c696420616464726573730000000000000000000000000000000000600082015250565b60006117a0600f836116ed565b91506117ab8261176a565b602082019050919050565b600060208201905081810360008301526117cf81611793565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061183f82611320565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82141561187257611871611805565b5b600182019050919050565b60008151905061188c816112f4565b92915050565b6000602082840312156118a8576118a76112bd565b5b60006118b68482850161187d565b91505092915050565b7f45786368616e67657220666f7220646661206e6f7420666f756e640000000000600082015250565b60006118f5601b836116ed565b9150611900826118bf565b602082019050919050565b60006020820190508181036000830152611924816118e8565b9050919050565b60008151905061193a8161132a565b92915050565b600060208284031215611956576119556112bd565b5b60006119648482850161192b565b91505092915050565b7f53656e64657220646f6573206e6f74206861766520656e6f7567682064666100600082015250565b60006119a3601f836116ed565b91506119ae8261196d565b602082019050919050565b600060208201905081810360008301526119d281611996565b9050919050565b60006040820190506119ee60008301856113a9565b6119fb60208301846113a9565b9392505050565b7f416d6f756e74206f6620444641206973206e6f7420616c6c6f77656400000000600082015250565b6000611a38601c836116ed565b9150611a4382611a02565b602082019050919050565b60006020820190508181036000830152611a6781611a2b565b9050919050565b6000606082019050611a8360008301866113a9565b611a9060208301856113a9565b611a9d60408301846116a7565b949350505050565b60008115159050919050565b611aba81611aa5565b8114611ac557600080fd5b50565b600081519050611ad781611ab1565b92915050565b600060208284031215611af357611af26112bd565b5b6000611b0184828501611ac8565b91505092915050565b6000604082019050611b1f60008301856113a9565b611b2c60208301846116a7565b9392505050565b608082016000820151611b496000850182611562565b506020820151611b5c6020850182611562565b506040820151611b6f6040850182611571565b506060820151611b826060850182611571565b50505050565b6000608082019050611b9d6000830184611b33565b92915050565b7f53656e646572206973206e6f742065786368616e676572000000000000000000600082015250565b6000611bd96017836116ed565b9150611be482611ba3565b602082019050919050565b60006020820190508181036000830152611c0881611bcc565b9050919050565b7f56616c69642072657175657374206e6f7420666f756e64000000000000000000600082015250565b6000611c456017836116ed565b9150611c5082611c0f565b602082019050919050565b60006020820190508181036000830152611c7481611c38565b905091905056fea26469706673582212208033a67c2563a8e897703fdf39eba0534c7245e25f58e43b591decb786629b9d64736f6c634300080b0033";

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

    public RemoteFunctionCall<List> getRequestsByDfa(String dfa) {
        final Function function = new Function(FUNC_GETREQUESTSBYDFA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfa)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<ExchangerRequestInfo>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
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
