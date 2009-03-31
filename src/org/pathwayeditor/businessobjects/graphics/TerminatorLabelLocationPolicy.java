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