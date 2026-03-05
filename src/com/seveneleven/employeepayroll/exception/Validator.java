package com.seveneleven.employeepayroll.exception;

import java.util.regex.Pattern;

/**
 * Validator class provides static validation methods
 * for employee data such as email, phone number, and employee ID.
 * 
 * Uses Regular Expressions (Regex) to validate input formats.
 */
public class Validator {

    /**
     * Validates the email format.
     * 
     * Example valid emails:
     * user@gmail.com
     * employee123@company.org
     * 
     * @param email the email address to validate
     * @throws ValidationException if the email format is invalid
     */
    public static void validateEmail(String email) throws ValidationException {

        // Regex pattern for validating email format
        String emailPattern = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";

        // Pattern.matches returns true if email matches regex
        if (!Pattern.matches(emailPattern, email)) {
            throw new ValidationException("Invalid email format");
        }
    }

    /**
     * Validates the phone number format.
     * 
     * Rules:
     * - Must be 10 digits
     * - Must start with 6, 7, 8, or 9
     * 
     * Example valid numbers:
     * 9876543210
     * 9123456789
     * 
     * @param phone the phone number to validate
     * @throws ValidationException if phone format is invalid
     */
    public static void validatePhone(String phone) throws ValidationException {

        // Regex pattern for Indian phone numbers
        String phonePattern = "^[6-9][0-9]{9}$";

        if (!Pattern.matches(phonePattern, phone)) {
            throw new ValidationException("Invalid phone number format");
        }
    }

    /**
     * Validates the employee ID format.
     * 
     * Required format:
     * EMP-XXXX
     * 
     * Where XXXX is a 4-digit number.
     * 
     * Example valid IDs:
     * EMP-1001
     * EMP-2345
     * 
     * @param empId the employee ID to validate
     * @throws ValidationException if the ID format is invalid
     */
    public static void validateEmpID(String empId) throws ValidationException {

        // Regex pattern for employee ID
        String empIdPattern = "^EMP-[0-9]{4}$";

        if (!Pattern.matches(empIdPattern, empId)) {
            throw new ValidationException("Invalid empId format");
        }
    }
}