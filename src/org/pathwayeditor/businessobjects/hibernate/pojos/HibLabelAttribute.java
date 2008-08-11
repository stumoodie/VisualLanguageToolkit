package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;


public class HibLabelAttribute  implements Serializable, ILabelAttribute {
	private static final long serialVersionUID = -2354270083525870259L;

     private Long id;
     private HibCanvas hibCanvas;
     private int creation_serial;
     private int XPosition;
     private int YPosition;
     private int width;
     private int height;
     private HibProperty visualisableProperty;
     private boolean isDisplayed;
     private int backgroundRed;
     private int backgroundGreen;
     private int backgroundBlue;
     private boolean noFillSet;
     private HibLabelNode labelNode ;

    public HibLabelAttribute() {
    }

    public HibLabelAttribute(HibCanvas hibCanvas, int creation_serial, int XPosition, int YPosition, int width, int height, HibProperty visualisableProperty, boolean isDisplayed, int backgroundRed, int backgroundGreen, int backgroundBlue, boolean noFillSet) {
       this.hibCanvas = hibCanvas;
       this.creation_serial = creation_serial;
       this.XPosition = XPosition;
       this.YPosition = YPosition;
       this.width = width;
       this.height = height;
       this.visualisableProperty = visualisableProperty;
       this.isDisplayed = isDisplayed;
       this.backgroundRed = backgroundRed;
       this.backgroundGreen = backgroundGreen;
       this.backgroundBlue = backgroundBlue;
       this.noFillSet = noFillSet;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public HibCanvas getCanvas() {
        return this.hibCanvas;
    }
    
    public void setCanvas(HibCanvas hibCanvas) {
        this.hibCanvas = hibCanvas;
    }
    public int getCreation_serial() {
        return this.creation_serial;
    }
    
    public void setCreation_serial(int label_index) {
        this.creation_serial = label_index;
    }
    public int getXPosition() {
        return this.XPosition;
    }
    
    public HibLabelNode getLabelNode() {
		return this.labelNode;
	}

	public void setLabelNode(HibLabelNode nodeId) {
		this.labelNode = nodeId;
	}

	public void setXPosition(int XPosition) {
        this.XPosition = XPosition;
    }
    public int getYPosition() {
        return this.YPosition;
    }
    
    public void setYPosition(int YPosition) {
        this.YPosition = YPosition;
    }
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    public HibProperty getVisualisableProperty() {
        return this.visualisableProperty;
    }
    
    public void setVisualisableProperty(HibProperty visualisableProperty) {
        this.visualisableProperty = visualisableProperty;
    }
    public boolean isIsDisplayed() {
        return this.isDisplayed;
    }
    
    public void setIsDisplayed(boolean isDisplayed) {
        this.isDisplayed = isDisplayed;
    }
    public int getBackgroundRed() {
        return this.backgroundRed;
    }
    
    public void setBackgroundRed(int backgroundRed) {
        this.backgroundRed = backgroundRed;
    }
    public int getBackgroundGreen() {
        return this.backgroundGreen;
    }
    
    public void setBackgroundGreen(int backgroundGreen) {
        this.backgroundGreen = backgroundGreen;
    }
    public int getBackgroundBlue() {
        return this.backgroundBlue;
    }
    
    public void setBackgroundBlue(int backgroundBlue) {
        this.backgroundBlue = backgroundBlue;
    }
    public boolean isNoFillSet() {
        return this.noFillSet;
    }
    
    public void setNoFillSet(boolean noFillSet) {
        this.noFillSet = noFillSet;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HibLabelAttribute) ) return false;
		 HibLabelAttribute castOther = ( HibLabelAttribute ) other; 
         
		 return ( (this.getCanvas()==castOther.getCanvas()) || ( this.getCanvas()!=null && castOther.getCanvas()!=null && this.getCanvas().equals(castOther.getCanvas()) ) )
 && (this.getCreation_serial()==castOther.getCreation_serial());
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getCanvas() == null ? 0 : this.getCanvas().hashCode() );
         result = 37 * result + this.getCreation_serial();
         return result;
   }

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getLocation()
 */
public Location getLocation() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getProperty()
 */
public IAnnotationProperty getProperty() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getSize()
 */
public Size getSize() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getFirstObject()
 */
public IZOrderedObject getFirstObject() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getLastObject()
 */
public IZOrderedObject getLastObject() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getNextObject()
 */
public IZOrderedObject getNextObject() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getPreviousObject()
 */
public IZOrderedObject getPreviousObject() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObject#getCreationSerial()
 */
public int getCreationSerial() {
	// TODO Auto-generated method stub
	return 0;
}   

}

