package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SlipElement
{
    private int id;
    private String advice;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getAdvice()
    {
        return advice;
    }
    public void setAdvice(String advice)
    {
        this.advice = advice;
    }

}