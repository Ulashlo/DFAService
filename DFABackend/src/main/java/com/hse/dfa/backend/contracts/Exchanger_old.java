package com.hse.dfa.backend.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
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
public class Exchanger_old extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b50610e92806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c806303fbeab81461003b57806374bc9bc214610057575b600080fd5b61005560048036038101906100509190610a9d565b610089565b005b610071600480360381019061006c9190610b18565b6106e2565b60405161008093929190610b89565b60405180910390f35b60008060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020905060005b818054905081101561039757600082828154811061012c5761012b610bc0565b5b90600052602060002090600302019050600081600101541480610153575060008160020154145b1561015e5750610384565b85816001015461016e9190610c1e565b84826002015461017e9190610c1e565b141561038257806001015484116102875761024960405180606001604052808a73ffffffffffffffffffffffffffffffffffffffff1681526020018773ffffffffffffffffffffffffffffffffffffffff1681526020018681525060405180606001604052808460000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018a73ffffffffffffffffffffffffffffffffffffffff16815260200189815250610756565b8381600101600082825461025d9190610c78565b92505081905550858160020160008282546102789190610c78565b925050819055505050506106db565b61034960405180606001604052808a73ffffffffffffffffffffffffffffffffffffffff1681526020018773ffffffffffffffffffffffffffffffffffffffff168152602001836001015481525060405180606001604052808460000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018a73ffffffffffffffffffffffffffffffffffffffff1681526020018460020154815250610756565b8060010154866103599190610c78565b955080600201548461036b9190610c78565b935060008160010181905550600081600201819055505b505b808061038f90610cac565b91505061010b565b5060008060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020905060005b818054905081101561051657600082828154811061043b5761043a610bc0565b5b906000526020600020906003020190508873ffffffffffffffffffffffffffffffffffffffff168160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161480156104ae575060008160020154145b80156104be575060008160010154145b1561050257848160020160008282546104d79190610cf5565b92505081905550868160010160008282546104f29190610cf5565b92505081905550505050506106db565b50808061050e90610cac565b91505061041a565b5060005b818054905081101561061f57600082828154811061053b5761053a610bc0565b5b906000526020600020906003020190508873ffffffffffffffffffffffffffffffffffffffff168160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161480156105c757508681600101546105b59190610c1e565b8582600201546105c59190610c1e565b145b1561060b57848160020160008282546105e09190610cf5565b92505081905550868160010160008282546105fb9190610cf5565b92505081905550505050506106db565b50808061061790610cac565b91505061051a565b508060405180606001604052808973ffffffffffffffffffffffffffffffffffffffff16815260200187815260200185815250908060018154018082558091505060019003906000526020600020906003020160009091909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001015560408201518160020155505050505b5050505050565b6000602052826000526040600020602052816000526040600020818154811061070a57600080fd5b906000526020600020906003020160009250925050508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010154908060020154905083565b816000816020015190508073ffffffffffffffffffffffffffffffffffffffff166370a0823183600001516040518263ffffffff1660e01b815260040161079d9190610d4b565b602060405180830381865afa1580156107ba573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107de9190610d7b565b82604001511115610824576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161081b90610e05565b60405180910390fd5b826000816020015190508073ffffffffffffffffffffffffffffffffffffffff166370a0823183600001516040518263ffffffff1660e01b815260040161086b9190610d4b565b602060405180830381865afa158015610888573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108ac9190610d7b565b826040015111156108f2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108e990610e05565b60405180910390fd5b6000866020015190506000866020015190508173ffffffffffffffffffffffffffffffffffffffff16636389c556896000015189600001518b604001516040518463ffffffff1660e01b815260040161094d93929190610e25565b600060405180830381600087803b15801561096757600080fd5b505af115801561097b573d6000803e3d6000fd5b505050508073ffffffffffffffffffffffffffffffffffffffff16636389c55688600001518a600001518a604001516040518463ffffffff1660e01b81526004016109c893929190610e25565b600060405180830381600087803b1580156109e257600080fd5b505af11580156109f6573d6000803e3d6000fd5b505050505050505050505050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610a3482610a09565b9050919050565b610a4481610a29565b8114610a4f57600080fd5b50565b600081359050610a6181610a3b565b92915050565b6000819050919050565b610a7a81610a67565b8114610a8557600080fd5b50565b600081359050610a9781610a71565b92915050565b600080600080600060a08688031215610ab957610ab8610a04565b5b6000610ac788828901610a52565b9550506020610ad888828901610a52565b9450506040610ae988828901610a88565b9350506060610afa88828901610a52565b9250506080610b0b88828901610a88565b9150509295509295909350565b600080600060608486031215610b3157610b30610a04565b5b6000610b3f86828701610a52565b9350506020610b5086828701610a52565b9250506040610b6186828701610a88565b9150509250925092565b610b7481610a29565b82525050565b610b8381610a67565b82525050565b6000606082019050610b9e6000830186610b6b565b610bab6020830185610b7a565b610bb86040830184610b7a565b949350505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610c2982610a67565b9150610c3483610a67565b9250817fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0483118215151615610c6d57610c6c610bef565b5b828202905092915050565b6000610c8382610a67565b9150610c8e83610a67565b925082821015610ca157610ca0610bef565b5b828203905092915050565b6000610cb782610a67565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415610cea57610ce9610bef565b5b600182019050919050565b6000610d0082610a67565b9150610d0b83610a67565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115610d4057610d3f610bef565b5b828201905092915050565b6000602082019050610d606000830184610b6b565b92915050565b600081519050610d7581610a71565b92915050565b600060208284031215610d9157610d90610a04565b5b6000610d9f84828501610d66565b91505092915050565b600082825260208201905092915050565b7f446f6573206e6f7420656e6f7567682064666100000000000000000000000000600082015250565b6000610def601383610da8565b9150610dfa82610db9565b602082019050919050565b60006020820190508181036000830152610e1e81610de2565b9050919050565b6000606082019050610e3a6000830186610b6b565b610e476020830185610b6b565b610e546040830184610b7a565b94935050505056fea2646970667358221220e84520e9c075ac717c0485eeac77bf63c3a52944f5ab688b6c830cb11129768864736f6c634300080b0033";

    public static final String FUNC_REQUESTS = "requests";

    public static final String FUNC_ADDEXCHANGEREQUEST = "addExchangeRequest";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected Exchanger_old(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Exchanger_old(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Exchanger_old(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Exchanger_old(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>> requests(String param0, String param1, BigInteger param2) {
        final Function function = new Function(FUNC_REQUESTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.Address(param1), 
                new org.web3j.abi.datatypes.generated.Uint256(param2)), 
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

    public RemoteFunctionCall<TransactionReceipt> addExchangeRequest(String user, String dfaToGet, BigInteger amountToGet, String dfaToGive, BigInteger amountToGive) {
        final Function function = new Function(
                FUNC_ADDEXCHANGEREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(user), 
                new org.web3j.abi.datatypes.Address(dfaToGet), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGet), 
                new org.web3j.abi.datatypes.Address(dfaToGive), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGive)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Exchanger_old load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Exchanger_old(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Exchanger_old load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Exchanger_old(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Exchanger_old load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Exchanger_old(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Exchanger_old load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Exchanger_old(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Exchanger_old> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Exchanger_old.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Exchanger_old> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Exchanger_old.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Exchanger_old> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Exchanger_old.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Exchanger_old> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Exchanger_old.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}
