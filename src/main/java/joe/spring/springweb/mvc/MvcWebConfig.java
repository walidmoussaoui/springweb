package joe.spring.springweb.mvc;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import joe.spring.springapp.data.jpa.DbHibernateConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "joe.spring.springweb.mvc.controller" })
public class MvcWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
    }
	
	@Bean
	public Validator customerModelValidator() {
		return new joe.spring.springweb.mvc.validator.CustomerModelValidator();
	}
	
	@Bean
	public Validator loginModelValidator() {
		return new joe.spring.springweb.mvc.validator.LoginModelValidator();
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource src = new ReloadableResourceBundleMessageSource();
		src.setBasename("classpath:message");
		src.setDefaultEncoding("UTF-8");
		return src;		
	}

	@Bean
	public ContentNegotiationManagerFactoryBean contentNegotiationManager() {
		ContentNegotiationManagerFactoryBean bean = new ContentNegotiationManagerFactoryBean();
		bean.setFavorPathExtension(true);
		bean.setFavorParameter(false);
		bean.setParameterName("mediaType");
		bean.setIgnoreAcceptHeader(true);
		bean.setUseJaf(false);
		bean.setDefaultContentType(MediaType.APPLICATION_JSON);
		
		Properties p = new Properties();
		p.setProperty("json", MediaType.APPLICATION_JSON.toString());
		p.setProperty("xml", MediaType.APPLICATION_XML.toString());
		bean.setMediaTypes(p);
		
		return bean;
	}

}