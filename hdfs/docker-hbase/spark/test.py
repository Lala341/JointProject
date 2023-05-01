from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("Test").getOrCreate()
sc = spark.sparkContext

data = [1, 2, 3, 4, 5]
rdd = sc.parallelize(data)
sum = rdd.reduce(lambda x, y: x + y)
print("Sum is:", sum)

spark.stop()
