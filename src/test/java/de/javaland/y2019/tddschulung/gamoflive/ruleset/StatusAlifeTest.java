package de.javaland.y2019.tddschulung.gamoflive.ruleset;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.javaland.y2019.tddschulung.gamoflive.basegame.GridCellState;

class StatusAlifeTest {

	private GridCellState state;

	@BeforeEach
	public void setup() {
		state = new GridCellStateAlive();
	}

	@Test
	void Rule1_anyLifeCellWithLessThanTwoNeighborsDies() {
		Collection<GridCellState> neigbourStates = Collections.emptySet();

		// act
		GridCellState result = state.calculateNext(neigbourStates);

		// assert
		assertEquals(GridCellStateDead.class, result.getClass(), "returns the other state");
	}

	@Test
	void Rule2_anyLifeCellWithTwoNeighborsStaysAlife() {
		// arrange
		GridCellState dead = new GridCellStateDead();
		GridCellState alive = new GridCellStateAlive();
		Collection<GridCellState> twoAlieNeigbourStates = Arrays.asList(dead, dead, alive, dead, alive, dead);

		// act
		GridCellState result = state.calculateNext(twoAlieNeigbourStates);

		// assert
		assertEquals(state, result, "returns itself");
	}

	@Test
	void Rule2_anyLifeCellWithThreeNeighborsStaysAlife() {
		// arrange
		GridCellState dead = new GridCellStateDead();
		GridCellState alive = new GridCellStateAlive();
		Collection<GridCellState> twoAlieNeigbourStates = Arrays.asList(dead, dead, alive, dead, alive, dead, alive,
				dead);

		// act
		GridCellState result = state.calculateNext(twoAlieNeigbourStates);

		// assert
		assertEquals(state, result, "returns itself");
	}
	
	@Test
	void Rule3_anyLifeCellWithMoreThreeNeighborsDies() {
		// arrange
		GridCellState dead = new GridCellStateDead();
		GridCellState alive = new GridCellStateAlive();
		Collection<GridCellState> twoAlieNeigbourStates = Arrays.asList(dead, dead, alive, dead, alive, dead, alive,
				alive,dead);

		// act
		GridCellState result = state.calculateNext(twoAlieNeigbourStates);

		// assert
		assertEquals(GridCellStateDead.class, result.getClass(), "returns the other state");
	}
}
