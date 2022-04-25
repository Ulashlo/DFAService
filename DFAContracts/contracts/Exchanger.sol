// SPDX-License-Identifier: MIT
pragma solidity ^0.8.3;

import "./DFA.sol";
import "./Factory.sol";

contract Exchanger {
  struct ExchangerRequestInfo {
    address user;
    uint amountToGet;
    uint amountToGive;
  }

  struct InnerExchangerRequestInfo {
    address user;
    address dfaToGive;
    uint amountToGive;
    uint amountToGet;
  }

  address public dfaAddress;
  address public factoryAddress;
  mapping (address => ExchangerRequestInfo[]) public requests;

  modifier isAddressValid(address addressToCheck) {
    require(addressToCheck != address(0), "Invalid address");
    _;
  }

  modifier isAmountsNotZero(uint amount1, uint amount2) {
    require(amount1 > 0, "Number is equal to zero");
    require(amount2 > 0, "Number is equal to zero");
  _;
  }

  modifier isAmountCanTransfer(uint amount, address dfa) {
    require(
      DFA(dfa).balanceOf(msg.sender) >= amount,
      "Sender does not have enough dfa"
    );
    require(
      DFA(dfa).allowance(msg.sender, address(this)) >= amount,
      "Amount of DFA is not allowed"
    );
    _;
  }

  modifier isExchangerValid(address addressToCheck) {
    require(
      Factory(factoryAddress).getExchanger(addressToCheck) != address(0),
      "Exchanger for dfa not found"
    );
    _;
  }

  modifier isSenderIsExchanger(address addressToCheck) {
    require(
      Factory(factoryAddress).getExchanger(addressToCheck) == msg.sender,
      "Sender is not exchanger"
    );
    _;
  }

  event ExchangeCompleted(
    address firstUser,
    address firstDfa,
    uint firstAmount,
    address secondUser,
    address secondDfa,
    uint secondAmount
  );

  constructor(address dfaAddressToLink) isAddressValid(dfaAddressToLink) {
    dfaAddress = dfaAddressToLink;
    factoryAddress = msg.sender;
  }

  function getCorrectRequestUser(
    address dfaToGive,
    uint amountToGive,
    uint amountToGet
  )
    external
    view
    isAmountsNotZero(amountToGive, amountToGet)
    isAddressValid(dfaToGive)
    returns (address)
  {
    ExchangerRequestInfo[] memory requestList = requests[dfaToGive];
    for (uint i = 0; i < requestList.length; i++) {
      ExchangerRequestInfo memory request = requestList[i];
      if (request.amountToGet == amountToGive && request.amountToGive == amountToGet) {
        return request.user;
      }
    }
    return address(0);
  }

  function addRequest(
    address dfaToGet,
    uint amountToGet,
    uint amountToGive
  )
    external
    isAmountsNotZero(amountToGive, amountToGet)
    isAddressValid(dfaToGet)
    isExchangerValid(dfaToGet)
    isAmountCanTransfer(amountToGive, dfaAddress)
  {
    address exchangerAddress = Factory(factoryAddress).getExchanger(dfaToGet);
    Exchanger exchanger = Exchanger(exchangerAddress);
    DFA(dfaAddress).transferFrom(msg.sender, address(this), amountToGive);
    address user = exchanger.getCorrectRequestUser(dfaAddress, amountToGive, amountToGet);
    if (user != address(0)) {
      DFA(dfaAddress).transfer(user, amountToGive);
      exchanger.tryToExchange(
        InnerExchangerRequestInfo(
          msg.sender,
          dfaAddress,
          amountToGive,
          amountToGet
        )
      );
    } else {
      requests[dfaToGet].push(
        ExchangerRequestInfo(
          msg.sender,
          amountToGet,
          amountToGive
        )
      );
    }
  }

  function tryToExchange(
    InnerExchangerRequestInfo memory info
  )
    external
    isAmountsNotZero(info.amountToGive, info.amountToGet)
    isAddressValid(info.dfaToGive)
    isSenderIsExchanger(info.dfaToGive)
    isAddressValid(info.user)
  {
    ExchangerRequestInfo[] storage requestList = requests[info.dfaToGive];
    bool isValidRequestFound = false;
    for (uint i = 0; i < requestList.length; i++) {
      ExchangerRequestInfo storage request = requestList[i];
      if (request.amountToGet == info.amountToGive && request.amountToGive == info.amountToGet) {
        emit ExchangeCompleted(
          request.user,
          dfaAddress,
          request.amountToGive,
          info.user,
          info.dfaToGive,
          info.amountToGive
        );
        request.amountToGet = 0;
        request.amountToGive = 0;
        DFA(dfaAddress).transfer(info.user, info.amountToGet);
        isValidRequestFound = true;
        break;
      }
    }
    require(isValidRequestFound, "Valid request not found");
  }

  function getRequestsByDfa(address dfa)
    public
    view
    isAddressValid(dfa)
    returns (
      address[] memory,
      uint[] memory,
      uint[] memory
    )
  {
    ExchangerRequestInfo[] memory reqs = requests[dfa];
    address[] memory users = new address[](reqs.length);
    uint[] memory amountsToGet = new uint[](reqs.length);
    uint[] memory amountsToGive = new uint[](reqs.length);
    for (uint i = 0; i < reqs.length; i++) {
      ExchangerRequestInfo memory info = reqs[i];
      users[i] = info.user;
      amountsToGet[i] = info.amountToGet;
      amountsToGive[i] = info.amountToGive;
    }
    return (users, amountsToGet, amountsToGive);
  }
}