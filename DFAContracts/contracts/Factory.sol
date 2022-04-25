// SPDX-License-Identifier: MIT
pragma solidity ^0.8.3;

import "./DFA.sol";
import "./Exchanger.sol";

contract Factory {
//  struct ExchangerRequestInfo {
//    address user;
//    uint amountToGet;
//    uint amountToGive;
//  }
//
//  struct ExchangerRequestFullInfo {
//    address user;
//    address dfaToGet;
//    uint amountToGet;
//    address dfaToGive;
//    uint amountToGive;
//  }
//  struct DfaInfo {
//    address dfaAddress;
//    string name;
//    string symbol;
//    uint totalSupply;
//  }

  address[] dfaList;
  mapping(address => address) dfaToExchanger;

  modifier isAddressValid(address addressToCheck) {
    require(addressToCheck != address(0), "Invalid token address");
    _;
  }

  event DFACreated(address whoCreate, address dfaAddress, string name, string symbol, uint initialSupply);

  function createDfa(
    uint initialSupply,
    string memory name,
    string memory symbol
  ) external returns (address)
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

//  function getAllDfaTest() public view returns (DfaInfo[] memory) {
//    DfaInfo[] memory info = new DfaInfo[](dfaList.length);
//    for (uint i = 0; i < dfaList.length; i++) {
//      address dfaAddress = dfaList[i];
//      DFA dfa = DFA(dfaAddress);
//      info[i] = DfaInfo(
//        dfaAddress,
//        dfa.name(),
//        dfa.symbol(),
//        dfa.totalSupply()
//      );
//    }
//    return info;
//  }

//  function getAllRequests() public view returns (ExchangerRequestFullInfo[] memory) {
//    ExchangerRequestFullInfo[] requestList = address[](dfaList.length);
//    for (uint i = 0; i < dfaList.length; i++) {
//
//    }
//    return requestList;
//  }

//  function getAllDfaByUser(address user)
//    public
//    view
//    isAddressValid(user)
//    returns (address[])
//  {
//    address[] usersDfa = address[]();
//    for (uint i = 0; i < dfaList.length; i++) {
//      if (DFA(dfaList[i]).owner() == user) {
//        usersDfa.push(dfaList[i]);
//      }
//    }
//    return usersDfa;
//  }
}