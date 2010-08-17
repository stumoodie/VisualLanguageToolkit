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
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;

/**
 * @author nhanlon
 *         Given a Canvas, iterate through its associated LinkAttributes, ensuring they (and their entities) are initialised by Hibernate and
 *         then injecting their properties with corresponding property definitions
 */
public interface IAttributesForCanvasBuilder {
	
	/**
	 * @param hibCanvas
	 * Given a Canvas, iterate through its associated Attributes, ensuring they (and their entities) are initialised by Hibernate
	 */
	public void initAttributes(HibCanvas hibCanvas);
	
//	/**
//	 * @param objectType not null but may not have any property definition
//	 * @param shapeAttr not null but may not have any properties
//	 *  Iterate through all the property definitions for a given objectType and inject them into their corresponding properties
//	 *  which are attached to a given ICanvasAttribute.
//	 */
//	public void injectPropertyDefinitions(IObjectType objectType, ICanvasAttribute shapeAttr);

}
