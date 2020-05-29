### Contracts


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
      "autoprolongation":{
         "type":"boolean"
      },
      "comment":{
         "type":"string"
      },
      "commodityCredit":{
         "type":"string"
      },
      "contractNumber":{
         "type":"string"
      },
      "counterparty1":{
         "$ref":"#/$defs/BaseEntityListView"
      },
      "counterparty2":{
         "$ref":"#/$defs/BaseEntityListView"
      },
      "endDate":{
         "type":"string"
      },
      "id":{
         "type":"integer"
      },
      "link":{
         "type":"string"
      },
      "name":{
         "type":"string"
      },
      "startDate":{
         "type":"string"
      },
      "status":{
         "$ref":"#/$defs/EnumerationView"
      },
      "subContracts":{
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
               "contract":{
                  "$ref":"#"
               },
               "id":{
                  "type":"integer"
               },
               "link":{
                  "type":"string"
               },
               "name":{
                  "type":"string"
               },
               "status":{
                  "$ref":"#/$defs/EnumerationView"
               },
               "subContractDate":{
                  "type":"string"
               },
               "subContractNumber":{
                  "type":"string"
               },
               "version":{
                  "type":"integer"
               }
            }
         }
      },
      "type":{
         "$ref":"#/$defs/EnumerationView"
      },
      "version":{
         "type":"integer"
      }
   }
}
```

### Get a single contract

**Request**

`GET /api/contracts/:contract_id`

**Response**

```json
{
    "id": 1,
    "version": 0,
    "active": true,
    "name": "Продажа товаров",
    "endDate": "2020-03-26T13:03:05+0000",
    "startDate": "2020-03-25T13:03:05+0000",
    "comment": null,
    "contractNumber": "123AA",
    "counterparty1": {
        "id": 1,
        "name": "Контагент1"
    },
    "counterparty2": {
        "id": 2,
        "name": "Контагент2"
    },
    "subContracts": []
}
```

### List of contracts

List all contracts. You can use the filter query parameter to fetch contracts:

**Request**

`GET /api/contracts/`

**Parameters**

| Param  | Description  | Example |
|---|---|---|
| `q` | Search by part of name.  | ?q=manu|
| `active` |  Sign that entity is active. Available values are `true/false`. | ?active=true |
| `name` |  Ignore case search by part of contract name. | ?name=sales |
| `contractNumber` |  Ignore case search by part of contract number. | ?name=xi112 |
| `counterparty1.id` |  Counterparty1 id. | ?counterparty1.id=11 |
| `counterparty1.name` |  Ignore case search by part of counterparty1 name. | ?counterparty1.name=counte |
| `counterparty2.id` |  Counterparty2 id. | ?counterparty2.id=11 |
| `counterparty2.name` |  Ignore case search by part of counterparty2 name. | ?counterparty2.name=counte |
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

### Add new Contract

**Request**

`POST /api/contracts`

Required fields for create request are:
* active
* name
* startDate
* endDate
* contractNumber
* counterparty1
* counterparty2

>fields id and version must be null

```json
{
    "id": null,
    "version": null,
    "active": true,
    "name": "Продажа пилюль",
    "endDate": "2020-03-26T13:03:05+0300",
    "startDate": "2020-03-25T13:03:05+0300",
    "comment": null,
    "contractNumber": "123AA",
    "counterparty1": {
        "id": 1,
        "name": "Контагент1"
    },
    "counterparty2": {
        "id": 2,
        "name": "Контагент2"
    },
    "subContracts": []
}
```

**Response**

The service returns the code of the created contract
```11```


### Edit an Contract

**Request**

`PATCH /api/contracts/:contract_id`

Required fields for patch request are:
* id
* version - current entity state version
* updatedFields - array of changed field names
* and all required fields from create request

````json
{
    "id": 11,
    "version": 0,
    "active": true,
    "name": "Продажа пилюль",
    "endDate": "2020-03-26T13:03:05+0300",
    "startDate": "2020-03-25T13:03:05+0300",
    "comment": null,
    "contractNumber": "123AA",
    "counterparty1": {
        "id": 3,
        "name": "Контагент3"
    },
    "counterparty2": {
        "id": 2,
        "name": "Контагент2"
    },
    "subContracts": [],
    "updatedFields": ["counterparty1"]
}
````