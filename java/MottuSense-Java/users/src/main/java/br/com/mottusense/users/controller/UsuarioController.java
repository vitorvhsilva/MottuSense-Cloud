package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.dto.input.AtualizarUsuarioRequestDTO;
import br.com.mottusense.users.dto.input.CadastroUsuarioRequestDTO;
import br.com.mottusense.users.dto.output.CadastroUsuarioResponseDTO;
import br.com.mottusense.users.dto.output.ConfiguracaoUsuarioDTO;
import br.com.mottusense.users.dto.output.ObterUsuarioResponseDTO;
import br.com.mottusense.users.dto.output.ObterUsuariosResponseDTO;
import br.com.mottusense.users.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<CadastroUsuarioResponseDTO> cadastrarUsuario(@Valid @RequestBody CadastroUsuarioRequestDTO dto){
        Usuario usuario = mapper.map(dto, Usuario.class);
        LocalDate dataNasc = LocalDate.of(dto.getAno(), dto.getMes(), dto.getDia());

        CadastroUsuarioResponseDTO rDTO = mapper.map(usuarioService.salvar(usuario, dataNasc), CadastroUsuarioResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(rDTO);
    }

    @GetMapping
    public List<ObterUsuariosResponseDTO> obterUsuarios() {
        List<Usuario> users = usuarioService.listarUsuarios();
        return users.stream()
                .map(usuario -> mapper.map(usuario, ObterUsuariosResponseDTO.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObterUsuarioResponseDTO> buscarUsuarioPorId(@PathVariable String id){
        Usuario usuario = usuarioService.obterPorId(id);

        ConfiguracaoUsuarioDTO configDTO = mapper.map(usuario.getConfiguracaoUsuario(), ConfiguracaoUsuarioDTO.class);

        ObterUsuarioResponseDTO response = mapper.map(usuario, ObterUsuarioResponseDTO.class);
        response.setConfiguracaoUsuario(configDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObterUsuarioResponseDTO> atualizarUsuario(@PathVariable String id, @Valid @RequestBody AtualizarUsuarioRequestDTO dto){
        Usuario usuario = usuarioService.atualizarUsuario(id, dto);

        return ResponseEntity.ok(mapper.map(usuario, ObterUsuarioResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id){
        usuarioService.deletarPorId(id);

        return ResponseEntity.notFound().build();
    }



}
