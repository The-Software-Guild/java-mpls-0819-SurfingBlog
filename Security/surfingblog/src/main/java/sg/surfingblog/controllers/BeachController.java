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
import org.springframework.web.bind.annotation.PathVariable;
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
    public String displayBeach(Model model) throws InvalidIdException{
        
        //On initial load, default data will be for the first beach
        int defaultBeachId = 301;
        
        List<Beach> allBeaches = sDao.getAllBeaches();
        model.addAttribute("allBeaches", allBeaches);
        
        Beach selectedBeach = sDao.getBeachById(defaultBeachId);
        model.addAttribute("selectedBeach", selectedBeach);
 
        List<Break> allBreaksForBeach = sDao.getBreaksByBeach(defaultBeachId);
        model.addAttribute("allBreaksForBeach", allBreaksForBeach);
        
        List<BeachComment> beachComments = sDao.getAllBeachComments();
        model.addAttribute("beachComments", beachComments);
        
        
        return "beach";
        
    }
    

    
   @PostMapping("addBeachComment")
   public String addBeachComment(BeachComment newBeachComment, HttpServletRequest request) throws SurfingDaoException, InvalidIdException {
       
       Beach testBeach = sDao.getBeachById(301);
       SiteUser testUser = uDao.getUserById(1);
       
       newBeachComment.setBeach(testBeach);
       newBeachComment.setUser(testUser);
       
       sDao.addBeachComment(newBeachComment);
       
       return "redirect:/beach";
       
   }
   
   
   
   
   
   
   
   
   
   }
    
    

    
    
    
    

