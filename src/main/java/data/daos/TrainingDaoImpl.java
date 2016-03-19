package data.daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Training;

@Repository
public class TrainingDaoImpl implements TrainingExtended{

	@Autowired
	private TrainingDao trainingDao;
	
	@Override
	public List<Training> getExistingTrainings(Calendar startDate, Calendar endDate) {
		List<Training> totalTrainings = trainingDao.findAll();
		List<Training> validTrainings = new ArrayList<Training>();
		
		for (Training training : totalTrainings)
		{
			if (startDate.getTimeInMillis() < training.getStarDate().getTimeInMillis() &&
				endDate.getTimeInMillis() > training.getEndDate().getTimeInMillis())
			{
				validTrainings.add(training);
			}
		}
		return validTrainings;
	}

}
