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

package org.pathwayeditor.figure.definition;

import java.util.List;

import org.pathwayeditor.figure.geometry.PointList;

/**
 * 
 * IOpCodeHandler is an interface that defines the operations that are can occur when an Interpreter reads a compiled figure definition.
 * See {@link IFigureDefinitionInterpreter}. Clients should implement this interface in order to perform actions based on an interpreted
 * figure definitions, such as rendering the figure definition. 
 * 
 *
 * @author Stuart Moodie
 *
 */
public 	interface IOpCodeHandler {
	void handleRoundRectangle(double x, double y, double width,
			double height, double arcWidth, double arcHeight);

	void handleRectangle(double x, double y, double width, double height);

	void handleOval(double x, double y, double width, double height);

	void handleLine(double startX, double startY, double endX, double endY);

	void handlePolygon(double[] pointArr);

	void handlePolyline(double[] pointArr);

	void handlePoint(double x, double y);

	void handleArc(double x, double y, double width, double height,	double offset, double length);
	
	void handleText(double x, double y, TextAlignment alignment, String text);

//	void setNoFill();

	void setFillColour(int red, int green, int blue, int alpha);

	List<Integer> getCurFillColour();

//	void setNoLine();

	List<Integer> getCurLineColour();

	void setLineColour(int red, int green, int blue, int alpha);

	void setFontSize(double fontSize);

	double getCurFontSize();

	String getCurFontStyle();

	void setFontStyle(String styleString);

	void saveGraphicsState();

	void restoreGraphicsState();

	void setLineWidth(double lineWidth);

	List<Double> getCurBounds();
	
	double currentLineWidth();
	
	void setChopHullAnchor();

	void setSemiFixedAnchorCode(PointList points);

	List<Double> getTextBounds(String text);
}
