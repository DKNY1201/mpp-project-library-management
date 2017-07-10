package ui;

import business.Address;
import business.Author;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.rulesets.RuleException;
import ui.rulesets.RuleSet;
import ui.rulesets.RuleSetFactory;

public class NewAuthorWindow extends Stage implements LibWindow {
	public static final NewAuthorWindow INSTANCE = new NewAuthorWindow();

	private TextField firstNameTextField;
	private TextField lastNameTextField;
	private TextField phoneTextField;
	private TextField streetTextField;
	private TextField cityTextField;
	private TextField stateTextField;
	private TextField zipTextField;
	private TextArea bioTextArea;

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private NewAuthorWindow() {
	}

	@Override
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("white-label-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Add new author");
		scenetitle.setId("white-color");
		scenetitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label firstNameLabel = new Label("First name");
		grid.add(firstNameLabel, 0, 1);
		firstNameTextField = new TextField();
		grid.add(firstNameTextField, 1, 1);

		Label lastNameLabel = new Label("Last name");
		grid.add(lastNameLabel, 0, 2);
		lastNameTextField = new TextField();
		grid.add(lastNameTextField, 1, 2);

		Label phoneLabel = new Label("Phone");
		grid.add(phoneLabel, 0, 3);
		phoneTextField = new TextField();
		grid.add(phoneTextField, 1, 3);

		Label streetLabel = new Label("Street");
		grid.add(streetLabel, 0, 4);
		streetTextField = new TextField();
		grid.add(streetTextField, 1, 4);

		Label cityLabel = new Label("City");
		grid.add(cityLabel, 0, 5);
		cityTextField = new TextField();
		grid.add(cityTextField, 1, 5);

		Label stateLabel = new Label("State");
		grid.add(stateLabel, 0, 6);
		stateTextField = new TextField();
		grid.add(stateTextField, 1, 6);

		Label zipLabel = new Label("Zip");
		grid.add(zipLabel, 0, 7);
		zipTextField = new TextField();
		grid.add(zipTextField, 1, 7);

		Label bioLablel = new Label("Biography");
		grid.add(bioLablel, 0, 8);
		bioTextArea = new TextArea();
		grid.add(bioTextArea, 1, 8);

		Button newAuthorBtn = new Button("Save");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(newAuthorBtn);
		grid.add(hbBtn, 1, 9);

		newAuthorBtn.setOnAction((ActionEvent e) -> {
			
			
			try {
				RuleSet addBookRules = RuleSetFactory.getRuleSet(NewAuthorWindow.this);
				addBookRules.applyRules(NewAuthorWindow.this);
				
				AddBookWindow.INSTANCE.addNewAuthorToTable(new Author(getFirstName(), getLastName(), getPhone(),
						new Address(getStreet(), getCity(), getState(), getZip()), getBio()));
				
				Start.hideAllWindows();
				AddBookWindow.INSTANCE.show();
			} catch (RuleException ex) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Incorrect input data");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		});
		
		Button backBtn = new Button("Back");
		backBtn.setOnAction((ActionEvent e) -> {
			Start.hideAllWindows();
			AddBookWindow.INSTANCE.show();;
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 0, 9);

		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("resource/css/library.css").toExternalForm());
		setScene(scene);
	}

	public String getFirstName() {
		return firstNameTextField.getText();
	}

	public String getLastName() {
		return lastNameTextField.getText();
	}

	public String getPhone() {
		return phoneTextField.getText();
	}

	public String getStreet() {
		return streetTextField.getText();
	}

	public String getCity() {
		return cityTextField.getText();
	}

	public String getState() {
		return stateTextField.getText();
	}

	public String getZip() {
		return zipTextField.getText();
	}

	public String getBio() {
		return bioTextArea.getText();
	}
}
