package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.ConfiguracaoUsuario;
import br.com.mottusense.users.dto.input.AtualizarConfiguracaoUsuarioRequestDTO;
import br.com.mottusense.users.dto.output.AtualizarConfiguracaoUsuarioResponseDTO;
import br.com.mottusense.users.service.ConfiguracaoUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("configuracoes")
@RestController
public class ConfiguracaoUsuarioController {

    private ModelMapper mapper;
    private ConfiguracaoUsuarioService service;

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarConfiguracaoUsuarioResponseDTO> alterarConfiguracao(@PathVariable String id, @RequestBody @Valid AtualizarConfiguracaoUsuarioRequestDTO dto) {
        ConfiguracaoUsuario config = mapper.map(dto, ConfiguracaoUsuario.class);
        AtualizarConfiguracaoUsuarioResponseDTO response = mapper.map(service.alterarConfiguracao(id, config),
                AtualizarConfiguracaoUsuarioResponseDTO.class);
        return ResponseEntity.ok(response);
    }

}
