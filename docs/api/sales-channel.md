### Sales channel


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

### Get a single Sales channel

**Request**

`GET /api/sales-channels/:sales_channel_id`

**Response**

```json
{
    "id": 1,
    "version": 0,
    "active": true,
    "name": "Канал продаж",
    "manufacturer": {
        "id": 1,
        "name": "ООО Производитель1"
    }
}
```

### List of sales channels

List all sales channels. You can use the filter query parameter to fetch sales channels:

**Request**

`GET /api/sales-channels/`

**Parameters**

| Param  | Description  | Example |
|---|---|---|
| `q` | Search by part of name.  | ?q=manu|
| `active` |  Sign that entity is active. Available values are `true/false`. | ?active=true |
| `name` |  Ignore case search by part of sales channel name. | ?name=chan |
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

### Add new Sales channel

**Request**

`POST /api/sales-channels`

Required fields for create request are:
* active
* name

>fields id and version must be null

```json
{
    "id": null,
    "version": null,
    "active": true,
    "name": "Канал продаж новый",
    "manufacturer":{"id":2}
}
```

**Response**

The service returns the code of the created sales channel
```21```


### Edit an Sales channel

**Request**

`PATCH /api/sales-channels/:sales_channel_id`

Required fields for patch request are:
* id
* version - current entity state version
* updatedFields - array of changed field names
* and all required fields from create request

````json
{
    "id": 21,
    "version": 0,
    "active": true,
    "name": "Канал продаж измененный",
    "manufacturer":{"id":2},
    "updatedFields": ["name"]
}
````