package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_INDEX;

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
 * Deletes an appointment in the address book.
 */
public class UnscheduleCommand extends Command {

    public static final String COMMAND_WORD = "unsched";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unschedules an appointment to a client.\n"
            + "Parameters: CLIENT_INDEX (must be a positive integer) "
            + PREFIX_APPT_INDEX + "APPOINTMENT_INDEX \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPT_INDEX + "2";

    public static final String MESSAGE_SUCCESS = "Client %1$s unscheduled: %2$s";
    private static final String MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX = "The appointment index provided is invalid";

    private Logger logger = LogsCenter.getLogger(getClass());
    private final Index clientIndex; // index of the Client
    private final Index apptIndex; // index of the Appointment

    public UnscheduleCommand(Index clientIndex, Index apptIndex) {
        requireAllNonNull(clientIndex, apptIndex);
        this.clientIndex = clientIndex;
        this.apptIndex = apptIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (clientIndex.getZeroBased() >= lastShownList.size()) {
            logger.warning("unsched FAIL: " + Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(clientIndex.getZeroBased());
        Client updatedClientWithoutAppointment;

        String message = "";
        Appointment apptDeleted = null;
        try {
            apptDeleted = clientToEdit.getAppointment(apptIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        assert apptIndex != null;

        updatedClientWithoutAppointment = clientToEdit.removeAppointment(apptIndex.getZeroBased());
        model.setClient(clientToEdit, updatedClientWithoutAppointment);
        message = String.format(MESSAGE_SUCCESS, clientToEdit, apptDeleted);
        logger.info("unsched OK: " + message);
        model.updateFilteredClientList(Model.PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(message);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("clientIndex", clientIndex)
                .add("apptIndex", apptIndex)
                .toString();
    }
}
