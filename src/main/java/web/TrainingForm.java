package web;

public class TrainingForm {

	private int courtId;

	private int startDayOfYear;

	private int endDayOfYear;

	private String trainer;

	public TrainingForm(int courtId, int starDayOfYear, int endDayOfYear, String trainer) {
		this.courtId = courtId;
		this.startDayOfYear = starDayOfYear;
		this.endDayOfYear = endDayOfYear;
		this.trainer = trainer;
	}

	public TrainingForm() {
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}

	public int getStartDayOfYear() {
		return startDayOfYear;
	}

	public void setStartDayOfYear(int startDayOfYear) {
		this.startDayOfYear = startDayOfYear;
	}

	public int getEndDayOfYear() {
		return endDayOfYear;
	}

	public void setEndDayOfYear(int endDayOfYear) {
		this.endDayOfYear = endDayOfYear;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

}
