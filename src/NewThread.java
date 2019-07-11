class NewThread implements Runnable {
    String name; // name of thread
    Thread t;
    boolean suspendFlag;

    public static NewThread createAndStart() {
        NewThread myThrd = new NewThread();
        myThrd.t.start();
        return myThrd;
    }

    public static NewThread createAndStart(String name) {
        NewThread myThrd = new NewThread(name);
        myThrd.t.start();
        return myThrd;
    }

    public NewThread() {
        this("Anonymous Thread");
    }

    public NewThread(String name) {
        this.name = name;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        suspendFlag = false;
    }


    @Override
    public void run() {
        try {
            for (int i = 15; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted");
        }
        System.out.println(name + " exiting.");
    }

    synchronized void mysuspend() {
        suspendFlag = true;
    }

    synchronized void myresume() {
        suspendFlag = false;
        notify();
    }
}
