// SPDX-License-Identifier: MIT
pragma solidity ^0.8.3;

import "./DFA.sol";
import "./Factory.sol";

contract Exchanger {
  enum ExchangeStatus{ OPEN, CLOSE }
  enum ExchangeType{ INDIVISIBLE, DIVISIBLE }

  struct ExchangerRequestInfo {
    ExchangeType exchangeType;
    address user;
    uint amountToGet;
    uint amountToGive;
    uint endTime;
    ExchangeStatus status;
  }

  struct AddExchangeRequestParams {
    ExchangeType exchangeType;
    address dfaToGet;
    uint amountToGet;
    uint amountToGive;
    uint endTime;
  }

  struct GetReciprocalRequestInfoParams {
    ExchangeType exchangeType;
    address dfaToGive;
    uint amountToGive;
    uint amountToGet;
  }

  struct ExchangeView {
    uint amountToGet;
    uint amountToGive;
  }

  struct TryToExchangeParams {
    address dfaToGive;
    uint amountToGive;
    uint amountToGet;
    uint requestIndex;
    address buyer;
  }

  struct ReciprocalRequestInfo {
    bool isFound;
    address user;
    uint index;
    uint reciprocalAmountToGet;
    uint reciprocalAmountToGive;
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

  modifier isAmountCanTransfer(uint amount) {
    require(
      DFA(dfaAddress).balanceOf(msg.sender) >= amount,
      "Sender does not have enough dfa"
    );
    require(
      DFA(dfaAddress).allowance(msg.sender, address(this)) >= amount,
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

  function isIndivisibleReciprocalToIndivisible(
    ExchangeView memory first,
    ExchangeView memory second
  ) private pure returns(bool) {
    return first.amountToGet == second.amountToGive && second.amountToGet == first.amountToGive;
  }

  function isDivisibleReciprocalToDivisible(
    ExchangeView memory first,
    ExchangeView memory second
  ) private pure returns(bool) {
    return first.amountToGive * second.amountToGive == first.amountToGet * second.amountToGet;
  }

  function isDivisibleReciprocalToIndivisible(
    ExchangeView memory first,
    ExchangeView memory second
  ) private pure returns(bool) {
    return first.amountToGive * second.amountToGive == first.amountToGet * second.amountToGet &&
    first.amountToGet >= second.amountToGive &&
    first.amountToGive >= second.amountToGet;
  }

  function getReciprocalRequestInfo(
    GetReciprocalRequestInfoParams memory info,
    uint index
  )
    external
    view
    isAmountsNotZero(info.amountToGive, info.amountToGet)
    isAddressValid(info.dfaToGive)
    returns (ReciprocalRequestInfo memory)
  {
    ExchangerRequestInfo[] memory requestList = requests[info.dfaToGive];
    for (uint i = index; i < requestList.length; i++) {
      ExchangerRequestInfo memory request = requestList[i];
      if (request.status == ExchangeStatus.CLOSE || request.endTime < block.timestamp) {
        continue;
      }
      bool cond = false;
      if (request.exchangeType == ExchangeType.INDIVISIBLE &&
        info.exchangeType == ExchangeType.INDIVISIBLE) {
        cond = isIndivisibleReciprocalToIndivisible(
          ExchangeView(info.amountToGet, info.amountToGive),
          ExchangeView(request.amountToGet, request.amountToGive)
        );
      } else if (request.exchangeType == ExchangeType.INDIVISIBLE &&
        info.exchangeType == ExchangeType.DIVISIBLE) {
        cond = isDivisibleReciprocalToIndivisible(
          ExchangeView(info.amountToGet, info.amountToGive),
          ExchangeView(request.amountToGet, request.amountToGive)
        );
      } else if (request.exchangeType == ExchangeType.DIVISIBLE &&
        info.exchangeType == ExchangeType.INDIVISIBLE) {
        cond = isDivisibleReciprocalToIndivisible(
          ExchangeView(request.amountToGet, request.amountToGive),
          ExchangeView(info.amountToGet, info.amountToGive)
        );
      } else {
        cond = isDivisibleReciprocalToDivisible(
          ExchangeView(info.amountToGet, info.amountToGive),
          ExchangeView(request.amountToGet, request.amountToGive)
        );
      }
      if (cond) {
        return ReciprocalRequestInfo(true, request.user, i, request.amountToGet, request.amountToGive);
      }
    }
    return ReciprocalRequestInfo(false, address(0), 0, 0, 0);
  }

  function addRequest(
    AddExchangeRequestParams memory data
  )
    external
    isAmountsNotZero(data.amountToGive, data.amountToGet)
    isAddressValid(data.dfaToGet)
    isExchangerValid(data.dfaToGet)
    isAmountCanTransfer(data.amountToGive)
  {
    address exchangerAddress = Factory(factoryAddress).getExchanger(data.dfaToGet);
    Exchanger exchanger = Exchanger(exchangerAddress);
    DFA(dfaAddress).transferFrom(msg.sender, address(this), data.amountToGive);
    ReciprocalRequestInfo memory requestInfo = exchanger.getReciprocalRequestInfo(
      GetReciprocalRequestInfoParams(data.exchangeType, dfaAddress, data.amountToGive, data.amountToGet), 0
    );
    if (data.exchangeType == ExchangeType.INDIVISIBLE) {
      if (requestInfo.isFound) {
        DFA(dfaAddress).transfer(requestInfo.user, data.amountToGive);
        exchanger.tryToExchange(
          TryToExchangeParams(
            dfaAddress,
            data.amountToGive,
            data.amountToGet,
            requestInfo.index,
            msg.sender
          )
        );
        data.amountToGive = 0;
        data.amountToGet = 0;
      } else {
        requests[data.dfaToGet].push(
          ExchangerRequestInfo(
            data.exchangeType,
            msg.sender,
            data.amountToGet,
            data.amountToGive,
            data.endTime,
            ExchangeStatus.OPEN
          )
        );
      }
    } else {
      while (requestInfo.isFound && data.amountToGive > 0 && data.amountToGet > 0) {
        if (requestInfo.reciprocalAmountToGet <= data.amountToGive &&
          requestInfo.reciprocalAmountToGive <= data.amountToGet) {
          DFA(dfaAddress).transfer(requestInfo.user, requestInfo.reciprocalAmountToGet);
          exchanger.tryToExchange(
            TryToExchangeParams(
              dfaAddress,
              requestInfo.reciprocalAmountToGet,
              requestInfo.reciprocalAmountToGive,
              requestInfo.index,
              msg.sender
            )
          );
          data.amountToGive -= requestInfo.reciprocalAmountToGet;
          data.amountToGet -= requestInfo.reciprocalAmountToGive;
        } else {
          DFA(dfaAddress).transfer(requestInfo.user, data.amountToGive);
          exchanger.tryToExchange(
            TryToExchangeParams(
              dfaAddress,
              data.amountToGive,
              data.amountToGet,
              requestInfo.index,
              msg.sender
            )
          );
          data.amountToGive = 0;
          data.amountToGet = 0;
        }
        if (data.amountToGive <= 0 || data.amountToGet <= 0) {
          break;
        }
        requestInfo = exchanger.getReciprocalRequestInfo(
          GetReciprocalRequestInfoParams(data.exchangeType, dfaAddress, data.amountToGive, data.amountToGet), requestInfo.index + 1
        );
      }
      if(data.amountToGet > 0 && data.amountToGive > 0) {
        requests[data.dfaToGet].push(
          ExchangerRequestInfo(
            data.exchangeType,
            msg.sender,
            data.amountToGet,
            data.amountToGive,
            data.endTime,
            ExchangeStatus.OPEN
          )
        );
      }
    }
  }

  function tryToExchange(
    TryToExchangeParams memory params
  )
    external
    isAmountsNotZero(params.amountToGive, params.amountToGet)
    isAddressValid(params.dfaToGive)
    isSenderIsExchanger(params.dfaToGive)
    isAddressValid(params.buyer)
  {
    ExchangerRequestInfo storage request = requests[params.dfaToGive][params.requestIndex];
    request.amountToGet -= params.amountToGive;
    request.amountToGive -= params.amountToGet;
    if (request.exchangeType == ExchangeType.INDIVISIBLE ||
    request.amountToGet == 0 ||
      request.amountToGive == 0) {
      request.status = ExchangeStatus.CLOSE;
    }
    DFA(dfaAddress).transfer(params.buyer, params.amountToGet);
    emit ExchangeCompleted(
      request.user,
      dfaAddress,
      request.amountToGive,
      params.buyer,
      params.dfaToGive,
      params.amountToGive
    );
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
    uint len = 0;
    for (uint i = 0; i < reqs.length; i++) {
      if (reqs[i].status == ExchangeStatus.OPEN && reqs[i].endTime >= block.timestamp) {
        len++;
      }
    }
    address[] memory users = new address[](len);
    uint[] memory amountsToGet = new uint[](len);
    uint[] memory amountsToGive = new uint[](len);
    uint j = 0;
    for (uint i = 0; i < reqs.length; i++) {
      if (reqs[i].status == ExchangeStatus.OPEN && reqs[i].endTime >= block.timestamp) {
        ExchangerRequestInfo memory info = reqs[i];
        users[j] = info.user;
        amountsToGet[j] = info.amountToGet;
        amountsToGive[j] = info.amountToGive;
        j += 1;
      }
    }
    return (users, amountsToGet, amountsToGive);
  }
}
