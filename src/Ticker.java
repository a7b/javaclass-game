import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Ticker implements ActionListener {
	private Context ctx;

	Ticker() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ctx.getGame().tick();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}
}