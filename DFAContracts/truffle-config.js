module.exports = {
  contracts_directory: './contracts/',
  contracts_build_directory: './build/contracts/',

  networks: {
    networks: {
      development: {
        host: "127.0.0.1",
        port: 7545,
        network_id: "*"
      }
    }
  },

  mocha: {
  },

  compilers: {
    solc: {
      version: "0.8.11",
      settings: {
        optimizer: {
          enabled: true,
          runs: 1
        }
      }
    }
  }
};