package br.com.caiofrancelinoss.api.controllers;

import br.com.caiofrancelinoss.api.domain.models.Medico;
import br.com.caiofrancelinoss.api.domain.repositories.MedicoRepository;
import br.com.caiofrancelinoss.api.dto.DadosCadastroMedicoDto;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
