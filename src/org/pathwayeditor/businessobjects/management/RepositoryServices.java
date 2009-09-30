/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.HashMap;
import java.util.Map;

import org.pathwayeditor.businessobjects.hyperlink.IRepositoryLookupService;
import org.pathwayeditor.businessobjects.hyperlink.IRepositoryServiceCallback;
import org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService;

/**
 * @author smoodie
 *
 */
public class RepositoryServices implements IRepositoryServices {
	private static IRepositoryServices anInstance = null;
	
	private final IRepositoryLookupService lookupService;
	private final Map<String, IRepositoryPersistenceManager> repolookup;
	private final IRepositoryServiceCallback callback;

	
	public static IRepositoryServices getInstance(){
		if(anInstance == null){
			anInstance = new RepositoryServices();
		}
		return anInstance;
	}
	
	private RepositoryServices(){
		this.repolookup = new HashMap<String, IRepositoryPersistenceManager>();
		this.callback = new IRepositoryServiceCallback(){

			public IRepositoryPersistenceManager getRepositoryByName(String name) {
				return repolookup.get(name);
			}

			public void registerRepository(IRepositoryPersistenceManager repoManager) {
				repolookup.put(repoManager.getRepository().getName(), repoManager);
			}
			
		};
		this.lookupService = new RepositoryLookupService(this.callback);
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IRepositoryServices#getRepositoryBuilder()
	 */
	public IRepositoryManagerBuilder getRepositoryBuilder() {
		return new RepositoryManagerBuilder(this.callback);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IRepositoryServices#getRepositoryLookup()
	 */
	public IRepositoryLookupService getRepositoryLookup() {
		return this.lookupService;
	}

}
