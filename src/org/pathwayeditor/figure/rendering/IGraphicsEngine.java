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

package org.pathwayeditor.figure.rendering;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;

/**
 * 
 * IGraphicsEngine is an interface that defines the primitive operations that must be supported by a
 * graphics engine that renders a figure definition.
 *
 * @author Stuart Moodie
 *
 */
public interface IGraphicsEngine {

	void drawPoint(double x, double y);

	void fillArc(double x, double y, double w, double h, double roundedOffset, double roundedLength);

	void drawArc(double x, double y, double w, double h, double roundedOffset, double roundedLength);

	void fillPolygon(double[] pointArr);

	void drawPolygon(double[] pointArr);

	void drawPolyline(double[] pointArr);

	void fillOval(double x, double y, double w, double h);

	void drawOval(double pos, double pos2, double w, double h);

	void fillRectangle(double pos, double pos2, double w, double h);

	void drawRoundRectangle(double x, double y, double w, double h, double arcWidthSize, double arcHeightSize);

	void fillRoundRectangle(double x, double y, double w, double h, double arcWidthSize, double arcHeightSize);

	void drawRectangle(double x, double y, double w, double h);

	void drawLine(double x1, double y1, double x2, double y2);

	void fillString(String text, double x, double y, GraphicalTextAlignment alignment);

	void drawString(String text, double x, double y, GraphicalTextAlignment alignment);

	void setFillColor(RGB color);

	void setLineColor(RGB color);

	void setLineWidth(double lineWidth);

	void setFont(IFont newFont);

	void setLineStyle(LineStyle lineStyle);

}
