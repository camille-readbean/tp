package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.parser.CliSyntax.DATETIME_FORMATTER;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Address;

class AppointmentTest {
    @Test
    public void success_appointmentConstructor() {
        String title = "Appointment ";
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.plusHours(2L);
        Address address = new Address(VALID_ADDRESS_AMY);
        Appointment apt = new Appointment(title, from, to, address);
        String expected = "【" + title + "】 from 🕑: <"
                + from.truncatedTo(ChronoUnit.MINUTES).format(DATETIME_FORMATTER)
                + "> to 🕒: <"
                + to.truncatedTo(ChronoUnit.MINUTES).format(DATETIME_FORMATTER)
                + "> at 📌: <"
                + address.toString()
                + ">";
        assertEquals(expected, apt.toString());
    }

    @Test
    public void equals() {
        String title1 = "Appointment ";
        LocalDateTime from1 = LocalDateTime.of(2024, 03, 14, 15, 9, 26);
        LocalDateTime to1 = from1.plusHours(2L);
        Address address1 = new Address(VALID_ADDRESS_AMY);
        Appointment apt1 = new Appointment(title1, from1, to1, address1);
        String title2 = title1;
        LocalDateTime from2 = LocalDateTime.of(2024, 03, 14, 15, 9, 26, 53);
        LocalDateTime to2 = from2.plusHours(2).plusSeconds(10);
        Address address2 = new Address(VALID_ADDRESS_AMY);
        Appointment apt2 = new Appointment(title2, from2, to2, address2);
        assertEquals(apt1, apt2);
    }

    @Test()
    public void toIsBeforeAfter_appointmentConstructor_exceptionThrown() {
        String title = "Appointment ";
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.minusMinutes(1);
        Address address = new Address(VALID_ADDRESS_AMY);
        assertThrows(AssertionError.class, () -> {
            new Appointment(title, from, to, address);
        });
    }
}
