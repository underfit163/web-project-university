package com.underfit.trpo.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    /**
     * Данный фильтер проверяет, является ли данный пользователь аутентифицированным
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
      try {
          String jwt = parseJwt(request);
          if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
              String username = jwtUtils.getUserNameFromJwtToken(jwt);

              UserDetails userDetails = userDetailsService.loadUserByUsername(username);
              //проверка пользователя в базе по логину и извлечение прав которыми он наделен
              UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                      userDetails, null, userDetails.getAuthorities());
              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

              SecurityContextHolder.getContext().setAuthentication(authentication);
          }
      } catch (Exception e) {
          log.error("Пользователь не может авторизоваться" + e.getMessage(), e);
      }
      filterChain.doFilter(request, response);
    }

    /**
     * Вытаскиваем токен из заголовка
     * @param request
     * @return
     */
    private String parseJwt(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        return StringUtils.hasText(auth) && auth.startsWith("Bearer ") ? auth.substring(7): null;
    }
}
