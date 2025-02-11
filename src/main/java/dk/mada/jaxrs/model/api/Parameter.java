package dk.mada.jaxrs.model.api;

import java.util.Optional;

import org.immutables.value.Value.Immutable;

import dk.mada.jaxrs.model.types.Reference;

/**
 * Information about a single parameter.
 */
@Immutable
public interface Parameter {
    /** {@return a builder for this type} */
    static ImmutableParameter.Builder builder() {
        return ImmutableParameter.builder();
    }

    /** {@return parameter name} */
    String name();
    /** {@return parameter type} */
    Reference reference();

    /** {@return an optional description of the parameter} */
    Optional<String> description();
    /** {@return true if the parameter is required, otherwise false} */
    boolean isRequired();

    /** {@return true if the parameter is a header parameter, otherwise false} */
    boolean isHeaderParam();
    /** {@return true if the parameter is a path parameter, otherwise false} */
    boolean isPathParam();
    /** {@return true if the parameter is a query parameter, otherwise false} */
    boolean isQueryParam();
    /** {@return true if the parameter is a form parameter, otherwise false} */
    boolean isFormParam();
}
