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



/**
 * @author Stuart Moodie
 *
 */
public class EnvelopeConvexHullBuilder  {
	private static final Point DEFAULT_ORIGIN = new Point(0, 0);
	private static final Dimension DEFAULT_SIZE = new Dimension(0, 0);
	private Envelope envelope;
	
	public EnvelopeConvexHullBuilder(Envelope bounds){
		this.envelope = bounds;
	}

	public EnvelopeConvexHullBuilder(){
		this.envelope = new Envelope(DEFAULT_ORIGIN, DEFAULT_SIZE);
	}
	
	public void setEnvelope(Envelope newEnvelope){
		this.envelope = newEnvelope;
	}

	public Envelope getEnvelope(){
		return this.envelope;
	}
	
	public IConvexHull getConvexHull(){
		ConvexHullBuilder builder = new ConvexHullBuilder();
		builder.addPoint(envelope.getOrigin());
		builder.addPoint(envelope.getHorizontalCorner());
		builder.addPoint(envelope.getDiagonalCorner());
		builder.addPoint(envelope.getVerticalCorner());
		return builder.getConvexHull();
	}
}
