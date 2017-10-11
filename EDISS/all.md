# **EDISS Notes**

About this course, the instructor is not gonna teach anything. So I listed materials that I find very helpful to me throughout this course. Most of them are video classes on Lynda, which CMU students have free access to if you log in using your Andrew account through [https://www.cmu.edu/lynda/](https://www.cmu.edu/lynda/).

We will be building an e-commerce server and deploy it on AWS. Each project builds upon some functionality or quality attributes like availability, performance to the project.

# 1. Javascript

- Basic syntax
- Import/export as modules
- Callback function

# 2. NodeJS

- What a NodeJS web server will look like: [Lynda course](https://www.lynda.com/Node-js-tutorials/RESTful-Web-API-Design-Node-js/577372-2.html)
- Module ‘express’ for handling HTTP requests: [Lynda course](https://www.lynda.com/Express-js-tutorials/Building-Website-Node-js-Express-js/502310-2.html)
- Set up dev environment
- Setup testing tools: [Postman](https://www.lynda.com/Node-js-tutorials/Acceptance-testing-Postman/577372/597050-4.html?autoplay=true)

# 3. AWS

## 3.1 Local Machine Setup

- [Install AWS Command Line Interface or CLI ](http://docs.aws.amazon.com/cli/latest/userguide/installing.html)

`**pip install --upgrade --user awscli**`

And check if installed successfully

`aws --version`

`aws-cli/1.11.97 Python/2.7.10 Darwin/16.6.0 botocore/1.5.60`

## 3.2. AWS Global Infrastructure

Different regions across the world, with at least 2 availability zones or AZ each region. 

ELB: Elastic Load Balancer that allows to distribute your application across different AZ

EIP: Elastic IP Address

ASG: Auto scaling group increases/decreases [#instances](https://paper.dropbox.com/?q=%23instances) in each AZ

## 3.3. Storage

Simple Storage Service (S3)

EC2 Block Storage (EBS)

Elastic File System (EFS)

Database:

DynamoDB: no-sql 

RDS: 

Elastic Cache

# 4. Availability

## 4.1 Concept

- Goal: to support the business needs because downtime costs a lot

- Availability = MTBF / (MTBF + MTTR) (mean time between failures, mean time to repair)

- 99.999%: <1 sec downtime per day → needs to be handled automatically instead of manuallyn

- Scalable (sequential) system makes failures much more likely

- Parallel/redundant components improve availability

- Faults

- - Fail silent VS. Fail operational
  - Transient VS. Deterministic
  - Hung process / Processor crash / Network outrage that looks the same as faults to end users

## 4.2 Fault Detection

- Heartbeat

- - liveness but not correctness
  - adds overhead

- Ping/Pong

- - external element monitoring 
  - liveness but not correctness
  - more coupling than heart beat
  - increases network traffic

- Exceptions

- - detect occurrence anomalous or exceptional event

- Voting

- - compare results with redundant components that perform same operation
  - detect erroneous output
  - adds overhead (must wait for operations done in all components and compare)

- Checksum

- - check data integrity, detect data corruption
  - adds some overhead

## 4.3 Masking faults

- Modular redundancy

- - State must be replicated across copies

  - Cold standby

  - - Non-operational copies available, brings online only when failure occurs
    - State is stored in logs, and
    - only reconstructed in the replica when failure occurs
    - Reduces operational overhead regards to maintain copies
    - Increases mean time to repair or MTTR

  - Warm standby / passive redundancy

  - - backup is online, but
    - not actively processing requests
    - state is periodically loaded into the backups (so not completely in sync but not too far away)
    - overhead is higher than cold standby
    - MTTR is less than cold standby and depends on the state checkpoints 

  - Hot standby

  - - multiple redundant components processing requests in parallel
    - all in the same state, synchronized continuously
    - Processing overhead increases a lot
    - MTTR is 0
    - Synchronization achieved by some transmission protocol: recipient has to acknowledge receiving messages and the integrity of the message (such as using checksum)

- Software redundancy / Hardware redundancy

- Key is state management

## 4.4 Fault Recovery

- Checkpoint/rollback

- - undo a transaction

  - - transaction: transaction performs actions that either all succeed or all fail

  - roll back to a previous state with a log of the transactions occurred since the snapshot was taken

  - Transactions: group sequential steps so they can be undone at once when rolling back

- Roll forward

- - restore a previous safe state and then applied the changes involved in the transactions

- Retry an operation

- Shedding load

- - timing errors/buffer overflows/memory consumption issues

- Reboot

- Faulty component removal

## 4.5 Implementation on AWS

Elastic IP address: which will automatically assign the same IP to a different instance if the previous instance in use wad down. Developers don’t need to bring a new server online and get it’s new IP.

**Rout 53 Health Check**

available, functional 

active/passive failover 

active routing: weighted round robin (3:1), latency-based, geolocation-based.

when failure happens, traffic will be routed only to healthy instances

**CloudWatch**

Monitoring

**Auto Scaling(launch config: what to scale + auto scaling group: how to scale)**

a. Amazon Machine Image or AMI

Using AMI allows you to launch multiple instances with the same configuration. 

Unit of deployment.

First step when creating an EC2 instance.

Modifying instances on startup with shell script

`--image-id`

`--count `

`--instance-type`

`--key-name`

`--subnet-id`

`--security-group-ids`

`--user-data`

`--profile`

**Elastic IP**** ****(****EIP) addressing**

Can be moved from one EC2 instance to another healthy instance

Replaces existing public IP of an EC2 instance

Lambda: automatically scaling

API gateway

# Decouple Components

Simple Queue Service (SQS): pulling based

- - components can communicate asynchronously and concurrently
  - decouple system components


- e.g. Upload an image on social media:


- - - image upload
    - image downsize
    - watermark added
    - notification sent

Simple Notification Service (SNS)

# 5. Performance

## 5.1 What does “performance” mean?

- Latency?


- response time after a stimulus (2 hours to finish a task)


-  = execution time+ wait time (we need to understand how many tasks will be there in the system (system load), will they have different priorities)


- - Worse case / Average case
  - Hard / Soft real time
  - Event arrival rate & arrival pattern (periodic / stochastic / sporadic)
  - Network latency


- Predictability: variations between executions
- Resource utilization: Network / CPU / Power consumption / Memory / Data stores
- Throughput: average rate of task execution per unit (do 5 tasks simultaneously)

## 5.2 Improve performance

Understanding the contributors → bottlenecks → where to improve

- Reduce resource demand

- - increase computation efficiency
  - reduce computational overhead (the resources required for processing an event stream)
  - reduce the number of events
  - control frequency of sampling

- Manage resources

- - Introduce concurrency
  - Replication of data to reduce contention
  - Increase available resources

- Schedule resources

- - First-in/first-out or FIFO
  - Fixed-priority
  - Dynamic priority: round robin / earliest deadline first
  - Static

## 5.3 Implement on AWS

- Set up 

# 6. Consistency

## 6.1 Eventual Consistency

- If no additional updates are made to a given data item, all reads to that item will eventually return the same value

- A system that has achieved eventual consistency is often said to have **converged**

- Eventual consistency is a weak guarantee

- - Guaranteed **Liveness**: something good will happen
  - Not Guaranteed **Safety**: an eventually consistent system can return any value before it converges (nothing bad will happen)

- Eventually consistent services are often classified as providing BASE (**B**asically **A**vailable, **S**oft state, **E**ventual consistency) semantics, in contrast to traditional ACID[ ](https://en.wikipedia.org/wiki/ACID)(**A**tomicity, **C**onsistency, **I**solation, **D**urability) guarantees

- - ACID: locks for each transaction 

  - - read lock: before you read data
    - write lock: before you write data

- How likely is a read to be out of date?


- If a node updates continuously then the only delay is local processing time and network delay → consuming a lot of processing power + consume more network bandwidth


- If updates are bundled → saves processing power + saves bandwidth BUT increase the likelihood that the reads will be out of date


- How to implement

- - Data stores send an asynchronous all-to-all broadcast

  - - ![face savouring delicious food](https://paper.dropboxstatic.com/static/img/ace/emoji/1f60b.png) Don’t have to worry about network partitions
    - ![face savouring delicious food](https://paper.dropboxstatic.com/static/img/ace/emoji/1f60b.png) Don’t have to worry about failed elements
    - ![face savouring delicious food](https://paper.dropboxstatic.com/static/img/ace/emoji/1f60b.png) Fast, no waiting on updates
    - ![face with open mouth and cold sweat](https://paper.dropboxstatic.com/static/img/ace/emoji/1f630.png) Lack of safety guarantees

  - Lamport’s clock: allow you to determine ordering

- Measures


- 100 ms after a write 99.9% of the reads will return the most recent updates/a version that is no more than 2 versions out of date

## 6.2 CAP Theorem - Consistency, Availability and partition tolerance

Partition → Consistency or Availability (response time)?

- Consistency: You customers, once they have updated information with you, will always get the most updated information when they call subsequently. No matter how quickly they call back
- Availability: Remembrance Inc will always be available for calls until any one of you(you or your wife) report to work.
- Partition Tolerance: Remembrance Inc will work even if there is a communication loss between you and your wife!

# 7. NoSQL

## 6.1 History

1980 → SQL

- Problems: stores one whole thing divided into lots of tables, mapping and matching is too much work. Not straightforward

1990 → Object Databases

- Problems: SQL databases had already been integrated into multiple applications so hard to be thrown away

2000 → Relational Dominance

2010 → Big internet sites, so much traffic → needs scalability → bigger boxes of storage / more smaller storage → SQL doesn’t work well

- Reason: As a result of the structure of the relational model data is typically pulled from multiple tables.  As the data is split across multiple nodes the queries often require fetching data from multiple nodes.  Doing this in an efficient way can be difficult

NoSQL movement: 

- Non-relational
- Open-source
- Cluster-friendly
- 21st century web
- No Schema

## 6.2 NoSQL Data Models and Graph Databases

**Aggregate-oriented**

- Key-value store
- Document: represented in forms of JSON

But may not be quite different from each other

Aggregate for key-value is the value. Aggregate for document is the document.

![img](https://d2mxuefqeaa7sj.cloudfront.net/s_47B5FB455E4507F52F29969FF43263ADF73BF67D1B6FFBCEB84B1379B80DF530_1499534892975_Screen+Shot+2017-07-08+at+1.27.52+PM.png)

- Column Family: row key + n * (column key + column value). Data retrieval is easier.

NoSQL

→ Increases flexibility? 

- Not necessarily. There is an implicit scheme recognized by the application consuming the data: assume there is a certain field, with a integer value. Still needs to manage schema.

→ Whole thing in the database at one place.

→ When change the aggregating method (group by things that is not the key), it’s hard.

**Graph Database Data Models**

Good at moving along relationships. without foreign keys and joins like relational databases.

also schema-less

## 6.3 Consistency of NoSQL

**Logical consistency**

What to do when 2 users try to modify one single record at the same time?

- Graph databases: follow ACID updates. More breakdown than SQL databases.
- Aggregated-oriented databases: don’t need transactions. From get data to post data is one transaction. But holding the transactions open will increase the latency a lot that most systems cannot afford.

→ offline lock: give a version stamp

**Replication consistency**

What to do when 2 users try to modify one single record stored/replicated in 2 different storages at the same time? 

A business choice.

## 6.4 When to Consider NoSQL?

- Large amount of data that can’t fit on a single server
- Shorter development time
- Data Warehousing 

## 6.5 MongoDB

- Three basic ways to model One-to-N relationships

- - One-to-Few: embedding 

````> db.person.findOne()
{
name: 'Kate Monster',
addresses : [
{ street: '123 Sesame St', city: 'Anytown', cc: 'USA' },
{ street: '123 Avenue Q', city: 'New York', cc: 'USA' }
]
}
````

- - One-to-many: an array of references 

  ```
  > db.products.findOne()

  {

  name : 'left-handed smoke shifter',

  parts : [     // array of references to Part documents

  ObjectID('AAAA'),    // reference to the #4 grommet above

  ObjectID('F17C')    // reference to a different Part

  ]

  }
  ```

```

```

`> db.parts.findOne()`

`{`

`    _id : ObjectID('AAAA'),`

`    partno : '123-aff-456',`

`    name : '#4 grommet',`

`    qty: 94,`

`    cost: 0.94,`

`    price: 3.99`

`}`

- - one-to-squillions: one reference


- e.g. an event logging system that collects log messages for different machines

`> db.hosts.findOne()`

`{`

`    _id : ObjectID('AAAB'),`

`    name : 'goofy.example.com',`

`    ipaddr : '127.66.66.66'`

`}`

`>db.logmsg.findOne()`

`{`

`    time : ISODate("2014-03-28T09:42:41.382Z"),`

`    message : 'cpu is on fire!',`

`    host: ObjectID('AAAB')       // Reference to the Host document`

`}`

- More sophisticated schema designs

- - denormalization: 

  - - Denormalizing saves you a lookup of the denormalized data at the cost of a more expensive update
    - You cannot perform an atomic update on denormalized data
    - Denormalization only makes sense when you have a high read to write ratio

`> db.products.findOne()`

`{`

`    name : 'left-handed smoke shifter',`

`    manufacturer : 'Acme Corp',`

`    catalog_number: 1234,`

`    parts : [`

`        { id : ObjectID('AAAA'), name : '#4 grommet' },         // Part name is denormalized`

`        { id: ObjectID('F17C'), name : 'fan blade assembly' },`

`        { id: ObjectID('D2AA'), name : 'power switch' },`

`        // etc`

`    ]`

`}`

- - two-way referencing: can represent M-to-M relation

`db.person.findOne()`

`{`

`    _id: ObjectID("AAF1"),`

`    name: "Kate Monster",`

`    tasks [     // array of references to Task documents`

`        ObjectID("ADF9"), `

`        ObjectID("AE02"),`

`        ObjectID("AE73") `

`    ]`

`}`

`db.tasks.findOne()`

`{`

`    _id: ObjectID("ADF9"), `

`    description: "Write lesson plan",`

`    due_date:  ISODate("2014-04-01"),`

`    owner: ObjectID("AAF1")     // Reference to Person document`

`}`

- Sharding


- to distribute data across clusters for horizontal scaling

## 6.6 DynamoDB on AWS

- Designed to tolerate hardware failure

- - Automatically replicate across multiple availability zones

- Designed to support scalability

- - Elastic storage
  - Elastic throuput
  - Continued availability

- Low Latency

- - data stored on solid-state drives → Fast Data Access

- High Performance

- High Throughput

- Helps keep application stateless

- - Good to store user session state (login or not)
  - Session state should not be saved on web servers
  - set ELB sticky for a single user to be routed to the same server all the time → single point of failure
  - store login info in a central place → DynamoDB