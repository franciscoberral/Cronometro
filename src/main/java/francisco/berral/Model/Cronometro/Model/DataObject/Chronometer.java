package francisco.berral.Model.Cronometro.Model.DataObject;

public class Chronometer {
	private int id;
	private int hour;
	private int minute;
	private int second;
	private int millisecond;
	
	public Chronometer() {
		
	}
	
	public Chronometer(int id, int hour, int minute, int second, int millisecond) {
		this.id = id;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.millisecond = millisecond;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(int millisecond) {
		this.millisecond = millisecond;
	}

	@Override
	public String toString() {
		return "Chronometer [id=" + id + "hour=" + hour + ", minute=" + minute + ", second=" + second + ", millisecond="
				+ millisecond + "]";
	}
}
