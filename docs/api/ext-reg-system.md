### Externtal registration systems


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

### Get a single ext reg system

**Request**

`GET /api/ext-reg-systems/:ext_reg_system_id`

**Response**

```json
{
    "id": 1,
    "version": 0,
    "active": true,
    "name": "Меркурий"
}
```

### List of ext reg systems

List all ext reg systems. You can use the filter query parameter to fetch ext reg systems:

**Request**

`GET /api/ext reg systems/`

**Parameters**

| Param  | Description  | Example |
|---|---|---|
| `q` | Search by part of name.  | ?q=manu|
| `active` |  Sign that entity is active. Available values are `true/false`. | ?active=true |
| `name` |  Ignore case search by part of ext reg system name. | ?name=merc |
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

### Add new Ext reg system

**Request**

`POST /api/ext-reg-systems`

Required fields for create request are:
* active
* name

>fields id and version must be null

```json
{
    "id": null,
    "version": null,
    "active": true,
    "name": "Меркурий"
}
```

**Response**

The service returns the code of the created ext reg system
```2```


### Edit an Ext reg system

**Request**

`PATCH /api/ext-reg-systems/:ext_reg_system_id`

Required fields for patch request are:
* id
* version - current entity state version
* updatedFields - array of changed field names
* and all required fields from create request

````json
{
    "id": null,
    "version": null,
    "active": true,
    "name": "Меркурий2",
    "updatedFields": ["name"]
}
````