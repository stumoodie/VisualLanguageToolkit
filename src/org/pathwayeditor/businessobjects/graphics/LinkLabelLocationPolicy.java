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