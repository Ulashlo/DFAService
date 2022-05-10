// SPDX-License-Identifier: MIT
pragma solidity ^0.8.3;

import "./DFA.sol";
import "./Exchanger.sol";

contract Factory {
  address owner;
  address[] dfaList;
  mapping(address => address) dfaToExchanger;
  mapping(address => bool) verifiedUsers;

  modifier isAddressValid(address addressToCheck) {
    require(addressToCheck != address(0), "Invalid token address");
    _;
  }

  modifier isUserVerified() {
    require(verifiedUsers[msg.sender], "User not verified");
    _;
  }

  modifier isUserNotVerified(address user) {
    require(!verifiedUsers[user], "User already verified");
    _;
  }

  modifier isOwner() {
    require(msg.sender == owner, "User is not owner");
    _;
  }

  constructor() {
    owner = msg.sender;
  }

  event DFACreated(address whoCreate, address dfaAddress, string name, string symbol, uint initialSupply);

  event UserVerified(address user);

  function createDfa(
    uint initialSupply,
    string memory name,
    string memory symbol
  ) external isUserVerified returns (address)
  {
    address dfaAddress = address(
      new DFA(
        initialSupply,
        name,
        symbol,
        msg.sender
      )
    );
    Exchanger exchanger = new Exchanger(dfaAddress);
    dfaToExchanger[dfaAddress] = address(exchanger);
    dfaList.push(dfaAddress);
    emit DFACreated(
      msg.sender,
      dfaAddress,
      name,
      symbol,
      initialSupply
    );
    return dfaAddress;
  }

  function verifyUser(address user)
    external
    isAddressValid(user)
    isUserNotVerified(user)
    isOwner
  {
    verifiedUsers[user] = true;
    emit UserVerified(user);
  }

  function getExchanger(address dfaAddress)
    public
    view
    isAddressValid(dfaAddress)
    returns (address)
  {
    return dfaToExchanger[dfaAddress];
  }

  function getAllDfa() external view
    returns (
      address[] memory,
      address[] memory,
      string[] memory,
      string[] memory,
      uint[] memory
    )
  {
    address[] memory addresses = new address[](dfaList.length);
    address[] memory owners = new address[](dfaList.length);
    string[] memory names = new string[](dfaList.length);
    string[] memory symbols = new string[](dfaList.length);
    uint[] memory supplies = new uint[](dfaList.length);
    for (uint i = 0; i < dfaList.length; i++) {
      address dfaAddress = dfaList[i];
      DFA dfa = DFA(dfaAddress);
      addresses[i] = dfaAddress;
      owners[i] = dfa.owner();
      names[i] = dfa.name();
      symbols[i] = dfa.symbol();
      supplies[i] = dfa.totalSupply();
    }
    return (addresses, owners, names, symbols, supplies);
  }

  function getAllDfaAddresses() external view returns (address[] memory)
  {
    return dfaList;
  }

  function getBalances()
    external
    view
    returns (address[] memory, uint[] memory)
  {
    address[] memory addresses = new address[](dfaList.length);
    uint[] memory balances = new uint[](dfaList.length);
    for (uint i = 0; i < dfaList.length; i++) {
      address dfaAddress = dfaList[i];
      DFA dfa = DFA(dfaAddress);
      addresses[i] = dfaAddress;
      balances[i] = dfa.balanceOf(msg.sender);
    }
    return (addresses, balances);
  }
}