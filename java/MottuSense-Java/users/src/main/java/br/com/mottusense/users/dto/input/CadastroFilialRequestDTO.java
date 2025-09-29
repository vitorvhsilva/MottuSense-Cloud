package br.com.mottusense.users.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CadastroFilialRequestDTO {
    @NotBlank(message = "Você precisa colocar o nome de uma filial")
    private String nomeFilial;
    @NotBlank(message = "É necessário digitar um CEP válido")
    @Size(min = 8, max = 8, message = "O CEP deve conter 8 digitos")
    private String cep;
}
