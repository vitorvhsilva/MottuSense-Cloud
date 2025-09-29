package br.com.mottusense.users.dto.output;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErroDTO {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String erro;
    private String mensagem;

    public ErroDTO() {
    }

    public ErroDTO(String mensagem, String erro) {
        this.mensagem = mensagem;
        this.erro = erro;
    }
}
