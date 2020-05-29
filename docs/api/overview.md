### Overview

***

This describes the resources that make up the official SPOCR REST API v1. 

**Main entities**

[Current version]()
[Schema]()
[Authentication]()
[Parameters]()
[Root endpoint]()
[Client errors]()
[HTTP verbs]()
[Pagination and Sorting]()
[Cross origin resource sharing]()
[Internalization]()
[Owners](owner.md)
[Counterparties](counterparty.md)
[Shops](shop.md)
[Contracts](contract.md)
[ExtRegSystems](ext-reg-system.md)
[Manufacturers](manufacturer.md)
[SalesChannels](sales-channel.md)
[ShopDeparts](shop-depart.md)
[ShopSpecializations](shop-specializations.md)
[ShopType](shop-type.md)


### Current version

By default, all requests to `/api` receive the v1 version of the REST API.

### Schema

All API access is over HTTPS, and accessed from `/api`. All data is sent and received as JSON.
Blank fields are included as `null` instead of being omitted.
All timestamps return in ISO 8601 format:

`YYYY-MM-DDTHH:MM:SSZ`

### Authentication

There is one way to authenticate through SPOCR API v1. Requests that require authentication will return 401 Unauthorized.

```sh
curl -X POST \
  http://localhost:8112/spocr/auth/signin \
  -H 'content-type: application/json' \
  -d '{
  "username":"user",
  "password":"111111"
}'
```

### Parameters

Many API methods take optional parameters. For GET requests the following parameters can be used to request a list of entities:

| Param  | Description  |
|---|---|
| `q` | search by part of a name |
| `page` | Page number |
| `size` |  Page rows limit |
| `sort` |  Sorting |

### Root endpoint

You can issue a GET request to the root endpoint to get all the endpoint categories that the REST API v1 supports:

```sh
/api
````

### Client errors

There are three possible types of client errors on API calls that receive request bodies:

1. Sending invalid JSON will result in a `400 Bad Request` response.
1. Sending the wrong type of JSON values will result in a `400 Bad Request` response.
1. Sending invalid fields will result in a `422 Unprocessable Entity` response.

Resources may also send custom validation errors (where code is custom). Custom errors will always have a message field describing the error

### HTTP verbs

Where possible, API v1 strives to use appropriate HTTP verbs for each action.

| Verb | Description  |
|---|---|
| `GET` | Used for retrieving resources. |
| `POST` |  Used for creating resources. |
| `PATCH` |  Used for updating resources. `updatedFields` is required field.`updatedFields` is array of changed field names. `version` is required field. `version` does not need to be changed on the client - it itself changes every time the entity changes. |
| `PUT` |  Used for replacing resources or collections. For `PUT` requests with no `body` attribute, be sure to set the `Content-Length` header to zero. |
| `DELETE` |  Not available at all |

### Pagination and Sorting

Requests that return multiple items will be paginated to 50 items by default and sorted by `name` ascending. You can specify further pages with the ?page parameter. For some resources, you can also set a custom page size up to 100 with the ?size parameter. You can specify sorting field name and direction with the ?sort parameter.

*model*

```json
{
  "content": [],
  "totalPages": 0,
  "totalElements": 0,
  "numberOfElements": 0,
  "size": 0,
  "page": 0,
  "sort": "string"
}
```

*example*
```json
{
  "totalPages": 1,
  "totalElements": 10,
  "numberOfElements": 10,
  "size": 50,
  "page": 0,
  "sort": "name: ASC"
}
```


```sh
/api/counterparties?page=2&size=100&sort=name,asc
```
Also, you can sort by a nested object property. For example, if you want sort shops by counterparty`s name:

```sh
/api/shops?sort=counterparty.name,asc
```

### Cross origin resource sharing

The API supports Cross Origin Resource Sharing (CORS) for AJAX requests from any origin. You can read the [CORS W3C Recommendation](http://www.w3.org/TR/cors/), or [this intro](http://code.google.com/p/html5security/wiki/CrossOriginRequestSecurity) from the HTML 5 Security Guide.

### Internalization

Service determine the current locale based on the Accept-Language header. Available headers are: ru, en.