package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ScheduleCommand.MESSAGE_FAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.client.Address;

/**
 * Parses input arguments and creates a new ScheduleCommand object. Handles creating the appointment
 * before passing it to ScheduleCommand.
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns an ScheduleCommand object for execution.
     * Appointment is created here to save complexity from parsing.
     *
     * @return ScheduleCommand initialized with the client index and the appointment
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_FROM, PREFIX_TO, PREFIX_ADDRESS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_FROM, PREFIX_TO, PREFIX_ADDRESS)) {
            ArrayList<String> missingFields = new ArrayList<String>();
            if (argMultimap.getValue(PREFIX_TITLE).isEmpty()) {
                missingFields.add("title");
            }
            if (argMultimap.getValue(PREFIX_FROM).isEmpty()) {
                missingFields.add("from");
            }
            if (argMultimap.getValue(PREFIX_TO).isEmpty()) {
                missingFields.add("to");
            }
            if (argMultimap.getValue(PREFIX_ADDRESS).isEmpty()) {
                missingFields.add("address");
            }
            String missingText = "Missing fields: "
                    + missingFields.stream().reduce((i, s) -> i + ", " + s).orElse(" ")
                    + "\n" + ScheduleCommand.MESSAGE_USAGE;
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, missingText));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TITLE, PREFIX_FROM, PREFIX_TO, PREFIX_ADDRESS);

        String title = ParserUtil.parseString(argMultimap.getValue(PREFIX_TITLE).get());
        LocalDateTime fromTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_FROM).get());
        LocalDateTime toTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_TO).get());
        if (toTime.isBefore(fromTime)) {
            throw new ParseException(String.format(MESSAGE_FAIL, "ending time (to) is before start (from)!"));
        }
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());

        Appointment appointmentDetails = new Appointment(title, fromTime, toTime, address);

        return new ScheduleCommand(index, appointmentDetails);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
