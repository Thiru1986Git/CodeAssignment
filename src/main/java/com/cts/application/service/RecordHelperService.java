package com.cts.application.service;

import org.springframework.stereotype.Service;

import com.cts.application.utils.FileConstants;

//Service class for Record level operations
@Service
public class RecordHelperService {

	/**
	 * The method is used to validate a field
	 * @param fieldValue
	 * @return
	 */
	public Boolean isFieldValid(String fieldValue) {
		if (fieldValue != null && !fieldValue.trim().equals(FileConstants.EMPTY_STRING))
			return true;
		return false;
	}

}
