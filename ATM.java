package ATM;

import java.io.IOException;

public class ATM {
    public static void main(String[] args) {
        OptionMenu optionMenu = new OptionMenu();
        
        try {
            optionMenu.getLogin(); // Handle IOException properly
        } catch (IOException e) {
            System.out.println("An error occurred while logging in: " + e.getMessage());
        }
    }
}
