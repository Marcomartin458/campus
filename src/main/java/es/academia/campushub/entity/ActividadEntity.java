package es.academia.campushub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actividades")

public class ActividadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actividadId;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad", nullable = false)
    private TipoActividad tipoActividad;

    @NotNull
    @Column(name = "fecha_prevista", nullable = false)
    private LocalDate fechaPrevista;

    @Min(1)
    @Column(name = "numero_maximo_participantes", nullable = false)
    private int numeroMaximoParticipantes;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "docenteResponsableId", nullable = false)
    private DocenteEntity docenteResponsable;

    @ManyToMany
    @JoinTable(
        name = "actividad_etiqueta",
        joinColumns = @JoinColumn(name = "actividad_id"),
        inverseJoinColumns = @JoinColumn(name = "etiqueta_id"),
        uniqueConstraints =
            @UniqueConstraint(columnNames = {"actividad_id", "etiqueta_id"}))
    private Set<EtiquetaEntity> etiquetas = new HashSet<>();

    public Set<EtiquetaEntity> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<EtiquetaEntity> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public void addEtiqueta(EtiquetaEntity etiqueta) {
        etiquetas.add(etiqueta);
    }

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
        this.actividadId = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoActividad = tipoActividad;
        this.fechaPrevista = fechaPrevista;
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
        this.docenteResponsable = docenteResponsable;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long id) {
        this.actividadId = id;
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
        TALLER, CHARLA, VISITA, JORNADA
    }
}
