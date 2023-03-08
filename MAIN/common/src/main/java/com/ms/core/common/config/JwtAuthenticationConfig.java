package com.ms.core.common.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * Config JWT.
 * Only one property 'security.jwt.secret' is mandatory.
 *
 * @author praveen 
 */
@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${shuaicj.security.jwt.url:/login}")
    private String url;

    @Value("${shuaicj.security.jwt.header:Authorization}")
    private String header;

    @Value("${shuaicj.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${shuaicj.security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${security.jwt.secret:uobpoc}")
    private String accessSecret;
}
