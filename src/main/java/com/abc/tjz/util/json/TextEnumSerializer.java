package com.abc.tjz.util.json;

import java.io.IOException;

import com.abc.tjz.util.spec.TextEnum;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.ArrayUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TextEnumSerializer extends StdSerializer<TextEnum> {

	private static final long serialVersionUID = 1L;
	
	public TextEnumSerializer() {
		super(TextEnum.class);
	}
	
	@Override
	public void serialize(TextEnum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeObjectField("text", value.getText());
		gen.writeObjectField("value", value.toString());
		gen.writeEndObject();
	}
}


