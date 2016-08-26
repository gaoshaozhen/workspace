package my;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
public class radius extends javax.swing.JPanel implements Runnable{
	int xcenter;
	int ycenter;
	int degree=0;//与竖直向上方向夹角，最初为0°
	int xSecond,ySecond;
	//int xMinute,yMinute;
	//int xHour,yHour;
	//int second;
	int secondDegree;
	int minuteDegree;
	int hourDegree;
	boolean xc=true;
	@SuppressWarnings("deprecation")
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		xcenter=getWidth()/2;//圆心x坐标
		ycenter=getHeight()/2;//y坐标
		degree=0;
		while(degree<=330)
		{
			double  xchange=80*Math.sin(Math.toRadians(degree));//每次横坐标变化量
			double ychange=80*Math.cos(Math.toRadians(degree));//每次纵坐标变化量
			g.fillOval(xcenter+(int)xchange,ycenter-(int)ychange, 5, 5);//画出整点标示
			degree+=30;//每次角度增加30°
		}
		
		g.setColor(Color.red);
		//g.fillOval(xcenter,ycenter, 5, 5);
		secondDegree=6*(new Date().getSeconds());//用获得的时间（秒）得出秒针的角度
		minuteDegree=6*(new Date().getMinutes());//用获得的时间（分）得出分针的角度
		hourDegree=((new Date().getHours())%12)*30+(int)(((float)(new Date().getMinutes())/60)*30);//用获得的时间得出时针的角度
		//hourDegree=((new Date().getHours())%12)*30;
		//System.out.println((float)(new Date().getMinutes())/60*30);
		
		int xSecond=(int)(xcenter+(50*Math.sin(Math.toRadians(secondDegree))));
		int ySecond=(int)(ycenter-50*Math.cos(Math.toRadians(secondDegree)));
		int xMinute=(int)(xcenter+(30*Math.sin(Math.toRadians(minuteDegree))));
		int yMinute=(int)(ycenter-30*Math.cos(Math.toRadians(minuteDegree)));
		int xHour=(int)(xcenter+(20*Math.sin(Math.toRadians(hourDegree))));
		int yHour=(int)(ycenter-20*Math.cos(Math.toRadians(hourDegree)));
		
		g.drawLine(xcenter,ycenter,xSecond,ySecond);//画出秒针		
		g.setColor(Color.gray);
		g.drawLine(xcenter,ycenter,xMinute,yMinute);//画出分针	
		g.setColor(Color.black);
		g.drawLine(xcenter,ycenter,xHour,yHour);//画出时针	
		if(xc) start();//创建并启动线程		
	}
	public void run()
	{
		while(true)
		{
			
			try {
				
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			   repaint();
		}
	}
	private void start()
	{
		xc=false;
		(new Thread(this)).start();
	}
}
