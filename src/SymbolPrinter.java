
class SymbolThread extends Thread {
    char symbol;
    int count;
    private static Object object;

    SymbolThread(char s, int c, Object o) {
        symbol = s;
        count = c;
        object = o;
    }

    public void run() {
        synchronized (object) {
            for(int i = 0; i < count; i++) {
                System.out.println(symbol);

                object.notifyAll();
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            object.notifyAll();
        }
    }
}

public class SymbolPrinter {

    public static void main(String[] args) {

        Object object = new Object();

        SymbolThread dash = new SymbolThread('-', 50, object);
        SymbolThread vbar = new SymbolThread('|', 50, object);

        dash.start();
        vbar.start();
    }
}
