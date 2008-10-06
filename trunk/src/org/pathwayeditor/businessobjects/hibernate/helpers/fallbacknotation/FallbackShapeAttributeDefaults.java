/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;

/**
 * @author smoodie
 *
 */
public class FallbackShapeAttributeDefaults implements IShapeAttributeDefaults {
	private static final ILabelAttributeDefaults LABEL_DEFAULTS = new FallbackLabelAttributeDefaults();
	private static final String DESCN = "Default Shape descn";
	private static final String DETAILED_DESCN = "Default shape detailed descn";
	private static final RGB FILL_COLOUR = RGB.WHITE;
	private static final RGB LINE_COLOUR = RGB.BLACK;
	private static final LineStyle LINE_STYLE = LineStyle.SOLID;
	private static final int LINE_WIDTH = 0;
	private static final String NAME = "Default Shape";
	private static final PrimitiveShapeType SHAPE_TYPE = PrimitiveShapeType.RECTANGLE;
	private static final Size SIZE = new Size(0, 0);
	private static final String URL = null;
	private static final Set<IPropertyDefinition> PROP_SET = Collections.emptySet();

	public FallbackShapeAttributeDefaults(){
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDefaultLabelAttributes()
	 */
	public ILabelAttributeDefaults getDefaultLabelAttributes() {
		return LABEL_DEFAULTS;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDescription()
	 */
	public String getDescription() {
		return DESCN;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDetailedDescription()
	 */
	public String getDetailedDescription() {
		return DETAILED_DESCN;
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
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getPropertiesFilter()
	 */
	public Iterator<IPropertyDefinition> propertyIterator() {
		return PROP_SET.iterator();
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

}
