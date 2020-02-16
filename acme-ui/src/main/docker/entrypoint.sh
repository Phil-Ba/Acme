#!/usr/bin/env bash
./wait-for-it.sh categories:8080
./wait-for-it.sh product-inventory:8080
exec /app/app.jar