package main.java.data;

import main.java.common.Message;
import main.java.exception.ChatbotException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String description, boolean isDone, LocalDate timestamp) {
        super(description, "D", isDone, timestamp);
    }

    public static Deadline newDeadline(String raw) throws ChatbotException {
        if (raw.length() == 0) {
            throw new ChatbotException("Oh no no, deadline shouldn't be empty.");
        }

        String description = raw.split("/by")[0].trim();
        LocalDate timestamp;

        try {
            String dateString = raw.split("/by")[1].trim();
            timestamp = LocalDate.parse(dateString);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatbotException("When is your task due?");
        } catch (DateTimeParseException e) {
            throw new ChatbotException(Message.INVALID_DATE);
        }

        return new Deadline(description, false, timestamp);
    }

    @Override
    public Deadline markDone() {
        return new Deadline(this.description, true, this.timestamp);
    }

    @Override
    public String toString() {
        String formattedDate = this.timestamp.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + " (by: " + formattedDate + ")";
    }
}
