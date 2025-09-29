package br.com.mottusense.users.dto.output;

import lombok.Data;

@Data
public class ConfiguracaoUsuarioDTO {
    private String idConfiguracaoUsuario;
    private Boolean alertaMotoEntrarPatio;
    private Boolean alertaMotoSairPatio;
    private Boolean alertaMotoChegarSemPlaca;
    private Boolean alertaMotoPrecisaManutencao;
    private Boolean alertaMotoPreparadaAlugada;
}
