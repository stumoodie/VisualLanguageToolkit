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

/**
 *   
 * TextAlignment is an enumerated type that defines the types of text alignment available
 * in a figure definition. It also can convert a text code into the enumerated type.
 *
 * @author Stuart Moodie
 *
 */
public enum TextAlignment{
	N, NE, E, SE, S, SW, W, NW, C;

	public static TextAlignment createFromString(String str){
		TextAlignment retVal = null;
		if(str.equals(N.name())){
			retVal = N;
		}
		else if(str.equals(NE.name())){
			retVal = NE;
		}
		else if(str.equals(E.name())){
			retVal = E;
		}
		else if(str.equals(SE.name())){
			retVal = SE;
		}
		else if(str.equals(S.name())){
			retVal = S;
		}
		else if(str.equals(SW.name())){
			retVal = SW;
		}
		else if(str.equals(W.name())){
			retVal = W;
		}
		else if(str.equals(NW.name())){
			retVal = NW;
		}
		else if(str.equals(C.name())){
			retVal = C;
		}
		return retVal;
	}	
}