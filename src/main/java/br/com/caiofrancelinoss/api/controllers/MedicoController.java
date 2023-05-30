package br.com.caiofrancelinoss.api.controllers;

import br.com.caiofrancelinoss.api.domain.models.Medico;
import br.com.caiofrancelinoss.api.domain.repositories.MedicoRepository;
import br.com.caiofrancelinoss.api.dto.DadosAtualizacaoMedicoDto;
import br.com.caiofrancelinoss.api.dto.DadosCadastroMedicoDto;
import br.com.caiofrancelinoss.api.dto.DadosListagemMedicoDto;
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
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedicoDto dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedicoDto> listar(
        @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC)
        Pageable paginacao
    ) {
        return repository.findAll(paginacao).map(DadosListagemMedicoDto::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicoDto dados) {
        try {
            var medico = repository.getReferenceById(dados.id());

            medico.atualizarInformacoes(dados);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }
    }
}
