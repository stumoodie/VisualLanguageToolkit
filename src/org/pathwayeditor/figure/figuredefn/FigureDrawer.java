package org.pathwayeditor.figure.figuredefn;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.figuredefn.GraphicsInstruction.GraphicalTextAlignment;
import org.pathwayeditor.figure.figuredefn.GraphicsInstruction.GraphicsOpCode;
import org.pathwayeditor.figure.figuredefn.IFont.Style;
import org.pathwayeditor.figure.geometry.Point;

public class FigureDrawer {
	private static final int TEXT_X_IDX = 0;
	private static final int TEXT_Y_IDX = 1;
	private static final int TEXT_ALIGN_IDX = 2;
	private static final int TEXT_IDX = 3;
	
	public interface IGraphicsOpCodeAction {
		
		void handleOpCode(GraphicsInstruction inst);
	}

	private final Logger logger = Logger.getLogger(this.getClass());
	private final GraphicsInstructionList instList;
	private final Map<GraphicsOpCode, IGraphicsOpCodeAction> opCodeLookup;
//	private final Envelope refBounds;
	private IGraphicsEngine graphics;
	private IFont currFont;
//	private Envelope newBounds;
	
	public FigureDrawer(GraphicsInstructionList graphicsInstList){
		this.instList = graphicsInstList;
		this.currFont = new GenericFont();
//		this.refBounds = refBounds;
		this.opCodeLookup = new HashMap<GraphicsOpCode, IGraphicsOpCodeAction>();
		this.opCodeLookup.put(GraphicsOpCode.DRAW_ARC, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> valList = inst.getList();
				processDrawArc(valList);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.DRAW_LINE, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> valList = inst.getList();
				processDrawLine(valList);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.DRAW_OVAL, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> valList = inst.getList();
				processDrawOval(valList);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.DRAW_POINT, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> valList = inst.getList();
				processDrawPoint(valList);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.DRAW_POLYGON, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				processDrawPolygon(inst.getDoubleArray());
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.DRAW_POLYLINE, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				processDrawPolyline(inst.getDoubleArray());
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.DRAW_RECT, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> points = inst.getList();
				processDrawRect(points);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.DRAW_RRECT, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> points = inst.getList();
				processDrawRoundedRect(points);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.DRAW_TEXT, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Object> values = inst.getList();
				processDrawText(values);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.FILL_ARC, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> points = inst.getList();
				processFillArc(points);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.FILL_OVAL, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> points = inst.getList();
				processFillOval(points);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.FILL_POLYGON, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				processFillPolygon(inst.getDoubleArray());
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.FILL_RECT, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> points = inst.getList();
				processFillRect(points);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.FILL_RRECT, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Double> points = inst.getList();
				processFillRoundedRect(points);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.FILL_TEXT, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				List<Object> values = inst.getList();
				processFillText(values);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.LINE_COLOUR, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				RGB colour = inst.getRGB();
				processLineColour(colour);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.FILL_COLOUR, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				RGB colour = inst.getRGB();
				processFillColour(colour);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.FONT_SIZE, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				Double values = inst.getTypedValue();
				processFontSize(values);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.FONT_STYLE, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				EnumSet<Style> values = inst.getEnumSet();
				processFontStyle(values);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.LINE_WIDTH, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				Double values = inst.getTypedValue();
				processLineWidth(values);
			}
			
		});
		this.opCodeLookup.put(GraphicsOpCode.LINE_STYLE, new IGraphicsOpCodeAction(){

			@Override
			public void handleOpCode(GraphicsInstruction inst) {
				LineStyle values = inst.getTypedValue();
				processLineStyle(values);
			}
			
		});
	}

	/**
	 * @param values
	 */
	private void processLineStyle(LineStyle values) {
		this.graphics.setLineStyle(values);
		if(logger.isDebugEnabled()){
			logger.debug("Setting line style=" + values);
		}
	}

	private void processLineWidth(Double width) {
		this.graphics.setLineWidth(width);
		if(logger.isDebugEnabled()){
			logger.debug("Setting line width=" + width);
		}
	}

	private void processFontStyle(EnumSet<Style> values) {
		this.currFont = this.currFont.newStyle(values);
		this.graphics.setFont(this.currFont);
		if(logger.isDebugEnabled()){
			logger.debug("Setting font style=" + values);
		}
	}

	private void processFontSize(Double fontSize) {
		this.currFont = this.currFont.newSize(fontSize);
		this.graphics.setFont(this.currFont);
		if(logger.isDebugEnabled()){
			logger.debug("Setting font size=" + fontSize);
		}
	}

	private void processFillColour(RGB colour) {
		this.graphics.setFillColor(colour);
		if(logger.isDebugEnabled()){
			logger.debug("Setting fill colour=" + colour);
		}
	}

	private void processLineColour(RGB colour) {
		this.graphics.setLineColor(colour);
		if(logger.isDebugEnabled()){
			logger.debug("Setting line colour=" + colour);
		}
	}

	private void processFillText(List<Object> values) {
		Point p = scaledPoint((Double)values.get(TEXT_X_IDX), (Double)values.get(TEXT_Y_IDX));
		GraphicalTextAlignment align = (GraphicalTextAlignment)values.get(TEXT_ALIGN_IDX);
		String text = (String)values.get(TEXT_IDX);
		this.graphics.fillString(text, p.getX(), p.getY(), align);
		if(logger.isDebugEnabled()){
			logger.debug("Fill text: text=" + text + ",pos=" + p);
		}
	}

	private void processFillRoundedRect(List<Double> points) {
		Point p = scaledPoint(points.get(0), points.get(1));
		double w = scaledWidth(points.get(2));
		double h = scaledHeight(points.get(3));
		double cw = scaledWidth(points.get(4));
		double ch = scaledHeight(points.get(5));
		this.graphics.fillRoundRectangle(p.getX(), p.getY(), w, h, cw, ch);
		if(logger.isDebugEnabled()){
			logger.debug("Fill rounded rect: pos=" + p + ",w=" + w + ",h=" + h + ",cw=" + cw + ",ch=" + ch);
		}
	}

	private void processFillRect(List<Double> points) {
		Point p = scaledPoint(points.get(0), points.get(1));
		double w = scaledWidth(points.get(2));
		double h = scaledHeight(points.get(3));
		this.graphics.fillRectangle(p.getX(), p.getY(), w, h);
		if(logger.isDebugEnabled()){
			logger.debug("Fill rect: pos=" + p + ",w=" + w + ",h=" + h);
		}
	}

	private void processFillPolygon(double[] doubleArray) {
		double scaledArray[] = scalePointArray(doubleArray);
		this.graphics.fillPolygon(scaledArray);
		if(logger.isDebugEnabled()){
			logger.debug("Fill polygon: points=" + Arrays.toString(scaledArray));
		}
	}

	private void processFillOval(List<Double> points) {
		Point p = scaledPoint(points.get(0), points.get(1));
		double w = scaledWidth(points.get(2));
		double h = scaledHeight(points.get(3));
		this.graphics.fillOval(p.getX(), p.getY(), w, h);
		if(logger.isDebugEnabled()){
			logger.debug("Fill oval: pos=" + p + ",w=" + w + ",h=" + h);
		}
	}

	private void processFillArc(List<Double> points) {
		Point p = scaledPoint(points.get(0), points.get(1));
		double w = scaledWidth(points.get(2));
		double h = scaledHeight(points.get(3));
		double start = points.get(4);
		double length = points.get(5);
		this.graphics.fillArc(p.getX(), p.getY(), w, h, start, length);
		if(logger.isDebugEnabled()){
			logger.debug("Fill arc: pos=" + p + ",w=" + w + ",h=" + h +",start=" + start + ",length=" + length);
		}
	}

	private void processDrawText(List<Object> values) {
		Point p = scaledPoint((Double)values.get(TEXT_X_IDX), (Double)values.get(TEXT_Y_IDX));
		GraphicalTextAlignment align = (GraphicalTextAlignment)values.get(TEXT_ALIGN_IDX);
		String text = (String)values.get(TEXT_IDX);
		this.graphics.drawString(text, p.getX(), p.getY(), align);
		if(logger.isDebugEnabled()){
			logger.debug("Draw text: text=" + text + ",pos=" + p);
		}
	}

	private void processDrawRoundedRect(List<Double> points) {
		Point p = scaledPoint(points.get(0), points.get(1));
		double w = scaledWidth(points.get(2));
		double h = scaledHeight(points.get(3));
		double cw = scaledWidth(points.get(4));
		double ch = scaledHeight(points.get(5));
		this.graphics.drawRoundRectangle(p.getX(), p.getY(), w, h, cw, ch);
		if(logger.isDebugEnabled()){
			logger.debug("Draw rounded rect: pos=" + p + ",w=" + w + ",h=" + h + ",cw=" + cw + ",ch=" + ch);
		}
	}

	private void processDrawRect(List<Double> points) {
		Point p = scaledPoint(points.get(0), points.get(1));
		double w = scaledWidth(points.get(2));
		double h = scaledHeight(points.get(3));
		this.graphics.drawRectangle(p.getX(), p.getY(), w, h);
		if(logger.isDebugEnabled()){
			logger.debug("Draw rect: pos=" + p + ",w=" + w + ",h=" + h);
		}
	}

	private void processDrawPolyline(double[] doubleArray) {
		double scaledArray[] = scalePointArray(doubleArray);
		this.graphics.drawPolyline(scaledArray);
		if(logger.isDebugEnabled()){
			logger.debug("Draw polyline: points=" + Arrays.toString(scaledArray));
		}
	}

	private void processDrawPolygon(double[] doubleArray) {
		double scaledArray[] = scalePointArray(doubleArray);
		this.graphics.drawPolygon(scaledArray);
		if(logger.isDebugEnabled()){
			logger.debug("Draw polygon: points=" + Arrays.toString(scaledArray));
		}
	}

	private void processDrawPoint(List<Double> valList) {
		Point p = scaledPoint(valList.get(0), valList.get(1));
		this.graphics.drawPoint(p.getX(), p.getY());
		if(logger.isDebugEnabled()){
			logger.debug("Draw point: pos=" + p);
		}
	}

	private void processDrawOval(List<Double> points) {
		Point p = scaledPoint(points.get(0), points.get(1));
		double w = scaledWidth(points.get(2));
		double h = scaledHeight(points.get(3));
		this.graphics.drawOval(p.getX(), p.getY(), w, h);
		if(logger.isDebugEnabled()){
			logger.debug("Draw oval: pos=" + p + ",w=" + w + ",h=" + h);
		}
	}

	private void processDrawLine(List<Double> valList) {
		Point p1 = scaledPoint(valList.get(0), valList.get(1));
		Point p2 = scaledPoint(valList.get(2), valList.get(3));
		this.graphics.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		if(logger.isDebugEnabled()){
			logger.debug("Draw line: start=" + p1 + ",end=" + p2);
		}
	}

	private void processDrawArc(List<Double> points) {
		Point p = scaledPoint(points.get(0), points.get(1));
		double w = scaledWidth(points.get(2));
		double h = scaledHeight(points.get(3));
		double start = points.get(4);
		double length = points.get(5);
		this.graphics.drawArc(p.getX(), p.getY(), w, h, start, length);
		if(logger.isDebugEnabled()){
			logger.debug("Draw arc: pos=" + p + ",w=" + w + ",h=" + h +",start=" + start + ",length=" + length);
		}
	}

	public GraphicsInstructionList getInstList() {
		return instList;
	}
	
	public void drawFigure(IGraphicsEngine g){
		this.graphics = g;
//		this.newBounds = newBounds;
//		logger.debug("Drawing figure: newBounds=" + newBounds);
//		logger.debug("Initial graphics state: fill=" + g.getFillColour() + ",line=" + g.getLineColour() + ",lineWidth=" + g.getLineWidth());
		Iterator<GraphicsInstruction> iter = this.instList.iterator();
		while(iter.hasNext()){
			GraphicsInstruction inst = iter.next();
			IGraphicsOpCodeAction action = this.opCodeLookup.get(inst.getOpCode());
			if(action == null){
				throw new IllegalStateException("Unrecognised opCode");
			}
			else{
				action.handleOpCode(inst);
			}
		}
//		logger.debug("Final graphics state: fill=" + g.getFillColour() + ",line=" + g.getLineColour() + ",lineWidth=" + g.getLineWidth());
		logger.debug("Finished drawing figure");
	}
	
	private double[] scalePointArray(double[] unscaledPoints){
//		double retVal[] = new double[unscaledPoints.length];
//		double xScale = this.newBounds.getDimension().getWidth()/this.getRefBounds().getDimension().getWidth();
//		double yScale = this.newBounds.getDimension().getHeight()/this.getRefBounds().getDimension().getHeight();
//		for(int i = 0, j=0; i < unscaledPoints.length;){
//			retVal[j++] = this.newBounds.getOrigin().getX() + ((unscaledPoints[i++] - this.getRefBounds().getOrigin().getX()) * xScale);
//			retVal[j++] = this.newBounds.getOrigin().getY() + ((unscaledPoints[i++] - this.getRefBounds().getOrigin().getY()) * yScale);
//		}
//		return retVal;
		return unscaledPoints;
	}
	
//	private Envelope getRefBounds(){
//		return this.refBounds;
//	}
	
	private double scaledHeight(double h) {
//		Dimension dim = this.getRefBounds().getDimension();
//		return h/dim.getHeight() * this.newBounds.getDimension().getHeight();
		return h;
	}

	private double scaledWidth(double w) {
//		Dimension dim = this.getRefBounds().getDimension();
//		return w/dim.getWidth() * this.newBounds.getDimension().getWidth();
		return w;
	}

	private Point scaledPoint(double x, double y) {
		Point p = new Point(x, y);
//		return this.getRefBounds().transformPointToNewEnvelope(p, this.newBounds);
		return p;
	}


}
