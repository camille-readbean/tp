package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.DATETIME_FORMAT_STR;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Address;

/**
 * Represents a client's appointment in the application.
 * Guarantees: immutable; <code>from</code> is before <code>to</code>
 */
public class Appointment implements Comparable<Appointment> {

    public final Address address;
    public final LocalDateTime from;
    public final LocalDateTime to;
    public final String title;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Constructs a {@code Appointment}.
     * This constructor will truncate the time units smaller than minutes to 0.
     *
     * @param title A title for the appointment.
     * @param from A valid from date and time. Should be before <code>to</code>.
     * @param to A valid to date and time.
     * @param address An address of the appointment
     */
    public Appointment(String title, LocalDateTime from, LocalDateTime to, Address address) {
        requireAllNonNull(title, from, to, address);
        from = from.truncatedTo(ChronoUnit.MINUTES);
        to = to.truncatedTo(ChronoUnit.MINUTES);
        if (!from.isBefore(to)) {
            logger.severe("INVALID TIME for Appointment: " + title
                    + " from " + from + " BEFORE " + "to: " + to);
            assert from.isBefore(to);
        }
        this.title = title;
        this.from = from;
        this.to = to;
        this.address = address;
        logger.info("Appointment: " + this + " created");
    }

    /**
     * Returns true if both appointments have the same identity and data fields.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherTag = (Appointment) other;
        return title.equals(otherTag.title)
                && from.equals(otherTag.from)
                && to.equals(otherTag.to)
                && address.equals(otherTag.address);
    }

    /**
     * Compares two appointment, returning in by lower <code>from</code>, then <code>to</code> then finally
     * by <code>title</code>
     *
     * @param o the Appointment to be compared against.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
     *   or greater than the specified object.
     */
    @Override
    public int compareTo(Appointment o) {
        int fromDiff = this.from.compareTo(o.from);
        if (fromDiff != 0) {
            return fromDiff;
        }

        int toDiff = this.to.compareTo(o.to);
        if (toDiff != 0) {
            return toDiff;
        }

        return this.title.compareTo(o.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, from, to, address);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATETIME_FORMAT_STR);
        return "【" + title + "】 from 🕑: <"
                + from.format(dtf)
                + "> to 🕒: <"
                + to.format(dtf)
                + "> at 📌: <"
                + address.toString()
                + ">";
    }

}
