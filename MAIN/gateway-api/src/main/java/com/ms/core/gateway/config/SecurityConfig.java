package com.ms.core.gateway.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ms.core.common.config.JwtAuthenticationConfig;
import com.ms.core.common.config.JwtTokenAuthenticationFilter;

/**
 * Config role-based auth.
 *
 * @author praveen
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationConfig config;

    @Bean
    public JwtAuthenticationConfig jwtConfig() {
        return new JwtAuthenticationConfig();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .anonymous()
                .and()
                    .exceptionHandling().authenticationEntryPoint(
                            (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                    .addFilterAfter(new JwtTokenAuthenticationFilter(config),
                            UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/auth/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/customer/**").hasRole("PREPARER")
                    .antMatchers(HttpMethod.PUT, "/customer/**").hasRole("PREPARER")
                    .antMatchers(HttpMethod.DELETE, "/customer/**").hasRole("ADMIN")
                    .anyRequest().authenticated(); 
    }
}

