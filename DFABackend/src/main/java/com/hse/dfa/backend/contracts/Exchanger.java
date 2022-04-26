package com.hse.dfa.backend.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
    public static final String BINARY = "0x60806040523480156200001157600080fd5b50604051620024493803806200244983398181016040528101906200003791906200019e565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415620000ab576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401620000a29062000231565b60405180910390fd5b816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505062000253565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620001668262000139565b9050919050565b620001788162000159565b81146200018457600080fd5b50565b60008151905062000198816200016d565b92915050565b600060208284031215620001b757620001b662000134565b5b6000620001c78482850162000187565b91505092915050565b600082825260208201905092915050565b7f496e76616c696420616464726573730000000000000000000000000000000000600082015250565b600062000219600f83620001d0565b91506200022682620001e1565b602082019050919050565b600060208201905081810360008301526200024c816200020a565b9050919050565b6121e680620002636000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806374adad1d1161005b57806374adad1d146100ea578063873deb041461011a5780638c75d7b414610138578063966dae0e1461016a5761007d565b80630c884bde1461008257806354dbc21e146100b25780635f1b9c28146100ce575b600080fd5b61009c6004803603810190610097919061172e565b610188565b6040516100a99190611790565b60405180910390f35b6100cc60048036038101906100c7919061172e565b61042c565b005b6100e860048036038101906100e391906118b4565b610cef565b005b61010460048036038101906100ff91906118e1565b611239565b604051610111919061191d565b60405180910390f35b610122611257565b60405161012f9190611790565b60405180910390f35b610152600480360381019061014d91906118e1565b61127b565b60405161016193929190611ab4565b60405180910390f35b610172611665565b60405161017f9190611790565b60405180910390f35b60008282600082116101cf576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101c690611b5d565b60405180910390fd5b60008111610212576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161020990611b5d565b60405180910390fd5b85600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610283576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027a90611bc9565b60405180910390fd5b6000600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001805480602002602001604051908101604052809291908181526020016000905b8282101561039e57838290600052602060002090600402016040518060800160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200160018201548152602001600282015481526020016003820160009054906101000a900460ff161515151581525050815260200190600101906102e7565b50505050905060005b815181101561041b5760008282815181106103c5576103c4611be9565b5b6020026020010151905080606001511580156103e45750888160200151145b80156103f35750878160400151145b156104075780600001519650505050610422565b50808061041390611c47565b9150506103a7565b5060009450505b5050509392505050565b808260008211610471576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161046890611b5d565b60405180910390fd5b600081116104b4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104ab90611b5d565b60405180910390fd5b84600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610525576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161051c90611bc9565b60405180910390fd5b85600073ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f1d7fa7836040518263ffffffff1660e01b81526004016105999190611790565b602060405180830381865afa1580156105b6573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105da9190611ca5565b73ffffffffffffffffffffffffffffffffffffffff161415610631576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161062890611d1e565b60405180910390fd5b8460008054906101000a900473ffffffffffffffffffffffffffffffffffffffff16818173ffffffffffffffffffffffffffffffffffffffff166370a08231336040518263ffffffff1660e01b815260040161068d9190611790565b602060405180830381865afa1580156106aa573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106ce9190611d53565b101561070f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161070690611dcc565b60405180910390fd5b818173ffffffffffffffffffffffffffffffffffffffff1663dd62ed3e33306040518363ffffffff1660e01b815260040161074b929190611dec565b602060405180830381865afa158015610768573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061078c9190611d53565b10156107cd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107c490611e61565b60405180910390fd5b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f1d7fa78b6040518263ffffffff1660e01b815260040161082a9190611790565b602060405180830381865afa158015610847573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061086b9190611ca5565b9050600081905060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd33308c6040518463ffffffff1660e01b81526004016108cf93929190611e81565b6020604051808303816000875af11580156108ee573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109129190611ef0565b5060008173ffffffffffffffffffffffffffffffffffffffff16630c884bde60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff168c8e6040518463ffffffff1660e01b815260040161097293929190611f1d565b602060405180830381865afa15801561098f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109b39190611ca5565b9050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614610b645760008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb828c6040518363ffffffff1660e01b8152600401610a44929190611f54565b6020604051808303816000875af1158015610a63573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610a879190611ef0565b508173ffffffffffffffffffffffffffffffffffffffff16635f1b9c2860405180608001604052803373ffffffffffffffffffffffffffffffffffffffff16815260200160008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018d81526020018e8152506040518263ffffffff1660e01b8152600401610b2d9190611fd2565b600060405180830381600087803b158015610b4757600080fd5b505af1158015610b5b573d6000803e3d6000fd5b50505050610ce1565b600260008d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160405180608001604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018d81526020018c815260200160001515815250908060018154018082558091505060019003906000526020600020906004020160009091909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550602082015181600101556040820151816002015560608201518160030160006101000a81548160ff02191690831515021790555050506001600260008e73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001016000828254610cd99190611fed565b925050819055505b505050505050505050505050565b8060400151816060015160008211610d3c576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d3390611b5d565b60405180910390fd5b60008111610d7f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d7690611b5d565b60405180910390fd5b8260200151600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610df4576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610deb90611bc9565b60405180910390fd5b83602001513373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f1d7fa7836040518263ffffffff1660e01b8152600401610e6b9190611790565b602060405180830381865afa158015610e88573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610eac9190611ca5565b73ffffffffffffffffffffffffffffffffffffffff1614610f02576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ef99061208f565b60405180910390fd5b8460000151600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610f77576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610f6e90611bc9565b60405180910390fd5b600060026000886020015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000190506000805b82805490508110156111ee576000838281548110610fe657610fe5611be9565b5b9060005260206000209060040201905089604001518160010154148015611014575089606001518160020154145b156111da5760018160030160006101000a81548160ff02191690831515021790555060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb8b600001518c606001516040518363ffffffff1660e01b8152600401611099929190611f54565b6020604051808303816000875af11580156110b8573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906110dc9190611ef0565b50600192506001600260008c6020015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101600082825461113891906120af565b925050819055507f3fcd82abd7b4aa2dae161019e066c1909e297f3a20b3b4757ef4aa36b1a122428160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683600201548d600001518e602001518f604001516040516111cc969594939291906120e3565b60405180910390a1506111ee565b5080806111e690611c47565b915050610fc5565b508061122f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161122690612190565b60405180910390fd5b5050505050505050565b60026020528060005260406000206000915090508060010154905081565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b606080606083600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156112f1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016112e890611bc9565b60405180910390fd5b6000600260008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001805480602002602001604051908101604052809291908181526020016000905b8282101561140c57838290600052602060002090600402016040518060800160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200160018201548152602001600282015481526020016003820160009054906101000a900460ff16151515158152505081526020019060010190611355565b5050505090506000600260008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010154905060008167ffffffffffffffff811115611475576114746117c1565b5b6040519080825280602002602001820160405280156114a35781602001602082028036833780820191505090505b50905060008267ffffffffffffffff8111156114c2576114c16117c1565b5b6040519080825280602002602001820160405280156114f05781602001602082028036833780820191505090505b50905060008367ffffffffffffffff81111561150f5761150e6117c1565b5b60405190808252806020026020018201604052801561153d5781602001602082028036833780820191505090505b5090506000805b865181101561164d578681815181106115605761155f611be9565b5b60200260200101516060015161163a57600087828151811061158557611584611be9565b5b6020026020010151905080600001518684815181106115a7576115a6611be9565b5b602002602001019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff168152505080602001518584815181106115f9576115f8611be9565b5b602002602001018181525050806040015184848151811061161d5761161c611be9565b5b6020026020010181815250506001836116369190611fed565b9250505b808061164590611c47565b915050611544565b50838383995099509950505050505050509193909250565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000604051905090565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006116c58261169a565b9050919050565b6116d5816116ba565b81146116e057600080fd5b50565b6000813590506116f2816116cc565b92915050565b6000819050919050565b61170b816116f8565b811461171657600080fd5b50565b60008135905061172881611702565b92915050565b60008060006060848603121561174757611746611695565b5b6000611755868287016116e3565b935050602061176686828701611719565b925050604061177786828701611719565b9150509250925092565b61178a816116ba565b82525050565b60006020820190506117a56000830184611781565b92915050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6117f9826117b0565b810181811067ffffffffffffffff82111715611818576118176117c1565b5b80604052505050565b600061182b61168b565b905061183782826117f0565b919050565b600060808284031215611852576118516117ab565b5b61185c6080611821565b9050600061186c848285016116e3565b6000830152506020611880848285016116e3565b602083015250604061189484828501611719565b60408301525060606118a884828501611719565b60608301525092915050565b6000608082840312156118ca576118c9611695565b5b60006118d88482850161183c565b91505092915050565b6000602082840312156118f7576118f6611695565b5b6000611905848285016116e3565b91505092915050565b611917816116f8565b82525050565b6000602082019050611932600083018461190e565b92915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b61196d816116ba565b82525050565b600061197f8383611964565b60208301905092915050565b6000602082019050919050565b60006119a382611938565b6119ad8185611943565b93506119b883611954565b8060005b838110156119e95781516119d08882611973565b97506119db8361198b565b9250506001810190506119bc565b5085935050505092915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b611a2b816116f8565b82525050565b6000611a3d8383611a22565b60208301905092915050565b6000602082019050919050565b6000611a61826119f6565b611a6b8185611a01565b9350611a7683611a12565b8060005b83811015611aa7578151611a8e8882611a31565b9750611a9983611a49565b925050600181019050611a7a565b5085935050505092915050565b60006060820190508181036000830152611ace8186611998565b90508181036020830152611ae28185611a56565b90508181036040830152611af68184611a56565b9050949350505050565b600082825260208201905092915050565b7f4e756d62657220697320657175616c20746f207a65726f000000000000000000600082015250565b6000611b47601783611b00565b9150611b5282611b11565b602082019050919050565b60006020820190508181036000830152611b7681611b3a565b9050919050565b7f496e76616c696420616464726573730000000000000000000000000000000000600082015250565b6000611bb3600f83611b00565b9150611bbe82611b7d565b602082019050919050565b60006020820190508181036000830152611be281611ba6565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000611c52826116f8565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415611c8557611c84611c18565b5b600182019050919050565b600081519050611c9f816116cc565b92915050565b600060208284031215611cbb57611cba611695565b5b6000611cc984828501611c90565b91505092915050565b7f45786368616e67657220666f7220646661206e6f7420666f756e640000000000600082015250565b6000611d08601b83611b00565b9150611d1382611cd2565b602082019050919050565b60006020820190508181036000830152611d3781611cfb565b9050919050565b600081519050611d4d81611702565b92915050565b600060208284031215611d6957611d68611695565b5b6000611d7784828501611d3e565b91505092915050565b7f53656e64657220646f6573206e6f74206861766520656e6f7567682064666100600082015250565b6000611db6601f83611b00565b9150611dc182611d80565b602082019050919050565b60006020820190508181036000830152611de581611da9565b9050919050565b6000604082019050611e016000830185611781565b611e0e6020830184611781565b9392505050565b7f416d6f756e74206f6620444641206973206e6f7420616c6c6f77656400000000600082015250565b6000611e4b601c83611b00565b9150611e5682611e15565b602082019050919050565b60006020820190508181036000830152611e7a81611e3e565b9050919050565b6000606082019050611e966000830186611781565b611ea36020830185611781565b611eb0604083018461190e565b949350505050565b60008115159050919050565b611ecd81611eb8565b8114611ed857600080fd5b50565b600081519050611eea81611ec4565b92915050565b600060208284031215611f0657611f05611695565b5b6000611f1484828501611edb565b91505092915050565b6000606082019050611f326000830186611781565b611f3f602083018561190e565b611f4c604083018461190e565b949350505050565b6000604082019050611f696000830185611781565b611f76602083018461190e565b9392505050565b608082016000820151611f936000850182611964565b506020820151611fa66020850182611964565b506040820151611fb96040850182611a22565b506060820151611fcc6060850182611a22565b50505050565b6000608082019050611fe76000830184611f7d565b92915050565b6000611ff8826116f8565b9150612003836116f8565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111561203857612037611c18565b5b828201905092915050565b7f53656e646572206973206e6f742065786368616e676572000000000000000000600082015250565b6000612079601783611b00565b915061208482612043565b602082019050919050565b600060208201905081810360008301526120a88161206c565b9050919050565b60006120ba826116f8565b91506120c5836116f8565b9250828210156120d8576120d7611c18565b5b828203905092915050565b600060c0820190506120f86000830189611781565b6121056020830188611781565b612112604083018761190e565b61211f6060830186611781565b61212c6080830185611781565b61213960a083018461190e565b979650505050505050565b7f56616c69642072657175657374206e6f7420666f756e64000000000000000000600082015250565b600061217a601783611b00565b915061218582612144565b602082019050919050565b600060208201905081810360008301526121a98161216d565b905091905056fea26469706673582212209e5b9f3646681e54f2a3b374451a0d76987854085208118ad495ce50040b9b3564736f6c634300080b0033";

    public static final String FUNC_DFAADDRESS = "dfaAddress";

    public static final String FUNC_FACTORYADDRESS = "factoryAddress";

    public static final String FUNC_REQUESTS = "requests";

    public static final String FUNC_GETCORRECTREQUESTUSER = "getCorrectRequestUser";

    public static final String FUNC_ADDREQUEST = "addRequest";

    public static final String FUNC_TRYTOEXCHANGE = "tryToExchange";

    public static final String FUNC_GETREQUESTSBYDFA = "getRequestsByDfa";

    public static final Event EXCHANGECOMPLETED_EVENT = new Event("ExchangeCompleted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

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

    public List<ExchangeCompletedEventResponse> getExchangeCompletedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EXCHANGECOMPLETED_EVENT, transactionReceipt);
        ArrayList<ExchangeCompletedEventResponse> responses = new ArrayList<ExchangeCompletedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ExchangeCompletedEventResponse typedResponse = new ExchangeCompletedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.firstUser = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.firstDfa = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.firstAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.secondUser = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.secondDfa = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.secondAmount = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ExchangeCompletedEventResponse> exchangeCompletedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ExchangeCompletedEventResponse>() {
            @Override
            public ExchangeCompletedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EXCHANGECOMPLETED_EVENT, log);
                ExchangeCompletedEventResponse typedResponse = new ExchangeCompletedEventResponse();
                typedResponse.log = log;
                typedResponse.firstUser = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.firstDfa = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.firstAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.secondUser = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.secondDfa = (String) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.secondAmount = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ExchangeCompletedEventResponse> exchangeCompletedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EXCHANGECOMPLETED_EVENT));
        return exchangeCompletedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> dfaAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DFAADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> factoryAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FACTORYADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> requests(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REQUESTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getCorrectRequestUser(String dfaToGive, BigInteger amountToGive, BigInteger amountToGet) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCORRECTREQUESTUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfaToGive), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGive), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGet)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addRequest(String dfaToGet, BigInteger amountToGet, BigInteger amountToGive) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfaToGet), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGet), 
                new org.web3j.abi.datatypes.generated.Uint256(amountToGive)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> tryToExchange(InnerExchangerRequestInfo info) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRYTOEXCHANGE, 
                Arrays.<Type>asList(info), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<List<String>, List<BigInteger>, List<BigInteger>>> getRequestsByDfa(String dfa) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETREQUESTSBYDFA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(dfa)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<Tuple3<List<String>, List<BigInteger>, List<BigInteger>>>(function,
                new Callable<Tuple3<List<String>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple3<List<String>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<List<String>, List<BigInteger>, List<BigInteger>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()));
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

    public static class ExchangeCompletedEventResponse extends BaseEventResponse {
        public String firstUser;

        public String firstDfa;

        public BigInteger firstAmount;

        public String secondUser;

        public String secondDfa;

        public BigInteger secondAmount;
    }
}
