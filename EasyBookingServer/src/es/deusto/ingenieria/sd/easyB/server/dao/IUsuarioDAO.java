package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;


import es.deusto.ingenieria.sd.easyB.server.data.Usuario;

public interface IUsuarioDAO {
	public void storeUsuario(Usuario usuario);
	public Usuario getUsuario(String nombre); //este metodo igual habria que eliminarlo.Porque puede haber mas de un usuario con un mismo nombre
	public void updateUsuario(Usuario usuario);
	public ArrayList<Usuario> getUsuarios();
	public void deleteAllUsuarios(); // duda en este metodo tambn.

}
