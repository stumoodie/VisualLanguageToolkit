package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

import uk.ed.inf.graph.util.IndexCounter;

/**
 * DataStore generated by hbm2java
 */
public class HibRepository implements Serializable, IRepository {
	private static final String ILLEGAL_NAME = "Name of a respoitory must not be either null or empty String and must be unique";
	private static final long serialVersionUID = -841109914395755034L;
	private static final String ILLEGAL_DESCRIPTION = "Description of a repository cannot be null";
//	private static final String ILLEGAL_ROOTFOLDER = "Root folder cannot be null";
	private static final String ILLEGAL_SUBFOLDERNAME = "Folder name cannot be null or empty String";
	private static final String PATH_DOES_NOT_EXIST = "This path does not exist in this repository";
	private Long id = null;
	private String name = null;
	private String description = null;
	private HibRootFolder rootFolder = null;
	private int buildNum;
	private IndexCounter iNodeCounter;
	private Set<HibFolder> folders;
	private Set<HibMap> maps;
     
	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibRepository(){
		this.iNodeCounter = new IndexCounter();
	}

	public HibRepository(String name, String description, int buildNum ) {
		this();
		if(name==null||name.equals(""))
				throw new IllegalArgumentException(ILLEGAL_NAME);
		this.name = name;
		if(description==null)
			throw new IllegalArgumentException(ILLEGAL_DESCRIPTION);
		this.description = description;
		this.buildNum = buildNum;
		this.iNodeCounter = new IndexCounter();
		rootFolder = new HibRootFolder(this);
	}

   public void changeRootFolder(HibRootFolder newRootFolder){
	   HibRootFolder oldRootFolder = this.rootFolder;
	   this.rootFolder = newRootFolder;
	   if(oldRootFolder != null){
		   oldRootFolder.setRepository(null);
	   }
	   if(this.rootFolder != null){
		   this.rootFolder.setRepository(this);
	   }
   }
	
	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	public IndexCounter getINodeCounter(){
		return this.iNodeCounter;
	}

	void setLastINode(int iNode){
		this.iNodeCounter = new IndexCounter(iNode);
	}
	
	int getLastINode(){
		return this.iNodeCounter.getLastIndex();
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HibRootFolder getRootFolder() {
		return rootFolder;
	}

	void setRootFolder(HibRootFolder hibRootFolder) {
		this.rootFolder = hibRootFolder;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(
				Integer.toHexString(hashCode())).append(" [");
		buffer.append("id").append("='").append(getId()).append("' ");
		buffer.append("name").append("='").append(getName()).append("' ");
		buffer.append("description").append("='").append(getDescription())
				.append("' ");
		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HibRepository))
			return false;
		HibRepository castOther = (HibRepository) other;

		return ((this.getName() == castOther.getName()) || (this.getName() != null
				&& castOther.getName() != null && this.getName().equals(
				castOther.getName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());

		return result;
	}


	public void setBuildNum(int buildNum){
		this.buildNum = buildNum;
	}
	
	public int getBuildNum(){
		return this.buildNum;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getSchemaBuildNum()
	 */
	public int getSchemaBuildNum() {
		return this.buildNum;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getFolderByPath(java.lang.String)
	 */
	public IFolder getFolderByPath(String path) {
	     if (!pathExists(path))
	    	 throw new IllegalArgumentException(PATH_DOES_NOT_EXIST);
	     return fetchFolder(rootFolder,path);
	}

	/**
	 * @param path
	 * @return
	 */
	private IFolder fetchFolder(HibFolder folder,String path) {
		if(folder.getPath().equals(path))
			return folder;
		Set<HibSubFolder>subs = folder.getSubFolders();
		for (HibSubFolder sub:subs){
			IFolder target =fetchFolder(sub, path); 
			if(target!=null){
				return target;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getFoldersByName(java.lang.String)
	 */
	public List<ISubFolder> getFoldersByName(String name) {
		if(name==null||name.equals(""))
			throw new IllegalArgumentException(ILLEGAL_SUBFOLDERNAME);
		List<ISubFolder> found = getChildrenOfFolderCalled(rootFolder,name);
		return found;
	}

	private List<ISubFolder> getChildrenOfFolderCalled(HibFolder folder , String name) {
		List <ISubFolder> found = new ArrayList<ISubFolder>();
		for (Iterator<? extends ISubFolder> it = folder.getSubFolderIterator(); it.hasNext();) {
			HibSubFolder sub = (HibSubFolder) it.next();
			if(sub.getName().equals(name))
				found.add(sub);
			found.addAll(getChildrenOfFolderCalled(sub, name));
		}
		return found;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#pathExists(java.lang.String)
	 */
	public boolean pathExists(String path) {
		if(fetchFolder(rootFolder,path)==null)
			return false;
		return true;
	}

	/**
	 * @return the folder
	 */
	public Set<HibFolder> getFolders() {
		return this.folders;
	}

	/**
	 * @param folder the folder to set
	 */
	void setFolders(Set<HibFolder> folder) {
		this.folders = folder;
	}

	/**
	 * @return the maps
	 */
	public Set<HibMap> getMaps() {
		return this.maps;
	}

	/**
	 * @param maps the maps to set
	 */
	void setMaps(Set<HibMap> maps) {
		this.maps = maps;
	}
}