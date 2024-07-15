#!/bin/bash

REPOSITORY=/home/ubuntu/spring-github-action
cd $REPOSITORY

APP_NAME=gasip
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME
STOP_LOG="$ROOT_PATH/stop.log"

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "서비스 NouFound" >> $STOP_LOG
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi
