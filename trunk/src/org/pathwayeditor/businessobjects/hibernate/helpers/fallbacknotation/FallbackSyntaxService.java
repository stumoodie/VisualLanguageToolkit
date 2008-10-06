package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.ObjectTypeClassification;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;


class FallbackSyntaxService implements INotationSyntaxService {
	private final INotationSubsystem notationSubsystem;
	private final IRootObjectType rootObjectType;
	private final Map<Integer, IShapeObjectType> shapeTypes;
	private final Map<Integer, ILinkObjectType> linkTypes;
	
	public FallbackSyntaxService(INotationSubsystem provider, HibNotation hibNotation) {
		this.notationSubsystem=provider;
		this.shapeTypes = new HashMap<Integer, IShapeObjectType>();
		this.linkTypes = new HashMap<Integer, ILinkObjectType>();
		IRootObjectType rot = null;
		for(HibObjectType hibObjectType : hibNotation.getObjectTypes()){
			if(hibObjectType.getClassificationCode() == ObjectTypeClassification.ROOTOBJECT){
				rot = new FallbackRootObjectType(this, hibObjectType);
			}
			else if(hibObjectType.getClassificationCode() == ObjectTypeClassification.SHAPE){
				this.shapeTypes.put(hibObjectType.getUniqueId(), new FallbackShapeObjectType(this, hibObjectType));
			}
			else if(hibObjectType.getClassificationCode() == ObjectTypeClassification.LINK){
				this.linkTypes.put(hibObjectType.getUniqueId(), new FallbackLinkObjectType(this, hibObjectType));
			}
			else{
				throw new RuntimeException("An addition ObjectTypeClassificiation was found that this class cannot handle");
			}
		}
		this.rootObjectType = rot;
	}

	public INotationSubsystem getNotationSubsystem() {
		return notationSubsystem;
	}

	public INotation getNotation() {
		return this.notationSubsystem.getNotation();
	}

	public IRootObjectType getRootObjectType() {
		return this.rootObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsLinkObjectType(int)
	 */
	public boolean containsLinkObjectType(int uniqueID) {
		return this.linkTypes.containsKey(uniqueID);
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsObjectType(int)
	 */
	public boolean containsObjectType(int uniqueID) {
		boolean retVal = (this.rootObjectType.getUniqueId() == uniqueID)
			|| this.linkTypes.containsKey(uniqueID)
			|| this.shapeTypes.containsKey(uniqueID);
		return retVal;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsShapeObjectType(int)
	 */
	public boolean containsShapeObjectType(int uniqueID) {
		return this.shapeTypes.containsKey(uniqueID);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getLinkObjectType(int)
	 */
	public ILinkObjectType getLinkObjectType(int uniqueId) {
		ILinkObjectType retVal = this.linkTypes.get(uniqueId);
		if(retVal == null) throw new IllegalArgumentException("No link object type found that matches uniqueID: " + uniqueId);
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getObjectType(int)
	 */
	public IObjectType getObjectType(int uniqueId) {
		IObjectType retVal = null;
		if(this.linkTypes.containsKey(uniqueId)){
			retVal = this.linkTypes.get(uniqueId);
		}
		else if(this.shapeTypes.containsKey(uniqueId)){
			retVal = this.shapeTypes.get(uniqueId);
		}
		else if(this.rootObjectType.getUniqueId() == uniqueId){
			retVal = this.rootObjectType;
		}
		else{
			throw new IllegalArgumentException("An object type matching uniqueId was not found");
		}
		return retVal;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getShapeObjectType(int)
	 */
	public IShapeObjectType getShapeObjectType(int uniqueId) {
		IShapeObjectType retVal = this.shapeTypes.get(uniqueId);
		if(retVal == null) throw new IllegalArgumentException("No shape object type found that matches uniqueID: " + uniqueId);
		return retVal;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#linkTypeIterator()
	 */
	public Iterator<ILinkObjectType> linkTypeIterator() {
		return this.linkTypes.values().iterator();
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#objectTypeIterator()
	 */
	public Iterator<IObjectType> objectTypeIterator() {
		Set<IObjectType> retSet = new TreeSet<IObjectType>(this.shapeTypes.values());
		retSet.addAll(this.linkTypes.values());
		retSet.add(this.rootObjectType);
		return retSet.iterator();
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#shapeTypeIterator()
	 */
	public Iterator<IShapeObjectType> shapeTypeIterator() {
		return this.shapeTypes.values().iterator();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.notationSubsystem == null) ? 0 : this.notationSubsystem
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FallbackSyntaxService))
			return false;
		FallbackSyntaxService other = (FallbackSyntaxService) obj;
		if (this.notationSubsystem == null) {
			if (other.notationSubsystem != null)
				return false;
		} else if (!this.notationSubsystem.equals(other.notationSubsystem))
			return false;
		return true;
	}

}
