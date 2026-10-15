public class PacketSymmetryAuditor {
    public static void main(String[] args) {
        System.out.println("=== Starting Question 2 Audit ===");
        String payload = "MADAM";
        System.out.println("Payload: " + payload);

        TransactionStack stack = new TransactionStack(payload.length());
        RequestQueue queue = new RequestQueue(payload.length());

        for (int i = 0; i < payload.length(); i++) {
            char c = payload.charAt(i);
            stack.push(c);
            queue.enqueue(c);
        }

        boolean isSymmetric = true;
        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                isSymmetric = false;
                break;
            }
        }

        if (isSymmetric) {
            System.out.println("[RESULT] Payload exhibits bidirectional symmetry.");
        } else {
            System.out.println("[RESULT] Asymmetry detected.");
        }
        System.out.println("=== Question 2 Complete ===");
    }
}