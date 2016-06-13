import org.apache.hadoop.io.Text;
import org.apache.hadoop.dynamodb.DynamoDBItemWritable
/* Importing DynamoDBInputFormat and DynamoDBOutputFormat */
import org.apache.hadoop.dynamodb.read.DynamoDBInputFormat
import org.apache.hadoop.dynamodb.write.DynamoDBOutputFormat
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.io.LongWritable
 
var jobConf = new JobConf(sc.hadoopConfiguration)
jobConf.set("dynamodb.servicename", "dynamodb")
jobConf.set("dynamodb.input.tableName", "SparkTest")   // Pointing to DynamoDB table
jobConf.set("dynamodb.endpoint", "dynamodb.ap-southeast-1.amazonaws.com")
jobConf.set("dynamodb.regionid", "ap-southeast-1")
jobConf.set("dynamodb.throughput.read", "1")
jobConf.set("dynamodb.throughput.read.percent", "1")
jobConf.set("dynamodb.version", "2011-12-05")
 
jobConf.set("mapred.output.format.class", "org.apache.hadoop.dynamodb.write.DynamoDBOutputFormat")
jobConf.set("mapred.input.format.class", "org.apache.hadoop.dynamodb.read.DynamoDBInputFormat")
 
var orders = sc.hadoopRDD(jobConf, classOf[DynamoDBInputFormat], classOf[Text], classOf[DynamoDBItemWritable])
 
// Doing a count of items on Orders table
orders.count()



