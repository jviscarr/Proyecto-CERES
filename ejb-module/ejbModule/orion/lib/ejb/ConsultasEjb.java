/**
 * 
 */
package orion.lib.ejb;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import orion.lib.beans.RespuestaValidaUsuario;
import orion.lib.jpa.entities.Usuario;

/**
 * @author jviscarr
 *
 */
@Stateless
@LocalBean
public class ConsultasEjb extends GeneralAbstractEjb{
	
	public String prueba(String msg) {
		return "Hola "+msg+"...como estas!";
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarUsuarios() throws SQLException {
		System.out.println( "##### consultarUsuarios()" );
		Query query = em.createQuery(
		    "SELECT t " 
		    + "FROM Usuario t " 
				+ "ORDER BY t.usuario " 
		    + ""
		    );
		List<Usuario> resp = query.getResultList();
		if(resp==null) {
			System.out.println( "############################# No hay resultado!" );
		}else {
			System.out.println( "##### retonan "+resp.size()+" elementos" );
		}
		return resp;
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarUsuarios2() throws SQLException {
		Query query = em1.createNamedQuery("Usuario.findAll");
		return query.getResultList();
	}
	
	/**
	 * @param idusuario
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario usuarioXId(Long idusuario) throws SQLException{
		Query query = em.createQuery(
		    "SELECT t " 
		    + "FROM Usuario t "
		    + "WHERE t.id.idusuario = :idusuario " 
		    + "AND t.fhasta = :fhasta"
		    );
		query.setParameter( "idusuario", idusuario );
		query.setParameter( "fhasta", fhasta() );
		List<Usuario> list = query.getResultList();
		if(list.isEmpty()) return null;
		return list.get( 0 );
	}
	
	/**
	 * Consulta por $usuario
	 * @param usuario - usuario que se desea consultar.
	 * @return - la entidad del usuario que se ha consultado, si no encuentra retorna NULL.
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario consultaXUsuario(String usuario) throws SQLException{
		Query query = em.createQuery(
		    "SELECT t " 
		    + "FROM Usuario t "
		    + "WHERE t.usuario = :usuario " 
		    + "AND t.fhasta = :fhasta "
		    );
		query.setParameter( "usuario", usuario );
		query.setParameter( "fhasta", fhasta() );
		List<Usuario> list = query.getResultList();
		if(list.isEmpty()) return null;
		return list.get( 0 );
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean existeUsuario(String usuario) throws SQLException{
		Query query = em.createQuery(
		    "SELECT COUNT(t) " 
		    + "FROM Usuario t "
		    + "WHERE t.usuario = :usuario " 
		    + "AND t.fhasta = :fhasta "
		    );
		query.setParameter( "usuario", usuario );
		query.setParameter( "fhasta", fhasta() );
		Number count = (Number)query.getSingleResult();
		if( count.intValue()==0 ) return false;
		return true;
	}
	
	/**
	 * @param usuario
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public RespuestaValidaUsuario validarUsuario(String usuario,String password) throws SQLException{
		RespuestaValidaUsuario respuesta = new RespuestaValidaUsuario();
		respuesta.setId( 1 );
		
		Usuario usrEntity = consultaXUsuario( usuario );
		
		if( usrEntity==null ) {
			respuesta.setValido( false );
			respuesta.setMensajeError( "Usuario y/o contraseña no son válidas." );
		}else {
			if( !usrEntity.getClave().equals(password) ) {
				respuesta.setValido( false );
				respuesta.setMensajeError( "Usuario y/o contraseña no son válidas." );
			}else {
				respuesta.setValido( true );
				respuesta.setMensajeError( null );
				respuesta.setUsuario( usrEntity );
			}
		}
		
		return respuesta;
	}
}
