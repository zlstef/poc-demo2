FROM harbor.cloud.netease.com/library/openjdk:8-jdk-buster

WORKDIR /root

RUN    echo "deb https://mirrors.aliyun.com/debian/ buster main non-free contrib" > /etc/apt/sources.list \
    && echo "deb https://mirrors.aliyun.com/debian-security buster/updates main" >> /etc/apt/sources.list \
    && echo "deb https://mirrors.aliyun.com/debian/ buster-updates main non-free contrib"  >> /etc/apt/sources.list \
    && echo "deb https://mirrors.aliyun.com/debian/ buster-backports main non-free contrib"  >> /etc/apt/sources.list

RUN set -eux; \
    apt-get update; \
    apt-get install -y --no-install-recommends \
    logrotate \ 
    cron \
    telnet \
    vim \
    less \
    tcpdump \
    net-tools \
    dnsutils \
    sysstat \
    strace \
    lsof \
    iproute2 \
    psmisc \
    wget \
    unzip \
    apache2-utils \
    ; \
    rm -rf /var/lib/apt/lists/*

RUN wget https://archive.apache.org/dist/rocketmq/4.9.2/rocketmq-all-4.9.2-bin-release.zip \
    && unzip  rocketmq-all-4.9.2-bin-release.zip \
    && rm -fr rocketmq-all-4.9.2-bin-release.zip

RUN echo "set encoding=utf8" > ~/.vimrc \
    && echo "set mouse-=a" > ~/.vimrc \
    && echo "ts=4" > ~/.vimrc


ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US.UTF-8

# RUN     apt-get update  && \
RUN     apt-get -o Acquire::Check-Valid-Until=false update && \
        apt-get install -y locales locales-all && \
        apt-get install mysql-client -y && \
        apt-get install postgresql-client -y && \
        apt install netcat -y && \
        apt-get install procps -y && \
        apt-get install python-pymongo python-mysqldb -y && \
        apt-get install mongodb-clients -y && \
        apt-get install redis-tools -y && \
        apt-get install python3 python3-pip -y && \
        pip3 install -i https://pypi.tuna.tsinghua.edu.cn/simple pip -U && \
        pip3 config set global.index-url https://pypi.tuna.tsinghua.edu.cn/simple && \
        pip3 install pipenv && \
        pip3 install requests && \
        cp -f /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
        echo "set encoding=utf-8" >> /etc/vim/vimrc && \
        apt-get clean && \
        mkdir -p /tmp/skiff-init/  && \
        rm -rf /var/lib/apt/lists/* && \
        cd /tmp/skiff-init/ && pipenv install && \
        wget https://skiff-pub.nos-eastchina1.126.net/kubectl -O /usr/bin/kubectl && \
        chmod +x /usr/bin/kubectl && \
        curl -L -o /usr/bin/yq https://github.com/mikefarah/yq/releases/download/3.4.1/yq_linux_amd64 && \
        chmod +x /usr/bin/yq && \
        curl -L -o /usr/bin/jq https://github.com/stedolan/jq/releases/download/jq-1.6/jq-linux64 && \
        chmod +x /usr/bin/jq



CMD ["tail", "-f", "/dev/null"]
