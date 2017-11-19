package com.abc.tjz.config.webConfig;

import com.abc.tjz.util.misc.ThreadContext;
import lombok.SneakyThrows;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFilter extends OncePerRequestFilter {
	
	@SneakyThrows
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		ThreadContext.setRequest(request);
		ThreadContext.setResponse(response);
		chain.doFilter(request, response);
	}
}



