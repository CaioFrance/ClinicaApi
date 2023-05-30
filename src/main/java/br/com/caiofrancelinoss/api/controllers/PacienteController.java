package br.com.caiofrancelinoss.api.controllers;

import br.com.caiofrancelinoss.api.domain.models.Paciente;
import br.com.caiofrancelinoss.api.domain.repositories.PacienteRepository;
import br.com.caiofrancelinoss.api.dto.DadosCadastroPacienteDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
