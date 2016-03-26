package data.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Training {
	
	 	@Id
	    @GeneratedValue
	    private int id;

	    @Column
	    private Calendar startDate;
	    
	    @Column
	    private Calendar endDate;

	    @ManyToOne
	    @JoinColumn
	    private Court court;
	    
	    @ManyToOne
	    @JoinColumn
	    private User trainer;
	    
	    @ManyToMany(fetch = FetchType.EAGER)
	    private List<User> players;
	    
	    @ManyToMany(fetch = FetchType.EAGER)
	    private List<Reserve> reserves;

	    public Training(Calendar startDate, Calendar endDate, Court court) {
	        this.court = court;
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.reserves = new ArrayList<Reserve>();
	    }
	    
	    public Training(Calendar startDate, Calendar endDate, Court court, User trainer) {
	        this.court = court;
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.trainer = trainer;
	        this.reserves = new ArrayList<Reserve>();
	    }
	    
	    public Training(Calendar startDate, Calendar endDate, Court court, User trainer, List<User> players) {
	        this.court = court;
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.trainer = trainer;
	        this.players = players;
	        this.reserves = new ArrayList<Reserve>();
	    }
	    
	    public Training() {
	    }

	    public int getId() {
	        return id;
	    }

	    public Court getCourt() {
	        return court;
	    }

	    public Calendar getStartDate() {
	        return startDate;
	    }

	    public User getTrainer() {
	        return trainer;
	    }
	    
	    public List <User> getPlayers() {
	        return players;
	    }
	    
	    public List <Reserve> getReserves() {
	        return reserves;
	    }
	    
	    public void setReserves(List <Reserve> reserves) {
	        this.reserves = reserves;
	    }

	    public void setTrainer(User trainer) {
	        this.trainer = trainer;
	    }
	    
	    public void addPlayer(User player)  {
	    	players.add(player);
	    }
	    
	    public void deletePlayer(User player)  {
	    	players.remove(player);
	    }
	    
	    public void addReserve(Reserve reserve)  {
	    	reserves.add(reserve);
	    }

	    public Calendar getStarDate() {
	        return startDate;
	    }
	    
	    public Calendar getEndDate() {
	        return endDate;
	    }
	    
	    @Override
	    public int hashCode() {
	        return id;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        return id == ((Training) obj).id;
	    }

	    @Override
	    public String toString() {
	        String sDate = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(startDate.getTime());
	        String eDate = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(endDate.getTime());

	        return "Training [id=" + id + ", courtId=" + court.getId() + ", startDate=" + sDate + ", endDate=" + eDate + " Trainer=" + trainer.getUsername() + "Players=" + players.toString()+ "Reserves=" + reserves.toString()+ "]";
	    }

	
	

}
