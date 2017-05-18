#!/bin/bash
sudo apt-get install maven --yes
sudo apt-get install openjdk-8-jdk --yes
apt-cache search jdk
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$PATH:/usr/lib/jvm/java-8-openjdk-amd64/bin
javac -version