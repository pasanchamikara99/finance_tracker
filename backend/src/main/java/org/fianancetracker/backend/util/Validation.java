package org.fianancetracker.backend.util;

import org.fianancetracker.backend.dto.CategoryDTO;
import org.fianancetracker.backend.dto.UserDTO;

public class Validation {

    protected static String message = "";

    public static String userRegistrationValidate(UserDTO userDTO) {


        if (userDTO.getFirstName().isEmpty() || userDTO.getFirstName() == null) {
            return MessageVarList.FIRST_NAME_EMPTY;
        } else if (userDTO.getLastName().isEmpty() || userDTO.getLastName() == null) {
            return MessageVarList.LAST_NAME_EMPTY;
        } else if (userDTO.getEmail().isEmpty() || userDTO.getEmail() == null) {
            return MessageVarList.EMAIL_EMPTY;
        } else if (userDTO.getPassword().isEmpty() || userDTO.getPassword() == null) {
            return MessageVarList.PASSWORD_EMPTY;
        } else if (userDTO.getCpassword().isEmpty() || userDTO.getCpassword() == null) {
            return MessageVarList.PASSWORD_EMPTY;
        } else if (!userDTO.getPassword().equals(userDTO.getCpassword())) {
            return MessageVarList.PASSWORD_MISMATCH;
        } else {
            return message;
        }

    }

    public static String userLoginValidate(UserDTO userDTO) {
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            return MessageVarList.EMAIL_EMPTY;
        } else if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            return MessageVarList.PASSWORD_EMPTY;
        } else {
            return message;
        }
    }

    public static String categoryValidation(CategoryDTO categoryDTO) {
        if (categoryDTO.getDescription() == null || categoryDTO.getDescription().isEmpty()) {
            return MessageVarList.EMPTY_CATEGORY;
        } else if (categoryDTO.getType() == null || categoryDTO.getType().isEmpty()) {
            return MessageVarList.EMPTY_CATEGORY_TYPE;
        } else {
            return message;
        }
    }
}
