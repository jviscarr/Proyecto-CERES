/**
 * 
 */
package orion.lib.ejb;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author jviscarr
 *
 */
public abstract class GeneralAbstractEjb {
	@Resource
	protected SessionContext sessionContext;
	
	@PersistenceContext(unitName = "pu-ceres")
	protected EntityManager em;
	
	@PersistenceContext(unitName = "pu-ceres")
	protected EntityManager em1;
	
	/**
	 * Obtiene el valor de la secuencia.
	 * @param seqname
	 * @return
	 * @throws SQLException
	 */
	public Integer nextvalue(String seqname) throws SQLException {
		Query query = em.createNativeQuery( "SELECT NEXTVAL('"+seqname+"')" );
		return ((Long)query.getSingleResult()).intValue();
	}
	
	/**
	 * Obtiene el valor de la secuencia.
	 * @param seqname
	 * @return
	 * @throws SQLException
	 */
	public Long nextvalueLong(String seqname) throws SQLException {
		Query query = em.createNativeQuery( "SELECT NEXTVAL('"+seqname+"')" );
		return (Long)query.getSingleResult();
	}
	
	public Date fechaActual() {
		return new Date( System.currentTimeMillis() );
	}
	
	public Timestamp horaActual() {
		return new Timestamp( System.currentTimeMillis() );
	}
	
	public Timestamp fhasta() {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Timestamp fhasta = null;
		try {
			Date dhasta = sdf.parse("2999-12-31");
			fhasta = new Timestamp( dhasta.getTime() );
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return fhasta;
	}
}
