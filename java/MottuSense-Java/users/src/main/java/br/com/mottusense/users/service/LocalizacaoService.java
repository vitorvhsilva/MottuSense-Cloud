package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Localizacao;
import br.com.mottusense.users.dto.output.EnderecoViaCep;
import br.com.mottusense.users.http.ViaCepClient;
import br.com.mottusense.users.repository.LocalizacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocalizacaoService {

    private LocalizacaoRepository repository;
    private ViaCepClient cepClient;

    public Localizacao persistirLocalizacao(String cep, Filial filial) {

        EnderecoViaCep viaCep = cepClient.obterEnderecoDoUsuario(cep);

        Localizacao localizacao = new Localizacao(null, cep, viaCep.getLogradouro(), viaCep.getBairro(),
                viaCep.getEstado(), viaCep.getRegiao(), filial);

        filial.setLocalizacao(localizacao);
        return repository.save(localizacao);
    }
}
