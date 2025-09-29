package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.dto.input.AtualizarFilialRequestDTO;
import br.com.mottusense.users.dto.input.CadastroFilialRequestDTO;
import br.com.mottusense.users.exception.FilialNaoEncontradaException;
import br.com.mottusense.users.repository.FilialRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilialService {

    private FilialRepository filialRepository;

    public Filial salvar(Filial filial) {
        return filialRepository.save(filial);
    }


    public List<Filial> listarFiliais() {
        return filialRepository.findAll();
    }

    public Filial obterPorId(String id) {
        return filialRepository.findById(id)
                .orElseThrow(() -> new FilialNaoEncontradaException("Filial não encontrada!"));
    }

    public void deletarPorId(String id) {
        filialRepository.deleteById(id);
    }

    @Transactional
    public Filial atualizarFilial(String id, @Valid AtualizarFilialRequestDTO dto) {
        Filial filial = obterPorId(id);
        filial.setNomeFilial(dto.getNomeFilial());

        return filial;
    }
}
