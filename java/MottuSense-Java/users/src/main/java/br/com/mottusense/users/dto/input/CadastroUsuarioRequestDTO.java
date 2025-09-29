package br.com.mottusense.users.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CadastroUsuarioRequestDTO {
    @NotBlank(message = "É necessário digitar um nome de usuário")
    @Size(min = 3, message = "O nome deve ter mais de 3 caracteres;")
    private String nomeUsuario;

    @NotBlank(message = "O CPF não pode estar vazio")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 digitos")
    private String cpfUsuario;

    @NotBlank(message = "É necessário digitar um número de telefone")
    private String telefoneUsuario;

    @Email
    @NotBlank(message = "É necessário digitar um email")
    private String emailUsuario;

    @NotBlank(message = "É necessário digitar uma senha")
    @Size(min = 6, message = "A senha deve ter mais de 6 caracteres")
    private String senhaUsuario;

    @NotNull(message = "É necessário digitar o dia de sua data de nascimento")
    private Integer dia;

    @NotNull(message = "É necessário digitar o mês de sua data de nascimento")
    private Integer mes;

    @NotNull(message = "É necessário digitar o ano de sua data de nascimento")
    private Integer ano;
}
