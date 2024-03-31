---
layout: page
title: User Guide
---

Social Worker's Efficiency Enhancer (**SWEE**) is a **desktop application for managing your contact details, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).  
If you can type fast, SWEE can get your contact management tasks done faster than traditional GUI apps.  
This is ideal as you do not often bring a mouse out with you on site visits to clients or on the go. 

--- 

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div class="page-break"></div>

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. You can find a copy [here](https://adoptium.net/temurin/releases/?version=11).  
   Java is needed to run the applications.

1. Download the latest `addressbook.jar` from [here](https://github.com/AY2324S2-CS2103T-T17-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

   1. Open the address book file in a way that suits your operating system. For example, on Windows, you can just double-click the file.<br>
      Shortly after, a graphical interface (GUI) like the one shown below should pop up. Take note that the app already includes some example data for you to see.<br>
      ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add --name=John Doe --phone=98765432 --email=johnd@example.com --addr=John street, block 123, #01-01 --tags=Disabled --tags=SeekingAssistance` : Adds a client named `John Doe` to the Address Book.

   * `del 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

<div class="page-break"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add --name=NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `--name=NAME [--tags=TAG]` can be used as `--name=John Doe --tags=friend` or as `--tags=John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[--tags=TAG]…​` can be used as ` ` (i.e. 0 times), `--tags=SECDC`, `--tags=NECDC --tags=FinAid` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `--name=NAME --phone=PHONE_NUMBER`, `--phone=PHONE_NUMBER --name=NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>


<div class="page-break"></div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpWindow.png)

Format: `help`


<div class="page-break"></div>

### Adding a client: `add`

Adds a client to the address book.

Format: `add --name=NAME --phone=PHONE_NUMBER --email=EMAIL --addr=ADDRESS [--tags=TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client can have any number of tags (including 0)
</div>

**Important**: You cannot leave tags empty, i.e. `add --tags=` with nothing for the tags. 

Examples:
* `add --name=John Doe --phone=98765432 --email=johnd@example.com --addr=John street, block 123, #01-01`
  * `add --name=Jane Doe --phone=92933578 --email=janed@example.sg --addr=Amy street, block 123, #11-02 --tags=Disabled --tags=SeekingAssistance`

### Listing all clients : `list`

Shows a list of all clients in the address book.

Format: `list`


<div class="page-break"></div>

### Editing a client : `edit`

Edits an existing client in the address book.

Format: `edit INDEX [--name=NAME] [--phone=PHONE] [--email=EMAIL] [--addr=ADDRESS] [--tags=TAG]…​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags for a client, any pre-existing tags will be directly replaced with the new ones provided in the command.<br>
  This means that the tag update is not cumulative; unless a tag is specified in the new command, it will not be retained from the previous set of tags.<br>
  If the tag option is not specified, i.e. `--tags=`, the existing tags will be left as is
* You can remove all the client’s tags by typing `--tags=` without
    specifying any tags after it.

Examples:
*  `edit 1 --phone=91234567 --email=johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 --name=Betsy Crower --tags=` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.


<div class="page-break"></div>

### Viewing a client's Information: `view`
Displays the saved information about a client.

Format: `view INDEX`

* Displays client information at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `view 2` displays the information of the 2nd client in the address book.
* `find Betsy` followed by `del 1` displays the information of
   the 1st client in the results of the `find` command.

### Locating clients by name: `find-name`

Finds clients whose names contain any of the given keywords.

Format: `find-name KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Clients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find-name John` returns `john` and `John Doe`
* `find-name alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)


<div class="page-break"></div>

### Locating clients by tag: `find-tag`

Finds clients whose tags contain any of the given keywords.

Format: `find-tag KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. disabled will match Disabled
* The order of the keywords does not matter e.g. disabled risky will match risky disabled
* Only the Tag is searched.
* Only full words will be matched e.g. dis will not match disabled
* Clients matching at least one keyword will be returned (i.e. at least one is present)<br>
  e.g. `disabled risky` will return clients that has at least one tag that is `disabled` or `risky`.<br>
  So clients with tags `disabled` and `diabetic`, and a client with `risky` and `diabetic` will be returned

Examples:
* `find-tag disabled` returns all clients who are tagged `disabled`<br>
  ![result for 'find-tag disabled'](images/FindTagDisabledResult.png)
* `find-tag disabled schizophrenic` will return `disabled`, `Schizophrenic`<br>
  ![result for 'find-tag disabled schizophrenic'](images/FindTagDisabledSchizophrenicResult.png)


<div class="page-break"></div>

### Deleting a client : `del`

Deletes the specified client from the address book.

Format: `del INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `del 2` deletes the 2nd client in the address book.
* `find Betsy` followed by `del 1` deletes the 1st client in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`


<div class="page-break"></div>

### Saving the data

SWEE data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SWEE data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SWEE will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the SWEE to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>


--------------------------------------------------------------------------------------------------------------------


<div class="page-break"></div>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------


<div class="page-break"></div>

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add --name=NAME --phone=PHONE_NUMBER --email=EMAIL --addr=ADDRESS [--tags=TAG]…​` <br> e.g., `add --name=James Ho --phone=22224444 --email=jamesho@example.com --addr=123, Clementi Rd, 1234665 --tags=friend --tags=colleague`
**Clear** | `clear`
**Delete** | `del INDEX`<br> e.g., `del 3`
**Edit** | `edit INDEX [--name=NAME] [--phone=PHONE_NUMBER] [--email=EMAIL] [--addr=ADDRESS] [--tags=TAG]…​`<br> e.g.,`edit 2 --name=James Lee --email=jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**View** | `view`
**Help** | `help`
