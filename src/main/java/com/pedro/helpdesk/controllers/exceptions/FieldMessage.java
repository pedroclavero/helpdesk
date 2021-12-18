package com.pedro.helpdesk.controllers.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String massege;

	public FieldMessage() {
		super();
	}

	public FieldMessage(String fieldName, String massege) {
		super();
		this.fieldName = fieldName;
		this.massege = massege;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMassege() {
		return massege;
	}

	public void setMassege(String massege) {
		this.massege = massege;
	}

}
