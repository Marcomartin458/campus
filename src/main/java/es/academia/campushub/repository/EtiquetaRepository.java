package es.academia.campushub.repository;

import es.academia.campushub.entity.EtiquetaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtiquetaRepository extends JpaRepository<EtiquetaEntity, Long> {
    Optional<EtiquetaEntity> findByNombre(String nombre);
}