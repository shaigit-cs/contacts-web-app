#!/usr/bin/env bash

mvn_command="mvn clean package"

${mvn_command}

status="$?"

if [ ${status} -ne 0 ]; then
    echo "Script could not perform [$mvn_command] and returned exit code [$status]"
    exit ${status}
else
    docker-compose up -d --build --force-recreate
fi
