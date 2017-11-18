package com.abc.tjz.config.webConfig;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public class ErrorController extends BasicErrorController {

    public ErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

	@Override
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		return super.errorHtml(request, response);
	}

	@Override
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		return super.error(request);
	}

}
