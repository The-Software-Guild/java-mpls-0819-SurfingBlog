/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.surfingblog.newpackage.dao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import sg.surfingblog.TestApplicationConfiguration;
import sg.surfingblog.newpackage.models.Beach;
import sg.surfingblog.newpackage.models.BeachComment;
import sg.surfingblog.newpackage.models.Break;
import sg.surfingblog.newpackage.models.BreakComment;
import sg.surfingblog.newpackage.models.News;
import sg.surfingblog.newpackage.models.Role;
import sg.surfingblog.newpackage.models.SiteUser;

/**
 *
 * @author blee0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@Repository
@ActiveProfiles(profiles = "test")
public class SurfingDbDaoTest {

    @Autowired
    SurfingDao toTest;

    @Autowired
    UserDao userDao;

    public SurfingDbDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //all tables deleted except user and roles 
        toTest.deleteAllTables();
        //userDao.deleteAllTables(); removed, adding new user was causing issues 
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getBreaksByBeach method, of class SurfingDbDao.
     */
    @Test
    public void testGetBreaksByBeachGoldenPath() {

        try {
            Beach beach1 = new Beach();
            beach1.setName("Beach A");
            beach1.setZipCode(96701);
            Beach addedBeach = toTest.addBeach(beach1);

            Break break1 = new Break();
            break1.setName("Break A");
            break1.setBeach(addedBeach);
            break1.setLatitude(new BigDecimal("20.716179"));
            break1.setLongitude(new BigDecimal("-158.214676"));
            Break addedBreak1 = toTest.addBreak(break1);

            Break break2 = new Break();
            break2.setName("Break B");
            break2.setBeach(addedBeach);
            break2.setLatitude(new BigDecimal("20.716185"));
            break2.setLongitude(new BigDecimal("-158.214684"));
            Break addedBreak2 = toTest.addBreak(break2);

            Break break3 = new Break();
            break3.setName("Break C");
            break3.setBeach(addedBeach);
            break3.setLatitude(new BigDecimal("20.716634"));
            break3.setLongitude(new BigDecimal("-158.214965"));
            Break addedBreak3 = toTest.addBreak(break3);

            List<Break> allBreaks = toTest.getBreaksByBeach(addedBeach.getId());

            assertEquals(3, allBreaks.size());

            Break firstBreak = allBreaks.get(0);
            assertEquals(addedBreak1.getId(), firstBreak.getId());
            assertEquals("Break A", firstBreak.getName());
            assertEquals(addedBeach.getId(), firstBreak.getBeach().getId());
            assertEquals(new BigDecimal("20.716179"), firstBreak.getLatitude());
            assertEquals(new BigDecimal("-158.214676"), firstBreak.getLongitude());

            Break secondBreak = allBreaks.get(1);
            assertEquals(addedBreak2.getId(), secondBreak.getId());
            assertEquals("Break B", secondBreak.getName());
            assertEquals(addedBeach.getId(), secondBreak.getBeach().getId());
            assertEquals(new BigDecimal("20.716185"), secondBreak.getLatitude());
            assertEquals(new BigDecimal("-158.214684"), secondBreak.getLongitude());

            Break thirdBreak = allBreaks.get(2);
            assertEquals(addedBreak3.getId(), thirdBreak.getId());
            assertEquals("Break C", thirdBreak.getName());
            assertEquals(addedBeach.getId(), thirdBreak.getBeach().getId());
            assertEquals(new BigDecimal("20.716634"), thirdBreak.getLatitude());
            assertEquals(new BigDecimal("-158.214965"), thirdBreak.getLongitude());

        } catch (SurfingDaoException | InvalidIdException ex) {
            fail();
        }

    }

    /**
     * Test of getCommentsByBeach method, of class SurfingDbDao.
     */
    @Test
    public void testGetCommentsByBeachGoldenPath() {

        try {
            Beach beach1 = new Beach();
            beach1.setName("Beach A");
            beach1.setZipCode(96701);
            Beach addedBeach = toTest.addBeach(beach1);

//            Set<Role> roles = new HashSet<>();
//            Role newRole = new Role();
//            newRole.setId(2);
//            roles.add(newRole);
//
//            SiteUser user1 = new SiteUser();
//            user1.setUsername("user");
//            user1.setPassword("$2a$10$Pwyf0jn0glXZ36Nvo0GBtuUdl0Y5OoXV7izp2/Mi6YGvx3YY4Zcmi");
//            user1.setEnabled(true);
//            user1.setRoles(roles);
//            SiteUser newUser = userDao.createUser(user1);
            BeachComment bc1 = new BeachComment();
            bc1.setUser(userDao.getUserById(2));
            bc1.setBeach(addedBeach);
            bc1.setCommentText("ea commodo consequat. Duis aute irure dolor in");
            BeachComment firstBC = toTest.addBeachComment(bc1);

            BeachComment bc2 = new BeachComment();
            bc2.setUser(userDao.getUserById(2));
            bc2.setBeach(addedBeach);
            bc2.setCommentText("vel illum qui");
            BeachComment secondBC = toTest.addBeachComment(bc2);

            BeachComment bc3 = new BeachComment();
            bc3.setUser(userDao.getUserById(2));
            bc3.setBeach(addedBeach);
            bc3.setCommentText("aute irure dolor in");
            BeachComment thirdBC = toTest.addBeachComment(bc3);

            List<BeachComment> allComments = toTest.getCommentsByBeach(addedBeach.getId());

            assertEquals(3, allComments.size());

            BeachComment firstComment = allComments.get(0);
            assertEquals(firstBC.getId(), firstComment.getId());
            assertEquals(2, firstComment.getUser().getId());
            assertEquals(addedBeach.getId(), firstComment.getBeach().getId());
            assertEquals("ea commodo consequat. Duis aute irure dolor in", firstComment.getCommentText());

            BeachComment secondComment = allComments.get(1);
            assertEquals(secondBC.getId(), secondComment.getId());
            assertEquals(2, secondComment.getUser().getId());
            assertEquals(addedBeach.getId(), secondComment.getBeach().getId());
            assertEquals("vel illum qui", secondComment.getCommentText());

            BeachComment thirdComment = allComments.get(2);
            assertEquals(thirdBC.getId(), thirdComment.getId());
            assertEquals(2, thirdComment.getUser().getId());
            assertEquals(addedBeach.getId(), thirdComment.getBeach().getId());
            assertEquals("aute irure dolor in", thirdComment.getCommentText());

        } catch (SurfingDaoException | InvalidIdException ex) {
            fail();
        }

    }

    /**
     * Test of getCommentsByBreak method, of class SurfingDbDao.
     */
    @Test
    public void testGetCommentsByBreakGoldenPath() {

        try {
            Beach beach1 = new Beach();
            beach1.setName("Beach A");
            beach1.setZipCode(96701);
            Beach addedBeach = toTest.addBeach(beach1);

            Break break1 = new Break();
            break1.setName("Break A");
            break1.setBeach(addedBeach);
            break1.setLatitude(new BigDecimal("20.716179"));
            break1.setLongitude(new BigDecimal("-158.214676"));
            Break addedBreak1 = toTest.addBreak(break1);

            BreakComment bc1 = new BreakComment();
            bc1.setUser(userDao.getUserById(2));
            bc1.setBeachBreak(addedBreak1);
            bc1.setCommentText("perspiciatis unde omnis iste natus error sit volupt");
            BreakComment addedBC1 = toTest.addBreakComment(bc1);

            BreakComment bc2 = new BreakComment();
            bc2.setUser(userDao.getUserById(2));
            bc2.setBeachBreak(addedBreak1);
            bc2.setCommentText("dolor sit amet, consectetu");
            BreakComment addedBC2 = toTest.addBreakComment(bc2);

            BreakComment bc3 = new BreakComment();
            bc3.setUser(userDao.getUserById(2));
            bc3.setBeachBreak(addedBreak1);
            bc3.setCommentText("commodi consequatur? Quis autem vel eum iure");
            BreakComment addedBC3 = toTest.addBreakComment(bc3);

            List<BreakComment> allComments = toTest.getCommentsByBreak(addedBreak1.getId());

            assertEquals(3, allComments.size());

            BreakComment firstComment = allComments.get(0);
            assertEquals(addedBC1.getId(), firstComment.getId());
            assertEquals(2, firstComment.getUser().getId());
            assertEquals(addedBreak1.getId(), firstComment.getBeachBreak().getId());
            assertEquals("perspiciatis unde omnis iste natus error sit volupt", firstComment.getCommentText());

            BreakComment secondComment = allComments.get(1);
            assertEquals(addedBC2.getId(), secondComment.getId());
            assertEquals(2, secondComment.getUser().getId());
            assertEquals(addedBreak1.getId(), secondComment.getBeachBreak().getId());
            assertEquals("dolor sit amet, consectetu", secondComment.getCommentText());

            BreakComment thirdComment = allComments.get(2);
            assertEquals(addedBC3.getId(), thirdComment.getId());
            assertEquals(2, thirdComment.getUser().getId());
            assertEquals(addedBreak1.getId(), thirdComment.getBeachBreak().getId());
            assertEquals("commodi consequatur? Quis autem vel eum iure", thirdComment.getCommentText());

        } catch (SurfingDaoException | InvalidIdException ex) {
            fail();
        }

    }

    /**
     * Test of getAllActiveNews method, of class SurfingDbDao.
     */
    @Test
    public void testGetAllActiveNewsGoldenPath() {

        try {
            News headline1 = new News();
            headline1.setNewsURL("https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513");
            headline1.setNewsAbbrText("Disappointing surf forecast for Waimea Bay");
            headline1.setPicURL("https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png");
            headline1.setIsActive(true);
            News newsAdded1 = toTest.addNews(headline1);

            News headline2 = new News();
            headline2.setNewsURL("https://www.surfertoday.com/surfing/aritz-aranburu-inducted-into-the-spanish-surfing-walk-of-fame");
            headline2.setNewsAbbrText("Congrats to Aritz Aranburu -- the newest member of the Spanish Surfing Walk of Fame");
            headline2.setPicURL("https://www.surfertoday.com/images/stories/aritzaranburu8.jpg");
            headline2.setIsActive(true);
            News newsAdded2 = toTest.addNews(headline2);

            News headline3 = new News();
            headline3.setNewsURL("https://www.surfertoday.com/surfing/jeremy-flores-wins-2019-quiksilver-pro-france");
            headline3.setNewsAbbrText("Flores and Moore win the 2019 Quiksilver and Roxy Pro France");
            headline3.setPicURL("https://www.surfertoday.com/images/stories/jeremyflores18.jpg");
            headline3.setIsActive(true);
            News newsAdded3 = toTest.addNews(headline3);

            News headline4 = new News();
            headline4.setNewsURL("https://www.surfertoday.com/surfing/kathmandu-buys-rip-curl");
            headline4.setNewsAbbrText("Ripcurl purchased by Kathmandu");
            headline4.setPicURL("https://www.surfertoday.com/images/stories/mickfanning67.jpg");
            headline4.setIsActive(false);
            News newsAdded4 = toTest.addNews(headline4);

            List<News> allActive = toTest.getAllActiveNews();

            assertEquals(3, allActive.size());

            News firstNews = allActive.get(0);
            assertEquals(newsAdded1.getId(), firstNews.getId());
            assertEquals("https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513", firstNews.getNewsURL());
            assertEquals("Disappointing surf forecast for Waimea Bay", firstNews.getNewsAbbrText());
            assertEquals("https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png", firstNews.getPicURL());
            assertEquals(true, firstNews.getIsActive());

            News secondNews = allActive.get(1);
            assertEquals(newsAdded2.getId(), secondNews.getId());
            assertEquals("https://www.surfertoday.com/surfing/aritz-aranburu-inducted-into-the-spanish-surfing-walk-of-fame", secondNews.getNewsURL());
            assertEquals("Congrats to Aritz Aranburu -- the newest member of the Spanish Surfing Walk of Fame", secondNews.getNewsAbbrText());
            assertEquals("https://www.surfertoday.com/images/stories/aritzaranburu8.jpg", secondNews.getPicURL());
            assertEquals(true, secondNews.getIsActive());

            News thirdNews = allActive.get(2);
            assertEquals(newsAdded3.getId(), thirdNews.getId());
            assertEquals("https://www.surfertoday.com/surfing/jeremy-flores-wins-2019-quiksilver-pro-france", thirdNews.getNewsURL());
            assertEquals("Flores and Moore win the 2019 Quiksilver and Roxy Pro France", thirdNews.getNewsAbbrText());
            assertEquals("https://www.surfertoday.com/images/stories/jeremyflores18.jpg", thirdNews.getPicURL());
            assertEquals(true, thirdNews.getIsActive());

        } catch (SurfingDaoException ex) {
            fail();
        }
    }

    @Test
    public void testGetAllNewsGoldenPath() {

        try {
            News headline1 = new News();
            headline1.setNewsURL("https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513");
            headline1.setNewsAbbrText("Disappointing surf forecast for Waimea Bay");
            headline1.setPicURL("https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png");
            headline1.setIsActive(true);
            News newsAdded1 = toTest.addNews(headline1);

            News headline2 = new News();
            headline2.setNewsURL("https://www.surfertoday.com/surfing/aritz-aranburu-inducted-into-the-spanish-surfing-walk-of-fame");
            headline2.setNewsAbbrText("Congrats to Aritz Aranburu -- the newest member of the Spanish Surfing Walk of Fame");
            headline2.setPicURL("https://www.surfertoday.com/images/stories/aritzaranburu8.jpg");
            headline2.setIsActive(true);
            News newsAdded2 = toTest.addNews(headline2);

            News headline3 = new News();
            headline3.setNewsURL("https://www.surfertoday.com/surfing/jeremy-flores-wins-2019-quiksilver-pro-france");
            headline3.setNewsAbbrText("Flores and Moore win the 2019 Quiksilver and Roxy Pro France");
            headline3.setPicURL("https://www.surfertoday.com/images/stories/jeremyflores18.jpg");
            headline3.setIsActive(true);
            News newsAdded3 = toTest.addNews(headline3);

            News headline4 = new News();
            headline4.setNewsURL("https://www.surfertoday.com/surfing/kathmandu-buys-rip-curl");
            headline4.setNewsAbbrText("Ripcurl purchased by Kathmandu");
            headline4.setPicURL("https://www.surfertoday.com/images/stories/mickfanning67.jpg");
            headline4.setIsActive(false);
            News newsAdded4 = toTest.addNews(headline4);

            List<News> allNews = toTest.getAllNews();

            assertEquals(4, allNews.size());

            News firstNews = allNews.get(0);
            assertEquals(newsAdded1.getId(), firstNews.getId());
            assertEquals("https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513", firstNews.getNewsURL());
            assertEquals("Disappointing surf forecast for Waimea Bay", firstNews.getNewsAbbrText());
            assertEquals("https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png", firstNews.getPicURL());
            assertEquals(true, firstNews.getIsActive());

            News secondNews = allNews.get(1);
            assertEquals(newsAdded2.getId(), secondNews.getId());
            assertEquals("https://www.surfertoday.com/surfing/aritz-aranburu-inducted-into-the-spanish-surfing-walk-of-fame", secondNews.getNewsURL());
            assertEquals("Congrats to Aritz Aranburu -- the newest member of the Spanish Surfing Walk of Fame", secondNews.getNewsAbbrText());
            assertEquals("https://www.surfertoday.com/images/stories/aritzaranburu8.jpg", secondNews.getPicURL());
            assertEquals(true, secondNews.getIsActive());

            News thirdNews = allNews.get(2);
            assertEquals(newsAdded3.getId(), thirdNews.getId());
            assertEquals("https://www.surfertoday.com/surfing/jeremy-flores-wins-2019-quiksilver-pro-france", thirdNews.getNewsURL());
            assertEquals("Flores and Moore win the 2019 Quiksilver and Roxy Pro France", thirdNews.getNewsAbbrText());
            assertEquals("https://www.surfertoday.com/images/stories/jeremyflores18.jpg", thirdNews.getPicURL());
            assertEquals(true, thirdNews.getIsActive());

            News fourthNews = allNews.get(3);
            assertEquals(newsAdded4.getId(), fourthNews.getId());
            assertEquals("https://www.surfertoday.com/surfing/kathmandu-buys-rip-curl", fourthNews.getNewsURL());
            assertEquals("Ripcurl purchased by Kathmandu", fourthNews.getNewsAbbrText());
            assertEquals("https://www.surfertoday.com/images/stories/mickfanning67.jpg", fourthNews.getPicURL());
            assertEquals(false, fourthNews.getIsActive());

        } catch (SurfingDaoException ex) {
            fail();
        }

    }

    /**
     * Test of getNewsById method, of class SurfingDbDao.
     */
    @Test
    public void testGetNewsByIdGoldenPath() {

        try {
            News headline1 = new News();
            headline1.setNewsURL("https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513");
            headline1.setNewsAbbrText("Disappointing surf forecast for Waimea Bay");
            headline1.setPicURL("https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png");
            headline1.setIsActive(true);
            News newsAdded1 = toTest.addNews(headline1);

            News headline2 = new News();
            headline2.setNewsURL("https://www.surfertoday.com/surfing/aritz-aranburu-inducted-into-the-spanish-surfing-walk-of-fame");
            headline2.setNewsAbbrText("Congrats to Aritz Aranburu -- the newest member of the Spanish Surfing Walk of Fame");
            headline2.setPicURL("https://www.surfertoday.com/images/stories/aritzaranburu8.jpg");
            headline2.setIsActive(true);
            News newsAdded2 = toTest.addNews(headline2);

            News toCheck1 = toTest.getNewsById(newsAdded1.getId());
            assertEquals(newsAdded1.getId(), toCheck1.getId());
            assertEquals("https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513", toCheck1.getNewsURL());
            assertEquals("Disappointing surf forecast for Waimea Bay", toCheck1.getNewsAbbrText());
            assertEquals("https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png", toCheck1.getPicURL());
            assertEquals(true, toCheck1.getIsActive());

            News toCheck2 = toTest.getNewsById(newsAdded2.getId());
            assertEquals(newsAdded2.getId(), toCheck2.getId());
            assertEquals("https://www.surfertoday.com/surfing/aritz-aranburu-inducted-into-the-spanish-surfing-walk-of-fame", toCheck2.getNewsURL());
            assertEquals("Congrats to Aritz Aranburu -- the newest member of the Spanish Surfing Walk of Fame", toCheck2.getNewsAbbrText());
            assertEquals("https://www.surfertoday.com/images/stories/aritzaranburu8.jpg", toCheck2.getPicURL());
            assertEquals(true, toCheck2.getIsActive());

        } catch (SurfingDaoException | InvalidIdException ex) {
            fail();
        }
    }

    /**
     * Test of addNews method, of class SurfingDbDao.
     */
    @Test
    public void testAddNewsGoldenPath() {
        //tested above
    }

    /**
     * Test of updateNews method, of class SurfingDbDao.
     */
    @Test
    public void testUpdateNewsGoldenPath() {

        try {
            News headline1 = new News();
            headline1.setNewsURL("https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513");
            headline1.setNewsAbbrText("Disappointing surf forecast for Waimea Bay");
            headline1.setPicURL("https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png");
            headline1.setIsActive(true);
            News newsAdded1 = toTest.addNews(headline1);

            News updatedNews = new News();
            updatedNews.setId(newsAdded1.getId());
            updatedNews.setNewsURL("https://www.surftoday.com/test");
            updatedNews.setNewsAbbrText("this is a test");
            updatedNews.setPicURL("https://www.surftoday.com/images/stories/test.png");
            updatedNews.setIsActive(false);
            toTest.updateNews(updatedNews);

            News toCheck = toTest.getNewsById(newsAdded1.getId());

            assertEquals(newsAdded1.getId(), toCheck.getId());
            assertEquals("https://www.surftoday.com/test", toCheck.getNewsURL());
            assertEquals("this is a test", toCheck.getNewsAbbrText());
            assertEquals("https://www.surftoday.com/images/stories/test.png", toCheck.getPicURL());
            assertEquals(false, toCheck.getIsActive());

        } catch (SurfingDaoException | InvalidIdException ex) {
            fail();
        }
    }

    /**
     * Test of deleteNews method, of class SurfingDbDao.
     */
    @Test
    public void testDeleteNewsGoldenPath() {

        try {
            News headline1 = new News();
            headline1.setNewsURL("https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513");
            headline1.setNewsAbbrText("Disappointing surf forecast for Waimea Bay");
            headline1.setPicURL("https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png");
            headline1.setIsActive(true);
            News newsAdded1 = toTest.addNews(headline1);

            News headline2 = new News();
            headline2.setNewsURL("https://www.surfertoday.com/surfing/aritz-aranburu-inducted-into-the-spanish-surfing-walk-of-fame");
            headline2.setNewsAbbrText("Congrats to Aritz Aranburu -- the newest member of the Spanish Surfing Walk of Fame");
            headline2.setPicURL("https://www.surfertoday.com/images/stories/aritzaranburu8.jpg");
            headline2.setIsActive(true);
            News newsAdded2 = toTest.addNews(headline2);

            News headline3 = new News();
            headline3.setNewsURL("https://www.surfertoday.com/surfing/jeremy-flores-wins-2019-quiksilver-pro-france");
            headline3.setNewsAbbrText("Flores and Moore win the 2019 Quiksilver and Roxy Pro France");
            headline3.setPicURL("https://www.surfertoday.com/images/stories/jeremyflores18.jpg");
            headline3.setIsActive(true);
            News newsAdded3 = toTest.addNews(headline3);

            News headline4 = new News();
            headline4.setNewsURL("https://www.surfertoday.com/surfing/kathmandu-buys-rip-curl");
            headline4.setNewsAbbrText("Ripcurl purchased by Kathmandu");
            headline4.setPicURL("https://www.surfertoday.com/images/stories/mickfanning67.jpg");
            headline4.setIsActive(false);
            News newsAdded4 = toTest.addNews(headline4);

            toTest.deleteNews(newsAdded2.getId());

            List<News> allNews = toTest.getAllNews();

            assertEquals(3, allNews.size());

            News firstNews = allNews.get(0);
            assertEquals(newsAdded1.getId(), firstNews.getId());
            assertEquals("https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513", firstNews.getNewsURL());
            assertEquals("Disappointing surf forecast for Waimea Bay", firstNews.getNewsAbbrText());
            assertEquals("https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png", firstNews.getPicURL());
            assertEquals(true, firstNews.getIsActive());

            News thirdNews = allNews.get(1);
            assertEquals(newsAdded3.getId(), thirdNews.getId());
            assertEquals("https://www.surfertoday.com/surfing/jeremy-flores-wins-2019-quiksilver-pro-france", thirdNews.getNewsURL());
            assertEquals("Flores and Moore win the 2019 Quiksilver and Roxy Pro France", thirdNews.getNewsAbbrText());
            assertEquals("https://www.surfertoday.com/images/stories/jeremyflores18.jpg", thirdNews.getPicURL());
            assertEquals(true, thirdNews.getIsActive());

            News fourthNews = allNews.get(2);
            assertEquals(newsAdded4.getId(), fourthNews.getId());
            assertEquals("https://www.surfertoday.com/surfing/kathmandu-buys-rip-curl", fourthNews.getNewsURL());
            assertEquals("Ripcurl purchased by Kathmandu", fourthNews.getNewsAbbrText());
            assertEquals("https://www.surfertoday.com/images/stories/mickfanning67.jpg", fourthNews.getPicURL());
            assertEquals(false, fourthNews.getIsActive());

        } catch (SurfingDaoException | InvalidIdException ex) {
            fail();
        }
    }

    /**
     * Test of getAllBeaches method, of class SurfingDbDao.
     */
    @Test
    public void testGetAllBeachesGoldenPath() {

        try {
            Beach beach1 = new Beach();
            beach1.setName("Beach A");
            beach1.setZipCode(96701);
            Beach addedBeach = toTest.addBeach(beach1);

            Beach beach2 = new Beach();
            beach2.setName("Beach B");
            beach2.setZipCode(94388);
            Beach addedBeach2 = toTest.addBeach(beach2);

            Beach beach3 = new Beach();
            beach3.setName("Beach C");
            beach3.setZipCode(92755);
            Beach addedBeach3 = toTest.addBeach(beach3);

            List<Beach> allBeaches = toTest.getAllBeaches();

            assertEquals(3, allBeaches.size());

            Beach firstBeach = allBeaches.get(0);
            assertEquals(addedBeach.getId(), firstBeach.getId());
            assertEquals("Beach A", firstBeach.getName());
            assertEquals(96701, firstBeach.getZipCode());

            Beach secondBeach = allBeaches.get(1);
            assertEquals(addedBeach2.getId(), secondBeach.getId());
            assertEquals("Beach B", secondBeach.getName());
            assertEquals(94388, secondBeach.getZipCode());

            Beach thirdBeach = allBeaches.get(2);
            assertEquals(addedBeach3.getId(), thirdBeach.getId());
            assertEquals("Beach C", thirdBeach.getName());
            assertEquals(92755, thirdBeach.getZipCode());

        } catch (SurfingDaoException ex) {
            fail();
        }

    }

    /**
     * Test of getBeachById method, of class SurfingDbDao.
     */
    @Test
    public void testGetBeachByIdGoldenPath() {
    }

    /**
     * Test of addBeach method, of class SurfingDbDao.
     */
    @Test
    public void testAddBeachGoldenPath() {
    }

    /**
     * Test of updateBeach method, of class SurfingDbDao.
     */
    @Test
    public void testUpdateBeachGoldenPath() {
    }

    /**
     * Test of deleteBeach method, of class SurfingDbDao.
     */
    @Test
    public void testDeleteBeachGoldenPath() {
    }

    /**
     * Test of getAllBreaks method, of class SurfingDbDao.
     */
    @Test
    public void testGetAllBreaksGoldenPath() {
    }

    /**
     * Test of getBreakById method, of class SurfingDbDao.
     */
    @Test
    public void testGetBreakByIdGoldenPath() {
    }

    /**
     * Test of addBreak method, of class SurfingDbDao.
     */
    @Test
    public void testAddBreakGoldenPath() {
    }

    /**
     * Test of updateBreak method, of class SurfingDbDao.
     */
    @Test
    public void testUpdateBreakGoldenPath() {
    }

    /**
     * Test of deleteBreak method, of class SurfingDbDao.
     */
    @Test
    public void testDeleteBreakGoldenPath() {
    }

    /**
     * Test of getAllBeachComments method, of class SurfingDbDao.
     */
    @Test
    public void testGetAllBeachCommentsGoldenPath() {
    }

    /**
     * Test of getBeachCommentById method, of class SurfingDbDao.
     */
    @Test
    public void testGetBeachCommentByIdGoldenPath() {
    }

    /**
     * Test of addBeachComment method, of class SurfingDbDao.
     */
    @Test
    public void testAddBeachCommentGoldenPath() {
    }

    /**
     * Test of updateBeachComment method, of class SurfingDbDao.
     */
    @Test
    public void testUpdateBeachCommentGoldenPath() {
    }

    /**
     * Test of deleteBeachComment method, of class SurfingDbDao.
     */
    @Test
    public void testDeleteBeachCommentGoldenPath() {
    }

    /**
     * Test of getAllBreakComments method, of class SurfingDbDao.
     */
    @Test
    public void testGetAllBreakCommentsGoldenPath() {
    }

    /**
     * Test of getBreakCommentById method, of class SurfingDbDao.
     */
    @Test
    public void testGetBreakCommentByIdGoldenPath() {
    }

    /**
     * Test of addBreakComment method, of class SurfingDbDao.
     */
    @Test
    public void testAddBreakCommentGoldenPath() {
    }

    /**
     * Test of updateBreakComment method, of class SurfingDbDao.
     */
    @Test
    public void testUpdateBreakCommentGoldenPath() {
    }

    /**
     * Test of deleteBreakComment method, of class SurfingDbDao.
     */
    @Test
    public void testDeleteBreakCommentGoldenPath() {
    }

}
