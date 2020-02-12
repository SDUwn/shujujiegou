#ifndef FRAME1_H
#define FRAME1_H
#include"node.h"
#include <QMainWindow>
#include<qmap.h>

QT_BEGIN_NAMESPACE
namespace Ui { class frame1; }
QT_END_NAMESPACE

class frame1 : public QMainWindow
{
    Q_OBJECT

public:
    friend class frame2;
    frame1(QWidget *parent = nullptr);
    ~frame1();
    bool  func_junyun;
     int all_terms,max_xf,t_V;
     QVector<node> G[1009];
     QMap<QString,int> mp;

private slots:
    void on_junyun_clicked();

    void on_jizhong_clicked();

    void on_start_clicked();

    void on_commandLinkButton_2_clicked();

    void on_commandLinkButton_3_clicked();

private:
    Ui::frame1 *ui;
};
#endif // FRAME1_H
