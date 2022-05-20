package edu.kalum.enrollment.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aspirante")
@NamedQueries({@NamedQuery(name = "Aspirante.findAll", query = "select a from Aspirante a order by a.apellidos")})
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement
public class Aspirante implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "no_expediente")
    private String noExpediente;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "examen_id")
    private String examenId;
    @Column(name = "estatus")
    private String estatus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrera_id", referencedColumnName = "carrera_id")
    private CarreraTecnica carreraTecnica;
    @Column(name = "jornada_id")
    private String jornadaId;

    @Override
    public String toString() {
        return "Aspirante{" +
                "noExpediente='" + noExpediente + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombres='" + nombres + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", examenId='" + examenId + '\'' +
                ", estatus='" + estatus + '\'' +
                ", carreraTecnica=" + carreraTecnica +
                ", jornadaId='" + jornadaId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aspirante aspirante = (Aspirante) o;
        return noExpediente.equals(aspirante.noExpediente) && apellidos.equals(aspirante.apellidos) && nombres.equals(aspirante.nombres) && direccion.equals(aspirante.direccion) && telefono.equals(aspirante.telefono) && email.equals(aspirante.email) && examenId.equals(aspirante.examenId) && estatus.equals(aspirante.estatus) && carreraTecnica.equals(aspirante.carreraTecnica) && jornadaId.equals(aspirante.jornadaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noExpediente, apellidos, nombres, direccion, telefono, email, examenId, estatus, carreraTecnica, jornadaId);
    }

}
