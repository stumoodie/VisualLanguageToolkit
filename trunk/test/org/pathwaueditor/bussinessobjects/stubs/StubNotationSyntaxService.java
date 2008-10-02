/**
 * 
 */
package org.pathwaueditor.bussinessobjects.stubs;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.contextadapter.INotation;
import org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem;
import org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
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
	public IRootObjectType getRootMapObjectType() {
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

}
