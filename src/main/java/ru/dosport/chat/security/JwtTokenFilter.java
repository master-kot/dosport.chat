package ru.dosport.chat.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.dosport.chat.dto.ErrorDto;
import ru.dosport.chat.exceptions.JwtAuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT токен фильтр, перехватывающий все HTTP запросы.
 */
@Log4j2
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    // Провайдер JWT токенов
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = jwtTokenProvider.resolveToken(req);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);

                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(req, res);
        } catch (JwtAuthenticationException ex) {
            log.debug(ex);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(new ErrorDto(401, ex.getMessage()));
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json);
        }
    }
}
