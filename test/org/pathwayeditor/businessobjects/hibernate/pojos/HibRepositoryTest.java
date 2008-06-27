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

@RunWith(JMock.class)
public class HibRepositoryTest {
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	private static final String REPOSITORY_NAME1 ="testName1" ;
	private static final String REPOSITORY_NAME2 ="testName2" ;
	
	private static final String REPOSITORY_DESCRIPTION1 ="testDescription1" ;
	private static final String REPOSITORY_DESCRIPTION2 ="testDescription2" ;
	
	private static final int VERSION_NUMBER1 = 1 ;
	private static final int VERSION_NUMBER2 = 2 ;
	
	private HibRepository testInstance1 = new HibRepository();
	private HibRepository testInstance2 = new HibRepository();

	@Before
	public void setUp() throws Exception {
		this.testInstance1 = new HibRepository(REPOSITORY_NAME1 , REPOSITORY_DESCRIPTION1, VERSION_NUMBER1);
		this.testInstance2 = new HibRepository(REPOSITORY_NAME2 , REPOSITORY_DESCRIPTION2 , VERSION_NUMBER2);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testHashCode() {
		assertFalse ( "Hash code not equals." , testInstance1.hashCode() == testInstance2.hashCode() ) ;
		this.testInstance2.setName(REPOSITORY_NAME1) ;
		assertTrue ( "Hash code equals." , testInstance1.hashCode() == testInstance2.hashCode() ) ;
	}

	@Test
	public final void testEqualsObject() {
		assertTrue ( this.testInstance1.equals(this.testInstance1)) ;
		assertFalse ( this.testInstance1.equals(null)) ;
		assertFalse ( this.testInstance1.equals(this.testInstance2)) ;
		assertFalse (this.testInstance1.equals(REPOSITORY_NAME1)) ;
		
		this.testInstance2.setName(REPOSITORY_NAME1) ;
		assertTrue (this.testInstance1.equals(this.testInstance2)) ;
	}

	@Test
	public final void testChangeRootFolderNull() {
		this.testInstance1.changeRootFolder(null);
		assertTrue("new folder is null", this.testInstance1.getRootFolder() == null);
	}

	@Test
	public final void testChangeRootFolder() {
		final HibRootFolder mockRootFolder1 = this.mockery.mock(HibRootFolder.class, "mockRootFolder");
		final HibRootFolder mockRootFolder2 = this.mockery.mock(HibRootFolder.class, "mockRootFolder2");
		this.mockery.checking(new Expectations(){{
			atLeast(1).of(mockRootFolder1).setDataStore(testInstance1);
			atLeast(1).of(mockRootFolder1).setDataStore(null);
			atLeast(1).of(mockRootFolder2).setDataStore(testInstance1);
			atLeast(1).of(mockRootFolder2).setDataStore(null);
		}});
		this.testInstance1.changeRootFolder(null);
		assertTrue("new folder is null", this.testInstance1.getRootFolder() == null);
		this.testInstance1.changeRootFolder(mockRootFolder1);
		assertTrue("new folder is null", this.testInstance1.getRootFolder() == mockRootFolder1);
		this.testInstance1.changeRootFolder(mockRootFolder2);
		assertTrue("new folder is null", this.testInstance1.getRootFolder() == mockRootFolder2);
		this.testInstance1.changeRootFolder(null);
		assertTrue("new folder is null", this.testInstance1.getRootFolder() == null);
		this.mockery.assertIsSatisfied();
	}

}
