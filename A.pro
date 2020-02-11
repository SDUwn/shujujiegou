QT       += core gui
QT       += axcontainer
greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

CONFIG += c++11

# The following define makes your compiler emit warnings if you use
# any Qt feature that has been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    main.cpp \
    frame1.cpp \
    frame2.cpp \
    frame3.cpp \
    frame4.cpp \
    frame5.cpp \
    frame6.cpp \
    node.cpp \
    toposort.cpp

HEADERS += \
    frame1.h \
    frame2.h \
    frame3.h \
    frame4.h \
    frame5.h \
    frame6.h \
    node.h \
    toposort.h

FORMS += \
    frame1.ui \
    frame2.ui \
    frame3.ui \
    frame4.ui \
    frame5.ui \
    frame6.ui

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

RESOURCES += \
    pic.qrc
