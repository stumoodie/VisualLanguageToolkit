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
import org.pathwayeditor.businessobjects.hibernate.pojos.ObjectTypeClassification;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class HibNotationFactory implements IHibNotationFactory {
	private final INotationSubsystem notationSubsystem;
	private final SessionFactory factory;
	private HibNotation notation = null;
	private final Map<IObjectType, HibObjectType> objectTypeMapping; 
	
	public HibNotationFactory(SessionFactory factory, INotationSubsystem syntaxService){
		this.factory = factory;
		this.notationSubsystem = syntaxService;
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
		INotation subSystemNotation = this.notationSubsystem.getNotation();
		HibNotation notation = new HibNotation(subSystemNotation.getGlobalId(), subSystemNotation.getName(),
				subSystemNotation.getDescription(),	subSystemNotation.getVersion());
		Iterator<IObjectType> iter = this.notationSubsystem.getSyntaxService().objectTypeIterator();
		while(iter.hasNext()){
			IObjectType objectType = iter.next();
			ObjectTypeClassification otClassn = objectType instanceof IShapeObjectType ? ObjectTypeClassification.SHAPE
					: objectType instanceof ILinkObjectType ? ObjectTypeClassification.LINK : ObjectTypeClassification.ROOTOBJECT; 
			HibObjectType hibObjectType = new HibObjectType(objectType.getUniqueId(), objectType.getName(), objectType.getDescription(), otClassn); 
			notation.addObjectType(hibObjectType);
		}
		sess.save(notation);
	}

	private void loadNotation(){
		Session sess = factory.getCurrentSession();
		Query qry = sess.createQuery("from HibNotation n left outer join fetch n.objectTypes where n.globalId = :globalId").setString("globalId", notationSubsystem.getNotation().getGlobalId());
		this.notation = (HibNotation)qry.uniqueResult();
		for(HibObjectType hibObjectType : this.notation.getObjectTypes()){
			IObjectType objectType = this.notationSubsystem.getSyntaxService().getObjectType(hibObjectType.getUniqueId());
			this.objectTypeMapping.put(objectType, hibObjectType);
		}
	}
	
	private final boolean doesNotationExist(){
		INotation notation = notationSubsystem.getNotation();
		Session sess = factory.getCurrentSession();
		Query qry = sess.createQuery("select count(*) from HibNotation where globalId = :globalId").setString("globalId", notation.getGlobalId());
		long numNotations = (Long)qry.uniqueResult();
		return numNotations > 0;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public HibNotation getNotation() {
		return this.notation;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType getObjectType(IObjectType objectType) {
		return this.objectTypeMapping.get(objectType);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

}
