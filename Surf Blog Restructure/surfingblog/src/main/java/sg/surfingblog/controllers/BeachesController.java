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
import sg.surfingblog.newpackage.dao.InvalidIdException;
import sg.surfingblog.newpackage.dao.SurfingDao;
import sg.surfingblog.newpackage.dao.UserDao;
import sg.surfingblog.newpackage.models.Beach;
import sg.surfingblog.newpackage.models.BeachComment;
import sg.surfingblog.newpackage.models.Break;

/**
 *
 * @author Chad
 */

@Controller
public class BeachesController {
    
    
    @Autowired
    SurfingDao sDao;
    
    @Autowired
    UserDao uDao;
    
    @GetMapping("beaches")
    public String displayBeaches(Model model) throws InvalidIdException{
        
        List<Beach> allBeaches = sDao.getAllBeaches();
        model.addAttribute("allBeaches", allBeaches);
        
        return "beaches";
        
    }
    
    
    @GetMapping("beachDetails")
    public String displayBeachDetails(HttpServletRequest request, Model model) throws InvalidIdException{
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Beach selectedBeach = sDao.getBeachById(id);
        model.addAttribute("selectedBeach", selectedBeach);
 
        List<Break> allBreaksForBeach = sDao.getBreaksByBeach(id);
        model.addAttribute("allBreaksForBeach", allBreaksForBeach);
        
        List<BeachComment> beachComments = sDao.getCommentsByBeach(id);
        model.addAttribute("beachComments", beachComments);
        
        
        return "beachDetails";
        
    }
    
}
