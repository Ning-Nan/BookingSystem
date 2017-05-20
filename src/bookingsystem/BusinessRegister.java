/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author msi-pc
 */
public class BusinessRegister extends javax.swing.JFrame {

    /**
     * Creates new form BusinessRegister
     */
    public BusinessRegister() {
        initComponents();
        jLabel1.requestFocusInWindow();
        passwordField.setEchoChar((char)0);
        confirmPasswordField.setEchoChar((char)0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        businessNameField = new javax.swing.JTextField();
        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        usernameField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        addressField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();
        registerButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        phoneNumberField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        startCB = new javax.swing.JComboBox<>();
        endCB = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        mondayCheckBox = new javax.swing.JCheckBox();
        tuesdayCheckBox = new javax.swing.JCheckBox();
        wednesdayCheckBox = new javax.swing.JCheckBox();
        thursdayCheckBox = new javax.swing.JCheckBox();
        fridayCheckBox = new javax.swing.JCheckBox();
        saturdayCheckBox = new javax.swing.JCheckBox();
        sundayCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Business Register");

        businessNameField.setText("Company Name");
        businessNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                businessNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                businessNameFieldFocusLost(evt);
            }
        });

        firstNameField.setText("First Name");
        firstNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstNameFieldFocusLost(evt);
            }
        });

        lastNameField.setText("Last Name");
        lastNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameFieldFocusLost(evt);
            }
        });

        usernameField.setText("Owner Username");
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFieldFocusLost(evt);
            }
        });

        emailField.setText("Owner E-mail");
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFieldFocusLost(evt);
            }
        });
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        addressField.setText("Address");
        addressField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addressFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addressFieldFocusLost(evt);
            }
        });

        passwordField.setText("Password");
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
        });
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        confirmPasswordField.setText("Confirm Password");
        confirmPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmPasswordFieldFocusGained(evt);
            }
        });

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        phoneNumberField.setText("Phone Number");
        phoneNumberField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                phoneNumberFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                phoneNumberFieldFocusLost(evt);
            }
        });

        jLabel2.setText("Business Hour:");

        startCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Start Time", "12:00am", "1:00am", "2:00am", "3:00am", "4:00am", "5.00 a.m.", "6.00 a.m.", "7.00 a.m.", "8.00 a.m.", "9.00 a.m.", "10.00 a.m.", "11.00 a.m.", "12.00 p.m.", "1:00 pm", "2:00 pm", "3:00 pm", "4:00 pm", "5:00 pm", "6:00 pm", "7:00 pm", "8:00 pm", "9:00 pm", "10:00 pm", "11:00 pm", "12:00 pm" }));

        endCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose End Time", "12:00am", "1:00am", "2:00am", "3:00am", "4:00am", "5.00 a.m.", "6.00 a.m.", "7.00 a.m.", "8.00 a.m.", "9.00 a.m.", "10.00 a.m.", "11.00 a.m.", "12.00 p.m.", "1:00 pm", "2:00 pm", "3:00 pm", "4:00 pm", "5:00 pm", "6:00 pm", "7:00 pm", "8:00 pm", "9:00 pm", "10:00 pm", "11:00 pm", "12:00 am" }));

        jLabel3.setText("Business Date:");

        mondayCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        mondayCheckBox.setText("Monday");

        tuesdayCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        tuesdayCheckBox.setText("Tuesday");

        wednesdayCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        wednesdayCheckBox.setText("Wednesday");

        thursdayCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        thursdayCheckBox.setText("Thursday");

        fridayCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        fridayCheckBox.setText("Friday");

        saturdayCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        saturdayCheckBox.setText("Saturday");

        sundayCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        sundayCheckBox.setText("Sunday");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastNameField))
                            .addComponent(businessNameField)
                            .addComponent(usernameField)
                            .addComponent(addressField)
                            .addComponent(emailField)
                            .addComponent(confirmPasswordField)
                            .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(phoneNumberField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(startCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wednesdayCheckBox)
                                    .addComponent(mondayCheckBox)
                                    .addComponent(fridayCheckBox)
                                    .addComponent(sundayCheckBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tuesdayCheckBox)
                                    .addComponent(thursdayCheckBox)
                                    .addComponent(saturdayCheckBox))))
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(businessNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(startCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mondayCheckBox)
                    .addComponent(jLabel3)
                    .addComponent(tuesdayCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wednesdayCheckBox)
                    .addComponent(thursdayCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fridayCheckBox)
                    .addComponent(saturdayCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sundayCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleName("BusinessRegister");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        if (endCB.getSelectedIndex() < startCB.getSelectedIndex()) {
            JOptionPane.showMessageDialog(this,
                    "End time must be after start time", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;            
        }
        
        int start = startCB.getSelectedIndex() - 1;
        int close = endCB.getSelectedIndex() - 1;
        
        String daysArr[] = new String[7];
        daysArr[0] = sundayCheckBox.isSelected() ? "1" : "0";
        daysArr[1] = mondayCheckBox.isSelected() ? "1" : "0";
        daysArr[2] = tuesdayCheckBox.isSelected() ? "1" : "0";
        daysArr[3] = wednesdayCheckBox.isSelected() ? "1" : "0";
        daysArr[4] = thursdayCheckBox.isSelected() ? "1" : "0";
        daysArr[5] = fridayCheckBox.isSelected() ? "1" : "0";
        daysArr[6] = saturdayCheckBox.isSelected() ? "1" : "0";
        
        String days = String.join(",", daysArr);
        
        try {
            Business.register(businessNameField.getText(), usernameField.getText(),
                    String.valueOf(passwordField.getPassword()), String.valueOf(confirmPasswordField.getPassword()), addressField.getText(),
                    phoneNumberField.getText(), emailField.getText(), start, close, days);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Registration successful!", "",
                JOptionPane.PLAIN_MESSAGE);

        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_registerButtonActionPerformed

    private void businessNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_businessNameFieldFocusGained
        // TODO add your handling code here:

        if (businessNameField.getText().equals("Company Name")) {
            businessNameField.setText("");
        }
    }//GEN-LAST:event_businessNameFieldFocusGained

    private void firstNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFieldFocusGained
        // TODO add your handling code here:
        if (firstNameField.getText().equals("First Name")) {
            firstNameField.setText("");
        }
    }//GEN-LAST:event_firstNameFieldFocusGained

    private void lastNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFieldFocusGained
        if (lastNameField.getText().equals("Last Name")) {
            lastNameField.setText("");
        }
    }//GEN-LAST:event_lastNameFieldFocusGained

    private void usernameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFieldFocusGained
        if (usernameField.getText().equals("Owner Username")) {
            usernameField.setText("");
        }
    }//GEN-LAST:event_usernameFieldFocusGained

    private void emailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusGained
        if (emailField.getText().equals("Owner E-mail")) {
            emailField.setText("");
        }
    }//GEN-LAST:event_emailFieldFocusGained

    private void addressFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressFieldFocusGained
        if (addressField.getText().equals("Address")) {
            addressField.setText("");
        }
    }//GEN-LAST:event_addressFieldFocusGained

    private void phoneNumberFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneNumberFieldFocusGained
        if (phoneNumberField.getText().equals("Phone Number")) {
            phoneNumberField.setText("");
        }
    }//GEN-LAST:event_phoneNumberFieldFocusGained

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained
        if (passwordField.getEchoChar() == (char) 0) {
            passwordField.setText("");
            passwordField.setEchoChar('*');
        }
    }//GEN-LAST:event_passwordFieldFocusGained

    private void confirmPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPasswordFieldFocusGained
        if (confirmPasswordField.getEchoChar() == (char) 0) {
            confirmPasswordField.setText("");
            confirmPasswordField.setEchoChar('*');
        }
    }//GEN-LAST:event_confirmPasswordFieldFocusGained

    private void businessNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_businessNameFieldFocusLost
        if (businessNameField.getText().equals("")) {
            businessNameField.setText("Company Name");
        }
    }//GEN-LAST:event_businessNameFieldFocusLost

    private void firstNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFieldFocusLost
        if (firstNameField.getText().equals("")) {
            firstNameField.setText("First Name");
        }
    }//GEN-LAST:event_firstNameFieldFocusLost

    private void lastNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFieldFocusLost
       if (lastNameField.getText().equals("")) {
            lastNameField.setText("Last Name");
        }
    }//GEN-LAST:event_lastNameFieldFocusLost

    private void usernameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFieldFocusLost
        if (usernameField.getText().equals("")) {
            usernameField.setText("Owner Username");
        }
    }//GEN-LAST:event_usernameFieldFocusLost

    private void emailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusLost
       if (emailField.getText().equals("")) {
            emailField.setText("Owner E-mail");
        }
    }//GEN-LAST:event_emailFieldFocusLost

    private void addressFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressFieldFocusLost
        if (addressField.getText().equals("")) {
            addressField.setText("Address");
        }
    }//GEN-LAST:event_addressFieldFocusLost

    private void phoneNumberFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneNumberFieldFocusLost
        if (phoneNumberField.getText().equals("")) {
            phoneNumberField.setText("Phone Number");
        }
    }//GEN-LAST:event_phoneNumberFieldFocusLost

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BusinessRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BusinessRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BusinessRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BusinessRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BusinessRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JTextField businessNameField;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JTextField emailField;
    private javax.swing.JComboBox<String> endCB;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JCheckBox fridayCheckBox;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JCheckBox mondayCheckBox;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JButton registerButton;
    private javax.swing.JCheckBox saturdayCheckBox;
    private javax.swing.JComboBox<String> startCB;
    private javax.swing.JCheckBox sundayCheckBox;
    private javax.swing.JCheckBox thursdayCheckBox;
    private javax.swing.JCheckBox tuesdayCheckBox;
    private javax.swing.JTextField usernameField;
    private javax.swing.JCheckBox wednesdayCheckBox;
    // End of variables declaration//GEN-END:variables
}
