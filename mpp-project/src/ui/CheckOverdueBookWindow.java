package ui;

import java.time.LocalDate;
import business.ControllerInterface;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.rulesets.RuleException;
import ui.rulesets.RuleSet;
import ui.rulesets.RuleSetFactory;

public class CheckOverdueBookWindow extends Stage implements LibWindow {
	public static final CheckOverdueBookWindow INSTANCE = new CheckOverdueBookWindow();
	private ObservableList<OverdueRecord> OverdueRecords = FXCollections.observableArrayList();

	private TextField isbnTextField;
	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private CheckOverdueBookWindow() {
	}

	@SuppressWarnings("unchecked")
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Check overdue book");
		scenetitle.setId("white-color");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label isbnLabel = new Label("ISBN");
		isbnLabel.setId("white-color-label");
		grid.add(isbnLabel, 0, 2);
		isbnTextField = new TextField();
		grid.add(isbnTextField, 1, 2);

		Button searchBtn = new Button("Search");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(searchBtn);
		grid.add(hbBtn, 1, 3);

		TableView<OverdueRecord> table = new TableView<OverdueRecord>();
		TableColumn<OverdueRecord, String> isbnColumn = new TableColumn<OverdueRecord, String>("ISBN");
		isbnColumn.setCellValueFactory(new PropertyValueFactory<OverdueRecord, String>("isbn"));
		TableColumn<OverdueRecord, String> titleColumn = new TableColumn<OverdueRecord, String>("Book Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<OverdueRecord, String>("title"));
		TableColumn<OverdueRecord, Integer> numCopiesColumn = new TableColumn<OverdueRecord, Integer>(
				"Copy Numbers");
		numCopiesColumn.setCellValueFactory(new PropertyValueFactory<OverdueRecord, Integer>("copyNum"));
		TableColumn<OverdueRecord, String> memberIdColumn = new TableColumn<OverdueRecord, String>("Member ID");
		memberIdColumn.setCellValueFactory(new PropertyValueFactory<OverdueRecord, String>("memberId"));
		TableColumn<OverdueRecord, String> firstNameColumn = new TableColumn<OverdueRecord, String>(
				"First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<OverdueRecord, String>("firstName"));
		TableColumn<OverdueRecord, String> lastNameColumn = new TableColumn<OverdueRecord, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<OverdueRecord, String>("lastName"));
		TableColumn<OverdueRecord, LocalDate> checkoutDateColumn = new TableColumn<OverdueRecord, LocalDate>(
				"Checkout Date");
		checkoutDateColumn.setCellValueFactory(new PropertyValueFactory<OverdueRecord, LocalDate>("checkoutDate"));
		TableColumn<OverdueRecord, LocalDate> dueDateColumn = new TableColumn<OverdueRecord, LocalDate>(
				"Due Date");
		dueDateColumn.setCellValueFactory(new PropertyValueFactory<OverdueRecord, LocalDate>("dueDate"));

		isbnTextField.setMinWidth(750);
		table.setMinWidth(800);
		table.setMinHeight(400);
		table.setMaxHeight(400);

		grid.add(table, 0, 4, 2, 1);

		ControllerInterface c = new SystemController();
		searchBtn.setOnAction((ActionEvent e) -> {
			try {
				RuleSet checkOverDueBookRules = RuleSetFactory.getRuleSet(CheckOverdueBookWindow.this);
				checkOverDueBookRules.applyRules(CheckOverdueBookWindow.this);

				OverdueRecords.addAll(c.getOverdueRecordByISBN(getISBN()));
				if (OverdueRecords != null && !OverdueRecords.isEmpty()) {
					table.setItems(OverdueRecords);
					table.getColumns().addAll(isbnColumn, titleColumn, numCopiesColumn, memberIdColumn, firstNameColumn,
							lastNameColumn, checkoutDateColumn, dueDateColumn);
				}
			} catch (RuleException ex) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
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
		grid.add(hBack, 0, 6, 2, 1);

		Scene scene = new Scene(grid, 900, 600);
		scene.getStylesheets().add(getClass().getResource("resource/css/library.css").toExternalForm());
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
		    public void handle(KeyEvent ke) {
		        if (ke.getCode() == KeyCode.ENTER) {
		        	searchBtn.fire();
		        	ke.consume();
		        }
		    }
		});
		
		setScene(scene);
	}

	public String getISBN() {
		return isbnTextField.getText();
	}

	public static class OverdueRecord {
		private String isbn;
		private String title;
		private int copyNum;
		private String memberId;
		private String firstName;
		private String lastName;
		private LocalDate checkoutDate;
		private LocalDate dueDate;

		public OverdueRecord(String isbn, String title, int copyNum, String memberId, String firstName,
				String lastName, LocalDate checkoutDate, LocalDate dueDate) {
			this.isbn = isbn;
			this.title = title;
			this.copyNum = copyNum;
			this.memberId = memberId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.checkoutDate = checkoutDate;
			this.dueDate = dueDate;
		}

		public String getIsbn() {
			return isbn;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getCopyNum() {
			return copyNum;
		}

		public String getMemberId() {
			return memberId;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public LocalDate getCheckoutDate() {
			return checkoutDate;
		}

		public LocalDate getDueDate() {
			return dueDate;
		}

	}
}
