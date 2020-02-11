package work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class frame3 {
	{
		JFrame frame=new JFrame("教学计划表");
		//JPanel panel=new JPanel();
		JButton butt=new JButton("确定");
		frame.setLocation(500, 300);
		frame.setSize(300, 250);
		
		JLabel all_term,max_xf,V,f,word;
		word=new JLabel("                          数据录入成功！                                         ");
		all_term=new JLabel("学期总数:                     "+frame1.all_terms);
		max_xf=new JLabel("最高学分:                     "+frame1.t_max_xf);
		V=new JLabel("选择的课程数:              "+frame1.t_V);
		f=new JLabel("排课方式:        "+"集中排课");
		word.setFont(new Font("微软雅黑", Font.BOLD, 15));
		all_term.setFont(new Font("微软雅黑", Font.BOLD, 15));
		max_xf.setFont(new Font("微软雅黑", Font.BOLD, 15));
		V.setFont(new Font("微软雅黑", Font.BOLD, 15));
		f.setFont(new Font("微软雅黑", Font.BOLD, 15));
//		//frame.setLayout(new FlowLayout());
//		JTextArea area=new JTextArea();
//		area.setPreferredSize(new Dimension(280,180));
//		//panel.setSize(new Dimension(280,180));
//		area.append("数据录入成功！\n");
//		area.append("学期总数为"+frame1.all_terms+"\n");
//		area.append("最高学分为"+frame1.t_max_xf+"\n");
//		area.append("已选"+frame1.t_V+"节课程\n");
//		area.append("您选择了集中排课！");
		//panel.add(area);
		JPanel panel=new JPanel();
		ImageIcon img = new ImageIcon("D:\\BaiduNetdiskDownload\\山东大学logo2.png");//这是背景图片   

		JLabel imgLabel = new JLabel(img);//将背景图放在标签里。      

		frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。    

		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置   
		
		panel.add(word);
		panel.add(all_term);
		panel.add(max_xf);
		panel.add(V);
		panel.add(f);
		panel.add(imgLabel);
		frame.add(panel,BorderLayout.CENTER);
		frame.add(butt,BorderLayout.SOUTH);
		butt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				System.out.println("topo to be start....");
				toposort topo=new toposort();
				topo.toposort();
				if(topo.flag==true) {
					try {
						topo.sort2();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
				else {new frame4();}
				
			}
		});
		ImageIcon icon=new ImageIcon("D:\\BaiduNetdiskDownload\\n.jpg");
		frame.setIconImage(icon.getImage());
		frame.setVisible(true);
		}
}
