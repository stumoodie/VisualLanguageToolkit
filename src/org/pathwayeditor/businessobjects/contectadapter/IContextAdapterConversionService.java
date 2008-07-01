package org.pathwayeditor.businessobjects.contectadapter;

public interface IContextAdapterConversionService {
	/**
	 * Get Service provider which was be used to instantiate this service. 
	 * This method could be used to get access to other services, which are registered for
	 * that context. 
	 * @return An non-null service provider which this service is registered with
	 */
	IContextAdapterServiceProvider getServiceProvider();

}
