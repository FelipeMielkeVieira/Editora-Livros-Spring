package br.senai.sc.editoralivros.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AutenticacaoConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    // Configura as autorizações de acesso

    @Override
    protected void configure(HttpSecurity httpSecurity) {
        try {
            httpSecurity.authorizeRequests()
                    // Libera o acesso sem autenticação para /login
                    .antMatchers("/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/editoralivros/pessoa").permitAll()
                    // Determina que todas as demais requsições precisam de autenticação
                    .anyRequest().authenticated()
//                    .and().formLogin()
                    .and().csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().addFilterBefore(new AutenticacaoFiltro(autenticacaoService), UsernamePasswordAuthenticationFilter.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Configura a autenticação para os acessos

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    // Utilizado para realizar a autenticação em AutenticacaoController
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
