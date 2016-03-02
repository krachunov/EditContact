package com.levins.my.contact.UI;
import java.util.List;

import com.levins.my.contact.ConnectionWithServer;
import com.levins.my.contact.ContactRecord;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class TableViewSample extends Application {
 
    private TableView<ContactRecord> table = new TableView<ContactRecord>();
    

    
    private final ObservableList<ContactRecord> data =
        FXCollections.observableArrayList();
    
    final HBox hb = new HBox();
 
    @Override
    public void start(Stage stage) throws ClassNotFoundException {
    	
    	ConnectionWithServer<ContactRecord> a = new ConnectionWithServer<ContactRecord>("contact");
    	List<ContactRecord> allRecords = a.getAllrecords("Employee");
    	for (ContactRecord contactRecord : allRecords) {
			data.add(contactRecord);
		}
    	
        Scene scene = new Scene(new Group());
        stage.setTitle("Edit Contact");
        stage.setWidth(800);
        stage.setHeight(600);
 
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);

        TableColumn<ContactRecord,String> firstNameCol = new TableColumn<ContactRecord,String>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<ContactRecord, String>("name"));
        
        TableColumn<ContactRecord,String> department = new TableColumn<ContactRecord,String>("department");
        department.setMinWidth(100);
        department.setCellValueFactory(
                new PropertyValueFactory<ContactRecord, String>("department"));
       
 
        TableColumn<ContactRecord,String> phone = new TableColumn<ContactRecord,String>("Phone");
        phone.setMinWidth(100);
        phone.setCellValueFactory(
                new PropertyValueFactory<ContactRecord, String>("phone"));
 
        TableColumn<ContactRecord,String> emailCol = new TableColumn<ContactRecord,String>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<ContactRecord, String>("email"));
        
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
            new EventHandler<CellEditEvent<ContactRecord, String>>() {
                @Override
                public void handle(CellEditEvent<ContactRecord, String> t) {
                    ((ContactRecord) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
                }
            }
        );
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol,department, phone, emailCol);
        
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(phone.getPrefWidth());
        addLastName.setPromptText("Last Name");
        
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");
 
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
//                data.add(new Person(
//                        addFirstName.getText(),
//                        addLastName.getText(),
//                        addEmail.getText()));
//                addFirstName.clear();
//                addLastName.clear();
//                addEmail.clear();
            }
        });
        
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class Person {
 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
 
        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
 
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
} 