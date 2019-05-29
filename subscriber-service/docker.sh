#!/usr/bin/env bash
port=9090
docker stop subscriber-service
docker rm subscriber-service
docker rmi subscriber-service
docker run -i -d -p 9090:9090 --expose 9090 --name subscriber-service -t demo/subscriber-service
