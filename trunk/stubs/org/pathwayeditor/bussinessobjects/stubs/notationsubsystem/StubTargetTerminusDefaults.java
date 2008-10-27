/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;

/**
 * @author smoodie
 *
 */
public class StubTargetTerminusDefaults implements ILinkTerminusDefaults {
	public static final LinkTermType LINK_END_TYPE = LinkTermType.SOURCE;
	public static final short EXPECTED_OFFSET = 1 ;
	public static final Size EXPECTED_END_SIZE = new Size(10, 20);
	public static final Size EXPECTED_TERM_SIZE = new Size(10, 20);
	public static final RGB EXPECTED_TERM_COLOUR = new RGB(100, 200, 300);
	public static final PrimitiveShapeType EXPECTED_TERM_DEC = PrimitiveShapeType.ELLIPSE;
	public static final LinkEndDecoratorShape EXPECTED_END_DEC = LinkEndDecoratorShape.ARROW;

	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getDefaultLabelAttributes()
	 */
	public ILabelAttributeDefaults getDefaultLabelAttributes() {
		throw new UnsupportedOperationException("not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getEndDecoratorType()
	 */
	public LinkEndDecoratorShape getEndDecoratorType() {
		return EXPECTED_END_DEC;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getEndSize()
	 */
	public Size getEndSize() {
		return EXPECTED_END_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getGap()
	 */
	public short getGap() {
		return EXPECTED_OFFSET;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermColour()
	 */
	public RGB getTermColour() {
		return EXPECTED_TERM_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermDecoratorType()
	 */
	public PrimitiveShapeType getTermDecoratorType() {
		return EXPECTED_TERM_DEC;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermSize()
	 */
	public Size getTermSize() {
		return EXPECTED_TERM_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getPropertiesIterator()
	 */
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
