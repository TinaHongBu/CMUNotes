# VI. System Functionality Tests

If you use **Postman** to test requests, type in http://“your DNS address:”“Port number your system is listening to/” “end points”

For example: [http://ec2-34-204-68-2.compute-1.amazonaws.com:3000/login](http://ec2-34-204-68-2.compute-1.amazonaws.com:3000/login)

and hit send to see if you get your results back.

Or to use **Artillery**, in the test file config session modify to “target”: “[http://ec2-34-204-68-2.compute-1.amazonaws.com:3000](http://ec2-34-204-68-2.compute-1.amazonaws.com:3000/)", and run this in terminal: artillery run test.json.

# VI. System Availability Tests

Kill instance and see if the traffic gets routed to other nodes.

Add elastic load balancing.

# VII. System Performance Tests

Hold Elasticsearch locally

- Install Elasticsearch

`brew install elasticsearch`

- Start the service

`brew services start elasticsearch`

- Set Elasticsearch to be accessible from the network

Move to Directory:

`elasticsearch-5.5.1/config`

`vim elasticsearch.yml`

`## put`

`network.host: 0.0.0.0`

Restart elasticsearch

`brew services restart elasticsearch`

Hold Elasticsearch on AWS EC2 instance

[https://www.elastic.co/blog/running-elasticsearch-on-aws](https://www.elastic.co/blog/running-elasticsearch-on-aws)

`sudo rpm -i https://download.elastic.co/elasticsearch/release/org/elasticsearch/distribution/rpm/elasticsearch/2.3.3/elasticsearch-2.3.3.rpm`

`sudo chkconfig --add elasticsearch`

`cd /usr/share/elasticsearch/ `

`sudo bin/plugin install cloud-aws -y`

`sudo service elasticsearch start`

`curl localhost:9200/_cluster/health?pretty`

[http://pavelpolyakov.com/2014/08/14/elasticsearch-cluster-on-aws-part-2-configuring-the-elasticsearch/](http://pavelpolyakov.com/2014/08/14/elasticsearch-cluster-on-aws-part-2-configuring-the-elasticsearch/)

`curl -XPOST 'https://search-edissp5-ucvcl2z7jslg37m75qujs63coy.us-east-1.es.amazonaws.com/_bulk'--data-binary @productRecords.json`

[https://search-edissp5-ucvcl2z7jslg37m75qujs63coy.us-east-1.es.amazonaws.com/](https://search-edissp5-ucvcl2z7jslg37m75qujs63coy.us-east-1.es.amazonaws.com/)