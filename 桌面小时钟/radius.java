package my;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
public class radius extends javax.swing.JPanel implements Runnable{
	int xcenter;
	int ycenter;
	int degree=0;//����ֱ���Ϸ���нǣ����Ϊ0��
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
		xcenter=getWidth()/2;//Բ��x����
		ycenter=getHeight()/2;//y����
		degree=0;
		while(degree<=330)
		{
			double  xchange=80*Math.sin(Math.toRadians(degree));//ÿ�κ�����仯��
			double ychange=80*Math.cos(Math.toRadians(degree));//ÿ��������仯��
			g.fillOval(xcenter+(int)xchange,ycenter-(int)ychange, 5, 5);//���������ʾ
			degree+=30;//ÿ�νǶ�����30��
		}
		
		g.setColor(Color.red);
		//g.fillOval(xcenter,ycenter, 5, 5);
		secondDegree=6*(new Date().getSeconds());//�û�õ�ʱ�䣨�룩�ó�����ĽǶ�
		minuteDegree=6*(new Date().getMinutes());//�û�õ�ʱ�䣨�֣��ó�����ĽǶ�
		hourDegree=((new Date().getHours())%12)*30+(int)(((float)(new Date().getMinutes())/60)*30);//�û�õ�ʱ��ó�ʱ��ĽǶ�
		//hourDegree=((new Date().getHours())%12)*30;
		//System.out.println((float)(new Date().getMinutes())/60*30);
		
		int xSecond=(int)(xcenter+(50*Math.sin(Math.toRadians(secondDegree))));
		int ySecond=(int)(ycenter-50*Math.cos(Math.toRadians(secondDegree)));
		int xMinute=(int)(xcenter+(30*Math.sin(Math.toRadians(minuteDegree))));
		int yMinute=(int)(ycenter-30*Math.cos(Math.toRadians(minuteDegree)));
		int xHour=(int)(xcenter+(20*Math.sin(Math.toRadians(hourDegree))));
		int yHour=(int)(ycenter-20*Math.cos(Math.toRadians(hourDegree)));
		
		g.drawLine(xcenter,ycenter,xSecond,ySecond);//��������		
		g.setColor(Color.gray);
		g.drawLine(xcenter,ycenter,xMinute,yMinute);//��������	
		g.setColor(Color.black);
		g.drawLine(xcenter,ycenter,xHour,yHour);//����ʱ��	
		if(xc) start();//�����������߳�		
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
