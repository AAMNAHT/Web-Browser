
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class BtnHandler implements ActionListener{
    
    GUIManager refg;
   
    public BtnHandler(GUIManager refg) 
    {
        this.refg = refg;
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {
        String input=e.getActionCommand();
       
        //next button
        if(input.equals("next"))
        {
            if(refg.stNext.isEmpty())
            {
                
            }
            else
            {
                String stURL=refg.stNext.pop();
                if(!refg.stNext.isEmpty())
                {
                    refg.loadPage(refg.stNext.lastElement());
                }
            }
        }
        //previous button
        else if(input.equals("previous"))
        {
            if(refg.stPrev.isEmpty())
            {
                
            }
            else
            {
                String stURL=refg.stPrev.pop();
                refg.stNext.push(stURL);
                if(!refg.stPrev.isEmpty())
                {
                    refg.loadPage(refg.stPrev.lastElement());
                }
                else
                {
                    refg.loadPage(refg.homeURL);
                }
            }
        }
        
        //home button
        if(input.equals("home"))
        {
            refg.loadPage(refg.homeURL);
        }
        
        //refresh button
        if(input.equals("refresh"))
        {
            refg.loadPage(refg.tfAddress.getText());
        }
        
        //history button
        if(input.equals("history"))
        {
            JFrame f2=new JFrame("History");
              
            refg.rwf.readMyFile(refg.origFile);
            JList l=new JList<String>(refg.vecHistoryReverse);
            l.addMouseListener(refg.ml);
            l.setBounds(0,0,500,500);

            //displaying close 
            JPanel panBtn;
            panBtn=new JPanel();
            panBtn.setLayout(new GridLayout(2,7));
            JButton closeBtn=new JButton("close");
            closeBtn.setPreferredSize(new Dimension(50,50));
            closeBtn.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
             System.exit(0);
            }
           });
            panBtn.add(closeBtn);
            f2.add(panBtn);
            f2.add(l);  
            f2.setSize(500,500);  
            f2.setLayout(null);  
            f2.setVisible(true);  
            f2.setLocationRelativeTo(null);
        }
        
        //fav folder button
        if(input.equals("favorite"))
        {
            JFrame frfav=new JFrame("View Favourites");  
             refg.rwf.readFavFile();  
             JList l=new JList<String>(refg.vecFavReverse);
             l.addMouseListener(refg.ml);
             //JScrollPane scrollPane1 = new JScrollPane(jlist);
             //frame.add(scrollPane1);
             l.setBounds(0,0,500,500);
             frfav.add(l); 
             frfav.setSize(500,500);  
             frfav.setLayout(null);  
             frfav.setVisible(true);  
             frfav.setLocationRelativeTo(null);
        }
        
        //search folder
        if(input.equals("search"))
        {
            JFrame f=new JFrame();   
            String search=JOptionPane.showInputDialog(f,"Enter what you want to search:");
            int count=0;
            
            String str=refg.jepMain.getText();
            System.out.println(str);
            
            int start = 0;  
            int index=0;
            while(index>-1)  
            {  
                index = str.indexOf(search, start);  
                if(index >= 0)  
                {   
                    count++;  
                    start = index + 1;  
                }  
            }  
 
            JOptionPane.showMessageDialog(null,"Total Searches "+ count);   
        } 
    }
}
