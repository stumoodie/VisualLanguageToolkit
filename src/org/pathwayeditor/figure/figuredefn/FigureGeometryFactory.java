/**
 * 
 */
package org.pathwayeditor.figure.figuredefn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelEdgeChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelNodeChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ModelStructureChangeType;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.RectangleHull;
import org.pathwayeditor.figurevm.FigureDefinitionCompiler;

/**
 * @author smoodie
 *
 */
public class FigureGeometryFactory implements IFigureGeometryFactory {
	private final Logger logger = Logger.getLogger(this.getClass());
	private final Map<IShapeNode, IFigureController> controllerLookup;
	private final Map<IDrawingNode, IConvexHull> hullLookup;
	private final ICanvasAttributePropertyChangeListener shapePropertyChangeListener;
	private final ICanvasAttributePropertyChangeListener drawingNodePropertyChangeListener;
	private final IAnnotationPropertyChangeListener annotPropChangeListener;
	private final IModelChangeListener modelListener;
	
	
	public FigureGeometryFactory(IModel model){
		this.controllerLookup = new HashMap<IShapeNode, IFigureController>();
		this.hullLookup = new HashMap<IDrawingNode, IConvexHull>();
		shapePropertyChangeListener = new ICanvasAttributePropertyChangeListener() {
			public void propertyChange(ICanvasAttributePropertyChangeEvent e) {
				if(e.getPropertyChange().equals(CanvasAttributePropertyChange.LINE_COLOUR)){
					IShapeAttribute attribute = (IShapeAttribute)e.getAttribute();
					IFigureController figureController = controllerLookup.get(attribute.getCurrentDrawingElement());
					figureController.setLineColour((RGB)e.getNewValue());
					figureController.generateFigureDefinition();
				}
				else if(e.getPropertyChange().equals(CanvasAttributePropertyChange.FILL_COLOUR)){
					IShapeAttribute attribute = (IShapeAttribute)e.getAttribute();
					IFigureController figureController = controllerLookup.get(attribute.getCurrentDrawingElement());
					figureController.setFillColour((RGB)e.getNewValue());
					figureController.generateFigureDefinition();
				}
				else if(e.getPropertyChange().equals(CanvasAttributePropertyChange.LINE_WIDTH)){
					IShapeAttribute attribute = (IShapeAttribute)e.getAttribute();
					IFigureController figureController = controllerLookup.get(attribute.getCurrentDrawingElement());
					Double newLineWidth = (Double)e.getNewValue();
					figureController.setLineWidth(newLineWidth);
					figureController.generateFigureDefinition();
				}
				else if(e.getPropertyChange().equals(CanvasAttributePropertyChange.SIZE)
						|| e.getPropertyChange().equals(CanvasAttributePropertyChange.LOCATION)){
					IShapeAttribute attribute = (IShapeAttribute)e.getAttribute();
					IFigureController figureController = controllerLookup.get(attribute.getCurrentDrawingElement());
					figureController.setRequestedEnvelope(attribute.getBounds());
					figureController.generateFigureDefinition();
				}
				else if(e.getPropertyChange().equals(CanvasAttributePropertyChange.LINE_STYLE)){
					IShapeAttribute attribute = (IShapeAttribute)e.getAttribute();
					IFigureController figureController = controllerLookup.get(attribute.getCurrentDrawingElement());
					figureController.setLineStyle(attribute.getLineStyle());
					figureController.generateFigureDefinition();
				}
			}
		};
		drawingNodePropertyChangeListener = new ICanvasAttributePropertyChangeListener() {
			public void propertyChange(ICanvasAttributePropertyChangeEvent e) {
				if(e.getPropertyChange().equals(CanvasAttributePropertyChange.SIZE)
						|| e.getPropertyChange().equals(CanvasAttributePropertyChange.LOCATION)){
					IDrawingNodeAttribute attribute = (IDrawingNodeAttribute)e.getAttribute();
					IConvexHull newHull = null;
					if(hullLookup.containsKey(attribute.getCurrentDrawingElement())){
						IConvexHull origHull = hullLookup.remove(attribute.getCurrentDrawingElement());
						newHull = origHull.changeEnvelope(attribute.getBounds());
					}
					else{
						newHull = createHull((IDrawingNode)attribute.getCurrentDrawingElement());
					}
					hullLookup.put((IDrawingNode)attribute.getCurrentDrawingElement(), newHull);
				}
			}
		};
		annotPropChangeListener = new IAnnotationPropertyChangeListener() {
			public void propertyChange(IAnnotationPropertyChangeEvent e) {
				IAnnotationProperty prop = e.getPropertyDefinition();
				IShapeNode node = ((IShapeAttribute)prop.getOwner()).getCurrentDrawingElement();
				IFigureController figureController = controllerLookup.get(node);
				assignBindVariablesToProperties(node.getAttribute(), figureController);
				figureController.generateFigureDefinition();
			}	
		};
		modelListener = new IModelChangeListener(){

			public void edgeStructureChange(IModelEdgeChangeEvent event) {
				// do nothing
			}

			public void nodeStructureChange(IModelNodeChangeEvent event) {
				if(event.getChangeType().equals(ModelStructureChangeType.DELETED)){
					if(controllerLookup.containsValue(event.getChangedItem())){
						IShapeNode shapeNode = (IShapeNode)event.getChangedItem();
						removeListeners(shapeNode);
						controllerLookup.remove(shapeNode);
					}
					else if(hullLookup.containsKey(event.getChangedItem())){
						IDrawingNode node = event.getChangedItem();
						node.getAttribute().removeChangeListener(drawingNodePropertyChangeListener);
						hullLookup.remove(node);
					}
				}
			}
			
		};
		model.addModelChangeListener(modelListener);
	}
	
	private IFigureController createController(IShapeNode node){
		IShapeAttribute attribute = node.getAttribute();
		FigureDefinitionCompiler compiler = new FigureDefinitionCompiler(attribute.getShapeDefinition());
		compiler.compile();
		IFigureController figureController = new FigureController(compiler.getCompiledFigureDefinition());
		figureController.setRequestedEnvelope(attribute.getBounds());
		figureController.setFillColour(attribute.getFillColour());
		figureController.setLineColour(attribute.getLineColour());
		figureController.setLineStyle(attribute.getLineStyle());
		figureController.setLineWidth(attribute.getLineWidth());
		assignBindVariablesToProperties(attribute, figureController);
		figureController.generateFigureDefinition();
		addListeners(node, figureController);
		// add to lookup map
		this.controllerLookup.put(node, figureController);
		return figureController;
	}
	
	
	/**
	 * @param attribute
	 * @param figureController
	 */
	private void addListeners(final IShapeNode node, final IFigureController figureController) {
		final IShapeAttribute attribute = node.getAttribute();
		attribute.addChangeListener(shapePropertyChangeListener);
		Iterator<IAnnotationProperty> iter = attribute.propertyIterator();
		while(iter.hasNext()){
			IAnnotationProperty prop = iter.next();
			prop.addChangeListener(annotPropChangeListener);
		}
	}

	/**
	 * @param shapeNode
	 */
	private void removeListeners(IShapeNode shapeNode) {
		final IShapeAttribute attribute = shapeNode.getAttribute();
		attribute.removeChangeListener(shapePropertyChangeListener);
		Iterator<IAnnotationProperty> iter = attribute.propertyIterator();
		while(iter.hasNext()){
			IAnnotationProperty prop = iter.next();
			prop.removeChangeListener(annotPropChangeListener);
		}
	}

	private void assignBindVariablesToProperties(IShapeAttribute att, final IFigureController figureController) {
		for(final String varName : figureController.getBindVariableNames()){
			if(att.containsProperty(varName)){
				IAnnotationProperty prop = att.getProperty(varName);
				prop.visit(new IAnnotationPropertyVisitor(){

					public void visitBooleanAnnotationProperty(IBooleanAnnotationProperty prop) {
						figureController.setBindBoolean(varName, prop.getValue());
					}

					public void visitIntegerAnnotationProperty(IIntegerAnnotationProperty prop) {
						figureController.setBindInteger(varName, prop.getValue());
					}

					public void visitListAnnotationProperty(IListAnnotationProperty prop) {
						logger.error("Unmatched bind variable: " + varName + ". Property has type that cannot be matched to bind variable of same name: " + prop);
					}

					public void visitNumberAnnotationProperty(INumberAnnotationProperty numProp) {
						figureController.setBindDouble(varName, numProp.getValue().doubleValue());
					}

					public void visitPlainTextAnnotationProperty(IPlainTextAnnotationProperty prop) {
						figureController.setBindString(varName, prop.getValue());
					}
					
				});
			}
			else{
				logger.error("Unmatched bind variable: " + varName
						+ ". No property matched bind variable name was found.");
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.figuredefn.IFigureControllerFactory#getFigureController(org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute)
	 */
	public IFigureController getFigureController(IShapeNode node) {
		IFigureController retVal = this.controllerLookup.get(node);
		if(retVal == null){
			retVal = this.createController(node);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.figuredefn.IFigureControllerFactory#getConvexHull(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)
	 */
	public IConvexHull getConvexHull(IDrawingNode node) {
		IConvexHull retVal = null; 
		if(node instanceof IShapeNode){
			retVal = getFigureController((IShapeNode)node).getConvexHull();
		}
		else{
			retVal = this.hullLookup.get(node);
			if(retVal == null){
				retVal = createHull(node);
			}
		}
		return retVal;
	}

	/**
	 * @param node
	 * @return
	 */
	private IConvexHull createHull(IDrawingNode node) {
		IConvexHull retVal = new RectangleHull(node.getAttribute().getBounds());
		this.hullLookup.put(node, retVal);
		node.getAttribute().addChangeListener(this.drawingNodePropertyChangeListener);
		return retVal;
	}
}
