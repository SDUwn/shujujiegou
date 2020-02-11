#ifndef FRAME4_H
#define FRAME4_H

#include <QWidget>

namespace Ui {
class frame4;
}

class frame4 : public QWidget
{
    Q_OBJECT

public:
    explicit frame4(QWidget *parent = nullptr);
    ~frame4();

private slots:
    void on_pushButton_clicked();

private:
    Ui::frame4 *ui;
};

#endif // FRAME4_H
