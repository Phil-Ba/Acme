[![Build Status](https://travis-ci.com/Phil-Ba/Acme.svg?branch=master)](https://travis-ci.com/Phil-Ba/Acme)
# Modules
The project consists of the following modules:
- acme-categories: MS responsible to managing categories and their fees.
- acme-product-inventory: MS responsible for managing the product inventory.
- acme-ui: The web front end.

# Data Model
The data model consists mainly of these 3 entities:
- Category: Contains information about a category, including it's name fee and the value range it is applicable for. 
- ProductType: General information about what products exists and which category they belong to.
- ProductItem: Concrete instance of a ProductType, with a declared value and a delivery date.
Besides this, modules have their own model for REST.

# Build
The build does following general tasks:
- Create an executable Spring Boot jar.
- Copy all needed files for the Docker build to the target/docker directory.
- Build the docker image according to the Dockerfile.

# Architecture
All modules build an individual MS. Each modules is packaged into an executable Spring Boot executable jar and then
into a docker image. 
- spring-data-rest: For exposing the entities via REST endpoints(not the best choice in hindsight).
- spring-data-jpa: For accessing the database.
- spring-boot-actuator: For providing application health metrics.
- Vaadin: For rendering the UI.
- spring-boot-openfeign: As uniform way to consume and access REST services independent of the underlying technology.
- Hibernate Validator: For validation bean constraints.
- testcontainers: To provide a database running in a docker container for environment independent integration tests.
- Kotlin: As language of choice for concise and null-safe code.

# Running
The project provides two docker-compose files. `docker-compose-postgres` provides a setup which only creates the necessary
databases for running local instances of the applications. In this case the applications should be started with the _local_
profile.  `docker-compose` will start all the applications in docker and expose the _acme-ui_ application on port 23231.
While the applications are independent of each other, due the startup initialization _acme-product-inventory_ depends on
_acme-categories_.
Intellij run configurations are also checked in. 