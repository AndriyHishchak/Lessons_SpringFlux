package SpringFlux.Start.Lesson_0_SpringFlux.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class CastomerEvent {

    private Castomer castomer;
    private Date date;

    public CastomerEvent(Castomer castomer, Date date) {
        this.castomer = castomer;
        this.date = date;
    }

    public Castomer getCastomer() {
        return castomer;
    }

    public void setCastomer(Castomer castomer) {
        this.castomer = castomer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
