package events;

import eventHandler.AbstractEvent;

public class PositionChanged extends AbstractEvent {
	private static final long serialVersionUID = 480096146703824993L;

	public PositionChanged(Object source) {
		super(source);
	}

	public void runOn(Object target) {
		PositionChangeListener listener = (PositionChangeListener) target;
		listener.whenPositionChanged(this);
	}
}
