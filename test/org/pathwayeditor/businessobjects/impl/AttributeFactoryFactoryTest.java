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

package org.pathwayeditor.businessobjects.impl;


import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class AttributeFactoryFactoryTest {
	private AttributeFactoryFactory testInstance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testInstance = new AttributeFactoryFactory(new IndexCounter());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AttributeFactoryFactory#shapeAttributeCopyFactory()}.
	 */
	@Test
	public void testShapeAttributeCopyFactory() {
		assertNotNull("shape copy factory exists", this.testInstance.shapeAttributeCopyFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AttributeFactoryFactory#linkAttributeFactory()}.
	 */
	@Test
	public void testLinkAttributeFactory() {
		assertNotNull("link att factory exists", this.testInstance.linkAttributeFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AttributeFactoryFactory#linkAttributeCopyFactory()}.
	 */
	@Test
	public void testLinkAttributeCopyFactory() {
		assertNotNull("link att copy factory exists", this.testInstance.linkAttributeCopyFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AttributeFactoryFactory#labelAttributeFactory()}.
	 */
	@Test
	public void testLabelAttributeFactory() {
		assertNotNull("label att factory exists", this.testInstance.labelAttributeFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AttributeFactoryFactory#labelAttributeCopyFactory()}.
	 */
	@Test
	public void testLabelAttributeCopyFactory() {
		assertNotNull("label att copy factory exists", this.testInstance.labelAttributeCopyFactory());
	}

}
