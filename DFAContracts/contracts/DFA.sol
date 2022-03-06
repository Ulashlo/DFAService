// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract DFA {
  uint256 totalSupply;
  address public owner;

  mapping (address => uint256) public balances;

  constructor() public {
    owner = msg.sender;
    totalSupply = 0;
  }

  function mint(uint256 amount) public {
    totalSupply += amount;
    balances[msg.sender] += amount;
  }
}
