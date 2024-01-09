import java.awt.*;
import javax.swing.*;

public class ReportCard extends JFrame{
    ReportCardEvent reportcard = new ReportCardEvent(this);
    public ReportCard(){
    super ("Report Cards");
    setSize(600,400);
    JLabel fName = new JLabel("First Name:");
    Font font = fName.getFont();
    fName.setAlignmentX(LEFT_ALIGNMENT);
    fName.setFont(new Font(fName.getFont().getName(),font.PLAIN,12));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    BoxLayout layout = new BoxLayout(this,BoxLayout.LINE_AXIS);
    setLayout(layout);
    
    }
    
    public static void main(String[] arguments){
         ReportCard frame = new ReportCard();
        }
}