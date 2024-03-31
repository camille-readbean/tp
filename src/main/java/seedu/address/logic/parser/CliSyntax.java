package seedu.address.logic.parser;

import java.time.format.DateTimeFormatter;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    public static final String DATETIME_FORMAT_STR = "dd/MM/yyyy HH:mm";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT_STR);

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("--name=");
    public static final Prefix PREFIX_PHONE = new Prefix("--phone=");
    public static final Prefix PREFIX_EMAIL = new Prefix("--email=");
    public static final Prefix PREFIX_ADDRESS = new Prefix("--addr=");
    public static final Prefix PREFIX_TAG = new Prefix("--tags=");
    public static final Prefix PREFIX_TITLE = new Prefix("--title=");
    public static final Prefix PREFIX_FROM = new Prefix("--from=");
    public static final Prefix PREFIX_TO = new Prefix("--to=");
    public static final Prefix PREFIX_APPT_INDEX = new Prefix("--appt=");
}
