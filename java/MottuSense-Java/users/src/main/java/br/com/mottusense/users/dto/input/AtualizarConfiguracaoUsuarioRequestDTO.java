package br.com.mottusense.users.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AtualizarConfiguracaoUsuarioRequestDTO {
    @NotNull
    private Boolean alertaMotoEntrarPatio;
    @NotNull
    private Boolean alertaMotoSairPatio;
    @NotNull
    private Boolean alertaMotoChegarSemPlaca;
    @NotNull
    private Boolean alertaMotoPrecisaManutencao;
    @NotNull
    private Boolean alertaMotoPreparadaAlugada;
}
