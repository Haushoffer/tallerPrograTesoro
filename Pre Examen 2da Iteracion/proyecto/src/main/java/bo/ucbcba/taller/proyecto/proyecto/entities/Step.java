package bo.ucbcba.taller.proyecto.proyecto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Created by CORE i7 on 24/05/2017.
 */
@Entity

public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer quantity;

    @NotNull
    @DecimalMax("10000.0")
    @DecimalMin("0.0")
    private Double meters;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    private Date uploadDate ;

    private Date registerDate ;

    @Transient
    private int bandId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Date getUploadDate() {
        return uploadDate;
    }
    public int getAssignedBandId(){
        bandId=band.getId();
        return bandId;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    @Override
    public String toString()
    {
        return quantity+" "+uploadDate.toString()+" "+registerDate.toString()+" "+bandId;
    }

    public Double getMeters() {
        return meters;
    }

    public void setMeters(Double meters) {
        this.meters = meters;
    }

}
