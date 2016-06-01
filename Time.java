import java.text.DecimalFormat;

public class Time
{
	public int time;

	public Time()
	{
		time = 0000;
	}

	public  Time(int times)
	{
		time = times;
	}

	public int getHours()
	{
		if(time == 0)
		{
			return 1200/100;
		}
		if(time < 1300)
		{
			return time/100;
		}	
			return (time - 1200)/100;	
	}

	public int getMinutes()
	{
		if(time%100 > 59)
		{
			time += 40;
		}
		return time%100;
	}

	public String getMeridian()
	{
		if(time < 1200 || time == 2400)
		{
			return "AM";
		}
		return "PM";
	}

	public boolean addHours(int hours)
	{
		time += ((hours%24) * 100);

		if(time < 0)
		{
			time += 2400;
		}
		while(time > 2400)
		{
			time -= 2400;
		}
		return true;
	}
	
	public boolean addMinutes(int minutes)
	{
		if(minutes > 59)
		{
			time += ((minutes/60)*100) + (minutes%60);
		}
		if(time%100 > 60)
		{
			time += 40;
		}
		if(minutes < 60)
		{
		time += minutes;
		}
		if(time < 0)
		{
			time += 2400;
		}
		if(time > 2400)
		{
			time -= 2400;
		}
		return true;
	}

	public String getMilitary()
	{
		DecimalFormat military = new DecimalFormat("0000");
		String m = military.format(time) + getMeridian();
		return m;
	}

	public String toString()
	{
		DecimalFormat zero = new DecimalFormat("00");
		String s = getHours() + ":" + zero.format(getMinutes()) + getMeridian();
		return s;
	}
}
