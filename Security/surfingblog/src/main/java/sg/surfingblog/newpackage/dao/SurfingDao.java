/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.surfingblog.newpackage.dao;

import java.util.List;
import sg.surfingblog.newpackage.models.Beach;
import sg.surfingblog.newpackage.models.Break;
import sg.surfingblog.newpackage.models.Comment;
import sg.surfingblog.newpackage.models.News;

/**
 *
 * @author blee0
 */
public interface SurfingDao {
    
    List<Break> getBreaksByBeach(int id);
    
    List<Comment> getCommentsByBeach(int id);
    
    List<Comment> getCommentsByBreak(int id);

    List<News> getAllActiveNews();

    News getNewsById(int id);

    News addNews(News toAdd);

    void updateNews(News updatedNews);

    void deleteNews(int id);

    List<Beach> getAllBeaches();

    Beach getBeachById(int id);

    Beach addBeach(Beach toAdd);

    void updateBeach(Beach updatedBeach);

    void deleteBeach(int id);

    List<Break> getAllBreaks();

    Break getBreakById(int id);

    Break addBreak(News toAdd);

    void updateBreak(News updatedNews);

    void deleteBreak(int id);

    List<Comment> getAllComments();

    Comment getCommentById(int id);

    Comment addComment(News toAdd);

    void updateComment(News updatedNews);

    void deleteComment(int id);

}
