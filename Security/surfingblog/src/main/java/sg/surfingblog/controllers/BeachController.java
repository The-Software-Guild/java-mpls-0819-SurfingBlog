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
import sg.surfingblog.newpackage.models.Beach;
import sg.surfingblog.newpackage.models.Break;

/**
 *
 * @author Chad
 */

@Controller
public class BeachController {
    
    @Autowired
    SurfingDao sDao;
    
//    @GetMapping("beach")
//    public String displayBeach(HttpServletRequest request, Model model) throws InvalidIdException{
//        
//        List<Beach> allBeaches = sDao.getAllBeaches();
//        int id = Integer.parseInt(request.getParameter("id"));
//        Beach selectedBeach = sDao.getBeachById(id);
//        
//        model.addAttribute("allBeaches", allBeaches);
//        model.addAttribute("selectedBeach", selectedBeach);
//        
//        return "beach";
//        
//    }
    
    @GetMapping("beach")
    public String displayBeach(HttpServletRequest request, Model model) throws InvalidIdException{
        
        List<Beach> allBeaches = sDao.getAllBeaches();
        model.addAttribute("allBeaches", allBeaches);
        
        int selectedBeachId = Integer.parseInt(request.getParameter("id"));
        Beach selectedBeach = sDao.getBeachById(selectedBeachId);
        model.addAttribute("selectedBeach", selectedBeach);
 
        List<Break> allBreaksForBeach = sDao.getBreaksByBeach(selectedBeachId);
        model.addAttribute("allBreaksForBeach", allBreaksForBeach);
        
        return "beach";
        
    }
    

    
    
    
    
}
