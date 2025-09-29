package br.com.mottusense.users.dto.output;

import lombok.Data;

@Data
public class LocalizacaoDTO {
    private String idLocalizacao;
    private String cepLocalizacao;
    private String logradouroLocalizacao;
    private String bairroLocalizacao;
    private String estadoLocalizacao;
    private String regiaoLocalizacao;
}
