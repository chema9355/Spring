package data.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDaoImpl implements TokenExtended {
	
	@Autowired 
	private TokenDao tokenDao;

	@Override
	public void eliminaAntiguos() {
		
		
		
	}

}
