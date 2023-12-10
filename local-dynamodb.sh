#!/usr/bin/env sh
set -e

# Name of the DynamoDB Docker container
DYNAMODB_CONTAINER_NAME="newangler-dynamodb"

# Check if the container already exists and remove it
STORAGE_CONTAINER=$(docker ps -a -f "name=${DYNAMODB_CONTAINER_NAME}" -q)
echo "Checking for existing DynamoDB container..."

if [ ! -z "$STORAGE_CONTAINER" ]; then
    echo "Removing existing container: ${DYNAMODB_CONTAINER_NAME}"
    docker rm -f ${STORAGE_CONTAINER}
fi

# Run a new DynamoDB Local container
echo "Running new DynamoDB Local container: ${DYNAMODB_CONTAINER_NAME}"
docker run --name ${DYNAMODB_CONTAINER_NAME} -p 8000:8000 -d amazon/dynamodb-local

# Optional: Wait for a few seconds to ensure DynamoDB Local is fully up and running
echo "Waiting for DynamoDB Local to start..."
sleep 10

echo "DynamoDB Local is now running on port 8000"
