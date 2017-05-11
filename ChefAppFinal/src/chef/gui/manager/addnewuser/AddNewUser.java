/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.gui.manager.addnewuser;

import chef.API.APIPostPerson;
import chef.datamodel.Person;
import chef.gui.manager.MainMenu;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author rjones14
 */
public class AddNewUser extends JFrame {

    private int width, height, center, y;

    private JLabel lblTitle = new JLabel("Add New User");
    private JLabel lblForename = new JLabel("Forename:");
    private JLabel lblSurname = new JLabel("Surname:");
    private JLabel lblDOB = new JLabel("DOB:");
    private JLabel lblEmail = new JLabel("Email:");
    private JLabel lblMobileNumber = new JLabel("Mobile Number:");
    private JLabel lblTownCity = new JLabel("Town/City:");
    private JLabel lblCounty = new JLabel("County:");
    private JLabel lblPostcode = new JLabel("Post Code:");
    private JLabel lblAddress1 = new JLabel("Address 1:");
    private JLabel lblAddress2 = new JLabel("Address 2:");
    private JLabel lblUsername = new JLabel("Username:");
    private JLabel lblPassword = new JLabel("Password:");
    private JLabel lblPassword2 = new JLabel("Password 2:");

    private JTextField txtForename = new JTextField();
    private JTextField txtSurname = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtMobileNumber = new JTextField();
    private JTextField txtTownCity = new JTextField();
    private JTextField txtCounty = new JTextField();
    private JTextField txtPostcode = new JTextField();
    private JTextField txtAddress1 = new JTextField();
    private JTextField txtAddress2 = new JTextField();
    private JTextField txtUsername = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private JPasswordField txtPassword2 = new JPasswordField();

    private JButton save = new JButton("Save");
    private JButton cancel = new JButton("Cancel");

    public AddNewUser() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        width = 400;
        height = 550;
        center = width / 2;
        y = 50;
        setSize(width, height);
        setTitle("Add new user");
        centerWindow();
        drawScreen();
    }

    private void drawScreen() {
        JLabel lblTitle = new JLabel("Add New User");
        JLabel lblForename = new JLabel("Forename:");
        JLabel lblSurname = new JLabel("Surname:");
        JLabel lblDOB = new JLabel("DOB:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblMobileNumber = new JLabel("Mobile Number:");
        JLabel lblTownCity = new JLabel("Town/City:");
        JLabel lblCounty = new JLabel("County:");
        JLabel lblPostcode = new JLabel("Post Code:");
        JLabel lblAddress1 = new JLabel("Address 1:");
        JLabel lblAddress2 = new JLabel("Address 2:");
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblPassword2 = new JLabel("Confirm Password:");
        JLabel lblUserLevel = new JLabel("UserL Level");

        JTextField txtForename = new JTextField();
        JTextField txtSurname = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtMobileNumber = new JTextField();
        JTextField txtTownCity = new JTextField();
        JTextField txtCounty = new JTextField();
        JTextField txtPostcode = new JTextField();
        JTextField txtAddress1 = new JTextField();
        JTextField txtAddress2 = new JTextField();
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        JPasswordField txtPassword2 = new JPasswordField();
        String[] userLevelArray = new String[]{"Driver", "Chef", "Admin"};
        JComboBox userLevel = new JComboBox(userLevelArray);
        
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = new Person("1",txtForename.getText(),txtSurname.getText(),"10-MAY-17"
                                        ,txtEmail.getText(),txtMobileNumber.getText(),txtTownCity.getText(),txtCounty.getText(),txtPostcode.getText()
                                        ,txtAddress1.getText());
                /*person.setDOB("10-MAY-17");
                System.out.println(txtForename.getText());
                person.setForename(txtForename.getText());
                person.setSurname(txtSurname.getText());
                person.setPerson_ID("P9999");
                person.setAddress1(txtAddress1.getText());
                person.setCounty(txtCounty.getText());
                person.setEmail(txtEmail.getText());
                person.setMobile_Number(txtMobileNumber.getText());
                person.setPostcode(txtPostcode.getText());
                person.setTown_City(txtTownCity.getText());
                */

                POST(person);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

        add(lblTitle);
        add(lblForename);
        add(txtForename);
        add(lblSurname);
        add(txtSurname);
        add(lblEmail);
        add(txtEmail);
        add(lblMobileNumber);
        add(txtMobileNumber);
        add(lblTownCity);
        add(txtTownCity);
        add(lblCounty);
        add(txtCounty);
        add(lblPostcode);
        add(txtPostcode);
        add(lblAddress1);
        add(txtAddress1);
        add(lblAddress2);
        add(txtAddress2);
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(lblPassword2);
        add(txtPassword2);
        add(save);
        add(cancel);

        lblTitle.setSize(100, 40);
        lblForename.setSize(100, 20);
        txtForename.setSize(100, 20);
        lblSurname.setSize(100, 20);
        txtSurname.setSize(100, 20);
        lblEmail.setSize(100, 20);
        txtEmail.setSize(100, 20);
        lblMobileNumber.setSize(100, 20);
        txtMobileNumber.setSize(100, 20);
        lblTownCity.setSize(100, 20);
        txtTownCity.setSize(100, 20);
        lblCounty.setSize(100, 20);
        txtCounty.setSize(100, 20);
        lblPostcode.setSize(100, 20);
        txtPostcode.setSize(100, 20);
        lblAddress1.setSize(100, 20);
        txtAddress1.setSize(100, 20);
        lblAddress2.setSize(100, 20);
        txtAddress2.setSize(100, 20);
        lblUsername.setSize(100, 20);
        txtUsername.setSize(100, 20);
        lblPassword.setSize(100, 20);
        txtPassword.setSize(100, 20);
        lblPassword2.setSize(130, 20);
        txtPassword2.setSize(100, 20);
        save.setSize(75, 40);
        cancel.setSize(75, 40);
        //JDatePicker datePicker = new JDatePicker();

        lblTitle.setLocation(center - 50, 10);

        lblForename.setLocation(center - 130, y);
        txtForename.setLocation(center, y);
        y += 25;
        lblSurname.setLocation(center - 130, y);
        txtSurname.setLocation(center, y);
        y += 25;
        lblUsername.setLocation(center - 130, y);
        txtUsername.setLocation(center, y);
        y += 25;
        lblPassword.setLocation(center - 130, y);
        txtPassword.setLocation(center, y);
        y += 25;
        lblPassword2.setLocation(center - 130, y);
        txtPassword2.setLocation(center, y);
        y += 25;
        lblDOB.setLocation(center - 130, y);
        y += 25;
        lblEmail.setLocation(center - 130, y);
        txtEmail.setLocation(center, y);
        y += 25;
        lblMobileNumber.setLocation(center - 130, y);
        txtMobileNumber.setLocation(center, y);
        y += 25;
        lblTownCity.setLocation(center - 130, y);
        txtTownCity.setLocation(center, y);
        y += 25;
        lblCounty.setLocation(center - 130, y);
        txtCounty.setLocation(center, y);
        y += 25;
        lblPostcode.setLocation(center - 130, y);
        txtPostcode.setLocation(center, y);
        y += 25;
        lblAddress1.setLocation(center - 130, y);
        txtAddress1.setLocation(center, y);
        y += 25;
        lblAddress2.setLocation(center - 130, y);
        txtAddress2.setLocation(center, y);

        save.setLocation(width - 100, height - 100);
        cancel.setLocation(width - 185, height - 100);
    }

    public void saveUser() {

        Person person = new Person();
        System.out.println(txtForename.getText());
        person.setForename(txtForename.getText());
        person.setSurname(txtSurname.getText());
        person.setPerson_ID("P1");
        person.setAddress1(txtAddress1.getText());
        person.setCounty(txtCounty.getText());
        person.setEmail(txtEmail.getText());
        person.setMobile_Number(txtMobileNumber.getText());
        person.setPostcode(txtPostcode.getText());
        person.setTown_City(txtTownCity.getText());

        POST(person);
    }

    private void POST(Person person) {
        new APIPostPerson(person);
    }

    public void close() {
        new MainMenu().setVisible(true);
        this.dispose();
    }

    private void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }
}
