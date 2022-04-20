const fs = require('fs');

const dir = './src/generated/backend';

fs.rmdir(dir, { recursive: true }, (err) => {
  if (err) {
    throw err;
  }
  console.log('Previously generated API has been deleted');
});
