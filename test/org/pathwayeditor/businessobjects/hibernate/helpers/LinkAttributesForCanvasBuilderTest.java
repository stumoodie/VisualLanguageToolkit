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
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.bussinessobjects.stubs.StubLinkObjectType;

/**
 * @author nhanlon
 *
 */
public class LinkAttributesForCanvasBuilderTest {
	
	private static final String MATCHING_NAME = "matching_name";
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	private Map<String, ? extends IAnnotationProperty> emptyMap = new HashMap<String, IAnnotationProperty>();
	private Map<String,HibProperty> matchingMap = new HashMap<String, HibProperty>();
	
	private HibLinkAttribute linkAttribute;
	private HibProperty property = mockery.mock(HibProperty.class);
	
	@Before
	public void setUp(){
		 linkAttribute = mockery.mock(HibLinkAttribute.class);
		matchingMap.put(MATCHING_NAME,property);
	}
	
	@Test
	public void testLinkAttributeWithPropertiesNullDoesNotThrowException(){
		mockery.checking( new Expectations () {{
			atLeast(1).of(linkAttribute).getHibLinkProperties();
			will(returnValue(null)) ;  
		}} ) ;
		LinkAttributesForCanvasBuilder  builder = new LinkAttributesForCanvasBuilder();
		builder.injectPropertyDefinitions(new StubLinkObjectType(), linkAttribute);
	}
	@Test
	public void testLinkAttributeWithPropertiesEmptyDoesNotThrowException(){
		mockery.checking( new Expectations () {
		{
			atLeast(1).of(linkAttribute).getHibLinkProperties();
			will(returnValue(emptyMap)) ;  
		}} ) ;
		LinkAttributesForCanvasBuilder  builder = new LinkAttributesForCanvasBuilder();
		builder.injectPropertyDefinitions(new StubLinkObjectType(), linkAttribute);
	}
	
	@Test // Name matching is NOT tested by this method, only injection
	public void testLinkAttributePropertyIsInjectedWithDefinition(){
		mockery.checking( new Expectations () {
			{
				atLeast(1).of(linkAttribute).getHibLinkProperties();
				will(returnValue(matchingMap)) ;  
				one(linkAttribute).getProperty(with(any(IPropertyDefinition.class)));will(returnValue(property)) ;
				atLeast(1).of(property).setPropertyDefinition(with(any(IPropertyDefinition.class)));
			}} ) ;
		LinkAttributesForCanvasBuilder  builder = new LinkAttributesForCanvasBuilder();
		builder.injectPropertyDefinitions(new StubLinkObjectType(), linkAttribute);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testPropertyDefinitionHasNoMatchingLinkAttributePropertyNameThrowsException(){
		mockery.checking( new Expectations () {
			{
				atLeast(1).of(linkAttribute).getHibLinkProperties();
				will(returnValue(matchingMap)) ;  
				one(linkAttribute).getProperty(with(any(IPropertyDefinition.class)));will(returnValue(null)) ;
				atLeast(1).of(property).setPropertyDefinition(with(any(IPropertyDefinition.class)));
			}} ) ;
		LinkAttributesForCanvasBuilder  builder = new LinkAttributesForCanvasBuilder();
		builder.injectPropertyDefinitions(new StubLinkObjectType(), linkAttribute);
	}

}
