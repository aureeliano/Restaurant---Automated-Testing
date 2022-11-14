package controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import excepciones.CantHijosInvalida_Exception;
import excepciones.EdadInvalida_Exception;
import excepciones.NyARepetido_Exception;
import negocio.Sistema;
import vista.IVistaAdmin;
import vista.IVistaAgregaProducto;
import vista.IVistaAltaMozo;
import vista.IVistaGestionProductoAdmin;
import vista.IVistaModificaDatosPersonales;
import vista.IVistaRegistrarOperario;
import vista.Ventana;

public class ControladorVistaAgregaProducto implements ActionListener {

	private IVistaAgregaProducto vista = null;
	private Ventana ventana = null;
	private JPanel contentPane = null;
	
	public ControladorVistaAgregaProducto(IVistaAgregaProducto vista, Ventana ventana) {
		this.vista = vista;
		this.vista.addAcionListener(this);
		this.ventana = ventana;
		this.contentPane = this.ventana.getContentPane();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout) contentPane.getLayout();
		String comando = e.getActionCommand();

		if (comando.equalsIgnoreCase("CONFIRMAR")) {
			try {
				Sistema.getInstance().getFuncionalidadAdmin().agregaMozo(this.vista.getNyA(), this.vista.fechaNacimiento(), this.vista.getCantHijos(), this.vista.getEstado());
				JOptionPane.showMessageDialog(null, "Producto agregado con exito.");
				this.vista.limpiarVista();
				//System.out.println(Sistema.getInstance().getMozos().get("Juan Carlos").getNyA());
			} catch (EdadInvalida_Exception | CantHijosInvalida_Exception | NyARepetido_Exception e1) {
				this.vista.ventanaEmergente(e1.getMessage());
			}
		}
		else if (comando.equalsIgnoreCase("VOLVER")) 
			cl.show(contentPane, ventana.getVistaGestionMozoAdmin());
	}
	

}
