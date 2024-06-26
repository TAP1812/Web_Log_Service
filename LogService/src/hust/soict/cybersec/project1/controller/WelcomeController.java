package hust.soict.cybersec.project1.controller;

import hust.soict.cybersec.project1.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class WelcomeController {
	private Main mainApp;
	
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
    }
	
	
	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Log out");
		alert.setHeaderText("You're about log out");
		alert.setContentText("Are you sure?");
		if (alert.showAndWait().get() == ButtonType.OK) {
			mainApp.logout();
		}
	}
	public void switchToOverview(ActionEvent event) {
		mainApp.switchToOverview();
	}
	public void switchToDashboard(ActionEvent event) {
		mainApp.switchToDashboard();
	}
	public void switchToStream(ActionEvent event) {
		mainApp.switchToStream();
	}
	public void switchToExplorer(ActionEvent event) {
		mainApp.switchToExplorer();
	}
	
}
