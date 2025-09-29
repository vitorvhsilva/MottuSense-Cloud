package br.com.mottusense.users.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AtualizarUsuarioRequestDTO {
    @NotBlank(message = "É necessário digitar um nome de usuário")
    @Size(min = 3, message = "O nome deve ter mais de 3 caracteres;")
    private String nomeUsuario;

    @NotBlank(message = "É necessário digitar uma senha")
    @Size(min = 6, message = "A senha deve ter mais de 6 caracteres")
    private String senhaUsuario;
}
