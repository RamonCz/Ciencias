/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author ramon
 */
class Ticket implements Serializable {
    private ArrayList<Pizza> lista = new ArrayList();

    private boolean d= false;
    private int refrescos =0;
    private int cerveza =0;
    private int jugos=0;
    private int fresas =0;
    private int flan=0;
    private int id =0;
    private Calendar calendario = Calendar.getInstance();
    private int total=0;
    public Ticket(int id){
        this.id = id;
    }

    public void setRefrescos() {
        refrescos++;
    }

    public void setCerveza() {
        this.cerveza++;
    }
    public void setJugos() {
        this.jugos++;
    }
    public void setFresas() {
        this.fresas++;
    }
    public void setFlan() {
        this.flan++;
    }


    public String hora(){
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        String s = hora+":"+minutos;
        return s;
    }


    public String getTicket(){
        String s ="";
        for(int i = 0; i< lista.size();i++){
            s +=" Pizza: "+lista.get(i).toString()+"\n";
        }
        if (refrescos != 0) {
            s +=" Refrescos: "+refrescos+" ...... $"+(refrescos*25)+"\n";
        }
        if(cerveza !=0){
            s +=" Cerveza: "+cerveza+" ...... $"+(cerveza*20)+"\n";
        }
        if (jugos != 0) {
            s +=" Jugos : "+jugos+" ...... $"+(jugos*15)+"\n";
        }
        if (fresas != 0) {
            s +=" Fresas : "+fresas+" ...... $"+(fresas*15)+"\n";
        }
        if (flan != 0) {
             s +=" Flan : "+fresas+" ...... $"+(flan*15)+"\n";
        }

        s += " Total: "+this.getTotal()+"\n";
        s += " Id :"+id+"\n";
        s += " Hora: "+hora();
        return s;
    }

    public int getTotal(){
        total = 0;
        for(int i = 0; i< lista.size();i++){
            total += lista.get(i).getPrecio();
        }
         total += (refrescos*25);
         total += (cerveza*20);
         total += (jugos*15);
         total += (fresas*15);
         total += (flan*15);
         if (d) {
            double t = total * 0.20;
            total -= (int)t;
        }
         return total;
    }
    /**
     *
     * @param p
     * @param t
     */
    public void addPizza(boolean d,int p, int t, int n){
        if (n == 2) {
            lista.add(new Pizza(t,p,0));
        }
        this.d = d;
        lista.add(new Pizza(t,p,0));

    }


}
