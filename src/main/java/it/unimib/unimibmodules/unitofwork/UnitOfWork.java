package it.unimib.unimibmodules.unitofwork;

/**
 * Defines the method that will be used by controllers to implement a Unit of Work
 * @author Davide Costantini
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface UnitOfWork<T> {

	String INSERT = "INSERT";

	String DELETE = "DELETE";

	String MODIFY = "MODIFY";

	/**
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	void registerNew(T entity);

	/**
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	void registerModified(T entity);

	/**
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	void registerDeleted(T entity);

	/**
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	void commit();
}
