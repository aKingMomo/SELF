package com.project.self.DataClass;

/**
 * Created by alexi on 4/26/2018.
 */

public class Persona {
    private String title;
    public Persona(String title){
        this.title = title;
    }
    public Persona(){
        title = "Default";
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
}
