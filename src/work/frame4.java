package work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class frame4 {
	{
		JFrame frame=new JFrame();
		//JPanel panel=new JPanel();
		JButton butt=new JButton("确定");
		frame.setLocation(500, 300);
		frame.setSize(300, 200);
		//frame.setLayout(new FlowLayout());
		JTextArea area=new JTextArea();
		area.setPreferredSize(new Dimension(280,180));
		//panel.setSize(new Dimension(280,180));
		area.append("排课失败\n找不到先修课程！\n请检查您输入的数据！");
		
		frame.add(area,BorderLayout.CENTER);
		frame.add(butt,BorderLayout.SOUTH);
		butt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		ImageIcon icon=new ImageIcon("D:\\BaiduNetdiskDownload\\n.jpg");
		frame.setIconImage(icon.getImage());
		frame.setVisible(true);
		}
		public static void main(String[]args)
		{
			new frame4();
		}
}
