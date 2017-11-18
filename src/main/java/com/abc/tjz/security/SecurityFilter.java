package com.abc.tjz.security;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.SneakyThrows;

import org.apache.commons.lang3.Validate;
import org.springframework.web.filter.OncePerRequestFilter;

public class SecurityFilter extends OncePerRequestFilter {
	
	@SneakyThrows
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		System.out.println(this + "***************start");
		chain.doFilter(request, response);
		System.out.println(this + "***************end");
	}
}



