package br.com.caiofrancelinoss.api.controllers;

import br.com.caiofrancelinoss.api.domain.models.Paciente;
import br.com.caiofrancelinoss.api.domain.repositories.PacienteRepository;
import br.com.caiofrancelinoss.api.dto.DadosAtualizacaoPacienteDto;
import br.com.caiofrancelinoss.api.dto.DadosCadastroPacienteDto;
import br.com.caiofrancelinoss.api.dto.DadosListagemPacienteDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPacienteDto dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPacienteDto> listar(
        @PageableDefault(sort = "nome", direction = Sort.Direction.ASC)
        Pageable paginacao
    ) {
        return repository.findByAtivoIsTrue(paginacao).map(DadosListagemPacienteDto::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPacienteDto dados) {
        try {
            var paciente = repository.getReferenceById(dados.id());

            paciente.atualizarInformacoes(dados);
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        try {
            var paciente = repository.getReferenceById(id);

            paciente.excluir();
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
