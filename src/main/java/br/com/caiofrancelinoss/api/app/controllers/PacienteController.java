package br.com.caiofrancelinoss.api.app.controllers;

import br.com.caiofrancelinoss.api.domain.models.Paciente;
import br.com.caiofrancelinoss.api.domain.repositories.PacienteRepository;
import br.com.caiofrancelinoss.api.app.dto.DadosAtualizacaoPacienteDto;
import br.com.caiofrancelinoss.api.app.dto.DadosCadastroPacienteDto;
import br.com.caiofrancelinoss.api.app.dto.DadosDetalhamentoPacienteDto;
import br.com.caiofrancelinoss.api.app.dto.DadosListagemPacienteDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
        var paciente = repository.getReferenceById(dados.id());

        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPacienteDto(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);

        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPacienteDto> detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPacienteDto(paciente));
    }

}
