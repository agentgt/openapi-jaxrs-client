package dk.mada.jaxrs.generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import dk.mada.jaxrs.generator.DtoGenerator.ExtraTemplate;
import dk.mada.jaxrs.model.Property;

/**
 * Keeps track of imports for a single template, taking
 * generator options into consideration.
 */
public class Imports {
	private static final String JAVA_UTIL_OBJECTS = "java.util.Objects";

	private static final Map<String, String> JACKSON_CODEHAUS = new HashMap<>();
	static {
		JACKSON_CODEHAUS.putAll(Map.of(
			"DeserializationContext", "org.codehaus.jackson.map.DeserializationContext",
			"JsonCreator",            "org.codehaus.jackson.annotate.JsonCreator",
			"JsonDeserialize",        "org.codehaus.jackson.map.annotate.JsonDeserialize",
			"JsonDeserializer",       "org.codehaus.jackson.map.JsonDeserializer",
			"JsonGenerator",          "org.codehaus.jackson.JsonGenerator",
			"JsonIgnore",             "org.codehaus.jackson.annotate.JsonIgnore"));
		JACKSON_CODEHAUS.putAll(Map.of(
			"JsonParser",             "org.codehaus.jackson.JsonParser",
			"JsonProcessingException","org.codehaus.jackson.JsonProcessingException",
			"JsonProperty",           "org.codehaus.jackson.annotate.JsonProperty",
			"JsonPropertyOrder",      "org.codehaus.jackson.annotate.JsonPropertyOrder",
			"JsonSerialize",          "org.codehaus.jackson.map.annotate.JsonSerialize",
			"JsonSerializer",         "org.codehaus.jackson.map.JsonSerializer",
			"JsonValue",              "org.codehaus.jackson.annotate.JsonValue",
			"SerializerProvider",     "org.codehaus.jackson.map.SerializerProvider"));
	}

	private static final Map<String, String> JACKSON_FASTERXML = new HashMap<>();
	static {
		JACKSON_FASTERXML.putAll(Map.of(
			"DeserializationContext", "com.fasterxml.jackson.databind.DeserializationContext",
			"JsonCreator",            "com.fasterxml.jackson.annotation.JsonCreator",
			"JsonDeserialize",        "com.fasterxml.jackson.databind.annotation.JsonDeserialize",
			"JsonDeserializer",       "com.fasterxml.jackson.databind.JsonDeserializer",
			"JsonGenerator",          "com.fasterxml.jackson.core.JsonGenerator",
			"JsonIgnore",             "com.fasterxml.jackson.annotation.JsonIgnore",
			"JsonParser",             "com.fasterxml.jackson.core.JsonParser",
			"JsonProcessingException","com.fasterxml.jackson.core.JsonProcessingException"));

		JACKSON_FASTERXML.putAll(Map.of(
			"JsonProperty",           "com.fasterxml.jackson.annotation.JsonProperty",
			"JsonPropertyOrder",      "com.fasterxml.jackson.annotation.JsonPropertyOrder",
			"JsonSerialize",          "com.fasterxml.jackson.databind.annotation.JsonSerialize",
			"JsonSerializer",         "com.fasterxml.jackson.databind.JsonSerializer",
			"JsonValue",              "com.fasterxml.jackson.annotation.JsonValue",
			"SerializerProvider",     "com.fasterxml.jackson.databind.SerializerProvider"));
	}
	
	private final GeneratorOpts opts;
	private final SortedSet<String> imports = new TreeSet<>();

	private Imports(GeneratorOpts opts) {
		this.opts = opts;
	}
	
	public SortedSet<String> get() {
		return imports;
	}
	
	public static Imports newPojo(GeneratorOpts opts) {
		return new Imports(opts)
				.add(JAVA_UTIL_OBJECTS)
				.jackson(opts.isUseJsonSerializeOptions(), "JsonSerialize")
				.jackson("JsonProperty", "JsonPropertyOrder")
				.jsonb("javax.json.bind.annotation.JsonbProperty", "javax.json.bind.annotation.JsonbPropertyOrder");
	}
	
	public static Imports newEnum(GeneratorOpts opts) {
		return new Imports(opts)
				.add(opts.isJackson(), JAVA_UTIL_OBJECTS)
				.jackson(opts.isUseJsonSerializeOptions(), "JsonSerialize")
				.jackson("JsonCreator", "JsonValue")
				.jsonb("javax.json.Json", "javax.json.JsonString", "javax.json.bind.adapter.JsonbAdapter", "javax.json.bind.annotation.JsonbTypeAdapter");
	}
	
	public static Imports newExtras(GeneratorOpts opts, ExtraTemplate tmpl) {
		var imports = new Imports(opts);
		
		if (tmpl == ExtraTemplate._LocalDateJacksonDeserializer) {
			imports
				.add("java.io.IOException", "java.time.LocalDate", "java.time.format.DateTimeFormatter")
				.jackson("JsonParser", "DeserializationContext", "JsonDeserializer");
		}
		if (tmpl == ExtraTemplate._LocalDateJacksonSerializer) {
			imports
				.add("java.io.IOException", "java.time.LocalDate", "java.time.format.DateTimeFormatter")
				.jackson("JsonGenerator", "SerializerProvider", "JsonSerializer", "JsonProcessingException");
		}
		if (tmpl == ExtraTemplate._OffsetDateTimeJacksonDeserializer) {
			imports
				.add("java.io.IOException", "java.time.LocalDateTime", "java.time.OffsetDateTime", "java.time.format.DateTimeFormatter", "java.time.format.DateTimeParseException", "java.time.ZoneId")
				.jackson("JsonParser", "DeserializationContext", "JsonDeserializer", "JsonProcessingException");
		}
		if (tmpl == ExtraTemplate._OffsetDateTimeJacksonSerializer) {
			imports
				.add("java.io.IOException", "java.time.OffsetDateTime", "java.time.format.DateTimeFormatter")
				.jackson("JsonGenerator", "SerializerProvider", "JsonSerializer", "JsonProcessingException");
		}
		return imports;
	}

	public void addPropertyImports(Collection<Property> properties) {
		properties
			.forEach(p -> addAll(p.type().neededImports()));
	}
	
	public Imports jackson(boolean enable, String... classes) {
		if (enable) {
			jackson(classes);
		}
		return this;
	}

	public Imports jackson(String... classes) {
		if (!opts.isJackson()) {
			return this;
		}

		boolean isCodehaus = opts.isJacksonCodehaus();
		for (String c : classes) {
			String mapped;
			if (isCodehaus) {
				mapped = JACKSON_CODEHAUS.get(c);
			} else {
				mapped = JACKSON_FASTERXML.get(c);
			}
			if (mapped == null) {
				throw new IllegalStateException("No jackson import mapping for " + c);
			}
			add(mapped);
		}
		return this;
	}

	public Imports jsonb(boolean enable, String... classes) {
		if (enable && opts.isJsonb()) {
			add(classes);
		}
		return this;
	}

	public Imports jsonb(String... classes) {
		if (opts.isJsonb()) {
			add(classes);
		}
		return this;
	}

	public Imports add(String... classes) {
		for (String c : classes) {
			imports.add(c);
		}
		return this;
	}

	public Imports add(boolean include, String... classes) {
		if (include) {
			add(classes);
		}
		return this;
	}
	
	public void addAll(Collection<String> classes) {
		imports.addAll(classes);
	}
}
