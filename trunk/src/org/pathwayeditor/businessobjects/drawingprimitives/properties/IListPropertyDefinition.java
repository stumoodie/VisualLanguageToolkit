/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.util.List;

/**
 * @author smoodie
 *
 */
public interface IListPropertyDefinition extends IPropertyDefinition {

	List<String> getDefaultValue();
	
}
