package ui;

import business.ControllerInterface;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CheckoutRecordWindow extends Stage implements LibWindow {
	public static final CheckoutRecordWindow INSTANCE = new CheckoutRecordWindow();

	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private CheckoutRecordWindow() {}



	private TableView<Person> table = new TableView<Person>();
	private final ObservableList<Person> data =
			FXCollections.observableArrayList(
					new Person("Jacob", "Smith", "jacob.smith@example.com"),
					new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
					new Person("Ethan", "Williams", "ethan.williams@example.com"),
					new Person("Emma", "Jones", "emma.jones@example.com"),
					new Person("Michael", "Brown", "michael.brown@example.com")
			);

	public static class Person {

		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;
		private final SimpleStringProperty email;

		private Person(String fName, String lName, String email) {
			this.firstName = new SimpleStringProperty(fName);
			this.lastName = new SimpleStringProperty(lName);
			this.email = new SimpleStringProperty(email);
		}

		public String getFirstName() {
			return firstName.get();
		}

		public void setFirstName(String fName) {
			firstName.set(fName);
		}

		public String getLastName() {
			return lastName.get();
		}

		public void setLastName(String fName) {
			lastName.set(fName);
		}

		public String getEmail() {
			return email.get();
		}

		public void setEmail(String fName) {
			email.set(fName);
		}
	}


	public void init() {
		final Label label = new Label("Checkout Records");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn firstNameCol = new TableColumn("Member name");
		firstNameCol.setMinWidth(100);
		firstNameCol.setCellValueFactory(
				new PropertyValueFactory<Person, String>("firstName"));

		TableColumn lastNameCol = new TableColumn("Book title");
		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(
				new PropertyValueFactory<Person, String>("lastName"));

		TableColumn emailCol = new TableColumn("Copy number");
		emailCol.setMinWidth(200);
		emailCol.setCellValueFactory(
				new PropertyValueFactory<Person, String>("email"));

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

		Button backBtn = new Button("<= Back to Main");
        backBtn.setOnAction(
                (ActionEvent e) -> {
                    Start.hideAllWindows();
                    Start.primStage().show();
                });
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hBack);


		Scene scene = new Scene(new Group());
		((Group) scene.getRoot()).getChildren().addAll(vbox);
		setScene(scene);

//		GridPane grid = new GridPane();
//		grid.setId("top-container");
//		grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));


//        Text scenetitle = new Text("Add new member");
//        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
//        grid.add(scenetitle, 0, 0, 2, 1);
//
//
//		Label memberIDLabel = new Label("Member ID");
//		grid.add(memberIDLabel, 0, 1);
//		TextField memberIDTextField = new TextField();
//		grid.add(memberIDTextField, 1, 1);
//
//		Label firstNameLabel = new Label("First name");
//		grid.add(firstNameLabel, 0, 2);
//		TextField firstNameTextField = new TextField();
//		grid.add(firstNameTextField, 1, 2);
//
//		Label lastNameLabel = new Label("Last name");
//		grid.add(lastNameLabel, 0, 3);
//		TextField lastNameTextField = new TextField();
//		grid.add(lastNameTextField, 1, 3);
//
//		Label streetLabel = new Label("Street");
//		grid.add(streetLabel, 0, 4);
//		TextField streetTextField = new TextField();
//		grid.add(streetTextField, 1, 4);
//
//		Label cityLabel = new Label("City");
//		grid.add(cityLabel, 0, 5);
//		TextField cityTextField = new TextField();
//		grid.add(cityTextField, 1, 5);
//
//		Label stateLabel = new Label("State");
//		grid.add(stateLabel, 0, 6);
//		TextField stateTextField = new TextField();
//		grid.add(stateTextField, 1, 6);
//
//		Label zipLabel = new Label("Zip");
//		grid.add(zipLabel, 0, 7);
//		TextField zipTextField = new TextField();
//		grid.add(zipTextField, 1, 7);
//
//		Label phoneLable = new Label("Phone");
//		grid.add(phoneLable, 0, 8);
//		TextField phoneTextField = new TextField();
//		grid.add(phoneTextField, 1, 8);
//
//		Button newMemberBtn = new Button("Create new member");
//		HBox hbBtn = new HBox(10);
//		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//		hbBtn.getChildren().add(newMemberBtn);
//		grid.add(hbBtn, 1, 9);
//
//		newMemberBtn.setOnAction(
//				(ActionEvent e) -> {
//					try {
//						ControllerInterface c = new SystemController();
//						c.addMember(memberIDTextField.getText(), firstNameTextField.getText(),
//								lastNameTextField.getText(), streetTextField.getText(),
//								cityTextField.getText(), stateTextField.getText(),
//								zipTextField.getText(), phoneTextField.getText());
//					} catch(Exception ex) {
//					}
//				});
//
//
//
//		Button backBtn = new Button("<= Back to Main");
//        backBtn.setOnAction(new EventHandler<ActionEvent>() {
//        	@Override
//        	public void handle(ActionEvent e) {
//        		Start.hideAllWindows();
//        		Start.primStage().show();
//        	}
//        });
//        HBox hBack = new HBox(10);
//        hBack.setAlignment(Pos.BOTTOM_LEFT);
//        hBack.getChildren().add(backBtn);
//        grid.add(hBack, 0, 10);

//		Scene scene = new Scene(new Group());
//		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
//		setScene(scene);
	}
}
