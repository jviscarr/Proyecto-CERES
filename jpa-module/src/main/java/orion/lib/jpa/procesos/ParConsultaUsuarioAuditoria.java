/**
 * 
 */
package orion.lib.jpa.procesos;

import java.io.Serializable;

/**
 * @author jviscarr
 *
 */

public class ParConsultaUsuarioAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String usuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
