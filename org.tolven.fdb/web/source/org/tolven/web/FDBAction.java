/**
 * 
 */
package org.tolven.web;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import org.tolven.app.AllergyVO;
import org.tolven.app.DrugVO;
import org.tolven.app.FDBInterface;
import org.tolven.app.MenuLocal;
import org.tolven.app.bean.MenuPath;
import org.tolven.app.entity.MenuQueryControl;
import org.tolven.app.entity.MenuStructure;
import org.tolven.core.entity.AccountUser;
import org.tolven.fdb.entity.FdbDispensable;
import org.tolven.fdb.entity.FdbRoute;
import org.tolven.fdb.entity.FdbSig;

/**
 * @author mohammed
 * 
 */
public class FDBAction extends MenuAction {

	private MenuDataList menuDataList = null;
	
	/**
	 * Default Constructor
	 */
	public FDBAction(){
		super();		
	}
	/**
	 * Parameterized Constructor
	 * @param filterCondition
	 */
	public FDBAction( String filterCondition) {
		super();
		this.filterValue = filterCondition;		
	}
	private String rowId = "0";
	
	/**
	 * Variable to hold the hidden String
	 */
	private String hidden;
	
	/**
	 * FDBBean variable
	 */
	@EJB protected FDBInterface fdbBean;
	/**
	 * Variable to bind the list
	 */
	private List<FdbDispensable> drugList = null;
	/**
	 * Variable to hold the OTC Drugs
	 */
	private List<DrugVO> otcDrugList = null;
	/**
	 * Variable to bind the allergy List
	 */
	private List<AllergyVO> allergyList = null;
	/**
	 * Variable to hold the total drug count
	 */
	private int totalDrugCount;
	/**
	 * Variable to hold the OTC Drug Count
	 */
	private int otcCount;
	/**
	 * Variable to hold the total Allergy Count
	 */
	private int totalAllergyCount;
	/**
	 * Variable to hold the filter value
	 */
	private String filterValue;
	/**
	 * Variable to hold the selected Drug Name
	 */
	private String selectedDrug;
	/**
	 * Variable to hold the FdbStorageAction
	 */
	private List<SelectItem> routes;
	
	/**
	 * List of SIG from FDB
	 */
	private List<SelectItem> sigValues;
	
	public List<FdbDispensable> getDrugList() throws Exception  {
		if (null == this.drugList ) {
			this.drugList = new ArrayList<FdbDispensable>();
			this.drugList = getFdbBean().retrieveDrugInformation(null,0);
		}	
		totalDrugCount = getFdbBean().findDrugCount("").intValue();
		return drugList;
	}
	

	/**
	 * @param drugList
	 *            the drugList to set
	 */
	public void setDrugList(List<FdbDispensable> drugList) {
		this.drugList = drugList;
	}

	/**
	 * @return the totalDrugCount
	 * @throws Exception 
	 */
	public int getTotalDrugCount() throws Exception {
		if(this.filterValue == null){
			this.filterValue = "";
		}
		if(getFdbBean() != null)
			this.totalDrugCount = getFdbBean().findDrugCount(this.filterValue).intValue();		
		return totalDrugCount;
	}

	/**
	 * @param totalDrugCount
	 *            the totalDrugCount to set
	 */
	public void setTotalDrugCount(int totalDrugCount) {
		this.totalDrugCount = totalDrugCount;
	}

	/**
	 * @return the filterValue
	 */
	public String getFilterValue() {
		return filterValue;
	}

	/**
	 * @param filterValue
	 *            the filterValue to set
	 */
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	/**
	 * @return the selectedDrug
	 */
	public String getSelectedDrug() {
		return selectedDrug;
	}

	/**
	 * @param selectedDrug
	 *            the selectedDrug to set
	 */
	public void setSelectedDrug(String selectedDrug) {
		this.selectedDrug = selectedDrug;
	}

	/**
	 * @return the allergyList
	 * @throws Exception 
	 */
	public List<AllergyVO> getAllergyList() throws Exception {
		if (null == this.allergyList) {
			this.allergyList = getFdbBean().fetchDrugAllergies(0, null);
			this.totalAllergyCount = getFdbBean().findDrugAllergyCount("").intValue();
		}	
		return allergyList;
	}
	/**
	 * @param allergyList the allergyList to set
	 */
	public void setAllergyList(List<AllergyVO> allergyList) {
		this.allergyList = allergyList;
	}
	
	/**
	 * @return the totalAllergyCount
	 * @throws Exception 
	 */
	public int getTotalAllergyCount() throws Exception {
			if(this.filterValue == null){
				this.filterValue = "";
			}
			if(getFdbBean() != null)
				this.totalAllergyCount = getFdbBean().findDrugAllergyCount(this.filterValue).intValue();
		return totalAllergyCount;
	}
	/**
	 * @param totalAllergyCount the totalAllergyCount to set
	 */
	public void setTotalAllergyCount(int totalAllergyCount) {
		this.totalAllergyCount = totalAllergyCount;
	}
	/**
	 * @return the fdbBean
	 */
	public FDBInterface getFdbBean() {
		return fdbBean;
	}
	/**
	 * @param fdbBean the fdbBean to set
	 */
	public void setFdbBean(FDBInterface fdbBean) {
		this.fdbBean = fdbBean;
	}
	/**
	 * @return the otcCount
	 * @throws Exception 
	 */
	public int getOtcCount() throws Exception {
		if(otcCount == 0){
			otcCount = getFdbBean().findDrugCount("OTCDRUGCOUNT").intValue();
			}
		return otcCount;
	}
	/**
	 * @param otcCount the otcCount to set
	 */
	public void setOtcCount(int otcCount) {
		this.otcCount = otcCount;
	}
	/**
	 * @return the otcDrugList
	 * @throws Exception 
	 */
	public List<DrugVO> getOtcDrugList() {
		if(otcDrugList == null){
			otcDrugList = getFdbBean().retrieveOTCDrugInformation(0,null);
		}
		return otcDrugList;
	}
	/**
	 * @param otcDrugList the otcDrugList to set
	 */
	public void setOtcDrugList(List<DrugVO> otcDrugList) {
		this.otcDrugList = otcDrugList;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	/**
	 * @return the routes
	 * @throws Exception 
	 */
	public List<SelectItem> getRoutes() throws Exception {
		if(null == this.routes){
			this.routes = convertRoutesToSelectItems(fdbBean.fetchAllRoutes());
		}
		return this.routes;
	}
	/**
	 * @param routes the routes to set
	 */
	public void setRoutes(List<SelectItem> routes) {
		this.routes = routes;
	}
		
	private List<SelectItem> convertRoutesToSelectItems (List<FdbRoute> routes){
		List<SelectItem> finalRoutes = new ArrayList<SelectItem>();
		for(FdbRoute route : routes){
			SelectItem r = new SelectItem(route.getRtid(),route.getDescription1());
			finalRoutes.add(r);
		}
		return finalRoutes;
	}
	private List<SelectItem> convertSigToSelectItems (List<FdbSig> sigs){
		List<SelectItem> finalRoutes = new ArrayList<SelectItem>();
		for(FdbSig sig : sigs){
			SelectItem r = new SelectItem(sig.getId().getSigcode(),sig.getId().getSigdesc());
			finalRoutes.add(r);
		}
		return finalRoutes;
	}
	/**
	 * Getter for SigValues
	 * @return
	 * @throws Exception 
	 */
	public List<SelectItem> getSigValues() throws Exception {
		if(null == this.sigValues){
			this.sigValues = convertSigToSelectItems(getFdbBean().retrieveSIG());
		}
		return sigValues;
	}
	/**
	 * Setter for SigValues
	 * @param sigValues
	 */
	public void setSigValues(List<SelectItem> sigValues) {
		this.sigValues = sigValues;
	}
	
	
	/**
	 * This class used to retrieve MenuData List. 
	 */
	class MenuDataList extends HashMap<Object, List<Map<String, Object>>> {
		private MenuLocal menuLocal;
		private long accountId;
		private String contextPath;
		private Date dateNow;
		private AccountUser accountUser;
		public MenuDataList( MenuLocal menuLocal, long accountId, String context, Date dateNow, AccountUser accountUser) {
			this.menuLocal = menuLocal;
			this.accountId = accountId;
			this.contextPath = context;
			this.dateNow = dateNow;
			this.accountUser = accountUser;
		}		
		/**
		 * This function prepare the menudata list and add it to 'MenuDataList'
		 * @param path
		 */ 
		public void buildList(Object path ) {
			try{
				MenuQueryControl ctrl = new MenuQueryControl();
				DateFormat df = new SimpleDateFormat("MM/dd/yy");
				String splitChar =",";
				String parsedPath = path.toString().split(splitChar)[0];			
				
				MenuStructure ms = menuLocal.findMenuStructure(accountId, parsedPath );
				Map<String, Long> nodeValues = new HashMap<String, Long>(10);
				nodeValues.putAll(new MenuPath( contextPath ).getNodeValues());
				ctrl.setMenuStructure( ms );
				ctrl.setNow(dateNow);
				ctrl.setAccountUser(accountUser);
				ctrl.setOriginalTargetPath( new MenuPath(ms.instancePathFromContext ( nodeValues, true )));
				ctrl.setRequestedPath( ctrl.getOriginalTargetPath() );
				if (path.toString().split(splitChar).length>1 && path.toString().split(splitChar)[1]!=""){
					String[] params=path.toString().split(splitChar)[1].split(":");
					String[] param;
					String paramStr;
					long t;
					for (int i = 0; i < params.length; i++) {
						param=params[i].split("=");
						if (param.length>1){
							if (param[0].equals("filter"))
								ctrl.setFilter(param[1]);
							else if (param[0].equals("fromDate"))
								ctrl.setFromDate(df.parse(param[1]));
							else if (param[0].equals("toDate"))
								ctrl.setToDate(df.parse(param[1]));
							else if (param[0].equals("DateFilter")){
								if (param[1].contains("/")){
									ctrl.setFromDate(df.parse(param[1]));
									t = df.parse(param[1]).getTime();
									t += 24 * 60 * 60 * 1000;
									ctrl.setToDate(new Date(t));
								}
								else if (param[1].length()==8){
									paramStr=param[1].substring(4, 6)+"/"+param[1].substring(6, 8)+"/"+param[1].substring(2, 4);
									ctrl.setFromDate(df.parse(paramStr));
									t = df.parse(paramStr).getTime();
									t += 24 * 60 * 60 * 1000;
									ctrl.setToDate(new Date(t));
								}
							}else if (param[0].endsWith("Filter")) {
								ctrl.getFilters().put(param[0].substring(0, param[0].length()-6), param[1]);
							}else if (param[0].endsWith("Sort")) {
								ctrl.setSortOrder(param[0].substring(0, param[0].length()-4));
								ctrl.setSortDirection(param[1]);
							}else if (param[0].toLowerCase().equals("limit"))
								ctrl.setLimit(Integer.parseInt(param[1]));
						}
					}
				}
				List<Map<String, Object>> list =  menuLocal.findMenuDataByColumns(ctrl).getResults();			
				this.put(path, list);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		public List<Map<String, Object>> get(Object path) {
			if (!this.containsKey(path)) buildList(path);
			return super.get(path);
		}
	}
	
	
	/**
	 * This function is used to retrieve MenuData List.
	 * Format - <path>,<fieldName1>Filter=<fieldValue1>:<fieldName2>Filter=<fieldValue2>:
	 * 			:fromDate=<FROMDATE>:toDate=<TODATE>:<fieldName>Sort=<sortOrder>:limit=<value>
	 * eg1: echr:patient:specimens:active
	 * eg2: echr:patient:specimens:active,titleFilter=Desc:nameSort=ASC:limit=10
	 * @author Suja
	 * added on 5/29/09
	 * @return MenuDataList
	 * @throws Exception
	 */
	public MenuDataList getAllMenuData() throws Exception {
		if (menuDataList==null) {
			menuDataList = new MenuDataList( this.getMenuLocal(), this.getAccountId(), 
					this.getElement(), getNow(), getAccountUser());
		}
		return menuDataList;
	}
	
	public List<Map<String, Object>> getFavouriteMedicationsList() {
		List<Map<String, Object>> favouriteMedicationList = new ArrayList<Map<String,Object>>();

		try {
			List<Map<String, Object>> favouriteList = getAllMenuData().get("echr:admin:lists:accountLists");
			for (Map<String, Object> map : favouriteList) {
				if (map.get("Type").equals("medications")) {
					favouriteMedicationList.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return favouriteMedicationList;
	}
		
}
