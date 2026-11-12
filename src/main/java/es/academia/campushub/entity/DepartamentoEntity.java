package es.academia.campushub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departamentos")
public class DepartamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DepartamentoId;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "departamento")
    private List<DocenteEntity> docentes = new ArrayList<>();

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(Long departamentoId, String nombre) {
        this.DepartamentoId = departamentoId;
        this.nombre = nombre;
    }

    public Long getDepartamentoId() {
        return DepartamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.DepartamentoId = departamentoId;
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
