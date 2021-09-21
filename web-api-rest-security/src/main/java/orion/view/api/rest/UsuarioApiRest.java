/**
 * 
 */
package orion.view.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import orion.lib.ejb.ConsultasEjb;
import orion.lib.ejb.TransaccionesEjb;
import orion.lib.jpa.entities.Usuario;
import orion.lib.jpa.procesos.ParConsultaUsuarioAuditoria;
import orion.lib.jpa.procesos.ParUsuariosXActivo;
import orion.view.api.GeneralApiRest;

/**
 * @author jviscarr
 *
 */
@Path("/usuarioApi") 
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioApiRest extends GeneralApiRest{
	@EJB
	private ConsultasEjb consultasEjb;
	
	@EJB
	private TransaccionesEjb transaccionesEjb;
	
	@GET
	@Path("/consultas")
	public Response consultarUsuarios() {
		List<Usuario> list = null;
		try {
			list = consultasEjb.consultarUsuarios();
		}catch(Exception ex) {
			return Response.status( Status.BAD_REQUEST ).entity( ex ).build();
		}
		return Response.ok( list ).build();
	}
	
	@GET
	@Path("/consultas")
	public Response consultarIdUsuario(@QueryParam("idusuario") Long idusuario) {
		System.out.println("consultarIdUsuario()");
		Usuario usuario = new Usuario();
		try {
			usuario = consultasEjb.usuarioXId( idusuario );
		}catch(Exception ex) {
			return Response.status( Status.BAD_REQUEST ).entity( ex ).build();
		}
		return Response.ok( usuario ).build();
	}
	
	@GET
	@Path("/consultas/{idusuario}")
	public Response consultarUsuarioPath(@PathParam("idusuario") Long idusuario) {
		System.out.println("consultarUsuarioPath()");
		Usuario usuario = new Usuario();
		try {
			usuario = consultasEjb.usuarioXId( idusuario );
		}catch(Exception ex) {
			return Response.status( Status.BAD_REQUEST ).entity( ex ).build();
		}
		return Response.ok( usuario ).build();
	}
	
	@GET
	@Path("/consultas/xusuario")
	public Response consultarUsuario(@QueryParam("usuario") String usuario) {
		System.out.println("consultarUsuario()");
		Usuario entity = new Usuario();
		try {
			entity = consultasEjb.consultaXUsuario( usuario );
		}catch(Exception ex) {
			return Response.status( Status.BAD_REQUEST ).entity( ex ).build();
		}
		if( entity==null ) return Response.noContent().build();
		return Response.ok( entity ).build();
	}
	
	/**
	 * @param usuario
	 * @return
	 */
	@POST
	public Response crearUsuario(Usuario usuario) {
		System.out.println("crearUsuario()");
		try {
			usuario = transaccionesEjb.crearUsuario(usuario);
		}catch(Exception ex) {
			return Response.status( Status.BAD_REQUEST ).entity( ex ).build();
		}
		return Response.ok( usuario ).build();
	}
	
	/**
	 * @param usuario
	 * @return
	 */
	@PUT
	public Response actualizarUsuario(Usuario usuario) {
		System.out.println("actualizarUsuario()");
		try {
			usuario = transaccionesEjb.actualizarUsuario( usuario );
		}catch(Exception ex) {
			return Response.status( Status.BAD_REQUEST ).entity( ex ).build();
		}
		return Response.ok( usuario ).build();
	}
	
	/**
	 * @param idusuario
	 * @return
	 */
	
	@DELETE
//	@Path("/{idusuario}")
	public Response eliminarUsuario(@QueryParam("idusuario") Long idusuario) {
		System.out.println("eliminarUsuario()");
		try {
			transaccionesEjb.eliminarUsuario( idusuario );
		}catch(Exception ex) {
			return Response.status( Status.BAD_REQUEST ).entity( ex ).build();
		}
		return Response.status(200).entity("Usuario eliminado").build();
	}
	
	@GET
	@Path("/conUsuariosXActivo")
	public Response consultarUsuariosXActivos(ParUsuariosXActivo parametros) {
		System.out.println( "consultarUsuariosXActivos() >> activo: "+parametros.getActivo() );
		List<Usuario> list = null;
		try {
			list = consultasEjb.consultarUsuarios();
		}catch(Exception ex) {
			return Response.status( Status.BAD_REQUEST ).entity( ex ).build();
		}
		return Response.ok( list ).build();
	}
}
