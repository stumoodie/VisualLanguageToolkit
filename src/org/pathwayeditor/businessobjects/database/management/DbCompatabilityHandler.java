/**
 * 
 */
package org.pathwayeditor.businessobjects.database.management;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.management.IDbCompatibilityHandler;
import org.pathwayeditor.businessobjects.management.IDbUpgrader;

/**
 * @author smoodie
 *
 */
public class DbCompatabilityHandler implements IDbCompatibilityHandler {
//	private static final int FIRST_COL = 1;
	private int requiredVersion;
	private final IDbConnectionManager connManager;
//	private Integer actualVersion = null;
	private final List<IDbUpgrader> upgraders;

	public DbCompatabilityHandler(IDbConnectionManager connManager, int requiredVersion){
		this.connManager = connManager;
		this.upgraders = new LinkedList<IDbUpgrader>();
		this.requiredVersion = requiredVersion;
	}
	
	public void addUpgrader(IDbUpgrader upgrader){
		this.upgraders.add(upgrader);
		upgrader.setConnectionManager(this.connManager);
		upgrader.setRequestedTargetVersion(requiredVersion);
	}
	
	public boolean isUpgradeRequired() throws SQLException {
		boolean retVal = false;
		Iterator<IDbUpgrader> iter = this.upgraders.iterator();
		while(iter.hasNext() && ! retVal){
			IDbUpgrader upgrader = iter.next();
			retVal = upgrader.isUpgradeRequired();
		}
		return retVal;
	}

	public void upgrade() throws SQLException {
		if(!canDoUpgrade()) throw new IllegalStateException("Can not upgrade");
		
		for(IDbUpgrader upgrader : this.upgraders){
			if(upgrader.canDoUpgrade()){
				upgrader.doUpgrade();
				// only need to do it once
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDbCompatibilityHandler#canDoUpgrade()
	 */
	public boolean canDoUpgrade() throws SQLException {
		boolean retVal = false;
		Iterator<IDbUpgrader> iter = this.upgraders.iterator();
		while(iter.hasNext() && ! retVal){
			IDbUpgrader upgrader = iter.next();
			retVal = upgrader.canDoUpgrade();
		}
		return retVal;
	}

}
