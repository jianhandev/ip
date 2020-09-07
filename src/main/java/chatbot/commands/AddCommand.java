package chatbot.commands;

import chatbot.common.Type;

import chatbot.data.Deadline;
import chatbot.data.Event;
import chatbot.data.TaskList;
import chatbot.data.Todo;

import chatbot.exception.ChatbotException;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class AddCommand extends Command {

    Type type;
    String body;

    public AddCommand(Type type, String body) {
        this.type = type;
        this.body = body;
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {

        String response = "";

        switch (type) {
        case TODO:
            Todo task = Todo.newTodo(body);
            if (taskList.addTask(task)) {
                response = ui.addSuccess(task, taskList.count());
            }
            break;
        case DEADLINE:
            Deadline deadline = Deadline.newDeadline(body);
            if (taskList.addTask(deadline)) {
                response = ui.addSuccess(deadline, taskList.count());
            }
            break;
        case EVENT:
            Event event = Event.newEvent(body);
            if (taskList.addTask(event)) {
                response = ui.addSuccess(event, taskList.count());
            }
            break;
        default:
            break;
        }

        assert storage.saveTasks(taskList.getTasks()) : "Save tasks supposed to return true.";

        return response;
    }
}
