import com.day05.dao.IAccountDao;
import com.day05.dao.IUserDao;
import com.day05.domain.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    IUserDao iUserDao;
    SqlSession sqlSession;
    IAccountDao iAccountDao;
    SqlSessionFactory sqlSessionFactory;
    /**
     * 初始化Mybatis
     */
    @Before
    public void init()
    {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
         sqlSessionFactory = sfb.build(is);
        sqlSession = sqlSessionFactory.openSession();
        iUserDao = sqlSession.getMapper(IUserDao.class);
        iAccountDao= sqlSession.getMapper(IAccountDao.class);

    }

    /**
     * 检索所有用户
      */
    @Test
    public void selectAll()
    {
        List<User> users = iUserDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /***
     * 通过多条id匹配多个用户
     */
    @Test
    public void selectByIds()
    {
//        List<Integer> ids = new ArrayList<>();
        List<Integer> ids = Arrays.asList();
        List<User> users = iUserDao.findByIds(ids);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询Account
     */
    @Test
    public void selectAllAccount(){
        List<Account> list = iAccountDao.findAll();
        for (Account account : list) {
            System.out.println(account);
        }
    }
    
    @Test
    public void findAccountUser()
    {
        List<AccountUser> accountUserList = iAccountDao.findAccountUser();
//        for (AccountUser accountUser : accountUserList) {
//            System.out.println(accountUser);
////            User user = accountUser.getUser();
////            System.out.println(user);
//        }
    }

    @Test
    public void selectUserById()
    {
        User user1 = iUserDao.findUserById(41);
        System.out.println(user1);

//        SqlSession sqlSession2 = sqlSessionFactory.openSession();
//        IUserDao iUserDao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = iUserDao.findUserById(41);
        System.out.println(user2);
        System.out.println(user1==user2);
    }


    /**
     * 查询所有账户，同时还要获取到当前账户的所有信息
     */

    @Test
    public void findUserAccount()
    {
        List<UserAccount> userAccounts = iAccountDao.findUserAccount();
        for (UserAccount userAccount : userAccounts) {
            System.out.println(userAccount);
        }
    }






    /**
     * 提交事务，同时关闭连接
     */
    @After
    public void destory()
    {
        sqlSession.commit();
        sqlSession.close();
    }



}
