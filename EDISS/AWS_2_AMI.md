# AWS Series 2 - AMI

## What is AMI

IAM is Identity and Access Management which takes care of your user's credentials. 

AMI is different. It allows you to take a snapshot of your current EC2 instance, and clone more identical or slightly changed instances. It's very useful when you want to hold several servers with the same configuration. 

## How to Create and Use an AMI

### 1. Create an AMI of the current instance

Select the current running instance, create AMI. It will take around 1 minute. The connection to the instance will be shut down.

### 2. Create multiple instances with the AMI

Select the AMI at the left panel, create a new instance. Specify the availability zone to be different from the first instance, but within the AZ of your load balancer.

Or you can go to the launch instance panel, instead of choosing the Amazon Linux machine from quick start, choose from my AMIs.

### 3. Launch the Server in all the instances

```shell
cd Code
npm install
node app
```

## Further Reading

EC2 Instance Basics

RDS - Hold MySQL DB on AWS

Load Balancer and Elastic Load Balancing

Auto Scaling Group