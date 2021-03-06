/*
 * Copyright (C) 2009 Tolven Inc

 * This library is free software; you can redistribute it and/or modify it under the terms of 
 * the GNU Lesser General Public License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;  
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 *
 * Contact: info@tolvenhealth.com 
 *
 * @author Joseph Isaac
 * @version $Id: SnapshotLocal.java 486 2011-03-29 00:51:12Z kanag.kuttiannan $
 */
package org.tolven.analysis;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.tolven.app.AppEvalAdaptor;
import org.tolven.app.entity.MenuData;
import org.tolven.core.entity.Account;
import org.tolven.core.entity.AccountUser;

public interface SnapshotLocal {

    public void snapshotNow(String cohortType, String snapshotType, Account account);
        
    public void scheduleSnapshot(String cohortType, String snapshotType, Account account);
    
    public Date getNextScheduledDate(String cohortType, String snapshotType, Date now, Account account);

    public void cancelSchedule(String cohortType, String snapshotType, Account account);
    
    public void cancelScheduleNow(String cohortType, String snapshotType, Account account, Date now);

    public MenuData createSnapshot(MenuData cohort, Account account);

    public MenuData findMenuData(long id, Account account);
    
    public List<Map<String, Object>> findAllMenuDataList(String path, String contextPath, String condition, AccountUser accountUser) throws ParseException;

    public void addCohortPlaceholderID(String cohortName, MenuData cohort);
    
    public MenuData findCohort(String cohortName, Account account);

    public void addPatientcohortPlaceholderID(String cohortName, MenuData patientcohort);
    
    public MenuData findPatientcohort(String cohortName, long patientId, Account account);

    public MenuData findPatientcohort(String cohortName, MenuData patient, Account account);

    public MenuData findPatientcohort(MenuData cohort, MenuData patient, Account account);

    public List<MenuData> findCohortPatients(String cohortType, Account account);

    public List<MenuData> findCohortPatients(MenuData cohort, Account account);

    public JFreeChart getChart(String cohortType, String snapshotType, String chartType, AccountUser accountUser, Date now);

    public void upateMenuDataVersion(String path, Account account, Date now);
   
    public void deleteAnalysisList(Account account,String snapshotType);    
    
    public Boolean validateCohortProperties(String type,MenuData patient,AppEvalAdaptor app);
    
    public void deletePatientCohortList(Account account,String analysisType);  
    
    public void deleteCohortPlaceholder(Account account,String cohortType); 
    
    public void deleteCohortList(Account account,String analysisType, String cohortType);  
    
    public void deleteAnalysisCohortList(Account account,String analysisType);  
    
    public void deleteFalseAnalysisCohortList(Account account,String analysisType);  
    
    public void deleteFalseCohortList(Account account,String analysisType, String cohortType);  
    
    public Boolean validateCodeStatusCohortProperties(String type,MenuData patient,AppEvalAdaptor app,String code);
    
}
