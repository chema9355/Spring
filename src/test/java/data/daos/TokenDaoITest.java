package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class TokenDaoITest {

    @Autowired
    private TokenDao tokenDao;
    
    @Autowired
    private DaosService daosService;

    @Test
    public void testFindByUser() {
        Token token = (Token) daosService.getMap().get("tu1");
        User user = (User) daosService.getMap().get("u9");
        assertEquals(token, tokenDao.findByUser(token.getUser()));
        assertNull(tokenDao.findByUser(user));
    }
    
    @Test
    public void testFindByValue() {
        Token token = (Token) daosService.getMap().get("tu1");
        Token token1 = tokenDao.findByValue(token.getValue());
        assertEquals(token, token1);
    }
    
    @Test
    public void testAnOldToken() {
        Token validToken = (Token) daosService.getMap().get("tu1");
        assertEquals(validToken.isOldToken(), false);
        Token oldToken = (Token) daosService.getMap().get("tu7");
        assertEquals(oldToken.isOldToken(), true);
    }
    
    @Test
    public void testDeleteOldTokens() {
        List<Token> tokens = new ArrayList<Token>();
        tokens = tokenDao.findAll();
        assertEquals(8, tokens.size());
        tokenDao.deleteOldTokens();
        tokens = tokenDao.findAll();
        assertEquals(4, tokens.size());
    }
    
}
