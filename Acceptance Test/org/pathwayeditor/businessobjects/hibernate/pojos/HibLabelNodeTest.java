/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.junit.Test;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author smoodie
 *
 */
public class HibLabelNodeTest extends PojoTester {
	private static final String DB_SOURCE_NAME = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";

	private static final long TEST_LABEL_ID = 100016L;
	private static final int EXPECTED_NODE_IDX = 24;
	
	private HibLabelNode testInstance;
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute#injectObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)}.
	 * @throws InconsistentNotationDefinitionException 
	 */
	@Test
	public final void testIsValid() throws InconsistentNotationDefinitionException {
		INotationSubsystem stubNotationSubsystem = new StubNotationSubSystem();
		INodeObjectType labelObjectType = new LabelObjectType(stubNotationSubsystem.getSyntaxService()); 
		this.testInstance.getAttribute().injectObjectType(labelObjectType);
		assertTrue("valid", this.testInstance.isValid());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute#getVisualisableProperty()}.
	 */
	@Test
	public final void testGetAttribute() {
		assertNotNull("current drawing element set", this.testInstance.getAttribute());
		assertTrue("reciprocal relationship exists", this.testInstance.getAttribute().getCurrentDrawingElement() == this.testInstance);
		assertEquals("expected label node", EXPECTED_NODE_IDX, this.testInstance.getAttribute().getCreationSerial());
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void additionalSetup() {
		Session sess = this.getHibFactory().getCurrentSession();
		sess.beginTransaction();
		this.testInstance = (HibLabelNode)sess.createQuery("from HibLabelNode where id = :id").setLong("id", TEST_LABEL_ID).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void additionalTeardown() {
		Session sess = this.getHibFactory().getCurrentSession();
		sess.getTransaction().commit();
		this.testInstance = null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return DB_SOURCE_NAME;
	}

}
