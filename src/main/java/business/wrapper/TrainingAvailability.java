package business.wrapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import data.entities.Training;

public class TrainingAvailability {

	private Calendar startDate;

	private Calendar endDate;

	private List<Training> trainings;

	public TrainingAvailability() {
	}

	public TrainingAvailability(Calendar startDate, Calendar endDate, List<Training> trainings) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainings = trainings;
	}

	public Calendar getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public List<Training> getTrainings() {
		return this.trainings;
	}

	public void setTrainings(List<Training> trainings) {
		this.trainings = trainings;
	}

	@Override
	public String toString() {
		String startDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(this.startDate.getTime());
		String endDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(this.endDate.getTime());

		return "Training Availability [StartDate=" + startDate + ", EndDate=" + endDate + ",trainings=" + this.trainings
				+ "]";
	}

}
