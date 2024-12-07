package com.bruno.training.web.util;

import org.apache.logging.log4j.util.Strings;

public class ValidationUtils {


	public static void validatePassword(String contrasena, Errors errors) {
		if (contrasena== null || Strings.isBlank(contrasena)) {
			errors.addFieldError(Parameters.CONTRASENA, ErrorCodes.MANDATORY_FIELD);
		} else {
			contrasena = contrasena.trim();
			if (contrasena.length() < 6 || contrasena.length() > 12) {
				errors.addFieldError(Parameters.CONTRASENA, ErrorCodes.INVALID_PASSWORD_LENGTH);
			}
		}
	}

	public static void validateRol(String rolStr, Errors errors) {
		if (rolStr == null || Strings.isBlank(rolStr)) {
			errors.addFieldError(Attributes.ROL, ErrorCodes.MANDATORY_FIELD);
		} else {
			try {
				Long rol = Long.valueOf(rolStr);
				if (rol != 1 && rol != 2 && rol!=3 && rol!=4) {
					errors.addFieldError(Parameters.ROLID, ErrorCodes.UNKNOWN_ROLE);
				}
			} catch (NumberFormatException nfe) {
				errors.addFieldError(Parameters.ROLID, ErrorCodes.UNKNOWN_ROLE);
			}
		}
	}

	public static void validateRolNotMandatory(String rolStr, Errors errors) {
		try {
			Long rol = Long.valueOf(rolStr);
			if (rol != 1 && rol != 2 && rol!=3 && rol!=4) {
				errors.addFieldError(Parameters.ROLID, ErrorCodes.UNKNOWN_ROLE);
			}
		} catch (NumberFormatException nfe) {
			errors.addFieldError(Parameters.ROLID, ErrorCodes.UNKNOWN_ROLE);
		}
	}

	public static void validateCorreo(String correo, Errors errors) {
	    if (correo == null || Strings.isBlank(correo)) {
	        errors.addFieldError(Parameters.CONTRASENA, ErrorCodes.MANDATORY_FIELD);
	    } else {
	        // Regex básica para validação de e-mails
	        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
	        if (!correo.matches(emailRegex)) {
	            errors.addFieldError(Parameters.EMAIL, ErrorCodes.INVALID_EMAIL);
	        }
	    }
	}


	public static void validateCorreoNotMandatory(String correo, Errors errors) {
	    if (correo != null && !Strings.isBlank(correo)) {
	        // Regex básica para validação de e-mails
	        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
	        if (!correo.matches(emailRegex)) {
	            errors.addFieldError(Parameters.EMAIL, ErrorCodes.INVALID_EMAIL);
	        }
	    }
	}


	public static void validateId(String idStr, Errors errors) {
		if (idStr == null || Strings.isBlank(idStr)) {
			errors.addFieldError(Parameters.ID, ErrorCodes.MANDATORY_FIELD);
		} else {
			try {
				Long.valueOf(idStr);
			} catch (NumberFormatException nfe) {
				errors.addFieldError(Parameters.ID, ErrorCodes.INVALID_ID);
			}
		}
	}

	public static void validateNombre(String nombre, Errors errors) {
		if (nombre == null || Strings.isBlank(nombre)) {
			errors.addFieldError(Parameters.NOMBRE, ErrorCodes.MANDATORY_FIELD);
		} else {
			nombre = nombre.trim();
			if (nombre.length() < 2 || nombre.length() > 30) {
				errors.addFieldError(Parameters.NOMBRE, ErrorCodes.INVALID_NAME_LENGTH);
			}
			if (!nombre.matches("^[a-zA-Z0-9\\s]*$")) {
				errors.addFieldError(Parameters.NOMBRE, ErrorCodes.INVALID_NAME_CHARACTERS);
			}
		}
	}

	public static void validateNombreNotMandatory(String nombre, Errors errors) {

		nombre = nombre.trim();
		if (nombre.length() < 2 || nombre.length() > 30) {
			errors.addFieldError(Parameters.NOMBRE, ErrorCodes.INVALID_NAME_LENGTH);
		}
		if (!nombre.matches("^[a-zA-Z0-9\\s]*$")) {
			errors.addFieldError(Parameters.NOMBRE, ErrorCodes.INVALID_NAME_CHARACTERS);
		}

	}


	
	public static void validateNumericField(String valueStr, String attribute, String errorCode, Errors errors) {
		if (valueStr == null || Strings.isBlank(valueStr)) {
			errors.addFieldError(attribute, ErrorCodes.MANDATORY_FIELD);
		} else {
			try {
				Long.valueOf(valueStr);
			} catch (NumberFormatException nfe) {
				errors.addFieldError(attribute, errorCode);
			}
		}
	}

	public static void validateNumericFieldNotMandatory(String valueStr, String attribute, String errorCode,
			Errors errors) {
		try {
			Long.valueOf(valueStr);
		} catch (NumberFormatException nfe) {
			errors.addFieldError(attribute, errorCode);
		}
	}




}
