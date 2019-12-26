package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import UI.GUIPrecioModelo;

public class tester implements ActionListener {
    JFrame mainFrame = null;
    JButton myButton = null;

    public tester() {
        mainFrame = new JFrame("TestTheDialog Tester");
        mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {System.exit(0);}
            });
        myButton = new JButton("Test the dialog!");
        myButton.addActionListener(this);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.getContentPane().add(myButton);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(myButton == e.getSource()) {
            System.err.println("Opening dialog.");
            GUIPrecioModelo myDialog = new GUIPrecioModelo(mainFrame);
//            myDialog.addController(new CtrPrecioModelo(myDialog));
            System.err.println("After opening dialog.");
            if(myDialog.getFieldPrecioExterior()==0) {
                System.err.println("The answer stored in CustomDialog is 'true' (i.e. user clicked yes button.)");
            }
            else {
                System.err.println("The answer stored in CustomDialog is 'false' (i.e. user clicked no button.)");
            }
        }
    }

    public static void main(String argv[]) {

        tester t=  new tester();
    }
}