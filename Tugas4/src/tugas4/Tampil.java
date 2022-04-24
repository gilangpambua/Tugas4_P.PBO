package tugas4;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tampil extends JFrame{
    public String id, username, password;

    JLabel luser = new JLabel("Username ");
    JLabel lpass = new JLabel("Password");

    JLabel lnuser = new JLabel("Username");
    JLabel lnpass = new JLabel("Password ");

    JTextField tfUser = new JTextField();
    JTextField tfPass = new JTextField();
    JTextField tfnuser = new JTextField();
    JTextField tfnpass = new JTextField();

    JButton btnLogin = new JButton("Login");
    JButton btnRegis = new JButton("Register");
    String DBtabel = "users";
    String query;
    String[][] data = new String[2][2];
    
    Connector connector = new Connector();
    
    public Tampil(){
        setTitle("Tugas4 JDBC");
        setLayout(null);
        setSize(480 , 480);

        add(luser);
        add(lpass);
        
        add(lnuser);
        add(lnpass);
      
        add(tfPass);
        add(tfUser);
        add(tfnuser);
        add(tfnpass);
        add(btnLogin);
        add(btnRegis);

        luser.setBounds(20,15,100,25);
        tfUser.setBounds(160,15,150,25);
        lpass.setBounds(20,60,100,25);
        tfPass.setBounds(160,60,150,25);     
        btnLogin.setBounds(20,100,80,30);
        
        lnuser.setBounds(20,170,100,25);
        tfnuser.setBounds(160 ,170,150,25);
        lnpass.setBounds(20,205,100,25);
        tfnpass.setBounds(160,205,150,25);
        btnRegis.setBounds(20,240,100,30);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connector.statement = connector.koneksi.createStatement();
                    query = "select 'username' from " + DBtabel + " where username = '" + getUser() + "';";
                    ResultSet result_user = connector.statement.executeQuery(query);
                    while (result_user.next())
                        data[0][0] = result_user.getString("username");
                    query = "select 'password' from " + DBtabel + " where password = '" + getPass() + "';";
                    ResultSet result_pass = connector.statement.executeQuery(query);
                    while (result_pass.next())
                        data[0][1] =result_pass.getString("password");
                    if (getUser().isEmpty()|| getPass().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Silahkan input data");
                    }else if (data[0][0] == null){
                        JOptionPane.showMessageDialog(null,"Username tidak ditemukan");
                    } else if (data[0][1] == null){
                        JOptionPane.showMessageDialog(null,"Password Salah");
                    } else {
                        JOptionPane.showMessageDialog(null,"Login berhasil");
                    }
                    data[0][0]= null; data[0][1]= null;
                } catch (SQLException e1){
                    System.out.println(e1.getMessage());
                }
            }
        });

        btnRegis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connector.statement = connector.koneksi.createStatement();
                    query = "select 'username' from " + DBtabel + " where username = '" + getnuser() + "';";
                    ResultSet result_user = connector.statement.executeQuery(query);
                    while (result_user.next())
                        data[0][0] = result_user.getString("username");
                    if (getnuser().isEmpty()|| getnpass().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Silahkan input data");
                    }else if(data[0][0] != null){
                        JOptionPane.showMessageDialog(null,"Username telah digunakan");
                    } else {
                        query = "Insert  into " + DBtabel + " (username, password) Values ('" + getnuser() + "','" + getnpass() + "')";
                        connector.statement.executeUpdate(query);
                        JOptionPane.showMessageDialog(null,"Registrasi berhasil");
                    }
                    data[0][0] = null;
                }catch (SQLException e2){
                    System.out.println(e2.getMessage());
                }

            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getUser(){
        return tfUser.getText();
    }

    public String getPass(){
        return tfPass.getText();
    }
    
    public String getnuser(){return tfnuser.getText();}
    public String getnpass(){return tfnpass.getText();}
}
