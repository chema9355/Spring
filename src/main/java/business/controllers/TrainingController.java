package business.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import business.wrapper.Availability;
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

    public Availability showTrainings(Calendar calendarDay) {
        Calendar endDay = (Calendar) calendarDay.clone();
        endDay.add(Calendar.DAY_OF_MONTH, 1);
        List<Training> trainingList = trainingDao.findAll();
        Map<Integer, List<Integer>> allTimesAvailable = new HashMap<>();

        for (Training training : trainingList) {
            List<Integer> hourList = new ArrayList<>();
            if (training.getPlayers().size()<MAX_SIZE && (training.getDate().get(Calendar.DAY_OF_YEAR) == calendarDay.get(Calendar.DAY_OF_YEAR))){            	
            hourList.add(training.getDate().get(Calendar.HOUR_OF_DAY));          
            allTimesAvailable.put(training.getId(), hourList);
            }
        }        
        return new Availability(calendarDay, allTimesAvailable);
    }

    public boolean createTraining(int courtId, Calendar date, String username) {	
    	Court court = courtDao.findOne(courtId);
    	Reserve reserve = new Reserve(court, date);
        if (reserveDao.findByCourtAndDate(reserve.getCourt(), reserve.getDate()) != null) {
            return false;
        }
        User trainer = userDao.findByUsernameOrEmail(username);
        reserve.setUser(trainer);
        reserveDao.save(reserve);
        Training training = new Training(date, court, trainer);
        trainingDao.save(training);
        return true;
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
    
    public boolean deleteTrainingPlayer(String playerName, int trainingId){
    	Training training = trainingDao.findOne(trainingId);
    	User player = userDao.findByUsernameOrEmail(playerName);
    	if (training == null)
    	{
    		return false;
    	}
    	if (!training.getPlayers().contains(player))
    	{
    		return false;
    	}
    	training.deletePlayer(player);
	    trainingDao.save(training);
	    return true;
    }
    
    public boolean registerTraining(String playerName, int trainingId) {
    	Training training = trainingDao.findOne(trainingId);
    	User player = userDao.findByUsernameOrEmail(playerName);
    	if (training == null)
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

