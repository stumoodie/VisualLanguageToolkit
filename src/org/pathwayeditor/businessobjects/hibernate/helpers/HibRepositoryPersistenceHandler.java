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
/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibFolder;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibMap;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRepository;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootFolder;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibSubFolder;
import org.pathwayeditor.businessobjects.management.IConnectionInfo;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public class HibRepositoryPersistenceHandler implements	IRepositoryPersistenceHandler {
	private final SessionFactory fact;
	private String repoName = null;
	private IRepository rep = null;
	
	public HibRepositoryPersistenceHandler(SessionFactory fact, String repoName){
		this.fact = fact;
		this.repoName = repoName;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#setOwningMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public void setName(String name){
		this.repoName = name;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#getOwningMap()
	 */
	public String getName() {
		return this.repoName;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#loadCanvas()
	 */
	public void loadRepository(){
		this.rep = null;
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		HibRepository hibRep = (HibRepository) s.getNamedQuery("loadRepository")
				.setString("name", this.repoName).uniqueResult();
		if(hibRep==null)
			hibRep=createAndSaveADefaultRepository(s);
		initialiseRepository(hibRep);
		s.getTransaction().commit();
		rep = hibRep;
	}
	
	/**
	 * 
	 */
	private HibRepository createAndSaveADefaultRepository(Session s) {
		HibRepository defaultRepository = new HibRepository(IConnectionInfo.DEFAULT_REPOSITORY_NAME,"Local repository",0);
		s.save(defaultRepository);
		return defaultRepository;
	}

	private void initialiseRepository(HibRepository hibRep){
		Hibernate.initialize(hibRep);
		Hibernate.initialize(hibRep.getMaps());
		Hibernate.initialize(hibRep.getFolders());
		HibRootFolder root = hibRep.getRootFolder();
//		Hibernate.initialize(root);
		loadSubFoldersAndMaps(root);
	}
	
	/**
	 * @param root
	 */
	private void loadSubFoldersAndMaps(HibFolder folder) {
		Hibernate.initialize(folder);
		Hibernate.initialize(folder.getMapDiagrams());
		Hibernate.initialize(folder.getSubFolders());
		for (HibSubFolder i : folder.getSubFolders()) {
			this.loadSubFoldersAndMaps(i);
		}
	}

	public IRepository getLoadedRepository(){
		return this.rep;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#finish()
	 */
	public void reset() {
		this.rep = null;
		this.repoName = null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#synchroniseCanvas()
	 */
	public void synchroniseRepository() {
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		HibRepository hibRep = (HibRepository)this.rep; 
		s.saveOrUpdate(hibRep);
		for(HibMap map : hibRep.getMaps()){
			s.saveOrUpdate(map);
		}
//		initialiseRepository(hibRep);
		s.getTransaction().commit();
	}
}
