package ui;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
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

public class PrintMemberCheckoutReport extends Stage implements LibWindow {
	public static final PrintMemberCheckoutReport INSTANCE = new PrintMemberCheckoutReport();
	private ObservableList<LibraryMember> libMemberData = FXCollections.observableArrayList();

	private TextField memberIdTextField;
	private TableView<LibraryMember> table;
	private ControllerInterface controller;
	private TableColumn<LibraryMember, String> firstNameColumn;
	private TableColumn<LibraryMember, String> lastNameColumn;
	private TableColumn<LibraryMember, String> telephoneColumn;
	private TableColumn<LibraryMember, Address> addressColumn;
	
	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private PrintMemberCheckoutReport() {
	}

	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Search library member");
		scenetitle.setId("white-color");
		scenetitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label memberIdLabel = new Label("Member ID");
		memberIdLabel.setId("white-color-label");
		grid.add(memberIdLabel, 0, 2);
		memberIdTextField = new TextField();
		grid.add(memberIdTextField, 1, 2);

		Button searchBtn = new Button("Search");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(searchBtn);
		grid.add(hbBtn, 1, 3);

		table = new TableView<LibraryMember>();
		firstNameColumn = new TableColumn<LibraryMember, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));
		lastNameColumn = new TableColumn<LibraryMember, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));
		telephoneColumn = new TableColumn<LibraryMember, String>("Telephone");
		telephoneColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("telephone"));
		addressColumn = new TableColumn<LibraryMember, Address>("Address");
		addressColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, Address>("address"));

		memberIdTextField.setMinWidth(700);
		table.setMinWidth(800);
		table.setMinHeight(80);
		table.setMaxHeight(80);

		grid.add(table, 0, 4, 2, 1);

		controller = new SystemController();
		searchBtn.setOnAction((ActionEvent e) -> {
			try {
				RuleSet searchLibMemberRules = RuleSetFactory.getRuleSet(PrintMemberCheckoutReport.this);
				searchLibMemberRules.applyRules(PrintMemberCheckoutReport.this);

				LibraryMember member = searchMember(getMemberId());
				displayMemberOnTable(member);
			} catch (RuleException ex) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Incorrect input data");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		});

		Button printCheckoutRecord = new Button("Print Checkout Record");
		printCheckoutRecord.setOnAction((ActionEvent e) -> {
			printMemberCheckoutReport();
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
	
	public String getMemberId(){
		return memberIdTextField.getText();
	}
	
	@SuppressWarnings("unchecked")
	private void displayMemberOnTable(LibraryMember member){
		if (member != null) {
			libMemberData.clear();
			libMemberData.add(member);
			
			table.setItems(libMemberData);
			table.getColumns().clear();
			table.getColumns().addAll(firstNameColumn, lastNameColumn, telephoneColumn, addressColumn);
		}
	}
	
	private LibraryMember searchMember(String memberID){
		return controller.searchMember(memberID);
	}
	
	private void printMemberCheckoutReport(){
		if (libMemberData.size() > 0) {
			LibraryMember libMem = libMemberData.get(0);
			System.out.println(libMem.getCheckoutRecord().printCheckoutRecord());
		}
	}
}
