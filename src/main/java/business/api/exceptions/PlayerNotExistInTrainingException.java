package business.api.exceptions;

public class PlayerNotExistInTrainingException extends ApiException{
	
	private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "player no existente en el entrenamiento";

    public static final int CODE = 555;

    public PlayerNotExistInTrainingException() {
        this("");
    }

    public PlayerNotExistInTrainingException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
