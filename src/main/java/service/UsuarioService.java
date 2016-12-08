package service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import modelo.Usuario;

import utilitarios.ListaUsuario;

@Path("/usuario")
@ApplicationPath("/resource")
public class UsuarioService extends Application {

	ListaUsuario listaUsuario = new ListaUsuario();
	Usuario usuario = new Usuario();

	@POST
	@Path("/cadastrarUsuario")
	@Consumes("application/json")
	public Response addUsuario(
			@FormParam("nome") String nome, 
			@FormParam("senha") String senha,
			@FormParam("email") String email, 
			@FormParam("cpf") String cpf,
			@FormParam("dataNascimento") LocalDate dataNascimento) {

		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		usuario.setCpf(cpf);
		usuario.setDataNascimento(dataNascimento);

		if (usuario.getCpf().matches("(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")) {
			if (ListaUsuario.listaUsuarios.stream().anyMatch(user -> user.getCpf().equals(usuario.getCpf()))) {
				ListaUsuario.listaUsuarios.add(usuario);
				return Response.status(Response.Status.OK).entity(Response.Status.OK.toString()).build();
			} else {
				return Response.status(Response.Status.FOUND).encoding(Response.Status.FOUND.toString()).build();
			}
		} else {
			return Response.status(Response.Status.FOUND).encoding(Response.Status.FOUND.toString()).build();
		}
	}

	@GET
	@Path("/realizarLogin/{email}/{senha}")
	public Response acessoAplicacao(
			@PathParam("email") String email, 
			@PathParam("senha") String senha) {

		if (ListaUsuario.listaUsuarios.stream().anyMatch(usuario -> usuario.getEmail().equals(usuario.getEmail()))) {
			if (ListaUsuario.listaUsuarios.stream().anyMatch(usuario -> usuario.getSenha().equals(usuario.getSenha()))) {
				return Response.status(Response.Status.OK).entity(Response.Status.OK.toString()).build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED).entity(Response.Status.UNAUTHORIZED.toString())
						.build();
			}
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity(Response.Status.UNAUTHORIZED.toString())
					.build();
		}
	}

	@GET
	@Path("/buscarPorNome/{nome}")
	@Produces("application/json")
	public Response buscarPorNome(@PathParam("nome") String nome) {
		return Response.status(200).entity(new GenericEntity<List<Usuario>>(ListaUsuario.listaUsuarios.stream()
				.filter(usuario -> usuario.getNome().equals(nome)).collect(Collectors.toList())) {
		}).build();
	}

	@GET
	@Path("/buscarPorCPF/{cpf}")
	@Produces("application/json")
	public Response buscarCPF(@PathParam("cpf") String cpf) {

		return Response.status(200).entity(new GenericEntity<List<Usuario>>(
				ListaUsuario.listaUsuarios.stream().filter(usuario -> usuario.getCpf().equals(cpf)).collect(Collectors.toList())) {
		}).build();
	}
}
