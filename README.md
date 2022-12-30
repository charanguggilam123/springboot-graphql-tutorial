# springboot-graphql-tutorial

This project is demonstration to implement graphql
It has both serve and client embedded in it. 
Client exposes rest endpoints and those can be consumed to preapre document and invoke server using HTTP GraphQL Client

Concepts Covered:

1. GraphQL Queries
2. GraphQL Mutations
3. GraphQL Error handling
4. GraphQL BatchMapping (resolving 1+n issue)
5. Junit testing using GraphQL
6. GraphQL Security(using spring method security)
7. GraphQL client to consume a graphql server

Future scope of enhancement:
As of now headers to consume can't be passed dynamically as inbuilt GraphQLClient builder
doesn't provide option to send headers at runtime. Those can be added at only bean creation
There are other solutions to achieved this by using third part clients like apollo, netflix GDS

References:
https://www.youtube.com/playlist?list=PLZV0a2jwt22slmUC9iwGGWfRQRIhs1ELa => For Queries, mutation, security, JUnit(Integration)
https://www.baeldung.com/spring-graphql-error-handling => Exception handling using DataFetcherResolverAdapter
https://medium.com/@philippechampion58/testing-your-graphql-apis-in-a-spring-boot-app-9fe02ebccc35 => junit
https://techdozo.dev/spring-for-graphql-how-to-solve-the-n1-problem/ => using @BatchMapping solving 1+n issue


