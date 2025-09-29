package br.com.mottusense.users.http;

import br.com.mottusense.users.dto.output.EnderecoViaCep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep-client", url = "https://viacep.com.br/ws")
public interface ViaCepClient {
    @GetMapping("/{cep}/json")
    EnderecoViaCep obterEnderecoDoUsuario(@PathVariable String cep);
}
