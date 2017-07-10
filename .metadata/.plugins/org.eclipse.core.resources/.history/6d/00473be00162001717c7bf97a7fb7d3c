package ui;

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

public class NewAuthorWindow extends Stage implements LibWindow {
	public static final NewAuthorWindow INSTANCE = new NewAuthorWindow();

	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private NewAuthorWindow() {}
	
	@Override
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add new author");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);


        Label firstNameLabel = new Label("First name");
		grid.add(firstNameLabel, 0, 1);
		TextField firstNameTextField = new TextField();
		grid.add(firstNameTextField, 1, 1);

		Label lastNameLabel = new Label("Last name");
		grid.add(lastNameLabel, 0, 2);
		TextField lastNameTextField = new TextField();
		grid.add(lastNameTextField, 1, 2);

		Label phoneLabel = new Label("Phone");
		grid.add(phoneLabel, 0, 3);
		TextField phoneTextField = new TextField();
		grid.add(phoneTextField, 1, 3);

		Label streetLabel = new Label("Street");
		grid.add(streetLabel, 0, 4);
		TextField streetTextField = new TextField();
		grid.add(streetTextField, 1, 4);

		Label cityLabel = new Label("City");
		grid.add(cityLabel, 0, 5);
		TextField cityTextField = new TextField();
		grid.add(cityTextField, 1, 5);

		Label stateLabel = new Label("State");
		grid.add(stateLabel, 0, 6);
		TextField stateTextField = new TextField();
		grid.add(stateTextField, 1, 6);

		Label zipLabel = new Label("Zip");
		grid.add(zipLabel, 0, 7);
		TextField zipTextField = new TextField();
		grid.add(zipTextField, 1, 7);

		Label bioLablel = new Label("Bio");
		grid.add(bioLablel, 0, 8);
		TextField bioTextField = new TextField();
		grid.add(bioTextField, 1, 8);

		Button newMemberBtn = new Button("Add new book");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(newMemberBtn);
		grid.add(hbBtn, 1, 9);

		newMemberBtn.setOnAction(
				(ActionEvent e) -> {
					try {
						
					} catch(Exception ex) {
					}
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
        grid.add(hBack, 0, 10);

		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
