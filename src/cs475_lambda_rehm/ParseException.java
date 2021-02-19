/**
 * @author Chad Rehm
 * @date 2/18/21
 * @description this is the parse exception class
 */
package cs475_lambda_rehm;

public class ParseException extends Exception {
	public ParseException(String errorMessage) {
		 super(errorMessage);
	}
}
