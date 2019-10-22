/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.surfingblog.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sg.surfingblog.newpackage.dao.InvalidIdException;
import sg.surfingblog.newpackage.dao.SurfingDao;
import sg.surfingblog.newpackage.dao.SurfingDaoException;
import sg.surfingblog.newpackage.dao.UserDao;
import sg.surfingblog.newpackage.models.Beach;
import sg.surfingblog.newpackage.models.BeachComment;
import sg.surfingblog.newpackage.models.Break;
import sg.surfingblog.newpackage.models.SiteUser;

/**
 *
 * @author Chad
 */

@Controller
public class BeachController {
    
    @Autowired
    SurfingDao sDao;
    
    @Autowired
    UserDao uDao;
    
    @GetMapping("beach")
    public String displayBeach(HttpServletRequest request, Model model) throws InvalidIdException{
        
        List<Beach> allBeaches = sDao.getAllBeaches();
        model.addAttribute("allBeaches", allBeaches);
        
//        int selectedBeachId = Integer.parseInt(request.getParameter("id"));
        Beach selectedBeach = sDao.getBeachById(301);
        model.addAttribute("selectedBeach", selectedBeach);
 
        List<Break> allBreaksForBeach = sDao.getBreaksByBeach(301);
        model.addAttribute("allBreaksForBeach", allBreaksForBeach);
        
        List<BeachComment> beachComments = sDao.getAllBeachComments();
        model.addAttribute("beachComments", beachComments);
        
        
        return "beach";
        
    }
    
   @PostMapping("addBeachComment")
   public String addBeachComment(BeachComment newBeachComment, HttpServletRequest request) throws SurfingDaoException, InvalidIdException {
       
       BeachComment newBeachComment2 = new BeachComment();
       
       Beach testBeach = sDao.getBeachById(301);
       SiteUser testUser = uDao.getUserById(1);
       
       newBeachComment2.setBeach(testBeach);
       newBeachComment2.setUser(testUser);
       newBeachComment2.setCommentText("This is a test comment");
       
       sDao.addBeachComment(newBeachComment2);
       
       return "redirect:/beach";
       
   }
   
   
   
   
   
   
   
   
   
   }
    
    

    
    
    
    

