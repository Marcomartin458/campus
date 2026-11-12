package es.academia.campushub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "actividades")
public class ActividadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ActividadId;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad", nullable = false)
    private TipoActividad tipoActividad;

    @NotNull
    @Column(name = "fecha_prevista", nullable = false)
    private LocalDate fechaPrevista;

    @Min(1)
    @Column(name = "numero_maximo_participantes", nullable = false)
    private int numeroMaximoParticipantes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "docenteResponsableId", nullable = false)
    private DocenteEntity docenteResponsable;

    public ActividadEntity() {
    }

    public ActividadEntity(
            Long id,
            String titulo,
            String descripcion,
            TipoActividad tipoActividad,
            LocalDate fechaPrevista,
            int numeroMaximoParticipantes,
            DocenteEntity docenteResponsable) {
        this.ActividadId = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoActividad = tipoActividad;
        this.fechaPrevista = fechaPrevista;
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
        this.docenteResponsable = docenteResponsable;
    }

    public Long getActividadId() {
        return ActividadId;
    }

    public void setActividadId(Long id) {
        this.ActividadId = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public LocalDate getFechaPrevista() {
        return fechaPrevista;
    }

    public void setFechaPrevista(LocalDate fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
    }

    public int getNumeroMaximoParticipantes() {
        return numeroMaximoParticipantes;
    }

    public void setNumeroMaximoParticipantes(int numeroMaximoParticipantes) {
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
    }

    public DocenteEntity getDocenteResponsable() {
        return docenteResponsable;
    }

    public void setDocenteResponsable(DocenteEntity docenteResponsable) {
        this.docenteResponsable = docenteResponsable;
    }

    public enum TipoActividad {
        TALLER_PRACTICO,
        CHARLA,
        VISITA_A_EMPRESA,
        JORNADA_TECNOLOGICA
    }
}
