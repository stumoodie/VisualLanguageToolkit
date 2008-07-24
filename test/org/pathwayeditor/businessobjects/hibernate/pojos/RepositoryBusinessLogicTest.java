/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author nhanlon tests for the HibRepository class' implementation of the IRepository interface
 *
 */
public class RepositoryBusinessLogicTest {

	private static final String JIMMY_KRANKIE = "JimmyKrankie";
	private static final String FANDABIDOSI = "fandabidosi";
	@Test
	public void testPublicConstructorEnsureNameNeverNullOrEmptyString(){
		try{HibRepository rep = new HibRepository("",FANDABIDOSI,1, new HibRootFolder());fail("empty name not allowed");}catch(IllegalArgumentException e) {;}
		try{HibRepository rep = new HibRepository(null,FANDABIDOSI,1,new HibRootFolder());fail("empty name not allowed");}catch(IllegalArgumentException e) {;}
	}
	@Test
	public void testPublicConstructorEnsureDescriptionNeverNullButEmptyStringAllowed(){
		try{HibRepository rep = new HibRepository(JIMMY_KRANKIE,null,1,new HibRootFolder());fail("empty name not allowed");}catch(IllegalArgumentException e) {;}
		HibRepository rep = new HibRepository(JIMMY_KRANKIE,"",1,new HibRootFolder());
	}
	
	
	@Test
	public void testGetNameHappyCase(){
		HibRepository rep = new HibRepository();
		rep.setName(JIMMY_KRANKIE);
		assertEquals(JIMMY_KRANKIE,rep.getName());
	}
	
	@Test
	public void testGetDesriptionHappyCase(){
		HibRepository rep = new HibRepository();
		rep.setDescription(JIMMY_KRANKIE);
		assertEquals(JIMMY_KRANKIE,rep.getDescription());
	}
	
	@Test
	public void testGetRootFolderHappyCase(){
		HibRepository rep = new HibRepository();
		HibRootFolder root = new HibRootFolder();
		rep.setHibRootFolder(root);
		root.setRepository(rep);
		assertEquals(root,rep.getRootFolder());
	}
	
	@Test
	public void testGetSchemaBuildNumHappyCase(){
		HibRepository rep = new HibRepository();
		rep.setBuildNum(1);
		assertEquals(1,rep.getSchemaBuildNum());
	}
	
	@Test
	public void testEqualsWorksOnName(){
		HibRepository rep = new HibRepository();
		rep.setName(JIMMY_KRANKIE);
		HibRepository rep2 = new HibRepository();
		assertFalse(rep.equals(rep2));
		rep2.setName("a");
		assertFalse(rep.equals(rep2));
		rep2.setName(JIMMY_KRANKIE);
		assertTrue(rep.equals(rep2));
	}
}
