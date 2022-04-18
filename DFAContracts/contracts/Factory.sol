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

  address[] dfaList;
  mapping(address => address) dfaToExchanger;

  modifier isAddressValid(address addressToCheck) {
    require(addressToCheck != address(0), "Invalid token address");
    _;
  }

  function createDfa(
    uint initialSupply,
    string memory name,
    string memory symbol
  ) external returns (address) {
    address dfaAddress = address(
      new DFA(
        initialSupply,
        name,
        symbol,
        msg.sender
      )
    );
    Exchanger exchanger = new Exchanger(dfaAddress);
    dfaToExchanger[address(dfaAddress)] = address(exchanger);
    dfaList.push(dfaAddress);
    return dfaAddress;
  }

  function getExchanger(address dfaAddress) public view returns (address) {
    return dfaToExchanger[dfaAddress];
  }

  function getAllDfa() public view returns (address[] memory) {
    return dfaList;
  }

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