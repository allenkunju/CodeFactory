# CodeFactory

# URL Shortener

This application provides proper REST API that will enable other users/ clients to send a
URL and will receive some kind of identifier/ hash to which this URL corresponds
in our system. And the other way around of course, meaning that a user would be
able to resolve the full URL.

# REST APIs

Shorten URL API
--
To shorter any url, pass the url in the body of the POST API `/v1/api/short-url/` as follows:
```
curl --location --request POST 'http://localhost:8080/v1/api/short-url' \
--header 'Content-Type: application/json' \
--data-raw 'http://test.com/this-is-a-test-url'
```

Resolve URL API
--
To resolve the original url from the shortened identifier, invoke the GET API `/v1/api/short-url/{id}` where `{id}` is the shortened url
```
curl --location --request GET 'http://localhost:8080/v1/api/short-url/34fe96a912a04c8d8a8d57c65f99f3da'
```


# Constraints
- The short url generated are based random generated strings. So same input might give different unique response on different requests.
- To make it consistent for same input even on multiple attempts, we need to implement hashing logic to generate unique response based on the input url.
- An in-memory cache is used for MVP. The implementation can be upgraded with external cache like Redis, or with databases like PostgreSQL for persisting the result.
