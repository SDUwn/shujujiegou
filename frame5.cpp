#include "frame5.h"
#include "ui_frame5.h"
#include<qprocess.h>

frame5::frame5(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::frame5)
{

    ui->setupUi(this);
    setStyleSheet ("border:1px groove gray;border-radius:5px;padding:1px 2px;");
   // setWindowOpacity(0.9);

    QPalette bgpal = palette();
    bgpal.setColor (QPalette::Background, QColor (170, 255, 127, 255));
    bgpal.setColor (QPalette::Foreground, QColor (170, 255, 127,255)); setPalette (bgpal);
}

frame5::~frame5()
{
    delete ui;
}


void frame5::on_pushButton_clicked()
{
    this->close();
    qApp->quit();
    QProcess::startDetached(qApp->applicationFilePath(), QStringList());
}
