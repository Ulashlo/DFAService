package com.hse.dfa.backend.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
    public static final String BINARY = "0x60806040523480156200001157600080fd5b5060405162001fa138038062001fa18339818101604052810190620000379190620005af565b8282816003908051906020019062000051929190620002c2565b5080600490805190602001906200006a929190620002c2565b50505080600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415620000e1576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401620000d890620006c0565b60405180910390fd5b620000f382866200013f60201b60201c565b81600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050505062000873565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415620001b2576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401620001a99062000732565b60405180910390fd5b620001c660008383620002b860201b60201c565b8060026000828254620001da919062000783565b92505081905550806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825462000231919062000783565b925050819055508173ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef83604051620002989190620007f1565b60405180910390a3620002b460008383620002bd60201b60201c565b5050565b505050565b505050565b828054620002d0906200083d565b90600052602060002090601f016020900481019282620002f4576000855562000340565b82601f106200030f57805160ff191683800117855562000340565b8280016001018555821562000340579182015b828111156200033f57825182559160200191906001019062000322565b5b5090506200034f919062000353565b5090565b5b808211156200036e57600081600090555060010162000354565b5090565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b6200039b8162000386565b8114620003a757600080fd5b50565b600081519050620003bb8162000390565b92915050565b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6200041682620003cb565b810181811067ffffffffffffffff82111715620004385762000437620003dc565b5b80604052505050565b60006200044d62000372565b90506200045b82826200040b565b919050565b600067ffffffffffffffff8211156200047e576200047d620003dc565b5b6200048982620003cb565b9050602081019050919050565b60005b83811015620004b657808201518184015260208101905062000499565b83811115620004c6576000848401525b50505050565b6000620004e3620004dd8462000460565b62000441565b905082815260208101848484011115620005025762000501620003c6565b5b6200050f84828562000496565b509392505050565b600082601f8301126200052f576200052e620003c1565b5b815162000541848260208601620004cc565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600062000577826200054a565b9050919050565b62000589816200056a565b81146200059557600080fd5b50565b600081519050620005a9816200057e565b92915050565b60008060008060808587031215620005cc57620005cb6200037c565b5b6000620005dc87828801620003aa565b945050602085015167ffffffffffffffff8111156200060057620005ff62000381565b5b6200060e8782880162000517565b935050604085015167ffffffffffffffff81111562000632576200063162000381565b5b620006408782880162000517565b9250506060620006538782880162000598565b91505092959194509250565b600082825260208201905092915050565b7f496e76616c696420746f6b656e20616464726573730000000000000000000000600082015250565b6000620006a86015836200065f565b9150620006b58262000670565b602082019050919050565b60006020820190508181036000830152620006db8162000699565b9050919050565b7f45524332303a206d696e7420746f20746865207a65726f206164647265737300600082015250565b60006200071a601f836200065f565b91506200072782620006e2565b602082019050919050565b600060208201905081810360008301526200074d816200070b565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000620007908262000386565b91506200079d8362000386565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115620007d557620007d462000754565b5b828201905092915050565b620007eb8162000386565b82525050565b6000602082019050620008086000830184620007e0565b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806200085657607f821691505b602082108114156200086d576200086c6200080e565b5b50919050565b61171e80620008836000396000f3fe608060405234801561001057600080fd5b50600436106100cf5760003560e01c806370a082311161008c578063a0712d6811610066578063a0712d681461022a578063a457c2d714610246578063a9059cbb14610276578063dd62ed3e146102a6576100cf565b806370a08231146101be5780638da5cb5b146101ee57806395d89b411461020c576100cf565b806306fdde03146100d4578063095ea7b3146100f257806318160ddd1461012257806323b872dd14610140578063313ce56714610170578063395093511461018e575b600080fd5b6100dc6102d6565b6040516100e99190610ea9565b60405180910390f35b61010c60048036038101906101079190610f64565b610368565b6040516101199190610fbf565b60405180910390f35b61012a61038b565b6040516101379190610fe9565b60405180910390f35b61015a60048036038101906101559190611004565b610395565b6040516101679190610fbf565b60405180910390f35b6101786103c4565b6040516101859190611073565b60405180910390f35b6101a860048036038101906101a39190610f64565b6103c9565b6040516101b59190610fbf565b60405180910390f35b6101d860048036038101906101d3919061108e565b610473565b6040516101e59190610fe9565b60405180910390f35b6101f66104bb565b60405161020391906110ca565b60405180910390f35b6102146104e1565b6040516102219190610ea9565b60405180910390f35b610244600480360381019061023f91906110e5565b610573565b005b610260600480360381019061025b9190610f64565b610632565b60405161026d9190610fbf565b60405180910390f35b610290600480360381019061028b9190610f64565b61071c565b60405161029d9190610fbf565b60405180910390f35b6102c060048036038101906102bb9190611112565b61073f565b6040516102cd9190610fe9565b60405180910390f35b6060600380546102e590611181565b80601f016020809104026020016040519081016040528092919081815260200182805461031190611181565b801561035e5780601f106103335761010080835404028352916020019161035e565b820191906000526020600020905b81548152906001019060200180831161034157829003601f168201915b5050505050905090565b6000806103736107c6565b90506103808185856107ce565b600191505092915050565b6000600254905090565b6000806103a06107c6565b90506103ad858285610999565b6103b8858585610a25565b60019150509392505050565b600090565b6000806103d46107c6565b9050610468818585600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461046391906111e2565b6107ce565b600191505092915050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6060600480546104f090611181565b80601f016020809104026020016040519081016040528092919081815260200182805461051c90611181565b80156105695780601f1061053e57610100808354040283529160200191610569565b820191906000526020600020905b81548152906001019060200180831161054c57829003601f168201915b5050505050905090565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610603576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105fa90611284565b60405180910390fd5b61062f600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1682610ca6565b50565b60008061063d6107c6565b90506000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905083811015610703576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106fa90611316565b60405180910390fd5b61071082868684036107ce565b60019250505092915050565b6000806107276107c6565b9050610734818585610a25565b600191505092915050565b6000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b600033905090565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16141561083e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610835906113a8565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156108ae576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108a59061143a565b60405180910390fd5b80600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9258360405161098c9190610fe9565b60405180910390a3505050565b60006109a5848461073f565b90507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8114610a1f5781811015610a11576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a08906114a6565b60405180910390fd5b610a1e84848484036107ce565b5b50505050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415610a95576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a8c90611538565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610b05576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610afc906115ca565b60405180910390fd5b610b10838383610e06565b60008060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905081811015610b96576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b8d9061165c565b60405180910390fd5b8181036000808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550816000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254610c2991906111e2565b925050819055508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef84604051610c8d9190610fe9565b60405180910390a3610ca0848484610e0b565b50505050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610d16576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d0d906116c8565b60405180910390fd5b610d2260008383610e06565b8060026000828254610d3491906111e2565b92505081905550806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254610d8991906111e2565b925050819055508173ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef83604051610dee9190610fe9565b60405180910390a3610e0260008383610e0b565b5050565b505050565b505050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610e4a578082015181840152602081019050610e2f565b83811115610e59576000848401525b50505050565b6000601f19601f8301169050919050565b6000610e7b82610e10565b610e858185610e1b565b9350610e95818560208601610e2c565b610e9e81610e5f565b840191505092915050565b60006020820190508181036000830152610ec38184610e70565b905092915050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610efb82610ed0565b9050919050565b610f0b81610ef0565b8114610f1657600080fd5b50565b600081359050610f2881610f02565b92915050565b6000819050919050565b610f4181610f2e565b8114610f4c57600080fd5b50565b600081359050610f5e81610f38565b92915050565b60008060408385031215610f7b57610f7a610ecb565b5b6000610f8985828601610f19565b9250506020610f9a85828601610f4f565b9150509250929050565b60008115159050919050565b610fb981610fa4565b82525050565b6000602082019050610fd46000830184610fb0565b92915050565b610fe381610f2e565b82525050565b6000602082019050610ffe6000830184610fda565b92915050565b60008060006060848603121561101d5761101c610ecb565b5b600061102b86828701610f19565b935050602061103c86828701610f19565b925050604061104d86828701610f4f565b9150509250925092565b600060ff82169050919050565b61106d81611057565b82525050565b60006020820190506110886000830184611064565b92915050565b6000602082840312156110a4576110a3610ecb565b5b60006110b284828501610f19565b91505092915050565b6110c481610ef0565b82525050565b60006020820190506110df60008301846110bb565b92915050565b6000602082840312156110fb576110fa610ecb565b5b600061110984828501610f4f565b91505092915050565b6000806040838503121561112957611128610ecb565b5b600061113785828601610f19565b925050602061114885828601610f19565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061119957607f821691505b602082108114156111ad576111ac611152565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006111ed82610f2e565b91506111f883610f2e565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111561122d5761122c6111b3565b5b828201905092915050565b7f43616c6c6572206973206e6f74206f776e657200000000000000000000000000600082015250565b600061126e601383610e1b565b915061127982611238565b602082019050919050565b6000602082019050818103600083015261129d81611261565b9050919050565b7f45524332303a2064656372656173656420616c6c6f77616e63652062656c6f7760008201527f207a65726f000000000000000000000000000000000000000000000000000000602082015250565b6000611300602583610e1b565b915061130b826112a4565b604082019050919050565b6000602082019050818103600083015261132f816112f3565b9050919050565b7f45524332303a20617070726f76652066726f6d20746865207a65726f2061646460008201527f7265737300000000000000000000000000000000000000000000000000000000602082015250565b6000611392602483610e1b565b915061139d82611336565b604082019050919050565b600060208201905081810360008301526113c181611385565b9050919050565b7f45524332303a20617070726f766520746f20746865207a65726f20616464726560008201527f7373000000000000000000000000000000000000000000000000000000000000602082015250565b6000611424602283610e1b565b915061142f826113c8565b604082019050919050565b6000602082019050818103600083015261145381611417565b9050919050565b7f45524332303a20696e73756666696369656e7420616c6c6f77616e6365000000600082015250565b6000611490601d83610e1b565b915061149b8261145a565b602082019050919050565b600060208201905081810360008301526114bf81611483565b9050919050565b7f45524332303a207472616e736665722066726f6d20746865207a65726f20616460008201527f6472657373000000000000000000000000000000000000000000000000000000602082015250565b6000611522602583610e1b565b915061152d826114c6565b604082019050919050565b6000602082019050818103600083015261155181611515565b9050919050565b7f45524332303a207472616e7366657220746f20746865207a65726f206164647260008201527f6573730000000000000000000000000000000000000000000000000000000000602082015250565b60006115b4602383610e1b565b91506115bf82611558565b604082019050919050565b600060208201905081810360008301526115e3816115a7565b9050919050565b7f45524332303a207472616e7366657220616d6f756e742065786365656473206260008201527f616c616e63650000000000000000000000000000000000000000000000000000602082015250565b6000611646602683610e1b565b9150611651826115ea565b604082019050919050565b6000602082019050818103600083015261167581611639565b9050919050565b7f45524332303a206d696e7420746f20746865207a65726f206164647265737300600082015250565b60006116b2601f83610e1b565b91506116bd8261167c565b602082019050919050565b600060208201905081810360008301526116e1816116a5565b905091905056fea2646970667358221220fd468f260e50d12ac0a7ae51627436e785af5aaebd2aa852735083b55b21913564736f6c634300080b0033";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_DECIMALS = "decimals";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x8c1B60A0A11f98a10C4e26fCcc1585DEdE36f5B4");
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

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(owner), 
                new org.web3j.abi.datatypes.Address(spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(spender), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(spender), 
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
                new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public static RemoteCall<DFA> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialSupply, String name, String symbol, String dfaOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.Address(dfaOwner)));
        return deployRemoteCall(DFA.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<DFA> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialSupply, String name, String symbol, String dfaOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.Address(dfaOwner)));
        return deployRemoteCall(DFA.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<DFA> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialSupply, String name, String symbol, String dfaOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.Address(dfaOwner)));
        return deployRemoteCall(DFA.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<DFA> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialSupply, String name, String symbol, String dfaOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.Address(dfaOwner)));
        return deployRemoteCall(DFA.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
