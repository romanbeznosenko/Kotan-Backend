package com.kotanapp.kotanappapi.config;

import com.kotanapp.kotanappapi.filters.JwtAuthenticationFilter;
import com.kotanapp.kotanappapi.filters.RequestLogFilter;
import com.kotanapp.kotanappapi.filters.TrackHeadersFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    private static final String[] WHITE_LIST_URL = {
            "/v3/api-docs",
            "/v3/api-docs.yaml",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/auth/**",
            "/error/**",
            "/ws/**",
            "/internal/**",
            "/api/team/list",
            "api/match/list",
            "api/team/{teamId}/player/list",
            "api/team/{teamId}/player/{playerId}",
            "api/news/list",
            "api/news/{newsId}",
            "/api/tag/list"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            TrackHeadersFilter trackHeadersFilter,
            RequestLogFilter requestLogFilter,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {

        // Stateless session management
        httpSecurity.sessionManagement(sess ->
                sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // Add filters in correct order
        httpSecurity.addFilterBefore(requestLogFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(trackHeadersFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // CORS
        httpSecurity.cors(Customizer.withDefaults());

        // Disable CSRF (not needed for stateless JWT)
        httpSecurity.csrf(csrf -> csrf.disable());

        // Authorization rules
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

            for (String urlPattern : WHITE_LIST_URL) {
                auth.requestMatchers(urlPattern).permitAll();
            }

            auth.requestMatchers("/api/**").authenticated();
        });

        // Exception handling
        httpSecurity.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                )
        );

        return httpSecurity.build();
    }
}