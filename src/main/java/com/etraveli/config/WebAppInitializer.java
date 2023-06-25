package com.etraveli.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class WebAppInitializer extends AbstractDispatcherServletInitializer {

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext wac = new AnnotationConfigWebApplicationContext();
		wac.register(WebAppConfig.class);
		return wac;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/mvc/*" };
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return null;
	}
}