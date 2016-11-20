# Learn-java.util.concurrent
- CountDownLatch被用来同步一个或多个任务，强制它们等待由其他任务执行的一组操作完成。
通过向CountDownLatch对象设置一个初始计数值，任何在这个对象上调用wait的方法都将阻塞，直至这个计数值到达0。其他任务在结束其工作时，可以在该对象上调用CountDown（）来减小这个计数值。
- CyclicBarrier(int parties)
Creates a new CyclicBarrier that will trip when the given number of parties (threads) are waiting upon it, and does not perform a predefined action when the barrier is tripped.CyclicBarrier(int parties, Runnable barrierAction)
Creates a new CyclicBarrier that will trip when the given number of parties (threads) are waiting upon it, and which will execute the given barrier action when the barrier is tripped, performed by the last thread entering the barrier.
可以向CyclicBarrier传递一个任务数以及一个runnable,runnable将在所有线程都处于wait状态时执行。
- DelayQueue
是一个无界的BlockingQueue，用于防止实现了Delayed接口的对象，其对象只能在其到期时才能被取走。这种对列是有序的，即队头对象延迟到期时间最久远（任务最紧急）。使用take方法将取走队头，实际就成为了优先队列的一种
- PriorityBlockingQueue
优先级队列，具有可阻塞的读取操作，优先级队列的对象按照优先级顺序在队列中排队执行的。对任务实现Coparable接口，或者创建PriorityBlocking时传递Comparator参数
- ScheduledThreadPoolExecutor
在多线程时代替timer或者线程容量有灵活的扩容需求时使用。遵循FIFO原则，具有固定的线程池数目。
schedule()延时执行,scheduleAtFixedRate()周期性执行（不受任务执行时间影响），scheduleWithFixedDelay()在任务完成后延时固定时间重复执行
