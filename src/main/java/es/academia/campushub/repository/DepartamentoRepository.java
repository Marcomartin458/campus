package es.academia.campushub.repository;

import es.academia.campushub.entity.DepartamentoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Long> {
    Optional<DepartamentoEntity> findByNombre(String nombre);
}