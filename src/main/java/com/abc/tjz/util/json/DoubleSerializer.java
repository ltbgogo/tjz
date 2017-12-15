package com.abc.tjz.util.json;

import com.abc.tjz.util.spec.TextEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.math.BigDecimal;

public class DoubleSerializer extends StdSerializer<Double> {

	private static final long serialVersionUID = 1L;

	public DoubleSerializer() {
		super(Double.class);
	}

	@Override
	public void serialize(Double value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeNumber(new BigDecimal(value.toString()));
	}
}


