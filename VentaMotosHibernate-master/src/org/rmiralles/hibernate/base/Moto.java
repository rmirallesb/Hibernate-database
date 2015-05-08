package org.rmiralles.hibernate.base;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Moto {
    private Vendedor vendedor;
    private List<Accesorio> accesorios;
    private Accesorio accesorio;
    private List<Cliente> clientes;
    private int id;
    private String marca;
    private String modelo;
    private String numerochasis;
    private String matricula;
    private Float preciomoto;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_vendedor", referencedColumnName = "id")
    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @ManyToMany
    @JoinTable(name = "moto_accesorio", catalog = "ventamotoshibernate", schema = "", joinColumns = @JoinColumn(name = "id_moto", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_accesorio", referencedColumnName = "id"))
    public List<Accesorio> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(List<Accesorio> accesorios) {
        this.accesorios = accesorios;
    }

    @ManyToOne
    @JoinColumn(name = "id_accesorio", referencedColumnName = "id")
    public Accesorio getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(Accesorio accesorio) {
        this.accesorio = accesorio;
    }

    @OneToMany(mappedBy = "moto")
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
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
    @Column(name = "marca")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Basic
    @Column(name = "modelo")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Basic
    @Column(name = "numerochasis")
    public String getNumerochasis() {
        return numerochasis;
    }

    public void setNumerochasis(String numerochasis) {
        this.numerochasis = numerochasis;
    }

    @Basic
    @Column(name = "matricula")
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Basic
    @Column(name = "preciomoto")
    public Float getPreciomoto() {
        return preciomoto;
    }

    public void setPreciomoto(Float preciomoto) {
        this.preciomoto = preciomoto;
    }

    @Basic
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moto moto = (Moto) o;

        if (id != moto.id) return false;
        if (fecha != null ? !fecha.equals(moto.fecha) : moto.fecha != null) return false;
        if (marca != null ? !marca.equals(moto.marca) : moto.marca != null) return false;
        if (matricula != null ? !matricula.equals(moto.matricula) : moto.matricula != null) return false;
        if (modelo != null ? !modelo.equals(moto.modelo) : moto.modelo != null) return false;
        if (numerochasis != null ? !numerochasis.equals(moto.numerochasis) : moto.numerochasis != null) return false;
        if (preciomoto != null ? !preciomoto.equals(moto.preciomoto) : moto.preciomoto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + (numerochasis != null ? numerochasis.hashCode() : 0);
        result = 31 * result + (matricula != null ? matricula.hashCode() : 0);
        result = 31 * result + (preciomoto != null ? preciomoto.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
