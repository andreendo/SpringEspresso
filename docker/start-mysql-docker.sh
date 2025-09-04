#!/bin/bash

docker stop $(docker ps -a -q)
echo "MySQL 8 na porta 3306"
echo "phpMyAdmin: http://localhost:8081 (login with root / root)"
docker-compose up