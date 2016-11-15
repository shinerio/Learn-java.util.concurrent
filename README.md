# Learn-java.util.concurrent
- CountDownLatch被用来同步一个或多个任务，强制它们等待由其他任务执行的一组操作完成。
通过向CountDownLatch对象设置一个初始计数值，任何在这个对象上调用wait的方法都将阻塞，直至这个计数值到达0。其他任务在结束其工作时，可以在该对象上调用CountDown（）来减小这个计数值。
