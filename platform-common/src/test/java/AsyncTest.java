
public class AsyncTest {

    public static void main(String[] args) throws InterruptedException {
        int a =0 ;
        int b ;
        int r1;
        int r2;
        
        r2 = a;
        b = 1;
        r1 = b;
        a = 2;
        print(r1 + "");
        print(r2 + "");

        final int finalB = b;
        final int finalA = a;
        for(int i = 1; i < 3; i++){
            print("( " + i + " ) :");
            Demo1 demo1 = new Demo1();
            Thread t1 = new Thread(new Runner1(demo1));
            Thread t2 = new Thread(new Runner2(demo1));
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            print("r1 => " + demo1.r1 + ", r2 => " + demo1.r2);
            if (demo1.r1 == 1 && demo1.r2 == 2){
                print("Fuck !!!!!!!!!!!!!!!!!!!!!!");
            }
            print("*****************************");
        }
    }

    public static void print(String msg){
        System.out.println(msg);
    }
}

interface execCallBack{
    public void execute(int a, int b);
}

class Runner1 implements Runnable{
    Demo1 d ;
    public Runner1(Demo1 d){
        this.d = d;
    }
    @Override
    public void run() {
        d.exec();
//        System.out.println("r1 => " + d.r1 + ", r2 => " + d.r2 + ", a => " + d.a + ", b => " + d.b);
    }
}

class Runner2 implements Runnable{
    Demo1 d ;
    public Runner2(Demo1 d){
        this.d = d;
    }
    @Override
    public void run() {
        d.exec();
//        System.out.println("r1 => " + d.r1 + ", r2 => " + d.r2 + ", a => " + d.a + ", b => " + d.b);
    }
}

class Demo1{
    public int a ;
    public int b ;
    public int r1;
    public int r2;
    public synchronized void exec(){
        r2 = a;
        b = 1;
        r1 = b;
        a = 2 ;
    }
}