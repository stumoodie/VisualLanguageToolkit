/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.figure.definition;

/**
 * 
 * ValueType is an enumerated type containing the possible value types available in a compiled value. See {@link Value}.  
 *
 * @author Stuart Moodie
 *
 */
public enum ValueType {
	REAL, INTEGER, STRING_LITERAL, LITERAL_VAR_NAME, BOOLEAN, ARRAY, NULL, PROCEDURE
}