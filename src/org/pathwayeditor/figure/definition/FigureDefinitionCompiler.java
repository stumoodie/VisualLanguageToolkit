/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.definition;

import java.util.Iterator;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

/**
 * 
 * FigureDefinitionCompiler
 *
 * @author Stuart Moodie
 *
 */
public class FigureDefinitionCompiler {
	private final String figureDefinition;
    private TreeDrawingVm walker = null;
	
	public FigureDefinitionCompiler(String figureDefinition){
		this.figureDefinition = figureDefinition;
	}
	
	
	public void compile(){
        DrawingVmLexer lex = new DrawingVmLexer(new ANTLRStringStream(figureDefinition));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        DrawingVmParser parser = new DrawingVmParser(tokens);
        
        try {
            DrawingVmParser.shapeDefn_return treeReturn = parser.shapeDefn();
            CommonTree t = (CommonTree)treeReturn.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
            nodes.setTokenStream(tokens);
            this.walker = new TreeDrawingVm(nodes);
            walker.setInstructionFactory(new InstructionFactoryImpl());
            walker.shapeDefn();
        } catch (RecognitionException e)  {
        	throw new RuntimeException(e);
        }
	}
	
	public Iterator<Instruction> instructionIterator(){
		return this.walker.getInstructions().iterator();
	}


	public ICompiledFigureDefinition getCompiledFigureDefinition(){
		return this.walker.getInstructions();
	}


	public String getFigureDefinitionString() {
		return figureDefinition;
	}
}
