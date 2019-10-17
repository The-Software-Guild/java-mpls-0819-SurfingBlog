/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.surfingblog.newpackage.models;

import java.util.List;

/**
 *
 * @author Chad
 */
public class Beach {
    
    private int id;
    private String name;
    private int zipCode;
    private List<Break> beachBreaks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public List<Break> getBeachBreaks() {
        return beachBreaks;
    }

    public void setBeachBreaks(List<Break> beachBreaks) {
        this.beachBreaks = beachBreaks;
    }
   
}
