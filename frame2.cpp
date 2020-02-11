#include "frame2.h"
#include "ui_frame2.h"
#include"toposort.h"
#include"frame1.h"
#include"frame4.h"

frame2::frame2(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::frame2)
{
    ui->setupUi(this);

}

frame2::~frame2()
{
    delete ui;
}

frame2::frame2(int all_terms,int max_xf,int t_V,frame1 * ww,QWidget *parent) :
    QWidget(parent),
    ui(new Ui::frame2)
{

    ui->setupUi(this);

    //setWindowOpacity(0.8);
    setStyleSheet ("QWidget{border:1px groove gray;border-radius:5px;padding:1px 2px;}");

    QPalette bgpal = palette();
    bgpal.setColor (QPalette::Background, QColor (170, 255, 127, 255));
    bgpal.setColor (QPalette::Foreground, QColor (170, 255, 127,255)); setPalette (bgpal);

    ui->label_5->setText(QString::number(all_terms));
    ui->label_6->setText(QString::number(max_xf));
    ui->label_7->setText(QString::number(t_V));
    ui->label_8->setText("均匀排课");
    w=ww;
}

void frame2::on_buttonBox_accepted()
{
    this->close();
        toposort t;
        t.topo(w->G,w->mp,w->t_V);
        if(t.topo_flag==true)t.sort1(w->all_terms,w->max_xf,w->t_V,w->G);
        else{
            frame4 *frame_4=new frame4();
            frame_4->show();
        }
}

void frame2::on_buttonBox_rejected()
{
    this->close();
}
