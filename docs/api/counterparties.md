### Counterparties


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
      "ContactView":{
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
               "$ref":"#/$defs/PersonView"
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
      },
      "PersonView":{
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
      }
   },
   "type":"object",
   "properties":{
      "active":{
         "type":"boolean"
      },
      "comment":{
         "type":"string"
      },
      "contacts":{
         "type":"array",
         "items":{
            "$ref":"#/$defs/ContactView"
         }
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
      "id":{
         "type":"integer"
      },
      "legalRekv":{
         "type":"object",
         "properties":{
            "fullName":{
               "type":"string"
            },
            "id":{
               "type":"integer"
            },
            "inn":{
               "type":"string"
            },
            "kpp":{
               "type":"string"
            },
            "ogrn":{
               "type":"string"
            },
            "ogrnAuthority":{
               "type":"string"
            },
            "ogrnDate":{
               "type":"string"
            },
            "okonh":{
               "type":"string"
            },
            "okpo":{
               "type":"string"
            },
            "shortName":{
               "type":"string"
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
      "legalType":{
         "$ref":"#/$defs/EnumerationView"
      },
      "name":{
         "type":"string"
      },
      "noVat":{
         "type":"boolean"
      },
      "owner":{
         "type":"object",
         "properties":{
            "active":{
               "type":"boolean"
            },
            "contacts":{
               "type":"array",
               "items":{
                  "$ref":"#/$defs/ContactView"
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
      },
      "parent":{
         "$ref":"#/$defs/BaseEntityListView"
      },
      "paymentDetails":{
         "type":"array",
         "items":{
            "type":"object",
            "properties":{
               "active":{
                  "type":"boolean"
               },
               "bank":{
                  "type":"string"
               },
               "bic":{
                  "type":"string"
               },
               "correspondingAccount":{
                  "type":"string"
               },
               "id":{
                  "type":"integer"
               },
               "paymentAccount":{
                  "type":"string"
               },
               "version":{
                  "type":"integer"
               }
            }
         }
      },
      "paymentTypes":{
         "type":"array",
         "items":{
            "$ref":"#/$defs/EnumerationView"
         }
      },
      "personRekv":{
         "$ref":"#/$defs/PersonView"
      },
      "statuses":{
         "type":"array",
         "items":{
            "$ref":"#/$defs/EnumerationView"
         }
      },
      "version":{
         "type":"integer"
      }
   }
}
```

### Get a single counterparty

**Request**

`GET /api/counterparties/:counterparty_id`

**Response**

```json
{
    "id": 1,
    "version": 0,
    "active": true,
    "name": "Контагент1",
    "legalType": {
        "id": 6,
        "name": "Организация/ИП",
        "ident": "LEGAL-TYPE",
        "properties": null
    },
    "legalRekv": {
        "id": 1,
        "shortName": "ИП Ивано",
        "fullName": "Индивидуальный предприниматель Иванов Сидр",
        "inn": "234234234",
        "kpp": null,
        "ogrn": "1167847438220",
        "ogrnDate": null,
        "ogrnAuthority": "Орган выдавший ОГРН",
        "okpo": "05561358",
        "okonh": null,
        "suggestion": null
    },
    "paymentDetails": [
        {
            "id": 1,
            "version": null,
            "active": true,
            "paymentAccount": "111",
            "correspondingAccount": "222",
            "bic": "000",
            "bank": "444"
        }
    ],
    "parent": null,
    "statuses": [
        {
            "id": 1,
            "name": "Клиент",
            "ident": "CP-STATUS",
            "properties": null
        }
    ],
    "paymentTypes": [
        {
            "id": 4,
            "name": "Безналичные",
            "ident": "PAYMENT-TYPE",
            "properties": null
        }
    ],
    "personRekv": null,
    "owner": {
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
    },
    "comment": null,
    "noVat": true,
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
    ],
    "extRegSystemProperties": {
        "extRegSystem": {
            "id": 1,
            "name": "Меркурий"
        },
        "properties": {
            "GUID": "7490fcad-9f04-4c63-ac53-9cd125de170d"
        },
        "id": 1
    }
}
```

### List of counterparties

List all counterparties. You can use the filter query parameter to fetch counterparties:

**Request**

`GET /api/counterparties`

**Parameters**

| Param  | Description  | Example |
|---|---|---|
| `q` | Search by part of name.  | ?q=manu|
| `active` |  Sign that entity is active. Available values are `true/false`. | ?active=true |
| `owner.id` |  Owner id. | ?owner.id=11 |
| `owner.name` |  Ignore case search by part of owner name. | ?owner.name=owne |
| `parent.id` |  Parent id. | ?parent.id=34 |
| `parent.name` |  Ignore case search by part of parent name. | ?parent.name=ivano |
| `legalRekv.inn` |  Ignore case search by part of inn. | ?legalRekv.inn=515676 |
| `legalRekv.kpp` |  Ignore case search by part of kpp. | ?legalRekv.kpp=6444 |
| `page` | Page number | ?page=1 |
| `size` |  Page rows limit | ?size=100 |
| `sort` |  Sorting. Available values for the direction are `asc/desc`. | ?sort=id,asc |

**Response**

```json
{
    "content": [],
    "totalPages": 1,
    "totalElements": 10,
    "numberOfElements": 10,
    "size": 50,
    "page": 0,
    "sort": "name: ASC"
}
```
### Add new Counterparty

**Request**

`POST /api/counterparties`

Required fields for patch request are:
* active
* name

>fields id and version must be null

```json
{
  "id": null,
  "version": null,
  "active": true,
  "name": "ИП Федоров",
  "parent": null,
  "legalType": null,
  "legalRekv": null,
  "personRekv": null,
  "comment": null,
  "noVat": null,
  "contacts": null,
  "paymentDetails": null,
  "statuses": null,
  "suggestion": null,
  "owner": null,
  "extRegSystemProperties": null
}
```

**Response**

The service returns the code of the created counterparty
```11```


### Edit an Counterparty

**Request**

`PATCH /api/counterparties/:counterparty_id`

Required fields for patch request are:
* id
* version - current entity state version
* updatedFields - array of changed field names

```json
{
  "id": 11,
  "version": 0,
  "active": true,
  "name": "ИП Федоров",
  "parent": null,
  "legalType": null,
  "legalRekv": null,
  "personRekv": null,
  "comment": null,
  "noVat": null,
  "contacts": null,
  "paymentDetails": null,
  "statuses": null,
  "suggestion": null,
  "owner": null,
  "extRegSystemProperties": null,
  "updatedFields": ["name"]
}
```