# AWS Series 0 - AWS Setup

## Create an AWS Account

Here [www.aws.amazon.com](http://www.aws.amazon.com).

If you want to create your account using eastern region 1 as default, select it and you should see "N. Virginia" as the heading in the top right of the page, inbetween your name and "support".  If not,select "U.S. East N. Virginia" from the drop down.

## Create a New IAM User

Here [https://console.aws.amazon.com/iam/home?region=us-east-1#home](https://console.aws.amazon.com/iam/home?region=us-east-1#home).

- Select "users" from the menu on the left handside
- Click the "Create New Users" button at the top of the page
- Enter name for a user (any name you want)
- Click "create"
- Click "Download Credentials" (save this filesomewhere, you’ll need this information for a future step)

## Give the IAM User Permissions

- Select "users" again from the menu on the lefthand side
- Click on the name of the user you just created
- Select the "permissions" tab on the lower half of the window 
- Select "Add Permissions"
- Select "Attach existing policies directly"
- Select "AdministratorAccess" and select "AddPermissions"