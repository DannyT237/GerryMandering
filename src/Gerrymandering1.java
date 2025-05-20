////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Student(s): Daniel Tekle                                                                                               //
// Project 2: Reading Input files                                                                             //                                                                          //
// CS-141/Edmonds College/FALL24                                                                            // 
                                                                                                              //
// Description: This program reads in data from two files, districts.txt and eligibleVoters.txt,              // 
// to determine if a state is gerrymandered. The program calculates the total number of wasted votes          //   
// for each district, then determines if the state is gerrymandered by comparing the total number of          // 
// wasted votes for each party. If the difference between the total number of wasted votes for each party     //
// is greater than 7%, the state is considered gerrymandered and the program determines which party benifits. //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gerrymandering1 {
    public static void main(String[] args) {
//        DrawingPanel panel = new DrawingPanel(500, 500);
//        Graphics2D g = panel.getGraphics();
//        drawGerrymandering drawBackground = new drawGerrymandering();
//        drawBackground.paintComponent(g);
        System.out.println("\n\n                 ***Gerrymandering***"  + "\n" 
            + "This program will determine if a state is gerrymandered." + "\n");  
        
        String state = getState();
        int eligibleVoters = getEligibleVoters(state);
        SwingUtilities.invokeLater(() -> displayPanel(eligibleVoters, state));
        Scanner scanner = new Scanner(System.in);

    }

    // getState(): returns the state name.
    public static String getState() {
        Scanner scanner = new Scanner(System.in);
        String state;

        while (true) {
            System.out.print("Please enter a state without spaces: ");
            state = scanner.nextLine().trim(); // Remove leading/trailing spaces

            try (Scanner scanner1 = new Scanner(new File("districts.txt"))) {
                boolean foundState = false;
                while (scanner1.hasNextLine()) {
                    String line = scanner1.nextLine();
                    String[] parts = line.split(" ");
                    if (parts[0].equalsIgnoreCase(state)) {
                        foundState = true;
                        break;
                    }
                }
                if (foundState) {
                    break; // Valid state found, exit loop
                } else {
                    System.out.println("State not found. Please try again.");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Districts file not found.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid data in districts file.");
            }
        }

        scanner.close();
        return state;
    }
                
    // getEligibleVoters(): returns the total number of eligible voters for the state.
    public static int getEligibleVoters(String state) {
        int totalEligibleVoters = 0;
        boolean foundState = false; // Flag to track if the state is found
        
        /* I. Replace this comment with your code */
    
        
        return totalEligibleVoters;
    }


    // displayPanel(): displays the gerrymandering panel and utilizes the drawGerrymandering class while 
    // SwingUtilities.invokeLater() is used to run the GUI on the Event Dispatch Thread, allowing resizing of the window.
    public static void displayPanel(int eligibleVoters, String state) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gerrymandering - Project 2: Reading Input files");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new drawGerrymandering(eligibleVoters, state));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

   // drawGerrymandering: class to draw the gerrymandering panel specified by the state, 
   // eligible voters, and votes by district.
   static class drawGerrymandering extends JPanel {
        private int eligibleVoters;
        private String state;

        public drawGerrymandering(int eligibleVoters, String state) {
            
            this.eligibleVoters = eligibleVoters;
            this.state = state;
            int width = 500;
            int height = 500;
            setPreferredSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            
            g.drawString(state.substring(0, 1).toUpperCase() 
            + state.substring(1).toLowerCase(), 10, 15);
            g.drawString(eligibleVoters + " eligible voters", width - 150, 15);
            g.drawLine(width / 2, 0, width / 2, height);
            g.drawLine(0, 20, width, 20);

        }
    }
}