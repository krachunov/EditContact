package com.levins.my.contact.UI;

import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
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

public class ContactTable extends Application {

	private TableView<ContactRecord> table = new TableView<ContactRecord>();

	private final ObservableList<ContactRecord> data = FXCollections
			.observableArrayList();

	final HBox hb = new HBox();
	private ComboBox<?> comboBox;
	private ConnectionWithServer<ContactRecord> recordFromServer;
	private String tableType;

	@SuppressWarnings({ "unchecked" })
	@Override
	public void start(Stage stage) throws ClassNotFoundException {

		ObservableList<String> options = FXCollections.observableArrayList(
				"Employee", "Agent");
		comboBox = new ComboBox<String>(options);
		comboBox.setPromptText("Choose which contact you want to edit");

		recordFromServer = new ConnectionWithServer<ContactRecord>("contact");
		comboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				tableType = comboBox.getSelectionModel().getSelectedItem()
						.toString();
				List<ContactRecord> allRecords = null;
				try {
					allRecords = recordFromServer.getAllrecords(tableType);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				for (ContactRecord contactRecord : allRecords) {
					data.add(contactRecord);
				}
			}
		});

		Scene scene = new Scene(new Group());
		stage.setTitle("Edit Contact");
		stage.setWidth(900);
		stage.setHeight(700);

		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn<ContactRecord, String> firstNameCol = createColumn("Name",
				"name", 150);
		TableColumn<ContactRecord, String> department = createColumn(
				"Department", "department", 200);
		TableColumn<ContactRecord, String> director = createColumn("Director",
				"director", 150);
		TableColumn<ContactRecord, String> post = createColumn("Post", "post",
				100);
		TableColumn<ContactRecord, String> internal = createColumn(
				"Internal phone", "internal", 10);
		TableColumn<ContactRecord, String> phone = createColumn("Phone",
				"phone", 100);
		TableColumn<ContactRecord, String> emailCol = createColumn("Email",
				"email", 200);

		// emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// emailCol.setOnEditCommit(new
		// EventHandler<CellEditEvent<ContactRecord, String>>() {
		// @Override
		// public void handle(CellEditEvent<ContactRecord, String> t) {
		// ((ContactRecord)
		// t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
		// }
		// });

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, post, department, director,
				internal, phone, emailCol);

		List<TextField> allField = new ArrayList<TextField>();

		final TextField addName = new TextField();
		addName.setPromptText("Name");
		addName.setMaxWidth(firstNameCol.getPrefWidth());
		allField.add(addName);

		final TextField addPosition = new TextField();
		addPosition.setMaxWidth(post.getPrefWidth());
		addPosition.setPromptText("Position");
		allField.add(addPosition);

		final TextField addDepart = new TextField();
		addDepart.setMaxWidth(department.getPrefWidth());
		addDepart.setPromptText("Department");
		allField.add(addDepart);

		final TextField addDirektor = new TextField();
		addDirektor.setMaxWidth(director.getPrefWidth());
		addDirektor.setPromptText("Direktor");
		allField.add(addDirektor);

		final TextField addInternal = new TextField();
		addInternal.setMaxWidth(internal.getPrefWidth());
		addInternal.setPromptText("Internal");
		allField.add(addInternal);

		final TextField addPhone = new TextField();
		addPhone.setMaxWidth(phone.getPrefWidth());
		addPhone.setPromptText("Phone");
		allField.add(addPhone);

		final TextField addEmail = new TextField();
		addEmail.setMaxWidth(emailCol.getPrefWidth());
		addEmail.setPromptText("Email");
		allField.add(addEmail);

		final TextField addUser = new TextField();
		addUser.setMaxWidth(emailCol.getPrefWidth());
		addUser.setPromptText("User");
		allField.add(addUser);

		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// TODO need change with list
				// final boolean checkFieldIsEmpty = checkFieldIsEmpty(addName,
				// addPosition, addDepart, addDirektor, addInternal,
				// addPhone, addEmail, addUser);

				final boolean checkFieldIsEmpty = checkFieldIsEmpty(allField);

				if (checkFieldIsEmpty) {
					createNewRecord(recordFromServer, addName, addPosition,
							addDepart, addDirektor, addInternal, addPhone,
							addEmail, addUser);

				}

			}

			private void createNewRecord(ConnectionWithServer<ContactRecord> a,
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

				for (TextField textField : allField) {
					textField.clear();
				}
			}

			// TODO - needed every one of the field has valid data
			private boolean checkFieldIsEmpty(
					final List<TextField> listWithFields) {
				boolean isEmpty = true;
				String regEx = "^\\s*$";
				for (TextField textField : listWithFields) {
					System.out.println(textField.getText());
					if (textField == null || textField.getText().equals(regEx)) {
						alertMessage(textField);
						isEmpty = false;
						break;
					}
				}
				return isEmpty;
			}

			// TODO problem
			private boolean checkFieldIsEmpty2(final TextField addName,
					final TextField addPosition, final TextField addDepart,
					final TextField addDirektor, final TextField addInternal,
					final TextField addPhone, final TextField addEmail,
					final TextField addUser) {
				String regEx = "^\\s*$";

				if (addName.getText().equals(regEx)) {
					alertMessage(addName);
					return false;
				} else if (addPosition.getText().matches(regEx)) {
					alertMessage(addPosition);
					return false;
				} else if (addDepart.getText().matches(regEx)) {
					alertMessage(addDepart);
					return false;
				} else if (addDirektor.getText().matches(regEx)) {
					alertMessage(addDirektor);
					return false;
				} else if (addInternal.getText().matches(regEx)) {
					alertMessage(addInternal);
					return false;
				} else if (addPhone.getText().matches(regEx)) {
					alertMessage(addPhone);
					return false;
				} else if (addEmail.getText().matches(regEx)) {
					alertMessage(addEmail);
					return false;
				} else if (addUser.getText().matches(regEx)) {
					alertMessage(addUser);
					return false;
				}
				return true;
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
						recordFromServer.deleteAllRecordsWhere(tableType,
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

		vbox.getChildren().addAll(label, comboBox, table, hb, hb2);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<ContactRecord, String> createColumn(String columnName,
			String columnField, int size) {
		TableColumn<ContactRecord, String> firstNameCol = new TableColumn<ContactRecord, String>(
				columnName);
		firstNameCol.setMinWidth(size);
		firstNameCol
				.setCellValueFactory(new PropertyValueFactory<ContactRecord, String>(
						columnField));
		return firstNameCol;
	}
}