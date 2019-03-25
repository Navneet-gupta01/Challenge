#Perseus Challenge

* UIser Service
* NotificationService
* Template Service


##Simple SBT project .

##Uses PostgresQL

#Functional Programming

#Start User Service:   
``` 
cd perseus/user-service
sbt run
```


#Start Template Service:  
```
cd perseus/template-service
sbt run
```

#Start notification Service:  
```
cd perseus/notification-service
sbt run
```


#Send Welcome Message
```
curl -X GET \
  http://localhost:8085/welcome/users/1 \
  -H 'Authorization: Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a03c8725-888a-4c96-89a8-35e4f35c318d' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```
#Send newsletter

```
curl -X GET \
  http://localhost:8085/newsletter \
  -H 'Authorization: Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: b2a199ff-16c5-4554-bdfb-9f72c7d3f585' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```

#Get USers by user id:
```
curl -X GET \
  http://localhost:8083/users/1 \
  -H 'Authorization: Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: e66f203e-7373-419f-9893-b36263a27011' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```

#Get Users list

```
curl -X GET \
  http://localhost:8083/users \
  -H 'Authorization: Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 6f867d21-893e-4ce2-9234-d507e8055007' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```

#Get Template by template id
```
curl -X GET \
  http://localhost:8084/templates/1 \
  -H 'Authorization: Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 065efc6c-53af-490d-b7f1-9eedbbee7b99' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```

#Get  Temoplate by key
```
curl -X GET \
  http://localhost:8084/templates/welcome \
  -H 'Authorization: Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: f65d9022-d044-46b1-87a6-9bc4c6c07d7d' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'cache-control: no-cache'
```

