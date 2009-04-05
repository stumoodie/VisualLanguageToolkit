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
package org.pathwayeditor.businessobjects.graphics;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;


public class TerminatorLabelLocationPolicy implements ILabelLocationPolicy {
        private ILinkTerminus terminus;
        
		public TerminatorLabelLocationPolicy(ILinkTerminus model) {
			this.terminus = model;
		}

		public Location nextLabelLocation() {
			Location rc;
			Location thisCentre = terminus.getLocation();
			Location otherCentre;
			if(terminus.getLinkTermType().equals(LinkTermType.SOURCE)){
				otherCentre = terminus.getOwningLink().getTargetTerminus().getLocation();
			} else {
				otherCentre = terminus.getOwningLink().getSourceTerminus().getLocation();
			}
			rc = new Location( (thisCentre.getX() + (int)((otherCentre.getX() - thisCentre.getX()) *0.2 )),
					(thisCentre.getY() + (int)((otherCentre.getY() - thisCentre.getY()) *0.2 ))); 
			return rc;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.graphics.ILabelLocationPolicy#getOwner()
		 */
		public ILinkTerminus getOwner() {
			return this.terminus;
		}


	}