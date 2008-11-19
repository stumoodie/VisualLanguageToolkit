/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSyntaxService;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAParentOfAllObjectType;

/**
 * @author nhanlon
 *
 */
public class ShapeAttributesForCanvasBuilderTest {
	private static final String MATCHING_NAME = "matching_name";
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	private Map<String, ? extends IAnnotationProperty> emptyMap = new HashMap<String, IAnnotationProperty>();
	private Map<String,HibProperty> matchingMap = new HashMap<String, HibProperty>();
	
	private HibShapeAttribute shapeAttribute;
	private HibProperty property = mockery.mock(HibProperty.class);
	
	@Before
	public void setUp(){
		 shapeAttribute = mockery.mock(HibShapeAttribute.class);
		matchingMap.put(MATCHING_NAME,property);
	}
	
	@Test
	public void testLinkAttributeWithPropertiesNullDoesNotThrowException(){
		mockery.checking( new Expectations () {{
			atLeast(1).of(shapeAttribute).getProperties();
			will(returnValue(null)) ;  
		}} ) ;
		ShapeAttributesForCanvasBuilder  builder = new ShapeAttributesForCanvasBuilder();
		builder.injectPropertyDefinitions(new StubShapeAParentOfAllObjectType(new StubNotationSyntaxService(new StubNotationSubSystem())),  shapeAttribute);
	}
	@Test
	public void testLinkAttributeWithPropertiesEmptyDoesNotThrowException(){
		mockery.checking( new Expectations () {
		{
			atLeast(1).of(shapeAttribute).getProperties();
			will(returnValue(emptyMap)) ;  
		}} ) ;
		ShapeAttributesForCanvasBuilder  builder = new ShapeAttributesForCanvasBuilder();
		builder.injectPropertyDefinitions(new StubShapeAParentOfAllObjectType(new StubNotationSyntaxService(new StubNotationSubSystem())),  shapeAttribute);
	}
	
	@Test // Name matching is NOT tested by this method, only injection
	public void testLinkAttributePropertyIsInjectedWithDefinition(){
		mockery.checking( new Expectations () {
			{
				atLeast(1).of(shapeAttribute).getProperties();
				will(returnValue(matchingMap)) ;  
				one(shapeAttribute).getProperty(with(any(IPropertyDefinition.class)));will(returnValue(property)) ;
				atLeast(1).of(property).setPropertyDefinition(with(any(IPropertyDefinition.class)));
			}} ) ;
		ShapeAttributesForCanvasBuilder  builder = new ShapeAttributesForCanvasBuilder();
		builder.injectPropertyDefinitions(new StubShapeAParentOfAllObjectType(new StubNotationSyntaxService(new StubNotationSubSystem())),  shapeAttribute);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testPropertyDefinitionHasNoMatchingLinkAttributePropertyNameThrowsException(){
		mockery.checking( new Expectations () {
			{
				atLeast(1).of(shapeAttribute).getProperties();
				will(returnValue(matchingMap)) ;  
				one(shapeAttribute).getProperty(with(any(IPropertyDefinition.class)));will(returnValue(null)) ;
				atLeast(1).of(property).setPropertyDefinition(with(any(IPropertyDefinition.class)));
			}} ) ;
		ShapeAttributesForCanvasBuilder  builder = new ShapeAttributesForCanvasBuilder();
		builder.injectPropertyDefinitions(new StubShapeAParentOfAllObjectType(new StubNotationSyntaxService(new StubNotationSubSystem())),  shapeAttribute);
	}
}
