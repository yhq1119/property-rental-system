package utilities;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

@SuppressWarnings({"ALL", "unused"})
public class DateTime
{

    private long advance;
    private long time;


    //constructors
    public DateTime()
    {
        time = System.currentTimeMillis();
    }

    public DateTime(int setClockForwardInDays)
    {
        advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
        time = System.currentTimeMillis() + advance;
    }

    public DateTime(DateTime startDate, int setClockForwardInDays)
    {
        advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
        time = startDate.getTime() + advance;
    }

    public DateTime(int day, int month, int year)
    {
        setDate(day, month, year);
    }

    public DateTime(String day,String month,String  year){

        int day_int = -1;
        int month_int = -1;
        int year_int = -1;

        try{
            day_int = Integer.parseInt(day);
            month_int = Integer.parseInt(month);
            year_int = Integer.parseInt(year);

        }catch (Exception e){
            System.out.println("Invalid value to build date. Check the input of day,month,year.");
        }

        try {
            setDate(day_int,month_int,year_int);

        }catch (Exception e){
            System.out.println("Error, date may be out of range.");
        }

    }

    //methods

    private long getTime()
    {
        return time;
    }

    public String toString()
    {
//		long currentTime = getTime();
//		Date gct = new Date(currentTime);
//		return gct.toString();
        return getFormattedDate();
    }



    public static String getCurrentTime()
    {
        Date date = new Date(System.currentTimeMillis());  // returns current Date/Time
        return date.toString();
    }

    public String getFormattedDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        long currentTime = getTime();
        Date gct = new Date(currentTime);

        return sdf.format(gct);
    }

    public String getEightDigitDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        long currentTime = getTime();
        Date gct = new Date(currentTime);

        return sdf.format(gct);
    }

    // returns difference in days
    public static int diffDays(DateTime endDate, DateTime startDate)
    {
        final long HOURS_IN_DAY = 24L;
        final int MINUTES_IN_HOUR = 60;
        final int SECONDS_IN_MINUTES = 60;
        final int MILLISECONDS_IN_SECOND = 1000;
        long convertToDays = HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTES * MILLISECONDS_IN_SECOND;
        long hirePeriod = endDate.getTime() - startDate.getTime();
        double difference = (double)hirePeriod / (double)convertToDays;
        return (int)Math.round(difference);
    }




    private void setDate(int day, int month, int year)
    {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0);

        java.util.Date date = calendar.getTime();

        time = date.getTime();
    }

    // Advances date/time by specified days, hours and mins for testing purposes
    public void setAdvance(int days, int hours, int mins)
    {
        advance = ((days * 24L + hours) * 60L) * 60000L;
    }

    public int getWEEK_OF_DAY() {
        Calendar c = Calendar.getInstance();
        long targetDate = this.time;
        java.util.Date actDate = new java.util.Date(targetDate);
        c.setTime(actDate);
        return c.get(Calendar.DAY_OF_WEEK);
    }
}

