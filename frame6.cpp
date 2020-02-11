#include "frame6.h"
#include "ui_frame6.h"
#include<QFileDialog>
#include<QAxObject>
#include<qdebug.h>

frame6::frame6(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::frame6)
{
    ui->setupUi(this);
}


frame6::frame6(int all_termss,QVector<node> QQ[],QWidget *parent) :
    QWidget(parent),
    ui(new Ui::frame6)
{
    ui->setupUi(this);
    setStyleSheet ("border:1px groove gray;border-radius:5px;padding:1px 2px;");
   // setWindowOpacity(0.9);

    QPalette bgpal = palette();
    bgpal.setColor (QPalette::Background, QColor (170, 255, 127, 255));
    bgpal.setColor (QPalette::Foreground, QColor (170, 255, 127,255)); setPalette (bgpal);

    setWindowOpacity(0.9);

    ui->treeWidget->setColumnCount(1); //设置列数

    ui->treeWidget->setHeaderLabel(tr("课程安排")); //设置头的标题

    all_terms=all_termss;
    for(int i=1;i<=all_terms;i++){
        Q[i]=QQ[i];
    }


    QTreeWidgetItem *imageItem_i[all_terms];
    for(int i=1;i<=all_terms;i++)
    {
        imageItem_i[i]=new QTreeWidgetItem(ui->treeWidget,QStringList(QString("第"+QString::number(i)+"学期")));
        imageItem_i[i]->setIcon(0,QIcon("D:\\BaiduNetdiskDownload\\n.jpg"));
        QTreeWidgetItem *imageItem_sub[Q[i].size()];
        for(int j=0;j<Q[i].size();j++)
        {
            imageItem_sub[j]=new QTreeWidgetItem(imageItem_i[i],QStringList(QString(Q[i][j].name)));
            imageItem_sub[j]->setIcon(0,QIcon("D:\\BaiduNetdiskDownload\\m.jpg"));

            imageItem_i[i]->addChild((imageItem_sub[j]));
        }
    }

    ui->treeWidget->expandAll(); //结点全部展开

}

frame6::~frame6()
{
    delete ui;
}

void frame6::on_pushButton_clicked()
{

    QString filepath = QFileDialog::getSaveFileName(this, QString::fromLocal8Bit("导出表格"), ".", tr("Microsoft Office(*.xlsx)"));//获取保存路径
        if (!filepath.isEmpty()){

            QAxObject *excel = new QAxObject(this);

            excel->setControl("Excel.Application");//连接Excel控件

            excel->dynamicCall("SetVisible (bool Visible)", "false");//不显示窗体

            excel->setProperty("DisplayAlerts", false);//不显示任何警告信息。如果为true那么在关闭是会出现类似“文件已修改，是否保存”的提示



            QAxObject *workbooks = excel->querySubObject("WorkBooks");//获取工作簿集合

            workbooks->dynamicCall("Add");//新建一个工作簿

            QAxObject *workbook = excel->querySubObject("ActiveWorkBook");//获取当前工作簿

            QAxObject *worksheets = workbook->querySubObject("Sheets");//获取工作表集合

            QAxObject *worksheet = worksheets->querySubObject("Item(int)", 1);//获取工作表集合的工作表1，即sheet1

            QAxObject *range1 = worksheet->querySubObject("Range(QString)", "A1:A1");
            range1->setProperty("Value", "学期");
            QAxObject *range2 = worksheet->querySubObject("Range(QString)", "B1:B1");
            range2->setProperty("Value", "课程号");
            QAxObject *range3 = worksheet->querySubObject("Range(QString)", "C1:C1");
            range3->setProperty("Value", "学分");
            int count=1;
            for(int i=1;i<=all_terms;i++){
                for(int j=0;j<Q[i].size();j++){
                    count++;
                    QAxObject *rangei_j = worksheet->querySubObject("Range(QString)", "A"+QString::number(count)+":A"+QString::number(count));
                    rangei_j->setProperty("Value", "第"+QString::number(i)+"学期");
                    QAxObject *rangei_j_B = worksheet->querySubObject("Range(QString)", "B"+QString::number(count)+":B"+QString::number(count));
                    rangei_j_B->setProperty("Value", Q[i][j].name);
                    QAxObject *rangei_j_C = worksheet->querySubObject("Range(QString)", "C"+QString::number(count)+":C"+QString::number(count));
                    rangei_j_C->setProperty("Value", Q[i][j].xf);
                }
            }

            workbook->dynamicCall("SaveAs(const QString&)", QDir::toNativeSeparators(filepath));//保存至filepath，注意一定要用QDir::toNativeSeparators将路径中的"/"转换为"\"，不然一定保存不了。

            workbook->dynamicCall("Close()");//关闭工作簿

            excel->dynamicCall("Quit()");//关闭excel

            delete excel;

            excel = NULL;

        }
        this->close();
}
