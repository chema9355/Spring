package data.daos;

import java.util.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import data.entities.Court;
import data.entities.Training;
import data.entities.User;


public interface TrainingDao extends JpaRepository<Training, Integer> {
    
    Training findByDate(Calendar date);
    
    Training findByTrainer(User trainer);
    
    Training findByCourt(Court court);
    
    Training findByTrainerAndCourt(User trainer, Court court);
}
