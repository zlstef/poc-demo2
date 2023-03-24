#!/bin/bash -x

TAG_NAME=$1

if [ -z $TAG_NAME ];then
     echo "tag 不能为空"
     exit 1
fi

# 判断该tag是否已经存储，放置使用重复的tag构建镜像
grep ${TAG_NAME} release &> /dev/null
if [ $? -eq 0 ];then
    echo "已经存在该版本，请重新指定版本"
    echo "release 文件后10行"
    echo "========================="
    tail release
    echo "========================="
    exit 1
fi

#docker build --no-cache --network host -t harbor.cloud.netease.com/library/solution-tools:${TAG_NAME} .
docker build --network host -t harbor.cloud.netease.com/library/solution-tools:${TAG_NAME} .

# 记录历史版本，放置版本tag重复
echo "solution-tools:${TAG_NAME}" >> release

docker push harbor.cloud.netease.com/library/solution-tools:${TAG_NAME}
