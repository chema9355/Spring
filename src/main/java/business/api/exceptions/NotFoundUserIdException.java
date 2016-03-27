package business.api.exceptions;

public class NotFoundUserIdException extends ApiException {

	private static final long serialVersionUID = -1344640670884805385L;

	public static final String DESCRIPTION = "User id not found";

	public static final int CODE = 333;

	public NotFoundUserIdException() {
		this("");
	}

	public NotFoundUserIdException(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}

}
