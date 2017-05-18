#!/bin/bash
echo "#############################################################"
echo "Checking for Updates ..."
echo "#############################################################"
sudo apt-get update --yes
echo "#############################################################"
echo "Installing docker ..."
echo "#############################################################"
sudo apt-get install apt-transport-https ca-certificates --yes
sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
sudo echo "deb https://apt.dockerproject.org/repo ubuntu-xenial main" > /etc/apt/sources.list.d/docker.list
sudo apt-get update --yes
sudo apt-get purge lxc-docker --yes
sudo apt-get install docker-engine --yes
sudo service docker start
sudo curl -L "https://github.com/docker/compose/releases/download/1.13.0/docker-compose-$(uname -s)-$(uname -m)" > /usr/local/bin/docker-compose
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