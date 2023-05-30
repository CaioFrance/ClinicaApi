package br.com.caiofrancelinoss.api.controllers;

import br.com.caiofrancelinoss.api.domain.models.Paciente;
import br.com.caiofrancelinoss.api.domain.repositories.PacienteRepository;
import br.com.caiofrancelinoss.api.dto.DadosCadastroPacienteDto;
import br.com.caiofrancelinoss.api.dto.DadosListagemPacienteDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroPacienteDto dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPacienteDto> listar(
        @PageableDefault(sort = "nome", direction = Sort.Direction.ASC)
        Pageable paginacao
    ) {
        return repository.findAll(paginacao).map(DadosListagemPacienteDto::new);
    }

}
