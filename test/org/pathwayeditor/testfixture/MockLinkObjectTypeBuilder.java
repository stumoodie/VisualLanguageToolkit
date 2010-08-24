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

package org.pathwayeditor.testfixture;

import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.not;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class MockLinkObjectTypeBuilder {
	private final Mockery mockery;
	private IShapeObjectType[] sources;
	private IShapeObjectType[] targets;
	private ILinkObjectType objectType;
	private ILinkConnectionRules connectionRules;
	private int index;
	private INotationSyntaxService syntaxService;

	public MockLinkObjectTypeBuilder(Mockery mock, INotationSyntaxService syntaxService, int idx, ILinkObjectType linkObjectType, ILinkConnectionRules connectionRules){
		this.mockery = mock;
		this.objectType = linkObjectType;
		this.connectionRules = connectionRules;
		this.index = idx;
		this.syntaxService = syntaxService;
	}
	
	
	public void setSources(IShapeObjectType ... sources){
		this.sources = sources;
	}
	
	public void setTargets(IShapeObjectType ... targets){
		this.targets = targets;
	}
	
	
	public void build(){
		this.mockery.checking(new Expectations(){{
			allowing(objectType).getUniqueId(); will(returnValue(index));
			allowing(objectType).getSyntaxService(); will(returnValue(syntaxService));
			allowing(objectType).getName(); will(returnValue("rootName"));
			allowing(objectType).getDescription(); will(returnValue("Descn"));
			allowing(objectType).getLinkConnectionRules(); will(returnValue(connectionRules));
			
			allowing(connectionRules).isValidSource(with(isOneOf(sources))); will(returnValue(true));
			allowing(connectionRules).isValidSource(with(not(isOneOf(sources)))); will(returnValue(false));
			allowing(connectionRules).isValidTarget(with(isOneOf(sources)), with(isOneOf(targets))); will(returnValue(true));
			allowing(connectionRules).isValidTarget(with(not(isOneOf(sources))), with(any(IShapeObjectType.class))); will(returnValue(false));
			allowing(connectionRules).isValidTarget(with(isOneOf(sources)), with(not(isOneOf(targets)))); will(returnValue(false));
		}});
	}
	
	public ILinkObjectType getObjectType(){
		return this.objectType;
	}
}
