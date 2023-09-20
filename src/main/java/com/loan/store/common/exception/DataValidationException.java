package com.loan.store.common.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * for data validation errors while trying create/update objects
 */
public final class DataValidationException extends RuntimeException {
	private static final long serialVersionUID = -526313233133876822L;
	private Map<String, Object> data = new HashMap<>();

	public Map<String, Object> getData() {
		return data;
	}

	public DataValidationException(String message) {
		super(message);
	}
}