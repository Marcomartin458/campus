package es.academia.campushub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departamentos")

public class DepartamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departamentoId;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<DocenteEntity> docentes = new ArrayList<>();

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(Long departamentoId, String nombre) {
        this.departamentoId = departamentoId;
        this.nombre = nombre;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DocenteEntity> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<DocenteEntity> docentes) {
        this.docentes = docentes;
    }
}
