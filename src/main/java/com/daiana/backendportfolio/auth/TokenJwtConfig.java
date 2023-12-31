package com.daiana.backendportfolio.auth;

import io.jsonwebtoken.Jwts;

import java.security.Key;

public class TokenJwtConfig {
	public final static Key SECRET_KET = Jwts.SIG.HS256.key().build();
	public final static String PREFIX_TOKEN = "Bearer ";
	public final static String HEADER_AUTHORIZATION = "Authorization";
}
