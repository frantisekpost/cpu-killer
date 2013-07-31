package com.frantisekpost.util.cpukiller.app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import com.frantisekpost.util.cpukiller.ComputingUnitRunner;

public class AppController implements Initializable {

	@FXML
	Button startButton;
	
	@FXML
	Label runningLabel;
	
	@FXML
	Label loadLabel;
	
	@FXML
	Slider loadSlider;
	
	@FXML
	Label numberOfCoresLabel;
	
	private int defaultSliderValue = 50;
    private IntegerProperty sliderValue = new SimpleIntegerProperty(defaultSliderValue);
    private ComputingUnitRunner cuRunner;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		numberOfCoresLabel.setText(String.format("%d", availableProcessors));
		
		loadSlider.valueProperty().bindBidirectional(sliderValue);
		loadLabel.textProperty().bind(sliderValue.asString());
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
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
			
		});
	}

}
