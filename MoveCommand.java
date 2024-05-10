import java.util.ArrayList;
import java.util.Enumeration;

public class MoveCommand extends Command {
    private int deltaX;
    private int deltaY;
    private ArrayList<Item> selectedItems;
    private boolean executed;

    public MoveCommand() {
        selectedItems = new ArrayList<>();
        executed = false;
    }

    public boolean setTranslation(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    
        selectedItems.clear();
        Enumeration<Item> enumeration = model.getSelectedItems();
        while (enumeration.hasMoreElements()) {
            selectedItems.add(enumeration.nextElement());
        }
    
        return !selectedItems.isEmpty();
    }

    public boolean undo() {
        if (!executed) {
            return false;
        }

        for (Item item : selectedItems) {
            item.translate(-deltaX, -deltaY);
        }
        model.setChanged();
        executed = false;
        return true;
    }

    public boolean redo() {
        if (executed) {
            return false;
        }

        for (Item item : selectedItems) {
            item.translate(deltaX, deltaY);
        }
        model.setChanged();
        executed = true;
        return true;
    }

    public void execute() {
        if (!executed) {
            for (Item item : selectedItems) {
                item.translate(deltaX, deltaY);
            }
            model.setChanged();
            executed = true;
        }
    }

    public boolean end() {
        return executed;
    }
}