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
		JFrame frame=new JFrame("��ѧ�ƻ���");
		//JPanel panel=new JPanel();
		JButton butt=new JButton("ȷ��");
		frame.setLocation(500, 300);
		frame.setSize(300, 250);
		
		JLabel all_term,max_xf,V,f,word;
		word=new JLabel("                          ����¼��ɹ���                                         ");
		all_term=new JLabel("ѧ������:                     "+frame1.all_terms);
		max_xf=new JLabel("���ѧ��:                     "+frame1.t_max_xf);
		V=new JLabel("ѡ��Ŀγ���:              "+frame1.t_V);
		f=new JLabel("�ſη�ʽ:        "+"�����ſ�");
		word.setFont(new Font("΢���ź�", Font.BOLD, 15));
		all_term.setFont(new Font("΢���ź�", Font.BOLD, 15));
		max_xf.setFont(new Font("΢���ź�", Font.BOLD, 15));
		V.setFont(new Font("΢���ź�", Font.BOLD, 15));
		f.setFont(new Font("΢���ź�", Font.BOLD, 15));
//		//frame.setLayout(new FlowLayout());
//		JTextArea area=new JTextArea();
//		area.setPreferredSize(new Dimension(280,180));
//		//panel.setSize(new Dimension(280,180));
//		area.append("����¼��ɹ���\n");
//		area.append("ѧ������Ϊ"+frame1.all_terms+"\n");
//		area.append("���ѧ��Ϊ"+frame1.t_max_xf+"\n");
//		area.append("��ѡ"+frame1.t_V+"�ڿγ�\n");
//		area.append("��ѡ���˼����ſΣ�");
		//panel.add(area);
		JPanel panel=new JPanel();
		ImageIcon img = new ImageIcon("D:\\BaiduNetdiskDownload\\ɽ����ѧlogo2.png");//���Ǳ���ͼƬ   

		JLabel imgLabel = new JLabel(img);//������ͼ���ڱ�ǩ�      

		frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����    

		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//���ñ�����ǩ��λ��   
		
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
