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

package org.pathwayeditor.figurevm;

import java.io.IOException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.pathwayeditor.figure.definition.DrawingVmLexer;
import org.pathwayeditor.figure.definition.DrawingVmParser;
import org.pathwayeditor.figure.definition.ICompiledFigureDefinition;
import org.pathwayeditor.figure.definition.InstructionFactoryImpl;
import org.pathwayeditor.figure.definition.TreeDrawingVm;

public class ParserTest {

	public static final void main(String args[]) throws IOException{
//		String input = "0.0 0.0 100.0 100.0 rect (abdgs sgga) text 0 foo lt { [ 0 0 true { 0 0 point } {} ifelse  [ /name 0.0 true ] ] setfillcol } { [255 255 255] setlinecol} ifelse";
//		String input = "0 foo lt [ 0 axy /anc :efg ] setfillcol"; // { [ 0 0 0 ] setfillcol } { [255 255 255] setlinecol} ifelse";
        DrawingVmLexer lex = new DrawingVmLexer(new ANTLRStringStream(args[0]));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        DrawingVmParser parser = new DrawingVmParser(tokens);
        
        try {
            DrawingVmParser.shapeDefn_return treeReturn = parser.shapeDefn();
            CommonTree t = (CommonTree)treeReturn.getTree();
//            DOTTreeGenerator gen = new DOTTreeGenerator();
//            StringTemplate st = gen.toDOT(t);
//            Writer w = new FileWriter("test.dot");
//            w.append(st.toString());
//            w.close();
//            w = null;
            System.out.println(t.toStringTree());
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
            nodes.setTokenStream(tokens);
            TreeDrawingVm walker = new TreeDrawingVm(nodes);
            walker.setInstructionFactory(new InstructionFactoryImpl());
            walker.shapeDefn();
            ICompiledFigureDefinition instructions = walker.getInstructions();
            System.out.println("Insts: " + instructions);
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
        System.exit(0);
	}
	
}
