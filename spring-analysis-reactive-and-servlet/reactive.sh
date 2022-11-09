#!/bin/bash

if [[ $@ == *"build"* ]]
then
    echo "Build reactive service.."
    ./gradlew :reactive-servlet:reactive:build
    docker-compose -f docker-compose-reactive.yaml build
fi

if [[ $@ == *"start"* ]]
then
    echo "Restarting the test environment..."
    echo "$ docker-compose -f docker-compose-reactive.yaml down --remove-orphans"
    docker-compose -f docker-compose-reactive.yaml down --remove-orphans
    echo "$ docker-compose -f docker-compose-reactive.yaml up -d"
    docker-compose -f docker-compose-reactive.yaml up -d
fi

if [[ $@ == *"stop"* ]]
then
    echo "Stopping the test environment..."
    echo "$ docker-compose -f docker-compose-reactive.yaml down --remove-orphans"
    docker-compose -f docker-compose-reactive.yaml down --remove-orphans
    docker rmi "$(docker images -f "dangling=true" -q)"
    exit 1
fi

while ! docker exec -i $(docker ps | grep mysql | awk '{print $1}') mysqladmin -uuser -p1234 --host "127.0.0.1" ping --silent &> /dev/null ; do
    echo "Waiting for database connection..."
    sleep 2
done

cd reactive-servlet/reactive

echo "Create Database Table"
docker exec -i $(docker ps | grep mysql | awk '{print $1}') mysql -uuser -p1234 reactive < schema.sql

echo "Create Database Dummy Data"
docker exec -i $(docker ps | grep mysql | awk '{print $1}') mysql -uuser -p1234 reactive < data.sql