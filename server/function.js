const https = require('https');
const querystring = require('querystring');

exports.handler = async (event, context) => {
  const fileData = JSON.parse(event.body);
  const nifiUrl = 'http://3.85.240.152:8080'; 
  const nifiPath = '/api/data';
  
  const postData = querystring.stringify({
     data: JSON.stringify(fileData),
  });
  
  const options = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Content-Length': postData.length,
    },
  };

  // Send the data to Nifi
  const response = await new Promise((resolve, reject) => {
    const req = https.request(nifiUrl + nifiPath, options, (res) => {
      const chunks = [];
      res.on('data', (chunk) => chunks.push(chunk));
      res.on('end', () => {
        const body = Buffer.concat(chunks);
        resolve({
          statusCode: res.statusCode,
          body: body.toString(),
        });
      });
    });
    req.on('error', (err) => reject(err));
    req.write(postData);
    req.end();
  });

  return response;
};
