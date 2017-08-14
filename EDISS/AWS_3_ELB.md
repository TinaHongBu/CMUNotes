# AWS Series 3 - Load Balancer

## Classic Load Balancer

### 1. Create a Load Balancer

In the EC2 console, “load balancer”:

Create Load Balancer → 

- Classic Load Balancer → 


- Listeners: HTTP port 3000, Availability Zones: at least chose 2  → 


- Security Groups: Group for Load Balancer →


- Ping Port: 3000, Ping Path: /health, Response Timeout:4, Interval: 5 →


- Add EC2 Instances later


- Routing: Create a new target group →


- Register instances to the target group

Don’t set it to be sticky, because

1. it sends requests from the same client to the same server the whole time so when the instance got killed, all following requests from its clients will still be routed to it and fail, and,
2. it will cause unbalance load among instances as the load balancer balances requests based on the traffic but not server load. So it could happen that one server gets a lot of big requests and cause connection draining.

### 2. Set up the ping target response in your code

The health ping target will be: http:3000/health

``` javascript
app.get('/health', **function**(req, res){
    res.send({"message": "Instance is alive"});
	// just return anything with code 200
});
```

## 3. Check if Load Balancer is attached to all instances

Everything should be in service if set up correctly.

![img](https://d2mxuefqeaa7sj.cloudfront.net/s_19BBF562584AB772842EB72D776E80FF230B155AEFC17587658730AFEC498F03_1499140252965_Screen+Shot+2017-07-03+at+11.50.31+PM.png)

Debug using this.

``` shell
curl -s -k -o /dev/null -v http://private-IP-address-of-the-instance:3000/health
```