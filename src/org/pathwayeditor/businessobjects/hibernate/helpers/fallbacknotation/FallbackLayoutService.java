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
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;


class FallbackLayoutService implements INotationAutolayoutService {

	public FallbackLayoutService(INotationSubsystem provider) {
		this.serviceProvider=provider;
	}
	private INotationSubsystem serviceProvider;

	public INotationSubsystem getNotationSubsystem() {
		return serviceProvider;
	}

	public INotation getNotation() {
		return serviceProvider.getNotation();
	}

	public boolean isImplemented() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService#layout(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public void layout(ICanvas canvas) {
		// TODO Auto-generated method stub
		
	}


}
