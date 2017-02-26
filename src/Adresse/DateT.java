package Adresse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateT {

    Calendar c = Calendar.getInstance(Locale.FRANCE);

    /**
     * Date au format aaaa-mm-jj (yyyy-mm-dd)
     *
     * @param date
     */
    public DateT(String date) {
        c.clear();
        c.set(Integer.parseInt(date.substring(0, date.indexOf("-"))),
                Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")))-1,
                Integer.parseInt(date.substring(date.lastIndexOf("-") + 1, date.length())));
    }

    /**
     * @return the d
     */
    public Calendar getC() {
        return c;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        return dateFormat.format(this.getC().getTime());
    }
}
