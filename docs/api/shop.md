### Shop


### Models

*schema*

```json
{
   "$schema":"https://json-schema.org/draft/2019-09/schema",
   "$defs":{
      "BaseEntityListView":{
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
      "ClassifierView":{
         "type":"object",
         "properties":{
            "active":{
               "type":"boolean"
            },
            "id":{
               "type":"integer"
            },
            "manufacturer":{
               "$ref":"#/$defs/BaseEntityListView"
            },
            "name":{
               "type":"string"
            },
            "version":{
               "type":"integer"
            }
         }
      },
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
      "address":{
         "type":"object",
         "properties":{
            "address":{
               "type":"string"
            },
            "comment":{
               "type":"string"
            },
            "id":{
               "type":"integer"
            },
            "latitude":{
               "type":"number"
            },
            "longitude":{
               "type":"number"
            },
            "suggestion":{
               "type":"object",
               "properties":{
                  "loadFactor":{
                     "type":"number"
                  },
                  "threshold":{
                     "type":"integer"
                  }
               }
            }
         }
      },
      "area":{
         "type":"number"
      },
      "comment":{
         "type":"string"
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
      "counterparty":{
         "$ref":"#/$defs/BaseEntityListView"
      },
      "extRegSystemProperties":{
         "type":"object",
         "properties":{
            "extRegSystem":{
               "$ref":"#/$defs/BaseEntityListView"
            },
            "id":{
               "type":"integer"
            },
            "properties":{
               "type":"object",
               "properties":{
                  "accessOrder":{
                     "type":"boolean"
                  },
                  "loadFactor":{
                     "type":"number"
                  },
                  "threshold":{
                     "type":"integer"
                  }
               }
            }
         }
      },
      "gln":{
         "type":"string"
      },
      "id":{
         "type":"integer"
      },
      "name":{
         "type":"string"
      },
      "salesChannels":{
         "type":"array",
         "items":{
            "$ref":"#/$defs/ClassifierView"
         }
      },
      "shopDeparts":{
         "type":"array",
         "items":{
            "$ref":"#/$defs/ClassifierView"
         }
      },
      "shopSpecializations":{
         "type":"array",
         "items":{
            "$ref":"#/$defs/ClassifierView"
         }
      },
      "shopTypes":{
         "type":"array",
         "items":{
            "$ref":"#/$defs/ClassifierView"
         }
      },
      "signboard":{
         "type":"string"
      },
      "version":{
         "type":"integer"
      }
   }
}
```

### Get a single shop

**Request**

`GET /api/shops/:shop_id`

**Response**

```json
{
    "id": 2,
    "version": 0,
    "active": true,
    "name": "Магазин2",
    "address": {
        "id": 2,
        "address": "address2",
        "comment": null,
        "suggestion": {
            "2": "s2",
            "s2": 2
        },
        "latitude": null,
        "longitude": null
    },
    "shopTypes": [
        {
            "id": 4,
            "version": 0,
            "active": false,
            "name": "Ларек",
            "manufacturer": {
                "id": 2,
                "name": "ООО Производитель2"
            }
        }
    ],
    "salesChannels": [
        {
            "id": 1,
            "version": 0,
            "active": true,
            "name": "Канал продаж1",
            "manufacturer": {
                "id": 1,
                "name": "ООО Производитель1"
            }
        },
        {
            "id": 4,
            "version": 0,
            "active": true,
            "name": "Канал продаж4",
            "manufacturer": {
                "id": 2,
                "name": "ООО Производитель2"
            }
        }
    ],
    "shopDeparts": [
        {
            "id": 3,
            "version": 0,
            "active": true,
            "name": "Отдел магазин 3",
            "manufacturer": {
                "id": 1,
                "name": "ООО Производитель1"
            }
        },
        {
            "id": 4,
            "version": 0,
            "active": true,
            "name": "Отдел магазин 4",
            "manufacturer": {
                "id": 1,
                "name": "ООО Производитель1"
            }
        }
    ],
    "shopSpecializations": [
        {
            "id": 1,
            "version": 0,
            "active": true,
            "name": "Специализация 1",
            "manufacturer": {
                "id": 1,
                "name": "ООО Производитель1"
            }
        },
        {
            "id": 2,
            "version": 0,
            "active": false,
            "name": "Специализация 2",
            "manufacturer": {
                "id": 2,
                "name": "ООО Производитель2"
            }
        }
    ],
    "counterparty": {
        "id": 6,
        "name": "Контагент6"
    },
    "gln": null,
    "area": null,
    "comment": null,
    "signboard": null,
    "contacts": [
        {
            "id": 2,
            "version": 0,
            "active": true,
            "role": {
                "id": 2,
                "version": 0,
                "active": true,
                "name": "Кладовщик"
            },
            "person": {
                "id": 2,
                "name": "Сидоров Петр Иванов",
                "lastName": "Сидоров",
                "firstName": "Петр",
                "patronymic": "Иванов",
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
    ],
    "extRegSystemProperties": {
        "extRegSystem": {
            "id": 1,
            "name": "Меркурий"
        },
        "properties": {
            "GUID": "0c93d73f-4110-4c2d-b75c-1af17d6c1f3d"
        },
        "id": 2
    }
}
```

### List of shops

List all shops. You can use the filter query parameter to fetch shops:

**Request**

`GET /api/shops/`

**Parameters**

| Param  | Description  | Example |
|---|---|---|
| `q` | Search by part of name.  | ?q=manu|
| `active` |  Sign that entity is active. Available values are `true/false`. | ?active=true |
| `name` |  Ignore case search by part of shop name. | ?name=sho |
| `counterparty.id` |  Counterparty id. | ?counterparty.id=11 |
| `counterparty.name` |  Ignore case search by part of counterparty name. | ?counterparty.name=counte |
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

### Add new Shop

**Request**

`POST /api/shops`

Required fields for patch request are:
* active
* name

>fields id and version must be null

```json
{
    "id": null,
    "version": null,
    "active": true,
    "name": "Магазин1",
    "address": null,
    "shopTypes": null,
    "salesChannels": null,
    "shopDeparts": null,
    "shopSpecializations": null,
    "counterparty": null,
    "gln": null,
    "area": null,
    "comment": null,
    "signboard": null,
    "contacts": null,
    "extRegSystemProperties": null
}
```

**Response**

The service returns the code of the created shop
```12```


### Edit an Shop

**Request**

`PATCH /api/shops/:shop_id`

Required fields for patch request are:
* id
* version - current entity state version
* updatedFields - array of changed field names
* and all required fields from create request

````json
{
    "id": 12,
    "version": 0,
    "active": true,
    "name": "Магазин2",
    "address": null,
    "shopTypes": null,
    "salesChannels": null,
    "shopDeparts": null,
    "shopSpecializations": null,
    "counterparty": null,
    "gln": null,
    "area": null,
    "comment": null,
    "signboard": null,
    "contacts": null,
    "extRegSystemProperties": null,
    "updatedFields": ["name"]
}
````