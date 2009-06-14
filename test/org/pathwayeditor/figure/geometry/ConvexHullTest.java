package org.pathwayeditor.figure.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvexHullTest {
	private static final double EXPECTED_AREA = 800.0;
	private static final double XTRANS = 4.0;
	private static final double YTRANS = 7.0;
	private static final double NEW_HEIGHT = 40;
	private static final double NEW_WIDTH = 40;
	private static final double NEWX = -5;
	private static final double NEWY = 8;
	private IConvexHull testInstance;
	private List<LineSegment> expectedLines;
	private final double LINES_DEFN[][] = {
			{ 1, 21, 1, 1  },	
			{ 1, 1, 41, 1  },	
			{ 41, 1, 41, 21  },	
			{ 41, 21, 1, 21  },	
	};
	private List<Point> expectedPoints;
	private final double POINTS_DEFN[][] = {
			{ 1, 1  },	
			{ 41, 1  },	
			{ 41, 21  },	
			{ 1, 21  },	
	};
	private List<Point> expectedTranslatedPoints;
	private final double TRANSLATED_POINTS_DEFN[][] = {
			{ 5, 8  },	
			{ 45, 8  },	
			{ 45, 28  },	
			{ 5, 28  },	
	};
	private List<Point> expectedChangedPoints;
	private final double CHANGED_POINTS_DEFN[][] = {
			{ -5, 8  },	
			{ 35, 8  },	
			{ 35, 48  },	
			{ -5, 48  },	
	};

	private IConvexHull createConvexHull(){
		List<Point> retVal = new ArrayList<Point>(); 
		retVal.add(new Point(1.0, 1.0));
		retVal.add(new Point(41.0, 1.0));
		retVal.add(new Point(41.0, 21.0));
		retVal.add(new Point(1.0, 21.0));
		return new ConvexHull(retVal);
	}
	
	@Before
	public void setUp() throws Exception {
		this.testInstance = createConvexHull();
		this.expectedLines = new ArrayList<LineSegment>(LINES_DEFN.length);
		for(double row[] : LINES_DEFN){
			this.expectedLines.add(new LineSegment(new Point(row[0], row[1]), new Point(row[2], row[3])));
		}
		this.expectedPoints = new ArrayList<Point>(POINTS_DEFN.length);
		for(double row[] : POINTS_DEFN){
			this.expectedPoints.add(new Point(row[0], row[1]));
		}
		this.expectedTranslatedPoints = new ArrayList<Point>(TRANSLATED_POINTS_DEFN.length);
		for(double row[] : TRANSLATED_POINTS_DEFN){
			this.expectedTranslatedPoints.add(new Point(row[0], row[1]));
		}
		this.expectedChangedPoints = new ArrayList<Point>(CHANGED_POINTS_DEFN.length);
		for(double row[] : CHANGED_POINTS_DEFN){
			this.expectedChangedPoints.add(new Point(row[0], row[1]));
		}
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
		this.expectedLines = null;
		this.expectedPoints = null;
	}

	@Test
	public void testGetPointLineIntersects(){
		assertEquals("expected point", new Point(1.0, 11.0), this.testInstance.getPointLineIntersects(new Point(-9.0, 11.0)));
		assertEquals("expected point", new Point(41.0, 11.0), this.testInstance.getPointLineIntersects(new Point(71.0, 11.0)));
		assertEquals("expected point", new Point(21.0, 1.0), this.testInstance.getPointLineIntersects(new Point(21.0, -29.0)));
		assertEquals("expected point", new Point(21.0, 21.0), this.testInstance.getPointLineIntersects(new Point(21.0, 71.0)));
		assertEquals("expected point", new Point(1.0, 1.0), this.testInstance.getPointLineIntersects(new Point(-19.0, -9.0)));
	}

	@Test
	public void testChangeEnvelope(){
		IConvexHull changedHull = this.testInstance.changeEnvelope(new Envelope(NEWX, NEWY, NEW_WIDTH, NEW_HEIGHT));
		List<Point> actualPoints = changedHull.getPoints();
		assertEquals("same length", this.expectedChangedPoints.size(), actualPoints.size());
		for(int i = 0; i < this.expectedChangedPoints.size(); i++){
			assertEquals("expected point[" + i + "]", expectedChangedPoints.get(i), actualPoints.get(i));
		}
	}
	
	@Test
	public void testGetCentre(){
		assertEquals("expected point", new Point(21.0, 11.0), this.testInstance.getCentre());
	}
	
	@Test
	public void testGetArea(){
		assertEquals("expected point", EXPECTED_AREA, this.testInstance.getArea(), 0.001);
	}

	@Test
	public final void testIterator() {
		Iterator<Point> actualIter = this.testInstance.iterator();
		int actualCount = 0;
		while(actualIter.hasNext()){
			assertEquals("expectedPoint[" + actualCount + "]", this.expectedPoints.get(actualCount++), actualIter.next());
		}
		assertEquals("All points iterated", this.expectedPoints.size(), actualCount);
	}

	@Test
	public final void testContainsPoint() {
		assertTrue("Contains point", this.testInstance.containsPoint(21.0, 11.0));
		assertTrue("Contains point", this.testInstance.containsPoint(1.01, 20.9));
	}

	@Test
	public final void testGetLines() {
		List<LineSegment> actualLines = this.testInstance.getLines();
		assertEquals("same length", this.expectedLines.size(), actualLines.size());
		for(int i = 0; i < this.expectedLines.size(); i++){
			assertEquals("expected line[" + i + "]", expectedLines.get(i), actualLines.get(i));
		}
	}

	@Test
	public final void testGetPoints() {
		List<Point> actualPoints = this.testInstance.getPoints();
		assertEquals("same length", this.expectedPoints.size(), actualPoints.size());
		for(int i = 0; i < this.expectedPoints.size(); i++){
			assertEquals("expected point[" + i + "]", expectedPoints.get(i), actualPoints.get(i));
		}
	}

	@Test
	public final void testTranslate() {
		IConvexHull translatedHull = this.testInstance.translate(XTRANS, YTRANS);
		List<Point> actualPoints = translatedHull.getPoints();
		assertEquals("same length", this.expectedTranslatedPoints.size(), actualPoints.size());
		for(int i = 0; i < this.expectedPoints.size(); i++){
			assertEquals("expected point[" + i + "]", expectedTranslatedPoints.get(i), actualPoints.get(i));
		}
	}

}
