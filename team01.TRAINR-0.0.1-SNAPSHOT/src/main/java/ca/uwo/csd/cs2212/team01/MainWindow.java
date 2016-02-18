/**
 * 
 */
package ca.uwo.csd.cs2212.team01;

import javax.swing.JFrame;

/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import java.awt.Color;
import javax.swing.GroupLayout;

import java.awt.BorderLayout;*/

/**
 * @author Kamal
 *
 */
public class MainWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*private JTextField txtName;
    private JLabel lblGreeting;
    private JRadioButton radBachelors;
    private JRadioButton radMasters;
    private JRadioButton radPhd;*/
    
	public MainWindow() {
		this.initUI();
	}
    
    private void initUI () {
    	this.setTitle("TRAINR");  //replace with your UWO email id
    	this.setSize(1024, 800);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		  
		  /*this.setJMenuBar(this.createMenubar());
		  
		  // Replace the line "this.add(this.createForm());" with the following:           
          JPanel pnlWest = new JPanel();
		  pnlWest.setBackground(Color.BLUE);
          pnlWest.setPreferredSize(new Dimension(100,200));
          JPanel pnlEast = new JPanel();
          pnlEast.setBackground(Color.GREEN);
          pnlEast.setPreferredSize(new Dimension(100,200));
          JPanel pnlNorth = new JPanel();
          pnlNorth.setBackground(Color.ORANGE);
          pnlNorth.setPreferredSize(new Dimension(300,25));
          JPanel pnlSouth = new JPanel();
          pnlSouth.setBackground(Color.RED);
          pnlSouth.setPreferredSize(new Dimension(300,25));
          this.setLayout(new BorderLayout());
          this.add(this.createForm(), BorderLayout.CENTER);
          this.add(pnlEast ,BorderLayout.EAST);
          this.add(pnlWest ,BorderLayout.WEST);
          this.add(pnlNorth ,BorderLayout.NORTH);
          this.add(pnlSouth ,BorderLayout.SOUTH);*/
	}
	 
     /*private JMenuBar createMenubar() {
          JMenuBar menubar = new JMenuBar();
          JMenu mnuFile = new JMenu("File");
          mnuFile.setMnemonic(KeyEvent.VK_F);
          JMenuItem mniFileExit = new JMenuItem("Exit");
          mniFileExit.setMnemonic(KeyEvent.VK_E);
          mniFileExit.setToolTipText("Exit application");
          mniFileExit.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent event) {
                     System.exit(0); }
          });
          mnuFile.add(mniFileExit);
          menubar.add(mnuFile);
          return menubar;
     }
	 
     private JPanel createForm() {
          JPanel panel = new JPanel();
          JLabel lblName = new JLabel("Name:");
          JLabel lblDegree = new JLabel("Degree:");
 
          txtName = new JTextField();
          txtName.setPreferredSize(new Dimension(75,25));
 
          radBachelors = new JRadioButton("BSc");
          radMasters = new JRadioButton("MSc");
          radPhd = new JRadioButton("PhD");
 
          ButtonGroup grpDegree = new ButtonGroup();
          grpDegree.add(this.radBachelors);
          grpDegree.add(this.radMasters);
          grpDegree.add(this.radPhd);
 
          lblGreeting = new JLabel();
          JButton btnGreet = new JButton("Greet Me");
 
          btnGreet.addActionListener(new ActionListener() {
               @Override
              public void actionPerformed(ActionEvent event) {
                     String msg = "Hello " + txtName.getText()
                               + ", you are working on ";
                     if (radBachelors.isSelected())
                          msg += "your BSc.";
                     else if (radMasters.isSelected())
                          msg += "your MSc.";
                     else if (radPhd.isSelected())
                          msg += "your PhD.";
                     else
                          msg += "absolutely nothing.";                           
                     lblGreeting.setText(msg);
              }
          });
		  
		  GroupLayout layout = new GroupLayout(panel);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            layout.setHorizontalGroup( layout.createSequentialGroup()
                  .addGroup( layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lblName)
                                    .addComponent(lblDegree)
                              )
                              .addGroup( layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName)
                                    .addGroup(layout.createSequentialGroup()
                                          .addComponent(radBachelors)
                                          .addComponent(radMasters)
                                          .addComponent(radPhd)
                                    )
                              )
                        )
                        .addComponent(lblGreeting)
                  )
                  .addComponent(btnGreet) );
            layout.setVerticalGroup( layout.createSequentialGroup()
                  .addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(txtName)
                  )
                  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDegree)
                        .addComponent(radBachelors)
                        .addComponent(radMasters)
                        .addComponent(radPhd)
                  )
                  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblGreeting)
                        .addComponent(btnGreet)
                  )
            );
			
		  // Replace all the panel.add(...) statements with the following statement
		  panel.setLayout(layout);
		  return panel;
     }*/
}