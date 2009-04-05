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
package org.pathwayeditor.businessobjects.notationsubsystem;

/**
 * A service provided by a notation subsystem. 
 * Services provided by a notation subsystem should implement an interface that extends this interface. 
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
