package com.frantisekpost.util.cpukiller.app;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import com.frantisekpost.util.cpukiller.ComputingUnitRunner;

public class AppController implements Initializable {

	@FXML
	private Button startButton;
	
	@FXML
	private Label runningLabel;
	
	@FXML
	private Label loadLabel;
	
	@FXML
	private Slider loadSlider;
	
	@FXML
	private Label numberOfCoresLabel;
	
	private int defaultSliderValue = 50;
    private IntegerProperty sliderValue = new SimpleIntegerProperty(defaultSliderValue);
    private ComputingUnitRunner cuRunner;
    
    private static final NumberFormat NUBER_FORMATTER = NumberFormat.getIntegerInstance();
    
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		numberOfCoresLabel.setText(NUBER_FORMATTER.format(availableProcessors));
		
		loadSlider.valueProperty().bindBidirectional(sliderValue);
		loadLabel.textProperty().bind(sliderValue.asString());
		
		startButton.setOnAction(EventHandler -> handleStartButton());
	}
	
	private void handleStartButton() {
		if (cuRunner == null) {
			cuRunner = new ComputingUnitRunner(sliderValue.floatValue()/100);
			cuRunner.run();
			runningLabel.visibleProperty().setValue(Boolean.TRUE);
			startButton.textProperty().setValue("Stop");
			loadSlider.disableProperty().setValue(Boolean.TRUE);
		} else {
			cuRunner.stop();
			cuRunner = null;
			runningLabel.visibleProperty().setValue(Boolean.FALSE);
			startButton.textProperty().setValue("Start");
			loadSlider.disableProperty().setValue(Boolean.FALSE);
		}
	}

}
