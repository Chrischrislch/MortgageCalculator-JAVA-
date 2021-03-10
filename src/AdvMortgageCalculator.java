import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AdvMortgageCalculator extends JFrame
{
    final int DEFAULT_FRAME_WIDTH = 550;
    final int DEFAULT_FRAME_HEIGHT = 240;

    private final JButton calculate, basicCalculator;
    private final JTextField loanAmount, loanTenor;
    private final JTextField installment1, installment2, interestRate1, interestRate2; 
    private final JTextField year_rate1;
    private final JLabel labelC, labelD;
    
    public AdvMortgageCalculator() 
    {
        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Advanced Mortgage Calculator");
        ActionListener listener = new ButtonListener();  
        
        calculate = new JButton("Calculate");
        basicCalculator = new JButton("Basic Mortgage Calculator");
        calculate.addActionListener(listener);
        basicCalculator.addActionListener(listener);
        
        loanAmount = new JTextField(10);
        loanTenor = new JTextField(10);
        interestRate1 = new JTextField(10);
        interestRate2 = new JTextField(10);
        installment1 = new JTextField(10);
        installment1.setEditable(false);
        installment2 = new JTextField(10);
        installment2.setEditable(false);
        
        year_rate1 = new JTextField(2);
        
        JLabel label1 = new JLabel("Loan Amount ($)");
        JLabel label2 = new JLabel("Loan Tenor (year)");
        JLabel label3 = new JLabel("Mortgage Interest Rate (% p.a.)");
        JLabel label4 = new JLabel("Mortgage Interest Rate (% p.a.)");
        JLabel label5 = new JLabel("Monthly Repayment (HKD)");
        JLabel label6 = new JLabel("Monthly Repayment (HKD)");
        
        JLabel labelA = new JLabel("years 1 to ");
        JLabel labelB = new JLabel("remaining years");
        
        JLabel dummy1 = new JLabel(" ");
        JLabel dummy2 = new JLabel(" ");
        JLabel dummy3 = new JLabel(" ");

        
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(1,2));
        subPanel.add(labelA);
        subPanel.add(year_rate1);
        
        labelC = new JLabel("To be determined");
        labelD = new JLabel("To be determined");
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 3));
        panel.add(label1);
        panel.add(loanAmount);
        panel.add(dummy1);
        
        panel.add(label2);
        panel.add(loanTenor);
        panel.add(dummy2);
        
        panel.add(label3);
        panel.add(interestRate1);
        panel.add(subPanel);
        
        panel.add(label4);
        panel.add(interestRate2);
        panel.add(labelB);
        
        panel.add(label5);
        panel.add(installment1);
        panel.add(labelC);
        
        panel.add(label6);
        panel.add(installment2);
        panel.add(labelD);
        
        panel.add(calculate);
        panel.add(basicCalculator);
        panel.add(dummy3);
       
        add(panel, "Center");
    }
    
    private class ButtonListener implements ActionListener 
    {
        public void actionPerformed (ActionEvent event)
        {
            Object source = event.getSource();
            if (source == calculate) 
            {
                boolean correct = true;
                int amount =0;
                int tenor = 0;
                double rr1 = 0;         
                double rr2 =0;
                int year = 0;
                boolean error;
                if (error = true)
                {
                    installment1.setText(""); 
                    installment2.setText("");
                }
                try
                {
                    String loan = loanAmount.getText();
                    int loanA = Integer.parseInt(loan);
                    amount = loanA;
                }
                catch (Exception e){
                    loanAmount.setText("Error");
                    correct = false;
                    error = true;
                }
                try
                {
                    String lt = loanTenor.getText();
                    int LT = Integer.parseInt(lt);
                    tenor = LT;
                }
                catch (Exception e){
                    loanTenor.setText("Error");
                    correct = false;
                    error = true;
                }
                try
                {
                    String ir1 = interestRate1.getText();
                    double rate1 = Double.parseDouble(ir1);
                    rr1 = rate1;
                }
                catch (Exception e){
                    interestRate1.setText("Error");
                    correct = false;
                    error = true;
                 }
                try
                {
                    String ir2 = interestRate2.getText();
                    double rate2 = Double.parseDouble(ir2);
                    rr2 = rate2;
                }
                catch (Exception e){
                    interestRate2.setText("Error");
                    correct = false;
                    error = true;
                 }
                try
                {
                    String yy = year_rate1.getText();
                    int yyy = Integer.parseInt(yy);
                    year = yyy;
                }
                catch (Exception e){
                    year_rate1.setText("Error");
                    correct = false;
                    error = true;
                 }

                if (correct = true)
                {
                    double re1 =0;
                    double re2 =0; 
                    String loan = loanAmount.getText();
                    int loanA = Integer.parseInt(loan);
                    String lt = loanTenor.getText();
                    int LT = Integer.parseInt(lt);
                    String ir1 = interestRate1.getText();
                    double rate1 = Double.parseDouble(ir1);
                    String ir2 = interestRate2.getText();
                    double rate2 = Double.parseDouble(ir2);
                    String y = year_rate1.getText();
                    int restyear = Integer.parseInt(y);
                    rate1 /= 100.0;
                    rate2 /= 100.0;
                    double monrate = rate1/12;
                    double rest = rate2/12;
                    int n = LT *12;
                    int m =  restyear *12;
                    
                    re1 = loanA *  monrate * (((Math.pow((1+monrate),n)) / ((Math.pow((1+monrate),n))-1)));
                    double storedrepay;
                    storedrepay = loanA * (Math.pow((1+monrate),n)-Math.pow((1+monrate),m))/((Math.pow((1+monrate),n)-1));
                    re2 = storedrepay * rest * Math.pow((1+rest), (n-m))/((Math.pow((1+rest), (n-m))-1));
                    labelC.setText("for years 1 to " + year);
                    labelD.setText("for years " + (restyear + 1) + " to " + (LT));
                    NumberFormat f1 = NumberFormat.getInstance();
                    f1.setMaximumFractionDigits(2);
                    NumberFormat f2 = NumberFormat.getInstance();
                    f2.setMaximumFractionDigits(2);
                    installment1.setText(f1.format(re1)); 
                    installment2.setText(f2.format(re2)); 
                }
   
            }
            else if (source == basicCalculator)
            {
                BasicMortgageCalculator cal = new BasicMortgageCalculator();
                AdvMortgageCalculator adv = new AdvMortgageCalculator();
                adv.setVisible(false);
                dispose();
                cal.setVisible(true);
                
            }
        }
        
        
        
    }
}
