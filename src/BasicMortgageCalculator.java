import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BasicMortgageCalculator extends JFrame
{
    final int DEFAULT_FRAME_WIDTH = 420;
    final int DEFAULT_FRAME_HEIGHT = 200;

    private final JButton calculate, advCalculator;
    private final JTextField loanAmount, interestRate, loanTenor, installment;
    public BasicMortgageCalculator()  
    {
        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Basic Mortgage Calculator");
        ActionListener listener = new ButtonListener();  
        
        calculate = new JButton("Calculate");
        advCalculator = new JButton("Adv. Mortgage Calculator");
        calculate.addActionListener(listener);
        advCalculator.addActionListener(listener);
        
        loanAmount = new JTextField(10);
        loanTenor = new JTextField(10);
        interestRate = new JTextField(10);
        installment = new JTextField(10);
        installment.setEditable(false);
        
        JLabel label1 = new JLabel("Loan Amount ($)");
        JLabel label2 = new JLabel("Loan Tenor (year)");
        JLabel label3 = new JLabel("Mortgage Interest Rate (% p.a.)");
        JLabel label4 = new JLabel("Monthly Repayment (HKD)");
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(label1);
        panel.add(loanAmount);
        panel.add(label2);
        panel.add(loanTenor);
        panel.add(label3);
        panel.add(interestRate);
        panel.add(label4);
        panel.add(installment);
        panel.add(calculate);
        panel.add(advCalculator);
        
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
                double rr = 0;               
                try
                {
                    String loan = loanAmount.getText();
                    int loanA = Integer.parseInt(loan);
                    amount = loanA;
                }
                catch (Exception e){
                    loanAmount.setText("Error");
                    installment.setText("");
                    correct = false;
                }
                try
                {
                    String lt = loanTenor.getText();
                    int LT = Integer.parseInt(lt);
                    tenor = LT;
                }
                catch (Exception e){
                    loanTenor.setText("Error");
                    installment.setText("");
                    correct = false;
                }
                try
                {
                    String ir = interestRate.getText();
                    double rate = Double.parseDouble(ir);
                    rr = rate;
                }
                catch (Exception e){
                    interestRate.setText("Error");
                    installment.setText("");
                    correct = false;
                 }
                
                if (correct = true)
                {
                    String loan = loanAmount.getText();
                    int loanA = Integer.parseInt(loan);
                    String lt = loanTenor.getText();
                    int LT = Integer.parseInt(lt);
                    String ir = interestRate.getText();
                    double rate = Double.parseDouble(ir);                           
                    rate /= 100.0;
                    double monrate = rate/12;
                    int n = LT *12;
                    double repayment;
                    double half = (((Math.pow((1+monrate),n)) / ((Math.pow((1+monrate),n))-1)));
                    repayment = loanA * monrate * half;
                    NumberFormat f = NumberFormat.getInstance();
                    f.setMaximumFractionDigits(2);
                    installment.setText(f.format(repayment));                   
                }                
            }
            else if(source == advCalculator)
            {
                BasicMortgageCalculator cal = new BasicMortgageCalculator();
                AdvMortgageCalculator adv = new AdvMortgageCalculator();
                cal.setVisible(false);
                dispose();
                adv.setVisible(true);
            }                      
        }       
    }
}
