#!/bin/bash
sudo apt update -y
sudo apt upgrade -y

sudo apt install mariadb-server -y

# Kill the anonymous users
sudo mysql -e "DROP USER ''@'localhost'"
# Because our hostname varies we'll use some Bash magic here.
sudo mysql -e "DROP USER ''@'$(hostname)'"
# Kill off the demo database
sudo mysql -e "DROP DATABASE test"
# Make our changes take effect
sudo mysql -e "FLUSH PRIVILEGES"
# Any subsequent tries to run queries this way will get access denied because lack of usr/pwd param

sudo mysql -e "CREATE USER pkeugine@localhost IDENTIFIED BY 'eugineiscool'"
sudo mysql -e "GRANT ALL ON *.* TO 'pkeugine'@'localhost' IDENTIFIED BY 'eugineiscool' WITH GRANT OPTION"
sudo mysql -e "FLUSH PRIVILEGES;"

sudo systemctl restart mariadb.service

