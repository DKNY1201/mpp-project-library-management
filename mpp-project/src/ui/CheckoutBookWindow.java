package ui;

import business.CheckoutBookException;
import business.ControllerInterface;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.rulesets.RuleException;
import ui.rulesets.RuleSet;
import ui.rulesets.RuleSetFactory;

public class CheckoutBookWindow extends Stage implements LibWindow {
	public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private CheckoutBookWindow() {
	}

	TextField memberIDTextField;
	TextField isbnTextField;

	public void init() {
		GridPane grid = new GridPane();
		grid.setId("white-label-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Checkout book");
		scenetitle.setId("white-color");
		scenetitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label memberIDLabel = new Label("Member ID");
		grid.add(memberIDLabel, 0, 1);
		memberIDTextField = new TextField();
		grid.add(memberIDTextField, 1, 1);

		Label isbnLabel = new Label("ISBN number");
		grid.add(isbnLabel, 0, 2);
		isbnTextField = new TextField();
		grid.add(isbnTextField, 1, 2);

		Button checkoutBookBtn = new Button("Checkout");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(checkoutBookBtn);
		grid.add(hbBtn, 1, 3);

		checkoutBookBtn.setOnAction((ActionEvent e) -> {
			try {
				RuleSet checkoutBookRules = RuleSetFactory.getRuleSet(CheckoutBookWindow.this);
				checkoutBookRules.applyRules(CheckoutBookWindow.this);

				ControllerInterface c = new SystemController();
				c.checkoutBook(memberIDTextField.getText(), isbnTextField.getText());
				
				Alert alert = new Alert(AlertType.NONE, "Checkout book successful!", ButtonType.OK);
				alert.showAndWait();
				if (alert.getResult() == ButtonType.OK) {
					Start.hideAllWindows();
					if (!CheckoutRecordWindow.INSTANCE.isInitialized()) {
						CheckoutRecordWindow.INSTANCE.init();
					}

					CheckoutRecordWindow.INSTANCE.show();
				}
				
			} catch (CheckoutBookException ex) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Database issue");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			} catch (RuleException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Incorrect input data");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		});

		Button backBtn = new Button("Back to Main");
		backBtn.setOnAction((ActionEvent e) -> {
			Start.hideAllWindows();
			Start.primStage().show();
		});

		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 0, 4);

		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("resource/css/library.css").toExternalForm());
		setScene(scene);
	}

	public String getMemberIDValue() {
		return this.memberIDTextField.getText();
	}

	public String getIsbnValue() {
		return this.isbnTextField.getText();
	}
}
