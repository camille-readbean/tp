package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.<br>
 * Appointments are unique and sorted
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    // appointments guaranteed to be in sorted order in best-effort manner as long as proper methods are used
    private final ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    /**
     * Every field except appointments must be present and not null.
     * <code>Appointments</code> is initialised to an empty Arraylist.
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     * Appointments are copied over
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<Appointment> appointments) {
        requireAllNonNull(name, phone, email, address, tags, appointments);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.appointments.addAll(appointments);
        appointments.sort(Appointment::compareTo);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable appointment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Appointment> getAppointments() {
        return Collections.unmodifiableList(appointments);
    }

    public Appointment getAppointment(Index index) throws IndexOutOfBoundsException {
        int zbIndex = index.getZeroBased();
        if (appointments.isEmpty()) {
            throw new IndexOutOfBoundsException("There are no appointments.");
        }
        if (zbIndex < 0 || zbIndex >= appointments.size()) {
            throw new IndexOutOfBoundsException("The appointment index: "
                    + index.toString() + " provided is invalid: ");
        }
        return appointments.get(zbIndex);
    }

    /**
     * Returns an appointment is already inside the current list.
     */
    public boolean hasAppointment(Appointment appointment) {
        return appointments.contains(appointment);
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToUpdate}
     * updated with {@code newAppointment}.
     * Guarantees that the appointments are unique
     *
     * @param newAppointment created appointment.
     * @return Immutable copy of the client with the appointment added.
     */
    public Client withNewAppointment(Appointment newAppointment) {
        assert newAppointment != null;
        assert !hasAppointment(newAppointment);

        ArrayList<Appointment> updatedAppointments = new ArrayList<>(appointments);
        updatedAppointments.add(newAppointment);
        updatedAppointments.sort(Appointment::compareTo);

        return new Client(name, phone, email, address, tags, updatedAppointments);
    }

    /**
     * Creates and returns a new {@code Client} with an appointment removed from the current list of appointments.
     * The appointment to be removed is specified by its index in the list.
     *
     * @param index The 0-based index of the appointment to be removed in the appointments list.
     * @return A new {@code Client} instance with the specified appointment removed.
     */
    public Client removeAppointment(int index) {
        assert !(index < 0 && index >= appointments.size());

        ArrayList<Appointment> updatedAppointments = new ArrayList<>(appointments);
        updatedAppointments.remove(index);
        updatedAppointments.sort(Appointment::compareTo);

        return new Client(name, phone, email, address, tags, updatedAppointments);
    }

    public int getNumberOfAppointments() {
        return appointments.size();
    }

    /**
     * Gets the <code>Optional<Appointment></code>next upcoming appointment.
     * Defined as the Appointment with the lowest from DateTime after now.
     *
     * @return Optional with the next upcoming appointment
     */
    public Optional<Appointment> getNextUpcomingAppointment() {
        return appointments.stream()
                .filter(appointment -> appointment.from.isAfter(LocalDateTime.now()))
                .min(Comparator.comparing(appointment -> appointment.from));
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return name.equals(otherClient.name)
                && phone.equals(otherClient.phone)
                && email.equals(otherClient.email)
                && address.equals(otherClient.address)
                && tags.equals(otherClient.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("appointments", appointments)
                .toString();
    }

}
