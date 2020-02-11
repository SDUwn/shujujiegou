#ifndef FRAME2_H
#define FRAME2_H
#include"frame1.h"
#include <QWidget>

#include <QWidget>

namespace Ui {
class frame2;
}

class frame2 : public QWidget
{
    Q_OBJECT

public:
    frame1 *w;
    bool junyun=false;
    explicit frame2(QWidget *parent = nullptr);
    frame2(int,int,int,frame1 *,QWidget *parent = nullptr);
    ~frame2();


private slots:
    void on_buttonBox_accepted();

    void on_buttonBox_rejected();

private:
    Ui::frame2 *ui;
};

#endif // FRAME2_H
