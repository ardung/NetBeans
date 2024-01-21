/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.report_card_maker_ah;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import javax.swing.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
/**
 *
 * @author 342513926
 */
public class MainEvent extends javax.swing.JFrame {
    static String c1, c2, c3, c4, grade, fName, lName, fullName;
    double a1, a2, a3, a4;
    static int index=0;
    public static ArrayList<ArrayList<String>> data = new ArrayList<>();
    public static DefaultListModel model;    

    /**
     * Creates new form NewJFrame
     */
    @SuppressWarnings("empty-statement")
    
    public static void updateFile(){
        try {
            FileWriter pw = new FileWriter("save.txt",false);
            for (int i=0;i<data.size();i++){
                for(int j=0;j<=8;j++){
                pw.write(data.get(i).get(j) + "|");
                } pw.write(data.get(i).get(9));
                pw.write("~");
            }
            pw.write(c1);
            pw.write("$");
            pw.write(c2);
            pw.write("$");
            pw.write(c3);
            pw.write("$");
            pw.write(c4);
            pw.write("$");
            pw.write(grade);
            pw.close();
        } catch (IOException e){
        }
    }
    public static boolean readFromFile(){
        try {
            File file = new File("save.txt");
            Scanner input = new Scanner(file);
            String[] parts1;
            String line = "";
            while (input.hasNextLine()){
                line+=input.nextLine();
            }
            if (line.isEmpty()){
                return true;
            }
            parts1 = line.split("~");
            String[][] parts2 = new String[parts1.length][];
            for (int i=0;i<parts1.length-1;i++){
                parts2[i] = parts1[i].split("\\|");
            } parts2[parts1.length-1] = parts1[parts1.length-1].split("\\$");
            for (int i=0;i<parts2.length-1;i++){
                data.add(new ArrayList<>());
                for (int j=0;j<10;j++){
                    data.get(i).add(j,parts2[i][j]);
                }
            }data.remove(data.size()-1);
            for (int i=0;i<data.size();i++){
                model.addElement(data.get(i).get(0));
            }
            c1 = parts2[parts2.length-1][0];
            c2 = parts2[parts2.length-1][1];
            c3 = parts2[parts2.length-1][2];
            c4 = parts2[parts2.length-1][3];
            grade = parts2[parts2.length-1][4];
            index = data.size()-1;
        } catch(FileNotFoundException e){
        }
        return false;
    }
    
    public static void Calculator(int ind){
        ArrayList<ArrayList<JTextField>> boxes = new ArrayList<>();
        JFrame calculator = new JFrame("");
        calculator.setResizable(false);
        JButton add = new JButton("Add");
        JButton calculate = new JButton("Calculate");
        JTextField result = new JTextField("",10);
        result.setFocusable(false);
        result.setEditable(false);
        calculator.add(calculate);
        calculator.add(add);
        calculator.add(result);
        calculator.add(new JLabel(" Grade      ",SwingConstants.LEFT));
        calculator.add(new JLabel("    Weight",SwingConstants.RIGHT));
        calculator.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        int width = 200, height = 300;
        calculator.setSize(width,height);
        FlowLayout flowLayout = new FlowLayout();
        calculator.setLayout(flowLayout);
        calculator.setVisible(true);
        for(int x = 0; x<5; x++){
            boxes.add(new ArrayList<>());
            for(int y = 0;y<2; y++){
                boxes.get(x).add(new JTextField(5));
                calculator.add(boxes.get(x).get(y));
            }
        }
        add.addActionListener((ActionEvent e) -> {
            boxes.add(new ArrayList<>());
            add.setVisible(false);
            result.setVisible(false);
            calculate.setVisible(false);
            for(int i = 0;i<2;i++){
                boxes.get(boxes.size()-1).add(new JTextField(5));
                calculator.add(boxes.get(boxes.size()-1).get(i));
            }
            add.setVisible(true);
            result.setVisible(true);
            calculate.setVisible(true);
            int h = calculator.getHeight();
            calculator.setSize(200,h+=33);
        });
        calculate.addActionListener((ActionEvent a) -> {
            try{
            int size = boxes.size(); 
            double weightTotal=0;
            double finalGrade=0;
            boolean valid = true;
            for (int i=0;i<size;i++){
                String gradeString,weightString;
                double score;
                double weight;
                if (boxes.get(i).get(0).getText().isEmpty() || 
                        boxes.get(i).get(1).getText().isEmpty()){
                        break;
                }else{
                    gradeString = boxes.get(i).get(0).getText();
                    score = Double.parseDouble(gradeString);
                    weightString = boxes.get(i).get(1).getText();
                    weight = Double.parseDouble(weightString);
                }
                weightTotal+=weight;
                finalGrade += score*(weight/100.0);
                if (weightTotal>100){
                    valid = false;
                    break;
                }
                
            }
            if (weightTotal<100){
                finalGrade+= 100*((100-weightTotal)/100);
            }
            finalGrade = (Math.round(finalGrade*10))/10.0;
            if (valid){
                result.setText(Double.toString(finalGrade));
                switch (ind) {
                    case 1 -> c1ScoreField.setText(Double.toString(finalGrade));
                    case 2 -> c2ScoreField.setText(Double.toString(finalGrade));
                    case 3 -> c3ScoreField.setText(Double.toString(finalGrade));
                    case 4 -> c4ScoreField.setText(Double.toString(finalGrade));
                    default -> {
                    }
                }
            }else{
                result.setText("Invalid data");
            }
            }
            catch(NumberFormatException e){
                result.setText("Invalid data");
            }
        });
       
    }
    public MainEvent() {
        initComponents();
        studentList.setModel(model);
        c1NameField.setText(c1);
        c2NameField.setText(c2);
        c3NameField.setText(c3);
        c4NameField.setText(c4);
        gradeField.setText(grade);
        errorText.setText("");
        if(readFromFile()){
            saveButton.setEnabled(true);
        }
        export.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        gradeField = new javax.swing.JTextField();
        fNameField = new javax.swing.JTextField();
        lNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        studentNumberField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        c3NameField = new javax.swing.JTextField();
        c3ScoreField = new javax.swing.JTextField();
        c3AvgField = new javax.swing.JTextField();
        c3CommentField = new javax.swing.JTextField();
        c3CalcButton = new javax.swing.JButton();
        c4NameField = new javax.swing.JTextField();
        c4ScoreField = new javax.swing.JTextField();
        c4CalcButton = new javax.swing.JButton();
        c4AvgField = new javax.swing.JTextField();
        c4CommentField = new javax.swing.JTextField();
        c1NameField = new javax.swing.JTextField();
        c1ScoreField = new javax.swing.JTextField();
        c1AvgField = new javax.swing.JTextField();
        c1CommentField = new javax.swing.JTextField();
        c1CalcButton = new javax.swing.JButton();
        c2NameField = new javax.swing.JTextField();
        c2ScoreField = new javax.swing.JTextField();
        c2CalcButton = new javax.swing.JButton();
        c2AvgField = new javax.swing.JTextField();
        c2CommentField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        newStudent = new javax.swing.JButton();
        search = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentList = new javax.swing.JList<>();
        export = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Report Cards");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel1.setText("Report Card Maker");

        jLabel2.setText("First Name:");

        jLabel3.setText("Last Name:");

        jLabel4.setText("Course 1:");

        jLabel5.setText("Course 2:");

        jLabel6.setText("Course 3:");

        jLabel7.setText("Course 4:");

        jLabel8.setText("Course Name");

        jLabel9.setText("Score");

        jLabel10.setText("Grade:");

        jLabel11.setText("Student #:");

        jLabel12.setText("Average");

        jLabel13.setText("Comments");

        jPanel1.setLayout(null);

        c3NameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3NameFieldActionPerformed(evt);
            }
        });

        c3AvgField.setEditable(false);

        c3CalcButton.setFont(new java.awt.Font("sansserif", 0, 8)); // NOI18N
        c3CalcButton.setText("Calc");
        c3CalcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3CalcButtonActionPerformed(evt);
            }
        });

        c4NameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4NameFieldActionPerformed(evt);
            }
        });

        c4CalcButton.setFont(new java.awt.Font("sansserif", 0, 8)); // NOI18N
        c4CalcButton.setText("Calc");
        c4CalcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4CalcButtonActionPerformed(evt);
            }
        });

        c4AvgField.setEditable(false);

        c1NameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1NameFieldActionPerformed(evt);
            }
        });

        c1AvgField.setEditable(false);

        c1CalcButton.setFont(new java.awt.Font("sansserif", 0, 8)); // NOI18N
        c1CalcButton.setText("Calc");
        c1CalcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1CalcButtonActionPerformed(evt);
            }
        });

        c2NameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2NameFieldActionPerformed(evt);
            }
        });

        c2CalcButton.setFont(new java.awt.Font("sansserif", 0, 8)); // NOI18N
        c2CalcButton.setText("Calc");
        c2CalcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2CalcButtonActionPerformed(evt);
            }
        });

        c2AvgField.setEditable(false);

        saveButton.setText("Save");
        saveButton.setEnabled(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        newStudent.setText("New Student");
        newStudent.setEnabled(false);
        newStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newStudentActionPerformed(evt);
            }
        });

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 51, 51));
        errorText.setText("ErrorText");

        studentList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(studentList);

        export.setText("Export");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newStudent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(export))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel3))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(gradeField, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(studentNumberField, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(lNameField)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel8)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel9)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel12)
                                .addGap(74, 74, 74)
                                .addComponent(jLabel13))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(c4NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(c4ScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(c4AvgField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(c4CommentField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(c4CalcButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(c2NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(c2ScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(c2AvgField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(c1NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(c1ScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(c1AvgField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(c2CommentField, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                            .addComponent(c1CommentField))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(c1CalcButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(c2CalcButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(c3NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(c3ScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(c3AvgField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(c3CommentField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(c3CalcButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {c1CalcButton, c2CalcButton, c3CalcButton, c4CalcButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(gradeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(studentNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c1NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c1ScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c1CommentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c1CalcButton)
                    .addComponent(c1AvgField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c2NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c2ScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c2CommentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c2CalcButton)
                    .addComponent(c2AvgField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(c3NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c3ScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c3CommentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c3CalcButton)
                    .addComponent(c3AvgField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(c4NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c4ScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c4CommentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c4CalcButton)
                    .addComponent(c4AvgField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(newStudent)
                    .addComponent(search)
                    .addComponent(errorText)
                    .addComponent(export))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void c3NameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c3NameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c3NameFieldActionPerformed

    private void c3CalcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c3CalcButtonActionPerformed
        // TODO add your handling code here:
        Calculator(3);
    }//GEN-LAST:event_c3CalcButtonActionPerformed

    private void c4NameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4NameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c4NameFieldActionPerformed

    private void c4CalcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4CalcButtonActionPerformed
        // TODO add your handling code here:
        Calculator(4);
    }//GEN-LAST:event_c4CalcButtonActionPerformed

    private void c1NameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1NameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c1NameFieldActionPerformed

    private void c1CalcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1CalcButtonActionPerformed
        // TODO add your handling code here:
        Calculator(1);
    }//GEN-LAST:event_c1CalcButtonActionPerformed

    private void c2NameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2NameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c2NameFieldActionPerformed

    private void c2CalcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2CalcButtonActionPerformed
        // TODO add your handling code here:
        Calculator(2);
    }//GEN-LAST:event_c2CalcButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        if (!c1ScoreField.getText().isEmpty() || !c2ScoreField.getText().isEmpty() || !c3ScoreField.getText().isEmpty() || !c4ScoreField.getText().isEmpty()){
            try{
            Double.valueOf(c1ScoreField.getText());
            Double.valueOf(c2ScoreField.getText());
            Double.valueOf(c3ScoreField.getText());
            Double.valueOf(c4ScoreField.getText());
            } catch(NumberFormatException e){
                errorText.setText("Course grade must be a number");
                return;
            }
        }
        if (!fNameField.getText().isEmpty() || !lNameField.getText().isEmpty()){
            c1 = c1NameField.getText();
            c2 = c2NameField.getText();
            c3 = c3NameField.getText();
            c4 = c4NameField.getText();
            grade = gradeField.getText();
            fName = fNameField.getText();
            lName = lNameField.getText();
            a1=0; a2=0; a3=0; a4=0;
            fullName = fName.replaceAll("\\s","") + " " + lName.replaceAll("\\s","");
            for (int i=0;i<data.size()-1;i++){
                if (data.get(i).get(0).equals(fullName) && i!=index){
                    errorText.setText("There cannot be duplicate names");
                    return;
                }
            }
            data.get(index).add(0, fullName);
            data.get(index).add(1, studentNumberField.getText());
            data.get(index).add(2, c1ScoreField.getText());
            data.get(index).add(3,c1CommentField.getText());
            data.get(index).add(4,c2ScoreField.getText());
            data.get(index).add(5,c2CommentField.getText());
            data.get(index).add(6,c3ScoreField.getText());
            data.get(index).add(7,c3CommentField.getText());
            data.get(index).add(8,c4ScoreField.getText());
            data.get(index).add(9, c4CommentField.getText());
            for (int i=0;i<data.size();i++){
                try{
                a1+=Double.valueOf(data.get(i).get(2));
                a2+=Double.valueOf(data.get(i).get(4));
                a3+=Double.valueOf(data.get(i).get(6));
                a4+=Double.valueOf(data.get(i).get(8));
                } catch(NumberFormatException e){
                    break;
                }
            }
            c1AvgField.setText(String.valueOf((Math.round((a1/(data.size()))*10))/10.0));
            c2AvgField.setText(String.valueOf((Math.round((a2/(data.size()))*10))/10.0));
            c3AvgField.setText(String.valueOf((Math.round((a3/(data.size()))*10))/10.0));
            c4AvgField.setText(String.valueOf((Math.round((a4/(data.size()))*10))/10.0));
            newStudent.setEnabled(true);
            search.setEnabled(true);
            errorText.setText("");
            model.clear();
            for (int i=0;i<data.size();i++){
                model.addElement(data.get(i).get(0));
            }
            studentList.setModel(model);
            updateFile();
            if (!(fNameField.getText().isEmpty() || lNameField.getText().isEmpty() || gradeField.getText().isEmpty() || 
                    studentNumberField.getText().isEmpty() || c1NameField.getText().isEmpty() || c2NameField.getText().isEmpty() || c3NameField.getText().isEmpty() || c4NameField.getText().isEmpty() || 
                    c1ScoreField.getText().isEmpty() || c2ScoreField.getText().isEmpty() || c3ScoreField.getText().isEmpty() || c4ScoreField.getText().isEmpty() || 
                    c1AvgField.getText().isEmpty() || c2AvgField.getText().isEmpty() || c3AvgField.getText().isEmpty() || c4AvgField.getText().isEmpty() || 
                    c1CommentField.getText().isEmpty() || c2CommentField.getText().isEmpty() ||c3CommentField.getText().isEmpty() || c4CommentField.getText().isEmpty())){
                export.setEnabled(true);
                return;
            }
            export.setEnabled(false);
            }
            errorText.setText("Name cannot be empty");
    }//GEN-LAST:event_saveButtonActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        if (fNameField.getText().isEmpty() || lNameField.getText().isEmpty()){
            errorText.setText("Name cannot be empty");
            return;
        }
        
        fName = fNameField.getText();
        lName = lNameField.getText();
        fullName = fName.replaceAll("\\s","") + " " + lName.replaceAll("\\s","");
        for (int i=0;i<data.size();i++){
            if (data.get(i).get(0).equals(fullName)){
                    index = i;
                    gradeField.setText(grade);
                    c1NameField.setText(c1);
                    c2NameField.setText(c2);
                    c3NameField.setText(c3);
                    c4NameField.setText(c4);
                    studentNumberField.setText(data.get(i).get(1));
                    c1ScoreField.setText(data.get(i).get(2));
                    c2ScoreField.setText(data.get(i).get(4));
                    c3ScoreField.setText(data.get(i).get(6));
                    c4ScoreField.setText(data.get(i).get(8));
                    c1CommentField.setText(data.get(i).get(3));
                    c2CommentField.setText(data.get(i).get(5));
                    c3CommentField.setText(data.get(i).get(7));
                    c4CommentField.setText(data.get(i).get(9));
                    errorText.setText("");
                    saveButton.setEnabled(true);
                    return;
            }
        }
        errorText.setText("Student does not exist");
    }//GEN-LAST:event_searchActionPerformed

    private void newStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newStudentActionPerformed
        // TODO add your handling code here:
        data.add(new ArrayList<>());
        index = data.size()-1;
        gradeField.setText(grade);
        c1NameField.setText(c1);
        c2NameField.setText(c2);
        c3NameField.setText(c3);
        c4NameField.setText(c4);
        fNameField.setText("");
        lNameField.setText("");
        studentNumberField.setText("");
        c1ScoreField.setText("");
        c2ScoreField.setText("");
        c3ScoreField.setText("");
        c4ScoreField.setText("");
        c1CommentField.setText("");
        c2CommentField.setText("");
        c3CommentField.setText("");
        c4CommentField.setText("");
        newStudent.setEnabled(false);
        search.setEnabled(false);
        errorText.setText("");
    }//GEN-LAST:event_newStudentActionPerformed

    private void studentListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentListMouseClicked
        // TODO add your handling code here:
        String[] part;
        part = studentList.getSelectedValue().split(" ");
        fNameField.setText(part[0]);
        lNameField.setText(part[1]);
    }//GEN-LAST:event_studentListMouseClicked

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        try {
            PDDocument pDDocument = PDDocument.load(new File("template.pdf"));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField field = pDAcroForm.getField("pName");
            field.setValue(lName+", "+fName);
            field = pDAcroForm.getField("pNum");
            field.setValue(data.get(index).get(1));
            field = pDAcroForm.getField("pGrade");
            field.setValue(grade);
            field = pDAcroForm.getField("pC1");
            field.setValue(c1);
            field = pDAcroForm.getField("pC2");
            field.setValue(c2);
            field = pDAcroForm.getField("pC3");
            field.setValue(c3);
            field = pDAcroForm.getField("pC4");
            field.setValue(c4);
            field = pDAcroForm.getField("pC1Score");
            field.setValue(data.get(index).get(2));
            field = pDAcroForm.getField("pC2Score");
            field.setValue(data.get(index).get(4));
            field = pDAcroForm.getField("pC3Score");
            field.setValue(data.get(index).get(6));
            field = pDAcroForm.getField("pC4Score");
            field.setValue(data.get(index).get(8));
            field = pDAcroForm.getField("pC1Avg");
            field.setValue(c1AvgField.getText());
            field = pDAcroForm.getField("pC2Avg");
            field.setValue(c2AvgField.getText());
            field = pDAcroForm.getField("pC3Avg");
            field.setValue(c3AvgField.getText());
            field = pDAcroForm.getField("pC4Avg");
            field.setValue(c4AvgField.getText());
            field = pDAcroForm.getField("pC1Com");
            field.setValue(data.get(index).get(3));
            field = pDAcroForm.getField("pC2Com");
            field.setValue(data.get(index).get(5));
            field = pDAcroForm.getField("pC3Com");
            field.setValue(data.get(index).get(7));
            field = pDAcroForm.getField("pC4Com");
            field.setValue(data.get(index).get(9));
            pDDocument.save("Export/ReportCard_"+fullName+".pdf");
            pDDocument.close();
        } catch (IOException e) {
        }
    }//GEN-LAST:event_exportActionPerformed

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
            java.util.logging.Logger.getLogger(MainEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        data.add(new ArrayList<>());
        model = new DefaultListModel<>();               /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainEvent().setVisible(true);
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField c1AvgField;
    private javax.swing.JButton c1CalcButton;
    private javax.swing.JTextField c1CommentField;
    private static javax.swing.JTextField c1NameField;
    public static javax.swing.JTextField c1ScoreField;
    private javax.swing.JTextField c2AvgField;
    private javax.swing.JButton c2CalcButton;
    private javax.swing.JTextField c2CommentField;
    private static javax.swing.JTextField c2NameField;
    public static javax.swing.JTextField c2ScoreField;
    private javax.swing.JTextField c3AvgField;
    private javax.swing.JButton c3CalcButton;
    private javax.swing.JTextField c3CommentField;
    private static javax.swing.JTextField c3NameField;
    public static javax.swing.JTextField c3ScoreField;
    private javax.swing.JTextField c4AvgField;
    private javax.swing.JButton c4CalcButton;
    private javax.swing.JTextField c4CommentField;
    private static javax.swing.JTextField c4NameField;
    public static javax.swing.JTextField c4ScoreField;
    public static javax.swing.JLabel errorText;
    private javax.swing.JButton export;
    private javax.swing.JTextField fNameField;
    private static javax.swing.JTextField gradeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lNameField;
    private static javax.swing.JButton newStudent;
    private static javax.swing.JButton saveButton;
    private static javax.swing.JButton search;
    private static javax.swing.JList<String> studentList;
    private javax.swing.JTextField studentNumberField;
    // End of variables declaration//GEN-END:variables
}
