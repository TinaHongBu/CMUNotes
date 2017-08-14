# AWS Series - EC2 Instance Basics

## What is an EC2 Instance

An EC2 instance is an AWS machine that you can use like your own computer. Think if it as a new laptop of yours. You can create one EC2 just as if you purchased it from a store, install the softwares or environment on it by typing commands just as if you clicked download and started the .dmg file, and then connect to it using SSH and let it do stuff for you as if you are accessing it physically.

There is nothing mysterious nor magic about AWS or EC2. It is an amazing idea. But it's basically some powerful machines (computing power) that Amazon lends to people to make money.

## Before Creating an EC2 - Security Groups Setup

Security groups control how the outside world (and you) can connect to the instance. And they not only apply to EC2 instances, hey also apply to database instances, your load balancer, etc. But the setup of security groups is located in the EC2 console. So let's talk about it here.

In the EC2 Console, click "Security Groups". Remember to give each group a descriptive name for future reference. You may be using the same security group for multiple instances or even multiple projects. It's good practice to make your resources as least accessible as possible.

### 1. For Load Balancer - Name: "Load Balancer"

- Type: Custom TCP, Protocol: TCP, Port Range: 3000, Source: Anywhere
Your load balancer will be the endpoint that your end users are route to. So let's allow all TCP traffic at the port of your server.

### 2. For App Server Instances - Name: "Web Tier"

- Type: Custom TCP, Protocol: TCP, Port Range: 3000, Source: Load Balancer Security Group ID
So your load balancer can route traffic to all your servers.

- Type: Custom TCP, Protocol: TCP, Port Range: 3000, Source: My IP
So you can connect to your servers by TCP.

- Type: SSH, Protocol: TCP, Port Range: 22, Source: My IP 
So you can SSH to your instance and make backend changes to it (as if you are physically with the computer and change it). It's just that there will be no graphic interface, all settings need to be done in the terminal.

### 3. For Database - Name: "DB Tier"

- Type: MYSQL/Aurora, Protocol: TCP, Port Range: 3306, Source: My IP
So you can connect to your DB.

- Type: MYSQL/Aurora, Protocol: TCP, Port Range: 3306, Source: Web Tier Security Group ID
So all your servers can connect to your databases. 

Now the security group is ready. I always prefer to setup the security groups before diving in to create the instance because after the instance is setup with a security group, it cannot be changed. You can modify the security group attached to it of course, but it will mean that all other instances with this security group will be affected. If you have to change the security group but only for this instance, the only solution is delete it and create a new one with the correct security group. 

## How to Create an EC2 

Open your EC2 console after logging in to your AWS account. Click Instances, then click launch instance. Most of the settings are straightforward and the default is good enough. But I find it helpful to be mindful of the following things in each step:

### Step 1

Most of the time, just choose The first choice Amazon Linux AMI 2017.03.1 (HVM), SSD Volume Type - ami-a4c7edb2.

### Step 2 - Size

The default is always a good start. The smallest machine comes with the free tier, which will suit your need most of the times if you are just playing around with AWS.

### Step 3 - Instance Details

Specify Subnet if you want your instance to be launched in a specific availability zone. The reason you want that could be that you want to locate one server in each zone to avoid single point of failure if one AZ is down.

Click Advanced Details to bootstrap your instance. Bootstrap simply means to make some changes/configurations to it during startup phase so you don't need to do it manually after it's up. For example, enter the following lines of bash script in the User Data to install Node.js, MySQL server and git on the EC2 instance.

    #!/bin/bash
    yum update -y
    curl --silent --location https://rpm.nodesource.com/setup_6.x | sudo bash -
    sudo yum -y install nodejs mysql-server git -y

### Step 5 - Tags

Also, add tags to help yourself manage your instances. Make them meaningful to yourself.

- name: AppServer

- project: p3

- env: test/prod

### Step 6 - Security Groups

Select "selecting an existing security group" and chose the web tier one.

And now you are good to go!

## How to Use it

### 1. Connect to it via SSH

In your terminal, cd to the folder with your pem file. Then type one of the following commands

    ssh -i Tina.pem ec2-user@public-ip
    ssh -i Tina.pem ec2-user@public-DNS 

Tina.pem can also be written as "./Tina.pem". 

Examples:

    ssh -i Tina.pem ec2-user@52.86.40.226
    ssh -i "./Tina.pem" ec2-user@ec2-34-227-227-6.compute-1.amazonaws.com

You will see something like this but it’s correct. Just ignore it.

    The authenticity of host 'ec2-198-51-100-1.compute-1.amazonaws.com (10.254.142.33)'
    can't be established.

### 2. Deploy Your Code

Now your EC2 is like a pure cute laptop with no code and apps, but only MySQL, git and node.js installed. To run your server on it, push your code to GitHub and clone it on your EC2, unless you want to manually copy and paste each code file. Run the following code to

- clone the code for my server
- install all the node dependencies
- and start the server app.js

    git clone https://github.com/TinaHongBu/CMUNotes.git
    cd CMUNotes
    npm install
    node app

Another way to get your code on your EC2 is to use scp, especially when you want to copy a big data file.

    scp -i full-path-to-pem-file full-path-to-local-folder full-path-of-ec2-folder

For example, if I want to copy the test folder in Documents to the inputData folder in the root directory of my EC2 instance, I will do

    scp -i /Users/Tina/Documents/test/Tina.pem /Users/Tina/Documents/test/ ec2-user@34.230.41.147:~/inputData

## How to Stop/Terminate it

In your EC2 console, select actions, instance state, then stop if you just want to pause it and may need to use this instance later or terminate if you want to delete if forever. Either choice will stop AWS from billing you for this service.

Make sure to go back to the main dashboard and check if there is 0 running instances if you want to make sure that you are not leaving something up there running and get a $300 bill end of the month. Don't ask me why I am mentioning this. The biggest design flaw on AWS is there is no show me all the running stuff that is costing me god damn money button. In the instance console, they only show you the instances in the specific region which you can change on the upper right corner. So when you built an amazing available and scalable system by deploying several servers in North Virginia, several in Ohio and several in North California, you have every reason to only remember to stop the ones in North Virginia and totally forget about checking the other 2. 

## Further Reading

Create an Image of your Instance to launch more of the clones of it. 

RDS - Hold MySQL DB on AWS

Load Balancer and Elastic Load Balancing

Auto Scaling Group
