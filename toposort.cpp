#include "toposort.h"
#include"frame1.h"
#include<QStack>
#include<qdebug.h>
#include"frame5.h"
#include"ui_frame6.h"
#include"frame6.h"

toposort::toposort()
{
topo_flag=false;
sum_xf=0;
}


void toposort::topo(QVector<node>G[],QMap<QString,int>mp,int t_V)
{

            int cnt=0;
            int inedge[1009];
            for(int i=0;i<t_V;i++)
            {
                inedge[i]=G[i].size()-1;
                sum_xf+=G[i][0].xf;
            }
            QStack<int> s;
            for(int i=0;i<t_V;i++)
            {
                if(inedge[i]==0)s.push(i);
            }
            while(s.size()!=0)
            {
                int cur=s.pop();
                ans[cnt++]=cur;
                for(int j=0;j<t_V;j++)
                {
                    for(int p=1;p<G[j].size();p++)
                    {
                        if(mp[G[j][p].name]==cur)
                            {inedge[j]--;
                            if(inedge[j]==0)s.push(j);
                            }
                    }
                }
            }

            if(cnt==t_V) {topo_flag=true;}

            for(int i=0;i<t_V;i++)
            {
                qDebug()<<ans[i];
            }
}


void toposort::sort1(int all_terms,int max_xf,int t_V,QVector<node>G[])
{
  //  bool xf_flag=true;
    QVector<node> Q[all_terms+1];
    int term;
    double average=1.*sum_xf/all_terms;
            if(average>max_xf)
            {
                frame5 *frame_5=new frame5();
                frame_5->show();
                //xf_flag=false;
                return;
            }

    while(average<=1.0*max_xf)
    {
            int count=0;
            qDebug()<<"average:"<<average<<"sum_xf:"<<sum_xf;
            for(term=1;term<=all_terms;term++)
            {
                int sum=0;
                while(sum+G[ans[count]][0].xf<=average)
                {
                    sum+=G[ans[count]][0].xf;
                    qDebug()<<term<<G[ans[count]][0].name;
                    count++;
                    if(count==t_V)break;
                }
                if(count==t_V)break;
            }

         if(count==t_V){qDebug()<<"均匀排课成功！";break;}
         else {average++;}
    }

    int q=1,count=0;
    for(term=1;term<=all_terms;term++)
      {
       int sum=0;
       while(sum+G[ans[count]][0].xf<=average)
            {
                sum+=G[ans[count]][0].xf;
                Q[term].append(G[ans[count++]][0]);
                if(count==t_V)break;
            }
            q++;
            if(count==t_V)break;
        }



         frame6 *frame_6=new frame6(all_terms,Q);
         frame_6->show();

}


void toposort::sort2(int all_terms,int max_xf,int t_V,QVector<node> G[])
{
    QVector<node> Q[all_terms+1];
    int q = 1, cnt = 0;
    while (q <=all_terms)
         {
           int C=G[ans[cnt]][0].xf;
           while(cnt<t_V && C<=max_xf)
              {
                qDebug()<<q<<G[ans[cnt]][0].name;
                Q[q].append(G[ans[cnt]][0]);
                if(cnt+1<t_V) C=C+G[ans[cnt+1]][0].xf;
                   cnt++;
              }
           if(cnt>=t_V )
              {
               qDebug()<<"集中排课成功！";
               break;
              }
           q++;
         }
   if(q>all_terms)
      {
       frame5 *frame_5=new frame5();
       frame_5->show();
       return;
      }

   frame6 *frame_6=new frame6(all_terms,Q);
   frame_6->show();
}

