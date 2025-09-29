package br.com.mottusense.users.dto.output;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ObterUsuariosResponseDTO {
    private String idUsuario;
    private String nomeUsuario;
    private String cpfUsuario;
    private String telefoneUsuario;
    private String emailUsuario;
    private LocalDate dataNascimentoUsuario;
}
