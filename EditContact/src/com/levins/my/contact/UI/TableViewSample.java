package com.levins.my.contact.UI;

import java.util.List;
import java.util.Optional;

import com.levins.my.contact.ConnectionWithServer;
import com.levins.my.contact.ContactRecord;
import com.levins.my.contact.Employee;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

	@SuppressWarnings({ "unchecked" })
	@Override
	public void start(Stage stage) throws ClassNotFoundException {

		ConnectionWithServer<ContactRecord> recordFromServer = new ConnectionWithServer<ContactRecord>(
				"contact");
		List<ContactRecord> allRecords = recordFromServer
				.getAllrecords("Employee");
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
				checkFieldIsEmpty(addName, addPosition, addDepart, addDirektor,
						addInternal, addPhone, addEmail, addUser);

				createnewRecord(recordFromServer, addName, addPosition,
						addDepart, addDirektor, addInternal, addPhone,
						addEmail, addUser);

			}

			private void createnewRecord(ConnectionWithServer<ContactRecord> a,
					final TextField addName, final TextField addPosition,
					final TextField addDepart, final TextField addDirektor,
					final TextField addInternal, final TextField addPhone,
					final TextField addEmail, final TextField addUser) {
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

			private void checkFieldIsEmpty(final TextField addName,
					final TextField addPosition, final TextField addDepart,
					final TextField addDirektor, final TextField addInternal,
					final TextField addPhone, final TextField addEmail,
					final TextField addUser) {
				String regEx = "^\\s*$";

				if (addName.getText().equals(regEx)) {
					alertMessage(addName);
				} else if (addPosition.getText().matches(regEx)) {
					alertMessage(addPosition);
				} else if (addDepart.getText().matches(regEx)) {
					alertMessage(addDepart);
				} else if (addDirektor.getText().matches(regEx)) {
					alertMessage(addDirektor);
				} else if (addInternal.getText().matches(regEx)) {
					alertMessage(addInternal);
				} else if (addPhone.getText().matches(regEx)) {
					alertMessage(addPhone);
				} else if (addEmail.getText().matches(regEx)) {
					alertMessage(addEmail);
				} else if (addUser.getText().matches(regEx)) {
					alertMessage(addUser);
				}
			}

			private void alertMessage(TextField field) {
				String emptyField = field.getPromptText();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("The field " + emptyField + " is empty");
				alert.setContentText("Ooops, there was an error!");

				alert.showAndWait();
			}
		});

		hb.getChildren().addAll(addName, addPosition, addDepart, addDirektor,
				addInternal, addPhone, addEmail, addUser, addButton);
		hb.setSpacing(20);

		final Button removeButton = new Button("Remove");
		removeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("Delete record");
				alert.setContentText("Do you realey want to delete?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

					ContactRecord itemToRemove = table.getSelectionModel()
							.getSelectedItem();
					data.remove(itemToRemove);
					try {
						recordFromServer.deleteAllRecordsWhere("Employee",
								"name", itemToRemove.getName());
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				} else {
					System.out.println("Ooops");
				}

			}
		});

		final HBox hb2 = new HBox();
		hb2.getChildren().addAll(removeButton);
		hb2.scaleXProperty();

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 100, 10));
		vbox.getChildren().addAll(label, table, hb, hb2);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}