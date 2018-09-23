# team20_sushi
Product Database API - [[internal source]](#plastic-karma-product-api), [[external source]](https://github.com/tokyojack/Plastic-Karma-Product-Rest-API)
<br/>
User Database API - [[internal source]](#plastic-karma-user-database-api), [[external source]](https://github.com/tokyojack/Plastic-Karma-User-Rest-API)

---

# Plastic Karma Product API

## Overview

Plastic Karma's REST API is a scaleable, quick, and efficent program. Using MongoDB and their Atlas service for scalable servers,
Heroku for scalable Node.js servers, and Express for quick responses and maximum efficentcy. The API allows all of our platforms (IOS, Andriod,
Websites), to communicate on one centeral database servers. This allows us to just change the one server instead of all of our platforms,
allowing increased reliability and ease of use.

## Tutorial

Some sample with [Postman](https://www.getpostman.com/) can be found [Postman](https://www.getpostman.com/collections/0bd736c7995968861cfc).

## Deploying

1. Clone (```git clone https://github.com/tokyojack/Plastic-Karma-Product-Rest-API```
2. ``CD`` into the folder
3. Run ```npm install``` for the packages
4. Add your MongoDB atlas URL into the ```config.js``` file
5. Run ```node index.js``` to start the server
6. Start testing it out at ```localhost:8080```

## Queries - Products

### POST - /product/add - Creates a product

* `UPC` - String
* `plastic_score` - Integer
* `action_desc` - String
* `name` - String
* `recyling_score` - Integer
* `waste_score` - Integer

*Example successful response*
``` JSON
{
    "message": "Product created successfully"
}
```
<h2></h2>


### GET - /find/<UPC> - Returns info about a product

*Example successful response*
``` JSON
{
    "_id": "5ba7dd97c7763b001544a16b",
    "upc": "732619602003",
    "plastic_score": 3,
    "action_desc": "refundable",
    "name": "laundry detergent bottle",
    "recycling_score": 2,
    "waste_score": 1,
    "transactions": []
}
```
<h2></h2>


### GET - /products - Returns all products

*Example successful response*
``` JSON
[
    {
        "_id": "5ba7db6ec7763b001544a157",
        "upc": "379941575228",
        "plastic_score": 18,
        "action_desc": "recyclable",
        "name": "pizza tray",
        "recycling_score": 17,
        "waste_score": 0,
        "transactions": []
    },
    {
        "_id": "5ba7dc58c7763b001544a15f",
        "upc": "616087993400",
        "plastic_score": 18,
        "action_desc": "recyclable",
        "name": "phone case package",
        "recycling_score": 16,
        "waste_score": 0,
        "transactions": [
            {
                "amount": 2,
                "user_email": "tokyojack2001@gmail.com",
                "date": "2018-09-23T19:27:55.081Z"
            }
        ]
    }
]
```
<h2></h2>

## Queries - Transactions

### POST - /product/add - Creates a product

* `UPC` - String
* `email` - String

*Example successful response*
``` JSON
{
    "message": "Transaction added successfully",
    "score": 1
}
```
<h2></h2>


### GET - /product/find/<UPC> - Finds the transactions for a product

*Example successful response*
``` JSON
[
    {
        "amount": 34,
        "user_email": "tokyojack2001@gmail.com",
        "date": "2018-09-23T19:27:55.081Z"
    },
    {
        "amount": 229,
        "user_email": "joebiden@gmail.com",
        "date": "2018-03-23T13:51:55.081Z"
    }
]
```
<h2></h2>

### Support

For support, simply just email us at support@plastickarma.com

---

# Plastic Karma User Database API

## Overview

Plastic Karma's REST API is a scaleable, quick, and efficent program. Using MongoDB and their Atlas service for scalable servers,
Heroku for scalable Node.js servers, and Express for quick responses and maximum efficentcy. The API allows all of our platforms (IOS, Andriod,
Websites), to communicate on one centeral database servers. This allows us to just change the one server instead of all of our platforms,
allowing increased reliability and ease of use.

## Tutorial

Some sample with [Postman](https://www.getpostman.com/) can be found [Postman](https://www.getpostman.com/collections/0bd736c7995968861cfc).

## Deploying

1. Clone (```git clone https://github.com/tokyojack/Plastic-Karma-User-Rest-API```
2. ``CD`` into the folder
3. Run ```npm install``` for the packages
4. Add your MongoDB atlas URL into the ```config.js``` file
5. Run ```node index.js``` to start the server
6. Start testing it out at ```localhost:8080```

## Queries

### POST - /add - Creates a product

* `email` - String
* `password` - Integer
* `current_score` - String
* `waste_score` - String
* `prior_month_score` - Integer

*Example successful response*
``` JSON
{
    "message": "User created successfully"
}
```
<h2></h2>


### GET - /find/<UPC> - Returns info about a product

*Example successful response*
``` JSON
{
    "_id": "5ba7b8102071244568b9b126",
    "email": "tokyojack2001@gmail.com",
    "password": "asd123",
    "current_score": 0,
    "waste_score": 0,
    "prior_month_score": 0
}
```
<h2></h2>


### POST - /increasescore - Increases a users score

* `email` - String
* `amount` - Integer

*Example successful response*
``` JSON
{
    "message": "Score added successfully",
    "score": 30
}
```
<h2></h2>


### Support

For support, simply just email us at support@plastickarma.com
