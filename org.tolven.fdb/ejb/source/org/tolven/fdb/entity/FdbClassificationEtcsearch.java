package org.tolven.fdb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fdb_classification_etcsearch database table.
 * 
 */
@Entity
@Table(name="fdb_classification_etcsearch")
public class FdbClassificationEtcsearch implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FdbClassificationEtcsearchPK id;

	private Integer baselangid;

	private String descaltsearch;

	private String descsearch;

    public FdbClassificationEtcsearch() {
    }

	public FdbClassificationEtcsearchPK getId() {
		return this.id;
	}

	public void setId(FdbClassificationEtcsearchPK id) {
		this.id = id;
	}
	
	public Integer getBaselangid() {
		return this.baselangid;
	}

	public void setBaselangid(Integer baselangid) {
		this.baselangid = baselangid;
	}

	public String getDescaltsearch() {
		return this.descaltsearch;
	}

	public void setDescaltsearch(String descaltsearch) {
		this.descaltsearch = descaltsearch;
	}

	public String getDescsearch() {
		return this.descsearch;
	}

	public void setDescsearch(String descsearch) {
		this.descsearch = descsearch;
	}

}