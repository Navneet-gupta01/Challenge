# Perseus Challenge

* UIser Service
* NotificationService
* Template Service


## Simple SBT project .

## Uses PostgresQL
Postgres Setup 

Login to Postgres.  
create a database =>' CREATE DATABASE perseus'  
change the configuration in files.  
 * username, databsename etc
1.  user-service/src/main/scala/com/perseus/implicits.scala  
2. template-service/src/main/scala/com/perseus/implicits.scala  

# import tables
`psql -c '\i table.sql' -d perseus -U postgres`


# Resetting/ Creating tables
```
curl -X POST \
  http://localhost:8083/users \
  -H 'Postman-Token: d14dc9cb-921b-4302-ad1e-db055b191cb1' \
  -H 'cache-control: no-cache'
  
  
curl -X POST \
  http://localhost:8084/templates \
  -H 'Postman-Token: 03c35eac-f2e4-4b8a-852a-42043278baa2' \
  -H 'cache-control: no-cache'
```  



# Functional Programming

# Start User Service:   
``` 
cd perseus/user-service
sbt run
```


# Start Template Service:  
```
cd perseus/template-service
sbt run
```

# Start notification Service:  
```
cd perseus/notification-service
sbt run
```


# Send Welcome Message
```
curl -X GET \
  http://localhost:8085/welcome/users/1 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a03c8725-888a-4c96-89a8-35e4f35c318d' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```
Response
status: 200 OK
body: 
Integer => depicitng number of messages sent
0 in case Constraint of 10 not met.

# Send newsletter

```
curl -X GET \
  http://localhost:8085/newsletter \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: b2a199ff-16c5-4554-bdfb-9f72c7d3f585' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```

Response
status: 200 OK
body:
Integer => depicitng number of messages sent
0 in case Constraint of 10 not met.

# Get Users by user id:
```
curl -X GET \
  http://localhost:8083/users/1 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: e66f203e-7373-419f-9893-b36263a27011' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```
Response
status: 200 OK
body:
{
    "surname": "Turner",
    "firstname": "Tom",
    "gender": "male",
    "email": "tom.turner@provider.de",
    "subscribedtonewsletter": true,
    "id": 1
}
# Get Users list

```
curl -X GET \
  http://localhost:8083/users \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 6f867d21-893e-4ce2-9234-d507e8055007' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```
Response 
status: 200 OK
body:
[
    {
        "surname": "Turner",
        "firstname": "Tom",
        "gender": "male",
        "email": "tom.turner@provider.de",
        "subscribedtonewsletter": true,
        "id": 1
    },
    {
        "surname": "Doe",
        "firstname": "John",
        "gender": "male",
        "email": "jon.doe@test-mailing.com",
        "subscribedtonewsletter": true,
        "id": 2
    }
    ]
# Get Template by template id
```
curl -X GET \
  http://localhost:8084/templates/1 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 065efc6c-53af-490d-b7f1-9eedbbee7b99' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```
Response 
status: 200 OK
body:
{
    "template": "template",
    "key": "key",
    "id": 2
}

# Get  Temoplate by key
```
curl -X GET \
  http://localhost:8084/templates/welcome \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: f65d9022-d044-46b1-87a6-9bc4c6c07d7d' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```
status: 200 OK
body:
Response 

{
    "template": "template",
    "key": "key",
    "id": 2
}
