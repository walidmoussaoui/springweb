package joe.spring.springweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class MultipleWebSecurityConfig {

//	private static final String PROPERTY_NAME_DATABASE_DRIVER_CLASS = "db.driverClass";
//	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
//	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
//	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
//
//	@Resource
//	private Environment environment;
	
//	@Autowired
//	private DataSource securityDataSource;
//
//	@Bean
//	public DriverManagerDataSource securityDataSource() {
//
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//
//		ds.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER_CLASS));
//		ds.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
//		ds.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
//		ds.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
//		return ds;
//	}
//
//	@Bean
//	public UserDetailsService userDetailsService() {
//		JdbcDaoImpl dao = new JdbcDaoImpl();
//		dao.setDataSource(securityDataSource);
//		return dao;
//	}

	/**
	 * In memory authentication implementation. Comment or remove this and uncomment
	 * the code above to switch to a JDBC implementation
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("password").roles("USER").build());
		manager.createUser(User.withUsername("admin").password("password").roles("ADMIN").build());
		return manager;
	}
	
	@Configuration
	@Order(1)                                                        
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.antMatcher("/api/**")                               
				.authorizeRequests()
					.anyRequest().access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
					.and()
				.httpBasic();
		}
	}

	@Configuration                                                   
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/", "/resources/**", "/welcome", "/login").permitAll()
				.antMatchers("/home").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
				.and()		
					.formLogin().loginPage("/login")
					.defaultSuccessUrl("/home")
					.failureUrl("/login?error")
					.usernameParameter("username").passwordParameter("password")				
				.and()
					.logout()
					.logoutSuccessUrl("/")
					.deleteCookies("JSESSIONID"); 
		}
		
	}
}
