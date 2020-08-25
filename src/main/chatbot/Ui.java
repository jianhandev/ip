import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Ui implements Printable<Task> {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private void display(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + message);
        System.out.println("    ____________________________________________________________");
    }

    public void greet() {
        System.out.println(
                "   ####################################################\n" +
                        "   #                                                  #\n" +
                        "   #  Hey there, I'm Hanry Kun the impatient ChatBot. #\n" +
                        "   #  What can I do for you? (-.-)                    #\n" +
                        "   #                                                  #\n" +
                        "   ####################################################"
        );
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void addSuccess(Task task, int count) {
        display("Got it. I've added this task:\n        " + task +
                String.format("\n    Now you have %d task(s) in the list.", count));
    }

    public void deleteSuccess(Task task, int count) {
        display("Alright. I've removed this task:\n        " + task +
                String.format("\n    Now you have %d task(s) in the list.", count));
    }

    public void markDoneSuccess(Task task) {
        display("Nice! I've marked this task as done:\n    " +
                "    " + task);
    }

    public void showErrorMessage(String message) {
        display(message);
    }

    public void list(ArrayList<Task> ls) {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("    ######################## Your Tasks ########################");
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        if (ls.size() == 0 ) {
            System.out.println("    No task here.");
            return;
        }

        Iterator<Task> iter = ls.iterator();
        int index = 1;
        while (iter.hasNext()) {
            System.out.println("    " + index + ". " + iter.next());
            index++;
        }
        System.out.println("    - End of list -");
    }
}
