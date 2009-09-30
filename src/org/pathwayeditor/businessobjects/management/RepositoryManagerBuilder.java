/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.management;

import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.database.util.HibernateDataSource;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandlerFactory;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.hyperlink.IRepositoryServiceCallback;
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
	private IRepositoryServiceCallback callback = null;
	

	/**
	 * Create the repository manager builder
	 * @deprecated It is preferable to create an instance of this class using the 
	 * RepositoryLookupService as this enables lookups via a URI.
	 * @see org.pathwayeditor.businessobjects.management.RepositoryServices 
	 */
	public RepositoryManagerBuilder(){
		this.hibdataSource = new HibernateDataSource(HIB_CONFIG_FILE_NAME);
	}

	RepositoryManagerBuilder(IRepositoryServiceCallback callback){
		this();
		this.callback = callback;
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
		IRepositoryPersistenceManager retVal = new RepositoryPersistenceManager(repoHandler, canvasPersistenceHandler);
		if(this.callback != null){
			this.callback.registerRepository(retVal);
		}
		return retVal;
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
