/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

/**
 * @author smoodie
 *
 */
public interface ISyntaxDependentObjectProvider {

	void setSyntaxMappingFactory(ISyntaxMappingFactory mappingFactory);
	
	ISyntaxMappingFactory getSyntaxMappingFactory();
}
