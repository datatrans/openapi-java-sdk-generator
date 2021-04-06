An example how to generate & use the Datatrans Java SDK from the [openapi](https://api-reference.datatrans.ch/) specification.

### Generating the Java SDK

```sh
# Generate the source files
$ mvn clean compile
```

### Deploy to your own artifacts sever
Currently, the Datatrans Java SDK is not yet available on Maven Central (or something similar). 
Thats why you manually need to deploy it to your own artifacts hosting solution.

```
# You might have to adjust your ~/.m2/settings.xml or add a <distributionManagement> section to the pom.xml
$ mvn deploy
```

## Using the Java SDK
Once deployed, add the following dependency:

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>ch.datatrans</groupId>
  <artifactId>datatrans-java-sdk</artifactId>
  <version>2.0.15</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "ch.datatrans:datatrans-java-sdk:2.0.15"
```

### Invoking the SDK
```java
ApiClient defaultClient = Configuration.getDefaultApiClient();
defaultClient.setBasePath("https://api.sandbox.datatrans.com");

// Configure HTTP basic authorization: Basic
HttpBasicAuth Basic = (HttpBasicAuth) defaultClient.getAuthentication("Basic");
Basic.setUsername("1100017675"); // your Datatrans merchantId
Basic.setPassword("password"); // your Datatrans server to server password

V1TransactionsApi transactionsApiInstance = new V1TransactionsApi(defaultClient);

AuthorizeRequest authorizeRequest = new AuthorizeRequest();
authorizeRequest.currency("CHF")
        .amount(100L)
        .refno(String.valueOf(System.currentTimeMillis()));

CardAuthorizeRequest cardAuthorizeRequest = new CardAuthorizeRequest();

cardAuthorizeRequest.alias("AAABcH0Bq92s3kgAESIAAbGj5NIsAHWC")
        .expiryMonth("12")
        .expiryYear("21");

authorizeRequest.setCard(cardAuthorizeRequest);

try {
    AuthorizeResponse result = transactionsApiInstance.authorize(authorizeRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling V1AliasesApi#aliasesConvert");
    System.err.println("Status code: " + e.getCode());
    System.err.println("Reason: " + e.getResponseBody());
    System.err.println("Response headers: " + e.getResponseHeaders());
    e.printStackTrace();
}
```

