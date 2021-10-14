
package org.tolven.app.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.tolven.app.FDBDrugAllergiesLocal;
import org.tolven.app.entity.ListQueryControl;
import org.tolven.app.entity.MDQueryResults;
import org.tolven.app.entity.MSColumn;
import org.tolven.fdb.entity.FdbAllergenpicklistPK;

@Stateless()
@Local(FDBDrugAllergiesLocal.class)
public class FDBDrugAllergiesBean extends GenericListDataBean implements FDBDrugAllergiesLocal{
	private static String FROM_TABLE_NAME = "from FdbAllergenpicklist";
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public long countListData(ListQueryControl ctrl) {
		return super.countListData(ctrl, em);
	}

	@Override
	public MDQueryResults findDataByColumns(ListQueryControl ctrl) {
		return super.findDataByColumns(ctrl, em);
	}
	
	protected String[] extractColumnNamesFromMSColumns( Collection<MSColumn> cols, Set<String> limitColumns ) {
		Set<String> columns = new HashSet<String>(20);
		// Get the column names (from clause)
		// But if limit columns specified, then only those columns
		for( MSColumn col : cols ){
		    if (limitColumns!=null && !limitColumns.contains(col.getHeading())) 
		    	continue; 
		    // Ignore computed columns
		    if (!col.isComputed()) {
		    	//for FdbAllergenpicklist the values for concepttype and conceptid are in FdbAllergenpicklistPK
		    	if(col.getInternal().equalsIgnoreCase("concepttype") || col.getInternal().equalsIgnoreCase("conceptid"))
		    		columns.add("id");
		    	else
		    		columns.add(col.getInternal());
		    }
		}
		return columns.toArray(new String[columns.size()]);
	}
	
	/** Module to construct the result collection using JPA query output
	 * @param results - JPA query output
	 * @param columnNames - internal field names
	 * @param columns - MSColumns
	 * @param fieldGetter
	 * @return
	 */
	protected MDQueryResults getResultsForList(List<Object[]> results,String[] columnNames,Collection<MSColumn> columns,MSColumn.RowMapFieldGetter fieldGetter,MDQueryResults queryResults) {
		for (Object[] row : results) {
			Map<String, Object> rowMap = new HashMap<String, Object>(columnNames.length + columns.size());
			int c1 = 0;
			// Load the raw internal fields into the map
			for (MSColumn col : columns) {
				if(col.getInternal().equalsIgnoreCase("concepttype") || col.getInternal().equalsIgnoreCase("conceptid")){
					FdbAllergenpicklistPK pk = (FdbAllergenpicklistPK)row[c1];
					if(col.getInternal().equalsIgnoreCase("concepttype")){
						rowMap.put(col.getHeading(), pk.getConcepttype());
					}else{
						rowMap.put(col.getHeading(), pk.getConceptid());
					}
				}else
					rowMap.put(col.getHeading(), row[c1]);
				c1++;
			}
			/*fieldGetter.setRowMap(rowMap);
			for (MSColumn col : columns) {
				// Ignore computed columns
				if (!col.isComputed()) {
					rowMap.put(col.getHeading(),col.getFormattedColumn(fieldGetter));
				}
			}*/
			queryResults.addRow(rowMap);
		}
		return queryResults;
	}
	protected Query prepareCriteria(EntityManager em, ListQueryControl ctrl, String selectClause,String queryClause,boolean includeSortOrder) {
		StringBuffer sbOrder = new StringBuffer(350);
		StringBuffer sbwhere = new StringBuffer(350);
		
		// Assemble the query string
		if(!StringUtils.isBlank(ctrl.getSortOrder())){
			sbOrder.append(String.format("ORDER BY %s",ctrl.getMenuStructure().getInitialSort()));
		}
		else if(!StringUtils.isBlank(ctrl.getMenuStructure().getInitialSort())){
			sbOrder.append(String.format("ORDER BY %s",ctrl.getMenuStructure().getInitialSort()));
		}
		if(!StringUtils.isBlank(ctrl.getFilter())){
			sbwhere.append(" where description1 like '%"+ctrl.getFilter().trim()+"%'");
		}
		String qs = null;
		if(includeSortOrder)
			qs = String.format(Locale.US, "SELECT %s %s %s %s", selectClause,FROM_TABLE_NAME, sbwhere, sbOrder);
		else
			qs = String.format(Locale.US, "SELECT %s %s %s", selectClause,FROM_TABLE_NAME,sbwhere);
		
		Query query = em.createQuery(qs);
		return query;
	}

}
