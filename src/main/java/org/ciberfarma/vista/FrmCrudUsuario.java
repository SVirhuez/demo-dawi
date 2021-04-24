package org.ciberfarma.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmCrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JTextField txtFecha;
	private JTextField txtTipo;
	private JTextField txtEstado;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CRUD USUARIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setBounds(179, 11, 126, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo");
		lblNewLabel_1.setBounds(10, 44, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(10, 70, 63, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido");
		lblNewLabel_3.setBounds(10, 95, 63, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Clave");
		lblNewLabel_4.setBounds(188, 120, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Usuario");
		lblNewLabel_5.setBounds(10, 120, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Fch. Nac");
		lblNewLabel_6.setBounds(10, 145, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Tipo");
		lblNewLabel_7.setBounds(10, 170, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Estado");
		lblNewLabel_8.setBounds(188, 170, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		JButton btnRegistrar = new JButton("Agregar Usuario");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			registro();
			}
		});
		btnRegistrar.setBounds(120, 198, 185, 23);
		contentPane.add(btnRegistrar);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(81, 41, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(81, 67, 153, 20);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(81, 92, 153, 20);
		contentPane.add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(81, 117, 86, 20);
		contentPane.add(txtUsuario);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(249, 117, 86, 20);
		contentPane.add(txtClave);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(81, 142, 86, 20);
		contentPane.add(txtFecha);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(81, 167, 86, 20);
		contentPane.add(txtTipo);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(249, 167, 86, 20);
		contentPane.add(txtEstado);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultar();
			}
		});
		btnNewButton.setBounds(249, 40, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Listado");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			listado();
			}
		});
		btnNewButton_1.setBounds(249, 66, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 414, 177);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
	}

	protected void listado() {
		// Obtener un liustado de los usuarios
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();
		//=======================Cambia para agregar parametros ====================
		//TypedQuery<Usuario> consulta = manager.createNamedQuery("findAll", Usuario.class);
		//List<Usuario> listaUsu = consulta.getResultList();
		
		List<Usuario> listaUsu;
		if(txtTipo.getText().isEmpty()) {
			listaUsu= manager.createNamedQuery("findAll", Usuario.class).getResultList();
			
		}else {
			int tipo = Integer.parseInt(txtTipo.getText());
			
			 listaUsu = manager.createNamedQuery("Usuario.findAllxTipo", Usuario.class).
					setParameter("xtipo",tipo).getResultList();
		}
		txtS.setText("Listado de Usuarios\n");
		// Muestro el listado al txtArea
		for(Usuario u : listaUsu) {
			
			txtS.append(u.getNombre() + "\t"+ u.getApellido()+"\n");
			}
		
	}

	protected void consultar() {
		 int codigo = Integer.parseInt(txtCodigo.getText());
			//buscar el codigo en los usuarios, si existe muestra los datos, sino avisa
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
			// 2. manejador de entidades
			EntityManager manager = fabrica.createEntityManager();
			Usuario u = manager.find(Usuario.class, codigo);
			if(u == null) {
				JOptionPane.showMessageDialog(this,"Usuario No Encontrado");
			}else {
				txtNombre.setText(u.getNombre());
				txtApellido.setText(u.getApellido());
				txtUsuario.setText(u.getUsuario());
				txtClave.setText(u.getClave());
				txtFecha.setText(u.getFnacim());
				txtTipo.setText(u.getTipo()+"");
				txtEstado.setText(u.getEstado()+"");
			}
	}

	void registro() {
			// leer datos - entradas
		 	//int codigo = Integer.parseInt(txtCodigo.getText());
			String nombre = txtNombre.getText();
			String apellido = txtApellido.getText();
			String usuario = txtUsuario.getText();
			String clave = txtClave.getText();
			String fecha = txtFecha.getText();
			int tipo = Integer.parseInt(txtTipo.getText());
			int estado = Integer.parseInt(txtEstado.getText());

			Usuario u = new Usuario();
			//u.setCodigo(codigo);
			u.setNombre(nombre);
			u.setApellido(apellido);
			u.setUsuario(usuario);
			u.setClave(clave);
			u.setFnacim(fecha);
			u.setTipo(tipo);
			u.setEstado(estado);
			
			
			try {
				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
				// 2. manejador de entidades
				EntityManager manager = fabrica.createEntityManager();
				
				// 3. empezar mi transaccion
				manager.getTransaction().begin();
				// proceso a realizar (persistencia)
				manager.persist(u);
				//manager.merge(u);
				// 4. Confirmar la transaccion
				manager.getTransaction().commit();
				JOptionPane.showMessageDialog(this,"Usuario Registrado");
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}
}
