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
    public static final String BINARY = "0x60806040523480156200001157600080fd5b506040516200235e3803806200235e8339810160408190526200003491620000b2565b806001600160a01b038116620000825760405162461bcd60e51b815260206004820152600f60248201526e496e76616c6964206164647265737360881b604482015260640160405180910390fd5b50600080546001600160a01b039092166001600160a01b03199283161790556001805490911633179055620000e4565b600060208284031215620000c557600080fd5b81516001600160a01b0381168114620000dd57600080fd5b9392505050565b61226a80620000f46000396000f3fe608060405234801561001057600080fd5b50600436106100785760003560e01c80636bef7a2b1461007d578063873deb04146100925780638c75d7b4146100bb578063966dae0e146100dd5780639ecebe2a146100f0578063c31f271014610115578063d550a5d414610128578063e09d6c3614610183575b600080fd5b61009061008b366004611c4e565b610196565b005b6000546100a5906001600160a01b031681565b6040516100b29190611cb0565b60405180910390f35b6100ce6100c9366004611cc4565b610d84565b6040516100b293929190611d1c565b6001546100a5906001600160a01b031681565b6101036100fe366004611d91565b611148565b6040516100b296959493929190611df1565b610090610123366004611e3f565b6111ad565b61013b610136366004611ea0565b6114d6565b6040516100b291908151151581526020808301516001600160a01b03169082015260408083015190820152606080830151908201526080918201519181019190915260a00190565b610090610191366004611f27565b611960565b80606001518160400151600082116101c95760405162461bcd60e51b81526004016101c090611fd8565b60405180910390fd5b600081116101e95760405162461bcd60e51b81526004016101c090611fd8565b60208301516001600160a01b0381166102145760405162461bcd60e51b81526004016101c090612009565b6020840151600154604051632f1d7fa760e01b81526000916001600160a01b031690632f1d7fa79061024a908590600401611cb0565b602060405180830381865afa158015610267573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061028b9190612032565b6001600160a01b031614156102e05760405162461bcd60e51b815260206004820152601b60248201527a115e18da185b99d95c88199bdc88191998481b9bdd08199bdd5b99602a1b60448201526064016101c0565b60608501516000546040516370a0823160e01b815282916001600160a01b0316906370a0823190610315903390600401611cb0565b602060405180830381865afa158015610332573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610356919061204f565b10156103a45760405162461bcd60e51b815260206004820152601f60248201527f53656e64657220646f6573206e6f74206861766520656e6f756768206466610060448201526064016101c0565b600054604051636eb1769f60e11b815233600482015230602482015282916001600160a01b03169063dd62ed3e90604401602060405180830381865afa1580156103f2573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610416919061204f565b10156104635760405162461bcd60e51b815260206004820152601c60248201527b105b5bdd5b9d081bd988111190481a5cc81b9bdd08185b1b1bddd95960221b60448201526064016101c0565b6001546020870151604051632f1d7fa760e01b81526000926001600160a01b031691632f1d7fa7916104989190600401611cb0565b602060405180830381865afa1580156104b5573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104d99190612032565b60005460608901516040516323b872dd60e01b8152336004820152306024820152604481019190915291925082916001600160a01b03909116906323b872dd906064016020604051808303816000875af115801561053b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061055f9190612078565b506000816001600160a01b031663d550a5d460405180608001604052808c60000151600181111561059257610592611dbd565b8152600080546001600160a01b031660208301526060808f01516040808501919091528f810151919093015290516001600160e01b031960e085901b1681526105df9291906004016120c8565b60a060405180830381865afa1580156105fc573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061062091906120e3565b905060008951600181111561063757610637611dbd565b14156108955780511561076557600054602082015160608b015160405163a9059cbb60e01b81526001600160a01b039093169263a9059cbb9261067e929091600401612145565b6020604051808303816000875af115801561069d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106c19190612078565b506040805160a0810182526000546001600160a01b0390811682526060808d015160208401528c8401518385015284840151908301523360808301529151630c31f27160e41b81529184169163c31f27109161071f9160040161215e565b600060405180830381600087803b15801561073957600080fd5b505af115801561074d573d6000803e3d6000fd5b5050600060608c0181905260408c015250610d799050565b600260008a602001516001600160a01b03166001600160a01b031681526020019081526020016000206040518060c001604052808b6000015160018111156107af576107af611dbd565b815233602082015260408c810151908201526060808d0151908201526080808d01519082015260a00160009052815460018181018455600093845260209093208251600590920201805492939092839160ff1990911690838181111561081757610817611dbd565b0217905550602082015181546001600160a01b0390911661010002610100600160a81b03199091161781556040820151600180830191909155606083015160028301556080830151600383015560a08301516004830180549192909160ff191690838181111561088957610889611dbd565b02179055505050610d79565b805180156108a7575060008960600151115b80156108b7575060008960400151115b15610c305788606001518160600151111580156108dc57508860400151816080015111155b15610a29576000546020820151606083015160405163a9059cbb60e01b81526001600160a01b039093169263a9059cbb9261091b929091600401612145565b6020604051808303816000875af115801561093a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061095e9190612078565b506040805160a0810182526000546001600160a01b039081168252606080850151602084015260808086015184860152858501519184019190915233908301529151630c31f27160e41b81529184169163c31f2710916109c09160040161215e565b600060405180830381600087803b1580156109da57600080fd5b505af11580156109ee573d6000803e3d6000fd5b50505050806060015189606001818151610a0891906121b5565b905250608081015160408a018051610a219083906121b5565b905250610b45565b600054602082015160608b015160405163a9059cbb60e01b81526001600160a01b039093169263a9059cbb92610a63929091600401612145565b6020604051808303816000875af1158015610a82573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610aa69190612078565b506040805160a0810182526000546001600160a01b0390811682526060808d015160208401528c8401518385015284840151908301523360808301529151630c31f27160e41b81529184169163c31f271091610b049160040161215e565b600060405180830381600087803b158015610b1e57600080fd5b505af1158015610b32573d6000803e3d6000fd5b5050600060608c0181905260408c015250505b60608901511580610b5857506040890151155b15610b6257610c30565b816001600160a01b031663d550a5d460405180608001604052808c600001516001811115610b9257610b92611dbd565b81526000546001600160a01b031660208201526060808e01516040808401919091528e8101519190920152840151610bcb9060016121cc565b6040518363ffffffff1660e01b8152600401610be89291906120c8565b60a060405180830381865afa158015610c05573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610c2991906120e3565b9050610895565b60008960400151118015610c48575060008960600151115b15610d7957600260008a602001516001600160a01b03166001600160a01b031681526020019081526020016000206040518060c001604052808b600001516001811115610c9757610c97611dbd565b815233602082015260408c810151908201526060808d0151908201526080808d01519082015260a00160009052815460018181018455600093845260209093208251600590920201805492939092839160ff19909116908381811115610cff57610cff611dbd565b0217905550602082015181546001600160a01b0390911661010002610100600160a81b03199091161781556040820151600180830191909155606083015160028301556080830151600383015560a08301516004830180549192909160ff1916908381811115610d7157610d71611dbd565b021790555050505b505050505050505050565b60608080836001600160a01b038116610daf5760405162461bcd60e51b81526004016101c090612009565b6001600160a01b038516600090815260026020908152604080832080548251818502810185019093528083529192909190849084015b82821015610ea9576000848152602090206040805160c08101909152600584029091018054829060ff166001811115610e2057610e20611dbd565b6001811115610e3157610e31611dbd565b8152815461010090046001600160a01b0316602082015260018083015460408301526002830154606083015260038301546080830152600483015460a09092019160ff1690811115610e8557610e85611dbd565b6001811115610e9657610e96611dbd565b8152505081526020019060010190610de5565b5050505090506000805b8251811015610f3c576000838281518110610ed057610ed06121e4565b602002602001015160a001516001811115610eed57610eed611dbd565b148015610f17575042838281518110610f0857610f086121e4565b60200260200101516080015110155b15610f2a5781610f26816121fa565b9250505b80610f34816121fa565b915050610eb3565b506000816001600160401b03811115610f5757610f57611bb4565b604051908082528060200260200182016040528015610f80578160200160208202803683370190505b5090506000826001600160401b03811115610f9d57610f9d611bb4565b604051908082528060200260200182016040528015610fc6578160200160208202803683370190505b5090506000836001600160401b03811115610fe357610fe3611bb4565b60405190808252806020026020018201604052801561100c578160200160208202803683370190505b5090506000805b8651811015611137576000878281518110611030576110306121e4565b602002602001015160a00151600181111561104d5761104d611dbd565b148015611077575042878281518110611068576110686121e4565b60200260200101516080015110155b15611125576000878281518110611090576110906121e4565b6020026020010151905080602001518684815181106110b1576110b16121e4565b60200260200101906001600160a01b031690816001600160a01b03168152505080604001518584815181106110e8576110e86121e4565b602002602001018181525050806060015184848151811061110b5761110b6121e4565b60209081029190910101526111216001846121cc565b9250505b8061112f816121fa565b915050611013565b50929a919950975095505050505050565b6002602052816000526040600020818154811061116457600080fd5b60009182526020909120600590910201805460018201546002830154600384015460049094015460ff80851697506101009094046001600160a01b031695509193909290911686565b80602001518160400151600082116111d75760405162461bcd60e51b81526004016101c090611fd8565b600081116111f75760405162461bcd60e51b81526004016101c090611fd8565b82516001600160a01b03811661121f5760405162461bcd60e51b81526004016101c090612009565b8351600154604051632f1d7fa760e01b815233916001600160a01b031690632f1d7fa790611251908590600401611cb0565b602060405180830381865afa15801561126e573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906112929190612032565b6001600160a01b0316146112e25760405162461bcd60e51b815260206004820152601760248201527629b2b73232b91034b9903737ba1032bc31b430b733b2b960491b60448201526064016101c0565b60808501516001600160a01b03811661130d5760405162461bcd60e51b81526004016101c090612009565b85516001600160a01b0316600090815260026020526040812060608801518154811061133b5761133b6121e4565b90600052602060002090600502019050866020015181600101600082825461136391906121b5565b909155505060408701516002820180546000906113819084906121b5565b9091555060009050815460ff16600181111561139f5761139f611dbd565b14806113ad57506001810154155b806113ba57506002810154155b156113cf5760048101805460ff191660011790555b60005460808801516040808a0151905163a9059cbb60e01b81526001600160a01b039093169263a9059cbb92611409929091600401612145565b6020604051808303816000875af1158015611428573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061144c9190612078565b508054600054600283015460808a8101518b516020808e0151604080516001600160a01b03610100909a048a16815297891692880192909252868201959095529186166060860152949094169083015260a082015290517f3fcd82abd7b4aa2dae161019e066c1909e297f3a20b3b4757ef4aa36b1a122429181900360c00190a150505050505050565b6115136040518060a0016040528060001515815260200160006001600160a01b031681526020016000815260200160008152602001600081525090565b826040015183606001516000821161153d5760405162461bcd60e51b81526004016101c090611fd8565b6000811161155d5760405162461bcd60e51b81526004016101c090611fd8565b60208501516001600160a01b0381166115885760405162461bcd60e51b81526004016101c090612009565b6020808701516001600160a01b031660009081526002825260408082208054825181860281018601909352808352929391929091849084015b82821015611685576000848152602090206040805160c08101909152600584029091018054829060ff1660018111156115fc576115fc611dbd565b600181111561160d5761160d611dbd565b8152815461010090046001600160a01b0316602082015260018083015460408301526002830154606083015260038301546080830152600483015460a09092019160ff169081111561166157611661611dbd565b600181111561167257611672611dbd565b81525050815260200190600101906115c1565b5092935088925050505b815181101561191b5760008282815181106116ac576116ac6121e4565b602002602001015190506001808111156116c8576116c8611dbd565b8160a0015160018111156116de576116de611dbd565b14806116ed5750428160800151105b156116f85750611909565b6000808251600181111561170e5761170e611dbd565b14801561172d575060008a51600181111561172b5761172b611dbd565b145b1561177b5761177460405180604001604052808c6060015181526020018c604001518152506040518060400160405280856040015181526020018560600151815250611b1b565b90506118b6565b60008251600181111561179057611790611dbd565b1480156117af575060018a5160018111156117ad576117ad611dbd565b145b156117f65761177460405180604001604052808c6060015181526020018c604001518152506040518060400160405280856040015181526020018560600151815250611b3d565b60018251600181111561180b5761180b611dbd565b14801561182a575060008a51600181111561182857611828611dbd565b145b1561187157611774604051806040016040528084604001518152602001846060015181525060405180604001604052808d6060015181526020018d60400151815250611b3d565b6118b360405180604001604052808c6060015181526020018c604001518152506040518060400160405280856040015181526020018560600151815250611b88565b90505b8015611906576040518060a0016040528060011515815260200183602001516001600160a01b03168152602001848152602001836040015181526020018360600151815250975050505050611957565b50505b80611913816121fa565b91505061168f565b506040518060a0016040528060001515815260200160006001600160a01b03168152602001600081526020016000815260200160008152509450505b50505092915050565b6001546001600160a01b031633146119b25760405162461bcd60e51b815260206004820152601560248201527443616c6c6572206973206e6f7420666163746f727960581b60448201526064016101c0565b60005b8151811015611b17576000600260008484815181106119d6576119d66121e4565b60200260200101516001600160a01b03166001600160a01b03168152602001908152602001600020905060005b8154811015611b02576000828281548110611a2057611a206121e4565b6000918252602082206005909102019150600482015460ff166001811115611a4a57611a4a611dbd565b148015611a5a5750428160030154105b15611aef576004818101805460ff191660011790556000548254600284015460405163a9059cbb60e01b81526001600160a01b039384169463a9059cbb94611aaa94610100900416929101612145565b6020604051808303816000875af1158015611ac9573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190611aed9190612078565b505b5080611afa816121fa565b915050611a03565b50508080611b0f906121fa565b9150506119b5565b5050565b60208101518251600091148015611b36575060208301518251145b9392505050565b80518251600091611b4d91612215565b82602001518460200151611b619190612215565b148015611b7357506020820151835110155b8015611b365750505160209190910151101590565b80518251600091611b9891612215565b82602001518460200151611bac9190612215565b149392505050565b634e487b7160e01b600052604160045260246000fd5b60405160a081016001600160401b0381118282101715611bec57611bec611bb4565b60405290565b604051601f8201601f191681016001600160401b0381118282101715611c1a57611c1a611bb4565b604052919050565b803560028110611c3157600080fd5b919050565b6001600160a01b0381168114611c4b57600080fd5b50565b600060a08284031215611c6057600080fd5b611c68611bca565b611c7183611c22565b81526020830135611c8181611c36565b806020830152506040830135604082015260608301356060820152608083013560808201528091505092915050565b6001600160a01b0391909116815260200190565b600060208284031215611cd657600080fd5b8135611b3681611c36565b600081518084526020808501945080840160005b83811015611d1157815187529582019590820190600101611cf5565b509495945050505050565b606080825284519082018190526000906020906080840190828801845b82811015611d5e5781516001600160a01b031684529284019290840190600101611d39565b50505083810382850152611d728187611ce1565b9150508281036040840152611d878185611ce1565b9695505050505050565b60008060408385031215611da457600080fd5b8235611daf81611c36565b946020939093013593505050565b634e487b7160e01b600052602160045260246000fd5b60028110611c4b57634e487b7160e01b600052602160045260246000fd5b60c08101611dfe88611dd3565b8782526001600160a01b0387166020830152604082018690526060820185905260808201849052611e2e83611dd3565b8260a0830152979650505050505050565b600060a08284031215611e5157600080fd5b611e59611bca565b8235611e6481611c36565b808252506020830135602082015260408301356040820152606083013560608201526080830135611e9481611c36565b60808201529392505050565b60008082840360a0811215611eb457600080fd5b6080811215611ec257600080fd5b50604051608081016001600160401b0381118282101715611ee557611ee5611bb4565b604052611ef184611c22565b81526020840135611f0181611c36565b602082015260408481013590820152606080850135908201529460809093013593505050565b60006020808385031215611f3a57600080fd5b82356001600160401b0380821115611f5157600080fd5b818501915085601f830112611f6557600080fd5b813581811115611f7757611f77611bb4565b8060051b9150611f88848301611bf2565b8181529183018401918481019088841115611fa257600080fd5b938501935b83851015611fcc5784359250611fbc83611c36565b8282529385019390850190611fa7565b98975050505050505050565b6020808252601790820152764e756d62657220697320657175616c20746f207a65726f60481b604082015260600190565b6020808252600f908201526e496e76616c6964206164647265737360881b604082015260600190565b60006020828403121561204457600080fd5b8151611b3681611c36565b60006020828403121561206157600080fd5b5051919050565b80518015158114611c3157600080fd5b60006020828403121561208a57600080fd5b611b3682612068565b805161209e81611dd3565b82526020818101516001600160a01b03169083015260408082015190830152606090810151910152565b60a081016120d68285612093565b8260808301529392505050565b600060a082840312156120f557600080fd5b6120fd611bca565b61210683612068565b8152602083015161211681611c36565b806020830152506040830151604082015260608301516060820152608083015160808201528091505092915050565b6001600160a01b03929092168252602082015260400190565b81516001600160a01b039081168252602080840151908301526040808401519083015260608084015190830152608092830151169181019190915260a00190565b634e487b7160e01b600052601160045260246000fd5b6000828210156121c7576121c761219f565b500390565b600082198211156121df576121df61219f565b500190565b634e487b7160e01b600052603260045260246000fd5b600060001982141561220e5761220e61219f565b5060010190565b600081600019048311821515161561222f5761222f61219f565b50029056fea26469706673582212208a8b5fcd56a07b46eb26737f000a18cd79ca0f36673eeff1a4ed18c7a201040b64736f6c634300080b0033";

    public static final String FUNC_DFAADDRESS = "dfaAddress";

    public static final String FUNC_FACTORYADDRESS = "factoryAddress";

    public static final String FUNC_REQUESTS = "requests";

    public static final String FUNC_GETRECIPROCALREQUESTINFO = "getReciprocalRequestInfo";

    public static final String FUNC_ADDREQUEST = "addRequest";

    public static final String FUNC_TRYTOEXCHANGE = "tryToExchange";

    public static final String FUNC_GETREQUESTSBYDFA = "getRequestsByDfa";

    public static final String FUNC_CLOSEOLDREQUESTS = "closeOldRequests";

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

    public RemoteFunctionCall<ReciprocalRequestInfo> getReciprocalRequestInfo(GetReciprocalRequestInfoParams info, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRECIPROCALREQUESTINFO, 
                Arrays.<Type>asList(info, 
                new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<ReciprocalRequestInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, ReciprocalRequestInfo.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addRequest(AddExchangeRequestParams data) {
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

    public RemoteFunctionCall<TransactionReceipt> closeOldRequests(List<String> dfaList) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CLOSEOLDREQUESTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(dfaList, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public static class GetReciprocalRequestInfoParams extends StaticStruct {
        public BigInteger exchangeType;

        public String dfaToGive;

        public BigInteger amountToGive;

        public BigInteger amountToGet;

        public GetReciprocalRequestInfoParams(BigInteger exchangeType, String dfaToGive, BigInteger amountToGive, BigInteger amountToGet) {
            super(new org.web3j.abi.datatypes.generated.Uint8(exchangeType),new org.web3j.abi.datatypes.Address(dfaToGive),new org.web3j.abi.datatypes.generated.Uint256(amountToGive),new org.web3j.abi.datatypes.generated.Uint256(amountToGet));
            this.exchangeType = exchangeType;
            this.dfaToGive = dfaToGive;
            this.amountToGive = amountToGive;
            this.amountToGet = amountToGet;
        }

        public GetReciprocalRequestInfoParams(Uint8 exchangeType, Address dfaToGive, Uint256 amountToGive, Uint256 amountToGet) {
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

    public static class AddExchangeRequestParams extends StaticStruct {
        public BigInteger exchangeType;

        public String dfaToGet;

        public BigInteger amountToGet;

        public BigInteger amountToGive;

        public BigInteger endTime;

        public AddExchangeRequestParams(BigInteger exchangeType, String dfaToGet, BigInteger amountToGet, BigInteger amountToGive, BigInteger endTime) {
            super(new org.web3j.abi.datatypes.generated.Uint8(exchangeType),new org.web3j.abi.datatypes.Address(dfaToGet),new org.web3j.abi.datatypes.generated.Uint256(amountToGet),new org.web3j.abi.datatypes.generated.Uint256(amountToGive),new org.web3j.abi.datatypes.generated.Uint256(endTime));
            this.exchangeType = exchangeType;
            this.dfaToGet = dfaToGet;
            this.amountToGet = amountToGet;
            this.amountToGive = amountToGive;
            this.endTime = endTime;
        }

        public AddExchangeRequestParams(Uint8 exchangeType, Address dfaToGet, Uint256 amountToGet, Uint256 amountToGive, Uint256 endTime) {
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
