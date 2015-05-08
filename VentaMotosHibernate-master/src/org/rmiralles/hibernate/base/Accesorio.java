package org.rmiralles.hibernate.base;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Raul-Laptop on 23/02/2015.
 */
@Entity
public class Accesorio {
    private List<Moto> motoss;
    private List<Moto> motos;
    private int id;
    private String nombre;
    private String refencia;

    @ManyToMany(mappedBy = "accesorios")
    public List<Moto> getMotoss() {
        return motoss;
    }

    public void setMotoss(List<Moto> motoss) {
        this.motoss = motoss;
    }

    @OneToMany(mappedBy = "accesorio")
    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "refencia")
    public String getRefencia() {
        return refencia;
    }

    public void setRefencia(String refencia) {
        this.refencia = refencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accesorio accesorio = (Accesorio) o;

        if (id != accesorio.id) return false;
        if (nombre != null ? !nombre.equals(accesorio.nombre) : accesorio.nombre != null) return false;
        if (refencia != null ? !refencia.equals(accesorio.refencia) : accesorio.refencia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (refencia != null ? refencia.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Accesorio{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
