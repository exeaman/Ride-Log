# API Overview

RideLog exposes REST APIs through the API Gateway.

## Authentication APIs

### Register

```http
POST /auth/register
```

Creates a new user account.

### Login

```http
POST /auth/login
```

Authenticates a user and returns a JWT token.

---

## Bike APIs

### Create Bike

```http
POST /bikes
```

Creates a new motorcycle record.

### Get My Bikes

```http
GET /bikes/my
```

Returns all bikes owned by the authenticated user.

### Get Bike

```http
GET /bikes/{bikeId}
```

Returns details for a specific bike.

### Delete Bike

```http
DELETE /bikes/{bikeId}
```

Soft deletes a bike.

---

## Ride APIs

### Create Ride

```http
POST /rides
```

Creates a ride entry.

### Get Bike Rides

```http
GET /rides/bike/{bikeId}
```

Returns all rides for a motorcycle.

---

## Analytics APIs

### User Analytics

```http
GET /analytics/user/{userId}
```

Returns user-wide statistics.

### Bike Analytics

```http
GET /analytics/bike/{bikeId}
```

Returns statistics for a specific bike.

---

## Authentication

All protected endpoints require:

```http
Authorization: Bearer <jwt-token>
```

The JWT token is generated during login and must be included in subsequent requests.
