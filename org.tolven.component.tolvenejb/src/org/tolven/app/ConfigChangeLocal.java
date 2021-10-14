package org.tolven.app;

import java.util.List;

import org.tolven.app.entity.AccountMenuStructure;
import org.tolven.app.entity.ConfigChange;


public interface ConfigChangeLocal {
	
	/** Start data migration for a menu structure. This method returns a boolean 
	 * indicating if the the data migration for the menu structure is completed.
	 * @param msId
	 * @return
	 */
	public boolean migrateMenuDataByMenuStructure(long msId);
	
	/** Post a message to JMS queue to start data migration
	 * @param migrateMenuDataMessage
	 */
	public void startDataMigration(MigrateMenuDataMessage migrateMenuDataMessage);
	
	
	/** Module to find config changes for a menu structure
	 * @param ams
	 * @return
	 */
	public List<ConfigChange> findMigrationChanges(AccountMenuStructure ams,boolean skippedForMigration);
	
	public ConfigChange saveConfigChange(ConfigChange change);
	public void startRollBackMigration(RollbackMigrationMessage tmmsg);
	boolean rollBackMigrationChanges(long changeId);
}
