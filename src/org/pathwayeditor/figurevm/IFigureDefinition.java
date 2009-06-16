/**
 * 
 */
package org.pathwayeditor.figurevm;

import java.util.Iterator;
import java.util.Set;

/**
 * @author smoodie
 *
 */
public interface IFigureDefinition {

	Iterator<Instruction> iterator();

	Set<String> getBindVariableNames();

	int size();

	Instruction get(int index);

}