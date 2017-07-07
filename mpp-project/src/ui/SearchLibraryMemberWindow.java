package ui;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.rulesets.RuleException;
import ui.rulesets.RuleSet;
import ui.rulesets.RuleSetFactory;

public class SearchLibraryMemberWindow extends Stage implements LibWindow {
	public static final SearchLibraryMemberWindow INSTANCE = new SearchLibraryMemberWindow();
	private ObservableList<LibraryMember> libMemberData = FXCollections.observableArrayList();

	private TextField memberIdTextField;
	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private SearchLibraryMemberWindow() {
	}

	@SuppressWarnings("unchecked")
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("while-label-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Search library member");
		scenetitle.setId("while-color");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label memberIdLabel = new Label("Member ID");
		grid.add(memberIdLabel, 0, 2);
		memberIdTextField = new TextField();
		grid.add(memberIdTextField, 1, 2);

		Button searchBtn = new Button("Search");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(searchBtn);
		grid.add(hbBtn, 1, 3);

		TableView<LibraryMember> table = new TableView<LibraryMember>();
		TableColumn<LibraryMember, String> firstNameColumn = new TableColumn<LibraryMember, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));
		TableColumn<LibraryMember, String> lastNameColumn = new TableColumn<LibraryMember, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));
		TableColumn<LibraryMember, String> telephoneColumn = new TableColumn<LibraryMember, String>("Telephone");
		telephoneColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("telephone"));
		TableColumn<LibraryMember, Address> addressColumn = new TableColumn<LibraryMember, Address>("Address");
		addressColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, Address>("address"));

		memberIdTextField.setMinWidth(700);
		table.setMinWidth(800);
		table.setMinHeight(80);
		table.setMaxHeight(80);

		grid.add(table, 0, 4, 2, 1);

		ControllerInterface c = new SystemController();
		searchBtn.setOnAction((ActionEvent e) -> {
			try {
				RuleSet searchLibMemberRules = RuleSetFactory.getRuleSet(SearchLibraryMemberWindow.this);
				searchLibMemberRules.applyRules(SearchLibraryMemberWindow.this);

				LibraryMember member = c.searchMember(getMemberId());
				if (member != null) {
					libMemberData.clear();
					libMemberData.add(member);
					table.setItems(libMemberData);
					table.getColumns().addAll(firstNameColumn, lastNameColumn, telephoneColumn, addressColumn);
				}
			} catch (RuleException ex) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Incorrect input data");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		});

		Button printCheckoutRecord = new Button("Print Checkout Record");
		printCheckoutRecord.setOnAction((ActionEvent e) -> {
			if (libMemberData.size() > 0) {
				LibraryMember libMem = libMemberData.get(0);
				System.out.println(libMem.getCheckoutRecord().printCheckoutRecord());
			}
		});

		HBox hPrint = new HBox(100);
		hPrint.setAlignment(Pos.BOTTOM_RIGHT);
		hPrint.getChildren().add(printCheckoutRecord);
		grid.add(hPrint, 0, 7, 2, 1);

		Button backBtn = new Button("Back to Main");
		backBtn.setOnAction((ActionEvent e) ->  {
			Start.hideAllWindows();
			Start.primStage().show();
		});

		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 0, 10);

		Scene scene = new Scene(grid, 900, 400);
		scene.getStylesheets().add(getClass().getResource("resource/css/library.css").toExternalForm());
		setScene(scene);
	}
	
	public String getMemberId(){
		return memberIdTextField.getText();
	}
}
