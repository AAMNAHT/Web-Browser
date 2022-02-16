
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
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
public class ReadWriteFile {
    GUIManager refg;
   
    public ReadWriteFile(GUIManager refg) 
    {
        this.refg = refg;
    }
    
    public void readFile(String str)
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(new File(str)));            
            String line = br.readLine();
            while (line!=null) {                
                System.out.println(line);
                line = br.readLine();
                
            }
            br.close();
            //fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void readMyFile(String str)
    {
        try 
        {
            //making vector for history
            refg.vecHistory=new Vector<String>();
            refg.vecHistoryReverse=new Vector<String>();
            BufferedReader br = new BufferedReader(new FileReader(new File(str)));            
            String line = br.readLine();
            while (line!=null) {                
                System.out.println(line);
                line = br.readLine();
                refg.vecHistory.add(line);
            }
            
            //if(refg.vecHistory.firstElement().)
            
            //storing in recent to old format
            for(int i=refg.vecHistory.size()-1;i>=0;i--)
            {
                refg.vecHistoryReverse.add(refg.vecHistory.get(i));
            }
            
            
            
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void readFavFile()
    {
        try {
            refg.vecFav=new Vector<String>();
            refg.vecFavReverse=new Vector<String>();
            BufferedReader br = new BufferedReader(new FileReader(new File("favFile.txt")));
            String line = br.readLine();
            while (line!=null) {                
                System.out.println(line);
                line = br.readLine();
                refg.vecFav.add(line);
            }
           
            for(int i=refg.vecFav.size()-1;i>=0;i--)
            {
                refg.vecFavReverse.add(refg.vecFav.get(i));
            }
            
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void writeFavFile(String str)
    {
        try {
            refg.vecFav=new Vector<String>();
            File f = new File("favFile.txt");
            FileWriter fw = new FileWriter(f,true);
            PrintWriter pw = new PrintWriter(fw);
            
            pw.println(str);
         
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
     public void writeHomeFile(String str)
    {
        try {
            File f = new File("DefaultHomeURL.txt");
            //appending-> deleting the url already present and changing it with str(new URL)
            FileWriter fw = new FileWriter(f,false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(str);
                    
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
     public String readHomeFile(String str)
    {
            String urlStr="";
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(new File(str)));            
            String line = br.readLine();
//            while (line!=null) {                
//                System.out.println(line);
//                line = br.readLine();
//                urlStr=line;
//            }
            br.close();
            return line;
            //fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            //return empty
            return "";
        }
    }
    
    public void writeMyFile(String str,String url)
    {
        try {
            File f = new File(url);
            FileWriter fw = new FileWriter(f,true);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(str);
                    
            SimpleDateFormat formatDate = new SimpleDateFormat(
                     "dd/MM/yy,HH:mm ");
                    Calendar c = Calendar.getInstance();
                   pw.println(" - "+formatDate.format(c.getTime()));
//                   String formatDateTime=formatDate.format(c.getTime());
//                   c.add(Calendar.DAY_OF_MONTH, -3);
    
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
