package br.com.danilo.config.security;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	private final int _1_HORA = 3600;
	private final int _3_HORA = 10800;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public AppUserDetailService userDetailsService;
	
	//BASIC AUTHENTITCATION
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients
		.inMemory()
		.withClient("wpp-api")
		.secret(new BCryptPasswordEncoder().encode("my_p4ssw0rd"))
		.authorizedGrantTypes("password",  "authorization_code", "password")
		.scopes("read", "write")
		.accessTokenValiditySeconds(_1_HORA)
		.refreshTokenValiditySeconds(_3_HORA);
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.reuseRefreshTokens(false)
		.userDetailsService(userDetailsService)
		.authenticationManager(authenticationManager);
	}
	

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		
		//----------------------------------------------------------------		
		//Sempre que a API for iniciada uma nova Chave será gerada
		//Dessa forma os clients serão obrigado a autenticar novamente 
		SecureRandom secureRandom = new SecureRandom();
		byte[] keyBytes = new byte[256]; 
		secureRandom.nextBytes(keyBytes);
		String randomKey = Base64.getEncoder().encodeToString(keyBytes);
		//----------------------------------------------------------------
		
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(randomKey);
		return converter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
}
