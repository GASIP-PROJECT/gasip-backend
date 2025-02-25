#!/bin/bash

REPOSITORY="/home/ubuntu/spring-github-action"
cd $REPOSITORY

APP_NAME=gasip
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

START_LOG="$REPOSITORY/start.log"
ERROR_LOG="$REPOSITORY/error.log"
APP_LOG="$REPOSITORY/application.log"

$ chmod 666 $START_LOG
$ chmod 666 ERROR_LOG
$ chmod 666 APP_LOG

SERVICE_PID=$(pgrep -f ${APP_NAME}.*.jar) # 실행중인 Spring 서버의 PID
NOW=$(date +%c)


echo "[$NOW] > $JAR_PATH 실행" >> $START_LOG
nohup java -Xms256m -Xmx512m -XX:+UseG1GC -jar $JAR_PATH --spring.profiles.active=dev > $APP_LOG 2> $ERROR_LOG &

echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG