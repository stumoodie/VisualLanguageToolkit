/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package org.pathwayeditor.figure.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LineSegmentForthQuadrantTest {
	private LineSegment testInstance;
	private static final Point ORIGIN = new Point(2.0, 1.0);
	private static final Point END = new Point(10.0, -1.0);
	private static final double EXPECTED_LEN = Math.sqrt(8*8+2*2);
	private static final double EXPECTED_EQUATION[] = { 0.25, 1.0, 1.5 }; 
	private static final LineSegment intersectingLine = new LineSegment(new Point(2, -1), new Point(10.0, 1.0));
	private static final Point INTERSECTING_POINT = new Point(6.0, 0);
	private static final LineSegment nonIntersectingLine = new LineSegment(new Point(-1.0, 0.0), new Point(9.0, 10.0));;
	private static final Point CONTAINED_POINT = new Point(3.0, 0.75);  
	private static final Point NOT_CONTAINED_POINT = new Point(3.0, 0.1);
	private static final double NEW_SEG_LEN = Math.sqrt((6*6)+(1.5*1.5));
	private static final LineSegment EXPECTED_NEW_LINE_SEG = new LineSegment(ORIGIN, new Point(8,-0.5));  
	
	@Before
	public void setUp() throws Exception {
		this.testInstance = new LineSegment(ORIGIN, END); 
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	@Test
	public final void testIntersect() {
		assertEquals("Intersects", INTERSECTING_POINT, this.testInstance.intersect(intersectingLine, 0.01));
		assertNull("Not intersecting", this.testInstance.intersect(nonIntersectingLine, 0.01));
	}

	@Test
	public final void testContainsPoint() {
		assertTrue("contained point", this.testInstance.containsPoint(CONTAINED_POINT));
		assertFalse("not contained point", this.testInstance.containsPoint(NOT_CONTAINED_POINT));
		assertTrue("contained point", this.testInstance.containsPoint(new Point(7.0, -0.25)));
		assertFalse("not contained point", this.testInstance.containsPoint(new Point(7.0, -4.01)));
		assertTrue("contained point", this.testInstance.containsPoint(new Point(7.0, -0.250001)));
		assertFalse("not contained point", this.testInstance.containsPoint(new Point(7.0, -4.10001)));
		assertTrue("contained point", this.testInstance.containsPoint(ORIGIN));
		assertTrue("contained point", this.testInstance.containsPoint(END));
		assertFalse("not contained point", this.testInstance.containsPoint(new Point(7.0, 7.0)));
		assertFalse("not contained point", this.testInstance.containsPoint(new Point(13.0, 12.0)));
	}

	@Test
	public final void testLength() {
		assertEquals("expected length", EXPECTED_LEN, this.testInstance.length(), 0.001);
	}

	@Test
	public final void testGetEquation() {
		double actualEqn[] = this.testInstance.getEquation();
		for(int i = 0; i < EXPECTED_EQUATION.length; i++){
			assertEquals("expected eqn[" + i + "]", EXPECTED_EQUATION[i], actualEqn[i], 0.000001);
		}
	}

	@Test
	public final void testGetOrigin() {
		assertEquals("expected origin",ORIGIN, this.testInstance.getOrigin());
	}

	@Test
	public final void testGetTerminus() {
		assertEquals("expected origin",END, this.testInstance.getTerminus());
	}

	
	@Test
	public final void testNewLineSegment(){
		LineSegment actualSeg = this.testInstance.newLineSegment(NEW_SEG_LEN);
		assertEquals("Expected length", NEW_SEG_LEN, actualSeg.length(), 0.001);
		assertEquals("Expected lineseg origin X", EXPECTED_NEW_LINE_SEG.getOrigin().getX(), actualSeg.getOrigin().getX(), 0.001);
		assertEquals("Expected lineseg origin Y", EXPECTED_NEW_LINE_SEG.getOrigin().getY(), actualSeg.getOrigin().getY(), 0.001);
		assertEquals("Expected lineseg term X", EXPECTED_NEW_LINE_SEG.getTerminus().getX(), actualSeg.getTerminus().getX(), 0.001);
		assertEquals("Expected lineseg term Y", EXPECTED_NEW_LINE_SEG.getTerminus().getY(), actualSeg.getTerminus().getY(), 0.001);
	}
}
