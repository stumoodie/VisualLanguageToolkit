/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

/**
 * @author smoodie
 *
 */
class PropertyVisitor {
	private static PropertyVisitor anInstance;
	
	private PropertyVisitor(){}

	public static PropertyVisitor getInstance(){
		if(anInstance == null){
			anInstance = new PropertyVisitor();
		}
		return anInstance;
	}
	
	public TextProperty copyTextProperty(Canvas newCanvas, TextProperty other){
		return new TextProperty(newCanvas, other); 
	}
	
	public RichTextProperty copyRichTextProperty(Canvas newCanvas, RichTextProperty other){
		return new RichTextProperty(newCanvas, other);
	}
	
	public NumberProperty copyNumberProperty(Canvas newCanvas, NumberProperty other){
		return new NumberProperty(newCanvas, other);
	}
	
	public ListProperty copyListProperty(Canvas newCanvas, ListProperty other){
		return new ListProperty(newCanvas, other);
	}
	
}
