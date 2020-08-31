/**
 * A storage class that contains methods for loading and saving tasks.
 */

package main.java.storage;

import main.java.data.Deadline;
import main.java.data.Event;
import main.java.data.Task;
import main.java.data.Todo;
import main.java.exception.ChatbotException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class Storage {

    private final Path location;

    public Storage(Path location) {
        this.location = location;
    }

    /**
     * Loads a list of tasks from a file on the disk. If no such file exists, create a new file.
     * @return list of tasks saved to the disk
     * @throws ChatbotException if could not load file
     */
    public ArrayList<Task> loadTasks() throws ChatbotException {
        try {

            // File does not exist
            if (!Files.exists(location)) {
                Files.createFile(location);
                return  new ArrayList<>();
            }

            // File exists, load data
            Stream<Task> taskStream = Files.lines(location).map(line -> {

                String[] lineData = line.split("\\|");
                String type = lineData[0].trim();
                boolean isDone = lineData[1].trim().equals("1");
                String description = lineData[2].trim();
                String timestamp = lineData[3].trim();

                Task task = null;

                switch (type) {
                    case "T":
                        task = new Todo(description, isDone);
                        break;
                    case "D":
                        task = new Deadline(description, isDone, LocalDate.parse(timestamp));
                        break;
                    case "E":
                        task = new Event(description, isDone, LocalDate.parse(timestamp));
                        break;
                    default:
                        break;
                }

                return task;
            });

            ArrayList<Task> taskList = new ArrayList<>();
            taskStream.forEach(taskList::add);

            return taskList;

        } catch (IOException e) {
            throw new ChatbotException("Ooops, I couldn't load the tasks.");
        }
    }

    public void saveTasks(ArrayList<Task> taskList) throws ChatbotException {

        Iterator<Task> iter = taskList.iterator();
        String dataStr = "";

        while (iter.hasNext()) {
            Task tsk = iter.next();
            String timestamp = tsk.getDate() == null ? "-" : tsk.getDate().toString();
            String entry = tsk.getType() + " | " +
                    tsk.getStatus() + " | " +
                    tsk.getDescription() + " | " +
                    timestamp  +
                    System.lineSeparator();
            dataStr = dataStr.concat(entry);
        }

        try {
            Files.write(location, dataStr.getBytes());
        } catch (IOException e) {
            throw new ChatbotException("Oooops, I couldn't save the tasks.");
        }
    }
}