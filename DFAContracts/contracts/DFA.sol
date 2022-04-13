// SPDX-License-Identifier: MIT
pragma solidity ^0.8.3;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract DFA is ERC20 {
  address public owner;
  address exchanger;

  constructor(
    uint initialSupply,
    address exchanger_address,
    string memory name,
    string memory symbol
  ) ERC20(name, symbol) {
    _mint(msg.sender, initialSupply);
    exchanger = exchanger_address;
  }

  modifier isOwner() {
    require(msg.sender == owner, "Caller is not owner");
    _;
  }

  modifier isExchanger() {
    require(msg.sender == exchanger, "Caller is not exchanger");
    _;
  }

  function transferForExchange(
    address from,
    address to,
    uint amount
  ) external isExchanger {
    _transfer(from, to, amount);
  }

  function mint(uint amount) external isOwner {
    _mint(owner, amount);
  }

  function decimals() public view virtual override returns (uint8) {
    return 0;
  }
}