CoreBus
=======

自己封装的Android事件总线框架

  1.单线程单任务模型:
    所有的任务以排队的形式等待处理.处理结果自动回调.代替异步框架.
  2 多线程多任务模型:各个线程拥有自己的消息总线,相互独立,异步更新UI界面.
