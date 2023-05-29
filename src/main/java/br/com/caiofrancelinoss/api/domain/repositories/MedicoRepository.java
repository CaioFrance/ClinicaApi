package br.com.caiofrancelinoss.api.domain.repositories;

import br.com.caiofrancelinoss.api.domain.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
