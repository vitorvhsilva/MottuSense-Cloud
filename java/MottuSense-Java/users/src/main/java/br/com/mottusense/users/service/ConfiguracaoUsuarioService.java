package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.ConfiguracaoUsuario;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.exception.ConfiguracaoUsuarioNaoEncontradaException;
import br.com.mottusense.users.repository.ConfiguracaoUsuarioRepository;
import br.com.mottusense.users.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConfiguracaoUsuarioService {

    private ConfiguracaoUsuarioRepository repository;

    @Transactional
    public ConfiguracaoUsuario alterarConfiguracao(String id, ConfiguracaoUsuario config) {
        ConfiguracaoUsuario configuracao = repository.findById(id)
                .orElseThrow(() -> new ConfiguracaoUsuarioNaoEncontradaException("Configuração do usuário não encontrada pelo id!"));

        configuracao.setAlertaMotoChegarSemPlaca(config.getAlertaMotoChegarSemPlaca());
        configuracao.setAlertaMotoPreparadaAlugada(config.getAlertaMotoPreparadaAlugada());
        configuracao.setAlertaMotoSairPatio(config.getAlertaMotoSairPatio());
        configuracao.setAlertaMotoPrecisaManutencao(config.getAlertaMotoPrecisaManutencao());
        configuracao.setAlertaMotoEntrarPatio(config.getAlertaMotoEntrarPatio());

        return configuracao;
    }


    public ConfiguracaoUsuario buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ConfiguracaoUsuarioNaoEncontradaException(
                        "Configuração do usuário não encontrada pelo id!"));
    }

}
