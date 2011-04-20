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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * ConvexHullRenderer
 *
 * @author Stuart Moodie
 *
 */
public class ConvexHullRenderer {
	private final List<Point> originalPoints;
	private final IConvexHull hullPoints;
	private Writer writer;
	
	public ConvexHullRenderer(List<Point> originalPoints, IConvexHull hullPoints){
		this.originalPoints = new ArrayList<Point>(originalPoints);
		this.hullPoints = hullPoints;
	}
	
	public void writeAsPsFile(File outputFile) throws IOException{
		writer = new FileWriter(outputFile);
		final String header = "%!\n\n150 300 translate\n2 2 scale\n";
		final String footer = "showpage\n";
		writer.append(header);
		writeOriginalPoints();
		writeHull();
		writer.append(footer);
		writer.close();
	}
	
	private void writeOriginalPoints() throws IOException{
		writer.append("newpath\n255 0 0 setrgbcolor\n2 setlinewidth\n");
		writePoints(this.originalPoints);
		writer.append("stroke\n");
	}
	
	private void writeHull() throws IOException{
		writer.append("newpath\n0 0 255 setrgbcolor\n1 setlinewidth\n");
		writePolygon(this.hullPoints.getPoints());
		writer.append("closepath\nstroke\n");
	}
	
	private void writePoints(List<Point> pointsList) throws IOException{
		for(Point point : pointsList){
			StringBuilder builder = new StringBuilder();
			builder.append(point.getX());
			builder.append(' ');
			builder.append(point.getY());
			builder.append(' ');
			builder.append("2 0 360 arc closepath fill");
			builder.append('\n');
			writer.append(builder.toString());
		}
	}

	private void writePolygon(List<Point> pointsList) throws IOException{
		boolean first = true;
		for(Point point : pointsList){
			StringBuilder builder = new StringBuilder();
			builder.append(point.getX());
			builder.append(' ');
			builder.append(point.getY());
			builder.append(' ');
			if(first){
				builder.append("moveto");
				first = false;
			}
			else{
				builder.append("lineto");
			}
			builder.append('\n');
			writer.append(builder.toString());
		}
	}
}
