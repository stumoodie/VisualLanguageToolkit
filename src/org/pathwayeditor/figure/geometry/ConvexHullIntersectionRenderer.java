package org.pathwayeditor.figure.geometry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ConvexHullIntersectionRenderer {
	private final IConvexHull originalPoints;
	private final IConvexHull hullPoints;
	private Writer writer;
	
	public ConvexHullIntersectionRenderer(IConvexHull originalPoints, IConvexHull hullPoints){
		this.originalPoints = originalPoints;
		this.hullPoints = hullPoints;
	}
	
	public void writeAsPsFile(File outputFile) throws IOException{
		writer = new FileWriter(outputFile);
		final String header = "%!\n\n20 20 translate\n1 1 scale\n";
		final String footer = "showpage\n";
		writer.append(header);
		writeOriginalPoints();
		writeHull();
		writer.append(footer);
		writer.close();
	}
	
	private void writeOriginalPoints() throws IOException{
		writer.append("newpath\n255 0 0 setrgbcolor\n1 setlinewidth\n");
		writePolygon(this.originalPoints.getPoints());
		writer.append("closepath\nstroke\n");
	}
	
	private void writeHull() throws IOException{
		writer.append("newpath\n0 0 255 setrgbcolor\n1 setlinewidth\n");
		writePolygon(this.hullPoints.getPoints());
		writer.append("closepath\nstroke\n");
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
