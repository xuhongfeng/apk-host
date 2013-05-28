#!/bin/bash

protoc -I=src/main/java --java_out=src/main/java src/main/java/hongfeng/xu/apk/data/Protobuf.proto
