package chatbot.parser;

import chatbot.commands.*;
import chatbot.common.Type;
import chatbot.exception.ChatbotException;

/**
 * A class that parses the command input given by the user.
 */

public class Parser {

    /**
     * Parses the user input and returns an executable command.
     * @param fullCmd the user input
     * @return command matching the user input
     * @throws ChatbotException
     */
    public static Command parse(String fullCmd) {

        String text = fullCmd.trim();
        String typeStr = text.split(" ")[0].trim();
        String body = text.substring(typeStr.length()).trim();

        Command command = null;

        try {
            Type type = Type.valueOf(typeStr.toUpperCase());

            boolean isShow = type == Type.LIST || type == Type.DATE;
            boolean isAdd = type == Type.TODO || type == Type.DEADLINE || type == Type.EVENT;
            boolean isAction = type == Type.DELETE || type == Type.DONE;
            boolean isFind = type == Type.FIND;
            boolean isExit = type == Type.BYE;

            if (isShow) {
                command = new ShowCommand(type, body);
            } else if (isAdd) {
                command = new AddCommand(type, body);
            } else if (isAction) {
                command = new ActionCommand(type, body);
            } else if (isExit) {
                command = new ExitCommand();
            } else if (isFind){
                command = new FindCommand(body);
            } else {
                command = new InvalidCommand();
            }

        } catch (IllegalArgumentException e){
            command = new InvalidCommand();
        }

        return command;
    }
}
