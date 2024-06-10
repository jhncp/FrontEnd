import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FrameManager extends JFrame {

    JPanel bannerPanel1;
    JPanel textPanel1;
    BackgroundPanel backgroundPanel1;

    JTextField empName;
    JTextField empAdd;
    JTextField toField;
    JTextField frmField;
    JTextField cName;
    JTextField cDBirth;
    JTextField cAddress;

    JCheckBox yup;
    JCheckBox nah;

    JCheckBox yup1;
    JCheckBox yup2;
    JCheckBox nah1;
    JCheckBox nah2;

    JComboBox<String> legiBox;
    JComboBox<String> typeBox;

    private JFrame empFrame;
    private JTextField SSNo, MemName, MemAddress, DBirth, PBirth, TIN, TelNo;
    private int numEntries;

    public FrameManager(JFrame empFrame, JTextField SSNo, JTextField MemName, JTextField MemAddress,
                        JTextField DBirth, JTextField PBirth, JTextField TIN, JTextField TelNo) {

        this.empFrame = empFrame;
        this.SSNo = SSNo;
        this.MemName = MemName;
        this.MemAddress = MemAddress;
        this.DBirth = DBirth;
        this.PBirth = PBirth;
        this.TIN = TIN;
        this.TelNo = TelNo;
    }

    public void openNextFrame() {
        if (SSNo.getText().isEmpty() || MemName.getText().isEmpty() ||
                MemAddress.getText().isEmpty() || DBirth.getText().isEmpty() ||
                PBirth.getText().isEmpty() || TIN.getText().isEmpty() ||
                TelNo.getText().isEmpty()) {
            int option = JOptionPane.showConfirmDialog(empFrame, "Looks like some fields are empty. Do you still want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                return;
            }
        }

        JFrame nextFrame = new JFrame("SSS | Retirement Claim Application");
        nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        nextFrame.setSize(1080, 720);
        nextFrame.setLayout(new BorderLayout());
        nextFrame.setLocationRelativeTo(null);

        ImageIcon SSSLogo = new ImageIcon("sss logo.png");
        nextFrame.setIconImage(SSSLogo.getImage());

        backgroundPanel1 = new BackgroundPanel(new ImageIcon("bg.jpg").getImage());
        backgroundPanel1.setLayout(new BorderLayout());

        // Banner panel
        bannerPanel1 = new JPanel();
        bannerPanel1.setBackground(new Color(0, 53, 170));
        bannerPanel1.setLayout(new BorderLayout());

        JLabel logoLabel2 = new JLabel(new ImageIcon("sss logo1.png"));
        logoLabel2.setHorizontalAlignment(SwingConstants.LEFT);

        textPanel1 = new JPanel();
        textPanel1.setLayout(new BoxLayout(textPanel1, BoxLayout.Y_AXIS));
        textPanel1.setOpaque(false);
        JLabel label1 = new JLabel("  Republic of the Philippines");
        label1.setFont(new Font("Inter", Font.PLAIN, 14));
        label1.setForeground(new Color(255, 255, 255));
        label1.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel label2 = new JLabel(" Social Security System");
        label2.setFont(new Font("Inter", Font.BOLD, 20));
        label2.setForeground(new Color(255, 255, 255));
        label2.setHorizontalAlignment(SwingConstants.LEFT);

        textPanel1.add(label1);
        textPanel1.add(label2);

        // Right text label
        JLabel label3 = new JLabel("Retirement Claim ");
        label3.setFont(new Font("Inter", Font.BOLD, 16));
        label3.setForeground(new Color(255, 255, 255));
        label3.setHorizontalAlignment(SwingConstants.RIGHT);

        // Logo and text Panel
        JPanel logoTextPanel = new JPanel(new BorderLayout());
        logoTextPanel.setOpaque(false);
        logoTextPanel.add(logoLabel2, BorderLayout.WEST);
        logoTextPanel.add(textPanel1, BorderLayout.CENTER);

        bannerPanel1.add(logoTextPanel, BorderLayout.WEST);
        bannerPanel1.add(label3, BorderLayout.EAST);

        // Background panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setOpaque(false); 

        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(10, 10, 10, 10);

        // Panel 1: Employment History
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 2;
        cons.gridheight = 1;
        contentPanel.add(createCustomPanel1("Employment History", new Dimension(600, 300)), cons);

        // Panel 2: Dependent Children
        cons.gridx = 2;
        cons.gridy = 0;
        cons.gridwidth = 2;
        contentPanel.add(createCustomPanel2("Dependent Children", new Dimension(600, 300)), cons);

        // Panel 3: First 18 months advance
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 1;
        contentPanel.add(createCustomPanel3("Do you want to receive the first 18 monthly pension in advance?", new Dimension(300, 150),yup1, nah1), cons);

        // Panel 4: Receiving SSS pension
        cons.gridx = 1;
        cons.gridy = 1;
        contentPanel.add(createCustomPanel4("Are you currently receiving SSS pension?", new Dimension(300, 150),yup2, nah2), cons);

        // Panel 5: Type of pension
        cons.gridx = 2;
        cons.gridy = 1;
        cons.gridwidth = 2;
        contentPanel.add(createCustomPanel5("If currently receiving SSS pension, input the type of pension", new Dimension(600, 150)), cons);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(9, 23, 95));
        submitButton.setFont(new Font("Inter", Font.BOLD, 16));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusable(false);
        submitButton.setPreferredSize(new Dimension(150, 50));
        buttonPanel.add(submitButton);

        //empName.addKeyListener(new FieldKeyListener());
        //empAdd.addKeyListener(new FieldKeyListener());
        toField.addKeyListener(new FieldKeyListener());
        frmField.addKeyListener(new FieldKeyListener());
        //cName.addKeyListener(new FieldKeyListener());
        cDBirth.addKeyListener(new FieldKeyListener());
        //cAddress.addKeyListener(new FieldKeyListener());

        empName.addFocusListener(new FieldFocusListener("Employer Name"));
        empAdd.addFocusListener(new FieldFocusListener("Address"));
        toField.addFocusListener(new FieldFocusListener("MM-YYYY"));
        frmField.addFocusListener(new FieldFocusListener("MM-YYYY"));
        cName.addFocusListener(new FieldFocusListener("Child Name"));
        cDBirth.addFocusListener(new FieldFocusListener("MM-DD-YYYY"));
        cAddress.addFocusListener(new FieldFocusListener("Address"));

        backgroundPanel1.add(bannerPanel1, BorderLayout.NORTH);
        backgroundPanel1.add(contentPanel, BorderLayout.CENTER);
        backgroundPanel1.add(buttonPanel, BorderLayout.SOUTH);

        nextFrame.add(backgroundPanel1);
        
        nextFrame.setVisible(true);
        empFrame.setVisible(false);
    }

    private JPanel createCustomPanel1(String title, Dimension size) {
        JPanel mainPanel = new RoundedPanel(size);
        mainPanel.setPreferredSize(size);
        mainPanel.setLayout(new BorderLayout(10, 10));

        JPanel titlePanel = new JPanel(new BorderLayout(10, 10));
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>" + title + "</div></html>");
        int fontSize = (int) (size.height * 0.10);
        titleLabel.setFont(new Font("Inter", Font.BOLD, fontSize));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);

        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(separator, BorderLayout.SOUTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel empNameLabel = new JLabel("Name of Employer");
        empNameLabel.setFont(new Font("Inter", Font.BOLD, 18));
        empNameLabel.setForeground(Color.WHITE);

        empName = new JTextField("Employer Name");
        empName.setFont(new Font("Inter", Font.BOLD, 14));
        empName.setBackground(new Color(244, 244, 244));

        contentPanel.add(empNameLabel);
        contentPanel.add(empName);

        JLabel empAddLabel = new JLabel("Address");
        empAddLabel.setFont(new Font("Inter", Font.BOLD, 18));
        empAddLabel.setForeground(Color.WHITE);

        empAdd = new JTextField("Address");
        empAdd.setFont(new Font("Inter", Font.BOLD, 14));
        empAdd.setBackground(new Color(244, 244, 244));

        contentPanel.add(empAddLabel);
        contentPanel.add(empAdd);

        JLabel prdEmp = new JLabel("Period of Employment");
        prdEmp.setFont(new Font("Inter", Font.BOLD, 18));
        prdEmp.setForeground(Color.WHITE);

        contentPanel.add(prdEmp);
        contentPanel.add(new JLabel());

        JLabel frmEmp = new JLabel("From");
        frmEmp.setFont(new Font("Inter", Font.BOLD, 18));
        frmEmp.setForeground(Color.WHITE);

        frmField = new JTextField("MM-YYYY");
        frmField.setFont(new Font("Inter", Font.BOLD, 14));
        frmField.setBackground(new Color(244, 244, 244));
        frmField.addKeyListener(new FieldKeyListener());

        contentPanel.add(frmEmp);
        contentPanel.add(frmField);

        JLabel toLabel = new JLabel("To");
        toLabel.setFont(new Font("Inter", Font.BOLD, 18));
        toLabel.setForeground(Color.WHITE);

        toField = new JTextField("MM-YYYY");
        toField.setFont(new Font("Inter", Font.BOLD, 14));
        toField.setBackground(new Color(244, 244, 244));
        toField.addKeyListener(new FieldKeyListener());

        contentPanel.add(toLabel);
        contentPanel.add(toField);

        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(9, 23, 95));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusable(false);
        addButton.setPreferredSize(new Dimension(80, 30));
        addButton.addActionListener(e -> {
            if (numEntries >= 5) {

                JOptionPane.showMessageDialog(mainPanel, "You can only add 5 entries.", "Limit Exceeded", JOptionPane.WARNING_MESSAGE);
                return;
            }

                numEntries++;
            if (numEntries >= 5) {
                addButton.setEnabled(false);
            }
        });

        JPanel addButtonPanel = new JPanel();
        addButtonPanel.setOpaque(false);
        addButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addButtonPanel.add(addButton);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(addButtonPanel, BorderLayout.PAGE_END);

        return mainPanel;
    }

    private JPanel createCustomPanel2(String title, Dimension size) {
        JPanel mainPanel = new RoundedPanel(size);
        mainPanel.setPreferredSize(size);
        mainPanel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout(10, 10));
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>" + title + "</div></html>");
        int fontSize = (int) (size.height * 0.10);
        titleLabel.setFont(new Font("Inter", Font.BOLD, fontSize));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);

        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(separator, BorderLayout.SOUTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel childLabel = new JLabel("Name of Children");
        childLabel.setFont(new Font("Inter", Font.BOLD, 18));
        childLabel.setForeground(Color.WHITE);

        cName = new JTextField("Child Name");
        cName.setFont(new Font("Inter", Font.BOLD, 14));
        cName.setBackground(new Color(244, 244, 244));

        contentPanel.add(childLabel);
        contentPanel.add(cName);

        JLabel cDBirthLabel = new JLabel("Date of Birth");
        cDBirthLabel.setFont(new Font("Inter", Font.BOLD, 18));
        cDBirthLabel.setForeground(Color.WHITE);

        cDBirth = new JTextField("MM-DD-YYYY");
        cDBirth.setFont(new Font("Inter", Font.BOLD, 14));
        cDBirth.setBackground(new Color(244, 244, 244));
        cDBirth.addKeyListener(new FieldKeyListener());

        contentPanel.add(cDBirthLabel);
        contentPanel.add(cDBirth);

        JLabel legitLabel = new JLabel("Legitimacy");
        legitLabel.setFont(new Font("Inter", Font.BOLD, 18));
        legitLabel.setForeground(Color.WHITE);

        String[] legit = {"Legitimate", "Illegitimate"};
        legiBox = new JComboBox<>(legit);

        contentPanel.add(legitLabel);
        contentPanel.add(legiBox);

        JLabel cAddLabel = new JLabel("Address");
        cAddLabel.setFont(new Font("Inter", Font.BOLD, 18));
        cAddLabel.setForeground(Color.WHITE);

        cAddress = new JTextField("Address");
        cAddress.setFont(new Font("Inter", Font.BOLD, 14));
        cAddress.setBackground(new Color(244, 244, 244));

        contentPanel.add(cAddLabel);
        contentPanel.add(cAddress);

        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(9, 23, 95));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusable(false);
        addButton.setPreferredSize(new Dimension(80, 30));
        addButton.addActionListener(e -> {
            // Add action logic here
        });

        JPanel addButtonPanel = new JPanel();
        addButtonPanel.setOpaque(false);
        addButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addButtonPanel.add(addButton);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(addButtonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createCustomPanel3(String title, Dimension size, JCheckBox yup1, JCheckBox nah1) {
        JPanel mainPanel = new RoundedPanel(size);
        mainPanel.setPreferredSize(size);
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>" + title + "</div></html>");
        int fontSize = (int) (size.height * 0.10);
        titleLabel.setFont(new Font("Inter", Font.BOLD, fontSize));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        yup1 = new JCheckBox();
        yup1.setOpaque(false);
        yup1.addItemListener(new CheckboxItemListener(new JCheckBox[]{yup1, nah1}, new JCheckBox[0]));

        contentPanel.add(yup1);
        
        JLabel yupLabel = new JLabel("Yes");
        yupLabel.setFont(new Font("Inter", Font.BOLD, 18));
        yupLabel.setForeground(Color.WHITE);
        
        contentPanel.add(yupLabel);

        nah1 = new JCheckBox();
        nah1.setOpaque(false);
        nah1.addItemListener(new CheckboxItemListener(new JCheckBox[]{yup1, nah1}, new JCheckBox[0]));

        contentPanel.add(nah1);

        JLabel nahLabel = new JLabel("No");
        nahLabel.setFont(new Font("Inter", Font.BOLD, 18));
        nahLabel.setForeground(Color.WHITE);

        contentPanel.add(nahLabel);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createCustomPanel4(String title, Dimension size, JCheckBox yup2, JCheckBox nah2) {
        JPanel mainPanel = new RoundedPanel(size);
        mainPanel.setPreferredSize(size);
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>" + title + "</div></html>");
        int fontSize = (int) (size.height * 0.10);
        titleLabel.setFont(new Font("Inter", Font.BOLD, fontSize));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        yup2 = new JCheckBox();
        yup2.setOpaque(false);
        yup2.addItemListener(new CheckboxItemListener(new JCheckBox[]{yup2, nah2}, new JCheckBox[0]));

        contentPanel.add(yup2);
        
        JLabel yupLabel = new JLabel("Yes");
        yupLabel.setFont(new Font("Inter", Font.BOLD, 18));
        yupLabel.setForeground(Color.WHITE);
        
        contentPanel.add(yupLabel);

        nah2 = new JCheckBox();
        nah2.setOpaque(false);
        nah2.addItemListener(new CheckboxItemListener(new JCheckBox[]{yup2, nah2}, new JCheckBox[0]));

        contentPanel.add(nah2);

        JLabel nahLabel = new JLabel("No");
        nahLabel.setFont(new Font("Inter", Font.BOLD, 18));
        nahLabel.setForeground(Color.WHITE);

        contentPanel.add(nahLabel);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createCustomPanel5(String title, Dimension size) {
        JPanel mainPanel = new RoundedPanel(size);
        mainPanel.setPreferredSize(size);
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>" + title + "</div></html>");
        int fontSize = (int) (size.height * 0.12);
        titleLabel.setFont(new Font("Inter", Font.BOLD, fontSize));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridLayout(1, 2, 10, 10)); 
        contentPanel.setBorder(BorderFactory.createEmptyBorder(60, 10, 20, 10));

        JLabel tyPenLabel = new JLabel("Type of Pension");
        tyPenLabel.setFont(new Font("Inter", Font.BOLD, 18));
        tyPenLabel.setForeground(Color.WHITE);


        contentPanel.add(tyPenLabel);

        String[] type = {"Disability", "Death"};
        typeBox = new JComboBox<>(type);
        typeBox.setSize(500, 55);

        contentPanel.add(typeBox);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    class RoundedPanel extends JPanel {
        private Dimension size;

        public RoundedPanel(Dimension size) {
            this.size = size;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(0, 53, 170, 180)); 

            int arcWidth = 50;
            int arcHeight = 50;
            int x = 0;
            int y = 0;
            int width = size.width;
            int height = size.height;

            g2d.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
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

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            JTextField textField = (JTextField) e.getSource();
            if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '-')) {
                e.consume();
            }
            if (textField.getText().length() >= 7 && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                e.consume();
            }
        }
    }

    class CheckboxItemListener implements ItemListener {
        private JCheckBox[] jCheckBoxs;
        private JCheckBox[] jCheckBoxs2;

        public CheckboxItemListener(JCheckBox[] jCheckBoxs, JCheckBox[] jCheckBoxs2) {
            this.jCheckBoxs = jCheckBoxs;
            this.jCheckBoxs2 = jCheckBoxs2;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox source = (JCheckBox) e.getSource();

        if (source.isSelected()) {
            if (source == jCheckBoxs[0]) {
                jCheckBoxs[1].setSelected(false);
            } else if (source == jCheckBoxs[1]) {
                jCheckBoxs[0].setSelected(false);
            } else if (source == jCheckBoxs2[0]) {
                jCheckBoxs2[1].setSelected(false);
            } else if (source == jCheckBoxs2[1]) {
                jCheckBoxs2[0].setSelected(false);
            }
        }
    }
}

    class FieldFocusListener implements FocusListener {
        private String placeholder;

        public FieldFocusListener(String placeholder){
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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JTextField ssNo = new JTextField(10);
        JTextField memName = new JTextField(10);
        JTextField memAddress = new JTextField(10);
        JTextField dBirth = new JTextField(10);
        JTextField pBirth = new JTextField(10);
        JTextField tin = new JTextField(10);
        JTextField telNo = new JTextField(10);

        frame.add(new JLabel("SS No:"));
        frame.add(ssNo);
        frame.add(new JLabel("Name:"));
        frame.add(memName);
        frame.add(new JLabel("Address:"));
        frame.add(memAddress);
        frame.add(new JLabel("Date of Birth:"));
        frame.add(dBirth);
        frame.add(new JLabel("Place of Birth:"));
        frame.add(pBirth);
        frame.add(new JLabel("TIN:"));
        frame.add(tin);
        frame.add(new JLabel("Tel No:"));
        frame.add(telNo);

        JButton nextButton = new JButton("Next");
        frame.add(nextButton);

        FrameManager manager = new FrameManager(frame, ssNo, memName, memAddress, dBirth, pBirth, tin, telNo);
        nextButton.addActionListener(e -> manager.openNextFrame());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}



