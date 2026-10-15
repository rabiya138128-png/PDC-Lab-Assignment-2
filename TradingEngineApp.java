public class TradingEngineApp {
    public static void main(String[] args) {
        TradeEvent fixOrder = new FIXOrderEvent("GATEWAY-FIX", System.currentTimeMillis(), 1200.00, "ORD-7788");
        TradeEvent reutersQuote = new ReutersQuoteEvent("GATEWAY-REUTERS", System.currentTimeMillis(), 1195.00, 1194.50, 1196.25);
    }
}