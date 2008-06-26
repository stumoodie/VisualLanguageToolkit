/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibRepository;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootFolder;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;
import org.pathwayeditor.businessobjects.repository.IRootFolder;

/**
 * @author smoodie
 *
 */
public class Repository implements IRepository {
	private final HibRepository hibRepository;
	
	public Repository(String name, String description, int buildNum) {
		this.hibRepository = new HibRepository(name, description, buildNum, new HibRootFolder());
	}
	
	public Repository(HibRepository hibRepository){
		this.hibRepository = hibRepository;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getName()
	 */
	public String getName() {
		return this.hibRepository.getName();
	}

	public String getDescription() {
		return this.hibRepository.getDescription();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getRootFolder()
	 */
	public IRootFolder getRootFolder() {
		return this.hibRepository.getRootFolder().getBusinessObject();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#levelOrderIterator()
	 */
	public Iterator<IRepositoryItem> levelOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	HibRepository getHibObject(){
		return this.hibRepository;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getBuildNum()
	 */
	public int getBuildNum() {
		// TODO Auto-generated method stub
		return 0;
	}

}
