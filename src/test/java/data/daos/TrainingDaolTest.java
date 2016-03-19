package data.daos;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Court;
import data.entities.Training;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaolTest {
	
	    @Autowired
	    private DaosService daosService;

	    @Autowired
	    private TrainingDao trainingDao;
	    
	    @Autowired
	    private CourtDao courtDao;
	    
	    @Autowired
	    private UserDao userDao;
	    
	    @Test
	    public void testFindByCourt() {
	        Court c = new Court(1);
	        assertEquals(1, trainingDao.findByCourt(c).size());
	    }
	    
	    @Test
	    public void testFindByTrainer() {
	    User trainer = userDao.findByUsernameOrEmail("u1");
	    assertEquals(4, trainingDao.findByTrainer(trainer).size());
	    }
	    
	    @Test
	    public void testFindByTrainerAndCourt() {
	    User trainer = userDao.findByUsernameOrEmail("u1");
	    Court c = new Court(1);
	    assertEquals(1, trainingDao.findByTrainerAndCourt(trainer, c).size());
	    }


}
