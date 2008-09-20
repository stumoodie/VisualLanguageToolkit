/**
 * 
 */
package org.pathwayeditor.businessobjects.contextadapter;

/**
 * @author smoodie
 *
 */
public interface INotationService {
	/**
	 * 
	 * @return the {@link INotation} to which this {@link ExportServiceException} service belongs.
	 *  Must not return  null
	 */
	INotation getNotation();

	/**
	 * Get Service provider which was be used to instantiate this service. 
	 * This method could be used to get access to other services, which are registered for
	 * that context. 
	 * @return An non-null service provider which this service is registered with
	 */
	INotationSubsystem getNotationSubsystem();
	
}
