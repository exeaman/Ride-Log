# Architecture Overview

RideLog follows a microservices architecture where each service has a clearly defined responsibility.

## High Level Architecture

```text
React Frontend
       │
       ▼
   API Gateway
    │      │
    ▼      ▼
RideService  AnalyticsService
      │
      ▼
   PostgreSQL
```

## Components

### React Frontend

Provides the user interface for:

* Authentication
* Bike management
* Ride management
* Analytics visualization

### API Gateway

Acts as the single entry point for frontend requests.

Responsibilities:

* Request routing
* CORS handling
* Service abstraction

### RideService

Core business service responsible for:

* Authentication
* Users
* Bikes
* Rides

### AnalyticsService

Responsible for aggregating ride data and generating statistics.

Examples:

* Total rides
* Total distance
* Average ride distance
* Longest ride

### PostgreSQL

Stores application data including:

* Users
* Bikes
* Rides
* Analytics records

## Communication

AnalyticsService communicates with RideService using OpenFeign clients.

The frontend never communicates directly with backend services and instead accesses everything through the API Gateway.
