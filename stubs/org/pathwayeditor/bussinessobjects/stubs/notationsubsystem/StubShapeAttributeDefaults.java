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
	public static final String DESCRIPTION = "description" ;
	public static final String DETAILED_DESCRIPTION = "detailed description" ;
	public static final RGB    FILL_COLOR = new RGB ( 100 , 100 , 100 ) ;
	public static final RGB    LINE_COLOR = new RGB ( 150 , 150 , 150 ) ;
	public static final LineStyle LINE_STYLE = LineStyle.DASH_DOT ;     
	public static final int LINE_WIDTH = 1 ;
	public static final String NAME = "name" ;
	public static final PrimitiveShapeType PRIMITIVE_SHAPE_TYPE = PrimitiveShapeType.ARC ;
	public static final Size SIZE = new Size ( 50 , 50 ) ;
	public static final String URL = "http://www.url.com" ;
	
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
		return DETAILED_DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getFillColour()
	 */
	public RGB getFillColour() {
		// TODO Auto-generated method stub
		return FILL_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineColour()
	 */
	public RGB getLineColour() {
		return LINE_COLOR;
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
		return PRIMITIVE_SHAPE_TYPE;
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
