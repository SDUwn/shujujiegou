package work;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class toposort {
	static int sum_xf=0;
	static DefaultMutableTreeNode term_name[]=new DefaultMutableTreeNode[frame1.all_terms+1];
	static DefaultMutableTreeNode tree=new DefaultMutableTreeNode();
	static boolean flag=false;
	
	static int ans[]=new int[1009];
	public static void toposort(){
		for(int j=1;j<=frame1.all_terms;j++) {
			term_name[j]=new DefaultMutableTreeNode("��"+j+"ѧ��");
			tree.add(term_name[j]);}
		for(int z=0;z<frame1.t_V;z++) 
		{
			for(int q=0;q<frame1.G[z].size();q++) 
			{
				System.out.print(frame1.G[z].get(q).name);
			}
			System.out.println();
		}
System.out.println("aaaaaaa");
		int cnt=0;
		int inedge[]=new int[1009];
		for(int i=0;i<frame1.t_V;i++)
		{
			inedge[i]=frame1.G[i].size()-1;
			sum_xf+=frame1.G[i].get(0).xf;
			System.out.println(inedge[i]);
		}
		Stack<Integer> s=new Stack<Integer>();
		for(int i=0;i<frame1.t_V;i++)
		{
			if(inedge[i]==0)s.push(i);
		}
System.out.println("bbbbbbb");
		while(s.size()!=0)
		{
System.out.println("stack is not null");
			int cur=s.pop();
System.out.println("size:"+s.size());
System.out.println(cur);
			ans[cnt++]=cur;
			for(int j=0;j<frame1.t_V;j++) 
			{
				for(int p=1;p<frame1.G[j].size();p++) 
				{
System.out.println(frame1.mp.get(frame1.G[j].get(p).name));
					if(frame1.mp.get(frame1.G[j].get(p).name)==cur)
						{inedge[j]--;
						if(inedge[j]==0)s.push(j);
						}
				}
				
			}
		}

		if(cnt==frame1.t_V) {flag=true;}
		
		for(int i=0;i<frame1.t_V;i++) {
			System.out.print(ans[i]);
		}
		System.out.println("OKKK!");
	}
	
	
	
	public void sort1() throws IOException 
	{
		//toposort();
		

		HSSFWorkbook wb=new HSSFWorkbook();//����������
		HSSFSheet sheet=wb.createSheet();//����������
		wb.setSheetName(0, "sheet1");//���ù�����
		HSSFRow row=null;
		HSSFCell cell=null;
		
		row=sheet.createRow(0);
		cell=row.createCell((short)0);
		cell.setCellValue("ѧ��");
		cell=row.createCell((short)1);
		cell.setCellValue("�γ̺�");
		cell=row.createCell((short)2);
		cell.setCellValue("ѧ��");
		
		
		
		int term;
		double average=1.0*sum_xf/frame1.all_terms;
		if(average>frame1.t_max_xf) 
		{
			System.out.println("��ѡ��Ŀγ̳�����ѧ��Ҫ��");
			new frame5();
			return;
		}
		
	while(average<=1.0*frame1.t_max_xf) {
		int count=0;
		System.out.println("average:"+average+"sum_xf:"+sum_xf);

		for(term=1;term<=frame1.all_terms;term++)
		{    
			int sum=0;
			while(sum+frame1.G[ans[count]].get(0).xf<=average) 
			{
				sum+=frame1.G[ans[count]].get(0).xf;
				
				System.out.print(frame1.G[ans[count]].get(0).name+"    ");
				count++;
				if(count==frame1.t_V)break;
			}
			if(count==frame1.t_V)break;
			System.out.println();
		}
		System.out.println();
		if(count==frame1.t_V) {System.out.println("�����ſγɹ���");break;}
		else {average++;}
		}
	
	
	
	int q=1,count=0;
	System.out.println("average:"+average+"sum_xf:"+sum_xf);

	for(term=1;term<=frame1.all_terms;term++)
	{    
		int sum=0;
		while(sum+frame1.G[ans[count]].get(0).xf<=average) 
		{
			sum+=frame1.G[ans[count]].get(0).xf;
			
			System.out.print(frame1.G[ans[count]].get(0).name+"    ");
			
			row=sheet.createRow(count+1);
			cell=row.createCell((short)0);
			cell.setCellValue("��"+q+"ѧ��");
			cell=row.createCell((short)1);
			cell.setCellValue(frame1.G[ans[count]].get(0).name);
			cell=row.createCell((short)2);
			cell.setCellValue(""+frame1.G[ans[count]].get(0).xf);
			
			DefaultMutableTreeNode course_name=new DefaultMutableTreeNode(frame1.G[ans[count++]].get(0).name);
			term_name[q].add(course_name);
			if(count==frame1.t_V)break;
		}
		q++;
		if(count==frame1.t_V)break;
		System.out.println();
	}	
		
//		int q=1,count=0;
//		int term;
//		for(term=1;term<=frame1.all_terms;term++)
//		{    
//			int average=frame1.t_V/frame1.all_terms;
//			System.out.println(average);
//			int sum=0;
//			while(average!=0) 
//			{
//				
//				average=average-1;
//				sum+=frame1.G[ans[count]].get(0).xf;
//				if(sum>frame1.t_max_xf) 
//					{
//						System.out.println("��ѡ��Ŀγ̳�����ѧ��Ҫ��");
//						new frame5();
//						return;
//					}
//				System.out.print(frame1.G[ans[count]].get(0).name+"    ");
//				
//				row=sheet.createRow(count+1);
//				cell=row.createCell((short)0);
//				cell.setCellValue("��"+q+"ѧ��");
//				cell=row.createCell((short)1);
//				cell.setCellValue(frame1.G[ans[count]].get(0).name);
//				cell=row.createCell((short)2);
//				cell.setCellValue(""+frame1.G[ans[count]].get(0).xf);
//				
//				DefaultMutableTreeNode course_name=new DefaultMutableTreeNode(frame1.G[ans[count++]].get(0).name);
//				term_name[q].add(course_name);
//			}
//			q++;
//			System.out.println();
//			if(count==frame1.t_V)System.out.println("�����ſγɹ���");
//		}
		
		
		JFrame frame=new JFrame("�����ſ�");
		frame.setLocationRelativeTo(null);
		frame.setSize(500,400);
		frame.setLocation(300, 200);
		
		//ʵ����һ��DefaultTreeCellRenderer����
		DefaultTreeCellRenderer cellRender=new DefaultTreeCellRenderer();
		//���ô����۵�״̬�·�Ҷ�ӽڵ��ͼ��
		cellRender.setClosedIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\tree.jpg"));
		//����Ҷ�ӽڵ��ͼ��
		cellRender.setLeafIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\h.jpg"));
		//���ô���չ��״̬�·�Ҷ�ӽڵ��ͼ��
		cellRender.setOpenIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\j.jpg"));
		//���÷�ѡ���ڵ�ı���ɫ
		cellRender.setBackgroundNonSelectionColor(Color.lightGray);
		//���ýڵ���ѡ��״̬�µı���ɫ
		cellRender.setBackgroundSelectionColor(Color.lightGray);
		//����ѡ��״̬�½ڵ�߿����ɫ
		cellRender.setBorderSelectionColor(Color.MAGENTA);
		//���û���ѡ��״̬�½ڵ��ı�����ɫ
		cellRender.setTextSelectionColor(Color.MAGENTA);
		
		
		
		ImageIcon img = new ImageIcon("D:\\BaiduNetdiskDownload\\z.jpg");//���Ǳ���ͼƬ   

		JLabel imgLabel = new JLabel(img);//������ͼ���ڱ�ǩ�      

		frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����    

		imgLabel.setBounds(130,0,img.getIconWidth(), img.getIconHeight());//���ñ�����ǩ��λ��   

		//Container cp=frame.getContentPane();   
		
		
	    JTree jtree=new JTree(tree);
	    JButton butt=new JButton("����ƻ�");
	    butt.setSize(30,10);
	    
	    jtree.setCellRenderer(cellRender); 	    
	    jtree.setOpaque(false);
	    jtree.add(imgLabel);
	    frame.add(jtree,BorderLayout.CENTER);
	    frame.add(butt,BorderLayout.SOUTH);
	    //((JPanel)cp)
	    
	    ImageIcon icon=new ImageIcon("D:\\BaiduNetdiskDownload\\n.jpg");
		frame.setIconImage(icon.getImage());
	    frame.setVisible(true);
	    
	    
	    butt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame frame_save=new JFrame();
				ImageIcon icon=new ImageIcon("D:\\BaiduNetdiskDownload\\n.jpg");
				frame_save.setIconImage(icon.getImage());
				//����ѡ�񴰿�
				FileDialog fdlg = new FileDialog(frame_save, "����Excel�ļ�", FileDialog.LOAD);
				frame_save.setIconImage(icon.getImage());
				fdlg.setIconImage(icon.getImage());

				fdlg.show();
				String sfilename = fdlg.getDirectory() + "\\" + fdlg.getFile()+".xls";
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(sfilename);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					wb.write(fos);
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
	}
	
	
	
	public void sort2() throws IOException
	{
		//toposort();
		
		HSSFWorkbook wb=new HSSFWorkbook();//����������
		HSSFSheet sheet=wb.createSheet();//����������
		wb.setSheetName(0, "sheet1");//���ù�����
		HSSFRow row=null;
		HSSFCell cell=null;
		
		row=sheet.createRow(0);
		cell=row.createCell((short)0);
		cell.setCellValue("ѧ��");
		cell=row.createCell((short)1);
		cell.setCellValue("�γ̺�");
		cell=row.createCell((short)2);
		cell.setCellValue("ѧ��");
		
		
		
		int q = 1, cnt = 0;
		while (q <= frame1.all_terms)
		    {
		        int C= frame1.G[ans[cnt]].get(0).xf;
		        
		        while(cnt<frame1.t_V && C<= frame1.t_max_xf)
		        {
		            System.out.print(frame1.G[ans[cnt]].get(0).name);
		            
		            
		            row=sheet.createRow(cnt+1);
					cell=row.createCell((short)0);
					cell.setCellValue("��"+q+"ѧ��");
					cell=row.createCell((short)1);
					cell.setCellValue(frame1.G[ans[cnt]].get(0).name);
					cell=row.createCell((short)2);
					cell.setCellValue(""+frame1.G[ans[cnt]].get(0).xf);
					
		            
		            DefaultMutableTreeNode course_name=new DefaultMutableTreeNode(frame1.G[ans[cnt]].get(0).name);
					term_name[q].add(course_name);
		            if(cnt+1<frame1.t_V) C=C+frame1.G[ans[cnt+1]].get(0).xf;
		            cnt++;
		        }
		        System.out.println();
		        if(cnt>=frame1.t_V )
		        {
		            System.out.println("�����ſγɹ�!\n");
		            break;
		        }
		        q++;
		    }
		if(q>frame1.all_terms)
			{System.out.println("��ѡ��Ŀγ̳���ѧ��Ҫ��");
			new frame5();
			return;}
		
		
		
		JFrame frame=new JFrame("�����ſ�");
		frame.setLocationRelativeTo(null);
		frame.setSize(500,400);
		frame.setLocation(300, 200);
		
		//ʵ����һ��DefaultTreeCellRenderer����
				DefaultTreeCellRenderer cellRender=new DefaultTreeCellRenderer();
				//���ô����۵�״̬�·�Ҷ�ӽڵ��ͼ��
				cellRender.setClosedIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\tree.jpg"));
				//����Ҷ�ӽڵ��ͼ��
				cellRender.setLeafIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\h.jpg"));
				//���ô���չ��״̬�·�Ҷ�ӽڵ��ͼ��
				cellRender.setOpenIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\j.jpg"));
				//���÷�ѡ���ڵ�ı���ɫ
				cellRender.setBackgroundNonSelectionColor(Color.lightGray);
				//���ýڵ���ѡ��״̬�µı���ɫ
				cellRender.setBackgroundSelectionColor(Color.lightGray);
				//����ѡ��״̬�½ڵ�߿����ɫ
				cellRender.setBorderSelectionColor(Color.MAGENTA);
				//���û���ѡ��״̬�½ڵ��ı�����ɫ
				cellRender.setTextSelectionColor(Color.MAGENTA);
				
				ImageIcon img = new ImageIcon("D:\\BaiduNetdiskDownload\\z.jpg");//���Ǳ���ͼƬ   

				JLabel imgLabel = new JLabel(img);//������ͼ���ڱ�ǩ�      

				frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����    

				imgLabel.setBounds(130,0,img.getIconWidth(), img.getIconHeight());//���ñ�����ǩ��λ��   

				//Container cp=frame.getContentPane();   
	    JTree jtree=new JTree(tree);
	    JButton butt=new JButton("����ƻ�");
	    butt.setSize(30,10);
	    jtree.setCellRenderer(cellRender); 	    
	    jtree.setOpaque(false);
	    jtree.add(imgLabel);
	    frame.add(jtree,BorderLayout.CENTER);
	    frame.add(butt,BorderLayout.SOUTH);
	    ImageIcon icon=new ImageIcon("D:\\BaiduNetdiskDownload\\n.jpg");
		frame.setIconImage(icon.getImage());
	    frame.setVisible(true);
	    
	    butt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				//����ѡ�񴰿�
				FileDialog fdlg = new FileDialog(new JFrame("����"), "Excel�ļ�", FileDialog.LOAD);
				fdlg.show();
				String sfilename = fdlg.getDirectory() + "\\" + fdlg.getFile()+".xls";
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(sfilename);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					wb.write(fos);
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
	    
	}
}
