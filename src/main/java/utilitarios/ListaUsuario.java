package utilitarios;

import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class ListaUsuario {
	
	public static List<Usuario> listaUsuarios = new ArrayList<Usuario>();

	public boolean inserir(Usuario usuario) {
		this.listaUsuarios.add(usuario);
		return true;
	}

	public List<Usuario> listarUsuarios() {
		return listaUsuarios;
	}

}
