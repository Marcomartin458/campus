package es.academia.campushub.repository;

import es.academia.campushub.entity.ActividadEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;

public interface ActividadRepository extends JpaRepository<ActividadEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from ActividadEntity a where a.actividadId = :id")
    Optional<ActividadEntity> findByIdForUpdate(@Param("id") Long id);

    @Query("select distinct a from ActividadEntity a join a.etiquetas e where e.id = :etiquetaId")
    List<ActividadEntity> findAllByEtiquetaId(@Param("etiquetaId") Long etiquetaId);
}