package joe.spring.springweb.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import joe.spring.springapp.data.jpa.DbHibernateConfig;
import joe.spring.springweb.security.MultipleWebSecurityConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { DbHibernateConfig.class, MultipleWebSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] { "/" };
	}
	
}
