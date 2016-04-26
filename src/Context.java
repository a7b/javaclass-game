import java.io.IOException;


public class Context {

	public static final int TICK = 100;

	private Window window;
	private Ticker ticker;
	private Game game;
	private MainMenu mainMenu;
	private Instructions instructions;

	protected Context(Window window, Ticker ticker, Game game,
			MainMenu mainMenu, Instructions instructions) {
        this.window = window;
        this.ticker = ticker;
        this.game = game;
		this.mainMenu = mainMenu;
		this.instructions = instructions;
		this.window.setContext(this);
		this.ticker.setContext(this);
		this.game.setContext(this);
		this.mainMenu.setContext(this);
		this.instructions.setContext(this);
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

	public Instructions getInstructions() {
		return instructions;
	}

	public void setInstructions(Instructions instructions) {
		this.instructions = instructions;
	}

	public static class Factory {
	    private Window w;
	    private Ticker t;
	    private Game g;
		private MainMenu mm;
		private Instructions i;

		public Factory() {
			this.w = null;
			this.t = null;
			this.g = null;
			this.mm = null;
			this.i = null;
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

		public Instructions getInstructions() {
			return i;
		}

		public Factory setInstructions(Instructions i) {
			this.i = i;
			return this;
		}

	    public Context create() throws IOException {
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
			if (i == null) {
				i = new Instructions();
			}
			return new Context(w, t, g, mm, i);
	    }

	}
}
