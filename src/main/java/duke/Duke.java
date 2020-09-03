package duke;

import java.util.Scanner;

/**
 * Duke is the main logic of the program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke Object.
     * 
     * @param filePath a new file will be created in the stated filePath.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadData());
    }

    /**
     * Consists of the logic of the program.
     */
    public void run() {

        ui.mochaIntroduction();
        Parser parser = ui.createParser();
        Scanner userInput = ui.createUserInputScanner();

        while (userInput.hasNextLine()) {
            try {
                String nextLine = userInput.nextLine();
                int commandNumber = parser.parseCommand(nextLine);
                
                if (commandNumber >= 1 && commandNumber <= 3) {
                    Task newTask = parser.createTask(commandNumber);
                    tasks.addTask(newTask);
                    // TODO: write an exception for 
                    ui.taskAdded(newTask, tasks.getSize());

                } else if (commandNumber == 4) {
                    int taskNumber = parser.doneTask();
                    // TODO: have a exception for TaskDoesNotExist (either -1 or more than number of task)
                    // TODO: figure out exception throw catch
                    Task doneTask = tasks.get(taskNumber);
                    doneTask.markAsDone();
                    ui.taskDone(doneTask);

                } else if (commandNumber == 5) {
                    ui.listAllTasks(tasks);

                } else if (commandNumber == 6) {
                    ui.mochaGoodbye();
                    storage.writeToFile(tasks.getTaskList());

                } else if (commandNumber == 7) {
                    int taskNumber = parser.deleteTask();
                    Task deleteTask = tasks.get(taskNumber);
                    tasks.deleteTask(taskNumber);
                    ui.deleteTask(deleteTask, tasks.getSize());

                } else {
                    String horizontalLine = "_______________________________________________________";
                    
                    throw new CommandNotRecognizedException(horizontalLine
                            + "\r\n"
                            + "Oops! I couldn't understand what you mean :("
                            + "\r\n"
                            + horizontalLine);
                }
            } catch (MissingTaskDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (MissingTaskNumberException e) {
                System.out.println(e.getMessage());
            } catch (CommandNotRecognizedException e) {
                System.out.println(e.getMessage());
//            } catch (TaskDoesNotExist e) {
//                System.out.println(e.getMessage());
//            }
            }
        }
    }

    /**
     * Driver of the program.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}