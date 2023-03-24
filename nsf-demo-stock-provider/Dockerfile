FROM harbor.cloud.netease.com/library/openjdk:8-jdk-slim-bullseye

ADD nsf-demo-stock-provider/target/*.jar /opt/nsf/

RUN mkdir -p /opt/apm
# APM config
ADD http://console-cust145.qingzhou.netease.com/download/napm/skywalking-napm-bin-8.8.0-latest.tar.gz /opt/apm/skywalking-napm-bin-8.8.0-latest.tar.gz
RUN cd /opt/apm  && \
tar zxvf skywalking-napm-bin-8.8.0-latest.tar.gz  && \
echo 'agent.authentication=MTQ2Mi16d3kta2Fvc2hp' > skywalking-napm-bin-8.8.0-latest/agent/config/agent.config && \
echo 'agent.service_name=zwy-provider' >> skywalking-napm-bin-8.8.0-latest/agent/config/agent.config && \
echo 'collector.backend_service=apm-otel-collector.service.163.org:11800' >> skywalking-napm-bin-8.8.0-latest/agent/config/agent.config && \
echo 'agent.sample_n_per_3_secs=10000' >> skywalking-napm-bin-8.8.0-latest/agent/config/agent.config

ADD nsf-demo-stock-provider/run.sh  /opt/
RUN chmod a+x /opt/run.sh
CMD ["/opt/run.sh"]
