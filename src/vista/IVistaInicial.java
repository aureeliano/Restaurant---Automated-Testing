package vista;

import java.awt.event.ActionListener;

public interface IVistaInicial {
	
	void addActionListener(ActionListener actionListener);
	void limpiarVista();
	void ventanaEmergente(String mensaje);
	String getUsuario();
	String getContrasena();
	
}
