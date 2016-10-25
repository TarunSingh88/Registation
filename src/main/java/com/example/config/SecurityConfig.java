package com.example.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true,jsr250Enabled=true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
public class SecurityConfig{
	@Autowired
	@Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;
	
	@Resource
    private PasswordEncoder passwordEncoder;
	
	
	@Configuration
    @Order(1)
    public static class ApiV1WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http    .antMatcher("/api/v1/**")
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/api/v1/user/create")        
                    .authenticated()
                    .antMatchers("/api/v1/users")
                    .authenticated()                   
                    .anyRequest().permitAll()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .httpBasic()
                    .and() 
                    .csrf()
                    .disable();

        }

    }
	
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
		/*auth.inMemoryAuthentication().withUser("tarun").password("tarun").roles("ADMIN","USER");*/
    }
	
}
