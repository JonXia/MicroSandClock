package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.timer.utils.GlobalVariates;
import com.timer.utils.PlayMusicUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MyController implements Initializable {
	static Timer timer = null;
	String xTime = null;
	static {
		timer = GlobalVariates.timer;
	}
	boolean oneManufactureFlag = false;
	@FXML
	private Button myButton;
	@FXML
	private Button ccButton;
	@FXML
	private Button exitButton;

	@FXML
	private TextField myTextField;

	@FXML
	private TextField timePlayer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	/**
	 * 解析字符串
	 */
	public void timeAnalyze() {

		GlobalVariates.hour = 0;
		GlobalVariates.minute = 0;
		GlobalVariates.second = 0;

		GlobalVariates.hour = Integer.parseInt(xTime.substring(0, 2));
		GlobalVariates.minute = Integer.parseInt(xTime.substring(3, 5));
		GlobalVariates.second = Integer.parseInt(xTime.substring(6, 8));
		if (GlobalVariates.second <= 59 && GlobalVariates.second >= 0 && GlobalVariates.minute <= 59
				&& GlobalVariates.minute >= 0 && GlobalVariates.hour <= 23 && GlobalVariates.hour >= 0) {
			// this.timeService();
		} else {
			System.out.println("It's a wrong time,please try again.");
		}
	}

	/**
	 * 退出沙漏的Gui
	 * 
	 * @param event
	 */
	public void exitTimer(ActionEvent event) {

	}

	/*
	 * 取消本次计时
	 */
	public void cancelTimer(ActionEvent event) {
		System.out.println("cancelTheTimer");
		timer.cancel();
		// timer=null;
		timer = GlobalVariates.getTimer();
		// System.exit(0);
	}

	/**
	 * 计时器主处理器
	 * 
	 * @param event
	 */
	public void showDateTime(ActionEvent event) {
		xTime = myTextField.getText();
		// 正则过滤格式HH:mm:ss
		String regEx = "^([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(xTime);
		boolean rs = matcher.matches();

		if (rs == true) {
			this.timeAnalyze();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					String hour = "";
					String minute = "";
					String second = "";
					this.timeService();
					hour = String.valueOf(GlobalVariates.hour);
					minute = String.valueOf(GlobalVariates.minute);
					second = String.valueOf(GlobalVariates.second);
					// 数字补0
					if (GlobalVariates.hour >= 0 && GlobalVariates.hour < 10) {
						hour = "0" + String.valueOf(GlobalVariates.hour);
					}
					if (GlobalVariates.minute >= 0 && GlobalVariates.minute < 10) {
						minute = "0" + String.valueOf(GlobalVariates.minute);
					}
					if (GlobalVariates.second >= 0 && GlobalVariates.second < 10) {
						second = "0" + String.valueOf(GlobalVariates.second);
					}
					timePlayer.setText(hour + ":" + minute + ":" + second);
					if (GlobalVariates.hour == 0 && GlobalVariates.minute == 0 && GlobalVariates.second == 0) {
						timePlayer.setText("alarming!");
						new PlayMusicUtil().play();
						timer.cancel();
					}
				}

				/**
				 * 时间功能代码
				 */
				public void timeService() {
					// timer.schedule(new TimerTask() {
					// @Override
					// public void run() {
					if (GlobalVariates.hour == 0 && GlobalVariates.minute == 0 && GlobalVariates.second == 0) {
						// System.out.println("alarming!");
						return;
					}
					GlobalVariates.second--;
					if (GlobalVariates.second < 0) {
						GlobalVariates.minute--;
						GlobalVariates.second = 59;
					} else if (GlobalVariates.minute == 0 && GlobalVariates.hour == 0 && GlobalVariates.second == 0) {
						GlobalVariates.second = 0;
					}
					if (GlobalVariates.minute < 0) {
						GlobalVariates.hour--;
						GlobalVariates.minute = 59;
					} else if (GlobalVariates.minute == 0 && GlobalVariates.hour == 0) {
						GlobalVariates.minute = 0;
					}
					if (GlobalVariates.hour < 0) {
						GlobalVariates.hour = 0;
					}
					// System.out.println("
					// ts--->"+String.valueOf(GlobalVariates.hour)+":"+String.valueOf(GlobalVariates.minute)+":"+String.valueOf(GlobalVariates.second));
					// t.setGlobalVariates.hour(GlobalVariates.hour);
					// t.setGlobalVariates.minute(GlobalVariates.minute);
					// t.setGlobalVariates.second(GlobalVariates.second);
				}
			}, 0, 1000l);
		} else {
			myTextField.setText("请输入正确的时间格式(HH:mm:ss)");
		}
	}

}
