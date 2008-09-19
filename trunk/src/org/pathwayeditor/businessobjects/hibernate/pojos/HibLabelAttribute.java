package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IDefaultLabelAttributes;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;


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
     private INodeObjectType objectType;

    public HibLabelAttribute() {
    }

    public HibLabelAttribute(HibCanvas hibCanvas, HibProperty property, IDefaultLabelAttributes labelDefaults) {
       this.hibCanvas = hibCanvas;
       this.creation_serial = hibCanvas.getAttributeSerialCounter().nextIndex();
       this.visualisableProperty = property;
       this.objectType = new LabelObjectType(hibCanvas.getContext());
       populateDefaults(labelDefaults);
    }

    private void populateDefaults(IDefaultLabelAttributes labelDefaults){
    	this.XPosition = 0;
    	this.YPosition = 0;
    	this.setSize(labelDefaults.getSize());
    	this.setBackgroundColor(labelDefaults.getFillColour());
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
        this.objectType = new LabelObjectType(this.hibCanvas.getContext());
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
	return new Location ( this.XPosition , this.YPosition );
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getProperty()
 */
public IAnnotationProperty getProperty() {
	return this.visualisableProperty ;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getSize()
 */
public Size getSize() {
	return new Size ( this.height , this.width);
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
	return this.creation_serial;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getBackgroundColor()
 */
public RGB getBackgroundColor() {
	return new RGB ( this.backgroundRed , this.backgroundGreen , this.backgroundRed);
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#isDisplayed()
 */
public boolean isDisplayed() {
	return this.isDisplayed;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#isFillSet()
 */
public boolean isFillSet() {
	return this.noFillSet;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setBackgroundColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
 */
public void setBackgroundColor(RGB color) {
	if ( color == null )
		throw new IllegalArgumentException ("Color cannot be null.") ;
	
	this.backgroundBlue = color.getBlue() ;
	this.backgroundGreen = color.getGreen() ;
	this.backgroundRed = color.getRed() ;
	
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setDisplayed(boolean)
 */
public void setDisplayed(boolean displayed) {
	this.isDisplayed = displayed ;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setFillSet(boolean)
 */
public void setFillSet(boolean fillSet) {
	this.noFillSet = fillSet ;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
 */
public void setLocation(Location location) {
	if ( location == null )
		throw new IllegalArgumentException ( "location cannot be null.") ;
	
	this.XPosition = location.getX() ; 
	this.YPosition = location.getY() ; 
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
 */
public void setSize(Size size) {
	if ( size == null )
		throw new IllegalArgumentException ("size cannot be null.") ;
	
	this.height = size.getHeight() ;
	this.width = size.getWidth() ;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getObjectType()
 */
public INodeObjectType getObjectType() {
	return this.objectType;
}

/* (non-Javadoc)
 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#hasProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
 */
public boolean hasProperty(IPropertyDefinition property) {
	// TODO Auto-generated method stub
	return false;
}   

}


