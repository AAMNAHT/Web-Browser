
import java.io.IOException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class DefaultHomeURL {
    GUIManager refg;
   
    public DefaultHomeURL(GUIManager refg) 
    {
        this.refg = refg;
    }
    
    public void loadHomePage(String st)
    {
        try
        {
            refg.jepMain.setPage(st);
//            refg.tfAddress.setText(st);
            refg.stPrev.push(st);
            //refg.rwf.writeHomeFile(st);
        }
        catch(IOException e)
        {
             System.out.println("***defaulthomePage***");
             
             JOptionPane.showMessageDialog(null,"Page not Found","Bad URL",JOptionPane.ERROR_MESSAGE);  
        }
    }
}
