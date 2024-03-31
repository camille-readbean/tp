package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("--name=");
    public static final Prefix PREFIX_PHONE = new Prefix("--phone=");
    public static final Prefix PREFIX_EMAIL = new Prefix("--email=");
    public static final Prefix PREFIX_ADDRESS = new Prefix("--addr=");
    public static final Prefix PREFIX_TAG = new Prefix("--tags=");
    public static final Prefix PREFIX_TITLE = new Prefix("--title=");
    public static final Prefix PREFIX_FROM = new Prefix("--from=");
    public static final Prefix PREFIX_TO = new Prefix("--to=");
}
