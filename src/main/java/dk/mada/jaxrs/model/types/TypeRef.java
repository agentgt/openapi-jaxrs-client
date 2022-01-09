package dk.mada.jaxrs.model.types;

import java.util.Set;

import org.immutables.value.Value.Immutable;

import dk.mada.jaxrs.model.types.TypeNames.TypeName;

/**
 * A reference to a sibling DTO used during parsing.
 * 
 * The sibling may not have been processed yet,
 * hence this type which acts as a lazy delegation.
 * 
 * 
 * 
 */
@Immutable
public interface TypeRef extends Type {
	public static TypeRef of(TypeName refTypeName, Types types) {
		return ImmutableTypeRef.builder().refTypeName(refTypeName).types(types).build();
	}
	
	TypeName refTypeName();
	Types types();

	default Type dereference() {
		return types().get(refTypeName());
	}
	
	@Override
	default TypeName typeName() {
		return dereference().typeName();
	}

	// FIXME: this should go!
	@Override
	default Set<String> neededImports() {
		return dereference().neededImports();
	}
}
