package com.abc.tjz.util.json;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

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
		ManyToOne manyToOne = a.getAnnotation(ManyToOne.class); // Default Eager
		if (manyToOne != null && manyToOne.fetch() == FetchType.LAZY) {
			return true;
		}
		OneToOne oneToOne = a.getAnnotation(OneToOne.class); // Default Eager
		if (oneToOne != null && oneToOne.fetch() == FetchType.LAZY) {
			return true;
		}
		return super._isIgnorable(a);
	}
}
