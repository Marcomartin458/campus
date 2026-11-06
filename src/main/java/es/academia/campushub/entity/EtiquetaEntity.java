package es.academia.campushub.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "etiquetas")
public class EtiquetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    public EtiquetaEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public EtiquetaEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
