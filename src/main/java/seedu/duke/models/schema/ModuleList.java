package seedu.duke.models.schema;

import java.io.InvalidObjectException;
import java.util.ArrayList;

import static seedu.duke.models.logic.DataRepository.getRequirements;

/**
 * A class representing a list of modules and providing operations to manage them.
 */
public class ModuleList {

    private ArrayList<String> mainModuleList;
    private int numberOfModules;

    /**
     * Constructs a ModuleList based on the module requirements for a specific major.
     *
     * @param major The major for which module requirements are needed.
     */
    public ModuleList(Major major) {
        mainModuleList = getRequirements(major.toString());
        for (String ignored : mainModuleList) {
            numberOfModules += 1;
        }
    }

    /**
     * Constructs a ModuleList from a space-separated string of modules.
     *
     * @param modules A space-separated string of module codes.
     */
    public ModuleList(String modules) {
        try {
            String[] moduleArray = modules.split(" ");
            mainModuleList = new ArrayList<String>();

            numberOfModules = 0;
            for (String module : moduleArray) {
                mainModuleList.add(module);
                numberOfModules += 1;
            }
        } catch (NullPointerException e) {
            new ModuleList();
        }
    }

    /**
     * Constructs an empty ModuleList.
     */
    public ModuleList() {
        mainModuleList = new ArrayList<String>();
        numberOfModules = 0;
    }

    public void addModule (String module) {
        mainModuleList.add(module);
    }

    /**
     * Computes the difference between two ModuleList objects (A - B) and updates the current list.
     *
     * @author janelleenqi
     * @param a The first ModuleList.
     * @param b The second ModuleList.
     * @throws InvalidObjectException If either A or B is null.
     */
    public void getDifference (ModuleList a, ModuleList b) throws InvalidObjectException {
        //A - B
        if (a == null || b == null) {
            throw new InvalidObjectException("Null Inputs");
        }
        mainModuleList.clear();

        for (String moduleA : a.mainModuleList) {
            try {
                if (!b.exists(moduleA)) {
                    mainModuleList.add(moduleA);
                    numberOfModules += 1;
                }
            } catch (InvalidObjectException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Checks if a module exists in the list.
     *
     * @author janelleenqi
     * @param moduleA The module to check for existence.
     * @return true if the module exists in the list; false otherwise.
     * @throws InvalidObjectException If moduleA is null.
     */
    public boolean exists(String moduleA) throws InvalidObjectException {
        if (moduleA == null || mainModuleList == null) {
            throw new InvalidObjectException("Null Inputs");
        }

        for (String moduleB : mainModuleList) {
            if (moduleA.equals(moduleB)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the list of modules.
     *
     * @author janelleenqi
     * @return The ArrayList containing the modules.
     */
    public ArrayList<String> getMainModuleList() {
        assert mainModuleList != null: "null mainModuleList";
        return mainModuleList;
    }

    public void printMainModuleList(){
        for (String mod: mainModuleList){
            System.out.print(mod + " ");
        }
        System.out.println();
    }

    /**
     * Retrieves the number of modules.
     *
     * @return The number of modules.
     */
    public int getNumberOfModules() {
        assert numberOfModules >= 0: "negative numberOfModules";
        return numberOfModules;
    }

    /**
     * Changes the number of modules by the specified difference.
     *
     * @param difference The difference by which to change the number of modules.
     *                   A positive value increases the number, while a negative value decreases it.
     */
    public void changeNumberOfModules(int difference) {
        numberOfModules += difference;
    }


}
