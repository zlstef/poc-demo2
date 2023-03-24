FROM harbor.cloud.netease.com/library/openjdk:8-jdk-slim-bullseye

ADD nsf-demo-stock-viewer/target/*.jar /opt/nsf/

ADD nsf-demo-stock-viewer/run.sh  /opt/
RUN chmod a+x /opt/run.sh
CMD ["/opt/run.sh"]
