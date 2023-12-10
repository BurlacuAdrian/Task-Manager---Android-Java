package app.main;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public Long dateToLong(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public Date LongToDate(Long l){
        return l == null ? null : new Date(l);
    }
}
