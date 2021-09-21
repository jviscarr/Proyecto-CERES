/**
 * 
 */
package orion.lib.beans;

import java.io.Serializable;

//import javax.persistence.Entity;
import javax.persistence.Id;

import orion.lib.GeneralBean;
import orion.lib.jpa.entities.Usuario;

/**
 * @author jviscarr
 *
 */
//@Entity
public class RespuestaValidaUsuario extends GeneralBean implements Serializable{
	private static final long serialVersionUID = -2920040088479146344L;

	@Id
	private Integer id;
	
	private Boolean valido = false;
	
	private String mensajeError;
	
	private Usuario usuario;
	
	/**
	 * 
	 */
	public RespuestaValidaUsuario() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
