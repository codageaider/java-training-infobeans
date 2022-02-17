package day4;

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(counter);
        Thread thread2 = new Thread(counter);
        Thread thread3 = new Thread(counter);
        thread1.start();
        thread2.start();
        thread3.start();

        while(true){
            if(!thread1.isAlive() && !thread2.isAlive() && !thread3.isAlive())
                break;
        }

// Each thread is incrementing the counter 100 times. So after the
        // count = 300
        System.out.println(counter.getCount());

    }
}

class Counter implements Runnable {
    private int count = 0;

    @Override // count = 3 * 1000_00
    public void run() {
        for (int i = 0; i < 1000_00; i++) {

//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
/*
Race condition-> Operating systems.
count++ <-- this is the instruction that we are asking our 3 threads to execute.
count = count + 1
Java code -> .class files (bytecode) -> machine language code is generated
(Assembly language coding -> registers and everything)
mainRegister=1
count++;
register2 = 2   (CPU ALU unit does the calculation)
mainRegister = 2; and copy this value to count variable
count++;
In machine language this gets converted into multiple instructions.
1) copy the value of count variable
2) increment this value that is copied
3) copy back the incremented value into the count variable.

count = 0
thread1
count++ -> 1)  copy = 0 | (There is a context switch - The thread1 losses the CPU)  2)   3)
thread2
count++ -> 1) copy=0 -> 2) copy = 1 -> 3) count = 1
So now the value of count = 1
Now thread1 which was put in waiting queue (It's waiting to get the CPU)
count++ -> 1)  copy = 0 | (There is a context switch - The thread1 losses the CPU)  2) copy=1  3) count=1
count =1
After one execution of thread1 and thread2 the value of variable count should be 2.
But due to context switch in the middle this value becomes 1.
*/