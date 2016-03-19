package data.daos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Token;
import data.entities.User;

@Repository
public class TokenDaoImpl implements TokenExtended {
	
	@Autowired 
	private TokenDao tokenDao;

	@Override
	public void deleteOldTokens() {
		List<Token> tokens = tokenDao.findAll();
		for (Token token : tokens)	{
			if (token.isOldToken())	{
				tokenDao.delete(token);
			}			
		}
		tokenDao.flush();
	}
}
