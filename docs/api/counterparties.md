### Counterparties


### Models

*counterparty*

```json
 {
      "id": 0,
      "version": 0,
      "active": true,
      "legalRekv": {
        "fullName": "string",
        "inn": "string",
        "kpp": "string",
        "ogrn": "string",
        "ogrnAuthority": "string",
        "ogrnDate": "string",
        "okonh": "string",
        "okpo": "string",
        "shortName": "string"
      },
      "legalType": {
        "id": 0,
        "version": 0,
        "active": true,
        "name": "string",
        "opfCode": "string",
        "opfFull": "string",
        "opfShort": "string",
        "opfType": "string"
      },
      "name": "string",
      "parent": {
        "id": 0,
        "name": "string"
      },
      "paymentDetails": {
        "id": 0,
        "version": 0,
        "active": true,
        "bank": "string",
        "bic": "string",
        "correspondingAccount": "string",
        "paymentAccount": "string"
      },
      "statuses": [
        {
          "id": 0,
          "name": "string"
        }
      ],
      "suggestion": {
        "additionalProp1": {},
        "additionalProp2": {},
        "additionalProp3": {}
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
    "name": "Имя Контрагента",
    "legalType": {
        "id": 2,
        "version": 0,
        "active": true,
        "name": "ИП",
        "opfShort": "ИП",
        "opfFull": "Индивидуальный предприниматель",
        "opfCode": "50101",
        "opfType": "2014"
    },
    "legalRekv": {
        "shortName": null,
        "fullName": null,
        "inn": null,
        "kpp": null,
        "ogrn": null,
        "ogrnDate": null,
        "ogrnAuthority": null,
        "okpo": null,
        "okonh": null
    },
    "paymentDetails": {
        "id": 1,
        "version": 0,
        "active": false,
        "paymentAccount": "111",
        "correspondingAccount": "222",
        "bic": "000",
        "bank": "444"
    },
    "suggestion": null,
    "parent": null,
    "statuses": [
        {
            "id": 1,
            "name": "Клиент"
        }
    ]
}
```

### List of counterparties

List all counterparties. You can use the filter query parameter to fetch counterparties:

`GET /api/counterparties`

**Parameters**

| Param  | Description  | Example |
|---|---|---|
| `q` | Search by part of name.  | ?q=manu|
| `active ` |  Sign that entity is active. Available values are `true/false`. | ?active=true |
| `page` | Page number | ?page=1 |
| `size` |  Page rows limit | ?size=100 |
| `sort` |  Sorting. Available values for the direction are `asc/desc`. | ?sort=id,asc |

**Response**

```json
{
    "content": [
        {
            "id": 1,
            "name": "Контагент1",
            "version": 0,
            "active": true
        },
        {
            "id": 10,
            "name": "Контагент10",
            "version": 0,
            "active": true
        },
        {
            "id": 2,
            "name": "Контагент2",
            "version": 0,
            "active": true
        },
        {
            "id": 3,
            "name": "Контагент3",
            "version": 0,
            "active": true
        },
        {
            "id": 4,
            "name": "Контагент4",
            "version": 0,
            "active": true
        }
    ],
    "totalPages": 2,
    "totalElements": 5,
    "numberOfElements": 5,
    "size": 5,
    "page": 0,
    "sort": "name: ASC"
}
```
### Add new Counterparty

`POST /api/counterparties`

```json
{
  "active": true,
  "name": "Новое Имя"
}
```
### Edit an Counterparty

`PATCH /api/counterparties/:counterparty_id`

**Request**

```json
{
  "name": "Новое Имя",
  "active": false,
  "version": 1
}
```