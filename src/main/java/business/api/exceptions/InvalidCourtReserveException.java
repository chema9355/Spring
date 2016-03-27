package business.api.exceptions;

public class InvalidCourtReserveException extends ApiException {

	private static final long serialVersionUID = -1344640670884805385L;

	public static final String DESCRIPTION = "invalid court reservation";

	public static final int CODE = 1;

	public InvalidCourtReserveException() {
		this("");
	}

	public InvalidCourtReserveException(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}

}
