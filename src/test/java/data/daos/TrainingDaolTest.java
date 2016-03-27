package data.daos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class, TestsPersistenceConfig.class })
public class TrainingDaolTest {

	@Autowired
	private DaosService daosService;

	@Autowired
	private TrainingDao trainingDao;

	@Test
	public void testExistingTraining() {
		Calendar startDate = Calendar.getInstance();
		startDate.set(Calendar.DAY_OF_YEAR, 1);
		Calendar endDate = Calendar.getInstance();
		endDate.set(Calendar.DAY_OF_YEAR, 80);
		List<Training> trainings = trainingDao.getExistingTrainings(startDate, endDate);
		assertEquals(1, trainings.size());
		startDate.set(Calendar.DAY_OF_YEAR, 32);
		endDate.set(Calendar.DAY_OF_YEAR, 50);
		trainings = trainingDao.getExistingTrainings(startDate, endDate);
		assertEquals(0, trainings.size());
		startDate.set(Calendar.DAY_OF_YEAR, 1);
		endDate.set(Calendar.DAY_OF_YEAR, 100);
		trainings = trainingDao.getExistingTrainings(startDate, endDate);
		assertEquals(2, trainings.size());
		daosService.deleteAll();
	}

}
