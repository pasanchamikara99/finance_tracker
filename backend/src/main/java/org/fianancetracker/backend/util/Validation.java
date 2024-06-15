package org.fianancetracker.backend.util;

import org.fianancetracker.backend.dto.UserDTO;

public class Validation {


    public static String userRegistrationValidate(UserDTO userDTO) {

        String message = "";

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

        String message = "";
        if (userDTO.getEmail().isEmpty() || userDTO.getEmail() == null) {
            return MessageVarList.EMAIL_EMPTY;
        } else if (userDTO.getPassword().isEmpty() || userDTO.getPassword() == null) {
            return MessageVarList.PASSWORD_EMPTY;
        } else {
            return message;
        }

    }
}
