package com.project.self.Helper;

/**
 * Created by MMondal on 2/21/2018.
 */

public class FieldCheck {
    String name;
    boolean enabled; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    public FieldCheck(String name, boolean value){
        this.name = name;
        this.enabled = value;
    }
    public String getName(){
        return this.name;
    }
    public boolean getEnabled(){
        return this.enabled;
    }
}
