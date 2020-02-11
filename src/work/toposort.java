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
			term_name[j]=new DefaultMutableTreeNode("第"+j+"学期");
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
		

		HSSFWorkbook wb=new HSSFWorkbook();//创建工作薄
		HSSFSheet sheet=wb.createSheet();//创建工作表
		wb.setSheetName(0, "sheet1");//设置工作表
		HSSFRow row=null;
		HSSFCell cell=null;
		
		row=sheet.createRow(0);
		cell=row.createCell((short)0);
		cell.setCellValue("学期");
		cell=row.createCell((short)1);
		cell.setCellValue("课程号");
		cell=row.createCell((short)2);
		cell.setCellValue("学分");
		
		
		
		int term;
		double average=1.0*sum_xf/frame1.all_terms;
		if(average>frame1.t_max_xf) 
		{
			System.out.println("您选择的课程超过了学分要求！");
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
		if(count==frame1.t_V) {System.out.println("均匀排课成功！");break;}
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
			cell.setCellValue("第"+q+"学期");
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
//						System.out.println("您选择的课程超过了学分要求！");
//						new frame5();
//						return;
//					}
//				System.out.print(frame1.G[ans[count]].get(0).name+"    ");
//				
//				row=sheet.createRow(count+1);
//				cell=row.createCell((short)0);
//				cell.setCellValue("第"+q+"学期");
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
//			if(count==frame1.t_V)System.out.println("均匀排课成功！");
//		}
		
		
		JFrame frame=new JFrame("均匀排课");
		frame.setLocationRelativeTo(null);
		frame.setSize(500,400);
		frame.setLocation(300, 200);
		
		//实例化一个DefaultTreeCellRenderer对象
		DefaultTreeCellRenderer cellRender=new DefaultTreeCellRenderer();
		//设置处于折叠状态下非叶子节点的图标
		cellRender.setClosedIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\tree.jpg"));
		//设置叶子节点的图标
		cellRender.setLeafIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\h.jpg"));
		//设置处于展开状态下非叶子节点的图标
		cellRender.setOpenIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\j.jpg"));
		//设置非选定节点的背景色
		cellRender.setBackgroundNonSelectionColor(Color.lightGray);
		//设置节点在选中状态下的背景色
		cellRender.setBackgroundSelectionColor(Color.lightGray);
		//设置选中状态下节点边框的颜色
		cellRender.setBorderSelectionColor(Color.MAGENTA);
		//设置绘制选中状态下节点文本的颜色
		cellRender.setTextSelectionColor(Color.MAGENTA);
		
		
		
		ImageIcon img = new ImageIcon("D:\\BaiduNetdiskDownload\\z.jpg");//这是背景图片   

		JLabel imgLabel = new JLabel(img);//将背景图放在标签里。      

		frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。    

		imgLabel.setBounds(130,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置   

		//Container cp=frame.getContentPane();   
		
		
	    JTree jtree=new JTree(tree);
	    JButton butt=new JButton("保存计划");
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
				//调出选择窗口
				FileDialog fdlg = new FileDialog(frame_save, "保存Excel文件", FileDialog.LOAD);
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
		
		HSSFWorkbook wb=new HSSFWorkbook();//创建工作薄
		HSSFSheet sheet=wb.createSheet();//创建工作表
		wb.setSheetName(0, "sheet1");//设置工作表
		HSSFRow row=null;
		HSSFCell cell=null;
		
		row=sheet.createRow(0);
		cell=row.createCell((short)0);
		cell.setCellValue("学期");
		cell=row.createCell((short)1);
		cell.setCellValue("课程号");
		cell=row.createCell((short)2);
		cell.setCellValue("学分");
		
		
		
		int q = 1, cnt = 0;
		while (q <= frame1.all_terms)
		    {
		        int C= frame1.G[ans[cnt]].get(0).xf;
		        
		        while(cnt<frame1.t_V && C<= frame1.t_max_xf)
		        {
		            System.out.print(frame1.G[ans[cnt]].get(0).name);
		            
		            
		            row=sheet.createRow(cnt+1);
					cell=row.createCell((short)0);
					cell.setCellValue("第"+q+"学期");
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
		            System.out.println("集中排课成功!\n");
		            break;
		        }
		        q++;
		    }
		if(q>frame1.all_terms)
			{System.out.println("您选择的课程超过学分要求！");
			new frame5();
			return;}
		
		
		
		JFrame frame=new JFrame("均匀排课");
		frame.setLocationRelativeTo(null);
		frame.setSize(500,400);
		frame.setLocation(300, 200);
		
		//实例化一个DefaultTreeCellRenderer对象
				DefaultTreeCellRenderer cellRender=new DefaultTreeCellRenderer();
				//设置处于折叠状态下非叶子节点的图标
				cellRender.setClosedIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\tree.jpg"));
				//设置叶子节点的图标
				cellRender.setLeafIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\h.jpg"));
				//设置处于展开状态下非叶子节点的图标
				cellRender.setOpenIcon(new ImageIcon("D:\\BaiduNetdiskDownload\\j.jpg"));
				//设置非选定节点的背景色
				cellRender.setBackgroundNonSelectionColor(Color.lightGray);
				//设置节点在选中状态下的背景色
				cellRender.setBackgroundSelectionColor(Color.lightGray);
				//设置选中状态下节点边框的颜色
				cellRender.setBorderSelectionColor(Color.MAGENTA);
				//设置绘制选中状态下节点文本的颜色
				cellRender.setTextSelectionColor(Color.MAGENTA);
				
				ImageIcon img = new ImageIcon("D:\\BaiduNetdiskDownload\\z.jpg");//这是背景图片   

				JLabel imgLabel = new JLabel(img);//将背景图放在标签里。      

				frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。    

				imgLabel.setBounds(130,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置   

				//Container cp=frame.getContentPane();   
	    JTree jtree=new JTree(tree);
	    JButton butt=new JButton("保存计划");
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
				//调出选择窗口
				FileDialog fdlg = new FileDialog(new JFrame("保存"), "Excel文件", FileDialog.LOAD);
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
