### Manufacturers


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
      "name":{
         "type":"string"
      },
      "version":{
         "type":"integer"
      }
   }
}
```

### Get a single manufacturer

**Request**

`GET /api/manufacturers/:manufacturer_id`

**Response**

```json
{
    "id": 1,
    "version": 0,
    "active": true,
    "name": "ООО Производитель1"
}
```

### List of manufacturers

List all manufacturers. You can use the filter query parameter to fetch manufacturers:

**Request**

`GET /api/manufacturers/`

**Parameters**

| Param  | Description  | Example |
|---|---|---|
| `q` | Search by part of name.  | ?q=manu|
| `active` |  Sign that entity is active. Available values are `true/false`. | ?active=true |
| `name` |  Ignore case search by part of manufacturer name. | ?name=manu |
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

### Add new Manufacturer

**Request**

`POST /api/manufacturers`

Required fields for create request are:
* active
* name

>fields id and version must be null

```json
{
    "id": null,
    "version": null,
    "active": true,
    "name": "ООО Производитель1"
}
```

**Response**

The service returns the code of the created manufacturer
```31```


### Edit an Manufacturer

**Request**

`PATCH /api/manufacturers/:manufacturer_id`

Required fields for patch request are:
* id
* version - current entity state version
* updatedFields - array of changed field names
* and all required fields from create request

````json
{
    "id": 31,
    "version": 0,
    "active": true,
    "name": "ООО Производитель1-1",
    "updatedFields": ["name"]
}
````