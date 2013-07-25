#!/bin/bash

SERVER_ENV=$1

if [ -n "$1" ]; then
  SERVER_ENV="-${SERVER_ENV}"
fi

printf "\n" | java -cp "lib/main/*:bin/main/" -XX:MaxPermSize=256M -Dserver.env=${SERVER_ENV}  com.trailblazers.freewheelers.FreeWheelersServer & 
