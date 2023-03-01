# Demo 1 Commands
The following commands will demonstrate that the required user stories for Sprint 1 have been implemented properly.

### Creating a New Product
---
<details>
<summary>Windows (Powershell)</summary>

```curl
// Product 1
curl.exe -X POST `
         -H "Content-Type:application/json" `
         -d '{\"name\": \"GMMK 2\", \"price\": 119.99, \"quantity\": 300}" ` 
         http://localhost:8080/keyboards

// Product 2
curl.exe -X POST `
         -H "Content-Type:application/json" `
         -d '{\"name\": \"GMMK PRO\", \"price\": 349.99, \"quantity\": 150}' ` 
         http://localhost:8080/keyboards
```

</details>

<details>
<summary>Windows (Command Prompt)</summary>

```curl
// Product 1
curl.exe -X POST ^ 
         -H "Content-Type:application/json" ^
         -d "{\"name\": \"GMMK 2\", \"price\": 119.99, \"quantity\": 300}" ^ 
         http://localhost:8080/keyboards

// Product 2
curl.exe -X POST ^ 
         -H "Content-Type:application/json" ^ 
         -d "{\"name\": \"GMMK PRO\", \"price\": 349.99, \"quantity\": 150}" ^ 
         http://localhost:8080/keyboards
```

</details>

<details>
<summary>MacOS & Linux</summary>

```curl
// Product 1
curl -X POST \
     -H 'Content-Type:application/json' \
     -d '{ "name": "GMMK 2", "price": 119.99, "quantity": 300 }' \
     http://localhost:8080/keyboards

// Product 2
curl -X POST \
     -H 'Content-Type:application/json' \
     -d '{ "name": "GMMK PRO", "price": 349.99, "quantity": 150 }' \
     http://localhost:8080/keyboards
```

</details>

<br>

### Listing All Products
---
<details>
<summary>Windows (Powershell)</summary>

```curl
curl.exe -X GET `
     http://localhost:8080/keyboards
```

</details>

<details>
<summary>Windows (Command Prompt)</summary>

```curl
curl.exe -X GET ^ 
     http://localhost:8080/keyboards
```

</details>

<details>
<summary>MacOS & Linux</summary>

```curl
curl -X GET \ 
     http://localhost:8080/keyboards
```

</details>

<br>

### Retrieve a Specific Product
---
<details>
<summary>Windows (Powershell)</summary>

```curl
// Get Product 1
curl.exe -X GET `
         http://localhost:8080/keyboards/1

// Get Product 2
curl.exe -X GET `
         http://localhost:8080/keyboards/2
```

</details>

<details>
<summary>Windows (Command Prompt)</summary>

```curl
// Get Product 1
curl.exe -X GET ^
         http://localhost:8080/keyboards/1

// Get Product 2
curl.exe -X GET ^
         http://localhost:8080/keyboards/2
     
```

</details>

<details>
<summary>MacOS & Linux</summary>

```curl
// Get Product 1
curl -X GET \
     http://localhost:8080/keyboards/1

// Get Product 2
curl -X GET \
     http://localhost:8080/keyboards/2
```

</details>

<br>

### Search Products by Name
---
<details>
<summary>Windows (Powershell)</summary>

```curl
curl.exe -X GET `
     http://localhost:8080/keyboards/?=PRO
```

</details>

<details>
<summary>Windows (Command Prompt)</summary>

```curl
curl.exe -X GET ^
     http://localhost:8080/keyboards/?=PRO
```

</details>

<details>
<summary>MacOS & Linux</summary>

```curl
curl.exe -X GET \
     http://localhost:8080/keyboards/?=PRO
```

</details>

<br>

### Update a Product
---

<details>
<summary>Windows (Powershell)</summary>

```curl
// Update the price
curl.exe -X PUT `
         -H "Content-Type:application/json" `
         -d '{\"id\": 1, \"name\": \"GMMK 2\", \"price\": 99.99, \"quantity\": 300}' `
         http://localhost:8080/keyboards

// Update the quantity
curl.exe -X PUT `
         -H "Content-Type:application/json" `
         -d '{\"id\": 2, \"name\": "GMMK PRO\", \"price\": 349.99, \"quantity\": 400}' `
         http://localhost:8080/keyboards
```

</details>

<details>
<summary>Windows (Command Prompt)</summary>

```curl
// Update the price
curl.exe -X PUT ^
         -H "Content-Type:application/json" ^
         -d "{\"id\": 1, \"name\": \"GMMK 2\", \"price\": 99.99, \"quantity\": 300}" ^
         http://localhost:8080/keyboards

// Update the quantity
curl.exe -X PUT ^
         -H "Content-Type:application/json" ^
         -d "{\"id\": 2, \"name\": "GMMK PRO\", \"price\": 349.99, \"quantity\": 400}" ^
         http://localhost:8080/keyboards
```

</details>

<details>
<summary>MacOS & Linux</summary>

```curl
// Update the price
curl -X PUT \ 
     -H 'Content-Type:application/json' \
     'http://localhost:8080/keyboards' \
     -d '{ "id": 1, "name": "GMMK 2", "price": 99.99, "quantity": 300 }'

// Update the quantity
curl -X PUT \ 
     -H 'Content-Type:application/json' \
     'http://localhost:8080/keyboards' \
     -d '{ "id": 2, "name": "GMMK PRO", "price": 349.99 "quantity": 400 }'
```

</details>

<br>

### Delete a Product
----
<details>
<summary>Windows (Powershell)</summary>

```
curl.exe -X DELETE \
         http://localhost:8080/keyboards/1
```

</details>

<details>
<summary>Windows (Command Prompt)</summary>

```
curl.exe -X DELETE \
         http://localhost:8080/keyboards/1
```

</details>

<details>
<summary>MacOS & Linux</summary>

```
curl -X DELETE \
     'http://localhost:8080/keyboards/1'
```

</details>