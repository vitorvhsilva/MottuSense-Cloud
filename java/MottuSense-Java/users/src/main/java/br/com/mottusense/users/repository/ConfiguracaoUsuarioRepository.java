package br.com.mottusense.users.repository;

import br.com.mottusense.users.domain.ConfiguracaoUsuario;
import br.com.mottusense.users.domain.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoUsuarioRepository extends JpaRepository<ConfiguracaoUsuario, String> {
}
