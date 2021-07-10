package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import sample.datamodel.Contact;
import sample.datamodel.ContactData;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainPanel;

    @FXML
    private TableView<Contact> contactsTable;

    private ContactData data;

    public void initialize() {
        // create an instance of the contactsData class
        data = new ContactData();
        data.loadContacts();
        contactsTable.setItems(data.getContacts());

    }


    @FXML
    public void showAddContactDialogue() {
        // set new diagloue instance and set main window as its parent
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Contact");

        FXMLLoader fxmlLoader = new FXMLLoader();
        // load the fxml file
        fxmlLoader.setLocation(getClass().getResource("contactdialogue.fxml"));

        try {
            // set the dialogue content to what is in the fxml file
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            ContactController contactcontroller = fxmlLoader.getController();
            Contact contact = contactcontroller.getNewContact();
            data.addContact(contact);
            data.saveContacts();

        }
    }


    @FXML
    public void showEditContactDialogue() {
        Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if(selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the contact you want to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Edit Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        // load the fxml file
        fxmlLoader.setLocation(getClass().getResource("contactdialogue.fxml"));


        try {
            // set the dialogue content to what is in the fxml file
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ContactController contactController = fxmlLoader.getController();
        // set the fields in the dialog for the Contact
        contactController.editContact(selectedContact);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            ContactController contactcontroller = fxmlLoader.getController();
            contactcontroller.updateContact(selectedContact);
            data.saveContacts();

        }


    }



    public void deleteContact() {

        Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        // alert the user that no contact has been selected
        if(selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please selected the contact you");
            alert.showAndWait();
            return;
        }

        // alert the user that the contact will be deleted
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected contact? " +
                selectedContact.getFirstName() + " " + selectedContact.getLastName());

        // confirm that the user clicks OK to deleting the contact
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            data.deleteContact(selectedContact);
            data.saveContacts();
        }


    }



}
