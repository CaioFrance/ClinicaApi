package br.com.caiofrancelinoss.api.domain.repositories;

import br.com.caiofrancelinoss.api.domain.models.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findByAtivoIsTrue(Pageable paginacao);
}
