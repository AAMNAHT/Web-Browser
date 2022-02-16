
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TimeZone;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class GUIManager {
    JFrame fMain;
    JEditorPane jepMain;
    JButton btnGo;
    JTextField tfAddress;
    
    JButton[] btnSetArr;
    JPanel setPanel;
    
    JPanel btnMainP;
    JButton[] btnMainArr;
    
    ImageIcon[] iconArr;
    
    JScrollPane scroll;
    //SitePanel sPanel;
  
    JPanel menuP;
    JMenuBar jmb;
    //JMenu subMenu;
    JMenu itemNext,itemPrevious,itemHome,itemRefresh,itemSearch,itemHistory,itemFav;
    JMenuItem addFav,viewFav;
    JMenuItem setHomeP;
    JMenuItem viewHistory;
    JMenuItem deleteHistory;
    JMenuItem searchWord;
  
    BtnHandler hnd;
    MouseHandler ml;
    ReadWriteFile rwf;
    DefaultHomeURL hp;
    
    DefaultListModel<String> lm;
    Stack<String> st;
    Stack<String> stPrev;
    Stack<String> stNext;
    
    Queue<String> QueStr;
    int count=0;
    LinkedList<String> ls;
    
    Vector<String> strPrev;
    Vector<String> strNext;
    
    Vector<String> vecHistory;
    Vector<String> vecHistoryReverse;
    Vector<String> vecFav;
    Vector<String> vecFavReverse;
    
    Stack<String> favStack;
    
    String origFile;
    String homeURL;
    
    int countURL=0;
    
    /** Creates a new instance of GUIManager */
    public GUIManager() {
        initGui();
    }
    public void initGui(){
        //making frame for the browser
        fMain = new JFrame();
        fMain.setBackground(Color.LIGHT_GRAY);
        
        //making stacks
        stPrev=new Stack();
        stNext=new Stack();
        
        //making array for storing string URL
        strPrev=new Vector<String>();
        strNext=new Vector<String>();
        
        //file in which all the data stored -->history
        origFile="file.txt";
        
        //initializing classes
        rwf=new ReadWriteFile(this);
        hp=new DefaultHomeURL(this); 
        hnd = new BtnHandler(this);
        ml=new MouseHandler(this);
         
        setPanel = new JPanel();
        setPanel.setBackground(Color.LIGHT_GRAY);
        menuP=new JPanel();
        menuP.setLayout(new GridLayout(1,9));

        jmb=new JMenuBar();
        jmb.setPreferredSize(new Dimension(685,25));
        jmb.setBackground(Color.ORANGE);
        fMain.setJMenuBar(jmb);
        
        
        itemHome=new JMenu("home");
        setHomeP=new JMenuItem("Set Home Page");
        setHomeP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame f=new JFrame();   
                String newHomePage=JOptionPane.showInputDialog(f,"Set new Home page:");
                homeURL=newHomePage;
                rwf.writeHomeFile(homeURL);
            }
        });
        itemHome.add(setHomeP);

        
        itemHistory=new JMenu("history");
        viewHistory=new JMenuItem("View History");
        viewHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             JFrame frHis=new JFrame("History");  
             rwf.readMyFile(origFile);  
             //readMyFile(origFile);  
             JList l=new JList<String>(vecHistoryReverse);
             l.addMouseListener(ml);
             l.setBounds(0,0,500,500);
             frHis.add(l); 
             frHis.setSize(600,600);  
             frHis.setLayout(null);  
             frHis.setVisible(true);  
             frHis.setLocationRelativeTo(null);    
            }
        });
        itemHistory.add(viewHistory);

         
        itemFav=new JMenu("favourite");
        addFav=new JMenuItem("Add Favourite");
        addFav.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               //JFrame f=new JFrame("Add Favourites");   
               //String addFav=JOptionPane.showInputDialog(f,"Add Favourite URL title:");
               //String stFav=tfAddress.getText()+" - "+addFav;

             rwf.readFavFile();
             //System.out.println(vecFav);
             //System.out.println(vecFav.size());
             for(int i=0;i<vecFav.size()-1;i++)
            {
                //vecFav.elementAt(i)==tfAddress.getText()
                
                if(vecFav.contains(tfAddress.getText()))
                {
                    System.out.println("blah blah blah");
                    JOptionPane.showMessageDialog(null,"Already added is Favourites"); 
                    return ;
                }
                else
                {
                   //String stFav=addFav+" ";
                   //System.out.println("-------------"+stFav+"-------------");
                    rwf.writeFavFile(tfAddress.getText());
                   //rwf.writeFavFile(stFav);
                }
             }
            }
        });
        viewFav=new JMenuItem("View Favourite");
        viewFav.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             JFrame frfav=new JFrame("View Favourites");  
             rwf.readFavFile();  
             JList l=new JList<String>(vecFavReverse);
             l.addMouseListener(ml);
             //JScrollPane scrollPane1 = new JScrollPane(jlist);
             //frame.add(scrollPane1);
             l.setBounds(0,0,500,500);
             frfav.add(l); 
             frfav.setSize(500,500);  
             frfav.setLayout(null);  
             frfav.setVisible(true);  
             frfav.setLocationRelativeTo(null);
            }
        });
        itemFav.add(addFav);
        itemFav.add(viewFav);
         
        
        itemSearch=new JMenu("search");
        searchWord=new JMenuItem("Search word");
        searchWord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              JFrame f=new JFrame();   
            String search=JOptionPane.showInputDialog(f,"Enter what you want to search:");
            int count=0;
            
            String str=jepMain.getText();
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
        });
        itemSearch.add(searchWord);
        
        jmb.add(itemHome);
        jmb.add(itemHistory);
        jmb.add(itemFav);
        jmb.add(itemSearch);
        
        menuP.add(jmb);
        
        btnMainP=new JPanel();
        btnMainP.setBackground(Color.DARK_GRAY);
        String[] btnMainText={"previous","next","home","refresh","history","favorite","search"};
        
        //array for storing icon images for the buttons 
        String[] iconArr={"C:\\Users\\User\\Desktop\\previousIcon.JPG",
                          "C:\\Users\\User\\Desktop\\nextIcon.JPG",
                          "C:\\Users\\User\\Desktop\\home1Icon.PNG",
                          "C:\\Users\\User\\Desktop\\refreshIcon.PNG",
                          "C:\\Users\\User\\Desktop\\folder.PNG",
                          "C:\\Users\\User\\Desktop\\folderIcon.PNG",
                          "C:\\Users\\User\\Desktop\\searchIcon.JPG"};
        btnMainArr=new JButton[7];
        for(int j=0;j<7;j++)
        {      
            ImageIcon icon = new ImageIcon(iconArr[j]);
            btnMainArr[j]=new JButton(icon);
          
            btnMainArr[j].setActionCommand(btnMainText[j]);
            btnMainArr[j].addActionListener(hnd);
            
            btnMainArr[j].setPreferredSize(new Dimension(89,60));       
            btnMainArr[j].setBackground(Color.WHITE);
            btnMainP.add(btnMainArr[j]);
        }
                
        //SitePanel-->display web page
        jepMain = new JEditorPane();
        jepMain.setPreferredSize(new Dimension(661,500));
        jepMain.setEditable(false);
        
        homeURL=rwf.readHomeFile("DefaultHomeURL.txt");
        hp.loadHomePage(homeURL);
        
        jepMain.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
                {
                    loadPage(e.getURL().toString());                 
                }
            }
        });
        
        //Address Bar
        btnGo = new JButton("GO");
        btnGo.setBackground(Color.ORANGE);
        btnGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadPage(tfAddress.getText());
            }
        });
        tfAddress = new JTextField(55);
        tfAddress.setPreferredSize(new Dimension(100,27));
        tfAddress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            loadPage(e.getActionCommand());
            }
        });
           
        Container c = fMain.getContentPane();
        c.setBackground(Color.LIGHT_GRAY);
        c.setLayout(new FlowLayout());
        
        fMain.add(setPanel);
        fMain.add(menuP);
        fMain.add(btnMainP);
        
        fMain.add(tfAddress);
        fMain.add(btnGo);
        fMain.add(jepMain);
        fMain.add(new JScrollPane(jepMain));
        
        fMain.setSize(750,750);
        fMain.setResizable(false);
        fMain.setLocation(200,200);
        fMain.setVisible(true);
        fMain.setLocationRelativeTo(null);
        fMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void loadPage(String url){
        try{   
            jepMain.setPage(url);
            tfAddress.setText(url);

            if(!stPrev.isEmpty())
            {
                if(url.equals(stPrev.peek()))
                {
                }
                else{
                    stPrev.push(url);
                    String num=stPrev.peek();
                    System.out.println(num);
                }
            }
            
            //writefile
            rwf.writeMyFile(url,"file.txt");
        }
        catch(IOException ioexp){
            System.out.println("***loadPage***");
            JOptionPane.showMessageDialog(null,"Page not Found","Bad URL",JOptionPane.ERROR_MESSAGE);    
        }
    }
}
