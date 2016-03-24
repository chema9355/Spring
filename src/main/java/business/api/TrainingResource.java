package business.api;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.NotFoundCourtIdException;
import business.api.exceptions.NotFoundTrainingIdException;
import business.api.exceptions.NotFoundUserIdException;
import business.api.exceptions.PlayerAlreadyInTrainingException;
import business.api.exceptions.PlayerMaximumReachedException;
import business.api.exceptions.PlayerNotExistInTrainingException;
import business.controllers.TrainingController;
import business.wrapper.TrainingAvailability;
import business.wrapper.TrainingAvailableTime;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {

    private TrainingController trainingController;

    @Autowired
    public void setTrainingController(TrainingController trainingController) {
        this.trainingController = trainingController;
    }

    @RequestMapping(value = Uris.AVAILABILITY, method = RequestMethod.GET)
    public TrainingAvailability showTrainingAvailability(@RequestParam(required = true) Calendar startDate, Calendar endDate){
        return trainingController.showTrainings(startDate, endDate);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createTraining(@AuthenticationPrincipal User activeUser, @RequestBody TrainingAvailableTime availableTime)
            throws NotFoundCourtIdException, NotFoundUserIdException {
    	
    	String result = trainingController.createTraining(availableTime, activeUser.getUsername());
    	
    	if (result == Results.COURT_NOT_FOUND) {
            throw new NotFoundCourtIdException("" + availableTime.getCourtId());
        }
    	else if (result == Results.TRAINER_NOT_FOUND) {
            throw new NotFoundUserIdException("" + activeUser.getUsername());
        }
    	else {
    		return Results.OK;
        }
    }
    
    @RequestMapping(value = Uris.PLAYERS, method = RequestMethod.DELETE)
    public String deletePlayer(@RequestParam(required = true) String playerName, int trainingId)
    		throws NotFoundTrainingIdException, NotFoundUserIdException, PlayerNotExistInTrainingException {    
	    
	    String result = trainingController.deleteTrainingPlayer(playerName, trainingId);
	    
	    if (result == Results.TRAINING_NOT_FOUND) {
	        throw new NotFoundTrainingIdException("training " + trainingId);
	    }
		else if (result == Results.PLAYER_NOT_FOUND) {
	        throw new NotFoundUserIdException("" + playerName);
	    }
		else if (result == Results.PLAYER_NOT_IN_TRAINING) {
	        throw new PlayerNotExistInTrainingException("" + playerName + "Not exist in Training");
	    }
		else {
			return Results.OK;
	    }
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteTraining(@RequestParam(required = true) int trainingId)
    		throws NotFoundTrainingIdException{    
	    
	    String result = trainingController.deleteTraining(trainingId);
	    
	    if (result == Results.TRAINING_NOT_FOUND) {
	        throw new NotFoundTrainingIdException("training " + trainingId);
	    }
		else {
			return Results.OK;
	    }
    }
    
    @RequestMapping(value = Uris.PLAYERS, method = RequestMethod.POST)
    public String registerPlayerInTraining(@RequestParam(required = true) String playerName, int trainingId)
            throws NotFoundTrainingIdException, NotFoundUserIdException, PlayerMaximumReachedException, PlayerAlreadyInTrainingException {
    	
    	String result = trainingController.registerPlayerInTraining(playerName, trainingId);
    	
    	if (result == Results.PLAYER_NOT_FOUND) {
            throw new NotFoundUserIdException("" + playerName);
        }
    	else if (result == Results.TRAINING_NOT_FOUND) {
            throw new NotFoundTrainingIdException("" + trainingId);
        }
    	if (result == Results.PLAYER_ALREADY_IN_TRAINING) {
            throw new PlayerAlreadyInTrainingException("" + playerName);
        }
    	else if (result == Results.PLAYER_MAXIMUM_REACHED) {
            throw new PlayerMaximumReachedException("" + trainingId);
        }
    	else {
    		return Results.OK;
        }
    }
    
    
}
