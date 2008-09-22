/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.database.util.HibernateUtil;

/**
 * @author nhanlon this is a business logic test so no actual database
 *         activity will be tested - see corresponding  tests  in MapDatabaseTest
 * Tests the IMap interface
 */
public class MapBusinessLogicTest {

	private static final String JIMMY_KRANKIE = "JimmyKrankie";

	static {
		 HibernateUtil.setStubSessionFactoryAsDefault(); // dont use the  database 
	}
	protected HibSubFolder childOne;
	
	@Before
	public void setUp() {
		childOne = new HibSubFolder();
		childOne.setName("one");
	}
	
	@Test
	public void testNewMapDiagramSetsFolderReciprocally(){
		HibMap newMapDiagram = new HibMap(childOne,JIMMY_KRANKIE);
		assertTrue(newMapDiagram.getOwner().equals(childOne));
	}
	@Test
	public void getOwnerHappyCase(){
		HibMap newMapDiagram = new HibMap(childOne,JIMMY_KRANKIE);
		assertTrue(newMapDiagram.getOwner().equals(childOne));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getOwnerNullTest(){
		HibMap newMapDiagram = new HibMap();
		newMapDiagram.getOwner();
	}
	
	
	
}

