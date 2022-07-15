class FooBar {
    private int n;
    private Object lock;
    private AtomicBoolean readyToSendFoo;
    private AtomicBoolean readyToSendBar;

    public FooBar(int n) {
        this.n = n;
        this.lock = new Object();
        this.readyToSendFoo = new AtomicBoolean(true);
        this.readyToSendBar = new AtomicBoolean(false);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (lock) {
                while (!readyToSendFoo.get()) {
                    lock.wait();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();

                readyToSendBar.set(true);
                readyToSendFoo.set(false);
                lock.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (lock) {
                while (!readyToSendBar.get()) {
                    lock.wait();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();

                readyToSendBar.set(false);
                readyToSendFoo.set(true);
                lock.notifyAll();
            }
        }
    }
}