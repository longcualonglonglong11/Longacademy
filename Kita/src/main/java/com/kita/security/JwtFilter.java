package com.kita.security;

import com.kita.entity.dto.AuthDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends BasicAuthenticationFilter {
    private UserDetailsService userDetailsService;

    public JwtFilter(AuthenticationManager authentication, UserDetailsService userDetailsService) {
        super(authentication);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if(request.getHeader("Public").equals("public")){
            UserDetails userDetails = new AuthDto("guest", "guest", new SimpleGrantedAuthority("ROLE_GUEST"));
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }
//                String tokenHeader = request.getHeader("Authorization");
//
//        System.out.println(tokenHeader);
//        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
//            try {
//                String token = tokenHeader.replace("Bearer ", "");
//                String email = Jwts.parser().setSigningKey("Longcademy").parseClaimsJws(token).getBody().getSubject();
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
//                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
//                        userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                chain.doFilter(request, response);
//
//            } catch (Exception e) {
//                System.out.println("TOKEN NOT VALID");
//                e.printStackTrace();
//            }
//        }


    }

}
