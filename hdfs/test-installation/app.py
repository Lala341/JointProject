from pyhdfs import HdfsClient

# Initialize HDFS client
client = HdfsClient(hosts='localhost:9000', user_name='root')

# Create folder
client.mkdirs('/myfolder')
