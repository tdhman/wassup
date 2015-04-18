package enst.infsi351.wassup;

import java.util.regex.Pattern;

import android.widget.EditText;
 
public class Validation {
 
    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
 
    // Error Messages
    private static final String PASS_DONT_MATCH = "confirmed password does not match";
    //private static final String ACCOUNT_EXIST = "account existed";
    private static final String REQUIRED_MSG = "required";
    private static final String EMAIL_MSG = "invalid email";
    private static final String PHONE_MSG = "###-#######";
 
    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }
 
    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }
 
    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {
 
        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);
 
        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;
 
        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };
 
        return true;
    }
    
    // check 2 inputs values are the same
    
    public static boolean isEqual(EditText editText, EditText editText2){
    	String text1 = editText.getText().toString().trim();
    	String text2 = editText2.getText().toString().trim();
    	boolean equal =  (text1.equals(text2));
    	if (text1.length()==0){
    		editText.setError(REQUIRED_MSG);
    		return false;
    	}
    	if (equal==false){
    		editText.setError(PASS_DONT_MATCH);
    		return false;
    	}
    		
    	return true;
    }
    
    public static boolean checkExisted(EditText editText, String msg){
    	String account = editText.getText().toString().trim();
    	if (account.equals("wassap")){
    		editText.setError(msg);
    		return false;
    	}
    	return true;
    }
 
    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {
 
        String text = editText.getText().toString().trim();
        editText.setError(null);
 
        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }
 
        return true;
    }
}