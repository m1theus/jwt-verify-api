# jwt-verify-api [![CI](https://github.com/m1theus/jwt-verify-api/actions/workflows/ci.yml/badge.svg)](https://github.com/m1theus/jwt-verify-api/actions/workflows/ci.yml) ![Coverage](.github/badges/jacoco.svg) ![Branches](.github/badges/branches.svg)

## Table of Contents

1. [Introduction](#introduction)
2. [API](#api)
3. [Usage](#usage)

## Introduction

This web application is built in Java and Spring. This API checks if a jwt token with specific claims is valid.

## **API**

The API is available at `api.mmartins.dev` so you can use by the following cURL or [Insominia Collection](Insomnia_jwt-verify-api.json).

Valid Token cURL

```bash
curl --request POST \
  --url http://api.mmartins.dev/token/verify \
  --header 'Content-Type: application/json' \
  --data '{
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJOYW1lIjoiSm9zw6kgU2lsdmEiLCJSb2xlIjoiQWRtaW4iLCJTZWVkIjo0MDF9.Y1KUGv6zjrR9HwYBMj8Ho1hFwQIaJn0_etGg8MhczP0"
}'
```

Invalid token cURL

```bash
curl --request POST \
  --url http://api.mmartins.dev/token/verify \
  --header 'Content-Type: application/json' \
  --data '{
	"token": "eyJhbGciOiJIUzI1N3iIsInR5cCI6IkpXVCJ9.eyJOYW1lIjoiSm9zw6k4gU2lsdmEiLCJSb2xlIjoiQWRtaW4iLCJTZWVkIjo0MDF9.Y1KUGv6zjrR9HwYBMj8Ho1hFwQIaJn0_etGg8MhczP0"
}'
```

## **Usage**

To run the application, follow these steps:

1. Clone the repository:

   ```bash
   git clone git@github.com:m1theus/jwt-verify-api.git
   ```

2. Navigate to the project directory:

   ```bash
   cd jwt-verify-api
   ```

3. Run the application:

   ```bash
   ./gradlew bootRun
   ```

   Docker alternative:

   ```bash
   docker build -t jwt-verify-api .
   ```

   ```bash
   docker run -dp 127.0.0.1:8080:8080 jwt-verify-api
   ```
