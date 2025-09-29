package br.com.mottusense.users.controller;


import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Localizacao;
import br.com.mottusense.users.dto.input.AtualizarFilialRequestDTO;
import br.com.mottusense.users.dto.input.CadastroFilialRequestDTO;
import br.com.mottusense.users.dto.output.LocalizacaoDTO;
import br.com.mottusense.users.dto.output.ObterFilialResponseDTO;
import br.com.mottusense.users.service.FilialService;
import br.com.mottusense.users.service.LocalizacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/filiais")
public class FilialController {

    private FilialService filialService;
    private LocalizacaoService localizacaoService;
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ObterFilialResponseDTO> cadastrarFilial(@RequestBody CadastroFilialRequestDTO dto){
        Filial filial = mapper.map(dto, Filial.class);
        Filial filialSalva = filialService.salvar(filial);
        Localizacao localizacao = localizacaoService.persistirLocalizacao(dto.getCep(), filialSalva);

        ObterFilialResponseDTO response = mapper.map(filialSalva, ObterFilialResponseDTO.class);
        response.setLocalizacao(mapper.map(localizacao, LocalizacaoDTO.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<ObterFilialResponseDTO> listarFiliais(){
        List<Filial> filiais = filialService.listarFiliais();
        return filiais.stream()
                .map(filial -> mapper.map(filial, ObterFilialResponseDTO.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObterFilialResponseDTO> buscarFilialPorId(@PathVariable String id){
        Filial filial = filialService.obterPorId(id);

        LocalizacaoDTO localizacaoDTO = mapper.map(filial.getLocalizacao(), LocalizacaoDTO.class);

        ObterFilialResponseDTO response = mapper.map(filial, ObterFilialResponseDTO.class);
        response.setLocalizacao(localizacaoDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObterFilialResponseDTO> atualizarFilial(@PathVariable String id, @Valid @RequestBody AtualizarFilialRequestDTO dto){
        Filial filial = filialService.atualizarFilial(id, dto);

        return ResponseEntity.ok(mapper.map(filial, ObterFilialResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilial(@PathVariable String id){
        filialService.deletarPorId(id);

        return ResponseEntity.notFound().build();
    }


}
