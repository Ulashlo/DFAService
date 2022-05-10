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
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
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
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple6;
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
    public static final String BINARY = "0x608060405234801561001057600080fd5b506040516200207c3803806200207c833981016040819052610031916100ad565b806001600160a01b03811661007e5760405162461bcd60e51b815260206004820152600f60248201526e496e76616c6964206164647265737360881b604482015260640160405180910390fd5b50600080546001600160a01b039092166001600160a01b031992831617905560018054909116331790556100dd565b6000602082840312156100bf57600080fd5b81516001600160a01b03811681146100d657600080fd5b9392505050565b611f8f80620000ed6000396000f3fe608060405234801561001057600080fd5b506004361061006d5760003560e01c80636bef7a2b14610072578063873deb04146100875780638c75d7b4146100b0578063966dae0e146100d25780639ecebe2a146100e5578063c31f27101461010a578063d550a5d41461011d575b600080fd5b610085610080366004611a16565b610178565b005b60005461009a906001600160a01b031681565b6040516100a79190611a78565b60405180910390f35b6100c36100be366004611a8c565b610d29565b6040516100a793929190611ae4565b60015461009a906001600160a01b031681565b6100f86100f3366004611b59565b6110ed565b6040516100a796959493929190611bb9565b610085610118366004611c07565b611152565b61013061012b366004611c68565b61147b565b6040516100a791908151151581526020808301516001600160a01b03169082015260408083015190820152606080830151908201526080918201519181019190915260a00190565b80606001518160400151600082116101ab5760405162461bcd60e51b81526004016101a290611cfd565b60405180910390fd5b600081116101cb5760405162461bcd60e51b81526004016101a290611cfd565b60208301516001600160a01b0381166101f65760405162461bcd60e51b81526004016101a290611d2e565b6020840151600154604051632f1d7fa760e01b81526000916001600160a01b031690632f1d7fa79061022c908590600401611a78565b602060405180830381865afa158015610249573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061026d9190611d57565b6001600160a01b031614156102c25760405162461bcd60e51b815260206004820152601b60248201527a115e18da185b99d95c88199bdc88191998481b9bdd08199bdd5b99602a1b60448201526064016101a2565b60608501516000546040516370a0823160e01b815282916001600160a01b0316906370a08231906102f7903390600401611a78565b602060405180830381865afa158015610314573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103389190611d74565b10156103865760405162461bcd60e51b815260206004820152601f60248201527f53656e64657220646f6573206e6f74206861766520656e6f756768206466610060448201526064016101a2565b600054604051636eb1769f60e11b815233600482015230602482015282916001600160a01b03169063dd62ed3e90604401602060405180830381865afa1580156103d4573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103f89190611d74565b10156104455760405162461bcd60e51b815260206004820152601c60248201527b105b5bdd5b9d081bd988111190481a5cc81b9bdd08185b1b1bddd95960221b60448201526064016101a2565b6001546020870151604051632f1d7fa760e01b81526000926001600160a01b031691632f1d7fa79161047a9190600401611a78565b602060405180830381865afa158015610497573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104bb9190611d57565b60005460608901516040516323b872dd60e01b8152336004820152306024820152604481019190915291925082916001600160a01b03909116906323b872dd906064016020604051808303816000875af115801561051d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105419190611d9d565b506000816001600160a01b031663d550a5d460405180608001604052808c60000151600181111561057457610574611b85565b8152600080546001600160a01b031660208301526060808f01516040808501919091528f810151919093015290516001600160e01b031960e085901b1681526105c1929190600401611ded565b60a060405180830381865afa1580156105de573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106029190611e08565b905060008951600181111561061957610619611b85565b14156108775780511561074757600054602082015160608b015160405163a9059cbb60e01b81526001600160a01b039093169263a9059cbb92610660929091600401611e6a565b6020604051808303816000875af115801561067f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106a39190611d9d565b506040805160a0810182526000546001600160a01b0390811682526060808d015160208401528c8401518385015284840151908301523360808301529151630c31f27160e41b81529184169163c31f27109161070191600401611e83565b600060405180830381600087803b15801561071b57600080fd5b505af115801561072f573d6000803e3d6000fd5b5050600060608c0181905260408c015250610d1e9050565b600260008a602001516001600160a01b03166001600160a01b031681526020019081526020016000206040518060c001604052808b60000151600181111561079157610791611b85565b815233602082015260408c810151908201526060808d0151908201526080808d01519082015260a00160009052815460018181018455600093845260209093208251600590920201805492939092839160ff199091169083818111156107f9576107f9611b85565b0217905550602082015181546001600160a01b0390911661010002610100600160a81b03199091161781556040820151600180830191909155606083015160028301556080830151600383015560a08301516004830180549192909160ff191690838181111561086b5761086b611b85565b02179055505050610d1e565b805115610bd557886060015181606001511115801561089e57508860400151816080015111155b156109eb576000546020820151606083015160405163a9059cbb60e01b81526001600160a01b039093169263a9059cbb926108dd929091600401611e6a565b6020604051808303816000875af11580156108fc573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109209190611d9d565b506040805160a0810182526000546001600160a01b039081168252606080850151602084015260808086015184860152858501519184019190915233908301529151630c31f27160e41b81529184169163c31f27109161098291600401611e83565b600060405180830381600087803b15801561099c57600080fd5b505af11580156109b0573d6000803e3d6000fd5b505050508060600151896060018181516109ca9190611eda565b905250608081015160408a0180516109e3908390611eda565b905250610b07565b600054602082015160608b015160405163a9059cbb60e01b81526001600160a01b039093169263a9059cbb92610a25929091600401611e6a565b6020604051808303816000875af1158015610a44573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610a689190611d9d565b506040805160a0810182526000546001600160a01b0390811682526060808d015160208401528c8401518385015284840151908301523360808301529151630c31f27160e41b81529184169163c31f271091610ac691600401611e83565b600060405180830381600087803b158015610ae057600080fd5b505af1158015610af4573d6000803e3d6000fd5b5050600060608c0181905260408c015250505b816001600160a01b031663d550a5d460405180608001604052808c600001516001811115610b3757610b37611b85565b81526000546001600160a01b031660208201526060808e01516040808401919091528e8101519190920152840151610b70906001611ef1565b6040518363ffffffff1660e01b8152600401610b8d929190611ded565b60a060405180830381865afa158015610baa573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610bce9190611e08565b9050610877565b60008960400151118015610bed575060008960600151115b15610d1e57600260008a602001516001600160a01b03166001600160a01b031681526020019081526020016000206040518060c001604052808b600001516001811115610c3c57610c3c611b85565b815233602082015260408c810151908201526060808d0151908201526080808d01519082015260a00160009052815460018181018455600093845260209093208251600590920201805492939092839160ff19909116908381811115610ca457610ca4611b85565b0217905550602082015181546001600160a01b0390911661010002610100600160a81b03199091161781556040820151600180830191909155606083015160028301556080830151600383015560a08301516004830180549192909160ff1916908381811115610d1657610d16611b85565b021790555050505b505050505050505050565b60608080836001600160a01b038116610d545760405162461bcd60e51b81526004016101a290611d2e565b6001600160a01b038516600090815260026020908152604080832080548251818502810185019093528083529192909190849084015b82821015610e4e576000848152602090206040805160c08101909152600584029091018054829060ff166001811115610dc557610dc5611b85565b6001811115610dd657610dd6611b85565b8152815461010090046001600160a01b0316602082015260018083015460408301526002830154606083015260038301546080830152600483015460a09092019160ff1690811115610e2a57610e2a611b85565b6001811115610e3b57610e3b611b85565b8152505081526020019060010190610d8a565b5050505090506000805b8251811015610ee1576000838281518110610e7557610e75611f09565b602002602001015160a001516001811115610e9257610e92611b85565b148015610ebc575042838281518110610ead57610ead611f09565b60200260200101516080015110155b15610ecf5781610ecb81611f1f565b9250505b80610ed981611f1f565b915050610e58565b506000816001600160401b03811115610efc57610efc61199e565b604051908082528060200260200182016040528015610f25578160200160208202803683370190505b5090506000826001600160401b03811115610f4257610f4261199e565b604051908082528060200260200182016040528015610f6b578160200160208202803683370190505b5090506000836001600160401b03811115610f8857610f8861199e565b604051908082528060200260200182016040528015610fb1578160200160208202803683370190505b5090506000805b86518110156110dc576000878281518110610fd557610fd5611f09565b602002602001015160a001516001811115610ff257610ff2611b85565b14801561101c57504287828151811061100d5761100d611f09565b60200260200101516080015110155b156110ca57600087828151811061103557611035611f09565b60200260200101519050806020015186848151811061105657611056611f09565b60200260200101906001600160a01b031690816001600160a01b031681525050806040015185848151811061108d5761108d611f09565b60200260200101818152505080606001518484815181106110b0576110b0611f09565b60209081029190910101526110c6600184611ef1565b9250505b806110d481611f1f565b915050610fb8565b50929a919950975095505050505050565b6002602052816000526040600020818154811061110957600080fd5b60009182526020909120600590910201805460018201546002830154600384015460049094015460ff80851697506101009094046001600160a01b031695509193909290911686565b806020015181604001516000821161117c5760405162461bcd60e51b81526004016101a290611cfd565b6000811161119c5760405162461bcd60e51b81526004016101a290611cfd565b82516001600160a01b0381166111c45760405162461bcd60e51b81526004016101a290611d2e565b8351600154604051632f1d7fa760e01b815233916001600160a01b031690632f1d7fa7906111f6908590600401611a78565b602060405180830381865afa158015611213573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906112379190611d57565b6001600160a01b0316146112875760405162461bcd60e51b815260206004820152601760248201527629b2b73232b91034b9903737ba1032bc31b430b733b2b960491b60448201526064016101a2565b60808501516001600160a01b0381166112b25760405162461bcd60e51b81526004016101a290611d2e565b85516001600160a01b031660009081526002602052604081206060880151815481106112e0576112e0611f09565b9060005260206000209060050201905086602001518160010160008282546113089190611eda565b90915550506040870151600282018054600090611326908490611eda565b9091555060009050815460ff16600181111561134457611344611b85565b148061135257506001810154155b8061135f57506002810154155b156113745760048101805460ff191660011790555b60005460808801516040808a0151905163a9059cbb60e01b81526001600160a01b039093169263a9059cbb926113ae929091600401611e6a565b6020604051808303816000875af11580156113cd573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906113f19190611d9d565b508054600054600283015460808a8101518b516020808e0151604080516001600160a01b03610100909a048a16815297891692880192909252868201959095529186166060860152949094169083015260a082015290517f3fcd82abd7b4aa2dae161019e066c1909e297f3a20b3b4757ef4aa36b1a122429181900360c00190a150505050505050565b6114b86040518060a0016040528060001515815260200160006001600160a01b031681526020016000815260200160008152602001600081525090565b82604001518360600151600082116114e25760405162461bcd60e51b81526004016101a290611cfd565b600081116115025760405162461bcd60e51b81526004016101a290611cfd565b60208501516001600160a01b03811661152d5760405162461bcd60e51b81526004016101a290611d2e565b6020808701516001600160a01b031660009081526002825260408082208054825181860281018601909352808352929391929091849084015b8282101561162a576000848152602090206040805160c08101909152600584029091018054829060ff1660018111156115a1576115a1611b85565b60018111156115b2576115b2611b85565b8152815461010090046001600160a01b0316602082015260018083015460408301526002830154606083015260038301546080830152600483015460a09092019160ff169081111561160657611606611b85565b600181111561161757611617611b85565b8152505081526020019060010190611566565b5092935088925050505b81518110156118c057600082828151811061165157611651611f09565b6020026020010151905060018081111561166d5761166d611b85565b8160a00151600181111561168357611683611b85565b14806116925750428160800151105b1561169d57506118ae565b600080825160018111156116b3576116b3611b85565b1480156116d2575060008a5160018111156116d0576116d0611b85565b145b156117205761171960405180604001604052808c6060015181526020018c604001518152506040518060400160405280856040015181526020018560600151815250611905565b905061185b565b60008251600181111561173557611735611b85565b148015611754575060018a51600181111561175257611752611b85565b145b1561179b5761171960405180604001604052808c6060015181526020018c604001518152506040518060400160405280856040015181526020018560600151815250611927565b6001825160018111156117b0576117b0611b85565b1480156117cf575060008a5160018111156117cd576117cd611b85565b145b1561181657611719604051806040016040528084604001518152602001846060015181525060405180604001604052808d6060015181526020018d60400151815250611927565b61185860405180604001604052808c6060015181526020018c604001518152506040518060400160405280856040015181526020018560600151815250611972565b90505b80156118ab576040518060a0016040528060011515815260200183602001516001600160a01b031681526020018481526020018360400151815260200183606001518152509750505050506118fc565b50505b806118b881611f1f565b915050611634565b506040518060a0016040528060001515815260200160006001600160a01b03168152602001600081526020016000815260200160008152509450505b50505092915050565b60208101518251600091148015611920575060208301518251145b9392505050565b8051825160009161193791611f3a565b8260200151846020015161194b9190611f3a565b14801561195d57506020820151835110155b80156119205750505160209190910151101590565b8051825160009161198291611f3a565b826020015184602001516119969190611f3a565b149392505050565b634e487b7160e01b600052604160045260246000fd5b60405160a081016001600160401b03811182821017156119e457634e487b7160e01b600052604160045260246000fd5b60405290565b8035600281106119f957600080fd5b919050565b6001600160a01b0381168114611a1357600080fd5b50565b600060a08284031215611a2857600080fd5b611a306119b4565b611a39836119ea565b81526020830135611a49816119fe565b806020830152506040830135604082015260608301356060820152608083013560808201528091505092915050565b6001600160a01b0391909116815260200190565b600060208284031215611a9e57600080fd5b8135611920816119fe565b600081518084526020808501945080840160005b83811015611ad957815187529582019590820190600101611abd565b509495945050505050565b606080825284519082018190526000906020906080840190828801845b82811015611b265781516001600160a01b031684529284019290840190600101611b01565b50505083810382850152611b3a8187611aa9565b9150508281036040840152611b4f8185611aa9565b9695505050505050565b60008060408385031215611b6c57600080fd5b8235611b77816119fe565b946020939093013593505050565b634e487b7160e01b600052602160045260246000fd5b60028110611a1357634e487b7160e01b600052602160045260246000fd5b60c08101611bc688611b9b565b8782526001600160a01b0387166020830152604082018690526060820185905260808201849052611bf683611b9b565b8260a0830152979650505050505050565b600060a08284031215611c1957600080fd5b611c216119b4565b8235611c2c816119fe565b808252506020830135602082015260408301356040820152606083013560608201526080830135611c5c816119fe565b60808201529392505050565b60008082840360a0811215611c7c57600080fd5b6080811215611c8a57600080fd5b50604051608081016001600160401b0381118282101715611cbb57634e487b7160e01b600052604160045260246000fd5b604052611cc7846119ea565b81526020840135611cd7816119fe565b602082015260408481013590820152606080850135908201529460809093013593505050565b6020808252601790820152764e756d62657220697320657175616c20746f207a65726f60481b604082015260600190565b6020808252600f908201526e496e76616c6964206164647265737360881b604082015260600190565b600060208284031215611d6957600080fd5b8151611920816119fe565b600060208284031215611d8657600080fd5b5051919050565b805180151581146119f957600080fd5b600060208284031215611daf57600080fd5b61192082611d8d565b8051611dc381611b9b565b82526020818101516001600160a01b03169083015260408082015190830152606090810151910152565b60a08101611dfb8285611db8565b8260808301529392505050565b600060a08284031215611e1a57600080fd5b611e226119b4565b611e2b83611d8d565b81526020830151611e3b816119fe565b806020830152506040830151604082015260608301516060820152608083015160808201528091505092915050565b6001600160a01b03929092168252602082015260400190565b81516001600160a01b039081168252602080840151908301526040808401519083015260608084015190830152608092830151169181019190915260a00190565b634e487b7160e01b600052601160045260246000fd5b600082821015611eec57611eec611ec4565b500390565b60008219821115611f0457611f04611ec4565b500190565b634e487b7160e01b600052603260045260246000fd5b6000600019821415611f3357611f33611ec4565b5060010190565b6000816000190483118215151615611f5457611f54611ec4565b50029056fea26469706673582212201d919cfd15a3ebabdef9eff87f08404cf143d4ca2811fa46f531ddf14f66ef0764736f6c634300080b0033";

    public static final String FUNC_DFAADDRESS = "dfaAddress";

    public static final String FUNC_FACTORYADDRESS = "factoryAddress";

    public static final String FUNC_REQUESTS = "requests";

    public static final String FUNC_GETRECIPROCALREQUESTINFO = "getReciprocalRequestInfo";

    public static final String FUNC_ADDREQUEST = "addRequest";

    public static final String FUNC_TRYTOEXCHANGE = "tryToExchange";

    public static final String FUNC_GETREQUESTSBYDFA = "getRequestsByDfa";

    public static final Event EXCHANGECOMPLETED_EVENT = new Event("ExchangeCompleted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
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

    public RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger>> requests(String param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REQUESTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple6<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<ReciprocalRequestInfo> getReciprocalRequestInfo(ExchangeRequestView info, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRECIPROCALREQUESTINFO, 
                Arrays.<Type>asList(info, 
                new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<ReciprocalRequestInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, ReciprocalRequestInfo.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addRequest(ExchangeRequestData data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDREQUEST, 
                Arrays.<Type>asList(data), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> tryToExchange(TryToExchangeParams params) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRYTOEXCHANGE, 
                Arrays.<Type>asList(params), 
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

    public static class ExchangeRequestView extends StaticStruct {
        public BigInteger exchangeType;

        public String dfaToGive;

        public BigInteger amountToGive;

        public BigInteger amountToGet;

        public ExchangeRequestView(BigInteger exchangeType, String dfaToGive, BigInteger amountToGive, BigInteger amountToGet) {
            super(new org.web3j.abi.datatypes.generated.Uint8(exchangeType),new org.web3j.abi.datatypes.Address(dfaToGive),new org.web3j.abi.datatypes.generated.Uint256(amountToGive),new org.web3j.abi.datatypes.generated.Uint256(amountToGet));
            this.exchangeType = exchangeType;
            this.dfaToGive = dfaToGive;
            this.amountToGive = amountToGive;
            this.amountToGet = amountToGet;
        }

        public ExchangeRequestView(Uint8 exchangeType, Address dfaToGive, Uint256 amountToGive, Uint256 amountToGet) {
            super(exchangeType,dfaToGive,amountToGive,amountToGet);
            this.exchangeType = exchangeType.getValue();
            this.dfaToGive = dfaToGive.getValue();
            this.amountToGive = amountToGive.getValue();
            this.amountToGet = amountToGet.getValue();
        }
    }

    public static class ReciprocalRequestInfo extends StaticStruct {
        public Boolean isFound;

        public String user;

        public BigInteger index;

        public BigInteger reciprocalAmountToGet;

        public BigInteger reciprocalAmountToGive;

        public ReciprocalRequestInfo(Boolean isFound, String user, BigInteger index, BigInteger reciprocalAmountToGet, BigInteger reciprocalAmountToGive) {
            super(new org.web3j.abi.datatypes.Bool(isFound),new org.web3j.abi.datatypes.Address(user),new org.web3j.abi.datatypes.generated.Uint256(index),new org.web3j.abi.datatypes.generated.Uint256(reciprocalAmountToGet),new org.web3j.abi.datatypes.generated.Uint256(reciprocalAmountToGive));
            this.isFound = isFound;
            this.user = user;
            this.index = index;
            this.reciprocalAmountToGet = reciprocalAmountToGet;
            this.reciprocalAmountToGive = reciprocalAmountToGive;
        }

        public ReciprocalRequestInfo(Bool isFound, Address user, Uint256 index, Uint256 reciprocalAmountToGet, Uint256 reciprocalAmountToGive) {
            super(isFound,user,index,reciprocalAmountToGet,reciprocalAmountToGive);
            this.isFound = isFound.getValue();
            this.user = user.getValue();
            this.index = index.getValue();
            this.reciprocalAmountToGet = reciprocalAmountToGet.getValue();
            this.reciprocalAmountToGive = reciprocalAmountToGive.getValue();
        }
    }

    public static class ExchangeRequestData extends StaticStruct {
        public BigInteger exchangeType;

        public String dfaToGet;

        public BigInteger amountToGet;

        public BigInteger amountToGive;

        public BigInteger endTime;

        public ExchangeRequestData(BigInteger exchangeType, String dfaToGet, BigInteger amountToGet, BigInteger amountToGive, BigInteger endTime) {
            super(new org.web3j.abi.datatypes.generated.Uint8(exchangeType),new org.web3j.abi.datatypes.Address(dfaToGet),new org.web3j.abi.datatypes.generated.Uint256(amountToGet),new org.web3j.abi.datatypes.generated.Uint256(amountToGive),new org.web3j.abi.datatypes.generated.Uint256(endTime));
            this.exchangeType = exchangeType;
            this.dfaToGet = dfaToGet;
            this.amountToGet = amountToGet;
            this.amountToGive = amountToGive;
            this.endTime = endTime;
        }

        public ExchangeRequestData(Uint8 exchangeType, Address dfaToGet, Uint256 amountToGet, Uint256 amountToGive, Uint256 endTime) {
            super(exchangeType,dfaToGet,amountToGet,amountToGive,endTime);
            this.exchangeType = exchangeType.getValue();
            this.dfaToGet = dfaToGet.getValue();
            this.amountToGet = amountToGet.getValue();
            this.amountToGive = amountToGive.getValue();
            this.endTime = endTime.getValue();
        }
    }

    public static class TryToExchangeParams extends StaticStruct {
        public String dfaToGive;

        public BigInteger amountToGive;

        public BigInteger amountToGet;

        public BigInteger requestIndex;

        public String buyer;

        public TryToExchangeParams(String dfaToGive, BigInteger amountToGive, BigInteger amountToGet, BigInteger requestIndex, String buyer) {
            super(new org.web3j.abi.datatypes.Address(dfaToGive),new org.web3j.abi.datatypes.generated.Uint256(amountToGive),new org.web3j.abi.datatypes.generated.Uint256(amountToGet),new org.web3j.abi.datatypes.generated.Uint256(requestIndex),new org.web3j.abi.datatypes.Address(buyer));
            this.dfaToGive = dfaToGive;
            this.amountToGive = amountToGive;
            this.amountToGet = amountToGet;
            this.requestIndex = requestIndex;
            this.buyer = buyer;
        }

        public TryToExchangeParams(Address dfaToGive, Uint256 amountToGive, Uint256 amountToGet, Uint256 requestIndex, Address buyer) {
            super(dfaToGive,amountToGive,amountToGet,requestIndex,buyer);
            this.dfaToGive = dfaToGive.getValue();
            this.amountToGive = amountToGive.getValue();
            this.amountToGet = amountToGet.getValue();
            this.requestIndex = requestIndex.getValue();
            this.buyer = buyer.getValue();
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
