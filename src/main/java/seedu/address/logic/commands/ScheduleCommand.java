package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.client.Client;

/**
 * Schedule an appointment for a client.
 */
public class ScheduleCommand extends Command {
    public static final String COMMAND_WORD = "sched";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules an appointment to a client. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_FROM + "FROM_DATETIME "
            + PREFIX_TO + "FROM_DATETIME "
            + PREFIX_ADDRESS + "ADDRESS \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FROM + "2024/03/14 15:09 "
            + PREFIX_TO + "98765432 "
            + PREFIX_ADDRESS + "Meeting Room A, 358 Clementi Ave 2 #01-285 Singapore 120358";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";

    private Logger logger = LogsCenter.getLogger(getClass());
    private final Client client;
    private final Appointment appointmentToAdd;

    /**
     * Schedules an appointment for the client to add the specified {@code Client}
     */
    public ScheduleCommand(Client client, Appointment appt) {
        requireNonNull(client);
        this.client = client;
        appointmentToAdd = appt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        logger.info("NOT IMPLEMENTED YET");
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(client)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        ScheduleCommand otherAddCommand = (ScheduleCommand) other;
        return client.equals(otherAddCommand.client)
                && appointmentToAdd.equals(otherAddCommand.appointmentToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("client", client)
                .add("appointmentToAdd", appointmentToAdd)
                .toString();
    }
}
