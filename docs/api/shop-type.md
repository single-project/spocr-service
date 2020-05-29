### Shop type


### Models

*schema*

```json
{
   "$schema":"https://json-schema.org/draft/2019-09/schema",
   "type":"object",
   "properties":{
      "active":{
         "type":"boolean"
      },
      "id":{
         "type":"integer"
      },
      "manufacturer":{
         "type":"object",
         "properties":{
            "id":{
               "type":"integer"
            },
            "name":{
               "type":"string"
            }
         }
      },
      "name":{
         "type":"string"
      },
      "version":{
         "type":"integer"
      }
   }
}
```

### Get a single Shop type

**Request**

`GET /api/shop-types/:shop_type_id`

**Response**

```json
{
    "id": 1,
    "version": 0,
    "active": true,
    "name": "Супермаркет",
    "manufacturer": {
        "id": 1,
        "name": "ООО Производитель1"
    }
}
```

### List of shop types

List all shop types. You can use the filter query parameter to fetch shop types:

**Request**

`GET /api/shop-types/`

**Parameters**

| Param  | Description  | Example |
|---|---|---|
| `q` | Search by part of name.  | ?q=manu|
| `active` |  Sign that entity is active. Available values are `true/false`. | ?active=true |
| `name` |  Ignore case search by part of shop type name. | ?name=sho |
| `manufacturer.id` |  manufacturer id. | ?manufacturer.id=11 |
| `manufacturer.name` |  Ignore case search by part of manufacturer name. | ?manufacturer.name=manu |
| `page` | Page number | ?page=1 |
| `size` |  Page rows limit | ?size=100 |
| `sort` |  Sorting. Available values for the direction are `asc/desc`. | ?sort=id,asc |

**Response**

```json
{
    "content": [],
    "totalPages": 50,
    "totalElements": 100,
    "numberOfElements": 2,
    "size": 2,
    "page": 0,
    "sort": "name: ASC"
}
```

### Add new Shop type

**Request**

`POST /api/shop-types`

Required fields for create request are:
* active
* name

>fields id and version must be null

```json
{
    "id": null,
    "version": null,
    "active": true,
    "name": "Мегамаркет",
    "manufacturer":{"id":2}
}
```

**Response**

The service returns the code of the created shop type
```8```


### Edit an Shop type

**Request**

`PATCH /api/shop-types/:shop_type_id`

Required fields for patch request are:
* id
* version - current entity state version
* updatedFields - array of changed field names
* and all required fields from create request

````json
{
    "id": 8,
    "version": 0,
    "active": true,
    "name": "МегамаркетСупермаркет",
    "manufacturer":{"id":2},
    "updatedFields": ["name"]
}
````