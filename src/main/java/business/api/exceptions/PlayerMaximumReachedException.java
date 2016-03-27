package business.api.exceptions;

public class PlayerMaximumReachedException extends ApiException {

	private static final long serialVersionUID = -1344640670884805385L;

	public static final String DESCRIPTION = "player maximum reached for training";

	public static final int CODE = 999;

	public PlayerMaximumReachedException() {
		this("");
	}

	public PlayerMaximumReachedException(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}

}
