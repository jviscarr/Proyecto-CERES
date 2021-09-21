package orion.lib.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the usuario database table.
 * 
 */
@Embeddable
public class UsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long idusuario;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fdesde;

	public UsuarioPK() {
	}
	public Long getIdusuario() {
		return this.idusuario;
	}
	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}
	public java.util.Date getFdesde() {
		return this.fdesde;
	}
	public void setFdesde(java.util.Date fdesde) {
		this.fdesde = fdesde;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsuarioPK)) {
			return false;
		}
		UsuarioPK castOther = (UsuarioPK)other;
		return 
			this.idusuario.equals(castOther.idusuario)
			&& this.fdesde.equals(castOther.fdesde);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idusuario.hashCode();
		hash = hash * prime + this.fdesde.hashCode();
		
		return hash;
	}
}