/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibFolder;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibMapDiagram;
import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public class Map implements IMap, IHibernateFacade<HibMapDiagram> {
	private final HibMapDiagram hibMap;
	
	public Map(SubFolder owner, String name) {
		this(owner.getHibObject(), name);
	}

	public Map(RootFolder owner, String name) {
		this(owner.getHibObject(), name);
	}

	private Map(HibFolder owner, String name){
		this.hibMap = new HibMapDiagram(owner, name);
	}
	
	public Map(HibMapDiagram hibMap){
		this.hibMap = hibMap;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getOwner()
	 */
	public IFolder getOwner() {
		return this.hibMap.getFolder().getBusinessObject();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getName()
	 */
	public String getName() {
		return this.hibMap.getName();
	}

	public String getDescription(){
		return this.hibMap.getDescription();
	}
	
	/**
	 * @return
	 */
	public HibMapDiagram getHibObject() {
		return this.hibMap;
	}
}
