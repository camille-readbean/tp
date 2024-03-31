package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.client.Address;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedAppointment {

    private final String address;
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String title;

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(@JsonProperty("title") String title, @JsonProperty("from") LocalDateTime from,
                                  @JsonProperty("to") LocalDateTime to, @JsonProperty("address") String address) {
        this.address = address;
        this.from = from;
        this.to = to;
        this.title = title;
    }

    public static JsonAdaptedAppointment JsonAdaptedAppointmentBuilder(Appointment source) {
        return new JsonAdaptedAppointment(source.title, source.from, source.to, source.address.toString());
    }


    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Appointment toModelType() throws IllegalValueException {
        return new Appointment(title, from, to, new Address(address));
    }

}
