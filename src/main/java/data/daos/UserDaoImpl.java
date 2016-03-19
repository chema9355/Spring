package data.daos;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Token;
import data.entities.User;

public class UserDaoImpl implements UserExtended{

	@Autowired 
	private TokenDao tokenDao;
	
	@Override
	public User findByValidToken(String value) {
		Token token = tokenDao.findByValue(value);
		if (token == null ||token.isOldToken())
		{
			return null;
		}
		return token.getUser();
	}
}
