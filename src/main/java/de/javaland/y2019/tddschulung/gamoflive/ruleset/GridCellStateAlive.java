package de.javaland.y2019.tddschulung.gamoflive.ruleset;

import java.util.Collection;

import de.javaland.y2019.tddschulung.gamoflive.basegame.GridCellState;

public class GridCellStateAlive implements GridCellState {

	private static final int MAXIMUM_ALIFE_NEIGBOURS = 3;
	private static final int MINIMUM_ALIFE_NEIGBORS = 2;

	@Override
	public GridCellState calculateNext(Collection<GridCellState> neigbourStates) {
		long alifeNeigboursCount = neigbourStates
				.stream()
				.filter(n -> n.getClass().equals(GridCellStateAlive.class))
				.count();

		return MINIMUM_ALIFE_NEIGBORS == alifeNeigboursCount 
				|| MAXIMUM_ALIFE_NEIGBOURS == alifeNeigboursCount 
				   ? this
				   : new GridCellStateDead();
	}

}
