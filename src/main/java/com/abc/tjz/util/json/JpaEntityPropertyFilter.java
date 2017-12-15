package com.abc.tjz.util.json;

import com.abc.tjz.util.db.entity.IdEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author LiuTongbin
 * @date 2017/12/11 0011 13:20
 */
public class JpaEntityPropertyFilter extends SimpleBeanPropertyFilter {

    public static final String ID = "JpaEntityPropertyFilter";

    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        if (writer.getAnnotation(ManyToOne.class) != null || writer.getAnnotation(OneToOne.class) != null) {
            jgen.writeObjectField(writer.getName() + "Id", ((IdEntity) pojo).getId());
        } else {
            writer.serializeAsField(pojo, jgen, provider);
        }
    }
}
