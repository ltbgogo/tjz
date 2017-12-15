package com.abc.tjz.util.json;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class JpaLazyIntrospector extends JacksonAnnotationIntrospector {

	private static final long serialVersionUID = 1L;

	@Override
	protected boolean _isIgnorable(Annotated a) {
		Basic basic = a.getAnnotation(Basic.class); // Default Eager
		if (basic != null && basic.fetch() == FetchType.LAZY) {
			return true;
		}
		ElementCollection elementCollection = a.getAnnotation(ElementCollection.class); // Default Lazy
		if (elementCollection != null && elementCollection.fetch() != FetchType.EAGER) {
			return true;
		}
		ManyToMany manyToMany = a.getAnnotation(ManyToMany.class); // Default Lazy
		if (manyToMany != null && manyToMany.fetch() != FetchType.EAGER) {
			return true;
		}
		OneToMany oneToMany = a.getAnnotation(OneToMany.class); // Default Lazy
		if (oneToMany != null && oneToMany.fetch() != FetchType.EAGER) {
			return true;
		}
		return super._isIgnorable(a);
	}
}
