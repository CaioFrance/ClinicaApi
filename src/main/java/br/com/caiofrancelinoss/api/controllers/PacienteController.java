package br.com.caiofrancelinoss.api.controllers;

import br.com.caiofrancelinoss.api.domain.models.Paciente;
import br.com.caiofrancelinoss.api.domain.repositories.PacienteRepository;
import br.com.caiofrancelinoss.api.dto.DadosAtualizacaoPacienteDto;
import br.com.caiofrancelinoss.api.dto.DadosCadastroPacienteDto;
import br.com.caiofrancelinoss.api.dto.DadosDetalhamentoPacienteDto;
import br.com.caiofrancelinoss.api.dto.DadosListagemPacienteDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDto> cadastrar(@RequestBody @Valid DadosCadastroPacienteDto dados, UriComponentsBuilder uriBuilder) {
        var paciente = repository.save(new Paciente(dados));

        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDto(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDto>> listar(
        @PageableDefault(sort = "nome", direction = Sort.Direction.ASC)
        Pageable paginacao
    ) {
        return ResponseEntity.ok(repository.findByAtivoIsTrue(paginacao).map(DadosListagemPacienteDto::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDto> atualizar(@RequestBody @Valid DadosAtualizacaoPacienteDto dados) {
        try {
            var paciente = repository.getReferenceById(dados.id());

            paciente.atualizarInformacoes(dados);

            return ResponseEntity.ok(new DadosDetalhamentoPacienteDto(paciente));
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            var paciente = repository.getReferenceById(id);

            paciente.excluir();

            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPacienteDto> detalhar(@PathVariable Long id) {
        try {
            var paciente = repository.getReferenceById(id);

            return ResponseEntity.ok(new DadosDetalhamentoPacienteDto(paciente));
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
