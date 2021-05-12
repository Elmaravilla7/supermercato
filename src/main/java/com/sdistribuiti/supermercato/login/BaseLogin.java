package com.sdistribuiti.supermercato.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class BaseLogin extends WebSecurityConfigurerAdapter
{
	private static String REALM = "DOMINIO";
	private static final String[] USER_MATCHER = { "/api/articoli/ricerca/**"};
	private static final String[] ADMIN_MATCHER = { "/api/articoli/inserisci/**", "/api/articoli/elimina/**" };
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	};

	@Bean
	@Override
	public UserDetailsService userDetailsService()
	{
		UserBuilder users = User.builder();
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		
		manager.createUser(
				 users
				.username("Emilio")
				.password(new BCryptPasswordEncoder().encode("123456"))
				.roles("USER")
				.build());
		
		manager.createUser(
				 users
				.username("Admin")
				.password(new BCryptPasswordEncoder().encode("123456"))
				.roles("USER","ADMIN")
				.build());
		
		return manager;
				
	}

	public void createUser(String name, String password) throws Exception {

		UserBuilder users = User.builder();

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		if(manager.userExists(name)){
			throw new Exception("Nome utente esistente, usarne uno diverso!");
		}

		manager.createUser(
				users
						.username(name)
						.password(new BCryptPasswordEncoder().encode(password))
						.roles("USER")
						.build());
	}
	
	@Override
	protected void configure(HttpSecurity http) 
			throws Exception
	{
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(USER_MATCHER).hasAnyRole("USER")
		.antMatchers(ADMIN_MATCHER).hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Bean
	public Accesso getBasicAuthEntryPoint()
	{
		return new Accesso();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
