package br.com.danilo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
	
@SuppressWarnings("deprecation")
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
		
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(
				"/*",
				"/v2/api-docs",
				"/configuration/**",
				"/swagger*/**",
				"/webjars/**")
			.permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception{
		resources.stateless(true);
	}
	
	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler(){
		return new OAuth2MethodSecurityExpressionHandler();
	}
	
}