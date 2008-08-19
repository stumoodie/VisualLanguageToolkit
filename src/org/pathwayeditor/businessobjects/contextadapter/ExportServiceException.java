package org.pathwayeditor.businessobjects.contextadapter;

public class ExportServiceException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7000244894654454094L;
    private IValidationReport report;
	public ExportServiceException() {
	}

	public ExportServiceException(String message) {
		super(message);
	}
	
	public ExportServiceException(String message, IValidationReport report) {
		super(message);
		this.report=report;
		
	}

	public ExportServiceException(Throwable cause) {
		super(cause);
	}

	public ExportServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	IValidationReport getReport() {
		return report;
	}


}
