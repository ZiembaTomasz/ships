package pl.ziembatomasz.statki.board;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShipTest {
    @Test
    public void creasteShipTest(){
        //Given
        Point point = new Point(3, 3);
        //When
        Ship ship = Ship.create(point,3,Orientation.HORIZONTAL);
        //Then
        assertEquals(3, ship.getPositions().size());
    }
}