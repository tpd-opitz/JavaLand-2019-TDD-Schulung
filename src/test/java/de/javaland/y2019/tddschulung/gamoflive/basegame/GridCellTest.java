package de.javaland.y2019.tddschulung.gamoflive.basegame;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class GridCellTest {

	@BeforeEach
	 void setUp() throws Exception {
	}

	@Test
	void doesPassesNeighborStatesToInitialStateOnCalculationsRequest() {
		GridCell neigbour = mock(GridCell.class,"to sting name");
		GridCellState neigbourState = mock(GridCellState.class);
		doReturn(neigbourState).when(neigbour).getState();
		GridCellState initialState = mock(GridCellState.class);
		
		GridCell cell = new GridCell(initialState );
		for (int i = 0; i < 8; i++) {
			cell.addNeighbor(neigbour);
		}
		
		cell.prepareNextGeneration();
		
		
		@SuppressWarnings("unchecked")
		ArgumentCaptor<Collection<GridCellState>> cellStateList = ArgumentCaptor.forClass(Collection.class);
		
		verify(initialState).calculateNext(cellStateList.capture());
		
	}

	@Test
	void takesOverNewStateOnChangeRequest() {
		GridCell neigbour = mock(GridCell.class);
		GridCellState neigbourState = mock(GridCellState.class);
     	GridCellState newState = mock(GridCellState.class);
     	GridCellState initialState = mock(GridCellState.class);
		doReturn(neigbourState).when(neigbour).getState();
		doReturn(newState).when(initialState).calculateNext(anyCollection());
		
		
		GridCell spy = Mockito.spy(new GridCell(initialState));

		doNothing().when(spy).prepareNextGeneration();
	
		
		GridCell cell = new GridCell(initialState );
		for (int i = 0; i < 8; i++) {
			cell.addNeighbor(neigbour);
		}
		
		spy.prepareNextGeneration();
		spy.changeState();
		

		GridCellState currentState = cell.getState();
		assertEquals(newState, currentState, "state did change");
	}
	@Test
	void doesChangeStateOnCalculationsRequest() {
		GridCell neigbour = mock(GridCell.class);
		GridCellState neigbourState = mock(GridCellState.class);
		doReturn(neigbourState).when(neigbour).getState();
				
		GridCellState initialState = mock(GridCellState.class);
		GridCell cell = new GridCell(initialState );
		for (int i = 0; i < 8; i++) {
			cell.addNeighbor(neigbour);
		}
		
		cell.prepareNextGeneration();
		
		GridCellState currentState = cell.getState();
		assertEquals(initialState, currentState, "state did not change");
	}


}
