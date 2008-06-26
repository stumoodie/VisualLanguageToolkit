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
	
	public HibTextProperty copyTextProperty(HibCanvas newCanvas, HibTextProperty other){
		return new HibTextProperty(newCanvas, other); 
	}
	
	public HibRichTextProperty copyRichTextProperty(HibCanvas newCanvas, HibRichTextProperty other){
		return new HibRichTextProperty(newCanvas, other);
	}
	
	public HibNumberProperty copyNumberProperty(HibCanvas newCanvas, HibNumberProperty other){
		return new HibNumberProperty(newCanvas, other);
	}
	
	public HibListProperty copyListProperty(HibCanvas newCanvas, HibListProperty other){
		return new HibListProperty(newCanvas, other);
	}
	
}
