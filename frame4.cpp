#include "frame4.h"
#include "ui_frame4.h"
#include"qprocess.h"

frame4::frame4(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::frame4)
{
    ui->setupUi(this);
     setStyleSheet ("border:1px groove gray;border-radius:5px;padding:1px 2px;");
   // setWindowOpacity(0.9);

    QPalette bgpal = palette();
    bgpal.setColor (QPalette::Background, QColor (170, 255, 127, 255));
    bgpal.setColor (QPalette::Foreground, QColor (170, 255, 127,255)); setPalette (bgpal);
}

frame4::~frame4()
{
    delete ui;
}

void frame4::on_pushButton_clicked()
{
    qApp->quit();
    QProcess::startDetached(qApp->applicationFilePath(), QStringList());
}
