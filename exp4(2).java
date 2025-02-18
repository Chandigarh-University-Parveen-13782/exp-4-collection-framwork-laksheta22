class TicketBooking implements Runnable {
    private static int availableSeats = 10;  
    private boolean isVIP;  
    private static final Object lock = new Object();  

    public TicketBooking(boolean isVIP) {
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        synchronized (lock) {
            if (availableSeats > 0) {
                if (isVIP) {
                    System.out.println(Thread.currentThread().getName() + " (VIP) is booking a seat...");
                } else {
                    System.out.println(Thread.currentThread().getName() + " is booking a seat...");
                }
                availableSeats--;
                System.out.println("Seat booked successfully. Remaining seats: " + availableSeats);
            } else {
                System.out.println("No available seats for " + Thread.currentThread().getName());
            }
        }
    }
    public static void main(String[] args) {
        Thread[] threads = new Thread[15];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new TicketBooking(true), "VIP-" + (i + 1));
            threads[i].setPriority(Thread.MAX_PRIORITY); 
        }
        for (int i = 5; i < 15; i++) {
            threads[i] = new Thread(new TicketBooking(false), "Customer-" + (i + 1));
            threads[i].setPriority(Thread.NORM_PRIORITY);  
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
