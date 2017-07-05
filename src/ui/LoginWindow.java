package ui;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow extends Stage implements LibWindow {
	public static final LoginWindow INSTANCE = new LoginWindow();
	public static Auth currentAuth = null;

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private Text messageBar = new Text();

	public void clear() {
		messageBar.setText("");
	}

	private LoginWindow() {
	}

	public void init() {
		setTitle("Login");
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Login");
		// setting id for CSS styling
		scenetitle.setId("login-text");
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		HBox messageBox = new HBox(10);
		messageBox.setAlignment(Pos.BOTTOM_RIGHT);
		messageBox.getChildren().add(messageBar);
		messageBar.setId("actiontarget");
		grid.add(messageBox, 1, 6);

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				try {
					ControllerInterface c = new SystemController();
					c.login(userTextField.getText().trim(), pwBox.getText().trim());
					messageBar.setFill(Start.Colors.green);
					messageBar.setText("Login successful");
					Start.updateMenuByAuth(SystemController.currentAuth);
					Start.hideAllWindows();
					Start.primStage().show();
				} catch (LoginException ex) {
					messageBar.setFill(Start.Colors.red);
					messageBar.setText("Error! " + ex.getMessage());
				}
			}
		});

		Button backBtn = new Button("<= Back to Main");
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.updateMenuByAuth(SystemController.currentAuth);
				Start.hideAllWindows();
				Start.primStage().show();
			}
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 0, 7);

		Scene scene = new Scene(grid);
		setScene(scene);

		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		show();
	}

}
