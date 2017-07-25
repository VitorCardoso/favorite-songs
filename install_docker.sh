#!/bin/bash
echo "#############################################################"
echo "Installing docker ..."
echo "#############################################################"
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu  $(lsb_release -cs) stable"
sudo apt-get update --yes
sudo apt-get install docker-ce --yes
sudo service docker start
sudo curl -L "https://github.com/docker/compose/releases/download/1.14.0/docker-compose-$(uname -s)-$(uname -m)" > /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
sudo docker --version
sudo docker-compose --version
echo "#############################################################"
echo "Installing extra tools ..."
echo "#############################################################"
sudo sudo apt install apache2-utils --yes
sudo sudo apt install htop --yes
echo "#############################################################"
echo "Done"
echo "#############################################################"
