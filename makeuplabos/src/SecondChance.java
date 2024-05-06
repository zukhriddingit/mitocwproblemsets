import java.util.Arrays;

public class SecondChance {
    int[] frames;
    boolean[] referenceBits;
    int capacity;
    int front, rear, count;

    public SecondChance(int capacity) {
        this.capacity = capacity;
        this.frames = new int[capacity];
        this.referenceBits = new boolean[capacity];
        Arrays.fill(frames, -1); // Initialize frames to -1 indicating empty
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    private int findPage(int page) {
        for (int i = 0; i < capacity; i++) {
            if (frames[i] == page) {
                return i;
            }
        }
        return -1;
    }

    public boolean referencePage(int page) {
        int index = findPage(page);
        if (index != -1) {
            // Page hit
            referenceBits[index] = true;
            return true;
        }

        // Page fault
        while (referenceBits[front]) {
            referenceBits[front] = false;
            front = (front + 1) % capacity;
        }

        frames[front] = page;
        referenceBits[front] = false;
        front = (front + 1) % capacity;
        return false;
    }

    public void simulate(int[] referenceString) {
        int faults = 0;
        int hits = 0;

        for (int page : referenceString) {
            if (referencePage(page)) {
                hits++;
            } else {
                faults++;
            }
        }

        double faultRate = (double) faults / referenceString.length * 100;
        double hitRate = (double) hits / referenceString.length * 100;

        System.out.println("Final frames: " + Arrays.toString(frames));
        System.out.println("Total faults: " + faults);
        System.out.println("Total hits: " + hits);
        System.out.println("Fault rate: " + String.format("%.2f", faultRate) + "%");
        System.out.println("Hit rate: " + String.format("%.2f", hitRate) + "%");
    }

    public static void main(String[] args) {
        int[] referenceString1 = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int[] referenceString2 = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int[] referenceString3 = {1, 2, 3, 1, 4, 5, 6, 7, 8, 7, 8, 9, 7, 8, 9, 5, 4, 5, 4, 2};

        SecondChance sc1 = new SecondChance(3);
        SecondChance sc2 = new SecondChance(4);
        SecondChance sc3 = new SecondChance(2);

        System.out.println("Test Input 1:");
        sc1.simulate(referenceString1);
        System.out.println("\nTest Input 2:");
        sc2.simulate(referenceString2);
        System.out.println("\nTest Input 3:");
        sc3.simulate(referenceString3);
    }
}