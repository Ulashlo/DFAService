package com.hse.dfa.backend.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
public class DFA extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b5033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008081905550610361806100686000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806327e235e3146100465780638da5cb5b14610076578063a0712d6814610094575b600080fd5b610060600480360381019061005b91906101c2565b6100b0565b60405161006d9190610208565b60405180910390f35b61007e6100c8565b60405161008b9190610232565b60405180910390f35b6100ae60048036038101906100a99190610279565b6100ee565b005b60026020528060005260406000206000915090505481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b806000808282546100ff91906102d5565b9250508190555080600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825461015591906102d5565b9250508190555050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061018f82610164565b9050919050565b61019f81610184565b81146101aa57600080fd5b50565b6000813590506101bc81610196565b92915050565b6000602082840312156101d8576101d761015f565b5b60006101e6848285016101ad565b91505092915050565b6000819050919050565b610202816101ef565b82525050565b600060208201905061021d60008301846101f9565b92915050565b61022c81610184565b82525050565b60006020820190506102476000830184610223565b92915050565b610256816101ef565b811461026157600080fd5b50565b6000813590506102738161024d565b92915050565b60006020828403121561028f5761028e61015f565b5b600061029d84828501610264565b91505092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006102e0826101ef565b91506102eb836101ef565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff038211156103205761031f6102a6565b5b82820190509291505056fea26469706673582212204c6127e646680c18b6a4c1bac3272b709de40da86c31bce1c9e039d8022554ca64736f6c634300080b0033";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_MINT = "mint";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x43B2D8E733188df614f3074990A48349bc569ed1");
    }

    @Deprecated
    protected DFA(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DFA(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DFA(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DFA(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> balances(String param0) {
        final Function function = new Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(BigInteger amount) {
        final Function function = new Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static DFA load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DFA(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DFA load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DFA(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DFA load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DFA(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DFA load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DFA(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DFA> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DFA.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<DFA> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DFA.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DFA> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DFA.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DFA> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DFA.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}
