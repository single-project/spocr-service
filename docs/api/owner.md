### Owners


### Models

*schema*

```json
{
   "$schema":"https://json-schema.org/draft/2019-09/schema",
   "$defs":{
      "EnumerationView":{
         "type":"object",
         "properties":{
            "id":{
               "type":"integer"
            },
            "ident":{
               "type":"string"
            },
            "name":{
               "type":"string"
            },
            "properties":{
               "type":"object"
            }
         }
      }
   },
   "type":"object",
   "properties":{
      "active":{
         "type":"boolean"
      },
      "contacts":{
         "type":"array",
         "items":{
            "type":"object",
            "properties":{
               "active":{
                  "type":"boolean"
               },
               "comment":{
                  "type":"string"
               },
               "id":{
                  "type":"integer"
               },
               "person":{
                  "type":"object",
                  "properties":{
                     "birthDate":{
                        "type":"string"
                     },
                     "birthPlace":{
                        "type":"string"
                     },
                     "citizenship":{
                        "$ref":"#/$defs/EnumerationView"
                     },
                     "docSeriesNumber":{
                        "type":"string"
                     },
                     "docType":{
                        "$ref":"#/$defs/EnumerationView"
                     },
                     "email":{
                        "type":"string"
                     },
                     "firstName":{
                        "type":"string"
                     },
                     "gender":{
                        "$ref":"#/$defs/EnumerationView"
                     },
                     "id":{
                        "type":"integer"
                     },
                     "inn":{
                        "type":"string"
                     },
                     "lastName":{
                        "type":"string"
                     },
                     "name":{
                        "type":"string"
                     },
                     "patronymic":{
                        "type":"string"
                     },
                     "phones":{
                        "type":"string"
                     }
                  }
               },
               "role":{
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
               },
               "version":{
                  "type":"integer"
               }
            }
         }
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

### Get a single owner

**Request**

`GET /api/owners/:owner_id`

**Response**

```json
{
    "id": 1,
    "version": 0,
    "active": true,
    "name": "Владелец",
    "contacts": [
        {
            "id": 1,
            "version": 0,
            "active": true,
            "role": {
                "id": 1,
                "version": 0,
                "active": true,
                "name": "Менеджер"
            },
            "person": {
                "id": 1,
                "name": "Петров Иван Сидорович",
                "lastName": "Петров",
                "firstName": "Иван",
                "patronymic": "Сидорович",
                "birthDate": null,
                "birthPlace": null,
                "docType": null,
                "docSeriesNumber": null,
                "inn": null,
                "citizenship": null,
                "gender": null,
                "email": null,
                "phones": null
            },
            "comment": null
        }
    ]
}
```

### List of owners

List all owners. You can use the filter query parameter to fetch owners:

**Request**

`GET /api/owners/`

**Parameters**

| Param  | Description  | Example |
|---|---|---|
| `q` | Search by part of name.  | ?q=manu|
| `active` |  Sign that entity is active. Available values are `true/false`. | ?active=true |
| `name` |  Ignore case search by part of owner name. | ?name=owne |
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

### Add new Owner

**Request**

`POST /api/owners`

Required fields for create request are:
* active
* name

>fields id and version must be null

```json
{
    "id": null,
    "version": null,
    "active": true,
    "name": "Владелец",
    "contacts": null
}
```

**Response**

The service returns the code of the created owner
```3```


### Edit an Owner

**Request**

`PATCH /api/owners/:owner_id`

Required fields for patch request are:
* id
* version - current entity state version
* updatedFields - array of changed field names
* and all required fields from create request

````json
{
    "id": 3,
    "version": 0,
    "active": true,
    "name": "Владелец Новый",
    "contacts": null,
    "updatedFields": ["name"]
}
````