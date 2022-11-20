package ReaderWriter;

public class readerwriter {
    int areader = 0, awriter = 0, wwriter = 0, wreader = 0;

    public int allowreader() {
        int res = 0;
        if (wreader == 0 && awriter == 0) {
            res = 1;
        }
        return res;
    }

    public int allowwriter() {
        int res = 0;
        if (areader == 0 && awriter == 0) {
            res = 1;
        }
        return res;
    }

    synchronized void beforeread() {
        wreader++;
        while (allowreader() != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
            wreader--;
            areader++;
        }
    }

    synchronized void beforewrite() {
        wwriter++;
        while (allowwriter() != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
            wwriter--;
            awriter++;
        }
    }

    synchronized void afterread() {
        areader--;
        notifyAll();
    }

    synchronized void afterwrite() {
        awriter--;
        notifyAll();
    }
}

class rew {
    static readerwriter ctl;

    public static void main(String[] args) {
        ctl = new readerwriter();
        new reader1(ctl).start();
        new reader2(ctl).start();
        new writer1(ctl).start();
        new writer2(ctl).start();
    }
}

class reader1 extends Thread {
    readerwriter ctl;

    public reader1(readerwriter c) {
        ctl = c;
    }

    public void run() {
        while (true) {
            ctl.beforeread();
            System.out.println("Reader1 Reading");
            System.out.println("Done Reading");
            ctl.afterread();
            System.out.println("After Read");
        }
    }
}

class reader2 extends Thread {
    readerwriter ctl;

    public reader2(readerwriter c) {
        ctl = c;
    }

    public void run() {
        while (true) {
            ctl.beforeread();
            System.out.println("Reader2 Reading");
            System.out.println("Done Reading");

            ctl.afterread();
            System.out.println("After Read");
        }
    }
}

class writer1 extends Thread {
    readerwriter ctl;

    public writer1(readerwriter c) {
        ctl = c;
    }

    public void run() {
        while (true) {
            ctl.beforewrite();
            System.out.println("Writer1 Writing");
            System.out.println("Done Writing");
            ctl.afterread();
            System.out.println("After Write");
        }
    }
}

class writer2 extends Thread {
    readerwriter ctl;

    public writer2(readerwriter c) {
        ctl = c;
    }

    public void run() {
        while (true) {
            ctl.beforewrite();
            System.out.println("Writer2 Writing");
            System.out.println("Done Writing");
            ctl.afterread();
            System.out.println("After Write");
        }
    }

}