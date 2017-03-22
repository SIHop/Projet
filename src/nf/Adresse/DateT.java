package nf.Adresse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateT {

    private Calendar c = Calendar.getInstance(Locale.FRANCE);

    /**
     * Date au format aaaa-mm-jj (yyyy-mm-dd)
     *
     * @param date
     */
    public DateT(String date) {
        if(date.length()>0){
            c.set(Integer.parseInt(date.substring(0, date.indexOf("-"))),
                Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")))-1,
                Integer.parseInt(date.substring(date.lastIndexOf("-") + 1, date.length())));
        }        
    }
    public DateT(Date date){
        this.c.setTime(date);
    }

    /**
     * @return the d
     */
    public Calendar getC() {
        return c;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        return dateFormat.format(this.getC().getTime());
    }

    /**
     * @param c the c to set
     */
    public void setC(Calendar c) {
        this.c = c;
    }
    
    public boolean equals(DateT dateRecus){
        return this.getC().get(Calendar.DAY_OF_MONTH) == dateRecus.getC().get(Calendar.DAY_OF_MONTH) && 
                this.getC().get(Calendar.MONTH) == dateRecus.getC().get(Calendar.MONTH) && 
                this.getC().get(Calendar.YEAR) == dateRecus.getC().get(Calendar.YEAR);
    }
}
