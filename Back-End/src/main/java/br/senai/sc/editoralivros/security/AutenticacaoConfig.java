package br.senai.sc.editoralivros.security;

import br.senai.sc.editoralivros.security.service.GoogleService;
import br.senai.sc.editoralivros.security.service.JpaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@AllArgsConstructor
public class AutenticacaoConfig {

    private JpaService jpaService;
    private GoogleService googleService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jpaService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(
                "http://localhost:3000"
        ));
        corsConfiguration.setAllowedMethods(List.of(
                "POST", "DELETE", "GET", "PUT"
        ));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    // Configura as autorizações de acesso
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                // Libera o acesso sem autenticação para /login
                .antMatchers("/editoralivros/login", "/editoralivros/login/auth", "/logout").permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/editoralivros/livro").hasAuthority("Autor")
                // Determina que todas as demais requisições precisam de autenticação
                .anyRequest().authenticated();

        httpSecurity.csrf().disable();
        httpSecurity.cors().configurationSource(corsConfigurationSource());

        httpSecurity.logout().permitAll();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(new AutenticacaoFiltro(new TokenUtils(), jpaService), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    // Injeção de dependências no AutenticacaoController quando necessitar (autowired não funcionar)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration ac) throws Exception {
        return ac.getAuthenticationManager();
    }
}
