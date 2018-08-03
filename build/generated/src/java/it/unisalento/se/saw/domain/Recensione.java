package it.unisalento.se.saw.domain;
// Generated 25-lug-2018 15.58.37 by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Recensione generated by hbm2java
 */
@Entity
@Table(name="recensione"
    ,catalog="se_db"
)
public class Recensione  implements java.io.Serializable {


     private int idrecensione;
     private Lezione lezione;
     private User user;
     private Integer voto;
     private String testo;

    public Recensione() {
    }

	
    public Recensione(int idrecensione, Lezione lezione, User user) {
        this.idrecensione = idrecensione;
        this.lezione = lezione;
        this.user = user;
    }
    public Recensione(int idrecensione, Lezione lezione, User user, Integer voto, String testo) {
       this.idrecensione = idrecensione;
       this.lezione = lezione;
       this.user = user;
       this.voto = voto;
       this.testo = testo;
    }
   
     @Id 

    
    @Column(name="idrecensione", unique=true, nullable=false)
    public int getIdrecensione() {
        return this.idrecensione;
    }
    
    public void setIdrecensione(int idrecensione) {
        this.idrecensione = idrecensione;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lezione_idlezione", nullable=false)
    public Lezione getLezione() {
        return this.lezione;
    }
    
    public void setLezione(Lezione lezione) {
        this.lezione = lezione;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_iduser", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="voto")
    public Integer getVoto() {
        return this.voto;
    }
    
    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    
    @Column(name="testo", length=45)
    public String getTesto() {
        return this.testo;
    }
    
    public void setTesto(String testo) {
        this.testo = testo;
    }




}

