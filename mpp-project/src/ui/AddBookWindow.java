package ui;

import java.util.ArrayList;
import java.util.List;

import business.AddBookException;
import business.Author;
import business.ControllerInterface;
import business.SystemController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.rulesets.RuleException;
import ui.rulesets.RuleSet;
import ui.rulesets.RuleSetFactory;

public class AddBookWindow extends Stage implements LibWindow {

	public static final AddBookWindow INSTANCE = new AddBookWindow();

	private TextField isbnTextField;
	private TextField titleTextField;
	private TextField numOfCopiesTextField;
	private ComboBox<Integer> maxCheckoutLenComboBox;
	private ObservableList<Integer> maxCheckoutLenOptions = FXCollections.observableArrayList(7, 21);
	private TableView<Author> tableView;
	private List<Author> selectedAuthors;
	private List<Author> listAuthors;
	private ControllerInterface controler;

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private AddBookWindow() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Add new book");
		scenetitle.setId("white-color");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label isbnLabel = new Label("ISBN");
		isbnLabel.setId("white-color-label");
		grid.add(isbnLabel, 0, 1);
		isbnTextField = new TextField();
		grid.add(isbnTextField, 1, 1);

		Label titleLabel = new Label("Title");
		titleLabel.setId("white-color-label");
		grid.add(titleLabel, 0, 2);
		titleTextField = new TextField();
		grid.add(titleTextField, 1, 2);

		Label numOfCopiesLabel = new Label("Number of copies");
		numOfCopiesLabel.setId("white-color-label");
		grid.add(numOfCopiesLabel, 0, 3);
		numOfCopiesTextField = new TextField();
		grid.add(numOfCopiesTextField, 1, 3);

		Label maxCheckoutLenLabel = new Label("Maximum checkout lenght");
		maxCheckoutLenLabel.setId("white-color-label");
		grid.add(maxCheckoutLenLabel, 0, 4);
		maxCheckoutLenComboBox = new ComboBox<>(maxCheckoutLenOptions);
		maxCheckoutLenComboBox.getSelectionModel().select(1);
		grid.add(maxCheckoutLenComboBox, 1, 4);

		controler = new SystemController();
		listAuthors = controler.getAllAuthors();
		selectedAuthors = new ArrayList<Author>();
		
		tableView = new TableView<Author>();
		TableColumn<Author, String> firstNameColumn = new TableColumn<Author, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Author, String>("firstName"));
		TableColumn<Author, String> lastNameColumn = new TableColumn<Author, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Author, String>("lastName"));
		TableColumn<Author, String> bioColumn = new TableColumn<Author, String>("Biography");
		bioColumn.setCellValueFactory(new PropertyValueFactory<Author, String>("bio"));
		TableColumn<Author,Boolean> selectColumn = new TableColumn<Author,Boolean>("Select");
		
		selectColumn.setCellFactory(col -> {
            CheckBoxTableCell<Author, Boolean> cell = new CheckBoxTableCell<>(index -> {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) -> {
					Author author = tableView.getItems().get(index);
					if (isNowSelected){
						selectedAuthors.add(author);
					}else{
						selectedAuthors.remove(author);
					}
                });
                return observable ;
            });
            return cell ;
        });
		
		tableView.getItems().setAll(listAuthors);
		tableView.getColumns().addAll(selectColumn,firstNameColumn,lastNameColumn,bioColumn);
		tableView.setMinWidth(600);
		tableView.setEditable(true);
		
		Label authorsLabel = new Label("Authors");
		authorsLabel.setId("white-color-label");
		grid.add(authorsLabel, 0, 5);
		grid.add(tableView, 1, 5);
		
		Button newAuthorBtn = new Button("New Author");
		HBox hbNewAuthorBtn = new HBox(10);
		hbNewAuthorBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbNewAuthorBtn.getChildren().add(newAuthorBtn);
		grid.add(hbNewAuthorBtn, 1, 6);
		
		newAuthorBtn.setOnAction((ActionEvent e) -> {
			Start.hideAllWindows();
			if (!NewAuthorWindow.INSTANCE.isInitialized()) {
				NewAuthorWindow.INSTANCE.init();
			}
			NewAuthorWindow.INSTANCE.show();
		});

		Button newMemberBtn = new Button("Save");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(newMemberBtn);
		grid.add(hbBtn, 1, 9);

		newMemberBtn.setOnAction((ActionEvent e) -> {
			addBook();
		});

		Button backBtn = new Button("Back to Main");
		backBtn.setOnAction((ActionEvent e) -> {
			Start.hideAllWindows();
			Start.primStage().show();
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 0, 10);

		Scene scene = new Scene(grid, 1000, 1000);
		scene.getStylesheets().add(getClass().getResource("resource/css/library.css").toExternalForm());
		setScene(scene);
	}

	public void addNewAuthorToTable(Author author) {
		listAuthors.add(author);
		tableView.getItems().add(author);
	}

	public String getISBN() {
		return isbnTextField.getText();
	}

	public String getBookTitle() {
		return titleTextField.getText();
	}

	public int getMaxCheckoutLenght() {
		return maxCheckoutLenComboBox.getValue();
	}

	public String getNumberOfCopies() {
		return numOfCopiesTextField.getText();
	}

	public Boolean isNoAuthorSelected() {
		if (selectedAuthors == null)
			return true;
		return selectedAuthors.isEmpty();
	}
	
	public void clearAllField(){
		isbnTextField.setText("");
		titleTextField.setText("");
		numOfCopiesTextField.setText("");
		selectedAuthors.clear();
		tableView.refresh();
	}
	
	public void showAllBooksWindow() {
		Start.hideAllWindows();
		if (!AllBooksWindow.INSTANCE.isInitialized()) {
			AllBooksWindow.INSTANCE.init();
		}

		AllBooksWindow.INSTANCE.show();
	}
	
	private void addBook(){
		try {
			RuleSet addBookRules = RuleSetFactory.getRuleSet(AddBookWindow.this);
			addBookRules.applyRules(AddBookWindow.this);

			controler.addBook(getISBN(), getBookTitle(), getMaxCheckoutLenght(),
					Integer.parseInt(getNumberOfCopies()), selectedAuthors);
			
			Alert alert = new Alert(AlertType.NONE, "Add new book successful!\nDo you want to add more book?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				clearAllField();
			}else{
				showAllBooksWindow();
			}
		} catch (AddBookException ex) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Database issue");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		} catch (RuleException ex) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Incorrect input data");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
}