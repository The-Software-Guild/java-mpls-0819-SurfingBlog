/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.surfingblog.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sg.surfingblog.newpackage.dao.InvalidIdException;
import sg.surfingblog.newpackage.dao.SurfingDao;
import sg.surfingblog.newpackage.dao.SurfingDaoException;
import sg.surfingblog.newpackage.dao.UserDao;
import sg.surfingblog.newpackage.models.Break;
import sg.surfingblog.newpackage.models.BreakComment;
import sg.surfingblog.newpackage.models.SiteUser;

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
    public String displayBreak(HttpServletRequest request, Model model) throws InvalidIdException {

        int id = Integer.parseInt(request.getParameter("id"));

        List<Break> allBreaks = sDao.getAllBreaks();
        model.addAttribute("allBreaks", allBreaks);

        Break selectedBreak = sDao.getBreakById(id);
        model.addAttribute("selectedBreak", selectedBreak);

        List<BreakComment> breakComments = sDao.getCommentsByBreak(id);
        model.addAttribute("breakComments", breakComments);

        return "break";
    }

//    @PostMapping("/addBreakComment")
//    public String addBreakComment(HttpServletRequest request) throws InvalidIdException, SurfingDaoException {
//        String commentText = request.getParameter("breakCommentToAdd");
//        String breakId = request.getParameter("breakId");
//
//        BreakComment breakComment = new BreakComment();
//        breakComment.setCommentText(commentText);
//
//        Break beachBreak = new Break();
//        beachBreak.setId(Integer.parseInt(breakId));
//        breakComment.setBeachBreak(beachBreak);
//
//        sDao.addBreakComment(breakComment);
//
//        return "redirect:/break";
//    }

    @PostMapping("addBreakComment")
    public String addBreakComment(BreakComment newBreakComment, HttpServletRequest request) throws SurfingDaoException, InvalidIdException {
        
        String breakId = request.getParameter("break.id");
        String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
      
        Break breakToAdd = sDao.getBreakById(Integer.parseInt(breakId));
        SiteUser user = uDao.getUserByUsername(userName);
        
        newBreakComment.setBeachBreak(breakToAdd);
        newBreakComment.setUser(user);

        sDao.addBreakComment(newBreakComment);

        return "redirect:/beach";
    }
    
}
