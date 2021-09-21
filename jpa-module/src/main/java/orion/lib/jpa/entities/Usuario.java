package orion.lib.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioPK id;

	private String activo;

	private String clave;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fexpiracion;

	private Timestamp fhasta;

	private Long idauditoria;

	private String usuario;

	public Usuario() {
	}

	public UsuarioPK getId() {
		return this.id;
	}

	public void setId(UsuarioPK id) {
		this.id = id;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFexpiracion() {
		return this.fexpiracion;
	}

	public void setFexpiracion(Date fexpiracion) {
		this.fexpiracion = fexpiracion;
	}

	public Timestamp getFhasta() {
		return this.fhasta;
	}

	public void setFhasta(Timestamp fhasta) {
		this.fhasta = fhasta;
	}

	public Long getIdauditoria() {
		return this.idauditoria;
	}

	public void setIdauditoria(Long idauditoria) {
		this.idauditoria = idauditoria;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}