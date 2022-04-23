package light.restassured.exceptions;


/**
 * JsonElementNotFoundException occurs when working with Json
 *
 */
public class JsonElementNotFoundException extends RuntimeException {

	/**
	 * Constructor with exception message
	 * @param message exception message
	 */
	public JsonElementNotFoundException(String message) {
		super(message);
	}
}