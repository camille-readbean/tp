package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.DATETIME_FORMAT_STR;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.client.Client;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label numberOfAppointments;
    @FXML
    private Label nextAppointment;
    @FXML
    private FlowPane tags;
    @FXML
    private Label note;

    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public ClientCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().value);
        email.setText(client.getEmail().value);
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        numberOfAppointments.setText("Appointments: " + client.getNumberOfAppointments());
        Optional<Appointment> nextAppt = client.getNextUpcomingAppointment();
        if (nextAppt.isPresent()) {
            Appointment nextAppointmentDetails = nextAppt.get();
            this.nextAppointment.setText("Next appointment: " + nextAppointmentDetails.title
                    + " on " + nextAppointmentDetails.from.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_STR)));
        } else {
            this.nextAppointment.setText("Next appointment: None!");
        }
        note.setText(client.getNote().value);

    }
}
