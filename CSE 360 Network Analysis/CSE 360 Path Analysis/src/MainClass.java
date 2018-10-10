import javax.swing.*;
import java.awt.*;


public class MainClass extends JFrame{
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Network Path Analysis");					// title of the window, change maybe
		
		frame.setSize(500, 800);											// frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent panels = new InterfacePanels();
		
		frame.add(panels);
		frame.pack();
		frame.setVisible(true);
		
	}
}
