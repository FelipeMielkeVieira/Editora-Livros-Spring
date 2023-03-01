package br.senai.sc.editoralivros.security.users;

import br.senai.sc.editoralivros.model.entities.Pessoa;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/*
    Classe que faz o link entre as classes "Pessoa" e "UserDetails", sendo Pessoa a classe usada no banco de dados e UserDetails para o login
 */
@Data
public class UserJpa implements UserDetails {

    private Pessoa pessoa;

    private Collection<GrantedAuthority> authorities;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    public UserJpa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String getPassword() {
        return pessoa.getSenha();
    }

    @Override
    public String getUsername() {
        return pessoa.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return pessoa.getAtivo();
    }
}
