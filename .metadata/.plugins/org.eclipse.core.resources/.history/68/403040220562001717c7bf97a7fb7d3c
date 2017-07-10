package ui;

import business.Book;
import business.ControllerInterface;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AllBooksWindow extends Stage implements LibWindow {
	public static final AllBooksWindow INSTANCE = new AllBooksWindow();
	
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private AllBooksWindow() {}
	
	private ObservableList<Book> bookData = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("All books");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		TableView<Book> table = new TableView<Book>();
		TableColumn<Book, String> isbnColumn = new TableColumn<Book, String>("ISBN");
		isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		TableColumn<Book, String> titleColumn = new TableColumn<Book, String>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		TableColumn<Book, String> mclColumn = new TableColumn<Book, String>("Max Checkout Length");
		mclColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("maxCheckoutLength"));
		table.getColumns().addAll(isbnColumn, titleColumn, mclColumn);

		table.setMinWidth(425);

		grid.add(table, 0, 1);

		ControllerInterface c = new SystemController();
		bookData.clear();
		bookData.addAll(c.getAllBooks());
		table.setItems(bookData);

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
