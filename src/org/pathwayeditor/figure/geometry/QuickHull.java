/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01742
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * END LICENSE */

/**
 * <p>Title: QuickHull</p>
 * @author Dmitry Markman, dima@concord.org
 * @version 1.0
 */
package org.pathwayeditor.figure.geometry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//http://www.cs.princeton.edu/~ah/alg_anim/version1/QuickHull.html

/**
 * class that implements QuickHull algorithm to construct convex hull polygon<br>
 * usage example: <blockquote>
 * 
 * <pre>
 * int nDots = 500;
 * int offset = 50;
 * int sizeX = 400;
 * int sizeY = 400;
 * Point[] dots = new Point[nDots];
 * for (int i = 0; i &lt; dots.length; i++) {
 * 	int px = (int) Math.round(offset + (sizeX - 2 * offset) * Math.random());
 * 	int py = (int) Math.round(offset + (sizeY - 2 * offset) * Math.random());
 * 	dots[i] = new Point(px, py);
 * }
 * qh = new QuickHull(dots);
 * Point[] dots = qh.getOriginalPoints();
 * Vector outPoints = qh.getHullPointsAsVector();
 * </pre>
 * 
 * </blockquote>
 */

public class QuickHull {
	private List<Point> originalPoints;
	private int fullSteps = 0;
	private final List<Point> hullPoints = new ArrayList<Point>();

	/*
	 * constructor for <code>QuickHull</code> class
	 * 
	 * @param originalPoints {@link Point}[] initial points
	 */
	public QuickHull(List<Point> originalPoints) {
		this.originalPoints = new ArrayList<Point>(originalPoints);
	}

	public QuickHull() {
		this.originalPoints = new ArrayList<Point>();
	}

	public void calculateConvexHull(){
		hullPoints.clear();
		qhull(originalPoints, 0, 0);
		reorderPoints(hullPoints);
	}
	
	public void addPoint(Point p){
		this.originalPoints.add(p);
	}
	
	/**
	 * Returns original {@link Point} array.
	 * 
	 * @return original {@link Point} array
	 */
	public List<Point> getOriginalPoints() {
		return new ArrayList<Point>(originalPoints);
	}

	public List<Point> getHullPoints() {
		return new ArrayList<Point>(this.hullPoints);
	}

	private void reorderPoints(List<Point> v) {
		AngleWrapper[] angleWrappers = new AngleWrapper[v.size()];
		double xc = 0;
		double yc = 0;
		for (int i = 0; i < v.size(); i++) {
			Point pt = v.get(i);
			xc += pt.getX();
			yc += pt.getY();
		}

		xc /= v.size();
		yc /= v.size();

		for (int i = 0; i < angleWrappers.length; i++) {
			angleWrappers[i] = createAngleWrapper( v.get(i), xc, yc);
		}
		java.util.Arrays.sort(angleWrappers);
		v.clear();
		for (int i = 0; i < angleWrappers.length; i++) {
			v.add(angleWrappers[i].pt);
		}
	}

	private void qhull(List<Point> dots0, int up, int step) {
		fullSteps++;
		if (dots0 == null || dots0.size() < 1 || step > 200)
			return;
		if (dots0.size() < 2) {
			addHullPoint(dots0.get(0));
			return;
		}
		int leftIndex = 0;
		int rightIndex = 0;
		for (int i = 1; i < dots0.size(); i++) {
			if (( dots0.get(i)).getX() < ( dots0.get(leftIndex)).getX()) {
				leftIndex = i;
			}
			if (( dots0.get(i)).getX() > ( dots0.get(rightIndex)).getX()) {
				rightIndex = i;
			}
		}
		Point leftPoint =  dots0.get(leftIndex);
		Point rightPoint =  dots0.get(rightIndex);
		addHullPoint(leftPoint);
		addHullPoint(rightPoint);
		if (dots0.size() == 3) {
			int middlePoint = -1;
			for (int i = 0; i < dots0.size(); i++) {
				if (i == leftIndex || i == rightIndex)
					continue;
				middlePoint = i;
				break;
			}
			addHullPoint( dots0.get(middlePoint));
		} else if (dots0.size() > 3) {
			List<Point> vIn = new LinkedList<Point>();
			List<Point> vOut = new LinkedList<Point>();
			if (up >= 0) {
				int upIndex = selectPoints(dots0, leftPoint, rightPoint, true,
						vIn);
				if (upIndex >= 0 && vIn.size() > 0) {
					Point upPoint = vIn.get(upIndex);
					vOut.clear();
					selectPoints(vIn, leftPoint, upPoint, true, vOut);
					qhull(vOut, 1, step + 1);
					vOut.clear();
					selectPoints(vIn, upPoint, rightPoint, true, vOut);
					qhull(vOut, 1, step + 1);
				}
			}
			if (up <= 0) {
				vIn.clear();
				int downIndex = selectPoints(dots0, rightPoint, leftPoint,
						false, vIn);
				if (downIndex >= 0 && vIn.size() > 0) {
					Point downPoint = vIn.get(downIndex);
					vOut.clear();
					selectPoints(vIn, rightPoint, downPoint, false, vOut);
					qhull(vOut, -1, step + 1);
					vOut.clear();
					selectPoints(vIn, downPoint, leftPoint, false, vOut);
					qhull(vOut, -1, step + 1);
				}
			}
		}
	}

	private void addHullPoint(Point pt) {
		if (!hullPoints.contains(pt))
			hullPoints.add(pt);
	}

//	private static int selectPoints(List<Point> pIn, Point pLeft, Point pRight,	boolean up, List<Point> vOut) {
//		int retValue = -1;
//		if (pIn == null || vOut == null)
//			return retValue;
//		double k = (double) (pRight.getY() - pLeft.getY())
//				/ (double) (pRight.getX() - pLeft.getX());
//		double A = -k;
//		double B = 1;
//		double C = k * pLeft.getX() - pLeft.getY();
//		double dup = 0;
//		for (int i = 0; i < pIn.length; i++) {
//			Point pt =  pIn[i];
//			if (pt.equals(pLeft) || pt.equals(pRight))
//				continue;
//			double px = pt.getX();
//			double py = pt.getY();
//			double y = pLeft.getY() + k * (px - pLeft.getX());
//			if ((!up && y < py) || (up && y > py)) {
//				vOut.add(pt);
//				double d = (A * px + B * py + C);
//				if (d < 0)
//					d = -d;
//				if (d > dup) {
//					dup = d;
//					retValue = vOut.size() - 1;
//				}
//			}
//		}
//		vOut.add(pLeft);
//		vOut.add(pRight);
//		return retValue;
//	}

	private static int selectPoints(List<Point> vIn, Point pLeft, Point pRight,
			boolean up, List<Point> vOut) {
		int retValue = -1;
		if (vIn == null || vOut == null)
			return retValue;
		double k = (double) (pRight.getY() - pLeft.getY())
				/ (double) (pRight.getX() - pLeft.getX());
		double A = -k;
		double B = 1;
		double C = k * pLeft.getX() - pLeft.getY();
		double dup = 0;
		for (int i = 0; i < vIn.size(); i++) {
			Point pt = vIn.get(i);
			if (pt.equals(pLeft) || pt.equals(pRight))
				continue;
			double px = pt.getX();
			double py = pt.getY();
			double y = pLeft.getY() + k * (px - pLeft.getX());
			if ((!up && y < py) || (up && y > py)) {
				vOut.add(pt);
				double d = (A * px + B * py + C);
				if (d < 0)
					d = -d;
				if (d > dup) {
					dup = d;
					retValue = vOut.size() - 1;
				}
			}
		}
		vOut.add(pLeft);
		vOut.add(pRight);
		return retValue;
	}

	private static AngleWrapper createAngleWrapper(Point pt, double xc,
			double yc) {
		double angle = Math.atan2(pt.getY() - yc, pt.getX() - xc);
		if (angle < 0)
			angle += 2 * Math.PI;
		return new AngleWrapper(angle, pt);
	}

//	private static class AngleComparator implements
//			java.util.Comparator<AngleWrapper> {
//		public int compare(AngleWrapper ac1, AngleWrapper ac2) {
//			return (ac1.angle < ac2.angle) ? -1 : (ac1.angle > ac2.angle) ? 1
//					: 0;
//		}
//	}
//
	private static class AngleWrapper implements Comparable<AngleWrapper> {
		double angle;
		Point pt;

		AngleWrapper(double angle, Point pt) {
			this.angle = angle;
			this.pt = pt;
		}

		public int compareTo(AngleWrapper ac) {
			return (ac.angle < angle) ? -1 : (ac.angle > angle) ? 1 : 0;
		}
	}

	public boolean isEmpty() {
		return this.originalPoints.isEmpty();
	}

	// static int nDots = 500;
	// static int offset = 50;
	// static int sizeX = 400;
	// static int sizeY = 400;
	// static double r = (double)sizeX/2 - offset;
	// static double xc = (double)sizeX / 2;
	// static double yc = (double)sizeY / 2;
	// static QuickHull qh;

	// static void initDots(){
	// Point []dots = new Point[nDots];
	// for(int i =0; i < dots.length; i++){
	// int px = (int)Math.round(offset + (sizeX - 2*offset)*Math.random());
	// int py = (int)Math.round(offset + (sizeY - 2*offset)*Math.random());
	//            
	// // double angle = (double)i*Math.PI*2/(double)dots.length;
	// // int px = (int)Math.round(xc + r*Math.cos(angle));
	// // int py = (int)Math.round(yc + r*Math.sin(angle));
	//            
	// dots[i] = new Point(px,py);
	// }
	//        
	// qh = new QuickHull(dots);
	// System.out.println("hullPoints "+qh.hullPoints.size()+" fullSteps "+qh.fullSteps);
	// }

	// static void drawDots(java.awt.Graphics g){
	// if(qh == null) return;
	// g.setColor(java.awt.Color.gray);
	// Point []dots = qh.getOriginalPoints();
	// for(int i = 0; i < dots.length; i++){
	// Point pt = dots[i];
	// g.fillRect(pt.getX(),pt.getY(),3,3);
	// }
	// g.setColor(java.awt.Color.red);
	// Vector outPoints = qh.getHullPointsAsVector();
	// for(int i = 0; i < outPoints.size(); i++){
	// Point pt = outPoints.elementAt(i);
	// g.fillRect(pt.getX(),pt.getY(),3,3);
	// if(i > 0){
	// Point ptPrev = outPoints.elementAt(i-1);
	// g.drawLine(ptPrev.getX(),ptPrev.getY(),pt.getX(),pt.getY());
	// }
	// if(i == outPoints.size() - 1){
	// Point ptPrev = outPoints.elementAt(0);
	// g.drawLine(ptPrev.getX(),ptPrev.getY(),pt.getX(),pt.getY());
	// }
	// }
	// }

	// public static void main(String args[]){
	// javax.swing.JFrame frame1 = new javax.swing.JFrame("test"){
	// boolean wasInited = false;
	// public void paint(java.awt.Graphics g){
	// super.paint(g);
	// if(!wasInited){
	// initDots();
	// wasInited = true;
	// }
	// drawDots(g);
	// }
	// };
	// frame1.setSize(sizeX,sizeY);
	// frame1.setVisible(true);
	// }
}