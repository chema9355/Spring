package data.entities;

import java.text.SimpleDateFormat;
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
	    private Calendar date;

	    @ManyToOne
	    @JoinColumn
	    private Court court;
	    
	    @ManyToOne
	    @JoinColumn
	    private User trainer;
	    
	    @ManyToMany(fetch = FetchType.EAGER)
	    private List<User> players;

	    public Training(Calendar date, Court court) {
	        this.court = court;
	        this.date = date;
	    }
	    
	    public Training(Calendar date, Court court, User trainer) {
	        this.court = court;
	        this.date = date;
	        this.trainer = trainer;
	    }
	    
	    public Training(Calendar date, Court court, User trainer, List<User> players) {
	        this.court = court;
	        this.date = date;
	        this.trainer = trainer;
	        this.players = players;
	    }
	    
	    public Training() {
	    }

	    public int getId() {
	        return id;
	    }

	    public Court getCourt() {
	        return court;
	    }

	    public Calendar getDate() {
	        return date;
	    }

	    public User getTrainer() {
	        return trainer;
	    }
	    
	    public List <User> getPlayers() {
	        return players;
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
	        String time = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(date.getTime());
	        return "Training [id=" + id + ", courtId=" + court.getId() + ", date=" + time + ", Trainer=" + trainer.getUsername() + "Players=" + players.toString()+ "]";
	    }
	
	
	

}
