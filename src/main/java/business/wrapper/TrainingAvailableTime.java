package business.wrapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TrainingAvailableTime {

    private int courtId;

    private Calendar startDate;
    
    private Calendar endDate;

    public TrainingAvailableTime() {
    }

    public TrainingAvailableTime(int courtId, Calendar startDate, Calendar endDate) {
        this.courtId = courtId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public Calendar getStartDate() {
        return this.startDate;
    }

    public void setTime(Calendar startDate) {
        this.startDate = startDate;
    }
    
    public Calendar getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String start = new SimpleDateFormat("HH:00").format(startDate.getTime());
        String end = new SimpleDateFormat("HH:00").format(endDate.getTime());
        return "AvailableTime [courtId=" + courtId + ", start=" + start + "end=" + end +"]";
    }

}
