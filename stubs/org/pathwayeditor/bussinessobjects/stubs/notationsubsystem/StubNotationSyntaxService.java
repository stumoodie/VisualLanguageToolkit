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
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author Stuart Moodie
 *
 */
public class StubNotationSyntaxService implements INotationSyntaxService {
	private static final int NUM_ROOT_OTS = 1;
	private static final int LABEL_ID = 9;
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
	private final Map<IPropertyDefinition, ILabelObjectType> labels;
	private final ILabelObjectType labelObjectType;
	
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
		this.labels = new HashMap<IPropertyDefinition, ILabelObjectType>();
		this.labelObjectType = new StubLabelObjectType(this, LABEL_ID);
		this.labels.put(new StubNumberPropertyDefinition(), this.labelObjectType);
		this.labels.put(new StubListPropertyDefinition(), this.labelObjectType);
		this.labels.put(new StubHtmlPropertyDefinition(), this.labelObjectType);
		this.labels.put(new StubTextPropertyDefinition(), this.labelObjectType);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#getRootMapObjectType()
	 */
	@Override
	public IRootObjectType getRootObjectType() {
		return this.rootObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#linkTypeIterator()
	 */
	@Override
	public Iterator<ILinkObjectType> linkTypeIterator() {
		return this.links.values().iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService#shapeTypeIterator()
	 */
	@Override
	public Iterator<IShapeObjectType> shapeTypeIterator() {
		return this.shapes.values().iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationService#getNotation()
	 */
	@Override
	public INotation getNotation() {
		return this.notationSubsystem.getNotation();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationService#getNotationSubsystem()
	 */
	@Override
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsLinkObjectType(int)
	 */
	@Override
	public boolean containsLinkObjectType(int uniqueID) {
		return this.links.containsKey(uniqueID);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsObjectType(int)
	 */
	@Override
	public boolean containsObjectType(int uniqueID) {
		return this.links.containsKey(uniqueID) || this.shapes.containsKey(uniqueID) || this.rootObjectType.getUniqueId() == uniqueID;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsShapeObjectType(int)
	 */
	@Override
	public boolean containsShapeObjectType(int uniqueID) {
		return this.shapes.containsKey(uniqueID);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getLinkObjectType(int)
	 */
	@Override
	public ILinkObjectType getLinkObjectType(int uniqueId) {
		ILinkObjectType retVal=this.links.get(uniqueId);
		if(retVal==null)// for object types that are present in setup data but not used in any test...
			throw new IllegalArgumentException("no object type with this uniqueId was found");
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getObjectType(int)
	 */
	@Override
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
	@Override
	public IShapeObjectType getShapeObjectType(int uniqueId) {
		IShapeObjectType retVal = this.shapes.get(uniqueId);
		if(retVal == null)
			throw new IllegalArgumentException("no object type with this uniqueId was found");

		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#objectTypeIterator()
	 */
	@Override
	public Iterator<IObjectType> objectTypeIterator() {
		Set<IObjectType> retVal = new HashSet<IObjectType>(this.shapes.values());
		retVal.addAll(this.links.values());
		retVal.add(this.rootObjectType);
		return retVal.iterator();
	}

	private <T extends IObjectType> T findObjectTypeByName(Collection<? extends T> otSet, String name){
		T retVal = null;
		for(T val : otSet){
			if(val.getName().equals(name)){
				retVal = val;
				break;
			}
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#findLinkObjectTypeByName(java.lang.String)
	 */
	@Override
	public ILinkObjectType findLinkObjectTypeByName(String name) {
		return findObjectTypeByName(this.links.values(), name);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#findShapeObjectTypeByName(java.lang.String)
	 */
	@Override
	public IShapeObjectType findShapeObjectTypeByName(String name) {
		return findObjectTypeByName(this.shapes.values(), name);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#numLinkObjectTypes()
	 */
	@Override
	public int numLinkObjectTypes() {
		return this.links.size();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#numShapeObjectTypes()
	 */
	@Override
	public int numShapeObjectTypes() {
		return this.shapes.size();
	}


	@Override
	public int numObjectTypes(){
		return this.numLinkObjectTypes() + this.numShapeObjectTypes() + NUM_ROOT_OTS;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getLabelObjectType(int)
	 */
	@Override
	public ILabelObjectType getLabelObjectType(int uniqueId) {
		return this.labelObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getLabelObjectTypeByProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	@Override
	public ILabelObjectType getLabelObjectTypeByProperty(IPropertyDefinition propDefn) {
		return this.labels.get(propDefn);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#isVisualisableProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	@Override
	public boolean isVisualisableProperty(IPropertyDefinition propDefn) {
		return true;
	}
}
