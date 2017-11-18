package com.abc.tjz.config.webConfig;

import java.util.Date;

import lombok.SneakyThrows;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换器
 */
class DateConverter implements Converter<String, Date> {

	@SneakyThrows
	@Override
	public Date convert(String source) {
		if (StringUtils.isBlank(source)) {
			return null;
		} else {
			return DateUtils.parseDate(source, "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm");
		}
	}
}