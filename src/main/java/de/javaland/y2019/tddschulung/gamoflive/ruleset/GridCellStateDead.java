package de.javaland.y2019.tddschulung.gamoflive.ruleset;

import java.util.Collection;

import de.javaland.y2019.tddschulung.gamoflive.basegame.GridCellState;

public class GridCellStateDead implements GridCellState {

	@Override
	public GridCellState calculateNext(Collection<GridCellState> neigbourStates) {
		long alifeNeigbousCount = neigbourStates.stream().filter(n -> n.getClass() == GridCellStateAlive.class).count();

		return 3 == alifeNeigbousCount ? new GridCellStateAlive() : this;
	}

}
