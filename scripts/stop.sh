#!/bin/bash

REPOSITORY=/home/ubuntu/spring-github-action
cd $REPOSITORY

APP_NAME=gasip
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME
STOP_LOG="$REPOSITORY/stop.log"

$ chmod 666 STOP_LOG

CURRENT_PID=$(pgrep -f ${APP_NAME}.*.jar)

if [ -z $CURRENT_PID ]
then
  echo "서비스 NouFound" >> $STOP_LOG
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi
