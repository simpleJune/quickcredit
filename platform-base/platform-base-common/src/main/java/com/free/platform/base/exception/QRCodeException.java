package com.free.platform.base.exception;

public class QRCodeException extends Exception{

	private static final long serialVersionUID = 6252442952024860665L;

	public QRCodeException() {
		super();
	}

	public QRCodeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QRCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public QRCodeException(String message) {
		super(message);
	}

	public QRCodeException(Throwable cause) {
		super(cause);
	}
	
}
