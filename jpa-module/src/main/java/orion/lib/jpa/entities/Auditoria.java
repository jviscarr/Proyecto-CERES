package orion.lib.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the auditoria database table.
 * 
 */
@Entity
@NamedQuery(name="Auditoria.findAll", query="SELECT a FROM Auditoria a")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idauditoria;

	private String accion;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Timestamp hora;

	private Long idanterior;

	private String ip;

	private String usuario;

	public Auditoria() {
	}

	public Long getIdauditoria() {
		return this.idauditoria;
	}

	public void setIdauditoria(Long idauditoria) {
		this.idauditoria = idauditoria;
	}

	public String getAccion() {
		return this.accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Timestamp getHora() {
		return this.hora;
	}

	public void setHora(Timestamp hora) {
		this.hora = hora;
	}

	public Long getIdanterior() {
		return this.idanterior;
	}

	public void setIdanterior(Long idanterior) {
		this.idanterior = idanterior;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}