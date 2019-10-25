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
import sg.surfingblog.newpackage.dao.InvalidIdException;
import sg.surfingblog.newpackage.dao.SurfingDao;
import sg.surfingblog.newpackage.dao.UserDao;
import sg.surfingblog.newpackage.models.Break;
import sg.surfingblog.newpackage.models.BreakComment;

/**
 *
 * @author blee0
 */
@Controller
public class BreakController {
    
    @Autowired
    SurfingDao sDao;
    
    @Autowired
    UserDao uDao;
    
    @GetMapping("break")
    public String displayBreak(Model model) throws InvalidIdException {
        
        List<Break> allBreaks = sDao.getAllBreaks();
        model.addAttribute("allBreaks", allBreaks);
        
        Break selectedBreak = sDao.getBreakById(803);
        model.addAttribute("selectedBreak", selectedBreak);
        
        List<BreakComment> breakComments = sDao.getAllBreakComments();
        model.addAttribute("breakComments", breakComments);
        
        return "break";
    }
    

 
    
}
