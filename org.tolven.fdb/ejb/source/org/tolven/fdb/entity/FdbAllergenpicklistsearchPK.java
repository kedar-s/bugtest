package org.tolven.fdb.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fdb_allergenpicklistsearch database table.
 * 
 */
@Embeddable
public class FdbAllergenpicklistsearchPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer languageid;

	private Integer conceptid;

	private Integer concepttype;

    public FdbAllergenpicklistsearchPK() {
    }
	public Integer getLanguageid() {
		return this.languageid;
	}
	public void setLanguageid(Integer languageid) {
		this.languageid = languageid;
	}
	public Integer getConceptid() {
		return this.conceptid;
	}
	public void setConceptid(Integer conceptid) {
		this.conceptid = conceptid;
	}
	public Integer getConcepttype() {
		return this.concepttype;
	}
	public void setConcepttype(Integer concepttype) {
		this.concepttype = concepttype;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FdbAllergenpicklistsearchPK)) {
			return false;
		}
		FdbAllergenpicklistsearchPK castOther = (FdbAllergenpicklistsearchPK)other;
		return 
			this.languageid.equals(castOther.languageid)
			&& this.conceptid.equals(castOther.conceptid)
			&& this.concepttype.equals(castOther.concepttype);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.languageid.hashCode();
		hash = hash * prime + this.conceptid.hashCode();
		hash = hash * prime + this.concepttype.hashCode();
		
		return hash;
    }
}