package ui;

import business.Address;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
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

	private CheckoutRecordWindow() {
	}

	private ObservableList<LibraryMember> libMemberData = FXCollections.observableArrayList();

	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Checkout Record");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		TableView<LibraryMember> table = new TableView<LibraryMember>();
		TableColumn<LibraryMember, String> firstNameColumn = new TableColumn<LibraryMember, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));
		TableColumn<LibraryMember, String> lastNameColumn = new TableColumn<LibraryMember, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));
		TableColumn<LibraryMember, String> telephoneColumn = new TableColumn<LibraryMember, String>("Telephone");
		telephoneColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("telephone"));
		TableColumn<LibraryMember, CheckoutRecord> checkoutRecordColumn = new TableColumn<LibraryMember, CheckoutRecord>(
				"Checkout Record");
		checkoutRecordColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, CheckoutRecord>("checkoutRecord"));
		table.getColumns().addAll(firstNameColumn, lastNameColumn, telephoneColumn, checkoutRecordColumn);

		table.setMinWidth(870);
		table.setMaxWidth(870);
		table.setMinHeight(80);
		firstNameColumn.setMinWidth(100);
		lastNameColumn.setMinWidth(100);
		telephoneColumn.setMinWidth(100);
		checkoutRecordColumn.setMinWidth(600);

		grid.add(table, 0, 1, 2, 1);

		ControllerInterface c = new SystemController();
		libMemberData.clear();
		libMemberData.addAll(c.getAllMembers());
		table.setItems(libMemberData);

		Button backBtn = new Button("<= Back to Main");
		backBtn.setOnAction((ActionEvent e) -> {
			Start.hideAllWindows();
			Start.primStage().show();
		});

		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 0, 2);

//		final VBox vbox = new VBox();
//		vbox.setSpacing(5);
//		vbox.setPadding(new Insets(10, 0, 0, 10));
//		vbox.getChildren().addAll(table, hBack);
//
//
//		Scene scene = new Scene(new Group());
//		((Group) scene.getRoot()).getChildren().addAll(vbox);
//		setScene(scene);
		
		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);
	}
}
