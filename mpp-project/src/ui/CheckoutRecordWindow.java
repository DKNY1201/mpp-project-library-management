package ui;

import java.time.LocalDate;

import business.BookCopy;
import business.ControllerInterface;
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

	private ObservableList<CheckoutRecordAndLibraryMember> checkoutRecordData = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Checkout Record");
		scenetitle.setId("while-color");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		TableView<CheckoutRecordAndLibraryMember> table = new TableView<CheckoutRecordAndLibraryMember>();
		
		ControllerInterface c = new SystemController();
		checkoutRecordData.clear();
		checkoutRecordData.addAll(c.getAllCheckoutRecordEntryAndLibraryMember());
		table.setItems(checkoutRecordData);
		
		TableColumn<CheckoutRecordAndLibraryMember, String> bookCopyColumn = new TableColumn<CheckoutRecordAndLibraryMember, String>("Book Copy");
		bookCopyColumn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordAndLibraryMember, String>("bookCopy"));
		TableColumn<CheckoutRecordAndLibraryMember, String> checkoutDateColumn = new TableColumn<CheckoutRecordAndLibraryMember, String>("Checkout Date");
		checkoutDateColumn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordAndLibraryMember, String>("checkoutDate"));
		TableColumn<CheckoutRecordAndLibraryMember, String> dueDateColumn = new TableColumn<CheckoutRecordAndLibraryMember, String>("Due Date");
		dueDateColumn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordAndLibraryMember, String>("dueDate"));
		TableColumn<CheckoutRecordAndLibraryMember, String> firstNameColumn = new TableColumn<CheckoutRecordAndLibraryMember, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordAndLibraryMember, String>("firstName"));
		TableColumn<CheckoutRecordAndLibraryMember, String> lastNameColumn = new TableColumn<CheckoutRecordAndLibraryMember, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordAndLibraryMember, String>("lastName"));
		table.getColumns().addAll(bookCopyColumn, checkoutDateColumn, dueDateColumn, firstNameColumn, lastNameColumn);

		table.setMinWidth(870);
		table.setMaxWidth(870);

		grid.add(table, 0, 1, 2, 1);

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
		scene.getStylesheets().add(getClass().getResource("resource/css/library.css").toExternalForm());
		setScene(scene);
	}
	
	public static class CheckoutRecordAndLibraryMember {
		private LocalDate checkoutDate;
	    private LocalDate dueDate;
	    private BookCopy bookCopy;
	    private String firstName;
	    private String lastName;
		public CheckoutRecordAndLibraryMember(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy,
				String firstName, String lastName) {
			this.checkoutDate = checkoutDate;
			this.dueDate = dueDate;
			this.bookCopy = bookCopy;
			this.firstName = firstName;
			this.lastName = lastName;
		}
		public LocalDate getCheckoutDate() {
			return checkoutDate;
		}
		public LocalDate getDueDate() {
			return dueDate;
		}
		public BookCopy getBookCopy() {
			return bookCopy;
		}
		public String getFirstName() {
			return firstName;
		}
		public String getLastName() {
			return lastName;
		}
	}
}
