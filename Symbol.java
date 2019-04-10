import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
class GenSymbolTable extends Frame implements ActionListener{
 JFrame frame;
 JPanel p;
 JLabel l1,l2,l3;
 JTextField tf1;
 JButton btn,btn2;
 JTextArea ans,inputfiledata;
 String output="";
 String inputContent="";
 String inputfile="";
 int srno=0;
 GenSymbolTable()
 {
  /*GUI designing*/
  frame=new JFrame();
  l1=new JLabel("ENTER THE INPUT FILE NAME:");
  
  l1.setBounds(100,100,200,50);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  frame.add(l1);
  tf1=new JTextField();
  tf1.setBounds(300,100,400,100);
  frame.add(tf1);

  btn=new JButton("Output");
  btn.setBounds(300,200,200,50);
  btn.addActionListener(this);

  frame.add(btn);
  btn2=new JButton("get input");
  btn2.setBounds(500,200,200,50);
  btn2.addActionListener(this);

  frame.add(btn2);
  
  l2=new JLabel("SYMBOL TABLE:");
  l2.setBounds(100,260,200,30);
  frame.add(l2);

  ans=new JTextArea();
  ans.setBounds(100,300,600,400);
  frame.add(ans);
  
  l3=new JLabel("INPUT FILE DATA:");
  l3.setBounds(750,100,500,30);
  frame.add(l3);
  inputfiledata=new JTextArea();
  inputfiledata.setBounds(750,140,500,600);
  frame.add(inputfiledata);
  frame.setSize(1500,800);
  frame.setLayout(null);
  frame.setVisible(true);
}

public void calculate()
{
output="Sr.No\tSymbol\tType\tSize\tLength\tpointer\n";
output=output+"___________________________________________________________________________\n";
try{
 BufferedReader br=new BufferedReader(new FileReader(inputfile));
 String str="";
 while((str=br.readLine())!=null)//gets the single line;
 {
  inputContent=inputContent+str+'\n';
  String type=""; //initially at the start of each line let type=null;
  if(str.contains("int ")||str.contains("float ")||str.contains("char ")) 
  {
   for(String s:str.split("\\s|,|;"))
   {
    String symbol="";
    int size=0,len=0,ptr=0;
    String temp="";
    int f=0;
    if(type.equals(""))//getting the datatype at start of line
     type=s;
    else
    {
     srno++;
     if(s.charAt(0)=='*')
     {
      symbol=s.substring(1,s.length());
      ptr=1;
     }
     else 
     {
      for(int j=0;j<s.length();j++)
      {
       if(s.charAt(j)=='[')
       { f=1;
        j++;
        while(s.charAt(j)!=']')
        {
         temp=temp+s.charAt(j);
         j++;
        }
       }
       else
        symbol=symbol+s.charAt(j);
       }
      }
      if(f==1)
       len=Integer.parseInt(temp);
      else
       len=1;
      
      if(type.equals("int"))
       size=2;
      else if(type.equals("float"))
       size=4;
      else if(type.equals("char"))
       size=1;
      output=output+srno+'\t'+symbol+'\t'+type+'\t'+size+'\t'+len+'\t'+ptr+'\n';
      output=output+"___________________________________________________________________\n";
     }
    }
   }
  }
 }
 catch(Exception e1)
 {
  System.out.println(e1);
 }
 
}
 //Action performed corresponding to each button;
public void actionPerformed(ActionEvent e)
{
  if(e.getSource()==btn)  //Displays the output(i.e.symbol table) in TextArea;
  { calculate();
   inputfiledata.setText(inputContent);
   ans.setText(output);
  }
  else if(e.getSource()==btn2) //Fetches the input String from the Textfield Area;
   inputfile=tf1.getText();
}
}
class Symbol
{
 public static void main(String [] args)throws Exception
 {
  GenSymbolTable ob=new GenSymbolTable();
  System.out.println("Symbol table is generated.");
  
 }

}