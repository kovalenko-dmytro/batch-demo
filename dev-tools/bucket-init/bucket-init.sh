#!/bin/bash

echo "Creating local AWS S3 bucket '$AWS_S3_BUCKET_NAME'"

awslocal s3api create-bucket --bucket $AWS_S3_BUCKET_NAME

echo "S3 bucket '$AWS_S3_BUCKET_NAME' created successfully"