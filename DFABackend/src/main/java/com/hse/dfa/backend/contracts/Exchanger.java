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
    public static final String BINARY = "0x608060405234801561001057600080fd5b50604051620020b9380380620020b9833981016040819052610031916100ad565b806001600160a01b03811661007e5760405162461bcd60e51b815260206004820152600f60248201526e496e76616c6964206164647265737360881b604482015260640160405180910390fd5b50600080546001600160a01b039092166001600160a01b031992831617905560018054909116331790556100dd565b6000602082840312156100bf57600080fd5b81516001600160a01b03811681146100d657600080fd5b9392505050565b611fcc80620000ed6000396000f3fe608060405234801561001057600080fd5b506004361061006d5760003560e01c80636bef7a2b14610072578063873deb04146100875780638c75d7b4146100b0578063966dae0e146100d25780639ecebe2a146100e5578063c31f27101461010a578063d550a5d41461011d575b600080fd5b610085610080366004611a53565b610178565b005b60005461009a906001600160a01b031681565b6040516100a79190611ab5565b60405180910390f35b6100c36100be366004611ac9565b610d66565b6040516100a793929190611b21565b60015461009a906001600160a01b031681565b6100f86100f3366004611b96565b61112a565b6040516100a796959493929190611bf6565b610085610118366004611c44565b61118f565b61013061012b366004611ca5565b6114b8565b6040516100a791908151151581526020808301516001600160a01b03169082015260408083015190820152606080830151908201526080918201519181019190915260a00190565b80606001518160400151600082116101ab5760405162461bcd60e51b81526004016101a290611d3a565b60405180910390fd5b600081116101cb5760405162461bcd60e51b81526004016101a290611d3a565b60208301516001600160a01b0381166101f65760405162461bcd60e51b81526004016101a290611d6b565b6020840151600154604051632f1d7fa760e01b81526000916001600160a01b031690632f1d7fa79061022c908590600401611ab5565b602060405180830381865afa158015610249573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061026d9190611d94565b6001600160a01b031614156102c25760405162461bcd60e51b815260206004820152601b60248201527a115e18da185b99d95c88199bdc88191998481b9bdd08199bdd5b99602a1b60448201526064016101a2565b60608501516000546040516370a0823160e01b815282916001600160a01b0316906370a08231906102f7903390600401611ab5565b602060405180830381865afa158015610314573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103389190611db1565b10156103865760405162461bcd60e51b815260206004820152601f60248201527f53656e64657220646f6573206e6f74206861766520656e6f756768206466610060448201526064016101a2565b600054604051636eb1769f60e11b815233600482015230602482015282916001600160a01b03169063dd62ed3e90604401602060405180830381865afa1580156103d4573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103f89190611db1565b10156104455760405162461bcd60e51b815260206004820152601c60248201527b105b5bdd5b9d081bd988111190481a5cc81b9bdd08185b1b1bddd95960221b60448201526064016101a2565b6001546020870151604051632f1d7fa760e01b81526000926001600160a01b031691632f1d7fa79161047a9190600401611ab5565b602060405180830381865afa158015610497573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104bb9190611d94565b60005460608901516040516323b872dd60e01b8152336004820152306024820152604481019190915291925082916001600160a01b03909116906323b872dd906064016020604051808303816000875af115801561051d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105419190611dda565b506000816001600160a01b031663d550a5d460405180608001604052808c60000151600181111561057457610574611bc2565b8152600080546001600160a01b031660208301526060808f01516040808501919091528f810151919093015290516001600160e01b031960e085901b1681526105c1929190600401611e2a565b60a060405180830381865afa1580156105de573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106029190611e45565b905060008951600181111561061957610619611bc2565b14156108775780511561074757600054602082015160608b015160405163a9059cbb60e01b81526001600160a01b039093169263a9059cbb92610660929091600401611ea7565b6020604051808303816000875af115801561067f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106a39190611dda565b506040805160a0810182526000546001600160a01b0390811682526060808d015160208401528c8401518385015284840151908301523360808301529151630c31f27160e41b81529184169163c31f27109161070191600401611ec0565b600060405180830381600087803b15801561071b57600080fd5b505af115801561072f573d6000803e3d6000fd5b5050600060608c0181905260408c015250610d5b9050565b600260008a602001516001600160a01b03166001600160a01b031681526020019081526020016000206040518060c001604052808b60000151600181111561079157610791611bc2565b815233602082015260408c810151908201526060808d0151908201526080808d01519082015260a00160009052815460018181018455600093845260209093208251600590920201805492939092839160ff199091169083818111156107f9576107f9611bc2565b0217905550602082015181546001600160a01b0390911661010002610100600160a81b03199091161781556040820151600180830191909155606083015160028301556080830151600383015560a08301516004830180549192909160ff191690838181111561086b5761086b611bc2565b02179055505050610d5b565b80518015610889575060008960600151115b8015610899575060008960400151115b15610c125788606001518160600151111580156108be57508860400151816080015111155b15610a0b576000546020820151606083015160405163a9059cbb60e01b81526001600160a01b039093169263a9059cbb926108fd929091600401611ea7565b6020604051808303816000875af115801561091c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109409190611dda565b506040805160a0810182526000546001600160a01b039081168252606080850151602084015260808086015184860152858501519184019190915233908301529151630c31f27160e41b81529184169163c31f2710916109a291600401611ec0565b600060405180830381600087803b1580156109bc57600080fd5b505af11580156109d0573d6000803e3d6000fd5b505050508060600151896060018181516109ea9190611f17565b905250608081015160408a018051610a03908390611f17565b905250610b27565b600054602082015160608b015160405163a9059cbb60e01b81526001600160a01b039093169263a9059cbb92610a45929091600401611ea7565b6020604051808303816000875af1158015610a64573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610a889190611dda565b506040805160a0810182526000546001600160a01b0390811682526060808d015160208401528c8401518385015284840151908301523360808301529151630c31f27160e41b81529184169163c31f271091610ae691600401611ec0565b600060405180830381600087803b158015610b0057600080fd5b505af1158015610b14573d6000803e3d6000fd5b5050600060608c0181905260408c015250505b60608901511580610b3a57506040890151155b15610b4457610c12565b816001600160a01b031663d550a5d460405180608001604052808c600001516001811115610b7457610b74611bc2565b81526000546001600160a01b031660208201526060808e01516040808401919091528e8101519190920152840151610bad906001611f2e565b6040518363ffffffff1660e01b8152600401610bca929190611e2a565b60a060405180830381865afa158015610be7573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610c0b9190611e45565b9050610877565b60008960400151118015610c2a575060008960600151115b15610d5b57600260008a602001516001600160a01b03166001600160a01b031681526020019081526020016000206040518060c001604052808b600001516001811115610c7957610c79611bc2565b815233602082015260408c810151908201526060808d0151908201526080808d01519082015260a00160009052815460018181018455600093845260209093208251600590920201805492939092839160ff19909116908381811115610ce157610ce1611bc2565b0217905550602082015181546001600160a01b0390911661010002610100600160a81b03199091161781556040820151600180830191909155606083015160028301556080830151600383015560a08301516004830180549192909160ff1916908381811115610d5357610d53611bc2565b021790555050505b505050505050505050565b60608080836001600160a01b038116610d915760405162461bcd60e51b81526004016101a290611d6b565b6001600160a01b038516600090815260026020908152604080832080548251818502810185019093528083529192909190849084015b82821015610e8b576000848152602090206040805160c08101909152600584029091018054829060ff166001811115610e0257610e02611bc2565b6001811115610e1357610e13611bc2565b8152815461010090046001600160a01b0316602082015260018083015460408301526002830154606083015260038301546080830152600483015460a09092019160ff1690811115610e6757610e67611bc2565b6001811115610e7857610e78611bc2565b8152505081526020019060010190610dc7565b5050505090506000805b8251811015610f1e576000838281518110610eb257610eb2611f46565b602002602001015160a001516001811115610ecf57610ecf611bc2565b148015610ef9575042838281518110610eea57610eea611f46565b60200260200101516080015110155b15610f0c5781610f0881611f5c565b9250505b80610f1681611f5c565b915050610e95565b506000816001600160401b03811115610f3957610f396119db565b604051908082528060200260200182016040528015610f62578160200160208202803683370190505b5090506000826001600160401b03811115610f7f57610f7f6119db565b604051908082528060200260200182016040528015610fa8578160200160208202803683370190505b5090506000836001600160401b03811115610fc557610fc56119db565b604051908082528060200260200182016040528015610fee578160200160208202803683370190505b5090506000805b865181101561111957600087828151811061101257611012611f46565b602002602001015160a00151600181111561102f5761102f611bc2565b14801561105957504287828151811061104a5761104a611f46565b60200260200101516080015110155b1561110757600087828151811061107257611072611f46565b60200260200101519050806020015186848151811061109357611093611f46565b60200260200101906001600160a01b031690816001600160a01b03168152505080604001518584815181106110ca576110ca611f46565b60200260200101818152505080606001518484815181106110ed576110ed611f46565b6020908102919091010152611103600184611f2e565b9250505b8061111181611f5c565b915050610ff5565b50929a919950975095505050505050565b6002602052816000526040600020818154811061114657600080fd5b60009182526020909120600590910201805460018201546002830154600384015460049094015460ff80851697506101009094046001600160a01b031695509193909290911686565b80602001518160400151600082116111b95760405162461bcd60e51b81526004016101a290611d3a565b600081116111d95760405162461bcd60e51b81526004016101a290611d3a565b82516001600160a01b0381166112015760405162461bcd60e51b81526004016101a290611d6b565b8351600154604051632f1d7fa760e01b815233916001600160a01b031690632f1d7fa790611233908590600401611ab5565b602060405180830381865afa158015611250573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906112749190611d94565b6001600160a01b0316146112c45760405162461bcd60e51b815260206004820152601760248201527629b2b73232b91034b9903737ba1032bc31b430b733b2b960491b60448201526064016101a2565b60808501516001600160a01b0381166112ef5760405162461bcd60e51b81526004016101a290611d6b565b85516001600160a01b0316600090815260026020526040812060608801518154811061131d5761131d611f46565b9060005260206000209060050201905086602001518160010160008282546113459190611f17565b90915550506040870151600282018054600090611363908490611f17565b9091555060009050815460ff16600181111561138157611381611bc2565b148061138f57506001810154155b8061139c57506002810154155b156113b15760048101805460ff191660011790555b60005460808801516040808a0151905163a9059cbb60e01b81526001600160a01b039093169263a9059cbb926113eb929091600401611ea7565b6020604051808303816000875af115801561140a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061142e9190611dda565b508054600054600283015460808a8101518b516020808e0151604080516001600160a01b03610100909a048a16815297891692880192909252868201959095529186166060860152949094169083015260a082015290517f3fcd82abd7b4aa2dae161019e066c1909e297f3a20b3b4757ef4aa36b1a122429181900360c00190a150505050505050565b6114f56040518060a0016040528060001515815260200160006001600160a01b031681526020016000815260200160008152602001600081525090565b826040015183606001516000821161151f5760405162461bcd60e51b81526004016101a290611d3a565b6000811161153f5760405162461bcd60e51b81526004016101a290611d3a565b60208501516001600160a01b03811661156a5760405162461bcd60e51b81526004016101a290611d6b565b6020808701516001600160a01b031660009081526002825260408082208054825181860281018601909352808352929391929091849084015b82821015611667576000848152602090206040805160c08101909152600584029091018054829060ff1660018111156115de576115de611bc2565b60018111156115ef576115ef611bc2565b8152815461010090046001600160a01b0316602082015260018083015460408301526002830154606083015260038301546080830152600483015460a09092019160ff169081111561164357611643611bc2565b600181111561165457611654611bc2565b81525050815260200190600101906115a3565b5092935088925050505b81518110156118fd57600082828151811061168e5761168e611f46565b602002602001015190506001808111156116aa576116aa611bc2565b8160a0015160018111156116c0576116c0611bc2565b14806116cf5750428160800151105b156116da57506118eb565b600080825160018111156116f0576116f0611bc2565b14801561170f575060008a51600181111561170d5761170d611bc2565b145b1561175d5761175660405180604001604052808c6060015181526020018c604001518152506040518060400160405280856040015181526020018560600151815250611942565b9050611898565b60008251600181111561177257611772611bc2565b148015611791575060018a51600181111561178f5761178f611bc2565b145b156117d85761175660405180604001604052808c6060015181526020018c604001518152506040518060400160405280856040015181526020018560600151815250611964565b6001825160018111156117ed576117ed611bc2565b14801561180c575060008a51600181111561180a5761180a611bc2565b145b1561185357611756604051806040016040528084604001518152602001846060015181525060405180604001604052808d6060015181526020018d60400151815250611964565b61189560405180604001604052808c6060015181526020018c6040015181525060405180604001604052808560400151815260200185606001518152506119af565b90505b80156118e8576040518060a0016040528060011515815260200183602001516001600160a01b03168152602001848152602001836040015181526020018360600151815250975050505050611939565b50505b806118f581611f5c565b915050611671565b506040518060a0016040528060001515815260200160006001600160a01b03168152602001600081526020016000815260200160008152509450505b50505092915050565b6020810151825160009114801561195d575060208301518251145b9392505050565b8051825160009161197491611f77565b826020015184602001516119889190611f77565b14801561199a57506020820151835110155b801561195d5750505160209190910151101590565b805182516000916119bf91611f77565b826020015184602001516119d39190611f77565b149392505050565b634e487b7160e01b600052604160045260246000fd5b60405160a081016001600160401b0381118282101715611a2157634e487b7160e01b600052604160045260246000fd5b60405290565b803560028110611a3657600080fd5b919050565b6001600160a01b0381168114611a5057600080fd5b50565b600060a08284031215611a6557600080fd5b611a6d6119f1565b611a7683611a27565b81526020830135611a8681611a3b565b806020830152506040830135604082015260608301356060820152608083013560808201528091505092915050565b6001600160a01b0391909116815260200190565b600060208284031215611adb57600080fd5b813561195d81611a3b565b600081518084526020808501945080840160005b83811015611b1657815187529582019590820190600101611afa565b509495945050505050565b606080825284519082018190526000906020906080840190828801845b82811015611b635781516001600160a01b031684529284019290840190600101611b3e565b50505083810382850152611b778187611ae6565b9150508281036040840152611b8c8185611ae6565b9695505050505050565b60008060408385031215611ba957600080fd5b8235611bb481611a3b565b946020939093013593505050565b634e487b7160e01b600052602160045260246000fd5b60028110611a5057634e487b7160e01b600052602160045260246000fd5b60c08101611c0388611bd8565b8782526001600160a01b0387166020830152604082018690526060820185905260808201849052611c3383611bd8565b8260a0830152979650505050505050565b600060a08284031215611c5657600080fd5b611c5e6119f1565b8235611c6981611a3b565b808252506020830135602082015260408301356040820152606083013560608201526080830135611c9981611a3b565b60808201529392505050565b60008082840360a0811215611cb957600080fd5b6080811215611cc757600080fd5b50604051608081016001600160401b0381118282101715611cf857634e487b7160e01b600052604160045260246000fd5b604052611d0484611a27565b81526020840135611d1481611a3b565b602082015260408481013590820152606080850135908201529460809093013593505050565b6020808252601790820152764e756d62657220697320657175616c20746f207a65726f60481b604082015260600190565b6020808252600f908201526e496e76616c6964206164647265737360881b604082015260600190565b600060208284031215611da657600080fd5b815161195d81611a3b565b600060208284031215611dc357600080fd5b5051919050565b80518015158114611a3657600080fd5b600060208284031215611dec57600080fd5b61195d82611dca565b8051611e0081611bd8565b82526020818101516001600160a01b03169083015260408082015190830152606090810151910152565b60a08101611e388285611df5565b8260808301529392505050565b600060a08284031215611e5757600080fd5b611e5f6119f1565b611e6883611dca565b81526020830151611e7881611a3b565b806020830152506040830151604082015260608301516060820152608083015160808201528091505092915050565b6001600160a01b03929092168252602082015260400190565b81516001600160a01b039081168252602080840151908301526040808401519083015260608084015190830152608092830151169181019190915260a00190565b634e487b7160e01b600052601160045260246000fd5b600082821015611f2957611f29611f01565b500390565b60008219821115611f4157611f41611f01565b500190565b634e487b7160e01b600052603260045260246000fd5b6000600019821415611f7057611f70611f01565b5060010190565b6000816000190483118215151615611f9157611f91611f01565b50029056fea26469706673582212200ed80e4942d038d88eb879702043c7b8a16d87c496d87e42ec0f9ccf77285bc764736f6c634300080b0033";

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
