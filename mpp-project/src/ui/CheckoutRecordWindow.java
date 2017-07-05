package ui;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

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

	private ObservableList<CheckoutRecordEntry> checkoutRecordData = FXCollections.observableArrayList();

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

		TableView<CheckoutRecordEntry> table = new TableView<CheckoutRecordEntry>();
		TableColumn<CheckoutRecordEntry, String> bookCopyColumn = new TableColumn<CheckoutRecordEntry, String>("Book Copy");
		bookCopyColumn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("bookCopy"));
		TableColumn<CheckoutRecordEntry, String> checkoutDateColumn = new TableColumn<CheckoutRecordEntry, String>("Checkout Date");
		checkoutDateColumn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("checkoutDate"));
		TableColumn<CheckoutRecordEntry, String> dueDateColumn = new TableColumn<CheckoutRecordEntry, String>("Due Date");
		dueDateColumn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("dueDate"));
		table.getColumns().addAll(bookCopyColumn, checkoutDateColumn, dueDateColumn);

		table.setMinWidth(870);
		table.setMaxWidth(870);
		table.setMinHeight(80);

		grid.add(table, 0, 1, 2, 1);

		ControllerInterface c = new SystemController();
		checkoutRecordData.clear();
		checkoutRecordData.addAll(c.getAllCheckoutRecordEntries());
		table.setItems(checkoutRecordData);

		Button backBtn = new Button("<= Back to Main");
		backBtn.setOnAction((ActionEvent e) -> {
			Start.hideAllWindows();
			Start.primStage().show();
		});

		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 0, 2);

		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);
	}
}
