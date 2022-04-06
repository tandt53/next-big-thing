# Ascenda Loyalty interview

## General

Test cases are implemented by using some java libraries:

- [Rest-assured](https://rest-assured.io/): for send APIs
- [TestNG](https://testng.org): for organize tests
- [data-provider](./data-provider.md): for serialize/deserialize json data
- [Apache Commons Codec](https://commons.apache.org/proper/commons-codec/): for decoding jwt token

## Tasks

### Forming the request using RestAssured

I just created a method to form the request with `Input` object as the body, so that every test method can call it.

```java
private RequestSpecification auth(Input input){
        return RestAssured.given().baseUri("https://dev-5twd4ss9.auth0.com/oauth/token")
        .contentType("application/json")
        .accept("application/json")
        .body(input).log().all();
        }
```

### Invalid requests

All Test cases, related to invalid requests, are implemented in only 1 test method
in [Interview.java line 33](./src/test/java/tandt/Interview.java). Test data and test script are saperated to help
decoupling and easy to maintain.

The test method get all test data from provider method

```java
@DataProvider(name = "auth")
public Object[]data(){}
```

Test data is deserialized from json file to `AuthRequestInfo[]` object. Each item in `data` is an object that contains
description, input data and output data.

- `des`: is just decription of test case
- `input` contains all needed info for API.
- `output` contains list of expected results that requires 2 info:
    - jsonpath: to extract value from response json body.
    - expected: the expected value of extracted value.

```json
"data": [
{
"des": "invalid client id",
"input": {
"grant_type": "client_credentials",
"client_id": "invalid_client_id",
"client_secret": "wo-gIcYoQjn-dmGgRe_-pYIZTvgWsA_3tDUOqFLwJpIJdD-wHeDuiQrxrXQor3_X",
"audience": "https://test-data-api.com"
},
"output": [
{
"jsonpath": "error",
"expected": "access_denied"
},
{
"jsonpath": "error_description",
"expected": "Unauthorized"
}
]
}
]
}
```

### Valid requests to the /oauth/token endpoints

I just fix the test data inside test method in [Interview.java line 55](./src/test/java/tandt/Interview.java)
For validating values from response body, I use json path to get value and user `Assert` methods to validate data.

### Token content is valid and correct in the event of a valid request and Bonus

I know jwt token but I have ever view decoded values in jwt.io, but never in java code. Thus, I find solution on
internet and use java library for decode it.

```java
String jwtToken=response.jsonPath().getString("access_token");
        String[]split_string=jwtToken.split("\\.");
        String base64EncodedBody=split_string[1];
        Base64 base64Url=new Base64(true);
        String body=new String(base64Url.decode(base64EncodedBody));
```

Then I also extract values by using json path with method `JsonParser.fromJsonStringToObject()`
in [data-provider](../data-provider/src/main/java/tandt/dataprovider/json/JsonParser.java)

#### For bonus task

I use the same technique with `Invalid requests` but there is a different in `jsonpath` keys. jsonpath keys now refer to
json path of jwt token after decoded.

```json
{
  "des": "scope includes get:invoices get:clients ",
  "input": {
    "grant_type": "client_credentials",
    "client_id": "KRpatygJ3C5xvWxy4UuLkdm5qXMhCvc5",
    "client_secret": "wo-gIcYoQjn-dmGgRe_-pYIZTvgWsA_3tDUOqFLwJpIJdD-wHeDuiQrxrXQor3_X",
    "audience": "https://test-data-api.com"
  },
  "output": [
    {
      "jsonpath": "iss",
      "expected": "https://dev-5twd4ss9.auth0.com/"
    },
    {
      "jsonpath": "aud",
      "expected": "https://test-data-api.com"
    },
    {
      "jsonpath": "scope",
      "expected": "get:invoices get:clients"
    }
  ]
}
```