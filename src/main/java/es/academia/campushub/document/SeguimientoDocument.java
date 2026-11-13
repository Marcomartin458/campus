package es.academia.campushub.document;

import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("seguimientos")
public class SeguimientoDocument {
    @Id private String id;

    private Long actividadId;
    private String tipo;
    private Map<String, Object> datos;
    private String creadoEn;

    public SeguimientoDocument() {}

    public SeguimientoDocument(
            Long actividadId, String tipo, Map<String, Object> datos, String creadoEn) {
        this.actividadId = actividadId;
        this.tipo = tipo;
        this.datos = datos;
        this.creadoEn = creadoEn;
    }

    public String getId() {
        return id;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public String getTipo() {
        return tipo;
    }

    public Map<String, Object> getDatos() {
        return datos;
    }

    public String getCreadoEn() {
        return creadoEn;
    }
}