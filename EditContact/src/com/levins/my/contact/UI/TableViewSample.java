package com.levins.my.contact.UI;

import java.util.List;

import com.levins.my.contact.ConnectionWithServer;
import com.levins.my.contact.ContactRecord;
import com.levins.my.contact.Employee;

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

	private final ObservableList<ContactRecord> data = FXCollections
			.observableArrayList();

	final HBox hb = new HBox();

	@SuppressWarnings({ "unchecked", "unchecked" })
	@Override
	public void start(Stage stage) throws ClassNotFoundException {

		ConnectionWithServer<ContactRecord> a = new ConnectionWithServer<ContactRecord>(
				"contact");
		List<ContactRecord> allRecords = a.getAllrecords("Employee");
		for (ContactRecord contactRecord : allRecords) {
			data.add(contactRecord);
		}

		Scene scene = new Scene(new Group());
		stage.setTitle("Edit Contact");
		stage.setWidth(1024);
		stage.setHeight(960);

		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn<ContactRecord, String> firstNameCol = new TableColumn<ContactRecord, String>(
				"Name");
		firstNameCol.setMinWidth(150);
		firstNameCol
				.setCellValueFactory(new PropertyValueFactory<ContactRecord, String>(
						"name"));

		TableColumn<ContactRecord, String> department = new TableColumn<ContactRecord, String>(
				"department");
		department.setMinWidth(200);
		department
				.setCellValueFactory(new PropertyValueFactory<ContactRecord, String>(
						"department"));

		TableColumn<ContactRecord, String> director = new TableColumn<ContactRecord, String>(
				"Director");
		director.setMinWidth(150);
		director.setCellValueFactory(new PropertyValueFactory<ContactRecord, String>(
				"director"));

		TableColumn<ContactRecord, String> post = new TableColumn<ContactRecord, String>(
				"Post");
		post.setMinWidth(100);
		post.setCellValueFactory(new PropertyValueFactory<ContactRecord, String>(
				"post"));

		TableColumn<ContactRecord, String> internal = new TableColumn<ContactRecord, String>(
				"Internal phone");
		internal.setMinWidth(10);
		internal.setCellValueFactory(new PropertyValueFactory<ContactRecord, String>(
				"internal"));

		TableColumn<ContactRecord, String> phone = new TableColumn<ContactRecord, String>(
				"Phone");
		phone.setMinWidth(100);
		phone.setCellValueFactory(new PropertyValueFactory<ContactRecord, String>(
				"phone"));

		TableColumn<ContactRecord, String> emailCol = new TableColumn<ContactRecord, String>(
				"Email");
		emailCol.setMinWidth(200);
		emailCol.setCellValueFactory(new PropertyValueFactory<ContactRecord, String>(
				"email"));

		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		emailCol.setOnEditCommit(new EventHandler<CellEditEvent<ContactRecord, String>>() {
			@Override
			public void handle(CellEditEvent<ContactRecord, String> t) {
				((ContactRecord) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setEmail(t
						.getNewValue());
			}
		});

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, post, department, director,
				internal, phone, emailCol);

		final TextField addName = new TextField();
		addName.setPromptText("Name");
		addName.setMaxWidth(firstNameCol.getPrefWidth());

		final TextField addPosition = new TextField();
		addPosition.setMaxWidth(phone.getPrefWidth());
		addPosition.setPromptText("Position");

		final TextField addDepart = new TextField();
		addDepart.setMaxWidth(emailCol.getPrefWidth());
		addDepart.setPromptText("Department");

		final TextField addDirektor = new TextField();
		addDirektor.setMaxWidth(emailCol.getPrefWidth());
		addDirektor.setPromptText("Direktor");

		final TextField addInternal = new TextField();
		addInternal.setMaxWidth(emailCol.getPrefWidth());
		addInternal.setPromptText("Internal");

		final TextField addPhone = new TextField();
		addPhone.setMaxWidth(emailCol.getPrefWidth());
		addPhone.setPromptText("Phone");

		final TextField addEmail = new TextField();
		addEmail.setMaxWidth(emailCol.getPrefWidth());
		addEmail.setPromptText("Email");

		final TextField addUser = new TextField();
		addUser.setMaxWidth(emailCol.getPrefWidth());
		addUser.setPromptText("User");

		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Employee newEmployee = new Employee(addName.getText(),
						addPosition.getText(), addDirektor.getText(), addDepart
								.getText(), Integer.valueOf(addInternal
								.getText()), addPhone.getText(), addEmail
								.getText(), addUser.getText());
				data.add(newEmployee);
				a.insertRecord(newEmployee);

				addName.clear();
				addPosition.clear();
				addDirektor.clear();
				addDepart.clear();
				addInternal.clear();
				addPhone.clear();
				addEmail.clear();
				addUser.clear();

			}
		});

		hb.getChildren().addAll(addName, addPosition, addDepart, addDirektor,
				addInternal, addPhone, addEmail, addUser, addButton);
		hb.setSpacing(20);

		
		final Button removeButton = new Button("Remove");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {


			}
		});
		
		final HBox hb2 = new HBox();
		hb2.getChildren().addAll(removeButton);
		hb2.scaleXProperty();
		
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 100, 10));
		vbox.getChildren().addAll(label, table, hb,hb2);
		vbox.getChildren().get(3).setLayoutX(1000);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}