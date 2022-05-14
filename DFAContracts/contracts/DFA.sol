// SPDX-License-Identifier: MIT
pragma solidity ^0.8.3;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract DFA is ERC20 {
  address public owner;

  modifier isAddressValid(address addressToCheck) {
    require(addressToCheck != address(0), "Invalid token address");
    _;
  }

  modifier isOwner() {
    require(msg.sender == owner, "Caller is not owner");
    _;
  }

  constructor(
    uint initialSupply,
    string memory name,
    string memory symbol,
    address dfaOwner
  )
    ERC20(name, symbol)
    isAddressValid(dfaOwner)
  {
    _mint(dfaOwner, initialSupply);
    owner = dfaOwner;
  }

  function mint(uint amount) external isOwner {
    _mint(owner, amount);
  }

  function decimals() public view virtual override returns (uint8) {
    return 0;
  }
}
