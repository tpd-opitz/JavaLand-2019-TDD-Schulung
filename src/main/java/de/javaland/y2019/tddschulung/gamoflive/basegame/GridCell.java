package de.javaland.y2019.tddschulung.gamoflive.basegame;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GridCell {

	private GridCellState initialState;
	private final Collection<GridCell> neigbours = new HashSet<GridCell>();
	private GridCellState nextGenerationState;

	public GridCell(GridCellState initialState) {
		this.initialState = initialState;
		// TODO Auto-generated constructor stub
	}

	public void prepareNextGeneration() {
		Set<GridCellState> neigborStates = neigbours.stream()
				.map(GridCell::getState)
				.collect(Collectors.toSet());
		nextGenerationState = initialState.calculateNext(neigborStates);
		
	}

	public GridCellState getState() {
		return initialState;
		
	}

	public void addNeighbor(GridCell neigbour) {
		neigbours.add(neigbour);
		
	}

	public void changeState() {
		initialState=nextGenerationState;
	}

}
