/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.surfingblog.newpackage.models;

/**
 *
 * @author Chad
 */
public class Comment {
    
    private int id;
    private Beach beach;
    private Break commentBreak;
    private boolean isbreak;
    private SiteUser user;
    private String commentText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SiteUser getUser() {
        return user;
    }

    public void setUser(SiteUser user) {
        this.user = user;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Beach getBeach() {
        return beach;
    }

    public void setBeach(Beach beach) {
        this.beach = beach;
    }

    public Break getCommentBreak() {
        return commentBreak;
    }

    public void setCommentBreak(Break commentBreak) {
        this.commentBreak = commentBreak;
    }

    public boolean getIsbreak() {
        return isbreak;
    }


    public void setIsbreak(boolean isbreak) {
        this.isbreak = isbreak;
    }
    
    
    
    
    
}
