package org.tolven.fdb.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.tolven.app.FDBInterface;
import org.tolven.app.el.TrimExpressionEvaluator;
import org.tolven.app.entity.MenuData;
import org.tolven.fdb.entity.FdbAllergenpicklist;
import org.tolven.fdb.entity.FdbAllergenpicklistPK;
import org.tolven.fdb.entity.FdbDispensable;
import org.tolven.process.ComputeBase;
import org.tolven.trim.ActRelationship;
import org.tolven.trim.Compute.Property;
import org.tolven.trim.ex.ActEx;
import org.tolven.trim.ex.ActRelationshipsMap;
import org.tolven.trim.ex.TrimEx;

public class EditFdbFavoritesListCompute extends ComputeBase{
	
	private boolean enabled;
	private boolean delete;
	private String template;
	private String fdbCode;
	private String type; //medicationOrder or drugAllergy
	private String alleryConceptType;
	private String listItemId; // favorite list item to be deleted
	@EJB private FDBInterface fdbBean;
	
	public EditFdbFavoritesListCompute(){
		try {
			if (fdbBean==null) {
				InitialContext ctx = new InitialContext();
				fdbBean = (FDBInterface) ctx.lookup("java:global/tolven/tolvenEJB/FDBBean!org.tolven.app.FDBInterface");
			}
		} catch (NamingException e) {
			throw new RuntimeException( "Unable to access FDBBean in InsertFdbMedicationAct", e);
		}
	}
	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public boolean isDelete() {
		return delete;
	}


	public void setDelete(boolean delete) {
		this.delete = delete;
	}


	public String getTemplate() {
		return template;
	}


	public void setTemplate(String template) {
		this.template = template;
	}


	public String getFdbCode() {
		return fdbCode;
	}


	public void setFdbCode(String fdbCode) {
		this.fdbCode = fdbCode;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	@Override
	public void compute() throws Exception {
		try {
			if (isEnabled()) {
				if(!isDelete()){
					TrimEx trimEx = getTrimBean().findTrim(template);
			     	ActRelationship ar = trimEx.getAct().getRelationships().get(0);
			     	ar.setSourceTrim(getFdbCode());
			     	TrimExpressionEvaluator evaluator = new TrimExpressionEvaluator();
			     	evaluator.addVariable("act",ar.getAct());
			     	if(getType().equals("medicationOrder")){
			     		FdbDispensable dispensable = fdbBean.findFdbDispensable(Integer.parseInt(getFdbCode()));
			     		evaluator.setValue("#{act.title.ST.value}", dispensable.getDescdisplay());
			     		//set code in to the trim as well. this is used in favorites
			    		evaluator.setValue("#{act.relationship['path'].act.title.ST.value}", getFdbCode());					     		
					}else if(getType().equals("drugAllergy")){
				  		FdbAllergenpicklist allergy =  fdbBean.findDrugAllergy(getAllergyId());
			     		evaluator.setValue("#{act.title.ST.value}", allergy.getDescription1());	
			     		//set code in to the trim as well. this is used in favorites
			     		evaluator.setValue("#{act.relationship['path'].act.title.ST.value}", getFdbCode()+"_"+getAlleryConceptType());		
				  	}
				    getTrim().getAct().getRelationships().add(ar);		     	
		     	}else{ // try deleting the menudata if exists. 
		     		//TODO: may not be need after fixind AppEvalAdaptor to remove the menudata for a given doc ID.
		     		List<ActRelationship> arList =  ((ActEx)getTrim().getAct()).getRelationshipsList().get("favoriteItem");
		     		List<ActRelationship> removeList =  new ArrayList<ActRelationship>();
		     		for(int i=0;i<arList.size();i++){
		     			if(arList.get(i).getSourceTrim().equals(getFdbCode())){
		     			//	ActRelationship toRemove = ;
		     				removeList.add(arList.get(i));
		     				if(getListItemId() == null || getListItemId().equals("undefined"))
		     					continue;
		     				MenuData deleteMD = menuBean.findMenuDataItem(Long.parseLong(getListItemId().substring(getListItemId().lastIndexOf("-")+1)));
		     				if(deleteMD == null)
		     					continue;
		     				deleteMD.setDeleted(true);
		     				menuBean.removeReferencingMenuData(deleteMD);
		     				menuBean.persistMenuData(deleteMD);	     				
		     			}
		     		}
		     		arList.removeAll(removeList);
		     		((ActRelationshipsMap)((ActEx)getTrim().getAct()).getRelationshipsList()).refreshList();
		     	}
				// Disable the Compute since its job is done.
		    	for (Property property : getComputeElement().getProperties()) {
					if (property.getName().equals("enabled")) {
						property.setValue(Boolean.FALSE);
						break;
					}
				}
			}			
		}catch (Exception e) {
			throw new RuntimeException("Exception in EditFdbFavoritesListCompute",e);
		}
		
	}
	private FdbAllergenpicklistPK getAllergyId(){
		FdbAllergenpicklistPK pk = new FdbAllergenpicklistPK();
		pk.setConceptid(new Integer(getFdbCode()));
		pk.setConcepttype(new Integer(getAlleryConceptType()));
		return pk;
	}
	public String getAlleryConceptType() {
		return alleryConceptType;
	}
	public void setAlleryConceptType(String alleryConceptType) {
		this.alleryConceptType = alleryConceptType;
	}
	public String getListItemId() {
		return listItemId;
	}
	public void setListItemId(String listItemId) {
		this.listItemId = listItemId;
	}
}
