/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.testutilities;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;

import uk.ed.inf.tree.ITree;
import uk.ed.inf.tree.ITreeNode;

/**
 * Helper class for inspecting field values using reflection
 * 
 * @author nhanlon
 */
public class FieldInspector {
	@SuppressWarnings("unchecked")
	private static Set objectsTestedForIdentity = new HashSet();
	private static List<String> ignoredList = new ArrayList<String>();

	public static InputStream getResourceAsStream(String resource) throws FileNotFoundException {
		InputStream stream = FieldInspector.class.getResourceAsStream(resource);
		if (stream == null) {
			throw new FileNotFoundException("Cannot find " + resource);
		}
		return stream;
	}

	private static Object extractFieldValue(Field f, Object one) {
		try {
			return f.get(one);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param comparison
	 *            object one
	 * @param comparison
	 *            object two
	 * @return true if all fields identical
	 */
	@SuppressWarnings("unchecked")
	public static boolean allFieldsIdenticalForTwoMapObjects(Object one, Object two, List<String> ignored) {
		objectsTestedForIdentity = new HashSet();
		ignoredList = ignored;
		return testFieldsIdenticalForTwoMapObjects(one, two);
	}

	/**
	 * @param one
	 *            - first object for comparison
	 * @param two
	 *            - second object for comparison
	 * @param excludeds
	 *            - get methods that will not be tested
	 * @param allowed
	 *            - get methods that will be tested, if a get method is discovered that is not in this set return false
	 * @return true if all values returned by the public get methods are the same
	 */
	@SuppressWarnings("unchecked")
	public static boolean allAllowedPublicInterfaceGetMethodsExceptExcludedReturnIdenticalForTwoObjects(Object one,
			Object two) {
		objectsTestedForIdentity = new HashSet();
		return testPublicInterfaceGetMethodsExceptExcludedReturnSameForTwoObjects(one, two);
	}

	@SuppressWarnings("unchecked")
	private static boolean testPublicInterfaceGetMethodsExceptExcludedReturnSameForTwoObjects(Object one, Object two) {
		objectsTestedForIdentity.add(one);
		objectsTestedForIdentity.add(two);
		if (!checkSameInterfacesExist(one, two))// this check is to detect inappropriate use of stubs or mocks
			return false;
		Class[] c = one.getClass().getInterfaces();
		Set<Method> gets = new HashSet<Method>();
		for (Class interfaceClass : c) {
			Method[] methodsOriginal = interfaceClass.getMethods();
			for (int i = 0; i < methodsOriginal.length; i++) {
				if (methodsOriginal[i].getName().indexOf("get") != -1 && methodsOriginal[i].getParameterTypes().length == 0)
					gets.add(methodsOriginal[i]);
			}
		}
		Map<String, Method> nameMethodMap = new HashMap<String, Method>();
		Set<String> getsMinusExcludeds = nameMethodMap.keySet();
		for (Method method : gets) {
			if (method.getName().equals("getObjectType")) // TODO - URGENT NH remove this conditional when object types are fixed
				continue;
			if (!testMethodReturnsSameInTwoObjects(method, one, two)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private static boolean checkSameInterfacesExist(Object one, Object two) {
		Set first = new HashSet(Arrays.asList(one.getClass().getInterfaces()));
		Set second = new HashSet(Arrays.asList(two.getClass().getInterfaces()));
		if (!first.containsAll(second))
			return false;
		return true;
	}

	/**
	 * @param allowed
	 * @param newMethods
	 * @return true if newMethods (the methods being tested for return value) contains more then allowed,
	 */
	private static boolean extraMethodsExist(Set<String> allowed, Set<String> newMethods) {
		newMethods.removeAll(allowed);
		if (newMethods.size() > 0) {
			for (String name : newMethods) {
				System.out.println("WARNING!!! Method " + name
						+ " has not been set up for export - add to the custom converter for its class.");
			}
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private static boolean testMethodReturnsSameInTwoObjects(Method method, Object one, Object two) {
		System.out.print(method.getName() + "    had value for one: ");
		Object result1 = extractMethodValue(method, one);
		System.out.println(result1);
		System.out.print(method.getName() + "    had value for two: ");
		Object result2 = extractMethodValue(method, two);
		System.out.println(result2);
		if (result1 == null) {
			return result2 == null ? true : false;
		}
		if (result1 instanceof Collection) {
			return isIdenticalCollection((Collection) result1, (Collection) result2);
		}
		if (hasRelevantFieldsToCompare(result1) && !objectsTestedForIdentity.contains(result1))
			return testPublicInterfaceGetMethodsExceptExcludedReturnSameForTwoObjects(result1, result2);
		return result1.equals(result2);

	}

	/**
	 * @param test
	 * @return true of given object is to be inspected further, ie its part of the application domain
	 */
	public static boolean hasRelevantFieldsToCompare(Object test) {
		if (IDrawingNode.class.isAssignableFrom(test.getClass()))
			return true;
		if (IDrawingElement.class.isAssignableFrom(test.getClass()))
			return true;
		if (ICanvasAttribute.class.isAssignableFrom(test.getClass()))
			return true;
		if (IPropertyDefinition.class.isAssignableFrom(test.getClass()))
			return true;
		if (ICanvas.class.isAssignableFrom(test.getClass()))
			return true;
		if (IAnnotationProperty.class.isAssignableFrom(test.getClass()))
			return true;
		if (IModel.class.isAssignableFrom(test.getClass()))
			return true;
		if (ISubModel.class.isAssignableFrom(test.getClass()))
			return true;
		if (ITreeNode.class.isAssignableFrom(test.getClass()))
			return true;
		if (ITree.class.isAssignableFrom(test.getClass()))
			return true;
		return false;

	}

	private static Object extractMethodValue(Method method, Object one) {
		try {
			return method.invoke(one, null);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private static boolean testFieldsIdenticalForTwoMapObjects(Object one, Object two) {
		objectsTestedForIdentity.add(one);
		objectsTestedForIdentity.add(two);
		System.out.println("Object1 is:" + one + "  Object2 is: " + two);
		System.out.println("");
		for (Class c = one.getClass(); c != Object.class; c = c.getSuperclass()) {
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if (ignoredList!=null&&ignoredList.contains(fields[i].getName()))
					System.out.println("ignored field: " + fields[i].getName());
				else if (!testFieldSameInTwoObjects(fields[i], one, two)) {
					System.out.println(i);
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * @param canvas
	 */
	private static void inspectFieldsFor(Object target) {
		objectsTestedForIdentity.add(target);
	
		for (Class c = target.getClass(); c != Object.class; c = c.getSuperclass()) {
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if (ignoredList!=null&&ignoredList.contains(fields[i].getName()))
					System.out.println("ignored field: " + fields[i].getName());
				else 
					inspectField(fields[i], target);
			}
		}
	}

	/**
	 * @param canvas an ICanvas
	 * @param ignoredFields fields which we don not want to inspect
	 */
	public static void inspectAllFields(ICanvas canvas, List<String>ignoredFields) {
		ignoredList=ignoredFields;
		objectsTestedForIdentity = new HashSet();
		inspectFieldsFor(canvas);
	}
	
	/**
	 * @param field
	 * @param target
	 */
	private static void inspectField(Field field, Object target) {
		field.setAccessible(true);
		System.out.print(" "+field.getName() +" value: ");
		if(field.getName().toLowerCase().indexOf("cglib")!=-1){
			throw new RuntimeException("Proxied found in class: " + target.getClass().getSimpleName());
		}
		Object testObj = extractFieldValue(field,target);
		if(testObj instanceof Method)// the extracted value of a proxied method is the actual method it was proxying
			throw new RuntimeException("PROXIES");
		if (testObj == null) 
			return;
		else
			testObj.toString();
		if (testObj instanceof Collection) {
			List tests = new ArrayList((Collection)testObj);
			for (Object inCollection:tests){
				if(hasRelevantFieldsToCompare(inCollection)&&!objectsTestedForIdentity.contains(inCollection)){
					inspectFieldsFor(inCollection);
				}
			}
		}
		else if (hasRelevantFieldsToCompare(testObj)&&!objectsTestedForIdentity.contains(testObj) ){
			inspectFieldsFor(testObj);
		}
		else
			System.out.print(testObj);
	}

	@SuppressWarnings("unchecked")
	public static boolean testFieldSameInTwoObjects(Field f, Object one, Object two) {
		f.setAccessible(true);
		Object testObj = extractFieldValue(f, one);
		if (testObj == null) {
			if (extractFieldValue(f, two) == null)
				return true;
			return false;
		} else
			testObj.toString();// this throws exception if the object is a proxy?
		if (testObj instanceof Collection) {
			return isIdenticalCollection((Collection) testObj, (Collection) extractFieldValue(f, two));
		}
		if (objectHasRelevantFieldsToCompareAndHasNotAlreadyBeenTested(testObj))
			return testFieldsIdenticalForTwoMapObjects(testObj, extractFieldValue(f, two));

		else if (!hasRelevantFieldsToCompare(testObj)) {// object not tested but has no fields of its own
			boolean isEqual = testObj.equals(extractFieldValue(f, two));
			if (!isEqual)
				System.out.println("FIELD:" + f.getName() + " value:" + testObj + " not equals to: "
						+ extractFieldValue(f, two));
			return isEqual;
		}
		return true;// we have already tested this object
	}

	/**
	 * @return
	 */
	private static boolean objectHasRelevantFieldsToCompareAndHasNotAlreadyBeenTested(Object testObj) {
		if (hasRelevantFieldsToCompare(testObj) && !objectsTestedForIdentity.contains(testObj))// object has relevant fields to compare
			return true;
		return false;
	}

	/**
	 * @param Collection
	 *            first - a collection with natural comparator
	 * @param Collection
	 *            second - a collection with natural comparator
	 * @return true if both collections can be sorted by natural order and each object in the collections is identical
	 */
	@SuppressWarnings("unchecked")
	public static boolean isIdenticalCollection(Collection testObj, Collection otherObj) {
		if (testObj.isEmpty()) {
			if (otherObj.isEmpty())
				return true;
			return false;
		} else if (otherObj.isEmpty())
			return false;
		List firstList = new ArrayList(testObj);
		List secondList = new ArrayList(otherObj);
		if (firstList.size() != secondList.size())
			return false;
		if (firstList.size() != 1) {
			if(firstList.get(0) instanceof HibShapeAttribute)
				System.out.println("a");
			Collections.sort(firstList);
			Collections.sort(secondList);
		}
		int pos = 0;
		for (Object obj : firstList) {
			if (!objectsTestedForIdentity.contains(obj) && hasRelevantFieldsToCompare(obj)) {
				if (!testFieldsIdenticalForTwoMapObjects(obj, secondList.get(pos++)))
					return false;
			} else if (!objectsTestedForIdentity.contains(obj)) {
				if (!obj.equals(secondList.get(pos++))) {
					System.out.println("Object " + obj + "in collection at posn: " + (pos - 1) + "is not equal to:"
							+ secondList.get(pos - 1));
					return false;
				}
			} else
				pos++;
		}
		return true;
	}

	// @SuppressWarnings("unchecked")
	// private static void sortBussinessMapObjectCollectionsOnUUID(List firstList, List secondList) {
	// Collections.sort(firstList, new Comparator() {
	// public int compare(Object o1, Object o2) {
	// return ((AbstractMapObject) o1).getPersistenceObject().getObjectUUID().compareTo(
	// ((AbstractMapObject) o2).getPersistenceObject().getObjectUUID());
	// }
	// });
	// Collections.sort(secondList, new Comparator() {
	// public int compare(Object o1, Object o2) {
	// return ((AbstractMapObject) o1).getPersistenceObject().getObjectUUID().compareTo(
	// ((AbstractMapObject) o2).getPersistenceObject().getObjectUUID());
	// }
	// });
	//
	// }
	//
	// @SuppressWarnings("unchecked")
	// private static void sortHibernateMapObjectCollectionOnUUID(List firstList, List secondList) {
	// Collections.sort(firstList, new Comparator() {
	// public int compare(Object o1, Object o2) {
	// return ((IHibernateMapObject) o1).getObjectUUID().compareTo(((IHibernateMapObject) o2).getObjectUUID());
	// }
	// });
	// Collections.sort(secondList, new Comparator() {
	// public int compare(Object o1, Object o2) {
	// return ((IHibernateMapObject) o1).getObjectUUID().compareTo(((IHibernateMapObject) o2).getObjectUUID());
	// }
	// });
	// }

	// public static void setUUIDTo(Object mapObject, String val) {
	// Field[] fields = mapObject.getClass().getSuperclass().getDeclaredFields();
	// for (Field f : fields) {
	// if (f.getName().toLowerCase().indexOf("uuid") != -1) {
	// f.setAccessible(true);
	// setFieldValue(mapObject, f, val);
	// }
	// }
	// }

	public static void setFieldValue(Object shapeOne, Field f, String val) {
		try {
			f.set(shapeOne, val);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
