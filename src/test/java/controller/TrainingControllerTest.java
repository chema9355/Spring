package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import business.api.Results;
import business.controllers.TrainingController;
import business.wrapper.TrainingAvailability;
import business.wrapper.TrainingAvailableTime;
import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.DaosService;
import data.daos.ReserveDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Reserve;
import data.entities.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class, TestsPersistenceConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrainingControllerTest {

	@Autowired
	private DaosService daosService;

	@Autowired
	private TrainingDao trainingDao;

	@Autowired
	private ReserveDao reserveDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private TrainingController trainingController;



	@Test
	public void testShowTrainings() {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.set(Calendar.DAY_OF_YEAR, 1);
		endDate.set(Calendar.DAY_OF_MONTH, 100);
		TrainingAvailability trainingAvailability = trainingController.showTrainings(startDate, endDate);
		assertEquals(2, trainingAvailability.getTrainings().size());
		daosService.deleteAll();
	}

	@Test
	public void testCreateTrainings() {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.set(Calendar.DAY_OF_YEAR, 1);
		endDate.set(Calendar.DAY_OF_YEAR, 100);
		TrainingAvailableTime trainingAvailableTime = new TrainingAvailableTime(7, startDate, endDate);
		assertEquals(Results.COURT_NOT_FOUND, trainingController.createTraining(trainingAvailableTime, "tr1"));
		trainingAvailableTime = new TrainingAvailableTime(1, startDate, endDate);
		assertEquals(Results.TRAINER_NOT_FOUND, trainingController.createTraining(trainingAvailableTime, "pepe"));
		assertEquals(Results.OK, trainingController.createTraining(trainingAvailableTime, "tr1"));
	}

	@Test
	public void testDeleteTrainings() {
		trainingController.deleteTraining(3);
		assertEquals(null, trainingDao.findOne(3));
		List<Reserve> reserves = reserveDao.findAll();
		for (Reserve r : reserves) {
			assertTrue(r.getUser().getUsername() != "tr1");
		}
	}

	@Test
	public void testDeleteTrainingPlayer() {
		assertEquals(Results.PLAYER_NOT_FOUND, trainingController.deleteTrainingPlayer("player", 2));
		assertEquals(Results.TRAINING_NOT_FOUND, trainingController.deleteTrainingPlayer("u1", 16));
		assertEquals(Results.PLAYER_NOT_IN_TRAINING, trainingController.deleteTrainingPlayer("u1", 2));
		Training training = trainingDao.findOne(2);
		training.addPlayer(userDao.findByUsernameOrEmail("u1"));
		trainingDao.save(training);
		assertEquals(Results.OK, trainingController.deleteTrainingPlayer("u1", 2));
	}

	@Test
	public void testRegisterPlayerInTraining() {
		assertEquals(Results.PLAYER_NOT_FOUND, trainingController.deleteTrainingPlayer("player", 2));
		assertEquals(Results.TRAINING_NOT_FOUND, trainingController.deleteTrainingPlayer("u1", 16));
		Training training = trainingDao.findOne(2);
		training.addPlayer(userDao.findByUsernameOrEmail("u1"));
		trainingDao.save(training);
		assertEquals(Results.PLAYER_ALREADY_IN_TRAINING, trainingController.registerPlayerInTraining("u1", 2));
		assertEquals(Results.OK, trainingController.registerPlayerInTraining("u2", 2));
		assertEquals(2, trainingDao.findOne(2).getPlayers().size());
	}

}
