# openapi-jaxrs-client

An OpenAPI JAX-RS client generator.

Generates API classes (only) for use with the MicroProfile client.

DTOs can be generated for use with jsonb, jackson2 (fasterxml), or jackson (codehaus).
So these can be used in other (older) contexts than the MP client.

## Inspiration

Much of the behavior is lifted more or less directly from https://github.com/OpenAPITools/openapi-generator.

But the code is implemented from scratch, based on end-to-end tests.

I plan to keep this generator much simpler. It will only generate Java code, primarily intended for the MP client.
It will never have as many features as openapi-generator.

However, the intention is that the generated code covers the real-world needs I see at work.
And that the generated code can "round trip" via OpenApi to second-hand clients.


## Configuration

**generator-use-zoneddatetime**

>date-time types are deserialized to OffsetDateTime by default, which matches the information
provided by the [spec](https://xml2rfc.tools.ietf.org/public/rfc/html/rfc3339.html).
>	
>But your remote connection may provide additional zone info which allows deserialization to
ZonedDateTime (depending on the deserializer implementation).
>	
>For example, `io.quarkus:quarkus-resteasy-jsonb` will serialize ZonedDateTime to:
>	
	`"2022-01-02T12:28:36.639812723+01:00[Europe/Copenhagen]"`
>	
>By enabling this option, the generated DTOs will have ZonedDateTime for properties
instead of OffsetDateTime.

>
	default value: false

**parser-localtime-is-time**

>While there is no local-time definition in the [OpenApi spec](https://swagger.io/specification/#data-types),
>the Quarkus open-api extension will output LocalTime types like this:

>
    LocalTime:
      format: local-time
      type: string
      externalDocs:
        description: As defined by 'partial-time' in RFC3339
        url: https://xml2rfc.ietf.org/public/rfc/html/rfc3339.html#anchor14
      example: 13:45.30.123456789

>This option allows parsing of the type `LocalDate` to be treated by the generator as `java.time.LocalDate`.

>It can be disabled if your remote connection uses the type name `LocalDate` for something bespoke.
	
>
	default value: true



## Testing

In Eclipse, add build/e2e as a source folder (and exclude **/*.java) to get easy access to test output.

Bulk update:

	./gradlew test
	cp -r build/e2e/mada/tests src/test/java/mada/
	# Then look at diff
