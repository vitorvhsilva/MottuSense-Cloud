package br.com.mottusense.users.dto.output;

import lombok.Data;

@Data
public class ObterFilialResponseDTO {
    private String idFilial;
    private String nomeFilial;
    private LocalizacaoDTO localizacao;
}
