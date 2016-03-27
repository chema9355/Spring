package business.api.exceptions;

public class PlayerAlreadyInTrainingException extends ApiException {

	private static final long serialVersionUID = -1344640670884805385L;

	public static final String DESCRIPTION = "player already in training";

	public static final int CODE = 555;

	public PlayerAlreadyInTrainingException() {
		this("");
	}

	public PlayerAlreadyInTrainingException(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}

}
