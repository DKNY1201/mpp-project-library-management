package ui;

import business.ControllerInterface;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckoutBook extends Stage implements LibWindow {
	public static final CheckoutBook INSTANCE = new CheckoutBook();

	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private CheckoutBook() {}
	
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Checkout book");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);


		Label memberIDLabel = new Label("Member ID");
		grid.add(memberIDLabel, 0, 1);
		TextField memberIDTextField = new TextField();
		grid.add(memberIDTextField, 1, 1);

		Label isbnLabel = new Label("ISBN number");
		grid.add(isbnLabel, 0, 2);
		TextField isbnTextField = new TextField();
		grid.add(isbnTextField, 1, 2);



		Button newMemberBtn = new Button("Checkout");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(newMemberBtn);
		grid.add(hbBtn, 1, 3);

		newMemberBtn.setOnAction(
				(ActionEvent e) -> {
					try {
						ControllerInterface c = new SystemController();
//						c.addMember(memberIDTextField.getText(), firstNameTextField.getText(),
//								lastNameTextField.getText(), streetTextField.getText(),
//								cityTextField.getText(), stateTextField.getText(),
//								zipTextField.getText(), phoneTextField.getText());
					} catch(Exception ex) {
					}

					System.out.println("New member created!");
				});



		Button backBtn = new Button("<= Back to Main");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.primStage().show();
        	}
        });
        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 4);

		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
