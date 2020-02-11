#ifndef FRAME5_H
#define FRAME5_H

#include <QWidget>

namespace Ui {
class frame5;
}

class frame5 : public QWidget
{
    Q_OBJECT

public:
    explicit frame5(QWidget *parent = nullptr);
    ~frame5();

private slots:
    void on_pushButton_clicked();

private:
    Ui::frame5 *ui;
};

#endif // FRAME5_H
