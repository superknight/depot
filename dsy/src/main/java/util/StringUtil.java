package util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String util class
 * @author Dandelion
 * @since 2008-08-21
 * @quote something from class org.apache.commons.lang.StringUtils;
 * @see org.apache.commons.lang.StringUtils;
 */
public final class StringUtil implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6862024827645099732L;
	
	public static final String BLANK="";
	
	/**
	 * when string is null return blank,where the string is not null it return string.trim
	 * @param str
	 * @return String
	 */
	public static String trim(final String string){
		String result=null;
		if(string==null){
			result=BLANK;
		}else{
			result=string.trim();
		}
		return result;
	}
	
	/**
	 * when string is null return blank string
	 * @param string
	 * @return String
	 */
	public static String nullToBlank(final String string){
		return string==null?BLANK:string;
	}
	
	/**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
	public static boolean isBlank(final String string) {
        int strLen;
        if (string == null || (strLen = string.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(string.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
	
	/**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is
     *  not empty and not null and not whitespace
     * @since 2.0
     */
    public static boolean isNotBlank(final String string) {
        return !isBlank(string);
    }
	
	/**
	 * <p></p>
	 * @param string
	 * @param patternString
	 * @return boolean
	 */
	public static boolean isMatchPattern(final String string, final String patternString) {
		if(string!=null&&patternString!=null){			
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(string);
			return matcher.matches();
		}else{
			return false;
		}
	}
}
