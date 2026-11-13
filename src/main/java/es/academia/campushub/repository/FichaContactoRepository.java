package es.academia.campushub.repository;

import es.academia.campushub.entity.FichaContactoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaContactoRepository extends JpaRepository<FichaContactoEntity, Long> {
    Optional<FichaContactoEntity> findByEstudiante_EstudianteId(Long estudianteId);
}