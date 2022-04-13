// SPDX-License-Identifier: MIT
pragma solidity ^0.8.3;

import "./DFA.sol";

contract Exchanger {
    struct ExchangeInfo {
        address user;
        address dfa;
        uint amount;
    }

    struct ExchangeRequest {
        address user;
        uint amountToGet;
        uint amountToGive;
    }

    mapping (address => mapping (address => ExchangeRequest[])) public requests;

    modifier isEnough(ExchangeInfo memory info) {
        DFA dfa = DFA(info.dfa);
        require(info.amount <= dfa.balanceOf(info.user), "Does not enough dfa");
        _;
    }

    function addExchangeRequest(
        address user,
        address dfaToGet,
        uint amountToGet,
        address dfaToGive,
        uint amountToGive
    ) external {
        ExchangeRequest[] storage reqsToExchange = requests[dfaToGive][dfaToGet];
        for (uint i = 0; i < reqsToExchange.length; i++) {
            ExchangeRequest storage request = reqsToExchange[i];
            if (request.amountToGive * amountToGive == request.amountToGet * amountToGet) {
                if (amountToGive <= request.amountToGet) {
                    exchange(
                        ExchangeInfo(user, dfaToGive, amountToGive),
                        ExchangeInfo(request.user, dfaToGet, amountToGet)
                    );
                    request.amountToGet -= amountToGive;
                    request.amountToGive -= amountToGet;
                    return;
                } else {
                    exchange(
                        ExchangeInfo(user, dfaToGive, request.amountToGet),
                        ExchangeInfo(request.user, dfaToGet, request.amountToGive)
                    );
                    amountToGet -= request.amountToGet;
                    amountToGive -= request.amountToGive;
                    delete requests[dfaToGive][dfaToGet][i];
                }
            }
        }
        ExchangeRequest[] storage reqsToSave = requests[dfaToGet][dfaToGive];
        for (uint i = 0; i < reqsToSave.length; i++) {
            ExchangeRequest storage request = reqsToSave[i];
            if (request.user == user &&
                request.amountToGive * amountToGive == request.amountToGet * amountToGet) {
                request.amountToGive += amountToGive;
                request.amountToGet += amountToGet;
                return;
            }
        }
        reqsToSave.push(ExchangeRequest(user, amountToGet, amountToGive));
    }

    function exchange(
        ExchangeInfo memory first,
        ExchangeInfo memory second
    ) internal
    isEnough(first)
    isEnough(second) {
        DFA dfaFirst = DFA(first.dfa);
        DFA dfaSecond = DFA(second.dfa);
        dfaFirst.transferForExchange(first.user, second.user, first.amount);
        dfaSecond.transferForExchange(second.user, first.user, second.amount);
    }

    function min(uint a, uint b) internal pure returns(uint) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }
}