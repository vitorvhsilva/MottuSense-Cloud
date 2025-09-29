package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.ConfiguracaoUsuario;
import br.com.mottusense.users.dto.input.AtualizarConfiguracaoUsuarioRequestDTO;
import br.com.mottusense.users.dto.output.AtualizarConfiguracaoUsuarioResponseDTO;
import br.com.mottusense.users.service.ConfiguracaoUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@RequestMapping("/configuracoesview")
@Controller
public class ConfiguracaoUsuarioControllerView {

    private final ModelMapper mapper;
    private final ConfiguracaoUsuarioService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        ConfiguracaoUsuario cfg = service.buscarPorId(id);
        AtualizarConfiguracaoUsuarioRequestDTO form = mapper.map(cfg, AtualizarConfiguracaoUsuarioRequestDTO.class);

        model.addAttribute("id", id);
        model.addAttribute("form", form);
        return "configuracoes/editar";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String atualizarViaPost(
            @PathVariable String id,
            @Valid @ModelAttribute("form") AtualizarConfiguracaoUsuarioRequestDTO form,
            BindingResult binding,
            RedirectAttributes redirect) {

        if (binding.hasErrors()) {
            return "configuracoes/editar";
        }

        ConfiguracaoUsuario config = mapper.map(form, ConfiguracaoUsuario.class);
        AtualizarConfiguracaoUsuarioResponseDTO response =
                mapper.map(service.alterarConfiguracao(id, config), AtualizarConfiguracaoUsuarioResponseDTO.class);

        redirect.addFlashAttribute("ok", "Configurações salvas com sucesso.");
        redirect.addFlashAttribute("response", response);
        return "redirect:/usuariosview/listar";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public String atualizarViaPut(
            @PathVariable String id,
            @Valid @ModelAttribute("form") AtualizarConfiguracaoUsuarioRequestDTO form,
            BindingResult binding,
            RedirectAttributes redirect) {

        return atualizarViaPost(id, form, binding, redirect);
    }

}
