
public class Context {
	private Window window;
	private Ticker ticker;
	private Game game;

    protected Context(Window window, Ticker ticker, Game game) {
        this.window = window;
        this.ticker = ticker;
        this.game = game;
    }

    public Window getWindow() {
        return w;
    }

    public void setWindow(Window w) {
        this.w = w;
    }

    public Ticker getWindow() {
        return w;
    }

    public void setTicker(Ticker t) {
        this.t = t;
    }

    public Game getGame() {
        return g;
    }

    public void setGame(Game g) {
        this.g = g;
    }

	private static class Factory {
	    private Window w;
	    private Ticker t;
	    private Game g;

	    public Window getWindow() {
	        return w;
	    }

        // allow chaining
	    public Factory setWindow(Window w) {
	        this.w = w;
	        return this;
	    }

	    public Ticker getWindow() {
	        return w;
	    }

	    public Factory setTicker(Ticker t) {
	        this.t = t;
	        return this;
	    }

	    public Game getGame() {
	        return g;
	    }

	    public Factory setGame(Game g) {
	        this.g = g;
	        return this;
	    }

	    public Context create() {
	        if (w == null) {
	            w = new Window();
	        }
	        if (t == null) {
	            t = new Ticker();
	        }
	        if (g == null) {
	            g = new Game();
	        }
	    }

	}
}
