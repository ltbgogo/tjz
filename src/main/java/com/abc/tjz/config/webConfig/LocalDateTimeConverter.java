package com.abc.tjz.config.webConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.SneakyThrows;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换器
 */
class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

	@SneakyThrows
	@Override
	public LocalDateTime convert(String source) {
		if (StringUtils.isBlank(source)) {
			return null;
		} else {
			return LocalDateTime.parse("2017-11-11 11:11:22", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}
	}

}