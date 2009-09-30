/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.hyperlink.IRepositoryLookupService;

/**
 * @author smoodie
 *
 */
public interface IRepositoryServices {

	IRepositoryManagerBuilder getRepositoryBuilder();
	
	IRepositoryLookupService getRepositoryLookup();
	
}
