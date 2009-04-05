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

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;


/**
 * Places labels at compass points surrounding the bounding box of the 
 * map object to be labelled. Once  the initial 8 points have been filled,
 * labels are placed further out from the shape to be labelled.
 * If a laid out label is subsequently moved further  away from its original position
 * than getDefaultTolerance(), the position is regarded as free and can be occupied by new labels.
 * 
 * The initial default Tolerance is 2 pixels.
 * 
 * @author Richard Adams/Stuart Moodie
 *
 */
public class CompassLabelPositionPolicy implements ILabelLocationPolicy {
    private static final int MAX_MULTIPLIER = 200;
	private static final int INIT_MULTIPLIER = 1;
	private static int X_OFFSET = 20;
    private static int Y_OFFSET = 20;
    
    // if a label is within this distance of calculated location, is considered
    // to be at that location
    private int tolerance = 2;
 	private final ILabelCommand []  loci;
 	private final IShapeAttribute mo;
	
    
    /**
     * Inner interface defines algorithms to place labels at particular locations
     * @author Richard Adams
     *
     */
    interface ILabelCommand {
		Location getLabelLocation(int multiplier);
	}
	private static ILabelCommand N,E, W, S, NE, SE, SW, NW;
	

	public CompassLabelPositionPolicy(final IShapeAttribute shapeAttribute){
		this.mo = shapeAttribute;
		N = new ILabelCommand (){
			public Location getLabelLocation(int multiplier) {
				Bounds b = new Bounds(mo.getLocation(), mo.getSize());
				return new Location(b.getLocation().getX() + b.getSize().getWidth()/2, 
						            b.getLocation().getY() - Y_OFFSET *multiplier); 
			}
			
		};
		
		E = new ILabelCommand (){
			public Location getLabelLocation(int multiplier) {
				Bounds b = new Bounds(mo.getLocation(), mo.getSize());
				return new Location(b.getTopRight().getX() + X_OFFSET*multiplier, 
						            b.getLocation().getY() + b.getHeight()/2); 
			}
			
		};
		
		S = new ILabelCommand (){
			public Location getLabelLocation(int multiplier) {
				Bounds b = new Bounds(mo.getLocation(), mo.getSize());
				return new Location(b.getX() + b.getWidth()/2, 
						            b.getBottomLeft().getY() + Y_OFFSET*multiplier); 
			}
		};
		W = new ILabelCommand (){
				public Location getLabelLocation(int multiplier) {
					Bounds b = new Bounds(mo.getLocation(), mo.getSize());
					return new Location(b.getX() - X_OFFSET*multiplier, 
							            b.getY() + b.getHeight()/2); 
				}
			
		};
		NE = new ILabelCommand (){
			public Location getLabelLocation(int multiplier) {
				Bounds b = new Bounds(mo.getLocation(), mo.getSize());
				return b.getTopRight().translate((X_OFFSET/2)*multiplier, - (Y_OFFSET/2)*multiplier); 
			}
			
		};
		
		SE = new ILabelCommand (){
			public Location getLabelLocation(int multiplier) {
				Bounds b = new Bounds(mo.getLocation(), mo.getSize());
				return b.getBottomRight().translate((X_OFFSET/2)*multiplier, (Y_OFFSET/2)*multiplier); 
			}
			
		};
		
		SW = new ILabelCommand (){
			public Location getLabelLocation(int multiplier) {
				Bounds b = new Bounds(mo.getLocation(), mo.getSize());
				return b.getBottomLeft().translate(-(X_OFFSET/2)*multiplier, (Y_OFFSET/2)*multiplier);
			}
			
		};
		
		NW = new ILabelCommand (){
			public Location getLabelLocation(int multiplier) {
				Bounds b = new Bounds(mo.getLocation(), mo.getSize());
				return b.getTopLeft().translate(-(X_OFFSET/2)*multiplier, -(Y_OFFSET/2)*multiplier );
			}
			
		};
		this.loci  = new ILabelCommand[]{N, E, W, S, NE, SE,SW, NW};
	}
	
	public Location nextLabelLocation(){
		Location retVal = null;
		for(int m = INIT_MULTIPLIER; m < MAX_MULTIPLIER && retVal == null; m++){
			for (int i = 0; i< loci.length && retVal == null;i++) {
				Location loc= loci[i].getLabelLocation(m);
				if(isNewLocation(loc)){
					retVal = loc;
				}
			}
		}
		return retVal;
	}
	
	
	
	private boolean isNewLocation(Location newloc) {
		Iterator<IAnnotationProperty> iter = this.mo.propertyIterator();
		boolean retVal = true;
		while(iter.hasNext() && retVal){
			IAnnotationProperty prop = iter.next();
			if(prop.isDisplayed()){
				ILabelAttribute label = prop.getDisplayedLabel();
				Location labLoc = label.getLocation();
				if(labLoc.getDistance(newloc) < getTolerance()) {
					retVal = false;
				}
			}
		}
		return retVal;
	}

	private int getTolerance() {
		return tolerance;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.graphics.ILabelLocationPolicy#getOwner()
	 */
	public IShapeAttribute getOwner() {
		return this.mo;
	}

//	public void setTolerance(int defaultTolerance) {
//		this.tolerance = defaultTolerance;
//	}
//
//
//
//	public boolean isInitialised() {
//		return isInitialized;
//	}



//	public void updateLabelLocation(List<ILabelAttribute> labellable) {
//		
//		for (ILabelAttribute label : labellable) {
//			Bounds b = new Bounds(label.getLocation(), label.getSize());
//			label.setLocation(b.getLocation().translate(label.getOffset()));
//		}
//	}

	 

}
