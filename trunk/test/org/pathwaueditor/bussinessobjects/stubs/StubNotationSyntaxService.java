/**
 * 
 */
package org.pathwaueditor.bussinessobjects.stubs;

import java.util.Iterator;

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
	
	public StubNotationSyntaxService(INotationSubsystem notationSubsystem){
		this.notationSubsystem = notationSubsystem;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#getRootMapObjectType()
	 */
	public IRootObjectType getRootObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#linkTypeIterator()
	 */
	public Iterator<ILinkObjectType> linkTypeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#shapeTypeIterator()
	 */
	public Iterator<IShapeObjectType> shapeTypeIterator() {
		// TODO Auto-generated method stub
		return null;
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
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#getShapeObjectType(java.lang.String)
	 */
	public IShapeObjectType getShapeObjectType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsLinkObjectType(int)
	 */
	public boolean containsLinkObjectType(int uniqueID) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsObjectType(int)
	 */
	public boolean containsObjectType(int uniqueID) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsShapeObjectType(int)
	 */
	public boolean containsShapeObjectType(int uniqueID) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getLinkObjectType(int)
	 */
	public ILinkObjectType getLinkObjectType(int uniqueId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getObjectType(int)
	 */
	public IObjectType getObjectType(int uniqueId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getShapeObjectType(int)
	 */
	public IShapeObjectType getShapeObjectType(int uniqueId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#objectTypeIterator()
	 */
	public Iterator<IObjectType> objectTypeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
