package com.example.barbendingschedule.Model;

import android.util.Log;

import java.io.Serializable;

public class Bar implements Serializable {
    private String _id;
    private String project_id;
    private int bar_type;
    private double a, b, c, d, e, f;
    private String title;
    private String bar_image;
    private int number_of_bars, dia;

    public Bar() {
    }

    public Bar(int barType,String title, int noBar, int dia, double a, double b, double c, double d,
               double e, double f) {
        this.bar_type = barType;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.title = title;
        this.number_of_bars = noBar;
        this.dia = dia;
    }



    public double getLength() {
        double length = 0.0;

        switch (bar_type) {
            case 0:
                length = a;
                break;

            case 1:
                length = a + b - 0.5 * Math.PI/2 - dia/1000;
                break;

            case 2:
                length = a + b - 0.43 * Math.PI/2 - 1.2 * dia/1000;
                break;

            case 3:
                length = a + (0.57 * b) + c - (1.6 * dia/1000);
                Log.d("LengthCall",""+length);
                break;

            case 4:
                length = a + c - 4 * dia/1000;
                break;

            case 5:
                length = a + c;
                break;

            case 6:
                length = a + b + c - Math.PI - 2 * dia/1000;
                break;

            case 7:
                length = a + b + c + d - 1.5 * Math.PI/2*3 - 3 * dia/1000;
                break;

            case 8:
                length = a + b + c - Math.PI - 2 * dia/1000;
                break;

            case 9:
                length = a + b + c;
                break;

            case 10:
                length = a + b + e;
                break;

            case 11:
                length = a + b + c;
                break;

            case 12:
                length = a + b + c  - 1.5 * Math.PI/2 - 3 * dia/1000;
                break;
        }
        return length;
    }

    public String getBar_img() {
        return bar_image;
    }

    public void setBar_img(String bar_img) {
        this.bar_image = bar_img;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getBar_type() {
        return bar_type;
    }

    public void setBar_type(int bar_type) {
        this.bar_type = bar_type;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNo_of_bars() {
        return number_of_bars;
    }

    public void setNo_of_bars(int no_of_bars) {
        this.number_of_bars = no_of_bars;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

}
