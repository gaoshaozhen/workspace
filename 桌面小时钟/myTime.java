package my;
import javax.swing.JFrame;
import java.awt.EventQueue;
//import java.awt.Graphics;

public class myTime extends JFrame{
     	public myTime()
     	{
     		
     		this.setSize(400,400);
     		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		this.setVisible(true);
     	}
     	public static void main(String[] args)
     	{
     		EventQueue.invokeLater(new Runnable(){
     			public void run(){
     				myTime time=new myTime();
     				
     				//timeTest.second s=new timeTest.second();
     				time.getContentPane().add(new radius());
     			}
     			
     		});
     		
     	}
}
