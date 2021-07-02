#!/bin/bash

function usage(){
  echo "$(basename $0) [-p | --package] [-r | --run] [-d | --dockerize] [ -c | --run_compose] [-h | --help]"
}

package=0
run=0
dockerize=0
run_compose=0
project_name="priority:0.0.1"
while [[ "$1" != "" ]]; do
  case $1 in
    -p | --package )
    package=1
    ;;
    -r | --run )
    run=1
    ;;
    -d | --dockerize )
    dockerize=1
    ;;
    -c | --run_compose )
    run_compose=1
    ;;
    -h | --help )
    usage;
    exit
    ;;
  esac
  shift
done


function exit_script(){
    echo "**** Error in file $0 while processing task $1 *****"
}

if [ $package == 1 ]; then
  echo "Packaging jar"
  mvn clean package || exit_script Packaging
  echo "Jar packaging done"
fi

if [ $dockerize == 1 ]; then
  echo "Building docker image "
  docker build -t $project_name . || exit_script Dockerize
  echo "Image built with name $project_name"
fi


if [ $run_compose == 1 ]; then
  echo "Starting docker compose "
  docker-compose up || exit_script Docker_compose_start
fi

if [ $run == 1 ]; then
  echo "Starting Springboot project"
  mvn spring-boot:run || exit_script Run
fi