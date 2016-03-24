import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Ticker implements ActionListener {
	private Context ctx;

	Ticker() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ctx.getGame().tick();
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}
}