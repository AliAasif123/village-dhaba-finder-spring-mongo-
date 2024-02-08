
 # API Documentation
 # Introduction : 
This document provides details about the RESTful API endpoints for managing Dhaba (restaurant) data.
# Base URL :
# http://localhost:4200/api/dhabacontroller

# Endpoints
# a) Save Dhaba
#URL: /save
Method: POST
Description: Saves a new Dhaba entry.
Request Body:
{
    "_id": 895272847,
    "name": "Chocolate Dreams",
    "category": "Dessert Shop",
    "address": "555 Cocoa Lane",
    "items": [
        "Chocolate Cake",
        "Truffles",
        "Brownies"
    ],
    "location": {
        "x": 77.5946,
        "y": 12.9716,
        "type": "Point",
        "coordinates": [
            77.5946,
            12.9716
        ]
    }
}
# b) Get Nearby Dhabas
URL: /get
Method: POST
Description: Retrieves nearby Dhabas based on provided coordinates and distance.
Request Body:
{
    "Latitude": 17.440103,
    "Longitude": 78.364566,
    "Distance": 1
}
# c) Get All Dhabas
URL: /getAll
Method: GET
Description: Retrieves all Dhabas.

# d) Get Dhabas by Category
URL: /getBycategory/{category}
Method: GET
Description: Retrieves Dhabas by a specific category.
Example: /getBycategory/Indian Cuisine

# e) Get Dhabas within Specific Coordinates
URL: /gettingSpecific
Method: POST
Description: Retrieves Dhabas within a specific range of coordinates.
Request Body:
 {
    "minLongitude": 77.5946,
    "minLatitude": 12.9716,
    "maxLongitude": 77.6046,
    "maxLatitude": 12.9816
}
# f)  Retrieves a list of food establishments based on a specified category within a given radius from the provided latitude and longitude coordinates.
# link : 
 GET /api/dhabacontroller/gettingCat/Indian%20Cuisine/17.433983/78.382992/2 HTTP/1.1
# Host: 
  localhost:4200

# g)
# link 
   GET /api/dhabacontroller/gettingListOfItems?items=Chocolate%20Cake&items=Truffles HTTP/1.1
 # Host: localhost:4200
# h)
GET /api/dhabacontroller/regex/o/17.440103/78.364566/7 HTTP/1.1
# Host: 
   localhost:4200

# Response
All endpoints return JSON objects representing Dhabas or relevant data.
# Error Handling
Proper error messages and status codes (e.g., 404 for not found, 400 for bad request) are returned for invalid requests or server errors.

