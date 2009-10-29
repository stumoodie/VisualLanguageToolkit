/**
 * 
 */
package org.pathwayeditor.businessobjects.hyperlink;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;

/**
 * @author smoodie
 *
 */
public class RepositoryLookupService implements IRepositoryLookupService {
	private static final String SCHEMA_STR = "pwe";
	private final IRepositoryServiceCallback callback;
	
	
	public RepositoryLookupService(IRepositoryServiceCallback callback){
		if(callback == null) throw new IllegalArgumentException("Callback should not be null");
		
		this.callback = callback;
	}
	
	public boolean isValidUri(URI hyperlink){
		return hyperlink != null && hyperlink.getScheme().equals(SCHEMA_STR);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hyperlink.IRepositoryLookupService#findCanvas(java.net.URI)
	 */
	public ICanvas findCanvas(URI hyperlink) {
		if(!isValidUri(hyperlink)) throw new IllegalArgumentException("the uri must be valid: " + hyperlink);
		if(!hyperlink.isAbsolute()) throw new IllegalArgumentException("the uri must be absolute: " + hyperlink.toString());
		
		ICanvas retVal = null;
		if(hyperlink.getHost() == null || isLocalHost(hyperlink.getHost())){
			String path = hyperlink.getPath();
			String repoName = getRepositoryName(path);
			if(repoName != null){
				IRepositoryPersistenceManager repo = findRepository(repoName);
				if(repo != null){
					String pathInRepo = getPathInRepository(path);
					IRepositoryItem repoItem = repo.getRepository().findRepositoryItemByPath(pathInRepo);
					if(repoItem != null && repoItem instanceof IMap){
						retVal = getOpenedCanvas(repo, (IMap)repoItem);
					}
				}
			}
		}
		return retVal;
	}
	
	/**
	 * @param host
	 * @return
	 */
	private boolean isLocalHost(String host) {
		boolean retVal = false;
		try {
			InetAddress address = InetAddress.getByName(host);
			retVal = address.isLoopbackAddress();
		} catch (UnknownHostException e) {
			// ignore we don't care we are just testing to see if the host is local or not
		}
		return retVal;
	}

	/**
	 * @param repoItem
	 * @return
	 */
	private ICanvas getOpenedCanvas(IRepositoryPersistenceManager repo, IMap repoItem) {
		IMapPersistenceManager mapManager = repo.getMapPersistenceManager(repoItem);
		ICanvas retVal = null;
		if(mapManager.isOpen()){
			retVal = mapManager.getCanvas();
		}
		else{
			mapManager.open();
			if(mapManager.doesCanvasExist()){
				retVal = mapManager.getCanvas();
			}
		}
		return retVal;
	}


	/**
	 * @param path
	 */
	private String getPathInRepository(String path) {
		Pattern pat = Pattern.compile("/[^/]+(/.*)$");
		Matcher mat = pat.matcher(path);
		String retVal = null;
		if(mat.matches()){
			retVal = mat.group(1);
		}
		return retVal;
	}


	private String getRepositoryName(String path){
		Pattern pat = Pattern.compile("^/([^/]+)/?.*$");
		Matcher mat = pat.matcher(path);
		String retVal = null;
		if(mat.matches()){
			retVal = mat.group(1);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hyperlink.IRepositoryLookupService#findCanvasObject(java.net.URI)
	 */
	public IDrawingElement findCanvasObject(URI hyperlink) {
		ICanvas canvas = findCanvas(hyperlink);
		IDrawingElement retVal = null;
		String anchor = hyperlink.getFragment();
		if(anchor != null){
			Integer creationSerial = parseAnchor(anchor);
			if(creationSerial != null){
				ICanvasAttribute attrib = canvas.findAttribute(creationSerial);
				retVal = attrib.getCurrentDrawingElement();
			}
		}
		return retVal;
	}
	
	
	private Integer parseAnchor(String anchor){
		Pattern pat = Pattern.compile("\\d+");
		Matcher mat = pat.matcher(anchor);
		Integer retVal = null;
		if(mat.matches()){
			retVal = Integer.valueOf(anchor);
		}
		return retVal;
	}

	private IRepositoryPersistenceManager findRepository(String name) {
		return this.callback.getRepositoryByName(name);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hyperlink.IRepositoryLookupService#createAbsoluteUri(org.pathwayeditor.businessobjects.management.IMapPersistenceManager)
	 */
	public URI createAbsoluteUri(IMapPersistenceManager map) {
		if(map.isOpen() == false) throw new IllegalArgumentException("Map manager must be open");
		
		StringBuilder pathBuf = new StringBuilder("/");
		pathBuf.append(map.getMap().getRepository().getName());
		pathBuf.append(map.getMap().getPath());
		URI retVal = null;
		try {
			retVal = new URI(SCHEMA_STR, null, pathBuf.toString(), null);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hyperlink.IRepositoryLookupService#createAbsoluteUri(org.pathwayeditor.businessobjects.management.IMapPersistenceManager, org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement)
	 */
	public URI createAbsoluteUri(IMapPersistenceManager map, IDrawingElement drawingElement) {
		URI retVal = null;
		try {
			URI mapUri = createAbsoluteUri(map);
			String serial = Integer.toString(drawingElement.getAttribute().getCreationSerial());
			retVal = mapUri.resolve(new URI(null, null, null, serial));
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		return retVal;
	}
}
