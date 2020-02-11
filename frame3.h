#ifndef FRAME3_H
#define FRAME3_H
#include"frame1.h"
#include <QWidget>

namespace Ui {
class frame3;
}

class frame3 : public QWidget
{
    Q_OBJECT

public:
    frame1 *w;
    bool jizhong=false;
    explicit frame3(QWidget *parent = nullptr);
    frame3(int,int,int,frame1 *,QWidget *parent = nullptr);
    ~frame3();

private slots:
    void on_buttonBox_accepted();

    void on_buttonBox_rejected();

private:
    Ui::frame3 *ui;
};

#endif // FRAME3_H
