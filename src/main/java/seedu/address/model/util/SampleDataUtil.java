package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Note;
import seedu.address.model.client.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        LocalDateTime now = LocalDateTime.now();
        return new Client[] {
            new Client(new Name("Jia Tan"), new Phone("87438807"), new Email("jt@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("disabled", "diabetic"), new Note("Patient is allergic to penicillin."))
                .withNewAppointment(
                        new Appointment("Medical Appointment",
                                now,
                                now.plusHours(1),
                                new Address("Natasha's Clinic, Boulder Town, 6 Jarilo Avenue"))),
            new Client(new Name("Hu Tao"), new Phone("90901555"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Liyue Habour, #07-15"),
                getTagSet("needy", "risky"), new Note("Patient has a dog named Rex."))
                .withNewAppointment(
                    new Appointment("Meetup",
                            now.plusMonths(2),
                            now.plusMonths(2).plusDays(2),
                            new Address("Star rail restaurant")))
                .withNewAppointment(
                    new Appointment("Pre-trial conference",
                            now.minusDays(2),
                            now.minusHours(46),
                            new Address("Family Court"))),
            new Client(new Name("Hans Jansen"), new Phone("90241217"), new Email("hjansen@example.com"),
                new Address("20 Commonwealth Avenue"),
                getTagSet("diabetes", "risky"), new Note("Patient's daughter is the primary care giver.")),
            new Client(new Name("Chen Hui chieh"), new Phone("91031282"), new Email("jy@example.com"),
                new Address("Lungmen Police Divison, #05-01"),
                getTagSet("colleagues", "police"), new Note("Patient was a police officer."))
                .withNewAppointment(
                    new Appointment("Appreciation dinner",
                            now.plusDays(40),
                            now.plusDays(40).plusHours(1),
                            new Address("Natasha's Clinic, Boulder Town, 6 Jarilo Avenue"))),
            new Client(new Name("Natasha"), new Phone("94367465"), new Email("natasha@example.com"),
                new Address("Natasha's Clinic, Boulder Town, 6 Jarilo Avenue"),
                getTagSet("doctor", "colleagues", "friends"), new Note("Patient was a doctor.")),
            new Client(new Name("Serval Landau"), new Phone("98375615"), new Email("serval@example.com"),
                new Address("#08-17, Tower B, Utown Residence"),
                getTagSet("volunteer"), new Note("NA"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
