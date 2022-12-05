package francisco.berral.Cronometro.Interfaces;

/**
 * Interfaz de Chronometer
 * @author Francisco José Berral Zafra
 *
 */
public interface IChronometer {
	int getHour();
	void setHour(int hour);
	int getMinute();
	void setMinute(int minute);
	int getSecond();
	void setSecond(int second);
	int getMillisecond();
	void setMillisecond(int millisecond);
	String toString();
}
