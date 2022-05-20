package edu.kalum.enrollment.models.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carrera_tecnica")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({ @NamedQuery(name="CarreraTecnica.findAll", query = "select ct from CarreraTecnica ct order by ct.carreraTecnica")})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CarreraTecnica implements Serializable {
    @Id
    @Column(name = "carrera_id")
    @Basic(optional = false)
    private String carreraId;
    @Column(name = "carrera_tecnica")
    private String carreraTecnica;
    @XmlTransient
    @OneToMany(mappedBy = "carreraTecnica", cascade = CascadeType.ALL)
    private List<Aspirante> aspirantes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarreraTecnica that = (CarreraTecnica) o;
        return carreraId.equals(that.carreraId) && carreraTecnica.equals(that.carreraTecnica) && aspirantes.equals(that.aspirantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carreraId, carreraTecnica, aspirantes);
    }

    @Override
    public String toString() {
        return "CarreraTecnica{" +
                "carreraId='" + carreraId + '\'' +
                ", carreraTecnica='" + carreraTecnica + '\'' +
                ", aspirantes=" + aspirantes +
                '}';
    }
}