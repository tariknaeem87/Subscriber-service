#!/usr/bin/env bash
port=9090
docker stop subscriber-service
docker rm subscriber-service
docker rmi subscriber-service
docker image build -t subscriber-service ~/Subscriber-service/.
docker run -d -p 9090:9999 --name subscriber-service subscrier-service
