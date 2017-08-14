# AWS Series 5 - MySQL and RDS

## 1. Setup a MySQL Database Instance

In the RDS dashboard, 

**Step 1: Select Engine**

- MySQL →

**Step 2: Production?**

- Dev/Test (Production version has 2 database instances in 2 different availability zones with one being primary and one being the backup, and synchronization is handled by Amazon) →

**Step 3: Specify DB Details**

- DB Instance Class chose db.t2.micro — 1 vCPU, 1 GiB RAM, 


- Multi-AZ Deployment: No,


- DB Instance Identifier: EDISS-P3,


- Master Username: admin, 


- Master Password: adminadmin →

**Step 4: Configure Advanced Settings**

- Availability Zone: Make sure same as your primary instances to decrease latency


- VPC Security Groups: DB tier 

## 2. Connect App Servers to the DB

Copy your DB endpoint: **ediss-p3.c6xzjopfczba.us-east-1.rds.amazonaws.com:3306**

Delete the port number, do

`mysql -h **ediss-p4.c6xzjopfczba.us-east-1.rds.amazonaws.com** -P 3306 -u admin -p`

`adminadmin (the password)`

``

`mysql -h **ediss-p4.c6xzjopfczba.us-east-1.rds.amazonaws.com **-P 3306 -u admin -p`

to enter the database.

`mysql> show databases;`

`+--------------------+`

`| Database           |`

`+--------------------+`

`| information_schema |`

`| EDISS              |`

`| innodb             |`

`| mysql              |`

`| performance_schema |`

`| sys                |`

`+--------------------+`

`6 rows in set (0.00 sec)`

`mysql> use EDISS;`

`Database changed`

``

`show tables;`

`select * from p2_prdct;`

`select * from p2_user;`

`select * from p2_usertype;`

shows that the EDISS database is successfully created already.

# V(2) Or, Connect Local MySQL Server to the AWS Instance

My system reads data from my MySQL database so I need to connect my AWS instance to my local MySQL server. The steps are taken from [here](https://support.rackspace.com/how-to/installing-mysql-server-on-centos/).

## 1. Install MySQL

Install the MySQL database through the CentOS package manager (yum):

`sudo yum install mysql-server`

## 2. Start mysql

`sudo /sbin/service mysqld start`

## 3. Enable chkconfig on MySQL

`sudo chkconfig mysqld on`

## 4. Launch the mysql shell and enter it as the root user:

`/usr/bin/mysql -u root -p`

Hit enter because no password is set for root user so far.

Now set root password

`SET PASSWORD FOR 'root'@'localhost' = PASSWORD('root');`

## 5. Launch MySQL

`brew services restart mysql`

`sudo mysql -u root -p `

`// enter password for root user`

## 6. Create database, table and insert default users in the table

Hopefully last line will generate duplicate entry error

## 7. Exit mysql from the terminal

`exit`

## 8. Or stop mysql when you need to

`sudo /sbin/service mysqld stop`

