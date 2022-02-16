
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class MouseHandler implements MouseListener{

    GUIManager refgm1;


    public MouseHandler(GUIManager gg)
    {
        refgm1=gg;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2)
        {
            JList source = (JList) e.getSource();
            String str=source.getSelectedValue().toString();

//            if(e.getSource()==refgm1.btnMainArr[4].getText())
//            {
//                int i=str.indexOf('-');
//                str=str.substring(0, i);
//            }
             int i=str.indexOf(' ');
             str=str.substring(0, i);
            System.out.println("str is :"+str);
            refgm1.loadPage(str);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        }

    @Override
    public void mouseReleased(MouseEvent e) {
       }

    @Override
    public void mouseEntered(MouseEvent e) {
       }

    @Override
    public void mouseExited(MouseEvent e) {
        }
    
}
