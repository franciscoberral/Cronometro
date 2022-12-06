package francisco.berral.Cronometro;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import francisco.berral.Cronometro.Model.DAO.ChronometerDAO;
import francisco.berral.Cronometro.Model.DataObject.Chronometer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * Clase Principal
 * @author Francisco José Berral Zafra
 *
 */
public class Principal implements Runnable, Initializable {
	
	@FXML
	private Button start;
	
	@FXML
	private Button stop;
	
	@FXML 
	private Button reanude;
	
	@FXML
	private Button restart;
	
	@FXML
	private Button show;
	
	@FXML
	private Label date;
	
	@FXML
	private Label time;
	
	@FXML 
	private Label hour;
	
	@FXML
	private Label minute;
	
	@FXML
	private Label second;
	
	@FXML
	private Label counterPause;
	
	@FXML
	private Label millisecond;
	
	@FXML
	private ImageView iPause;
	
	@FXML
	private ImageView iPlay;
	
	@FXML
	private ImageView iStop;
	
	@FXML
	private ImageView iNoStop;
	
	@FXML
	private ImageView openBook;
	
	@FXML
	private ImageView closeBook;
	
	@FXML
	private TableView<Chronometer> chrono;
	
	@FXML
	private TableColumn<Chronometer, String> timeCol;
	
	@FXML
	private AnchorPane an;
	
	private Thread thread;
	
	private Chronometer c;
	
	private ChronometerDAO cDAO = new ChronometerDAO();
	
	private boolean started;
	
	private boolean entered = false;
	
	String mi;
	
	String se;
	
	String minu;
	
	String ho;

	/**
	 * Método que ejecuta los procesos en un hilo
	 */
	@Override
	public void run() {
		try {
			while (started) {
				Thread.sleep(4);
				int milli = c.getMillisecond();
				milli += 4;
				c.setMillisecond(milli);
				
				if (c.getMillisecond() >= 1000) {
					c.setMillisecond(0);
					int sec = c.getSecond();
					sec++;
					c.setSecond(sec);
					
					if (c.getSecond() == 60) {
						c.setSecond(0);
						int min = c.getMinute();
						min++;
						c.setMinute(min);
						
						if (c.getMinute() == 60) {
							c.setMinute(0);
							int h = c.getHour();
							h++;
							c.setHour(h);
							
							if (c.getHour() == 24) {
								c.setHour(0);
							}
						}
					}
				}
				
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (c.getMillisecond() < 100) {
							millisecond.setText("0" + c.getMillisecond());
							
							if (c.getMillisecond() < 10) {
								millisecond.setText("0" + "0" + c.getMillisecond());
							}
						} else {
							millisecond.setText(String.valueOf(c.getMillisecond()));
						}
						if (c.getSecond() < 10) {
							second.setText("0" + c.getSecond());
						} else {
							second.setText(String.valueOf(c.getSecond()));
						}
						
						if (c.getMinute() < 10) {
							minute.setText("0" + c.getMinute());
						} else {
							minute.setText(String.valueOf(c.getMinute()));
						}
						
						if (c.getHour() < 10) {
							hour.setText("0" + c.getHour());
						} else {
							hour.setText(String.valueOf(c.getHour()));
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para iniciar el contador
	 */
	@FXML
	public void startCounter() {
		started = true;
		c = new Chronometer();
		thread = new Thread(this);
		thread.start();
		start.setVisible(false);
		stop.setVisible(true);
		iPause.setVisible(true);
		iPlay.setVisible(false);
	}
	
	/**
	 * Método para pausar el contador
	 */
	@FXML
	public void pauseCounter() {
		started = false;
		int svar = Integer.parseInt(second.getText());
		int mivar = Integer.parseInt(minute.getText());
		int hvar = Integer.parseInt(hour.getText());
		String s1;
		if (hvar < 10) {
			s1 = "0" + hvar;
		} else {
			s1 = hvar + "";
		}
		String s2;
		if (mivar < 10) {
			s2 = "0" + mivar;
		} else {
			s2 = mivar + "";
		}
		String s3;
		if (svar < 10) {
			s3 = "0" + svar;
		} else {
			s3 = svar + "";
		}
		counterPause.setText(s1 + ":" + s2 + ":" + s3);
		cDAO.insert(c);
		stop.setVisible(false);
		reanude.setVisible(true);
		restart.setVisible(true);
		restart.setDisable(false);
		iPause.setVisible(false);
		iPlay.setVisible(true);
		iStop.setVisible(true);
		iNoStop.setVisible(false);
	}
	
	/**
	 * Método para reanudar el contador
	 */
	@FXML 
	public void reanudeCounter() {
		started = true;
		thread = new Thread(this);
		thread.start();
		stop.setVisible(true);
		reanude.setVisible(false);
		restart.setDisable(true);
		iPause.setVisible(true);
		iPlay.setVisible(false);
		iStop.setVisible(false);
		iNoStop.setVisible(true);
	}
	
	/**
	 * Método para reiniciar el contador
	 */
	@FXML
	public void restartCounter() {
		millisecond.setText("000");
		second.setText("00");
		minute.setText("00");
		hour.setText("00");
		start.setVisible(true);
		restart.setDisable(true);
		reanude.setVisible(false);
		iStop.setVisible(false);
		iNoStop.setVisible(true);
	}
	
	/**
	 * Método para mostrar la lista de tiempos
	 */
	@FXML
	public void showTimes() {
		if (entered == false) {
			an.setVisible(true);
			entered = true;
			openBook.setVisible(false);
			closeBook.setVisible(true);
		} else {
			an.setVisible(false);
			entered = false;
			openBook.setVisible(true);
			closeBook.setVisible(false);
		}
	}
	
	/**
	 * Método para establecer el formato de fecha y hora
	 */
	public void dateTime() {
		LocalDateTime localDate = LocalDateTime.now();
		int ldyear = localDate.getYear();
		int ldmonth = localDate.getMonthValue();
		String s1;
		if (ldmonth < 10) {
			s1 = "0" + ldmonth;
		} else {
			s1 = ldmonth + "";
		}
		int ldday = localDate.getDayOfMonth();
		String s2;
		if (ldday < 10) {
			s2 = "0" + ldday;
		} else {
			s2 = ldday + "";
		}
		int ldhour = localDate.getHour();
		String s3;
		if (ldhour < 10) {
			s3 = "0" + ldhour;
		} else {
			s3 = ldhour + "";
		}
		int ldmin = localDate.getMinute();
		String s4;
		if (ldmin < 10) {
			s4 = "0" + ldmin;
		} else {
			s4 = ldmin + "";
		}
		date.setText(s2 + "/" + s1 + "/" + ldyear);
		time.setText(s3 + ":" + s4);
	}

	/**
	 * Método donde se ejecutan procesos al inicio
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reanude.setVisible(false);
		stop.setVisible(false);
		restart.setDisable(true);
		counterPause.setText("00:00:00");
		
		dateTime();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
			LocalDateTime localDate = LocalDateTime.now();
			int ldyear = localDate.getYear();
			int ldmonth = localDate.getMonthValue();
			String s1;
			if (ldmonth < 10) {
				s1 = "0" + ldmonth;
			} else {
				s1 = ldmonth + "";
			}
			int ldday = localDate.getDayOfMonth();
			String s2;
			if (ldday < 10) {
				s2 = "0" + ldday;
			} else {
				s2 = ldday + "";
			}
			int ldhour = localDate.getHour();
			String s3;
			if (ldhour < 10) {
				s3 = "0" + ldhour;
			} else {
				s3 = ldhour + "";
			}
			int ldmin = localDate.getMinute();
			String s4;
			if (ldmin < 10) {
				s4 = "0" + ldmin;
			} else {
				s4 = ldmin + "";
			}
			date.setText(s2 + "/" + s1 + "/" + ldyear);
			time.setText(s3 + ":" + s4);
			
			List<Chronometer> list = cDAO.getAll();
			
			ObservableList<Chronometer> ob = FXCollections.observableArrayList(list);
			
			timeCol.setCellValueFactory(chronometer ->{
				SimpleStringProperty s = new SimpleStringProperty();
				s.setValue(chronometer.getValue().getTime());
				return s;
			});
			
			chrono.setItems(FXCollections.observableArrayList(ob));
	    }));
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();
	}
}
