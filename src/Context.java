
public class Context {

	public static final int TICK = 250;

	private Window window;
	private Ticker ticker;
	private Game game;
	private MainMenu mainMenu;

	protected Context(Window window, Ticker ticker, Game game, MainMenu mainMenu) {
        this.window = window;
        this.ticker = ticker;
        this.game = game;
		this.setMainMenu(mainMenu);
		this.window.setContext(this);
		this.ticker.setContext(this);
		this.game.setContext(this);
		this.mainMenu.setContext(this);
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public static class Factory {
	    private Window w;
	    private Ticker t;
	    private Game g;
		private MainMenu mm;

		public Factory() {
			this.w = null;
			this.t = null;
			this.g = null;
			this.mm = null;
		}

	    public Window getWindow() {
	        return w;
	    }

        // allow chaining
	    public Factory setWindow(Window w) {
	        this.w = w;
	        return this;
	    }

	    public Ticker getTicker() {
	        return t;
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

		public MainMenu getMainMenu() {
			return mm;
		}

		public Factory setMainMenu(MainMenu mm) {
			this.mm = mm;
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
			if (mm == null) {
				mm = new MainMenu();
			}
			return new Context(w, t, g, mm);
	    }

	}
}
