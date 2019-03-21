package de.javaland.y2019.tddschulung.gamoflive.ruleset;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.javaland.y2019.tddschulung.gamoflive.basegame.GridCellState;

class StatusDeadTest {

	private GridCellState state;

	@BeforeEach
	public void setup() {
		state = new GridCellStateDead();
	}

	@Test
	void Rule4_anyDeadCellWithThreeNeighborsIsBorn() {
		// arrange
		GridCellState dead = new GridCellStateDead();
		GridCellState alive = new GridCellStateAlive();
		Collection<GridCellState> threeAlieNeigbourStates = Arrays.asList(dead, dead, alive, dead, alive, dead, alive,
				dead);

		// act
		GridCellState result = state.calculateNext(threeAlieNeigbourStates);

		// assert
		assertEquals(GridCellStateAlive.class, result.getClass(), "returns the other state");
	}

	@Test
	void Rule4b_anyDeadCellWithLessThanThreeNeighborsSTaysDead() {
		// arrange
		GridCellState dead = new GridCellStateDead();
		GridCellState alive = new GridCellStateAlive();
		Collection<GridCellState> twoAlieNeigbourStates = Arrays.asList(dead, dead, dead, alive, dead, alive, dead);

		// act
		GridCellState result = state.calculateNext(twoAlieNeigbourStates);

		// assert
		assertEquals(state, result, "returns itself");
	}

	@Test
	void Rule4c_anyDeadCellWithMoreThanThreeNeighborsSTaysDead() {
		// arrange
		GridCellState dead = new GridCellStateDead();
		GridCellState alive = new GridCellStateAlive();
		Collection<GridCellState> fiveAlieNeigbourStates = Arrays.asList(dead, dead, alive, dead, alive, dead, alive,
				alive, dead);

		// act
		GridCellState result = state.calculateNext(fiveAlieNeigbourStates);

		// assert
		assertEquals(state, result, "returns itself");
	}
}
