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
package org.pathwayeditor.businessobjects.notationsubsystem;

import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;

/**
 * This interfaces provides a client with access to the services provided by a context adapter. It will typically
 * be implemented behind an Eclipse extension point.  
 * @author smoodie
 *
 */
public interface INotationSubsystem {

	void registerCanvas(ICanvas canvasToRegister);
	
	void unregisterCanvas(ICanvas canvasToRegister);

	/**
	 * The context (graphical notation) that is defined by this context adapter and for which the services
	 * are specifically for.  
	 * @return A context instance, which cannot be null.
	 */
	INotation getNotation();
	
	/**
	 * Reports if the subsystem is a minimal or fallback subsystem. If it is then this subsystem should only bed used to read maps, and maps
	 * should not be created using this subsystem. It should also not provide any additional services other than a syntax service.  
	 * @return true if this is a fallback notation subsystem, false otherwise.
	 */
	boolean isFallback();
	
	/**
	 * Obtains a syntax service implementation that is used by clients (typically EPE) to define the appearance
	 * of the graphical notation and the syntactic rules of its shapes and links.
	 * @return an implementation of the syntax service, which cannot be null.
	 */
	INotationSyntaxService getSyntaxService();

	/**
	 * Obtains a set of export services from the context adapter. These provide implementations that
	 * can export a map that uses this context into different types of export format. The lists effectively
	 * informs a client with all the export services it can perform on a given context.
	 * @return A set of export services or an empty set if there are none.
	 */
	Set<INotationExportService> getExportServices();
	
	/**
	 * Obtains a set of import services from the context adapter. These provide implementations that
	 * can import information in a file and use it to create a new map that uses this context.
	 * @return A set of import services or an empty set if there are none.
	 */
	Set<INotationImportService> getImportServices();
	
	/**
	 * Obtains the autolayout services from the context adapter. This can be used by a client to automatically
	 * layout a map that uses the context supported by the context adapter.
	 * @return An auto-layout service guaranteed to be non-null.
	 */
	INotationAutolayoutService getAutolayoutService();
	
	/**
	 * Obtains a validation service that is used to validate a map.
	 * @return An instance of a validation service. Guaranteed to be non-null.
	 */
	INotationValidationService getValidationService();
	
	/**
	 * Obtains the plugin services from the context adapter. This can be used by a client to perfrom
	 * some actions using the information provided in a map that uses the context supported by this context adapter.
	 * @return A set of plugin services or an empty set if there are none.
	 */
	Set<INotationPluginService> getPluginServices();
	
	/**
	 * Obtains a set of conversion services. 
	 * @return the set of conversion services, which cannot be null.
	 */
	Set<INotationConversionService> getConversionServices();
}
