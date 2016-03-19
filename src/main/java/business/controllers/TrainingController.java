package business.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import business.api.Results;
import business.wrapper.Availability;
import business.wrapper.TrainingAvailability;
import data.daos.CourtDao;
import data.daos.ReserveDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Court;
import data.entities.Reserve;
import data.entities.Training;
import data.entities.User;

public class TrainingController {
    
    private static final int MAX_SIZE = 4;
    
    private static final int DAYS_IN_WEEK = 7;

    private TrainingDao trainingDao;

    private CourtDao courtDao;

    private UserDao userDao;
    
    private ReserveDao reserveDao;

    @Autowired
    public void setTraining(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }
    
    @Autowired
    public void setReserve(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
    }

    @Autowired
    public void setCourtDao(CourtDao courtDao) {
        this.courtDao = courtDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TrainingAvailability showTrainings(Calendar startDate, Calendar endDate) {
        List<Training> trainings = trainingDao.getExistingTrainings(startDate, endDate);
    	return new TrainingAvailability(startDate, endDate, trainings);
    }

    public String createTraining(int courtId, Calendar startDate, Calendar endDate, String username) {	
    	Court court = courtDao.findOne(courtId);
    	User trainer = userDao.findByUsernameOrEmail(username);
    	if (court == null)
    	{
    		return Results.COURT_NOT_FOUND;
    	}
    	else if(trainer == null)
    	{
    		return Results.TRAINING_NOT_FOUND;
    	}
    	int numDias = startDate.get(Calendar.DAY_OF_YEAR) - endDate.get(Calendar.DAY_OF_YEAR);
    	int numTrainings = numDias / DAYS_IN_WEEK;
    	Calendar lastClass = (Calendar) startDate.clone();
    	lastClass.add(Calendar.DAY_OF_YEAR, numTrainings * DAYS_IN_WEEK);
        Training training = new Training(startDate, lastClass, court, trainer);
        for (int i = 0; i<numTrainings; i++)
        {
        	Reserve reserve = new Reserve(court, trainer, startDate);
        	reserveDao.save(reserve);
        	training.addReserve(reserve);
        	startDate.add(Calendar.DAY_OF_YEAR, DAYS_IN_WEEK);
        }
        trainingDao.save(training);
        return Results.OK;
    }
    
    public boolean deleteTraining(int trainingId){
    	Training training = trainingDao.findOne(trainingId);
    	if (training == null)
    	{
    		return false;
    	}
	    trainingDao.delete(training);
	    return true;
    }
    
    public String deleteTrainingPlayer(String playerName, int trainingId){
    	Training training = trainingDao.findOne(trainingId);
    	User player = userDao.findByUsernameOrEmail(playerName);
    	if (training == null )
    	{
    		return Results.TRAINING_NOT_FOUND;
    	}
    	else if (player == null)
    	{
    		return Results.USER_NOT_FOUND;
    	}
    	else if (!training.getPlayers().contains(player))
    	{
    		return Results.PLAYER_NOT_IN_TRAINING;
    	}
    	training.deletePlayer(player);
	    trainingDao.save(training);
	    return Results.OK;
    }
    
    public boolean registerPlayerInTraining(String playerName, int trainingId) {
    	Training training = trainingDao.findOne(trainingId);
    	User player = userDao.findByUsernameOrEmail(playerName);
    	if (training == null)
    	{
    		return false;
    	}
    	else if (player == null)
    	{
    		return false;
    	}
    	if (training.getPlayers().contains(player) || training.getPlayers().size() >= MAX_SIZE)
    	{
    		return false;
    	}
    	training.getPlayers().add(player);
	    trainingDao.save(training);
	    return true;
    }


}

