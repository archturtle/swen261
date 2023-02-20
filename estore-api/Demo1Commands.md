# Demo 1 Commands
The following commands will demonstrate that the required user stories for Sprint 1 have been implemented properly.

### Creating a New Product
---
```curl
// Product 1
curl -X POST \
     -H 'Content-Type:application/json' \
     'http://localhost:8080/keyboards' \
     -d '{ "name": "GMMK 2", "price": 119.99, "quantity": 300 }'

// Product 2
curl -X POST \
     -H 'Content-Type:application/json' \
     'http://localhost:8080/keyboards' \
     -d '{ "name": "GMMK PRO", "price": 349.99, "quantity": 150 }'
```

### Listing All Products
---
```curl
curl -X GET \ 
     'http://localhost:8080/keyboards'
```

### Retrieve a Specific Product
---
```curl
// Get Product 1
curl -X GET \
     'http://localhost:8080/keyboards/1'

// Get Product 2
curl -X GET \
     'http://localhost:8080/keyboards/2'
```

### Search Products by Name
---
```curl
curl -X GET \ 
     'http://localhost:8080/keyboards/?=PRO'
```

### Update a Product
---
```
// Update the price
curl -X PUT \ 
     -H 'Content-Type:application/json' \
     'http://localhost:8080/keyboards' \
     -d '{ "id": 1, "price": 99.99 }'

// Update the quantity
curl -X PUT \ 
     -H 'Content-Type:application/json' \
     'http://localhost:8080/keyboards' \
     -d '{ "id": 2, "quantity": 400 }'
```

### Delete a Product
----
```
curl -X DELETE \
     'http://localhost:8080/keyboards/1'
```