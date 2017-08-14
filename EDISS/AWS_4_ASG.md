# AWS Series 4 - Auto Scaling Group (ASG)

## 1. Create a Launch Configuration

In the EC2 console, choose Launch Configuration

- Create from existing AMI


- User Data

`#!/bin/sh`

`yum update -y`

`git clone https://github.com/TinaHongBu/PROJ.git`

- Use app server security group

## 2. Create a Scaling Group

In the EC2 console, choose Auto Scaling Group

- Add subnets → 

![img](https://d2mxuefqeaa7sj.cloudfront.net/s_19BBF562584AB772842EB72D776E80FF230B155AEFC17587658730AFEC498F03_1499571875362_Screen+Shot+2017-07-08+at+11.44.13+PM.png)

- Use scaling policies to adjust the capacity of this group: choose “Scale the Auto Scaling group using step or simple scaling policies”, Add new alarm for both the increase group size and decrease group size section.

![img](https://d2mxuefqeaa7sj.cloudfront.net/s_19BBF562584AB772842EB72D776E80FF230B155AEFC17587658730AFEC498F03_1499572227922_Screen+Shot+2017-07-08+at+11.50.05+PM.png)

## 3. Using Application Load Balancer with Auto Scaling Group

Create a new target group, in the “target” tag choose all instances launched by the auto scaling group. Add to register, then save. 

Go to load balancer, add rule. 