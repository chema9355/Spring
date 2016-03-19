package data.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Token;
import data.entities.User;

@Repository
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
