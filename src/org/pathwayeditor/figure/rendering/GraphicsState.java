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
import org.pathwayeditor.figure.geometry.Envelope;

/**
 * 
 * @author Stuart Moodie
 *
 */
public class GraphicsState {
	private static final double DEFAULT_LINE_WIDTH = 1;
	private RGB fill; 
	private RGB line;
	private double lineWidth;
	private IFont font;
	private Envelope env;
	private LineStyle lineStyle;
	
	public GraphicsState(){
		this.fill = RGB.WHITE;
		this.line = RGB.BLACK;
		this.lineWidth = DEFAULT_LINE_WIDTH;
		this.font = new GenericFont();
		this.env = new Envelope(0, 0, 0, 0);
		this.lineStyle = LineStyle.SOLID;
	}
	
	public GraphicsState(GraphicsState other){
		this.fill = other.fill;
		this.line = other.line;
		this.lineWidth = other.lineWidth;
		this.font = new GenericFont(other.font);
		this.env = other.env;
		this.lineStyle = other.lineStyle;
	}
	
	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double newLineWidth){
		this.lineWidth = newLineWidth;
	}

	public void setFont(IFont font){
		this.font = font;
	}
	
	public RGB getFillColour() {
		return this.fill;
	}

	public void setFillColour(RGB newFillColour){
		this.fill = newFillColour;
	}

	public RGB getLineColour() {
		return this.line;
	}
	
	public void setLineColour(RGB newLineColour){
		this.line = newLineColour;
	}

	public IFont getFont() {
		return this.font;
	}
	
	public void setEnvelope(Envelope newEnvelope){
		this.env = newEnvelope;
	}

	public Envelope getEnvelope() {
		return env;
	}

	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
}
