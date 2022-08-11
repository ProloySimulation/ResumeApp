package com.cvmaster.xosstech.model;

public class Education {

    private String inst_name,degree,dept,pass_year,result,board;
    private int id ;

    public Education(String inst_name, String degree, String dept, String pass_year,
                     String result, String board, int id) {
        this.inst_name = inst_name;
        this.degree = degree;
        this.dept = dept;
        this.pass_year = pass_year;
        this.result = result;
        this.board = board;
        this.id = id;
    }

    public String getInst_name() {
        return inst_name;
    }

    public void setInst_name(String inst_name) {
        this.inst_name = inst_name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPass_year() {
        return pass_year;
    }

    public void setPass_year(String pass_year) {
        this.pass_year = pass_year;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
