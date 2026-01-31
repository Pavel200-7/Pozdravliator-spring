#!/bin/bash
echo "Setting up Pozdravliator..."

find ./services -name "gradlew" -exec chmod +x {} \;

docker-compose build
docker-compose up -d

echo "Done! Application is running at http://localhost:8080"