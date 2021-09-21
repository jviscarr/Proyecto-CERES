/**
 * 
 */
package orion.view.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import orion.lib.ejb.ConsultasEjb;
import orion.lib.jpa.entities.Usuario;

/**
 * @author jviscarr
 *
 */
@Path("/helloworld")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldRest {

	@EJB
	private ConsultasEjb consultasEjb;
	
//	@GET
//	public Response sayHello() {
//		return Response.ok( "Hello world desde API Rest!!",MediaType.APPLICATION_JSON ).build();
//	}
	
	@GET
	public Response findUsuarios() {
		System.out.println("##### findUsuarios()");
		List<Usuario> list = null;
		try {
			list = consultasEjb.consultarUsuarios();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.ok( list ).build();
	}
}
