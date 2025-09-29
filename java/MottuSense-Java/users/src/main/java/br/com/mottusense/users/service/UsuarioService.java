package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.ConfiguracaoUsuario;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.dto.input.AtualizarUsuarioRequestDTO;
import br.com.mottusense.users.dto.input.CadastroUsuarioRequestDTO;
import br.com.mottusense.users.exception.UsuarioNaoEncontradoException;
import br.com.mottusense.users.repository.ConfiguracaoUsuarioRepository;
import br.com.mottusense.users.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private ConfiguracaoUsuarioRepository configuracaoRepository;

    public Usuario salvar(Usuario usuario, LocalDate dataNasc) {
        usuario.setDataNascimentoUsuario(dataNasc);
        usuario.setDataCriacaoUsuario(LocalDateTime.now());
        usuario.setLinkFotoUsuario("");

        ConfiguracaoUsuario config = configuracaoRepository.save(criarConfiguracaoUsuario(usuario));
        usuario.setConfiguracaoUsuario(config);

        return usuarioRepository.save(usuario);
    }

    private ConfiguracaoUsuario criarConfiguracaoUsuario(Usuario usuario) {
        return new ConfiguracaoUsuario(null, false, false,
                false, false, false, usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obterPorId(String id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado!"));
    }

    public void deletarPorId(String id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario atualizarUsuario(String id, AtualizarUsuarioRequestDTO dto) {
        Usuario usuario = obterPorId(id);

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenhaUsuario(dto.getSenhaUsuario());

        return usuario;
    }
}
