package com.abc.tjz.util.ftl;

import freemarker.template.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CustomObjectWrapper extends DefaultObjectWrapper {
    
    @Override
    public TemplateModel wrap(final Object obj) throws TemplateModelException {
        if (obj instanceof LocalDateTime) {
        	return new SimpleDate(Timestamp.valueOf((LocalDateTime) obj), TemplateDateModel.DATETIME);
        } else if (obj instanceof LocalDate) {
        	return new SimpleDate(Date.valueOf((LocalDate) obj), TemplateDateModel.DATE);
        } else if (obj instanceof LocalTime) {
        	return new SimpleDate(Time.valueOf((LocalTime) obj), TemplateDateModel.TIME);
        }
        return super.wrap(obj);
    }
}


