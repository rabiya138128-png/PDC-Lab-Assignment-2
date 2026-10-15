public class TradingEngineApp {
    public static void main(String[] args) {
        System.out.println("=== Enterprise Financial Exchange Architecture Gateway ===");
        TradeEvent fixOrder = new FIXOrderEvent("GATEWAY-FIX", System.currentTimeMillis(), 1200.00, "ORD-7788");
        TradeEvent reutersQuote = new ReutersQuoteEvent("GATEWAY-REUTERS", System.currentTimeMillis(), 1195.00, 1194.50, 1196.25);

        fixOrder.dispatch();
        reutersQuote.dispatch();
        System.out.println("=== Execution Complete ===");
    }
}