/*
Copyright 2009-2011, Court of the University of Edinburgh
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