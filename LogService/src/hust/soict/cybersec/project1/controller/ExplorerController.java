package hust.soict.cybersec.project1.controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import hust.soict.cybersec.project1.Main;
import hust.soict.cybersec.project1.model.AccessLog;
import hust.soict.cybersec.project1.stuff.LogTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ExplorerController {
	
	
	private Main mainApp;
	
	@FXML
	TableView<AccessLog> logtable = new TableView<AccessLog>();
	
	
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
	
	@SuppressWarnings("unchecked")
	public void createTable(){
		// Declare each column
		TableColumn<AccessLog, String> typeColumn = new TableColumn<>("Type");
		TableColumn<AccessLog, String> IpColumn = new TableColumn<>("IP");
		TableColumn<AccessLog, String> remoteIdentColumn = new TableColumn<>("User Ident");
		TableColumn<AccessLog, String> remoteUserColumn = new TableColumn<>("User Name");
		TableColumn<AccessLog, String> timeColumn = new TableColumn<>("Timestamp");
		TableColumn<AccessLog, String> requestColumn = new TableColumn<>("Request Message");
		TableColumn<AccessLog, Integer> statusColumn = new TableColumn<>("Status");
		TableColumn<AccessLog, Integer> bytesSentColumn = new TableColumn<>("Bytes");
		TableColumn<AccessLog, String> refererColumn = new TableColumn<>("Referer");
		TableColumn<AccessLog, String> userColumn = new TableColumn<>("User Agent");
		TableColumn<AccessLog, String> logColumn = new TableColumn<>("Log");

		// Set cell 
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		IpColumn.setCellValueFactory(new PropertyValueFactory<>("IP"));
		remoteIdentColumn.setCellValueFactory(new PropertyValueFactory<>("remoteIdent"));
		remoteUserColumn.setCellValueFactory(new PropertyValueFactory<>("remoteUser"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
		userColumn.setCellValueFactory(new PropertyValueFactory<>("userAgent"));
		requestColumn.setCellValueFactory(new PropertyValueFactory<>("requestUrl"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusCode"));
		bytesSentColumn.setCellValueFactory(new PropertyValueFactory<>("bytesSent"));
		refererColumn.setCellValueFactory(new PropertyValueFactory<>("referer"));
		logColumn.setCellValueFactory(new PropertyValueFactory<>("logEntry"));

		// Format each column
		IpColumn.setMinWidth(10);
		IpColumn.setMaxWidth(500);

		remoteIdentColumn.setMinWidth(10);
		remoteIdentColumn.setMaxWidth(60);

		remoteUserColumn.setMinWidth(10);
		remoteUserColumn.setMaxWidth(60);

		timeColumn.setMinWidth(10);
		timeColumn.setMaxWidth(150);

		requestColumn.setMinWidth(10);
		requestColumn.setMaxWidth(250);

		statusColumn.setMinWidth(10);
		statusColumn.setMaxWidth(50);

		bytesSentColumn.setMinWidth(10);
		bytesSentColumn.setMaxWidth(50);

		refererColumn.setMinWidth(10);
		refererColumn.setMaxWidth(300);

		// Define onAction for each row
		
		// Add to table view
		logtable.getColumns().addAll(typeColumn, IpColumn, remoteIdentColumn, remoteUserColumn, timeColumn, requestColumn, statusColumn, bytesSentColumn, refererColumn, userColumn, logColumn);
	}
	public void openSetting(ActionEvent event ) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExplorerSetting.fxml"));
		Parent root = loader.load();
		
		Stage setting  = new Stage();
		Scene settingscene = new Scene(root);
		setting.setScene(settingscene);
		setting.show();
		setting.setResizable(false);
		
	}
	
	public void selectFileLog(ActionEvent event) {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Select the log source");
		
		File resourceDir = new File("src/logsample");
		filechooser.setInitialDirectory(resourceDir);
		
		
		java.io.File selectedFile = filechooser.showOpenDialog(stage);
		
		if (selectedFile != null) {
			try{
				Scanner scanner = new Scanner(selectedFile);
				ObservableList<AccessLog> logs = FXCollections.observableArrayList();
				while (scanner.hasNextLine()) {
					logs.add(new AccessLog(scanner.nextLine()));
					
				}
				
				logtable.setItems(logs);
				
				scanner.close();
				System.out.println("show log ");
			}
			catch (Exception e){
				e.printStackTrace();
			}
            
        } else {
            System.out.println("Không có file nào được chọn.");
        }
		
	
		
	
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
	
	public void switchToWelcome(ActionEvent event) {
		mainApp.switchToOverview();
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
