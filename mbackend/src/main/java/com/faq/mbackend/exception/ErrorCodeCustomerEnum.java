package com.faq.mbackend.exception;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom error codes
 * <p>
 * These are custom error codes specific to BaasBox, returned into the bb_code field.
 * <p>
 * 40001: You are attempting to update a database object with older data. Version is not the same.
 * 40002: The ACL field is not a valid JSON string.
 * 40003: The specified permission is unknown. Valid ones are ‘read’, 'update’, 'delete’, 'all’.
 * 40004: Only users and roles can be used.
 * 40005: The specified user does not exist.
 * 40006: The specified role does not exist.
 * 40010: The JSON value must be an array.
 * 40011: The body payload cannot be empty.
 * 40101: Authentication info not valid or not provided. HINT: has your session expired?.
 * 40020: The body payload doesn’t contain the 'message’ key or message value is NOT a String.
 * 40021: Push profile invalid. It must be an Array of integer and accepted values are 1,2 or 3.
 * 40022: Users MUST be an array of String.
 * 40023: The body payload doesn’t contain key users.
 * 40024: Sound value MUST be a String.
 * 40025: Badge value MUST be a number.
 * 40026: ActionLocalizedKey MUST be a String
 * 40027: LocalizedKey MUST be a String.
 * 40028: LocalizedArguments MUST be an Array of String.
 * 40029: Collapse_key MUST be a String
 * 40030: Time_to_live MUST be a positive number or equal zero.
 * 40031: Message MUST be a String.
 * 50301: Push settings are not properly configured. HINT: go to the administration console and check the settings.
 * 50302: The server cannot resolve the host name. HINT: check your internet connection.
 * 50303: Could not send push notifications. HINT: Check your API Key (Google).
 * 50304: Could not save API KEY. HINT: Check your API Key, it’s possible that push service aren’t enabled in the Google Play Developer Console.
 * 50305: Push app disabled, one or more app are disabled.
 * 50306: Cannot switch, because settings for the selected mode are missing.
 */
public enum ErrorCodeCustomerEnum implements ErrorCodeEnum {


    UNKNOWN_ERROR("40001", "UNKNOWN_ERROR", "You are attempting to update a database object with older data. Version is not the same"),
    INVALID_PARAMS("40002", "INVALID_PARAMS", "The specified permission is unknown. Valid ones are 'read’, 'update’, 'delete’, 'all’"),
    NOT_FOUND("40003", "NOT_FOUND", "customer.error.entity.notfound"),
    PERMISSION_ERROR("40004", "PERMISSION_ERROR", "Only users and roles can be used"),

    //FIXME: add more here

    ;

    // lookup table to be used to find enum for conversion
    private static final Map<String, ErrorCodeCustomerEnum> lookup = new HashMap<String, ErrorCodeCustomerEnum>();

    static {
        for (ErrorCodeCustomerEnum e : EnumSet.allOf(ErrorCodeCustomerEnum.class))
            lookup.put(e.getErrorCode(), e);
    }

    private static ServiceEnum serviceEnum = ServiceEnum.CUSTOMER_SERVICE;
    private String errorCode;
    private String name;
    private String i18nKey;

    ErrorCodeCustomerEnum(String errorCode, String name, String i18nKey) {
        this.errorCode = errorCode;
        this.name = name;
        this.i18nKey = i18nKey;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getName() {
        return this.name;
    }

    public int getServiceId() {
        return serviceEnum.getServiceId();
    }

    public String getMessageKey() {
        return i18nKey;
    }

    public String getDefaultMessage() {
        switch (this) {
            case UNKNOWN_ERROR:
                return "An unknown error has been encountered";
            case INVALID_PARAMS:
                return "Invalid parameters were received";
            case NOT_FOUND:
                return "Requested entity was not found";
            case PERMISSION_ERROR:
                return "Duplicate customer name used";

            //FIXME: add more here and can use resource bundle with i18nKey if desired

            default:
                return "An undefined error has been encountered";
        }
    }

    public static ErrorCodeCustomerEnum get(String errorCode) {
        return lookup.get(errorCode);
    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getBb_code() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getResource() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getMethod() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getAPI_version() {
        // TODO Auto-generated method stub
        return null;
    }


}
