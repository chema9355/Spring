package business.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.api.Results;
import business.wrapper.TrainingAvailability;
import business.wrapper.TrainingAvailableTime;
import data.daos.CourtDao;
import data.daos.ReserveDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Court;
import data.entities.Reserve;
import data.entities.Training;
import data.entities.User;

@Controller
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

	public String createTraining(TrainingAvailableTime trainingAvailableTime, String username) {
		Court court = courtDao.findOne(trainingAvailableTime.getCourtId());
		User trainer = userDao.findByUsernameOrEmail(username);
		if (court == null) {
			return Results.COURT_NOT_FOUND;
		} else if (trainer == null) {
			return Results.TRAINER_NOT_FOUND;
		}
		int numDias = trainingAvailableTime.getEndDate().get(Calendar.DAY_OF_YEAR)
				- trainingAvailableTime.getStartDate().get(Calendar.DAY_OF_YEAR);
		int numTrainings = numDias / DAYS_IN_WEEK;
		Calendar nextClass = (Calendar) trainingAvailableTime.getStartDate().clone();
		Calendar lastClass = (Calendar) trainingAvailableTime.getStartDate().clone();
		lastClass.add(Calendar.DAY_OF_YEAR, numTrainings * DAYS_IN_WEEK);
		Training training = new Training(nextClass, lastClass, court, trainer);
		for (int i = 0; i < numTrainings; i++) {
			Reserve reserve = new Reserve(court, trainer, nextClass);
			Reserve existingReserve = reserveDao.findByCourtAndDate(court, reserve.getDate());
			if (existingReserve != null) {
				reserveDao.delete(existingReserve);
			}
			reserveDao.save(reserve);
			training.addReserve(reserve);
			nextClass.add(Calendar.DAY_OF_YEAR, DAYS_IN_WEEK);
		}
		trainingDao.save(training);
		return Results.OK;
	}

	public String deleteTraining(int trainingId) {
		Training training = trainingDao.findOne(trainingId);
		if (training == null) {
			return Results.TRAINING_NOT_FOUND;
		}
		trainingDao.delete(training);
		return Results.OK;
	}

	public String deleteTrainingPlayer(String playerName, int trainingId) {
		Training training = trainingDao.findOne(trainingId);
		User player = userDao.findByUsernameOrEmail(playerName);
		if (training == null) {
			return Results.TRAINING_NOT_FOUND;
		} else if (player == null) {
			return Results.PLAYER_NOT_FOUND;
		} else if (!training.getPlayers().contains(player)) {
			return Results.PLAYER_NOT_IN_TRAINING;
		}
		training.deletePlayer(player);
		trainingDao.save(training);
		return Results.OK;
	}

	public String registerPlayerInTraining(String playerName, int trainingId) {
		Training training = trainingDao.findOne(trainingId);
		User player = userDao.findByUsernameOrEmail(playerName);
		if (training == null) {
			return Results.TRAINER_NOT_FOUND;
		} else if (player == null) {
			return Results.PLAYER_NOT_FOUND;
		} else if (training.getPlayers().contains(player)) {
			return Results.PLAYER_ALREADY_IN_TRAINING;
		} else if (training.getPlayers().size() >= MAX_SIZE) {
			return Results.PLAYER_MAXIMUM_REACHED;
		}
		training.getPlayers().add(player);
		trainingDao.save(training);
		return Results.OK;
	}

}
