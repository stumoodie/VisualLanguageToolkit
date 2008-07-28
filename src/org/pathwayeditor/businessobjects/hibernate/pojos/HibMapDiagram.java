package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.UUID;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;

public class HibMapDiagram  implements  IMap,
		java.io.Serializable {
	private static final long serialVersionUID = -7566323206185334088L;

	private Long id;
	private HibFolder folder;
	private String name = "";
	private String description = "";
	private HibRepository repository;
	private int iNode = makeIntUUID();

	HibMapDiagram() {
	}

	/**
	 * @return a int representation of the first 8 digits in a real UUID
	 */
	private int makeIntUUID() { // FIXME - this IS NOT GUARANTEED
								// UNIQUE!!!!!!!!!!!!!
		Long tempL = UUID.randomUUID().getMostSignificantBits();
		return tempL.intValue();
	}

	public HibMapDiagram(HibFolder hibFolder, String name) {
		this.folder = hibFolder;
		this.name = name;
		hibFolder.addMapDiagram(this);
		this.repository = hibFolder.getRepository();
	}

	public HibMapDiagram(HibFolder newParent, HibMapDiagram other) {
		this.folder = newParent;
		this.name = other.name;
		this.description = other.description;
		this.repository = newParent.getRepository();
	}

	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setRepository(HibRepository repository) {
		this.repository = repository;
	}

	public HibRepository getRepository() {
		return repository;
	}

	public HibFolder getFolder() {
		return this.folder;
	}

	void setFolder(HibFolder hibFolder) {
		this.folder = hibFolder;
	}

	public void changeFolder(HibFolder newFolder) {
		HibFolder oldOwner = this.folder;
		this.folder = newFolder;
		if (oldOwner != null) {
			oldOwner.getMapDiagrams().remove(this);
		}
		if (this.folder != null) {
			this.folder.getMapDiagrams().add(this);
		}
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

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HibMapDiagram))
			return false;
		HibMapDiagram castOther = (HibMapDiagram) other;
		HibFolder otherFolder = castOther.getFolder();
		HibFolder myFolder = getFolder();
		String myName = this.getName();
		String otherName = castOther.getName();

		return (foldersSame(otherFolder, myFolder))
				&& (namesSame(myName, otherName));
	}

	private boolean namesSame(String myName, String otherName) {
		return (myName == otherName)
				|| (myName != null && otherName != null && myName
						.equals(otherName));
	}

	private boolean foldersSame(HibFolder otherFolder, HibFolder myFolder) {

		if (myFolder == otherFolder)
			return true;
		if (myFolder == null && otherFolder == null)
			return true;
		if (myFolder != null && otherFolder != null) {
			boolean toRet = false;
			try {
				toRet = myFolder.equals(otherFolder);
				return toRet;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFolder() == null ? 0 : this.getFolder().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());

		return result;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getOwner()
	 */
	public IFolder getOwner() {
		if (folder == null||!folder.containsMap(this))
			throw new IllegalArgumentException();
		return folder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getINode()
	 */
	public int getINode() {
		return iNode;
	}
}
