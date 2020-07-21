package com.lambdaschool.schools.models;

import javax.persistence.*;

@Entity
@Table(name = "slip")
public class Slip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String advice;

    public Slip(String advice) {
        this.advice = advice;
    }

    public Slip() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public getSlip() { return super() };

    @Override
    public String toString() {
        return super.toString();
    }
}
