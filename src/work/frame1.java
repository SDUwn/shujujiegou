package work;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import work.Node;

public class frame1 {
	public static int all_terms,t_max_xf,t_V;  
	public static Vector<Node>[]G=new Vector[1009];
	public static Map<String, Integer> mp;
	
	public static void main(String[]args) 
	{
		for(int i=0;i<1009;i++) G[i]=new Vector<Node>();
		mp=new HashMap<String,Integer>();
		
		JFrame frame1=new JFrame("��ѧ�ƻ�ϵͳ");
		frame1.setSize(400,500);
		frame1.setLocationRelativeTo(null);
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("��ѧ����           ");
		JTextField text1=new JTextField();
		text1.setPreferredSize(new Dimension(200,30));
		JLabel label2=new JLabel("   ѧ������           ");
		JTextField text2=new JTextField();
		text2.setPreferredSize(new Dimension(210,30));
		panel1.add(label1);
		panel1.add(text1);
		panel1.add(label2);
		panel1.add(text2);
		ButtonGroup bg=new ButtonGroup();
		
		Object[][] Info = {
	            // ��������е�����
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" },
	            { "", "", "" }};
		String[] Names = { "�γ̺�", "ѧ��","���޿γ̺�"};
		// ��Names��playerInfoΪ����������һ�����
		JTable table = new JTable(Info,Names);
		// ���ô˱���ͼ����ѡ��С
		table.setPreferredScrollableViewportSize(new Dimension(300, 280));
		// �������뵽�����������
		JScrollPane scrollPane = new JScrollPane(table);
		panel1.add(scrollPane, BorderLayout.CENTER);
		
		JRadioButton one,two;
		one=new JRadioButton("���Ȱ���        ",true);
		two=new JRadioButton("���а���        ");
		bg.add(one);
		bg.add(two);
		panel1.add(one);
		panel1.add(two);
		JButton bt1,bt2,bt3;
		bt1=new JButton("    �½���ѧ�ƻ�����       ");
		//bt2=new JButton("    �����ѧ�ƻ�����       ");
		panel1.add(bt1);
		//panel1.add(bt2);
	
		bt1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				all_terms=Integer.parseInt(text1.getText());
				t_max_xf=Integer.parseInt(text2.getText());
				int i=0;
//				while(table.getValueAt(i, 0) != null)
//				{
					t_V=table.getSelectedRowCount();
					
//					i++;
//				}
				System.out.println(t_V);
				for(int j=0;j<t_V;j++)
				{
				 Node node=new Node();
				 node.name=(String)table.getValueAt(j, 0);
				 System.out.println(node.name);
				 node.xf=Integer.parseInt((String) table.getValueAt(j, 1));
				 System.out.println(node.xf);
				 System.out.println(node);
			     G[j].add(node);
			     mp.put(node.name, j);
				}
				for(int j=0;j<t_V;j++)
				{
					String spro="";
					String pro=table.getValueAt(j,2).toString();
					System.out.println(pro);
					if(pro.charAt(0)=='#')continue;
					int k=0;
					while(pro.charAt(k)!='#')
					{
						String sname=""+pro.charAt(k)+pro.charAt(k+1)+pro.charAt(k+2);
						G[j].add(G[mp.get(sname)].get(0));
						System.out.println(sname);
						k=k+3;
					}
				}  
				//ѡ���ſη�ʽ
				if(one.isSelected())new frame2();
				if(two.isSelected())new frame3();
			}
		});
		ImageIcon icon=new ImageIcon("D:\\BaiduNetdiskDownload\\n.jpg");
		frame1.setIconImage(icon.getImage());
		frame1.add(panel1);
		frame1.setVisible(true);
//23333
	}
}
