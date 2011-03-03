/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.definition;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

/**
 * 
 * FigureDefinitionCompiler is a class that is responsible for compiling a textual figure definition into
 * a compiled byte code style definition.  
 *
 * @author Stuart Moodie
 *
 */
public class FigureDefinitionCompiler {
	private final String figureDefinition;
//    private TreeDrawingVm walker = null;
    private ICompiledFigureDefinition compilerFigDefn;
	
    /**
     * Constructor that takes a textual figure definition as a string. 
     * @param figureDefinition the textual figure defintion.
     */
	public FigureDefinitionCompiler(String figureDefinition){
		this.figureDefinition = figureDefinition;
	}
	
	/**
	 * Compiles the figure definition.
	 * @throws RuntimeException is an error occured parsing the figure definition. 
	 */
	public void compile(){
        DrawingVmLexer lex = new DrawingVmLexer(new ANTLRStringStream(figureDefinition));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        DrawingVmParser parser = new DrawingVmParser(tokens);
        
        try {
            DrawingVmParser.shapeDefn_return treeReturn = parser.shapeDefn();
            CommonTree t = (CommonTree)treeReturn.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
            nodes.setTokenStream(tokens);
            TreeDrawingVm walker = new TreeDrawingVm(nodes);
            walker.setInstructionFactory(new InstructionFactoryImpl());
            walker.shapeDefn();
            this.compilerFigDefn = walker.getInstructions();
        } catch (RecognitionException e)  {
        	throw new RuntimeException(e);
        }
	}
	
//	public Iterator<Instruction> instructionIterator(){
//		return this.walker.getInstructions().iterator();
//	}


	/**
	 * Gets the compiled figure definition.
	 * @return the compiled figure definition, which will be null if the definition was not successfully compiled.
	 */
	public ICompiledFigureDefinition getCompiledFigureDefinition(){
		return this.compilerFigDefn;
	}


	/**
	 * Gets the textual figure definition that is to be compiled. 
	 * @return the textual figure definition.
	 */
	public String getFigureDefinitionString() {
		return figureDefinition;
	}
}
