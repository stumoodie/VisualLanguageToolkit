package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.IPrimitiveShape;
import org.pathwayeditor.businessobjects.LineStyle;

public interface IShapeObjectType extends IObjectType {

	IPrimitiveShape getShapeType();

	String getDescription();

	String getDetailedDescription();

	String getName();

	String getURL();

	int getSizeWidth();

	int getSizeHeight();

	LineStyle getLineStyle();

	int getLineColourRed();

	int getLineColourGreen();

	int getLineColourBlue();

	int getFillTransparency();

	int getFillColourRed();

	int getFillColourGreen();

	int getFillColourBlue();

	void addProperty(IPropertyDefinition createTextProperty);

	int getLineWidth();

	IPropertyDefinitionFilter getPropertiesFilter();

	IShapeParentingRules getParentingRules();

	boolean isLineColourEditable();

	boolean isLineStyleEditable();
	
	boolean isLineWidthEditable();
	
	boolean isFillEditable();
}
