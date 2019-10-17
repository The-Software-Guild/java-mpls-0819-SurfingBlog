/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.surfingblog.newpackage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sg.surfingblog.newpackage.models.Beach;
import sg.surfingblog.newpackage.models.Break;
import sg.surfingblog.newpackage.models.Comment;
import sg.surfingblog.newpackage.models.News;

/**
 *
 * @author blee0
 */
@Repository
@Profile({"production", "test"})
public class SurfingDaoDB implements SurfingDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Break> getBreaksByBeach(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getCommentsByBeach(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getCommentsByBreak(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<News> getAllActiveNews() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public News getNewsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public News addNews(News toAdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateNews(News updatedNews) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteNews(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Beach> getAllBeaches() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Beach getBeachById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Beach addBeach(Beach toAdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateBeach(Beach updatedBeach) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBeach(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Break> getAllBreaks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Break getBreakById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Break addBreak(News toAdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateBreak(News updatedNews) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBreak(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getAllComments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comment getCommentById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comment addComment(News toAdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateComment(News updatedNews) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteComment(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class NewsMapper implements RowMapper<News> {

        @Override
        public News mapRow(ResultSet results, int rowNum) throws SQLException {
            News toReturn = new News();
            toReturn.setId(results.getInt("Id"));
            toReturn.setNewsURL(results.getString("News_url"));
            toReturn.setPicURL(results.getString("Picture_url"));
            toReturn.setIsActive(results.getBoolean("IsActive"));
            
            return toReturn;
        }
    }
    
    private class BeachMapper implements RowMapper<Beach> {

        @Override
        public Beach mapRow(ResultSet results, int rowNum) throws SQLException {
            Beach toReturn = new Beach();
            toReturn.setId(results.getInt("Id"));
            toReturn.setName(results.getString("Name"));
            toReturn.setZipCode(results.getInt("Zipcode"));
            
            return toReturn;
        }     
    }
    
    private class BreakMapper implements RowMapper<Break> {

        @Override
        public Break mapRow(ResultSet results, int rowNum) throws SQLException {
            Break toReturn = new Break();
            toReturn.setId(results.getInt("BreakId"));
            toReturn.setName(results.getString("BreakName"));
            toReturn.setLatitude(results.getBigDecimal("Latitude"));
            toReturn.setLongitude(results.getBigDecimal("Longitude"));
            
            Beach toAdd = new Beach();
            toAdd.setId(results.getInt("BeachId"));
            toAdd.setName(results.getString("BeachName"));
            toAdd.setZipCode(results.getInt("Zipcode"));
            toReturn.setBeach(toAdd);
            
            return toReturn;
        }
    }
    
    private class CommentMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet results, int rowNum) throws SQLException {
            Comment toReturn = new Comment();
            toReturn.setId(results.getInt("CommentId"));
            toReturn.setIsbreak(results.getBoolean("IsBreakComment"));
            toReturn.setCommentText(results.getString("Comment"));
            
            Beach beachToAdd = new Beach();
            beachToAdd.setId(results.getInt("BeachId"));
            beachToAdd.setName(results.getString("BeachName"));
            beachToAdd.setZipCode(results.getInt("Zipcode"));
            toReturn.setBeach(beachToAdd);
            
            Break breakToAdd = new Break();
            breakToAdd.setId(results.getInt("BreakId"));
            breakToAdd.setName(results.getString("BreakName"));
            breakToAdd.setBeach(beachToAdd);
            breakToAdd.setLatitude(results.getBigDecimal("Latitude"));
            breakToAdd.setLongitude(results.getBigDecimal("Longitude"));
            toReturn.setCommentBreak(breakToAdd);
            
            return toReturn;
        }
    }   

}
