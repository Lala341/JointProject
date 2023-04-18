var Hdfs = require('hdfs247')
var fs = require('fs');

var hdfs = new Hdfs({
  protocol: 'http',
   host: 'localhost',
  port: 9870

});

// Write a file to HDFS
const localpath = 'C:/Users/lauis/Downloads/Maestria/JointProject/hdfs/test/te.txt';
const remotepath = '/tt.txt';

hdfs.upload({
  'user.name': 'root',
  overwrite: true,
  localpath: localpath,
  path: remotepath
}, function(error, response, body) {
  console.log(error);    // Error will be null if upload process is succeed.
  console.log(response); // Raw response from node.js request.
  console.log(body);     // Body of request result.
});