package es.academia.campushub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "grupo")
public class GrupoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GrupoId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nivel")
    private String nivel;

    @OneToOne
    @JoinColumn(name = "tutorDocenteId", updatable = false, nullable = false)
    private DocenteEntity tutorDocente;

    public GrupoEntity(Long GrupoId, String nombre, String nivel, DocenteEntity tutorDocente) {
        this.GrupoId = GrupoId;
        this.nombre = nombre;
        this.nivel = nivel;
        this.tutorDocente = tutorDocente;
    }

    public GrupoEntity() {
    }

    public Long getGrupoId() {
        return GrupoId;
    }

    public void setGrupoId(Long GrupoId) {
        this.GrupoId = GrupoId;
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

}
