#ifndef FRAME6_H
#define FRAME6_H
#include"node.h"
#include <QWidget>

namespace Ui {
class frame6;
}

class frame6 : public QWidget
{
    Q_OBJECT

public:
    QVector<node> Q[109];
    int all_terms;
    explicit frame6(QWidget *parent = nullptr);
    frame6(int,QVector<node>[],QWidget *parent = nullptr);
    ~frame6();

private slots:
    void on_pushButton_clicked();

private:
    Ui::frame6 *ui;
};

#endif // FRAME6_H
