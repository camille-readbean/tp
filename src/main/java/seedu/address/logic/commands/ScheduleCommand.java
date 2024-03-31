package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.DATETIME_FORMAT_STR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules an appointment to a client.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_FROM + "FROM_DATETIME "
            + PREFIX_TO + "FROM_DATETIME "
            + PREFIX_ADDRESS + "ADDRESS \n"
            + "Date time are in " + DATETIME_FORMAT_STR + " in 24 hours format\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Monthly touchbase "
            + PREFIX_FROM + "14/03/2024 15:00 "
            + PREFIX_TO + "14/03/2024 15:20 "
            + PREFIX_ADDRESS + "Meeting Room A, 358 Clementi Ave 2 #01-285 Singapore 120358";

    public static final String MESSAGE_SUCCESS = "New appointment scheduled: %1$s";
    public static final String MESSAGE_FAIL = "Error appointment cannot be scheduled: %1$s";

    private Logger logger = LogsCenter.getLogger(getClass());
    private final Appointment appointmentToAdd;
    private final Index index;

    /**
     * Schedules an appointment for the client to add the specified {@code Client}
     *
     * @param index of the client in the filtered client list to edit
     * @param appt appointment to add to the client with
     */
    public ScheduleCommand(Index index, Appointment appt) {
        requireAllNonNull(index, appt);
        this.index = index;
        appointmentToAdd = appt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            logger.warning("sched FAIL: " + Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client updatedClientWithAppointment = clientToEdit.withNewAppointment(appointmentToAdd);

        model.setClient(clientToEdit, updatedClientWithAppointment);
        model.updateFilteredClientList(Model.PREDICATE_SHOW_ALL_CLIENTS);

        String message = String.format(MESSAGE_SUCCESS, appointmentToAdd);
        logger.info("sched OK: " + message);
        return new CommandResult(message);
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
        return index.equals(otherAddCommand.index)
                && appointmentToAdd.equals(otherAddCommand.appointmentToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("appointmentToAdd", appointmentToAdd)
                .toString();
    }
}
