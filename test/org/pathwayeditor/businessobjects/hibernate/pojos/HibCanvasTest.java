/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationConversionService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationPluginService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRepositoryChangeListener;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;
import org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubLinkAObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSyntaxService;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAObjectType;

public class HibCanvasTest {
	private static final String EXPECTED_REPO_NAME = "repo";
	private static final String EXPECTED_ALT_REPO_NAME = "altRepo";
	private static final int EXPECTED_INODE = 99;
	private static final int EXPECTED_ALT_INODE = 1;
	private static final int EXPECTED_SERIAL_COUNT = 3;
	private static final String VALID_MAP_NAME1 = "Test Map";
	private static final String VALID_MAP_NAME2 = "1Test Map2";
	private static final String VALID_MAP_NAME3 = "A";
	private static final String INVALID_MAP_NAME1 = "Test Map ";
	private static final String INVALID_MAP_NAME2 = " Test Map";
	private static final String INVALID_MAP_NAME3 = "  ";
//	private static final String EMPTY_MAP_NAME = "";

	private ICanvas testInstance;
	private ICanvas otherTestInstance;
	private InnerStubMap map1;
	private InnerStubMap map2;
	private InnerStubHibNotationFactory hibNotationFactory;
	private INotationSubsystem notationSubsystem;

	@Before
	public void setUp() {
		map1 = new InnerStubMap();
		map2 = new InnerStubMap();
		hibNotationFactory = new InnerStubHibNotationFactory();
		notationSubsystem = new StubNotationSubSystem();
		this.map1.setRepositoryName(EXPECTED_REPO_NAME);
		this.map1.setINode(EXPECTED_INODE);
		this.testInstance = new HibCanvas(map1.getRepository().getName(), map1.getINode(), hibNotationFactory, notationSubsystem, map1.getName());
		this.map2.setRepositoryName(EXPECTED_ALT_REPO_NAME);
		this.map2.setINode(EXPECTED_ALT_INODE);
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, new InnerStubNotationSubsystem(), map2.getName());
	}

	@After
	public void tearDown() {
		this.testInstance = null;
		this.otherTestInstance = null;
		this.map1 = null;
		this.map2 = null;
		this.hibNotationFactory = null;
		this.notationSubsystem = null;
	}

	@Test
	public void testEquals() {
		this.map2.setRepositoryName(EXPECTED_REPO_NAME);
		this.map2.setINode(EXPECTED_INODE);
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, new InnerStubNotationSubsystem(), map2.getName());
		assertTrue("same instance", this.testInstance.equals(this.testInstance));
		assertFalse("false if null", this.testInstance.equals(null));
		assertFalse("false if node same", this.testInstance.equals(null));
		assertTrue("same instance", this.testInstance.equals(this.otherTestInstance));
		this.map2.setRepositoryName(EXPECTED_ALT_REPO_NAME);
		this.map2.setINode(EXPECTED_INODE);
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, notationSubsystem, map2.getName());
		assertFalse("same instance", this.testInstance.equals(this.otherTestInstance));
		this.map2.setRepositoryName(EXPECTED_REPO_NAME);
		this.map2.setINode(EXPECTED_ALT_INODE);
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, notationSubsystem, map2.getName());
		assertFalse("same instance", this.testInstance.equals(this.otherTestInstance));
	}

	@Test
	public void testHashCode() {
		this.map2.setRepositoryName(EXPECTED_REPO_NAME);
		this.map2.setINode(EXPECTED_INODE);
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, new InnerStubNotationSubsystem(), map2.getName());
		assertEquals("same instance", this.testInstance.hashCode(), this.testInstance.hashCode());
		assertTrue("hashes equal when equal", this.testInstance.hashCode() == this.otherTestInstance.hashCode());
		this.map2.setRepositoryName(EXPECTED_ALT_REPO_NAME);
		this.map2.setINode(EXPECTED_INODE);
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, notationSubsystem, map2.getName());
		assertTrue("hashes not same when not equal", this.testInstance.hashCode() != this.otherTestInstance.hashCode());
		this.map2.setRepositoryName(EXPECTED_REPO_NAME);
		this.map2.setINode(EXPECTED_ALT_INODE);
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, notationSubsystem, map2.getName());
		assertTrue("hashes not same when not equal", this.testInstance.hashCode() != this.otherTestInstance.hashCode());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidNameInConstructor(){
		map2.setName(INVALID_MAP_NAME1);
		new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, new InnerStubNotationSubsystem(), map2.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullNameInConstructor(){
		map2.setName(null);
		new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, new InnerStubNotationSubsystem(), map2.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameWithInvalidName1(){
		this.testInstance.setName(INVALID_MAP_NAME1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameWithInvalidName2(){
		this.testInstance.setName(INVALID_MAP_NAME2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameWithInvalidName3(){
		this.testInstance.setName(INVALID_MAP_NAME3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameWithNull(){
		this.testInstance.setName(null);
	}
	
	@Test
	public void testSetNameWithValidName1(){
		this.testInstance.setName(VALID_MAP_NAME1);
		assertEquals("valid name", VALID_MAP_NAME1, this.testInstance.getName());
	}
	
	@Test
	public void testSetNameWithValidName2(){
		this.testInstance.setName(VALID_MAP_NAME2);
		assertEquals("valid name", VALID_MAP_NAME2, this.testInstance.getName());
	}
	@Test
	public void testSetNameWithSingleCharNameIsValid(){
		this.testInstance.setName(VALID_MAP_NAME3);
		assertEquals("valid name", VALID_MAP_NAME3, this.testInstance.getName());
	}
	
	@Test
	public void testCanCopyHereWithNull() {
		assertFalse("can't copy here if null", this.testInstance.canCopyHere(null));
	}

	@Test
	public void testCanCopyHereWithDifferentNotationSubsystem() {
		assertFalse("can't copy here if diff notation subsystem", this.testInstance.canCopyHere(otherTestInstance));
	}

	@Test
	public void testCanCopyHereWithSameCanvas() {
		assertFalse("can't copy here if same canvas", this.testInstance.canCopyHere(this.testInstance));
	}

	@Test
	public void testCanCopyHereWithNonEmptyCanvas() {
		assertFalse("can't copy here if non-empty canvas", this.testInstance.canCopyHere(this.otherTestInstance));
	}

	@Test
	public void testCanCopyHereWithEmptyCorrectCanvas() {
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, notationSubsystem, map2.getName());
		assertTrue("can copy here", this.otherTestInstance.canCopyHere(this.testInstance));
	}

	@Test
	public void testCanCopyHereWithPopulatedCorrectCanvas() {
		IModel model = this.testInstance.getModel();
		IShapeNodeFactory shapeFact = model.getRootNode().getSubModel().shapeNodeFactory();
		INotationSyntaxService syntaxService = this.notationSubsystem.getSyntaxService();
		shapeFact.setObjectType(syntaxService.getShapeObjectType(StubShapeAObjectType.UNIQUE_ID));
		IShapeNode node1 = shapeFact.createShapeNode();
		IShapeNode node2 = shapeFact.createShapeNode();
		ILinkEdgeFactory linkFact = model.linkEdgeFactory();
		linkFact.setObjectType(syntaxService.getLinkObjectType(StubLinkAObjectType.UNIQUE_ID));
		linkFact.setShapeNodePair(node1, node2);
		linkFact.canCreateLink();
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, notationSubsystem, map2.getName());
		assertTrue("can copy here", this.otherTestInstance.canCopyHere(this.testInstance));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCopyHereWithNull() {
		this.testInstance.copyHere(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCopyHereWithDifferentNotationSubsystem() {
		this.testInstance.copyHere(otherTestInstance);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCopyHereWithSameCanvas() {
		this.testInstance.copyHere(this.testInstance);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCopyHereWithNonEmptyCanvas() {
		this.testInstance.copyHere(this.otherTestInstance);
	}

	@Test
	public void testCopyHereWithEmptyCorrectCanvas() {
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, notationSubsystem, map2.getName());
		this.otherTestInstance.copyHere(this.testInstance);
		assertTrue("still empty after copy", this.otherTestInstance.isEmpty());
	}

	@Test
	public void testCopyHereWithPopulatedCorrectCanvas() {
		IModel model = this.testInstance.getModel();
		IShapeNodeFactory shapeFact = model.getRootNode().getSubModel().shapeNodeFactory();
		INotationSyntaxService syntaxService = this.notationSubsystem.getSyntaxService();
		shapeFact.setObjectType(syntaxService.getShapeObjectType(StubShapeAObjectType.UNIQUE_ID));
		IShapeNode node1 = shapeFact.createShapeNode();
		IShapeNode node2 = shapeFact.createShapeNode();
		ILinkEdgeFactory linkFact = model.linkEdgeFactory();
		linkFact.setObjectType(syntaxService.getLinkObjectType(StubLinkAObjectType.UNIQUE_ID));
		linkFact.setShapeNodePair(node1, node2);
		linkFact.canCreateLink();
		int expectedNodes = this.testInstance.getModel().numDrawingNodes();
		int expectedLinks = this.testInstance.getModel().numLinkEdges();
		this.otherTestInstance = new HibCanvas(map2.getRepository().getName(), map2.getINode(), hibNotationFactory, notationSubsystem, map2.getName());
		this.otherTestInstance.copyHere(this.testInstance);
		assertEquals("expected nodes copied", expectedNodes, this.otherTestInstance.getModel().numDrawingNodes());
		assertEquals("expected links copied", expectedLinks, this.otherTestInstance.getModel().numLinkEdges());
		// test serial counter are incremented
		HibCanvas hibCanvas = (HibCanvas)this.otherTestInstance;
		assertEquals("expected serial count", EXPECTED_SERIAL_COUNT, hibCanvas.getCreationSerialCounter().getLastIndex());
	}

	private class InnerStubMap implements IMap {
		protected int stubINode;
		protected String stubRepositoryName;
		private String name = VALID_MAP_NAME1;

		public void setINode(int iNode) {
			this.stubINode = iNode;
		}

		public void setRepositoryName(String repoName) {
			this.stubRepositoryName = repoName;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.repository.IMap#getDescription()
		 */
		public String getDescription() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.repository.IMap#getName()
		 */
		public String getName() {

			return this.name;
		}

		public void setName(String name){
			this.name = name;
		}
		
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.repository.IMap#getOwner()
		 */
		public IFolder getOwner() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.repository.IMap#getPath()
		 */
		public String getPath() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.repository.IMap#setDescription(java.lang.String)
		 */
		public void setDescription(String description) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.repository.IRepositoryItem#addChangeListener(org.pathwayeditor.businessobjects.repository.
		 * IRepositoryItemChangeListener)
		 */
		public void addChangeListener(IRepositoryItemChangeListener changeListener) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getChangeListeners()
		 */
		public List<IRepositoryItemChangeListener> getChangeListeners() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getINode()
		 */
		public int getINode() {
			return stubINode;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getRepository()
		 */
		public IRepository getRepository() {
			return new IRepository() {

				public void addChangeListener(IRepositoryChangeListener listener) {

				}

				public IRepositoryItem findRepositoryItemByPath(String path) {

					return null;
				}

				public String getDescription() {

					return null;
				}

				public IFolder getFolderByPath(String path) {

					return null;
				}

				public List<ISubFolder> getFoldersByName(String name) {

					return null;
				}

				public List<IRepositoryChangeListener> getListeners() {

					return null;
				}

				public String getName() {
					return stubRepositoryName;
				}

				public IRootFolder getRootFolder() {

					return null;
				}

				public int getSchemaBuildNum() {

					return 0;
				}

				public boolean pathExists(String path) {

					return false;
				}

				public void removeChangeListener(IRepositoryChangeListener listener) {

				}

				public void setDescription(String description) {

				}

				public IRepositoryItem findRepositoryItemByINode(int node) {
					throw new UnsupportedOperationException("Not implemented");
				}

			};
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.repository.IRepositoryItem#removeChangeListener(org.pathwayeditor.businessobjects.repository.
		 * IRepositoryItemChangeListener)
		 */
		public void removeChangeListener(IRepositoryItemChangeListener changeListener) {

		}

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compareTo(IRepositoryItem o2) {
			return Integer.valueOf(this.getINode()).compareTo(Integer.valueOf(o2.getINode())); 
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#levelOrderIterator()
		 */
		public Iterator<IRepositoryItem> levelOrderIterator() {
			throw new UnsupportedOperationException("Not implemented yet!");
		}
	}

	private class InnerStubHibNotationFactory implements IHibNotationFactory {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#containsObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType
		 * )
		 */
		public boolean containsObjectType(IObjectType objectType) {

			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation()
		 */
		public HibNotation getNotation() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotationSubsystem()
		 */
		public INotationSubsystem getNotationSubsystem() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
		 */
		public HibObjectType getObjectType(IObjectType objectType) {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#initialise()
		 */
		public void initialise() {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isFallback()
		 */
		public boolean isFallback() {

			return false;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#hasInitialisationFailed()
		 */
		public boolean hasInitialisationFailed() {
			return false;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isInitialised()
		 */
		public boolean isInitialised() {
			return true;
		}

	}

	private class InnerStubNotationSubsystem implements INotationSubsystem {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getAutolayoutService()
		 */
		public INotationAutolayoutService getAutolayoutService() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getConversionServices()
		 */
		public Set<INotationConversionService> getConversionServices() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getExportServices()
		 */
		public Set<INotationExportService> getExportServices() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getImportServices()
		 */
		public Set<INotationImportService> getImportServices() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getNotation()
		 */
		public INotation getNotation() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getPluginServices()
		 */
		public Set<INotationPluginService> getPluginServices() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getSyntaxService()
		 */
		public INotationSyntaxService getSyntaxService() {
			return new StubNotationSyntaxService(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getValidationService()
		 */
		public INotationValidationService getValidationService() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#isFallback()
		 */
		public boolean isFallback() {

			return false;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#registerCanvas(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
		 */
		public void registerCanvas(ICanvas canvasToRegister) {
			
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#unregisterCanvas(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
		 */
		public void unregisterCanvas(ICanvas canvasToRegister) {
		}

	}
}
