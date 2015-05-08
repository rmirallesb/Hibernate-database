package org.rmiralles.hibernate.base;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vendedor {
    private List<Moto> motos;
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String idcliente;
    private String provincia;
    private Integer salario;

    @OneToMany(mappedBy = "vendedor")
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
    @Column(name = "apellido")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Basic
    @Column(name = "dni")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "idcliente")
    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    @Basic
    @Column(name = "provincia")
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Basic
    @Column(name = "salario")
    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vendedor vendedor = (Vendedor) o;

        if (id != vendedor.id) return false;
        if (apellido != null ? !apellido.equals(vendedor.apellido) : vendedor.apellido != null) return false;
        if (dni != null ? !dni.equals(vendedor.dni) : vendedor.dni != null) return false;
        if (idcliente != null ? !idcliente.equals(vendedor.idcliente) : vendedor.idcliente != null) return false;
        if (nombre != null ? !nombre.equals(vendedor.nombre) : vendedor.nombre != null) return false;
        if (provincia != null ? !provincia.equals(vendedor.provincia) : vendedor.provincia != null) return false;
        if (salario != null ? !salario.equals(vendedor.salario) : vendedor.salario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        result = 31 * result + (idcliente != null ? idcliente.hashCode() : 0);
        result = 31 * result + (provincia != null ? provincia.hashCode() : 0);
        result = 31 * result + (salario != null ? salario.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
