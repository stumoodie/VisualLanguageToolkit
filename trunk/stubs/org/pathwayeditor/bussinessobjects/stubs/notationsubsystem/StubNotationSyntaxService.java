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
	private final IShapeObjectType shapeAParentOfAllObjectType;
	private final IShapeObjectType shapeBChildOfAllObjectType;
	private final IShapeObjectType shapeCParentOfShapeDObjectType;
	private final IShapeObjectType shapeDChildOfShapeCObjectType;
	private final ILinkObjectType linkAConnectsShapesBToCAndDObjectType;
	private final ILinkObjectType linkBConnectsShapesCToBObjectType;
	private final Map<Integer, IShapeObjectType> shapes;
	private final Map<Integer, ILinkObjectType> links;
	
	public StubNotationSyntaxService(INotationSubsystem notationSubsystem){
		this.notationSubsystem = notationSubsystem;
		this.rootObjectType = new StubRootObjectType(this);
		this.shapeAParentOfAllObjectType = new StubShapeAParentOfAllObjectType(this);
		this.shapeBChildOfAllObjectType = new StubShapeBChildOfAllObjectType(this);
		this.shapeCParentOfShapeDObjectType = new StubShapeCParentOfShapeDObjectType(this);
		this.shapeDChildOfShapeCObjectType = new StubShapeDChildOfShapeCObjectType(this);
		this.linkAConnectsShapesBToCAndDObjectType = new StubLinkAConnectsShapesBToCAndDObjectType(this);
		this.linkBConnectsShapesCToBObjectType = new StubLinkBConnectsShaesCToBObjectType(this);
		this.shapes = new HashMap<Integer, IShapeObjectType>();
		this.shapes.put(this.shapeAParentOfAllObjectType.getUniqueId(), this.shapeAParentOfAllObjectType);
		this.shapes.put(this.shapeBChildOfAllObjectType.getUniqueId(), this.shapeBChildOfAllObjectType);
		this.shapes.put(this.shapeCParentOfShapeDObjectType.getUniqueId(), this.shapeCParentOfShapeDObjectType);
		this.shapes.put(this.shapeDChildOfShapeCObjectType.getUniqueId(), this.shapeDChildOfShapeCObjectType);
		this.links = new HashMap<Integer, ILinkObjectType>();
		this.links.put(this.linkAConnectsShapesBToCAndDObjectType.getUniqueId(), this.linkAConnectsShapesBToCAndDObjectType);
		this.links.put(this.linkBConnectsShapesCToBObjectType.getUniqueId(), this.linkBConnectsShapesCToBObjectType);
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
		return this.links.get(uniqueId);
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
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getShapeObjectType(int)
	 */
	public IShapeObjectType getShapeObjectType(int uniqueId) {
		return this.shapes.get(uniqueId);
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
