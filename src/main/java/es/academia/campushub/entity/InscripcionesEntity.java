package es.academia.campushub.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "inscripciones",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "InscripcionEstudianteActividad",
                        columnNames = {"estudiante_id", "actividad_id"}
                )
        }
)
public class InscripcionesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private EstudianteEntity estudiante;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actividad_id", nullable = false)
    private ActividadEntity actividad;

    @Column(name = "fechaInscripcion", nullable = false, updatable = false)
    private LocalDateTime fechaInscripcion;

    public InscripcionesEntity() {
    }

    public InscripcionesEntity(EstudianteEntity estudiante, ActividadEntity actividad) {
        this.estudiante = estudiante;
        this.actividad = actividad;
    }

    @PrePersist
    protected void onCreate() {
        if (this.fechaInscripcion == null) {
            this.fechaInscripcion = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }

    public ActividadEntity getActividad() {
        return actividad;
    }

    public void setActividad(ActividadEntity actividad) {
        this.actividad = actividad;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}