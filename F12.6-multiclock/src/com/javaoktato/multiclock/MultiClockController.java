/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.multiclock;

import static java.util.stream.Collectors.toList;

import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class MultiClockController {
	@FXML
	private ChoiceBox<ZoneId> timeZoneList;

	@FXML
	private Label clockLabel;

	@FXML
	private Label stopperLabel;

	private Timeline updateStopper;

	@FXML
	private ListView<String> stopperLapsList;

	@FXML
	private Label timerLabel;

	private Timeline updateTimer;

	@FXML
	private TextField timerTextField;

	private DateTimeUtil util = new DateTimeUtil();

	private ObjectProperty<LocalDate> durationStart;

	private ObjectProperty<LocalDate> durationEnd;

	@FXML
	private DatePicker durationStartPicker;

	@FXML
	private DatePicker durationEndPicker;

	@FXML
	private Label durationLabel;

	@FXML
	public void initialize() {
		List<ZoneId> zoneIds = ZoneId.getAvailableZoneIds().stream().map(id -> ZoneId.of(id))
				.sorted((a, b) -> a.getId().compareTo(b.getId())).collect(toList());
		timeZoneList.setItems(FXCollections.observableArrayList(zoneIds));
		timeZoneList.setValue(ZoneId.systemDefault());

		Timeline updateClock = new Timeline(
				new KeyFrame(Duration.seconds(0),
						a -> clockLabel.setText(util.getCurrentTimeByTimeZone(timeZoneList.getValue().toString()))),
				new KeyFrame(Duration.seconds(1)));
		updateClock.setCycleCount(Animation.INDEFINITE);
		updateClock.play();

		stopperLabel.setText(util.getZeroTime());
		stopperLapsList.setItems(FXCollections.observableArrayList());

		updateStopper = new Timeline(new KeyFrame(Duration.seconds(0), a -> {
			stopperLabel.setText(util.getCurrentStopperDuration());
			stopperLapsList.setItems(FXCollections.observableArrayList(util.getStopperLaps()));
		}), new KeyFrame(Duration.seconds(1)));
		updateStopper.setCycleCount(Animation.INDEFINITE);

		timerLabel.setText(util.getZeroTime());

		updateTimer = new Timeline(new KeyFrame(Duration.seconds(0), a -> {
			timerLabel.setText(util.getCurrentTimerDuration());
			if (util.isTimerOff()) {
				Toolkit.getDefaultToolkit().beep();
				handleTimerStopClick(null);
			}
		}), new KeyFrame(Duration.seconds(1)));
		updateTimer.setCycleCount(Animation.INDEFINITE);

		durationStart = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		durationStartPicker.valueProperty().bindBidirectional(durationStart);
		durationEnd = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		durationEndPicker.valueProperty().bindBidirectional(durationEnd);
		updateDuration(null);
	}

	@FXML
	public void handleStopperLapClick(ActionEvent e) {
		util.lapStopper();
	}

	@FXML
	public void handleStopperStartClick(ActionEvent e) {
		util.startStopper();
		updateStopper.play();
	}

	@FXML
	public void handleStopperStopClick(ActionEvent e) {
		util.stopStopper();
		updateStopper.stop();
	}

	@FXML
	public void handleStopperResetClick(ActionEvent e) {
		util.resetStopper();
		updateStopper.stop();
		stopperLabel.setText(util.getCurrentStopperDuration());
		stopperLapsList.setItems(FXCollections.observableArrayList());
	}

	@FXML
	public void handleTimerStartClick(ActionEvent e) {
		util.startTimer(timerTextField.getText());
		updateTimer.play();
	}

	@FXML
	public void handleTimerStopClick(ActionEvent e) {
		util.stopTimer();
		updateTimer.stop();
	}

	@FXML
	public void handleTimerResumeClick(ActionEvent e) {
		util.resumeTimer();
		updateTimer.play();
	}

	@FXML
	public void durationFromNow(ActionEvent e) {
		durationStart.setValue(LocalDate.now());
	}

	@FXML
	public void durationUntilNow(ActionEvent e) {
		durationEnd.setValue(LocalDate.now());
	}

	@FXML
	public void updateDuration(ActionEvent e) {
		LocalDate first = durationStart.getValue();
		LocalDate second = durationEnd.getValue();
		String duration = util.getDuration(first.getYear(), first.getMonthValue(), first.getDayOfMonth(),
				second.getYear(), second.getMonthValue(), second.getDayOfMonth());
		durationLabel.setText(duration);
	}
}
