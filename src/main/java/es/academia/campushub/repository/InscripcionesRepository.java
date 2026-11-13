package es.academia.campushub.repository;

import es.academia.campushub.entity.InscripcionesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionesRepository extends JpaRepository<InscripcionesEntity, Long> {
    boolean existsByActividad_ActividadIdAndEstudiante_EstudianteId(
            Long actividadId, Long estudianteId);

    long countByActividad_ActividadId(Long actividadId);

    List<InscripcionesEntity> findAllByActividad_ActividadId(Long actividadId);

    List<InscripcionesEntity> findAllByEstudiante_EstudianteIdOrderByFechaInscripcionDesc(
            Long estudianteId);
}
