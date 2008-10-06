/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

/**
 * @author smoodie
 *
 */
public class HibNotationFactory implements IHibNotationFactory {
	private final INotationSyntaxService syntaxService;
	private final SessionFactory factory;
	private HibNotation notation;
	private final Map<IObjectType, HibObjectType> objectTypeMapping; 
	
	public HibNotationFactory(SessionFactory factory, INotationSyntaxService syntaxService){
		this.factory = factory;
		this.syntaxService = syntaxService;
		this.objectTypeMapping = new HashMap<IObjectType, HibObjectType>();
	}

	public void initialise(){
		if(!doesNotationExist()){
			storeNotation();
		}
		loadNotation();
	}
	
	
	/**
	 * 
	 */
	private void storeNotation() {
		Session sess = factory.getCurrentSession();
		INotation subSystemNotation = this.syntaxService.getNotation();
		HibNotation notation = new HibNotation(subSystemNotation.getGlobalId(), subSystemNotation.getName(),
				subSystemNotation.getDescription(),	subSystemNotation.getVersion());
		Iterator<IObjectType> iter = this.syntaxService.objectTypeIterator();
		while(iter.hasNext()){
			IObjectType objectType = iter.next();
			HibObjectType hibObjectType = new HibObjectType(objectType.getUniqueId(), objectType.getName(), objectType.getDescription()); 
			notation.addObjectType(hibObjectType);
		}
		sess.save(notation);
	}

	public void loadNotation(){
		Session sess = factory.getCurrentSession();
		Query qry = sess.createQuery("select count(*) from HibNotation where globalId = :globalId").setString("globalId", notation.getGlobalId());
		this.notation = (HibNotation)qry.uniqueResult();
		for(HibObjectType hibObjectType : this.notation.getObjectTypes()){
			IObjectType objectType = this.syntaxService.getObjectType(hibObjectType.getUniqueId());
			this.objectTypeMapping.put(objectType, hibObjectType);
		}
	}
	
	private final boolean doesNotationExist(){
		INotation notation = syntaxService.getNotation();
		Session sess = factory.getCurrentSession();
		Query qry = sess.createQuery("select count(*) from HibNotation where globalId = :globalId").setString("globalId", notation.getGlobalId());
		Integer numNotations = (Integer)qry.uniqueResult();
		return numNotations.intValue() > 0;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public HibNotation getNotation(INotation notation) {
		return this.notation;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType getObjectType(IObjectType objectType) {
		return this.objectTypeMapping.get(objectType);
	}

}
