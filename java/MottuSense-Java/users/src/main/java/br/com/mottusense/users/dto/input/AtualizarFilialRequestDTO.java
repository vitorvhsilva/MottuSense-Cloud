package br.com.mottusense.users.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AtualizarFilialRequestDTO {
    @NotBlank(message = "Você precisa colocar o nome da filial")
    private String nomeFilial;
}
