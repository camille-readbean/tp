package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnscheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_INDEX;

public class UnscheduleCommandParser implements Parser<UnscheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns an ScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnscheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPT_INDEX);

        Index clientIndex;

        try {
            clientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnscheduleCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_APPT_INDEX)) {
            ArrayList<String> missingFields = new ArrayList<String>();
            if (argMultimap.getValue(PREFIX_APPT_INDEX).isEmpty()) {
                missingFields.add("index");
            }
            String missingText = "Missing fields: "
                    + missingFields.stream().reduce((i, s) -> i + ", " + s).orElse(" ")
                    + "\n" + UnscheduleCommand.MESSAGE_USAGE;
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, missingText));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPT_INDEX);

        Index apptIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_APPT_INDEX).get());

        return new UnscheduleCommand(clientIndex, apptIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
