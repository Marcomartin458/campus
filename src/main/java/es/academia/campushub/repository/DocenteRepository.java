package es.academia.campushub.repository;

import es.academia.campushub.entity.DocenteEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {
    Optional<DocenteEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}