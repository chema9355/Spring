package data.daos;

import java.util.Calendar;
import java.util.List;

import data.entities.Training;

public interface TrainingExtended {

	List<Training> getExistingTrainings(Calendar StartDate, Calendar endDate);
	
}
