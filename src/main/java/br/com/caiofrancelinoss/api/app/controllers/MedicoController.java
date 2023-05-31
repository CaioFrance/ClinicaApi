package br.com.caiofrancelinoss.api.app.controllers;

import br.com.caiofrancelinoss.api.domain.models.Medico;
import br.com.caiofrancelinoss.api.domain.repositories.MedicoRepository;
import br.com.caiofrancelinoss.api.app.dto.DadosAtualizacaoMedicoDto;
import br.com.caiofrancelinoss.api.app.dto.DadosCadastroMedicoDto;
import br.com.caiofrancelinoss.api.app.dto.DadosDetalhamentoMedicoDto;
import br.com.caiofrancelinoss.api.app.dto.DadosListagemMedicoDto;
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
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroMedicoDto dados, UriComponentsBuilder uriBuilder) {
        var medico = repository.save(new Medico(dados));

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDto>> listar(
        @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC)
        Pageable paginacao
    ) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDto::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoMedicoDto dados) {
        var medico = repository.getReferenceById(dados.id());

        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedicoDto(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);

        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedicoDto(medico));
    }
}
