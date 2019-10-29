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
    public String displayBreak(Model model) throws InvalidIdException {

//        int id = Integer.parseInt(request.getParameter("id"));
        List<Break> allBreaks = sDao.getAllBreaks();
        model.addAttribute("allBreaks", allBreaks);

//        Break selectedBreak = sDao.getBreakById(id);
//        model.addAttribute("selectedBreak", selectedBreak);
//        
//        List<BreakComment> breakComments = sDao.getCommentsByBreak(id);
//        model.addAttribute("breakComments", breakComments);
        return "break";
    }

    @GetMapping("breakDetail")
    public String breakDetail(Integer id, Model model) throws InvalidIdException {
        Break beachBreak = sDao.getBreakById(id);
        model.addAttribute("beachBreak", beachBreak);

        List<BreakComment> breakComments = sDao.getCommentsByBreak(id);
        model.addAttribute("breakComments", breakComments);

        return "breakDetail";
    }

    @PostMapping("addBreakComment")
    public String addBreakComment(BreakComment newBreakComment, HttpServletRequest request) throws InvalidIdException, SurfingDaoException {
        String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        SiteUser user = uDao.getUserByUsername(userName);

        int id = Integer.parseInt(request.getParameter("breakId"));

        Break breakToAdd = sDao.getBreakById(id);

        newBreakComment.setUser(user);
        newBreakComment.setBeachBreak(breakToAdd);

        sDao.addBreakComment(newBreakComment);

        return "redirect:/breakDetail?id=" + id;
    }

    @GetMapping("deleteBreakComment")
    public String deleteBreakComment(Integer id, HttpServletRequest request) throws InvalidIdException {
        
        int breakId = sDao.getBreakCommentById(id).getBeachBreak().getId();
        
        sDao.deleteBreakComment(id);
        
        return "redirect:/breakDetail?id=" + breakId;
    }

}
