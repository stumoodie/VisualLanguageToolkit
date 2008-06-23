package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
public class DataStoreTest {
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	
	private DataStore testInstance = new DataStore();

	@Before
	public void setUp() throws Exception {
		this.testInstance = new DataStore();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testChangeRootFolderNull() {
		this.testInstance.changeRootFolder(null);
		assertTrue("new folder is null", this.testInstance.getRootFolder() == null);
	}

	@Test
	public final void testChangeRootFolder() {
		final RootFolder mockRootFolder1 = this.mockery.mock(RootFolder.class, "mockRootFolder");
		final RootFolder mockRootFolder2 = this.mockery.mock(RootFolder.class, "mockRootFolder2");
		this.mockery.checking(new Expectations(){{
			atLeast(1).of(mockRootFolder1).setDataStore(testInstance);
			atLeast(1).of(mockRootFolder1).setDataStore(null);
			atLeast(1).of(mockRootFolder2).setDataStore(testInstance);
			atLeast(1).of(mockRootFolder2).setDataStore(null);
		}});
		this.testInstance.changeRootFolder(null);
		assertTrue("new folder is null", this.testInstance.getRootFolder() == null);
		this.testInstance.changeRootFolder(mockRootFolder1);
		assertTrue("new folder is null", this.testInstance.getRootFolder() == mockRootFolder1);
		this.testInstance.changeRootFolder(mockRootFolder2);
		assertTrue("new folder is null", this.testInstance.getRootFolder() == mockRootFolder2);
		this.testInstance.changeRootFolder(null);
		assertTrue("new folder is null", this.testInstance.getRootFolder() == null);
		this.mockery.assertIsSatisfied();
	}

}
