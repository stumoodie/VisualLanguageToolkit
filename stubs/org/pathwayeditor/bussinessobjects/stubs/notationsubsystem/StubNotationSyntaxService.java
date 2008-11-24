/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class StubNotationSyntaxService implements INotationSyntaxService {
	private final INotationSubsystem notationSubsystem ;
	private final IRootObjectType rootObjectType;
	private final IShapeObjectType shapeAObjectType;
	private final IShapeObjectType shapeBObjectType;
	private final IShapeObjectType shapeCObjectType;
	private final IShapeObjectType shapeDObjectType;
	private final ILinkObjectType linkAObjectType;
	private final ILinkObjectType linkBObjectType;
	private final ILinkObjectType linkCObjectType;
	private final ILinkObjectType linkDObjectType;
	private final Map<Integer, IShapeObjectType> shapes;
	private final Map<Integer, ILinkObjectType> links;
	
	public StubNotationSyntaxService(INotationSubsystem notationSubsystem){
		this.notationSubsystem = notationSubsystem;
		this.rootObjectType = new StubRootObjectType(this);
		this.shapeAObjectType = new StubShapeAObjectType(this);
		this.shapeBObjectType = new StubShapeBObjectType(this);
		this.shapeCObjectType = new StubShapeCObjectType(this);
		this.shapeDObjectType = new StubShapeDObjectType(this);
		this.linkAObjectType = new StubLinkAObjectType(this);
		this.linkBObjectType = new StubLinkBObjectType(this);
		this.linkCObjectType = new StubLinkCObjectType(this);
		this.linkDObjectType = new StubLinkDObjectType(this);
		this.shapes = new HashMap<Integer, IShapeObjectType>();
		this.shapes.put(this.shapeAObjectType.getUniqueId(), this.shapeAObjectType);
		this.shapes.put(this.shapeBObjectType.getUniqueId(), this.shapeBObjectType);
		this.shapes.put(this.shapeCObjectType.getUniqueId(), this.shapeCObjectType);
		this.shapes.put(this.shapeDObjectType.getUniqueId(), this.shapeDObjectType);
		this.links = new HashMap<Integer, ILinkObjectType>();
		this.links.put(this.linkAObjectType.getUniqueId(), this.linkAObjectType);
		this.links.put(this.linkBObjectType.getUniqueId(), this.linkBObjectType);
		this.links.put(this.linkCObjectType.getUniqueId(), this.linkCObjectType);
		this.links.put(this.linkDObjectType.getUniqueId(), this.linkDObjectType);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#getRootMapObjectType()
	 */
	public IRootObjectType getRootObjectType() {
		return this.rootObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#linkTypeIterator()
	 */
	public Iterator<ILinkObjectType> linkTypeIterator() {
		return this.links.values().iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#shapeTypeIterator()
	 */
	public Iterator<IShapeObjectType> shapeTypeIterator() {
		return this.shapes.values().iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationService#getNotation()
	 */
	public INotation getNotation() {
		return this.notationSubsystem.getNotation();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationService#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsLinkObjectType(int)
	 */
	public boolean containsLinkObjectType(int uniqueID) {
		return this.links.containsKey(uniqueID);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsObjectType(int)
	 */
	public boolean containsObjectType(int uniqueID) {
		return this.links.containsKey(uniqueID) || this.shapes.containsKey(uniqueID) || this.rootObjectType.getUniqueId() == uniqueID;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsShapeObjectType(int)
	 */
	public boolean containsShapeObjectType(int uniqueID) {
		return this.shapes.containsKey(uniqueID);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getLinkObjectType(int)
	 */
	public ILinkObjectType getLinkObjectType(int uniqueId) {
		ILinkObjectType retVal=this.links.get(uniqueId);
		if(retVal==null)// for object types that are present in setup data but not used in any test...
			throw new IllegalArgumentException("no object type with this uniqueId was found");
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getObjectType(int)
	 */
	public IObjectType getObjectType(int uniqueId) {
		IObjectType retVal = this.shapes.get(uniqueId);
		if(retVal == null){
			retVal = this.links.get(uniqueId);
			if(retVal == null && this.rootObjectType.getUniqueId() == uniqueId){
				retVal = this.rootObjectType;
			}
			else if( retVal==null) // for object types that are present in setup data but not used in any test...
				throw new IllegalArgumentException("no object type with this uniqueId was found");
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getShapeObjectType(int)
	 */
	public IShapeObjectType getShapeObjectType(int uniqueId) {
		IShapeObjectType retVal = this.shapes.get(uniqueId);
		if(retVal == null)
			throw new IllegalArgumentException("no object type with this uniqueId was found");

		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#objectTypeIterator()
	 */
	public Iterator<IObjectType> objectTypeIterator() {
		Set<IObjectType> retVal = new HashSet<IObjectType>(this.shapes.values());
		retVal.addAll(this.links.values());
		retVal.add(this.rootObjectType);
		return retVal.iterator();
	}

}
