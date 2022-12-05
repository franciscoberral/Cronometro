package francisco.berral.Cronometro.Model.DataObject;

import francisco.berral.Cronometro.Interfaces.IChronometer;

/**
 * Clase Chronometer
 * @author Francisco José Berral Zafra
 *
 */
public class Chronometer implements IChronometer {
	private int hour;
	private int minute;
	private int second;
	private int millisecond;
	
	/**
	 * Constructor
	 */
	public Chronometer() {
		
	}
	
	/**
	 * Constructor
	 * @param hour Horas del crónometro
	 * @param minute Minutos del cronómetro
	 * @param second Segundos del cronómetro
	 * @param millisecond Milisegundos del cronómetro
	 */
	public Chronometer(int hour, int minute, int second, int millisecond) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.millisecond = millisecond;
	}
	
	/**
	 * Método para obtener las horas
	 * @return Horas del cronómetro
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Método para setear las horas
	 * @param hour Horas por las que se va a cambiar
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * Método para obtener los minutos
	 * @return Minutos del cronómetro
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Método para setear los minutos
	 * @param minute Minutos por los que se va a cambiar
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * Método para obtener los segundos
	 * @return Segundos del cronómetro
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * Método para setear los segundos
	 * @param second Segundos por los que se va a cambiar
	 */
	public void setSecond(int second) {
		this.second = second;
	}

	/**
	 * Método para obtener los milisegundos
	 * @return Milisegundos del cronómetro
	 */
	public int getMillisecond() {
		return millisecond;
	}

	/**
	 * Método setear los milisegundos
	 * @param millisecond Milisegundos por los que se va a cambiar
	 */
	public void setMillisecond(int millisecond) {
		this.millisecond = millisecond;
	}

	/**
	 * Método para mostrar los datos del cronómetro
	 * @return Datos del cronómetro
	 */
	@Override
	public String toString() {
		return "Chronometer [hour=" + hour + ", minute=" + minute + ", second=" + second + ", millisecond="
				+ millisecond + "]";
	}
}
