#ifndef TOPOSORT_H
#define TOPOSORT_H
#include"node.h"
#include"qvector.h"
#include"qmap.h"
#include"frame6.h"

class toposort
{
public:
    //frame6 *frame_6;
    int ans[1009];
    double sum_xf;
    bool topo_flag;
    toposort();
    void topo(QVector<node>[],QMap<QString,int>,int);
    void sort1(int,int,int,QVector<node>[]);
    void sort2(int,int,int,QVector<node>[]);
};

#endif // TOPOSORT_H
