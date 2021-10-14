package org.tolven.fdb.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fdb_imprint database table.
 * 
 */
@Embeddable
public class FdbImprintPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String pmid;

	private String startdate;

    public FdbImprintPK() {
    }
	public String getPmid() {
		return this.pmid;
	}
	public void setPmid(String pmid) {
		this.pmid = pmid;
	}
	public String getStartdate() {
		return this.startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FdbImprintPK)) {
			return false;
		}
		FdbImprintPK castOther = (FdbImprintPK)other;
		return 
			this.pmid.equals(castOther.pmid)
			&& this.startdate.equals(castOther.startdate);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pmid.hashCode();
		hash = hash * prime + this.startdate.hashCode();
		
		return hash;
    }
}