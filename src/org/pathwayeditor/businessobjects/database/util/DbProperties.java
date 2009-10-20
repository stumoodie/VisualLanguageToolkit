/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author smoodie
 *
 */
public class DbProperties {
	private static final String BUNDLE_NAME = "org.pathwayeditor.businessobjects.database.util.db"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private DbProperties() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

}
