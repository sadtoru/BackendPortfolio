package com.daiana.backendportfolio.auth.filters;

import com.daiana.backendportfolio.auth.SimpleGrantedAuthorityJsonCreator;
import com.daiana.backendportfolio.auth.TokenJwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JwtValidationFilter extends BasicAuthenticationFilter {

	public JwtValidationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		String header = request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION);

		if(header == null || !header.startsWith(TokenJwtConfig.PREFIX_TOKEN)){
			chain.doFilter(request, response);
			return;
		}

		String token = header.replace(TokenJwtConfig.PREFIX_TOKEN, "");

		try {
			Claims claims = Jwts.parser().verifyWith((SecretKey) TokenJwtConfig.SECRET_KET).build().parseSignedClaims(token).getPayload();

			Object authotiesClaims = claims.get("authorities");
			String username = claims.getSubject();

			Collection<? extends GrantedAuthority> authorities = Arrays
					.asList(new ObjectMapper()
							.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
							.readValue(authotiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			chain.doFilter(request, response);
		}catch (JwtException e){
			Map<String, String> body = new HashMap<>();
			body.put("error", e.getMessage());
			body.put("message", "el token no es valido");

			response.getWriter().write(new ObjectMapper().writeValueAsString(body));
			response.setStatus(403);
			response.setContentType("application/json");
		}
	}
}
