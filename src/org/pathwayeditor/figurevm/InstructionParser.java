package org.pathwayeditor.figurevm;

import java.util.Iterator;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

public class InstructionParser {
	private final String figureDefinition;
    private TreeDrawingVm walker = null;
	
	public InstructionParser(String figureDefinition){
		this.figureDefinition = figureDefinition;
	}
	
	
	public void parse(){
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


	public IFigureDefinition getInstructions(){
		return this.walker.getInstructions();
	}


	public String getFigureDefinition() {
		return figureDefinition;
	}
}
