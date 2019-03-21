package de.javaland.y2019.tddschulung.gamoflive.basegame;

import java.util.Collection;

public interface GridCellState {

	GridCellState calculateNext(Collection<GridCellState> neigbourStates);

}
