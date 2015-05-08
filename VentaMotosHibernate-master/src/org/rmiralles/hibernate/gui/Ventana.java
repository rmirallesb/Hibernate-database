package org.rmiralles.hibernate.gui;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.rmiralles.hibernate.base.Accesorio;
import org.rmiralles.hibernate.base.Cliente;
import org.rmiralles.hibernate.base.Moto;
import org.rmiralles.hibernate.base.Vendedor;
import org.rmiralles.hibernate.util.HibernateUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

public class Ventana {
    private JTabbedPane tpPestanas;
    private JPanel panel1;
    private JTextField tfNombreVendedor;
    private JTextField tfApellidoVendedor;
    private JTextField tfDniVendedor;
    private JTextField tfIdClienteVendedor;
    private JTextField tfProvinciaVendedor;
    private JTextField tfSalarioVendedor;
    private JTextField tfNombreAccesorio;
    private JTextField tfReferenciaAccesorio;
    private JTextField tfMarcaMoto;
    private JTextField tfModeloMoto;
    private JTextField tfNumChasisMoto;
    private JTextField tfMatriculaMoto;
    private JTextField tfPrecioMoto;
    private JComboBox cbVendedorMoto;
    private JComboBox cbAccesorioMoto;
    private JDateChooser dcFechaMoto;
    private JTextField tfNombreCliente;
    private JTextField tfApellidoCliente;
    private JTextField tfDniCliente;
    private JTextField tfDireccionCliente;
    private JTextField tfProvinciaCliente;
    private JTextField tfTelefonoCliente;
    private JComboBox cbMotoCliente;
    private JButton btEliminarVendedor;
    private JButton btModificarVendedor;
    private JButton btGuardarVendedor;
    private JButton btMostrarVendedor;
    private JButton btGuardarMoto;
    private JButton btModificarMoto;
    private JButton btEliminarMoto;
    private JButton btMostrarMoto;
    private JButton btGuardarCliente;
    private JButton btModificarCliente;
    private JButton btEliminarCliente;
    private JButton btMostrarCliente;
    private JButton btGuardarAccesorio;
    private JButton btModificarAccesorio;
    private JButton btEliminarAccesorio;
    private JButton btMostrarAccesorio;
    private JTextField tfBuscarVendedor;
    private JTextField tfBuscarMoto;
    private JTextField tfBuscarAccesorio;
    private JTextField tfBuscarCliente;
    private JTable tbCliente;
    private JTable tbAccesorio;
    private JTable tbMoto;
    private JTable tbVendedor;
    private JButton btNuevoVendedor;
    private JButton btNuevaMoto;
    private JButton btNuevoAccesorio;
    private JButton btNuevoCliente;

    private enum Pestana {
        CLIENTE, MOTO, VENDEDOR, ACCESORIO
    }

    private DefaultTableModel modeloTablaVendedores;
    private DefaultTableModel modeloTablaMotos;
    private DefaultTableModel modeloTablaAccesorios;
    private DefaultTableModel modeloTablaClientes;

    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDO = "apellido";
    public static final String DNI = "dni";
    public static final String IDCLIENTE = "idcliente";
    public static final String PROVINCIA = "provincia";
    public static final String SALARIO = "salario";
    public static final String MARCA = "marca";
    public static final String MODELO = "modelo";
    public static final String NUMEROCHASIS = "numerochasis";
    public static final String MATRICULA = "matricula";
    public static final String PRECIOMOTO = "preciomoto";
    public static final String FECHA = "fecha";
    public static final String DIRECCION = "direccion";
    public static final String TELEFONO = "telefono";
    public static final String REFERENCIA = "referencia";

    public Ventana(){
        conectar();

        modeloTablaVendedores = new DefaultTableModel();
        tbVendedor.setModel(modeloTablaVendedores);
        modeloTablaVendedores.addColumn(ID);
        modeloTablaVendedores.addColumn(NOMBRE);
        modeloTablaVendedores.addColumn(APELLIDO);
        modeloTablaVendedores.addColumn(DNI);
        modeloTablaVendedores.addColumn(IDCLIENTE);
        modeloTablaVendedores.addColumn(PROVINCIA);
        modeloTablaVendedores.addColumn(SALARIO);

        modeloTablaMotos = new DefaultTableModel();
        tbMoto.setModel(modeloTablaMotos);
        modeloTablaMotos.addColumn(MARCA);
        modeloTablaMotos.addColumn(MODELO);
        modeloTablaMotos.addColumn(NUMEROCHASIS);
        modeloTablaMotos.addColumn(MATRICULA);
        modeloTablaMotos.addColumn(PRECIOMOTO);
        modeloTablaMotos.addColumn(FECHA);
        refrescarComboVendedorMoto();
        refrescarComboAccesorioMoto();

        modeloTablaAccesorios = new DefaultTableModel();
        tbAccesorio.setModel(modeloTablaAccesorios);
        modeloTablaAccesorios.addColumn(ID);
        modeloTablaAccesorios.addColumn(NOMBRE);
        modeloTablaAccesorios.addColumn(REFERENCIA);

        modeloTablaClientes = new DefaultTableModel();
        tbCliente.setModel(modeloTablaClientes);
        modeloTablaClientes.addColumn(ID);
        modeloTablaClientes.addColumn(NOMBRE);
        modeloTablaClientes.addColumn(APELLIDO);
        modeloTablaClientes.addColumn(DNI);
        modeloTablaClientes.addColumn(DIRECCION);
        modeloTablaClientes.addColumn(PROVINCIA);
        modeloTablaClientes.addColumn(TELEFONO);
        refrescarComboMotoCliente();

        btNuevoVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoVendedor();
            }
        });
        btNuevaMoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaMoto();
            }
        });
        btNuevoAccesorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoAccesorio();
            }
        });
        btNuevoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoCliente();
            }
        });

        btGuardarVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarVendedor();
            }
        });
        btModificarVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarVendedor();
            }
        });
        btEliminarVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVendedor();
            }
        });
        tfBuscarVendedor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                buscarVendedor();
            }
        });
        tbVendedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarVendedor();
            }
        });

        btGuardarMoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMoto();
            }
        });
        btModificarMoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarMoto();
            }
        });
        btEliminarMoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarMoto();
            }
        });
        tfBuscarMoto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                buscarMoto();
            }
        });
        tbMoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarMoto();
            }
        });

        btGuardarAccesorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarAccesorio();
            }
        });
        btModificarAccesorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarAccesorio();
            }
        });
        btEliminarAccesorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             eliminarAccesorio();
            }
        });
        tfBuscarAccesorio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                buscarAccesorio();
            }
        });
        tbAccesorio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarAccesorio();
            }
        });

        btGuardarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }
        });
        btModificarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarCliente();
            }
        });
        btEliminarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });
        tfBuscarCliente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                buscarCliente();
            }
        });
        tbCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarCliente();
            }
        });
        tpPestanas.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                cargarPestanaActual();
            }
        });

        cargarVendedor();
        mostrarVendedor();
    }

    public void conectar(){
        try {
            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    private Pestana getPestanaActual() {
        int indice = tpPestanas.getSelectedIndex();
        return Pestana.values()[indice];
    }

    private void cargarPestanaActual(){
        switch (getPestanaActual()){
            case CLIENTE:
                cargarCliente();
                mostrarCliente();
                refrescarComboMotoCliente();
                refrescarComboVendedorMoto();
                refrescarComboAccesorioMoto();
                break;
            case MOTO:
                cargarMoto();
                mostrarMoto();
                refrescarComboMotoCliente();
                refrescarComboVendedorMoto();
                refrescarComboAccesorioMoto();
                break;
            case VENDEDOR:
                cargarVendedor();
                mostrarVendedor();
                refrescarComboMotoCliente();
                refrescarComboVendedorMoto();
                refrescarComboAccesorioMoto();
                break;
            case ACCESORIO:
                cargarAccesorio();
                mostrarAccesorio();
                refrescarComboMotoCliente();
                refrescarComboVendedorMoto();
                refrescarComboAccesorioMoto();
                break;
        }
    }

    public void nuevoVendedor(){
        tfNombreVendedor.setText("");
        tfApellidoVendedor.setText("");
        tfDniVendedor.setText("");
        tfIdClienteVendedor.setText("");
        tfProvinciaVendedor.setText("");
        tfSalarioVendedor.setText("");
    }

    public void nuevaMoto(){
        tfMarcaMoto.setText("");
        tfModeloMoto.setText("");
        tfNumChasisMoto.setText("");
        tfMatriculaMoto.setText("");
        tfPrecioMoto.setText("");
        dcFechaMoto.setDate(null);
    }

    public void nuevoAccesorio(){
        tfNombreAccesorio.setText("");
        tfReferenciaAccesorio.setText("");
    }

    public void nuevoCliente(){
        tfNombreCliente.setText("");
        tfApellidoCliente.setText("");
        tfDniCliente.setText("");
        tfDireccionCliente.setText("");
        tfProvinciaCliente.setText("");
        tfTelefonoCliente.setText("");
    }

    public void guardarVendedor(){
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(tfNombreVendedor.getText());
        vendedor.setApellido(tfApellidoVendedor.getText());
        vendedor.setDni(tfDniVendedor.getText());
        vendedor.setIdcliente(tfIdClienteVendedor.getText());
        vendedor.setProvincia(tfProvinciaVendedor.getText());
        vendedor.setSalario(Integer.valueOf(tfSalarioVendedor.getText()));

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(vendedor);
        session.getTransaction().commit();
        session.close();

        cargarVendedor();
        mostrarVendedor();
    }

    public void guardarMoto(){
        Moto moto = new Moto();
        moto.setMarca(tfMarcaMoto.getText());
        moto.setModelo(tfModeloMoto.getText());
        moto.setNumerochasis(tfNumChasisMoto.getText());
        moto.setMatricula(tfMatriculaMoto.getText());
        moto.setPreciomoto(Float.valueOf(tfPrecioMoto.getText()));
        moto.setFecha(new Date(dcFechaMoto.getDate().getTime()));
        moto.setVendedor((Vendedor) cbVendedorMoto.getSelectedItem());
        moto.setAccesorio((Accesorio) cbAccesorioMoto.getSelectedItem());

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(moto);
        session.getTransaction().commit();
        session.close();

        cargarMoto();
        mostrarMoto();
    }

    public void guardarAccesorio(){
        Accesorio accesorio = new Accesorio();
        accesorio.setNombre(tfNombreAccesorio.getText());
        accesorio.setRefencia(tfReferenciaAccesorio.getText());

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(accesorio);
        session.getTransaction().commit();
        session.close();

        cargarAccesorio();
        mostrarAccesorio();
    }

    public void guardarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNombre(tfNombreCliente.getText());
        cliente.setApellido(tfApellidoCliente.getText());
        cliente.setDni(tfDniCliente.getText());
        cliente.setDireccion(tfDireccionCliente.getText());
        cliente.setProvincia(tfProvinciaCliente.getText());
        cliente.setTelefono(Integer.valueOf(tfTelefonoCliente.getText()));
        cliente.setMoto((Moto) cbMotoCliente.getSelectedItem());

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(cliente);
        session.getTransaction().commit();
        session.close();

        cargarCliente();
        mostrarCliente();
    }

    public void modificarVendedor(){
        Vendedor vendedor = getVendedorSeleccionado();
        if (vendedor == null)
            return;
        vendedor.setNombre(tfNombreVendedor.getText());
        vendedor.setApellido(tfApellidoVendedor.getText());
        vendedor.setDni(tfDniVendedor.getText());
        vendedor.setIdcliente(tfIdClienteVendedor.getText());
        vendedor.setProvincia(tfProvinciaVendedor.getText());
        vendedor.setSalario(Integer.valueOf(tfSalarioVendedor.getText()));

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.update(vendedor);
        session.getTransaction().commit();
        session.close();

        cargarVendedor();
        mostrarVendedor();
    }

    public void modificarMoto(){
        Moto moto = getMotoSeleccionada();
        if (moto == null)
            return;

        moto.setMarca(tfMarcaMoto.getText());
        moto.setModelo(tfModeloMoto.getText());
        moto.setNumerochasis(tfNumChasisMoto.getText());
        moto.setMatricula(tfMatriculaMoto.getText());
        moto.setPreciomoto(Float.valueOf(tfPrecioMoto.getText()));
        moto.setFecha(new Date(dcFechaMoto.getDate().getTime()));
        moto.setVendedor((Vendedor) cbVendedorMoto.getSelectedItem());
        moto.setAccesorio((Accesorio) cbAccesorioMoto.getSelectedItem());

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.update(moto);
        session.getTransaction().commit();
        session.close();

        cargarMoto();
        mostrarMoto();
    }

    public void modificarAccesorio(){
        Accesorio accesorio = getAccesorioSeleccionado();
        if (accesorio == null)
            return;

        accesorio.setNombre(tfNombreAccesorio.getText());
        accesorio.setRefencia(tfReferenciaAccesorio.getText());

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.update(accesorio);
        session.getTransaction().commit();
        session.close();

        cargarAccesorio();
        mostrarAccesorio();
    }

    public void modificarCliente(){
        Cliente cliente = getClienteSeleccionado();
        if (cliente == null)
            return;

        cliente.setNombre(tfNombreCliente.getText());
        cliente.setApellido(tfApellidoCliente.getText());
        cliente.setDni(tfDniCliente.getText());
        cliente.setDireccion(tfDireccionCliente.getText());
        cliente.setProvincia(tfProvinciaCliente.getText());
        cliente.setTelefono(Integer.valueOf(tfTelefonoCliente.getText()));
        cliente.setMoto((Moto) cbMotoCliente.getSelectedItem());

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.update(cliente);
        session.getTransaction().commit();
        session.close();

        cargarCliente();
        mostrarCliente();
    }

    public void eliminarVendedor(){
        Vendedor vendedor = getVendedorSeleccionado();
        if (vendedor == null)
            return;

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(vendedor);
        session.getTransaction().commit();
        session.close();

        cargarVendedor();
        mostrarVendedor();
    }

    public void eliminarMoto(){
        Moto moto = getMotoSeleccionada();
        if (moto == null)
            return;

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(moto);
        session.getTransaction().commit();
        session.close();

        cargarMoto();
        mostrarMoto();
    }

    public void eliminarAccesorio(){
        Accesorio accesorio = getAccesorioSeleccionado();
        if (accesorio == null)
            return;

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(accesorio);
        session.getTransaction().commit();
        session.close();

        cargarAccesorio();
        mostrarAccesorio();
    }

    public void eliminarCliente(){
        Cliente cliente = getClienteSeleccionado();
        if (cliente == null)
            return;

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(cliente);
        session.getTransaction().commit();
        session.close();

        cargarCliente();
        mostrarCliente();
    }

    public void buscarVendedor(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Vendedor");
        List<Vendedor> listaVendedores = query.list();

        modeloTablaVendedores.setNumRows(0);
        for(Vendedor vendedor : listaVendedores){
            if(vendedor.getNombre().contains(tfBuscarVendedor.getText()) || vendedor.getApellido().contains(tfBuscarVendedor.getText())) {
                Object[] fila = new Object[]{vendedor.getId(), vendedor.getNombre(), vendedor.getApellido(),
                        vendedor.getDni(),vendedor.getIdcliente(),vendedor.getProvincia(),vendedor.getSalario()};
                modeloTablaVendedores.addRow(fila);
            }
        }

    }

    public void buscarMoto(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Moto");
        List<Moto> listaMotos = query.list();

        modeloTablaMotos.setNumRows(0);
        for (Moto moto : listaMotos){
            if(moto.getMarca().contains(tfBuscarMoto.getText()) || moto.getModelo().contains(tfBuscarMoto.getText())) {
                Object[] fila = new Object[]{moto.getId(), moto.getMarca(), moto.getModelo(), moto.getNumerochasis(),
                        moto.getMatricula(), moto.getPreciomoto(), moto.getFecha()};
                modeloTablaMotos.addRow(fila);
            }
        }
    }

    public void buscarAccesorio(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Accesorio");
        List<Accesorio> listaAccesorios = query.list();

        modeloTablaAccesorios.setNumRows(0);
        for (Accesorio accesorio : listaAccesorios){
            if(accesorio.getNombre().contains(tfBuscarAccesorio.getText()) || accesorio.getRefencia().contains(tfBuscarAccesorio.getText())) {
                Object[] fila = new Object[]{accesorio.getId(), accesorio.getNombre(), accesorio.getRefencia()};
                modeloTablaAccesorios.addRow(fila);
            }
        }
    }

    public void buscarCliente(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Cliente");
        List<Cliente> listaClientes = query.list();

        modeloTablaClientes.setNumRows(0);
        for (Cliente cliente : listaClientes) {
            if(cliente.getNombre().contains(tfApellidoCliente.getText()) || cliente.getApellido().contains(tfBuscarCliente.getText())) {
                Object[] fila = new Object[]{cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getDni(),
                        cliente.getDireccion(), cliente.getProvincia(), cliente.getTelefono()};
                modeloTablaClientes.addRow(fila);
            }
        }
    }

    public void cargarVendedor(){
        Vendedor vendedor = getVendedorSeleccionado();
        if (vendedor == null)
            return;
        tfNombreVendedor.setText(vendedor.getNombre());
        tfApellidoVendedor.setText(vendedor.getApellido());
        tfDniVendedor.setText(vendedor.getDni());
        tfIdClienteVendedor.setText(vendedor.getIdcliente());
        tfProvinciaVendedor.setText(vendedor.getProvincia());
        tfSalarioVendedor.setText(String.valueOf(vendedor.getSalario()));
    }

    public void cargarMoto(){
        Moto moto = getMotoSeleccionada();
        if (moto == null)
            return;
        tfMarcaMoto.setText(moto.getMarca());
        tfModeloMoto.setText(moto.getModelo());
        tfNumChasisMoto.setText(moto.getNumerochasis());
        tfMatriculaMoto.setText(moto.getMatricula());
        tfPrecioMoto.setText(String.valueOf(moto.getPreciomoto()));
        dcFechaMoto.setDate(moto.getFecha());

        refrescarComboVendedorMoto();
        cbVendedorMoto.setSelectedItem(moto.getVendedor());
        refrescarComboAccesorioMoto();
        cbAccesorioMoto.setSelectedItem(moto.getAccesorio());
    }

    public void cargarAccesorio(){
        Accesorio accesorio = getAccesorioSeleccionado();
        if (accesorio == null)
            return;
        tfNombreAccesorio.setText(accesorio.getNombre());
        tfReferenciaAccesorio.setText(accesorio.getRefencia());
    }

    public void cargarCliente(){
        Cliente cliente = getClienteSeleccionado();
        if (cliente == null)
            return;
        tfNombreCliente.setText(cliente.getNombre());
        tfApellidoCliente.setText(cliente.getApellido());
        tfDniCliente.setText(cliente.getDni());
        tfDireccionCliente.setText(cliente.getDireccion());
        tfProvinciaCliente.setText(cliente.getProvincia());
        tfTelefonoCliente.setText(String.valueOf(cliente.getTelefono()));

        refrescarComboMotoCliente();
        cbMotoCliente.setSelectedItem(cliente.getMoto());
    }

    public void mostrarVendedor(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Vendedor");
        List<Vendedor> listaVendedores = query.list();

        modeloTablaVendedores.setNumRows(0);
        for(Vendedor vendedor : listaVendedores){
            Object[] fila = new Object[]{vendedor.getId(),vendedor.getNombre(), vendedor.getApellido(),
            vendedor.getDni(),vendedor.getIdcliente(),vendedor.getProvincia(),vendedor.getSalario()};
            modeloTablaVendedores.addRow(fila);
        }
    }

    public void mostrarMoto(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Moto");
        List<Moto> listaMotos = query.list();

        modeloTablaMotos.setNumRows(0);
        for (Moto moto : listaMotos){
            Object[] fila = new Object[]{moto.getId(),moto.getMarca(),moto.getModelo(),moto.getNumerochasis(),
            moto.getMatricula(),moto.getPreciomoto(),moto.getFecha()};
            modeloTablaMotos.addRow(fila);
        }
    }

    public void mostrarAccesorio(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Accesorio");
        List<Accesorio> listaAccesorios = query.list();

        modeloTablaAccesorios.setNumRows(0);
        for (Accesorio accesorio : listaAccesorios){
            Object[] fila = new Object[]{accesorio.getId(),accesorio.getNombre(),accesorio.getRefencia()};
            modeloTablaAccesorios.addRow(fila);
        }
    }

    public void mostrarCliente(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Cliente");
        List<Cliente> listaClientes = query.list();

        modeloTablaClientes.setNumRows(0);
        for (Cliente cliente : listaClientes) {
            Object[] fila = new Object[]{cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getDni(),
                    cliente.getDireccion(), cliente.getProvincia(), cliente.getTelefono()};
            modeloTablaClientes.addRow(fila);
        }
    }

    public Vendedor getVendedorSeleccionado(){
        int filaSeleccionada = 0;
        filaSeleccionada = tbVendedor.getSelectedRow();

        if(filaSeleccionada == -1)
            return null;
        int id = (Integer) tbVendedor.getValueAt(filaSeleccionada, 0);

        Vendedor vendedor = (Vendedor) HibernateUtil.getCurrentSession().get(Vendedor.class, id);
        return vendedor;
    }

    public Moto getMotoSeleccionada(){
        int filaSeleccionada = 0;
        filaSeleccionada = tbMoto.getSelectedRow();

        if(filaSeleccionada == -1)
            return null;
        int id = (Integer) tbMoto.getValueAt(filaSeleccionada, 0);

        Moto moto = (Moto) HibernateUtil.getCurrentSession().get(Moto.class, id);
        return moto;
    }

    public Accesorio getAccesorioSeleccionado(){
        int filaSeleccionada = 0;
        filaSeleccionada = tbAccesorio.getSelectedRow();

        if(filaSeleccionada == -1)
            return null;
        int id = (Integer) tbAccesorio.getValueAt(filaSeleccionada, 0);

        Accesorio accesorio = (Accesorio) HibernateUtil.getCurrentSession().get(Accesorio.class, id);
        return accesorio;
    }

    public Cliente getClienteSeleccionado(){
        int filaSeleccionada = 0;
        filaSeleccionada = tbCliente.getSelectedRow();

        if(filaSeleccionada == -1)
            return null;
        int id = (Integer) tbCliente.getValueAt(filaSeleccionada, 0);

        Cliente cliente = (Cliente) HibernateUtil.getCurrentSession().get(Cliente.class, id);
        return cliente;
    }

    public void refrescarComboVendedorMoto(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Vendedor");
        List<Vendedor> listaVendedores = query.list();

        cbVendedorMoto.removeAllItems();
        cbVendedorMoto.addItem(null);
        for(Vendedor vendedor : listaVendedores)
            cbVendedorMoto.addItem(vendedor);
        cbVendedorMoto.setSelectedIndex(0);
    }

    public void refrescarComboAccesorioMoto(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Accesorio");
        List<Accesorio> listaAccesorios = query.list();

        cbAccesorioMoto.removeAllItems();
        cbAccesorioMoto.addItem(null);
        for(Accesorio accesorio : listaAccesorios)
            cbAccesorioMoto.addItem(accesorio);
        cbAccesorioMoto.setSelectedIndex(0);
    }

    public void refrescarComboMotoCliente(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Moto");
        List<Moto> listaMoto = query.list();

        cbMotoCliente.removeAllItems();
        cbMotoCliente.addItem(null);
        for(Moto moto : listaMoto)
            cbMotoCliente.addItem(moto);
        cbMotoCliente.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Practica Hibernate - Raúl Miralles");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
    }
}
