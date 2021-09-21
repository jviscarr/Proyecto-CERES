/**
 * 
 */
package orion.lib.jpa.procesos;

import java.io.Serializable;

/**
 * @author jviscarr
 *
 */
public class ParUsuariosXActivo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String activo;

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}
	
}
