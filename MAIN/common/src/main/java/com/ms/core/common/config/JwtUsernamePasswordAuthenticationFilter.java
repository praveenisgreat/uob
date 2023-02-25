package com.ms.core.common.config;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ms.core.common.config.model.UserAuth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Authenticate the request to url /login by POST with json body '{ username, password }'.
 * If successful, response the client with header 'Authorization: Bearer jwt-token'.
 *
 * @author shuaicj 2017/10/18
 */
public class JwtUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtAuthenticationConfig config;
    private final ObjectMapper mapper;

    public JwtUsernamePasswordAuthenticationFilter(JwtAuthenticationConfig config, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(config.getUrl(), "POST"));
        setAuthenticationManager(authManager);
        this.config = config;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rsp)
            throws AuthenticationException, IOException {
        UserAuth u = mapper.readValue(req.getInputStream(), UserAuth.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                u.getUsername(), u.getPassword(), Collections.emptyList()
        ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain chain,
                                            Authentication auth) {
    	
        Instant now = Instant.now();
        String accessToken = Jwts.builder()
                .setSubject(auth.getName())
                .setIssuer("JWT")
                /*
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        */
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .claim("data", auth.getPrincipal())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(config.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, config.getAccessSecret().getBytes())
                .compact();
        System.out.println(auth.getPrincipal());
        // create a new Gson instance
        Gson gson = new Gson();
        // convert your list to json
        String jsonCartList = gson.toJson(auth.getPrincipal());
        // print your generated json
        System.out.println("jsonCartList: " + jsonCartList);
        try {
			rsp.getWriter().write(jsonCartList);
		} catch (IOException e) {
			e.printStackTrace();
		}
        rsp.addHeader(config.getHeader(), config.getPrefix() + " " + accessToken);;
    }
}
