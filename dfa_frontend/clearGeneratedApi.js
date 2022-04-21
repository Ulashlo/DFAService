const fs = require('fs');

const dir = './src/generated/backend';

fs.rmdir(dir, { recursive: true }, (err) => {
  if (err) {
    if (err.code === 'ENOENT') {
      console.log('Directory didnt exist');
    } else {
      throw err;
    }
  } else {
    console.log('Previously generated API has been deleted');
  }
});
