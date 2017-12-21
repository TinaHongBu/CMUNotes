# XSEDE Big Data Workshop

## Compute Nodes

```shell
interact # start a interactive session with a standard compute node 
interact -gpu # GPU nodes
```

## Modules

```shell
module load spark
module load tensorflow/1.1.0
```

## Big Data

Size

- 1000 Genomes project: 260 TB hosted on AWS
- Common Crawl soon hosted on Bridges. 300 - 800 TB

Throughput

- IoT: endless streaming

Problems with SQL with Big Data

- Fixed schema doesn't suit agile data
- Flexible queries are inhibited by joining tables (slow)
- sharding isn't trivial
- caching is tough: SQL is more about reliability and consistency (for banks) -> extreme performance penalty

NoSQL 

- Key-value: often used for session information (online games, shopping carts)
- Document: adds more structures (JSON) to it so we can perform searches (for things in the document object)
- Wide Column Stores: 
- Graph: good for semantic web (connectivity problem); hard for visualization & serialization (to disk file) & queries
  - Queries (SPARQL, Cypher)

## HDFS

HBASE & HIVE

- WORM: write one read many -> optimized for streaming throughput
- Replication
- Process data in place

To start using Hadoop and Spark with Yarn and HDFS on Bridges, connect to the login node and issue the following commands:

```shell
interact -N 3 # you will need to wait until resources are allocated to you before continuing
module load hadoop
start-hadoop.sh
hdfs dfs -ls
```

default location: /usr/

Launch a Hadoop job: code.java -> compile to code.class -> wrap to code.jar -> submit the jar file

## Spark

Spark SQL, GraphX, MLlib

- Performance: use RAM & be smarter
- Ease of Use: Python, Scala, Java
- New Paradigms:
- Use HADOOP as the backend store is a common and sensible option
- Data in RDD: resilient distributed dataset
- Transforms vs. Actions

```shell
# 28 cores per node
```

Common Transformations

- map()
- flatMap()
- filter()
- ...

Actions

- collect()
- count()
- saveAsTetFile(path)
- first(), take(n), top(n), takeSample(...)

Pair RDDs

- key/value tuples
- Persistence: `rdd.persist`
- Partition: 
- Programming features: Accumulators, Broadcast Variables, 
- Performance & Debugging: Spark Web UI
- No ordering

Spark 2.0: SparkSQL & DataFrames

Reverse the key and value when you want to search something for the value because you can always search by key.

## Shakespear Example

```python
interact
# change to shakespear directory
module load spark
pyspark # start the pyspark shell
rdd = sc.textFile("Complete_Shakespeare.txt")
# Number of lines
rdd.count()
# 124787 

# 2. Number of words
rdd.flatMap(lambda x: x.split()).count() 
# tokens = rdd.flatMap(lambda line: line.split()).map(lambda s: len(s)).reduce(lambda a, b: a + b)
# Don't use split(" ") because it will treat multiple spaces separately
# split() runs of consecutive whitespace are regarded as a single separator
# 4054428

# 3. Number of unique words
rdd.flatMap(lambda x: x.split()).distinct().count() 

from operator import add
from collections import Counter
words = rdd.flatMap(lambda doc: Counter(doc.split()).items()).reduceByKey(add)
count = words.distinct().count()
# 67779

# 4. Number of occurrence of each word
words.collect()

# 5. Show the top 5 most frequent words
words.map(lambda (k,v): (v,k)).sortByKey(False).top(5)
words.sortBy(lambda a: -a[1]).take(5) # Descending order
words.sortBy(lambda a: a[1]).take(5) # Ascending order
# [(506610, u''), (23407, u'the'), (19540, u'I'), (18358, u'and'), (15682, u'to')

# 6. Word frequency after removing stop words - homework
'''
<<Copyright>> separated sections and acts, how to separate by sections, in separate RDDs or separate keys?
'''

```

No ordering imposed in an RDD. Comes in as ordered. 

## MLib

Clustering

To run some pythong script: `execfile("clustering.py")`

## Deep Learning

```shell
# Get a GPU node
interact -gpu
module load tensorflow/1.1.0
source $TENSORFLOW_ENV/bin/activate
python
```

```python
from tensorflow.example.tutorials.mnist import input_data
import tensorflow as tf
mnist = input_data.read_data_sets(".", one_hot = True)
x , y = mnist.train.next_batch(2)
# Placeholder for inputs and outputs
x = tf.placeholder(tf.float32, [None, 784])
y_ = tf.placeholder(tf.float32, [None, 10])
# Variable of the weights
W = tf.Variable(tf.zeros([784, 10]))
b = tf.Variable(tf.zeros([10]))
y = tf.matmul(x, W) + b
# Error definition - Softmax Regression 
cross_entropy= tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(labels=y_, logits=y))
train_step= tf.train.GradientDescentOptimizer(0.5).minimize(cross_entropy)
# Launch the model and initialize the variables
sess= tf.InteractiveSession()
tf.global_variables_initializer().run()
# Training
for _ in range(1000):
  batch_xs, batch_ys = mnist.train.next_batch(100)
  sess.run(train_step, feed_dict={x: batch_xs, y_: batch_ys}) 
# Prediction
correct_prediction = tf.equal(tf.argmax(y,1), tf.argmax(y_,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
print(sess.run(accuracy, feed_dict={x: mnist.test.images, y_: mnist.test.labels}))
# 92%
```

### Convolutional Net

Edge filter

depth: different filters applied

Don't flatten the 2-D input image, but reserve the structure

Tensorflow takes care of the paddings

