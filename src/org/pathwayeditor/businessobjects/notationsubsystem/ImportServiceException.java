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
 * @author smoodie
 *
 */
public class ImportServiceException extends Exception {
	private static final long serialVersionUID = 5761660422270148685L;

	public ImportServiceException() {
		super();
	}

	public ImportServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImportServiceException(String message) {
		super(message);
	}

	public ImportServiceException(Throwable cause) {
		super(cause);
	}

}
