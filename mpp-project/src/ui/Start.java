package ui;

import java.util.Collections;
import java.util.List;

import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Start extends Application {
	private static MenuBar mainMenu;
	private static Menu optionsMenu;
	private static MenuItem bookIds;
	private static MenuItem memberIds;
	private static MenuItem addNewMember;
	private static MenuItem checkoutBook;
    private static MenuItem checkoutRecords;
    private static MenuItem addCopyBook;
    private static MenuItem addNewBook;
    private static MenuItem searchLibraryMember;
	private static MenuItem login;
	private static MenuItem logout;
	private static Button btnLogin;
	
    public static void main(String[] args) {
        launch(args);
    }

    private static Stage primStage = null;

    public static Stage primStage() {
        return primStage;
    }

    public static class Colors {
        static Color green = Color.web("#034220");
        static Color red = Color.FIREBRICK;
    }

    private static Stage[] allWindows = {
            LoginWindow.INSTANCE,
            AllMembersWindow.INSTANCE,
            AllBooksWindow.INSTANCE,
            NewMemberWindow.INSTANCE,
            CheckoutBookWindow.INSTANCE,
            CheckoutRecordWindow.INSTANCE,
            AddCopyBookWindow.INSTANCE,
            AddBookWindow.INSTANCE,
            SearchLibraryMemberWindow.INSTANCE
    };

    public static void hideAllWindows() {
        primStage.hide();
        for (Stage st : allWindows) {
            st.hide();
        }
    }
    
    public static void updateMenuByAuth(Auth auth){
    	switch (SystemController.currentAuth) {
		case ADMIN:
			btnLogin.setText("Logout");
			optionsMenu.getItems().clear();
			optionsMenu.getItems().addAll(bookIds, memberIds, addNewMember, addCopyBook, addNewBook);
			mainMenu.getMenus().addAll(optionsMenu);
			Start.hideAllWindows();
			Start.primStage().show();
			break;
		case LIBRARIAN:
			btnLogin.setText("Logout");
			optionsMenu.getItems().clear();
			optionsMenu.getItems().addAll(bookIds, memberIds, checkoutBook, checkoutRecords, searchLibraryMember);
			mainMenu.getMenus().addAll(optionsMenu);
			Start.hideAllWindows();
			Start.primStage().show();
			break;
		case BOTH:
			btnLogin.setText("Logout");
			optionsMenu.getItems().clear();
			optionsMenu.getItems().addAll(bookIds, memberIds, addNewMember, addCopyBook, addNewBook, checkoutBook, checkoutRecords, searchLibraryMember);
			mainMenu.getMenus().addAll(optionsMenu);
			Start.hideAllWindows();
			Start.primStage().show();
		default:
			break;
		}
    }


    @Override
    public void start(Stage primaryStage) {
        primStage = primaryStage;
        primaryStage.setTitle("Library System");

        VBox topContainer = new VBox();
        topContainer.setId("top-container");
        mainMenu = new MenuBar();
        VBox imageHolder = new VBox();
        Image image = new Image("ui/library.jpg", 400, 300, false, false);

        // simply displays in ImageView the image as is
        ImageView iv = new ImageView();
        iv.setImage(image);
        imageHolder.getChildren().add(iv);
        imageHolder.setAlignment(Pos.CENTER);
        HBox splashBox = new HBox();
        Label splashLabel = new Label("The Library System");
        splashLabel.setFont(Font.font("Trajan Pro", FontWeight.BOLD, 30));
        splashBox.getChildren().add(splashLabel);
        splashBox.setAlignment(Pos.CENTER);
        
        btnLogin = new Button("Login");
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (SystemController.currentAuth == Auth.UNAUTHENTICATED){
                	hideAllWindows();
                	if (!LoginWindow.INSTANCE.isInitialized()) {
                        LoginWindow.INSTANCE.init();
                    }
                    LoginWindow.INSTANCE.clear();
                    LoginWindow.INSTANCE.show();
                }else{
                	Alert alert = new Alert(AlertType.NONE, "Do you want to logout?", ButtonType.YES, ButtonType.NO);
                	alert.showAndWait();

                	if (alert.getResult() == ButtonType.YES) {
                		SystemController.currentAuth = Auth.UNAUTHENTICATED;
                    	optionsMenu.getItems().clear();
                    	optionsMenu.getItems().addAll(login);
                    	mainMenu.getMenus().clear();
                    	btnLogin.setText("Login");
                	}
                }
            }
        });
        
        HBox hbox = new HBox(mainMenu, btnLogin);
        HBox.setHgrow(mainMenu, Priority.ALWAYS);
        HBox.setHgrow(btnLogin, Priority.NEVER);
        
        topContainer.getChildren().add(hbox);
        topContainer.getChildren().add(splashBox);
        topContainer.getChildren().add(imageHolder);
        
        optionsMenu = new Menu("Menu");
        
        login = new MenuItem("Login");
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                hideAllWindows();
                if (!LoginWindow.INSTANCE.isInitialized()) {
                    LoginWindow.INSTANCE.init();
                }
                LoginWindow.INSTANCE.clear();
                LoginWindow.INSTANCE.show();
            }
        });
        
        logout = new MenuItem("Logout");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	SystemController.currentAuth = Auth.UNAUTHENTICATED;
            	optionsMenu.getItems().clear();
            	optionsMenu.getItems().addAll(login);
            }
        });

        bookIds = new MenuItem("All Book Ids");
        bookIds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                hideAllWindows();
                if (!AllBooksWindow.INSTANCE.isInitialized()) {
                    AllBooksWindow.INSTANCE.init();
                }
                ControllerInterface ci = new SystemController();
                List<String> ids = ci.allBookIds();
                Collections.sort(ids);
                StringBuilder sb = new StringBuilder();
                for (String s : ids) {
                    sb.append(s + "\n");
                }
                AllBooksWindow.INSTANCE.setData(sb.toString());
                AllBooksWindow.INSTANCE.show();
            }
        });

        memberIds = new MenuItem("All Member Ids");
        memberIds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                hideAllWindows();
                if (!AllMembersWindow.INSTANCE.isInitialized()) {
                    AllMembersWindow.INSTANCE.init();
                }
                ControllerInterface ci = new SystemController();
                List<String> ids = ci.allMemberIds();
                Collections.sort(ids);
                System.out.println(ids);
                StringBuilder sb = new StringBuilder();
                for (String s : ids) {
                    sb.append(s + "\n");
                }
                System.out.println(sb.toString());
                AllMembersWindow.INSTANCE.setData(sb.toString());
                AllMembersWindow.INSTANCE.show();
            }
        });

        addNewMember = new MenuItem("Add new member");
        addNewMember.setOnAction(
                (ActionEvent e) -> {
                    hideAllWindows();
                    if (!NewMemberWindow.INSTANCE.isInitialized()) {
                        NewMemberWindow.INSTANCE.init();
                    }

                    NewMemberWindow.INSTANCE.show();
                });

        checkoutBook = new MenuItem("Checkout book");
        checkoutBook.setOnAction(
                (ActionEvent e) -> {
                    hideAllWindows();
                    if (!CheckoutBookWindow.INSTANCE.isInitialized()) {
                        CheckoutBookWindow.INSTANCE.init();
                    }

                    CheckoutBookWindow.INSTANCE.show();
                });

        checkoutRecords = new MenuItem("Checkout Records");
        checkoutRecords.setOnAction(
                (ActionEvent e) -> {
                    hideAllWindows();
                    if (!CheckoutRecordWindow.INSTANCE.isInitialized()) {
                        CheckoutRecordWindow.INSTANCE.init();
                    }

                    CheckoutRecordWindow.INSTANCE.show();
                });

        addCopyBook = new MenuItem("Add copy book");
        addCopyBook.setOnAction(
                (ActionEvent e) -> {
                    hideAllWindows();
                    if (!AddCopyBookWindow.INSTANCE.isInitialized()) {
                        AddCopyBookWindow.INSTANCE.init();
                    }

                    AddCopyBookWindow.INSTANCE.show();
                });

        addNewBook = new MenuItem("Add new book");
        addNewBook.setOnAction(
                (ActionEvent e) -> {
                    hideAllWindows();
                    if (!AddBookWindow.INSTANCE.isInitialized()) {
                    	AddBookWindow.INSTANCE.init();
                    }

                    AddBookWindow.INSTANCE.show();
                });
        
        searchLibraryMember = new MenuItem("Search Library Member");
        searchLibraryMember.setOnAction(
                (ActionEvent e) -> {
                    hideAllWindows();
                    if (!SearchLibraryMemberWindow.INSTANCE.isInitialized()) {
                    	SearchLibraryMemberWindow.INSTANCE.init();
                    }

                    SearchLibraryMemberWindow.INSTANCE.show();
                });
        
        Scene scene = new Scene(topContainer, 420, 375);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        primaryStage.show();
    }

}