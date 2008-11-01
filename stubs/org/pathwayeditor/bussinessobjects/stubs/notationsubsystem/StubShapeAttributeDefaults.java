/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;

/**
 * @author smoodie
 *
 */
public class StubShapeAttributeDefaults implements IShapeAttributeDefaults {
	public static final String DESCRIPTION = "Shape Description";
	public static final String DETAILS = "Shape Details";
	public static final RGB FILL_COLOUR = RGB.GREEN;
	public static final RGB LINE_COLOUR = RGB.RED;
	public static final LineStyle LINE_STYLE = LineStyle.SOLID;
	public static final int LINE_WIDTH = 2;
	public static final String NAME = "A Shape";
	public static final PrimitiveShapeType SHAPE_TYPE = PrimitiveShapeType.RECTANGLE;
	public static final Size SIZE = new Size(19, 23);
	public static final String URL = "http://www.gooogle.com";
	private final Set<IPropertyDefinition> propertyDefinitionList;
	
	
	public StubShapeAttributeDefaults(){
		this.propertyDefinitionList = new HashSet<IPropertyDefinition>();
		this.propertyDefinitionList.add(new StubTextPropertyDefinition());
		this.propertyDefinitionList.add(new StubNumberPropertyDefinition());
		this.propertyDefinitionList.add(new StubHtmlPropertyDefinition());
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDescription()
	 */
	public String getDescription() {
		return DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDetailedDescription()
	 */
	public String getDetailedDescription() {
		return DETAILS;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getFillColour()
	 */
	public RGB getFillColour() {
		return FILL_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineColour()
	 */
	public RGB getLineColour() {
		return LINE_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineStyle()
	 */
	public LineStyle getLineStyle() {
		return LINE_STYLE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineWidth()
	 */
	public int getLineWidth() {
		return LINE_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getName()
	 */
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getShapeType()
	 */
	public PrimitiveShapeType getShapeType() {
		return SHAPE_TYPE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getSize()
	 */
	public Size getSize() {
		return SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getURL()
	 */
	public String getURL() {
		return URL;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#propertyIterator()
	 */
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return propertyDefinitionList.iterator();
	}

}
