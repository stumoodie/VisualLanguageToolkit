/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibCanvasTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private HibCanvas canvas ;
	private HibCanvas canvas2 ;
//	private final static int NUMERIC_VALUE_TWO = 2;
//	private final static int NUMERIC_VALUE_ZERO = 0;
	private final static String ANOTHER_OBJECT = "another object" ;

	private static final int EXPECTED_INODE_VAL = 111;

	private static final int EXPECTED_OTHER_INODE_VAL = 999;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testEquals () throws Exception 
	{
		final HibMap mockMapDiagram = mockery.mock(HibMap.class , "mockMapDiagram") ;
		final HibMap mockMapDiagram2 = mockery.mock(HibMap.class , "mockMapDiagram2") ;
		final HibMap mockMapDiagram3 = mockery.mock(HibMap.class , "mockMapDiagram3") ;
		final INotationSubsystem mockContext = mockery.mock(INotationSubsystem.class , "mockContext") ;
		final IHibNotationFactory mockHibNotationFactory = mockery.mock(IHibNotationFactory.class, "mockHibNotationFactory");
		final HibRepository mockRepository = mockery.mock(HibRepository.class, "mockRepository");
		final HibNotation mockHibNotation = this.mockery.mock(HibNotation.class, "mockHibNotation");
		final INotation mockNotation = this.mockery.mock(INotation.class, "mockNotation");
		final INotationSyntaxService mockSyntaxService = this.mockery.mock(INotationSyntaxService.class, "mockNotationSyntaxService"); 
		final IRootObjectType mockRootObjectType = this.mockery.mock(IRootObjectType.class, "mockRootObjectType");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockMapDiagram).getRepository(); will(returnValue(mockRepository));
			allowing(mockMapDiagram).getINode(); will(returnValue(EXPECTED_INODE_VAL));
			
			allowing(mockMapDiagram2).getRepository(); will(returnValue(mockRepository));
			allowing(mockMapDiagram2).getINode(); will(returnValue(EXPECTED_INODE_VAL));
			
			allowing(mockMapDiagram3).getRepository(); will(returnValue(mockRepository));
			allowing(mockMapDiagram3).getINode(); will(returnValue(EXPECTED_OTHER_INODE_VAL));
			
			allowing(mockContext).getNotation(); will(returnValue(mockNotation));
			
			allowing(mockHibNotationFactory).getNotation(with(any(INotation.class))); will(returnValue(mockHibNotation));
			
			allowing(mockContext).getSyntaxService(); will(returnValue(mockSyntaxService));
			
			allowing(mockSyntaxService).getRootObjectType(); will(returnValue(mockRootObjectType));
		}});
		
		canvas = new HibCanvas ( mockMapDiagram, mockHibNotationFactory , mockContext) ;
		canvas2 = new HibCanvas ( mockMapDiagram2, mockHibNotationFactory , mockContext) ;
		HibCanvas canvas3 = new HibCanvas ( mockMapDiagram3, mockHibNotationFactory , mockContext) ;
		
		assertTrue ( canvas.equals(canvas)) ;
		assertFalse ( canvas.equals(null)) ;
		assertTrue ( canvas.equals(canvas2)) ;
		assertFalse ( canvas.equals(canvas3)) ;
		assertFalse (canvas.equals(ANOTHER_OBJECT)) ;
		this.mockery.assertIsSatisfied();
	}
	
	@Test
	public void testHashCode() throws Exception
	{
		final HibMap mockMapDiagram = mockery.mock(HibMap.class , "mockMapDiagram") ;
		final HibMap mockMapDiagram2 = mockery.mock(HibMap.class , "mockMapDiagram2") ;
		final HibMap mockMapDiagram3 = mockery.mock(HibMap.class , "mockMapDiagram3") ;
		final INotationSubsystem mockContext = mockery.mock(INotationSubsystem.class , "mockContext") ;
		final IHibNotationFactory mockHibNotationFactory = mockery.mock(IHibNotationFactory.class, "mockHibNotationFactory");
		final HibRepository mockRepository = mockery.mock(HibRepository.class, "mockRepository");
		final HibNotation mockHibNotation = this.mockery.mock(HibNotation.class, "mockHibNotation");
		final INotation mockNotation = this.mockery.mock(INotation.class, "mockNotation");
		final INotationSyntaxService mockSyntaxService = this.mockery.mock(INotationSyntaxService.class, "mockNotationSyntaxService"); 
		final IRootObjectType mockRootObjectType = this.mockery.mock(IRootObjectType.class, "mockRootObjectType");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockMapDiagram).getRepository(); will(returnValue(mockRepository));
			allowing(mockMapDiagram).getINode(); will(returnValue(EXPECTED_INODE_VAL));
			
			allowing(mockMapDiagram2).getRepository(); will(returnValue(mockRepository));
			allowing(mockMapDiagram2).getINode(); will(returnValue(EXPECTED_INODE_VAL));
			
			allowing(mockMapDiagram3).getRepository(); will(returnValue(mockRepository));
			allowing(mockMapDiagram3).getINode(); will(returnValue(EXPECTED_OTHER_INODE_VAL));
			
			allowing(mockContext).getNotation(); will(returnValue(mockNotation));
			
			allowing(mockHibNotationFactory).getNotation(with(any(INotation.class))); will(returnValue(mockHibNotation));
			
			allowing(mockContext).getSyntaxService(); will(returnValue(mockSyntaxService));
			
			allowing(mockSyntaxService).getRootObjectType(); will(returnValue(mockRootObjectType));
		}});
		
		canvas = new HibCanvas ( mockMapDiagram, mockHibNotationFactory , mockContext) ;
		canvas2 = new HibCanvas ( mockMapDiagram2, mockHibNotationFactory , mockContext) ;
		HibCanvas canvas3 = new HibCanvas ( mockMapDiagram3, mockHibNotationFactory , mockContext) ;
		
		assertTrue ( canvas.hashCode() == canvas2.hashCode()) ;
		assertFalse ( canvas.hashCode() == canvas3.hashCode()) ;
		this.mockery.assertIsSatisfied();
	}
}
