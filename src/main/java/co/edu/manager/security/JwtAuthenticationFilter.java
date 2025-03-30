package co.edu.manager.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.edu.manager.service.UserDetail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	 private final JwtUtil jwtUtil;
	    private final UserDetail userDetail;
	    
		public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetail userDetail) {
			super();
			this.jwtUtil = jwtUtil;
			this.userDetail = userDetail;
		}

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
				FilterChain filterChain) throws ServletException, IOException {
	        String authHeader = request.getHeader("Authorization");
	        String token = null;
	        String username = null;

	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            username = jwtUtil.extractUsername(token);
			
		}
	        
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetail.loadUserByUsername(username);
	            
	            if (jwtUtil.validateToken(token, userDetails)) {
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }
	        }
	        filterChain.doFilter(request, response);
	 
	        
		}

}
