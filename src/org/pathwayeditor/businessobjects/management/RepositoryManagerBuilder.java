package org.pathwayeditor.businessobjects.management;

import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.database.util.HibernateDataSource;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandlerFactory;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.management.impl.RepositoryPersistenceManager;

/**
 * @author smoodie
 *
 */
public class RepositoryManagerBuilder implements IRepositoryManagerBuilder {
	private final static String HIB_CONFIG_FILE_NAME = "hibernate.cfg.xml";

	private final HibernateDataSource hibdataSource;
	private IConnectionInfo connectionInfo = null;
	private INotationSubsystemPool notationSubsystemPool = null;
	

	public RepositoryManagerBuilder(){
		this.hibdataSource = new HibernateDataSource(HIB_CONFIG_FILE_NAME);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IRepositoryManagerBuilder#canCreateRepoManager()
	 */
	public boolean canCreateRepoManager() {
		return this.connectionInfo != null && this.notationSubsystemPool != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IRepositoryManagerBuilder#createRepoManager()
	 */
	public IRepositoryPersistenceManager createRepoManager() {
		if(!canCreateRepoManager()) throw new IllegalStateException("this instance is not in a suitable state to create a new repository manager");
		
		this.hibdataSource.setConnectionInfo(this.getConnectionInfo());
		SessionFactory factory = this.hibdataSource.getSessionFactory(); 
		ICanvasPersistenceHandlerFactory canvasPersistenceHandler = new HibCanvasPersistenceHandlerFactory(factory, this.getNotationSubsystemPool());
		IRepositoryPersistenceHandler repoHandler = new HibRepositoryPersistenceHandler(factory, this.getConnectionInfo().getRepositoryName());  
		return new RepositoryPersistenceManager(repoHandler, canvasPersistenceHandler);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IRepositoryManagerBuilder#setConnectionInfo(org.pathwayeditor.businessobjects.database.util.IConnectionInfo)
	 */
	public void setConnectionInfo(IConnectionInfo connection) {
		this.connectionInfo = connection;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IRepositoryManagerBuilder#setNotationSubsystemPool(org.pathwayeditor.businessobjects.management.INotationSubsystemPool)
	 */
	public void setNotationSubsystemPool(INotationSubsystemPool notationSubsystemPool) {
		this.notationSubsystemPool = notationSubsystemPool;
	}

	public IConnectionInfo getConnectionInfo() {
		return this.connectionInfo;
	}

	public INotationSubsystemPool getNotationSubsystemPool() {
		return this.notationSubsystemPool;
	}
}
