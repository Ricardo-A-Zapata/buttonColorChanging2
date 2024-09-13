import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
/**
 *
 * @author ricardozapata
 */
public class buttonColorChanging2 {

    /**
     * @param args the command line arguments
     */
    
    private static int numberOfButtons = 8;    // Here is a variable that can be changed for ease of scalability
    private static JButton[] buttons = new JButton[numberOfButtons];
    private static boolean[] buttonPressed = new boolean[numberOfButtons]; // Keep track of pressed button
    // Making a function to generate a random color for button initalization and after button press
    private static Color randomColor(){
        Random randomDecider = new Random();
        Color randomColor = new Color(randomDecider.nextInt(256), randomDecider.nextInt(256),
        randomDecider.nextInt(256));
        return randomColor;
    }
    
    public static void main(String[] args) {
        
        JFrame jf = new JFrame("Button Color Changing Application"); // Making window for buttons
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500,500);
       
        JPanel background = new JPanel();
        
        background.setLayout(new GridLayout(numberOfButtons/2,2, 25, 25)); // Setting layout of buttons
        
        // Making a shared action listener for all buttons so all buttons know when one is pressed
        ButtonActionListener actionListener = new ButtonActionListener();
        
        for (int i = 0; i < numberOfButtons; i++) {
            JButton button = new JButton(Integer.toString(i + 1));
            button.addActionListener(actionListener);
            button.setOpaque(true);
            buttons[i] = button;
            button.setBackground(randomColor());
            background.add(button);
        }
        
        // I added a timer to help in changing color each second
        Timer timer = new Timer(1000, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            for(int index = 0; index < numberOfButtons; index++){
                if (!buttonPressed[index]){ // Need to check whether a button has been pressed to change color
                    buttons[index].setBackground(randomColor());
                }
            }
        }});
        timer.start();
       
        jf.add(background);
        jf.setVisible(true);
    }
    
    static class ButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            JButton clicked = (JButton) e.getSource();
            for(int index = 0; index < numberOfButtons; index++){
                if(buttons[index] == clicked){  // changed code a bit here to reflect need to update buttonPressed
                    buttonPressed[index] = !(buttonPressed[index]);
                }
            }
        }
    }  
}
