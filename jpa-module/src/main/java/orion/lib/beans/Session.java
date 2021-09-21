/**
 * 
 */
package orion.lib.beans;

import java.io.Serializable;
import java.util.Date;

import orion.lib.GeneralBean;
import orion.lib.jpa.entities.Usuario;

/**
 * @author jviscarr
 *
 */
public class Session extends GeneralBean implements Serializable {
	private static final long serialVersionUID = -6381187070599962803L;
	
	private Usuario usuario;

	private Date finicio;
	private String sessionId;
	/**
	 * 
	 */
	public Session() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFinicio() {
		return finicio;
	}
	public void setFinicio(Date finicio) {
		this.finicio = finicio;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
