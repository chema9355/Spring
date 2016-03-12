package data.daos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Token;

@Repository
public class TokenDaoImpl implements TokenExtended {
	
	@Autowired 
	private TokenDao tokenDao;

	@Override
	public void deleteOldTokens() {
		List<Token> tokens = tokenDao.findAll();
		Date now = Calendar.getInstance().getTime();
		for (Token t: tokens)
		{
			if (now.getTime() - t.getCreation().getTime()/(60*60*1000) > 1)
			{
				tokenDao.delete(t);
			}
		}
		tokenDao.flush();
	}
}
