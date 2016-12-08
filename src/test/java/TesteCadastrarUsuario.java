import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import modelo.Usuario;
import utilitarios.ListaUsuario;

public class TesteCadastrarUsuario {
	
private ListaUsuario dao = new ListaUsuario();
	
	@Test
	public void testAddUsuario() throws Exception{
		
		Usuario usuario = new Usuario();
		
		usuario.setNome("Rafael");
		usuario.setSenha("123");
		usuario.setEmail("zanco@gamil.com");
		usuario.setCpf("00500500500");
		usuario.setDataNascimento(LocalDate.of(1981, Month.MAY, 23));
		
		dao.inserir(usuario);
		List<Usuario> listaUsuarios = dao.listarUsuarios();
		//Verifica a Inclusao
		Assert.assertEquals(1, listaUsuarios.size());
	}
}
