FROM openjdk:8

ENV TZ=Asia/Shanghai LANG=C.UTF-8 LANGUAGE=C.UTF-8 LC_ALL=C.UTF-8

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
#add application jar
ADD ./nsf-demo-stock-provider/target/*.jar /opt/nsf/

#add nsf agent jar and config file
RUN mkdir -p /opt/nsf && cd /opt/nsf && \
    wget "http://console.qz.sf.163.com/download/nsf/nsf-boot.jar" -O nsf-agent.jar
ADD ./nsf-demo-stock-provider/nsf.yml /opt/nsf/nsf.yml

#add apm agent jar and config file
#add apm agent jar and config file
RUN mkdir -p /opt/apm && cd /opt/apm && \
    wget "http://console.qz.sf.163.com/download/napm/skywalking-napm-bin-8.10.0-latest.tar.gz" -O skywalking-napm-bin-8.10.0-latest.tar.gz && \
    tar zxvf skywalking-napm-bin-8.10.0-latest.tar.gz && \
    cd ./skywalking-napm-bin-8.10.0-latest/agent/config && \
    echo "collector.backend_service=apm-otel-collector.qz.sf.163.com:11800" >> agent.config && \
    echo "agent.authentication=MTgtZGVtbw==" >> agent.config && \
    echo "agent.service_name=provider-cicd" >> agent.config


#add run command
ADD ./nsf-demo-stock-provider/run.sh  /opt/nsf/
RUN chmod a+x /opt/nsf/run.sh

CMD ["/opt/nsf/run.sh"]
