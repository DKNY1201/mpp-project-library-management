package ui;

import java.util.ArrayList;
import java.util.List;

import business.Author;
import business.ControllerInterface;
import business.SystemController;
import dataaccess.DataAccessFacade;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddBookWindow extends Stage implements LibWindow {

	public static final AddBookWindow INSTANCE = new AddBookWindow();

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private AddBookWindow() {
	}

	@Override
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Add new book");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
		grid.add(scenetitle, 0, 0, 2, 1);

		Label isbnLabel = new Label("ISBN");
		grid.add(isbnLabel, 0, 1);
		TextField isbnTextField = new TextField();
		grid.add(isbnTextField, 1, 1);

		Label titleLabel = new Label("Title");
		grid.add(titleLabel, 0, 2);
		TextField titleTextField = new TextField();
		grid.add(titleTextField, 1, 2);

		Label maxCheckoutLenLabel = new Label("Maximum checkout lenght");
		grid.add(maxCheckoutLenLabel, 0, 3);
		TextField maxCheckoutLenTextField = new TextField();
		grid.add(maxCheckoutLenTextField, 1, 3);

		Label numOfCopiesLabel = new Label("Number of copies");
		grid.add(numOfCopiesLabel, 0, 4);
		TextField numOfCopiesTextField = new TextField();
		grid.add(numOfCopiesTextField, 1, 4);

		ListView<Author> listView = new ListView<>();
		DataAccessFacade daf = new DataAccessFacade();
		List<Author> authors = daf.getAllAuthors();
		for (Author author : authors) {
			listView.getItems().add(author);
		}

		List<Author> selectedAuthors = new ArrayList<Author>();
		listView.setCellFactory(CheckBoxListCell.forListView(new Callback<Author, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(Author item) {
				BooleanProperty observable = new SimpleBooleanProperty();
				observable.addListener((obs, wasSelected, isNowSelected) -> {
					if (isNowSelected)
						selectedAuthors.add(item);
					else
						selectedAuthors.remove(item);
				});
				return observable;
			}
		}));

		listView.setMinWidth(400);

		Label authorsLabel = new Label("Authors");
		grid.add(authorsLabel, 0, 5);
		grid.add(listView, 1, 5);

		Button newMemberBtn = new Button("Add new book");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(newMemberBtn);
		grid.add(hbBtn, 1, 9);

		newMemberBtn.setOnAction((ActionEvent e) -> {
			try {
				ControllerInterface c = new SystemController();
				c.addBook(isbnTextField.getText(), titleTextField.getText(),
						Integer.parseInt(maxCheckoutLenTextField.getText()),
						Integer.parseInt(numOfCopiesTextField.getText()), selectedAuthors);
			} catch (Exception ex) {
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

		Scene scene = new Scene(grid, 700, 1000);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);
	}

}
