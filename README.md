# Ethereum Smart Contract for Earnest Money 

This project demonstrates how an Etheruem Smart Contract can be used to manage `good-faith` earnest money contracts used in real estate transactions.
When a property buyer enters into a real estate contract, the realtor will typically request that the buyer deposit a sum of money as good faith (typically 1% of the sales price) to help convince the seller that the buyer is serious about the purchase. Currently, this is accomplished by:
1. The buyer writes a check to the realtor
2. The realtor holds the check until the seller agrees to the terms of the real estate contract.
3. The realtor deposits the check into a bank account.
4. The realtor writes a check to the closing agent (typically an attorney) and brings it to closing.
5. The closing agent deposits the check into a bank account.

If, for some reason, the real estate contract becomes invalid, the realtor needs to refund the money to the buyer either by returning the buyer's check or by writing a new check to the buyer.

This manual movement of writing and depositing checks is now automated using a smart contract containing the following functions:
- **register** -  A realtor registers a new earnest money agreement. A stipend is paid to the contract owner as a usage fee. Ether is taken from the realtor's account and transferred to the contract owner's account.
- **deposit** - A property buyer deposits funds. Ether is taken from the buyer's account and transferred to the smart contract balance.
- **release** - A realtor releases funds to the closing agent. Ether is transferred from the contract balance to the closing agent's account.
- **refund** - A realtor returns funds to the buyer. Ether is transferred from the contract balance to the closing agent's account.
- **cancel** - A realtor cancels an earnest money agreement after funds are either released or refunded.

## Development stack

This project was developed using Solidity v0.4.18, Truffle v4.0.4, and Ganache.

## Prerequisites

Used Visual Studio Code 1.19.2 for text editing `.sol` files.

## Build
From a command prompt opened to the source directory, run this command:

...`truffle.cmd compile --all`.. 

## Tests
It is recommended to run the tests individually and to restart Ganache after each test.  This will avoid _Out of Gas_ conditions from occurring while running the tests.

From a command prompt opened to the source directory, run this command:

...`truffle.cmd test ./test/earnestmoneyRegister.js`..
...`truffle.cmd test ./test/earnestmoneyDeposit.js`..
...`truffle.cmd test ./test/earnestmoneyRelease.js`..
...`truffle.cmd test ./test/earnestmoneyRefund.js`..
...`truffle.cmd test ./test/earnestmoneyCancel.js`..

## Migrations
From a command prompt opened to the source directory, run this command to deploy to the default Ganache instance:

...`truffle.cmd migrate --reset --network ganachedevelopment`..

The network name is defined in `truffle.js`.