/**
 * 
 */
package orion.lib.ejb;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import orion.lib.jpa.entities.Auditoria;
import orion.lib.jpa.entities.Usuario;
import orion.lib.jpa.entities.UsuarioPK;

/**
 * @author jviscarr
 *
 */
@Stateless
@LocalBean
public class TransaccionesEjb extends GeneralAbstractEjb {
	@EJB
	private ConsultasEjb consultasEjb;
	
	/**
	 * @param entity
	 * @return
	 * @throws SQLException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario crearUsuario(Usuario entity) throws SQLException{
		if( consultasEjb.existeUsuario(entity.getUsuario()) ) {
			throw new SQLException( "El usuario '"+entity.getUsuario()+"' ya existe." );
		}
		
		Auditoria auditoria = new Auditoria();
		auditoria.setDescripcion( "Creamos el usuario '"+entity.getUsuario()+"'" );
		auditoria.setAccion("INS");
		auditoria.setUsuario( entity.getUsuario() );
		auditoria.setIp( "127.0.0.1" );
		auditoria = crearAuditoria( auditoria );
		
		entity.setId( new UsuarioPK() );
		entity.getId().setIdusuario( nextvalueLong("seq_usuario") );
		entity.getId().setFdesde( auditoria.getHora() );
		entity.setIdauditoria( auditoria.getIdauditoria() ); 
		entity.setFhasta( fhasta() );
		em.persist( entity );
		return entity;
	}
	
	/**
	 * @param entity
	 * @return
	 * @throws SQLException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario actualizarUsuario(Usuario entity) throws SQLException{
		Usuario usrAct = consultasEjb.usuarioXId( entity.getId().getIdusuario() );
		
		Auditoria auditoria = new Auditoria();
		auditoria.setDescripcion( "Actualizamos el usuario '"+entity.getUsuario()+"'" );
		auditoria.setAccion("UPD");
		auditoria.setUsuario( entity.getUsuario() );
		auditoria.setIp( "127.0.0.1" );
		auditoria = crearAuditoria( auditoria );
		
		Timestamp horaActual = auditoria.getHora();
		
		usrAct.setFhasta( horaActual );
		em.merge( usrAct );
		
		entity.getId().setFdesde( horaActual );
		entity.setFhasta( fhasta() );
		entity.setIdauditoria( auditoria.getIdauditoria() );
		entity.setEstado( usrAct.getEstado() );
		entity.setActivo( usrAct.getActivo() );
		em.persist( entity );
		return entity;
	}
	
	/**
	 * @param idusuario
	 * @throws SQLException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarUsuario(Long idusuario) throws SQLException{
		if(idusuario==null) {
			throw new SQLException( "Debe ingresar un idusuario" );
		}
		
		try {
			Usuario entity = consultasEjb.usuarioXId( idusuario );
			if(entity==null) {
				throw new SQLException( "No existe el usuario con pk "+idusuario+"." );
			}
			
			if( "N".equals(entity.getActivo()) ) {
				throw new SQLException( "El usuario con pk "+idusuario+" ya esta inactivo." );
			}
			
			Auditoria auditoria = new Auditoria();
			auditoria.setDescripcion( "Eliminamos el usuario '"+entity.getUsuario()+"'" );
			auditoria.setAccion("DEL");
			auditoria.setUsuario( entity.getUsuario() );
			auditoria.setIp( "127.0.0.1" );
			auditoria = crearAuditoria( auditoria );
			
			Timestamp horaActual = auditoria.getHora();
			
			entity.setFhasta( horaActual );
			em.merge( entity );
			
			entity.setActivo( "N" );
			entity.setEstado( "ELI" );
			entity.getId().setFdesde( horaActual );
			entity.setFhasta( fhasta() );
			em.persist( entity );
		}catch (Exception ex) {
			throw new SQLException(ex);
		}
		return;
	}
	
	/**
	 * @param entity
	 * @return
	 * @throws SQLException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Auditoria crearAuditoria(Auditoria entity) throws SQLException{
		entity.setIdauditoria( nextvalueLong("seq_auditoria") );
		entity.setFecha( fechaActual() );
		entity.setHora( horaActual() );
		em.persist( entity );
		return entity;
	}
}
