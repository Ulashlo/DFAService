const cracoLessPlugin = require('craco-less');
const cracoAlias = require('craco-alias');

module.exports = {
  plugins: [
    {
      plugin: cracoLessPlugin,
      options: {
        lessLoaderOptions: {
          lessOptions: {
            modifyVars: { '@primary-color': '#E30611' },
            javascriptEnabled: true,
          },
        },
      },
    },
    {
      plugin: cracoAlias,
      options: {
        source: 'tsconfig',
        baseUrl: './src',
        tsConfigPath: './tsconfig.extend.json',
      },
    },
  ],
};
