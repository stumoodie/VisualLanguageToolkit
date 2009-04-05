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

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;


/**
	 * Calculates the initial position of a label on a link. This will offset
	 * the label from the centre of the link by the value of
	 * OFFSET_FROM_LINK_MIDPOINT
	 * 
	 * @author Richard Adams/Stuart Moodie
	 * 
	 */
	public class LinkLabelLocationPolicy implements ILabelLocationPolicy {
	
		ILinkAttribute link;
		public final int OFFSET_FROM_LINK_MIDPOINT = 10;
		boolean isInitialised;

		public LinkLabelLocationPolicy(ILinkAttribute link) {
			this.link = link;
		}

		/**
		 * Side effect is to set the location of the link - this is for
		 * reference only and does not trigger a refresh of the connection
		 * visuals
		 */
		public Location nextLabelLocation(){
			Location srcCentre = link.getSourceTerminus().getLocation();
			Location targCentre = link.getTargetTerminus().getLocation();
			Location loc = srcCentre.getMidPoint(targCentre);
			Location translated = loc.translate(OFFSET_FROM_LINK_MIDPOINT,
					OFFSET_FROM_LINK_MIDPOINT);
			return translated;

		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.graphics.ILabelLocationPolicy#getOwner()
		 */
		public ILinkAttribute getOwner() {
			return this.link;
		}
	}