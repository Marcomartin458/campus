package es.academia.campushub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "grupo")

public class GrupoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grupoId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nivel", nullable = false, length = 50)
    private String nivel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tutorDocenteId", nullable = false)
    private DocenteEntity tutorDocente;

    public GrupoEntity(Long grupoId, String nombre, String nivel, DocenteEntity tutorDocente) {
        this.grupoId = grupoId;
        this.nombre = nombre;
        this.nivel = nivel;
        this.tutorDocente = tutorDocente;
    }

    public GrupoEntity() {
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public DocenteEntity getTutorDocente() {
        return tutorDocente;
    }

    public void setTutorDocente(DocenteEntity tutorDocente) {
        this.tutorDocente = tutorDocente;
    }
}
