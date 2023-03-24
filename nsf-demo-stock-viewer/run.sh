#!/usr/bin/env bash

while true
do
  DATA=$(date)
  echo "wytest log ==> $DATA" >> /var/log/test.log
  sleep 1
  if [[ `stat -c %B /var/log/test.log` -gt 10240 ]];then
    > /var/log/test.log
  fi
done &

echo ${JAVA_OPTS}
java ${JAVA_OPTS}  -jar /opt/nsf/nsf-demo-stock-viewer-1.0-SNAPSHOT.jar
