import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.geom.RoundRectangle2D;

public class Employee implements ActionListener {

    JFrame empFrame;

    JPanel bannerPanel;
    JPanel textPanel;
    JPanel backgroundPanel;
    JPanel currentPanel;
    JPanel nextPagePanel;

    JButton next_button;

    JTextField SSNo;
    JTextField MemName;
    JTextField MemAddress;
    JTextField DBirth;
    JTextField PBirth;
    JTextField CStatus;
    JTextField TIN;
    JTextField TelNo;

    JComboBox<String> genderBox;
    JComboBox<String> CStatBox;

    public Employee() {

        empFrame = new JFrame("SSS | Retirement Claim Application");
        empFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        empFrame.setSize(1080, 720);
        empFrame.setLayout(new BorderLayout());

        // Set SSS logo 
        ImageIcon SSSLogo = new ImageIcon("sss logo.png");
        empFrame.setIconImage(SSSLogo.getImage());

        //Background
        backgroundPanel = new BackgroundPanel(new ImageIcon("bg.jpg").getImage());
        backgroundPanel.setLayout(new BorderLayout());

        // Banner panel
        bannerPanel = new JPanel();
        bannerPanel.setBackground(new Color(0, 53, 170));
        bannerPanel.setLayout(new BorderLayout());

        // SSS Logo
        JLabel logoLabel = new JLabel(new ImageIcon("sss logo1.png"));
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Text panel
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
 
        JLabel label1 = new JLabel("  Republic of the Philippines");
        label1.setFont(new Font("Inter", Font.PLAIN, 14));
        label1.setForeground(new Color(255, 255, 255));
        label1.setHorizontalAlignment(SwingConstants.LEFT);
 
        JLabel label2 = new JLabel(" Social Security System");
        label2.setFont(new Font("Inter", Font.BOLD, 20));
        label2.setForeground(new Color(255, 255, 255));
        label2.setHorizontalAlignment(SwingConstants.LEFT);
 
        textPanel.add(label1);
        textPanel.add(label2);
 
        // Right text label
        JLabel label3 = new JLabel("Retirement Claim ");
        label3.setFont(new Font("Inter", Font.BOLD, 16));
        label3.setForeground(new Color(255, 255, 255));
        label3.setHorizontalAlignment(SwingConstants.RIGHT);

        // Logo and text Panel
        JPanel logoTextPanel = new JPanel(new BorderLayout());
        logoTextPanel.setOpaque(false);
        logoTextPanel.add(logoLabel, BorderLayout.WEST);
        logoTextPanel.add(textPanel, BorderLayout.CENTER);
 
        bannerPanel.add(logoTextPanel, BorderLayout.WEST);
        bannerPanel.add(label3, BorderLayout.EAST);

        backgroundPanel.add(bannerPanel, BorderLayout.NORTH);

        //Blue Rectangle
        RectanglePanel rectanglePanel = new RectanglePanel();
        backgroundPanel.add(rectanglePanel, BorderLayout.CENTER);
 
        empFrame.add(backgroundPanel, BorderLayout.CENTER);

        
        empFrame.setVisible(true);
     }
 
     public static void main(String[] args) {
         new Employee();
     }
 
     @Override
     public void actionPerformed(ActionEvent e) {
         // Handle action events here
         Object source = e.getSource();
        if (source == genderBox) {
            System.out.println(genderBox.getSelectedItem());
        } else if (source == CStatBox) {
            System.out.println(CStatBox.getSelectedItem());
        }
        if (source == next_button) {
            FrameManager next_Frame = new FrameManager(empFrame, SSNo, MemName, MemAddress, DBirth, PBirth, TIN, TelNo);
            next_Frame.openNextFrame();
        }

     }

     class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    class RectanglePanel extends JPanel {
        public RectanglePanel() {
            setOpaque(false);
            setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.insets = new Insets(10, 10, 10, 10);
            cons.fill = GridBagConstraints.HORIZONTAL;

            // Member's Information
            JLabel memInfo = new JLabel("Member's Information");
            memInfo.setFont(new Font("Inter", Font.BOLD, 30));
            memInfo.setAlignmentX(CENTER_ALIGNMENT);
            memInfo.setForeground(Color.WHITE);
            cons.gridx = 0;
            cons.gridy = 0;
            cons.gridwidth = 3;
            cons.anchor = GridBagConstraints.CENTER;
            add(memInfo, cons);

            cons.gridy++;
            JSeparator separator = new JSeparator();
            cons.gridwidth = GridBagConstraints.REMAINDER;
            cons.fill = GridBagConstraints.HORIZONTAL;
            add(separator, cons);

            // SS Number
            cons.gridwidth = 1;
            cons.fill = GridBagConstraints.NONE;
            cons.gridy++;
            cons.anchor = GridBagConstraints.WEST;
            addLabelAndTextField(cons, "SS Number", "SSS Number", 18);

            // Name of Member
            cons.gridy++;
            addLabelAndTextField(cons, "Name of Member", "Surname, First Name Middle Name", 18);

            // Address
            cons.gridy++;
            addLabelAndTextField(cons, "Address", "No Street Barangay Town, City/Province", 18);

            // Date of Birth and Place of Birth
            cons.gridy++;
            JLabel DBirthLabel = new JLabel("Date of Birth");
            DBirthLabel.setFont(new Font("Inter", Font.BOLD, 18));
            DBirthLabel.setForeground(Color.WHITE);
            cons.gridx = 0;
            add(DBirthLabel, cons);

            DBirth = new JTextField("MM/DD/YYYY");
            DBirth.setFont(new Font("Inter", Font.BOLD, 14));
            DBirth.setBackground(new Color(244, 244, 244));
            DBirth.addFocusListener(new TextFieldFocusListener("MM/DD/YYYY"));
            DBirth.addKeyListener(new DateKeyListener());
            cons.gridx = 1;
            cons.fill = GridBagConstraints.HORIZONTAL;
            add(DBirth, cons);

            JLabel PBirthLabel = new JLabel("Place of Birth");
            PBirthLabel.setFont(new Font("Inter", Font.BOLD, 18));
            PBirthLabel.setForeground(Color.WHITE);
            cons.gridx = 2;
            cons.fill = GridBagConstraints.NONE;
            add(PBirthLabel, cons);

            PBirth = new JTextField("Town, City/Province");
            PBirth.setFont(new Font("Inter", Font.BOLD, 14));
            PBirth.setBackground(new Color(244, 244, 244));
            PBirth.addFocusListener(new TextFieldFocusListener("Town, City/Province"));
            cons.gridx = 3;
            cons.fill = GridBagConstraints.HORIZONTAL;
            add(PBirth, cons);

            // Gender and Civil Status
            cons.gridy++;
            cons.gridx = 0;
            cons.fill = GridBagConstraints.NONE;
            JLabel GenLabel = new JLabel("Gender");
            GenLabel.setFont(new Font("Inter", Font.BOLD, 18));
            GenLabel.setForeground(Color.WHITE);

            add(GenLabel, cons);

            String[] gender = {"Female", "Male"};
            genderBox = new JComboBox<>(gender);
            genderBox.addActionListener(Employee.this);
            cons.gridx = 1;
            cons.fill = GridBagConstraints.HORIZONTAL;

            add(genderBox, cons);

            JLabel CStatLabel = new JLabel("Civil Status");
            CStatLabel.setFont(new Font("Inter", Font.BOLD, 18));
            CStatLabel.setForeground(Color.WHITE);
            cons.gridx = 2;
            cons.fill = GridBagConstraints.NONE;

            add(CStatLabel, cons);

            String[] CStat = {"Single", "Married", "Legally Separated", "Widow/Widower"};
            CStatBox = new JComboBox<>(CStat);
            CStatBox.addActionListener(Employee.this);
            cons.gridx = 3;
            cons.fill = GridBagConstraints.HORIZONTAL;

            add(CStatBox, cons);

            // TIN
            cons.gridy++;
            cons.gridx = 0;
            addLabelAndTextField(cons, "TIN", "TIN Number", 18);

            // Telephone/Mobile Number
            cons.gridy++;
            addLabelAndTextField(cons, "Tel/Mobile Number", "Telephone/Mobile Number", 18);

            // Next Button
            cons.gridy++;
            cons.gridx = 0;
            cons.gridwidth = 4;
            cons.anchor = GridBagConstraints.CENTER;
            cons.insets = new Insets(20, 350, 0, 350);

            next_button = new JButton("Next");
            next_button.setBackground(new Color(9, 23, 95));
            next_button.setFont(new Font("Inter", Font.BOLD, 14));
            next_button.setForeground(Color.WHITE);
            next_button.setFocusable(false);
            next_button.addActionListener(Employee.this);

            add(next_button, cons);
        
        }

        private void addLabelAndTextField(GridBagConstraints cons, String labelText, String textFieldText, int fontSize) {
            JLabel label = new JLabel(labelText);
            label.setFont(new Font("Inter", Font.BOLD, fontSize));
            label.setForeground(Color.WHITE);
            cons.gridx = 0;
            add(label, cons);

            JTextField textField = new JTextField(textFieldText);
            textField.setFont(new Font("Inter", Font.BOLD, 14));
            textField.setBackground(new Color(244, 244, 244));
            textField.setEnabled(true);
            textField.addFocusListener(new TextFieldFocusListener(textFieldText));
            if (!labelText.equals("Date of Birth")) {
                if (!labelText.equals("Name of Member") && !labelText.equals("Address")) { 
                textField.addKeyListener(new NumericKeyListener());
            }
        }
            
            cons.gridx++;
            cons.fill = GridBagConstraints.HORIZONTAL;

            add(textField, cons);

            cons.gridx = 2;
            cons.gridwidth = 2;
            cons.fill = GridBagConstraints.HORIZONTAL;

            add(Box.createHorizontalStrut(1), cons); 
            cons.gridwidth = 1;

            switch (labelText) {
                case "SS Number":
                    SSNo = textField;
                    break;
                case "Name of Member":
                    MemName = textField;
                    break;
                case "Address":
                    MemAddress = textField;
                    break;
                case "TIN":
                    TIN = textField;
                    break;
                case "Tel/Mobile Number":
                    TelNo = textField;
                    break;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(24, 48, 176, 180)); 

            // Define the width, height, and corner radius of the rectangle
            int rectWidth = 1100; 
            int rectHeight = 480; 
            int arcWidth = 50;
            int arcHeight = 50;

            // Calculate the position to center the rectangle
            int x = (getWidth() - rectWidth) / 2;
            int y = (getHeight() - rectHeight) / 2;

            g2d.fillRoundRect(x, y, rectWidth, rectHeight, arcWidth, arcHeight);
        } 
    }

    class TextFieldFocusListener implements FocusListener {
        private String placeholder;

        public TextFieldFocusListener(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource() instanceof JTextField) {
                JTextField textField = (JTextField) e.getSource();
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.DARK_GRAY);
                }
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() instanceof JTextField) {
                JTextField textField = (JTextField) e.getSource();
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        }
    }

    class NumericKeyListener extends KeyAdapter {
        @Override

        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                e.consume(); // Ignore non-digit characters
            }
        }
    }

    class DateKeyListener extends KeyAdapter {
        @Override

        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != '/' && c != KeyEvent.VK_BACK_SPACE) {
                e.consume(); 
            }
        }
    }

}

