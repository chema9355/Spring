package data.daos;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@RunWith(Suite.class)
@SuiteClasses({
    TokenDaoITest.class,
    UserDaoITest.class,
    AuthorizationDaoITest.class,
    ReserveDaoITest.class,
    TrainingDaolTest.class
})
public class AllDaosITests {

}
