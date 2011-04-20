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


package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectTypeParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

/**
 * @author Stuart Moodie
 *
 */
public class StubLabelObjectType implements ILabelObjectType {
	private static final String NAME = "labelObjectType";
	private static final String DESCN = "Label Object Type";
	private final INotationSyntaxService syntaxService;
	private final int uniqueId;
	private final String name;
	private final String descn;
	private final ILabelAttributeDefaults attDefaults;
	
	
	public StubLabelObjectType(INotationSyntaxService syntaxService, int uniqueId){
		this.syntaxService = syntaxService;
		this.uniqueId = uniqueId;
		this.name = NAME;
		this.descn = DESCN;
		this.attDefaults = new StubLabelAttributeDefaults();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
	 */
	@Override
	public INotationSyntaxService getSyntaxService() {
		return this.syntaxService;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getUniqueId()
	 */
	@Override
	public int getUniqueId() {
		return this.uniqueId;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.descn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getParentingRules()
	 */
	@Override
	public ILabelObjectTypeParentingRules getParentingRules() {
		return new ILabelObjectTypeParentingRules(){

			@Override
			public ILabelObjectType getObjectType() {
				return StubLabelObjectType.this;
			}

			@Override
			public boolean isValidChild(IObjectType possibleChild) {
				return true;
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IObjectType other) {
		int retVal = this.uniqueId < other.getUniqueId() ? -1 : (this.uniqueId > other.getUniqueId() ? 1 : 0);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelObjectType#getDefaultAttributes()
	 */
	@Override
	public ILabelAttributeDefaults getDefaultAttributes() {
		return this.attDefaults;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelObjectType#isAlwaysDisplayed()
	 */
	@Override
	public boolean isAlwaysDisplayed() {
		return true;
	}

}
