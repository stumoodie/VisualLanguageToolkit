/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figurevm;

import java.util.Iterator;
import java.util.Set;

/**
 * 
 * IFigureDefinition
 *
 * @author Stuart Moodie
 *
 */
public interface IFigureDefinition {

	Iterator<Instruction> iterator();

	Set<String> getBindVariableNames();

	int size();

	Instruction get(int index);

}