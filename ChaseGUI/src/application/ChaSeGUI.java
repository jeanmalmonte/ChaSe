/* File: ChaSeGUI.java
 * Authors: Joshua C Oglesby, Jean Michael Almonte
 * Instructor: Dr. Stacy Lukins
 * CS 321 - Object-Oriented Programming
 * ChaSe - A Chat Service
 * ChaSe seeks to aid in the instant communication between peers working within a closed network.
 */

package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChaSeGUI {
	
		//Private variables to be used by controller class
		private String username;
		private Stage currentStage;
		private FXMLLoader loader;
		 
		public void setStage(Stage stage, FXMLLoader load)
		{
			this.currentStage = stage;
			this.loader = load;
		}
		
		private void setUsername(String usr) {
			this.username = usr;
		}
		
		public String getUsername() {
			return this.username;
		}
		
	    @FXML // fx:id="userTextField"
	    private TextField userTextField; 
	    
	    @FXML // fx:id="loginButton"
	    private Button loginButton; 
	    
	    @FXML // This method is called by the FXMLLoader when initialization is complete
	    public void initialize() 
	    {
	    	assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'Login - ChaSe.fxml'.";
	        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'Login - ChaSe.fxml'.";
	        
	        loginButton.setOnAction((event) -> {
        		//Get text from user 
				String usr = userTextField.getText();
        		setUsername(usr.toString());
        		
        		//Check if empty username or we can check other conditions for name here
        		if (username.isEmpty() || username.contains(" ")) {
        			//userTextField.setText("Please enter a valid username!");
        			userTextField.clear();
        		}
        		else {
        			try {
        				//Close previous window
        				currentStage.hide();
        				 
        				//Create a new loader for the mainWinPane
        				loader = new FXMLLoader(getClass().getResource("MainChat-ChaSe.fxml"));
        				         				
        				Pane mainWinPane = (Pane)loader.load();
        				
        				//Set controller to mainChatControl
        				loader.setController(new mainChatControl());
        				
        				//Set window title
        				currentStage.setTitle("ChaSe - A Chat Service");	
        				
        				//Get its controller and set the stage to the currentStage. TODO -> Set username is not working
        				mainChatControl mainControl = (mainChatControl) loader.getController();
        				mainControl.setStage(currentStage);
        				mainControl.setUsername(username);
        							
        				//Create a new Scene with the mainWinPane and show to screen
        				Scene mainScene = new Scene(mainWinPane);
        				currentStage.setScene(mainScene);
        				currentStage.centerOnScreen();
        				currentStage.show();
        	        }
        		    catch (Exception e) {
        		    	e.printStackTrace();
        		    }
        		}
        	});
	    }
}