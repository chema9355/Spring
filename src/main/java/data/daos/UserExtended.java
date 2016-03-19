package data.daos;

import data.entities.User;

public interface UserExtended {

	User findByValidToken(String value);
}
