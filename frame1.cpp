#include "frame1.h"
#include "ui_frame1.h"
#include <QDebug>
#include<string.h>
#include"toposort.h"
#include"frame2.h"
#include<qfiledialog.h>
#include<qdesktopservices.h>
#include"frame3.h"
#include<QAxObject>

frame1::frame1(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::frame1)
{
    ui->setupUi(this);

   // setWindowOpacity(0.9);
    setStyleSheet ("border:1px groove gray;border-radius:5px;padding:1px 2px;");
    QPalette bgpal = palette();
    bgpal.setColor (QPalette::Background, QColor (170, 255, 127, 255));
    bgpal.setColor (QPalette::Foreground, QColor (170, 255, 127,255));
    setPalette (bgpal);

}

frame1::~frame1()
{
    delete ui;
}


void frame1::on_junyun_clicked()
{
    func_junyun=true;
}

void frame1::on_jizhong_clicked()
{
    func_junyun=false;
}

void frame1::on_start_clicked()
{
    all_terms=ui->all_term->text().toInt();
    max_xf=ui->max_xf->text().toInt();
    t_V=0;
    for(int i=0;i<ui->tableWidget->rowCount();i++){
        for(int j=0;j<ui->tableWidget->columnCount();j++){
            if(ui->tableWidget->item(i,j)!=NULL){
                qDebug()<<ui->tableWidget->item(i,j)->text();
                t_V++;
            }
        }
    }
    t_V/=3;
    qDebug()<<all_terms<<"  "<<max_xf<<"  "<<t_V;
    for(int i=0;i<t_V;i++)
    {
        node n;
        strcpy(n.name,ui->tableWidget->item(i,0)->text().toUtf8().data());
        n.xf=ui->tableWidget->item(i,1)->text().toInt();
        qDebug()<<n.name<<"   "<<n.xf<<endl;
        G[i].append(n);
        mp[n.name]=i;
    }
  for(int j=0;j<t_V;j++)
     {
        QString pro=ui->tableWidget->item(j,2)->text();
        int k=0;
        while(pro[k]!='#')
          {
           QString sname=""+pro[k]+pro[k+1]+pro[k+2];
           k=k+3;
           G[j].append(G[mp[sname]][0]);
           //qDebug()<<j<<"sname:"<<sname;
          }
   }
  for(int i=0;i<t_V;i++)
  {
      for(int j=0;j<G[i].size();j++){
          qDebug()<<i<<"     "<<G[i][j].name<<"  ";
      }
  }



  if(func_junyun==true){
      frame2 *frame_2=new frame2(all_terms,max_xf,t_V,this);
      frame_2->show();
      this->hide();
  }else{
      frame3 *frame_3=new frame3(all_terms,max_xf,t_V,this);
      frame_3->show();
      this->hide();
     }
}

void frame1::on_commandLinkButton_2_clicked()
{
    QString file_name = QFileDialog::getOpenFileName(this,
            tr("Open File"),
            "",
            "",
            0);
     QFile file(file_name);
     if(file.exists()){
         QDesktopServices::openUrl(QUrl::fromLocalFile(file_name));
     }
}

void frame1::on_commandLinkButton_3_clicked()
{

        QAxObject* excel = new QAxObject("Excel.Application");

        excel->setProperty("Visible", false);

        QAxObject* workbooks = excel->querySubObject("WorkBooks");

        QString file_name = QFileDialog::getOpenFileName(this,
                tr("Open File"),
                "",
                "",
                0);

        workbooks->dynamicCall("Open (const QString&)", file_name); //filename

        QAxObject* workbook = excel->querySubObject("ActiveWorkBook");

       // QAxObject* worksheets = workbook->querySubObject("WorkSheets");

        QAxObject* worksheet = workbook->querySubObject("Worksheets(int)", 1); //worksheet number

        QAxObject* usedrange = worksheet->querySubObject("UsedRange");

        QAxObject* rows = usedrange->querySubObject("Rows");

        QAxObject* columns = usedrange->querySubObject("Columns");

 //       int intRowStart = usedrange->property("Row").toInt();

 //       int intColStart = usedrange->property("Column").toInt();

        int intCols = columns->property("Count").toInt();

        int intRows = rows->property("Count").toInt();

        QAxObject * cell;

        for (int i = 2; i <2 + intRows; i++)

        {
            for (int j = 1; j < 1 + intCols; j++)
            {
                cell = excel->querySubObject("Cells(Int, Int)", i, j );
                QVariant cellValue = cell->dynamicCall("value");
               if(cellValue.toString()!="")
               {
                QTableWidgetItem *item=new QTableWidgetItem;
                item->setText(cellValue.toString());
                ui->tableWidget->setItem(i-2,j-1,item);
               }
            }

        }

        excel->setProperty("DisplayAlerts", 0);

        workbook->dynamicCall("Save(void)");

        workbook->dynamicCall("Close (Boolean)", false);

        excel->setProperty("DisplayAlerts",1);

        delete excel;
        qDebug()<<"导入表格完成！";
}
