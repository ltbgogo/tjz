package com.abc.tjz.util.json;

import com.abc.tjz.util.spec.TextEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;

	public LocalDateTimeSerializer() {
		super(LocalDateTime.class);
	}
	
	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeObject(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
}


