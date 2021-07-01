#!/bin/bash

# npm install @openapitools/openapi-generator-cli -g
OPENAPI_GENERATOR_VERSION=3.0.0 openapi-generator-cli generate \
    -g spring \
    --library spring-boot \
    -i api-spec.yml \
    -o ${PWD} \
    -p groupId=com.sarfraz \
    -p artifactId=priority \
    -p artifactVersion=0.0.1 \
    -p basePackage=com.sarfraz.priority \
    -p configPackage=com.sarfraz.priority.config \
    -p apiPackage=com.sarfraz.priority.api \
    -p modelPackage=com.sarfraz.priority.model \
    -p sourceFolder=src/main/gen \
    -p dateLibrary=java8 \
    -p java8=true \
    --skip-validate-spec
