/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author Stuart Moodie
 *
 */
public class StubShapeAObjectType implements IShapeObjectType {
	public static final int UNIQUE_ID = 1;
	public static final String DESCN = "Shape A Test ObjectType";
	public static final String NAME = "Shape A";
	public static final EnumSet<EditableShapeAttributes> EDITABLE_ATTRIBUTES = EnumSet.of(EditableShapeAttributes.FILL_COLOUR, EditableShapeAttributes.LINE_COLOUR);
	private final INotationSyntaxService syntaxService;
	private final IShapeAttributeDefaults attributeDefaults;
	private final int id = UNIQUE_ID;
	
	/**
	 * @param stubNotationSyntaxService
	 */
	public StubShapeAObjectType(StubNotationSyntaxService stubNotationSyntaxService) {
		this.syntaxService = stubNotationSyntaxService;
		this.attributeDefaults = new StubShapeAttributeDefaultsWithPlainText();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getDefaultAttributes()
	 */
	@Override
	public IShapeAttributeDefaults getDefaultAttributes() {
		return this.attributeDefaults;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getEditableAttributes()
	 */
	@Override
	public EnumSet<EditableShapeAttributes> getEditableAttributes() {
		return EDITABLE_ATTRIBUTES;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getParentingRules()
	 */
	@Override
	public IObjectTypeParentingRules getParentingRules() {
		return new IObjectTypeParentingRules(){

			@Override
			public IShapeObjectType getObjectType() {
				return StubShapeAObjectType.this;
			}

			@Override
			public boolean isValidChild(IObjectType possibleChild) {
				return StubShapeBObjectType.UNIQUE_ID == possibleChild.getUniqueId()
					|| StubShapeCObjectType.UNIQUE_ID == possibleChild.getUniqueId()
					|| StubShapeDObjectType.UNIQUE_ID == possibleChild.getUniqueId();
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getUniqueId()
	 */
	@Override
	public int getUniqueId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	@Override
	public String getDescription() {
		return DESCN;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
	 */
	@Override
	public INotationSyntaxService getSyntaxService() {
		return this.syntaxService;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IObjectType o) {
		return this.getUniqueId() < o.getUniqueId() ? -1 : this.getUniqueId() > o.getUniqueId() ? 1 : 0;
	}

}
