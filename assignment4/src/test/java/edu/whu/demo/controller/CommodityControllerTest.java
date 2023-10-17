package edu.whu.demo.controller;

import edu.whu.demo.entity.Commodity;
import edu.whu.demo.service.CommodityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CommodityControllerTest {

    @Mock
    CommodityService commodityService;

    @InjectMocks
    Controller controller;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCommodity_PositiveCase(){
        Commodity commodity = new Commodity();
        commodity.setId(1);
        commodity.setName("pen");
        commodity.setPrice(5.99);

        when(commodityService.getCommodity(1)).thenReturn(commodity);

        ResponseEntity<Commodity> response = controller.getCommodity(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(commodity, response.getBody());
    }


    @Test
    public void testGetTodo_NegativeCase() {
        when(commodityService.getCommodity(1)).thenReturn(null);

        ResponseEntity<Commodity> response = controller.getCommodity(1);

        assertEquals(204, response.getStatusCodeValue());
        assertEquals(null, response.getBody());
    }



    @Test
    public void testAddTodo_PositiveCase() {
        Commodity commodity = new Commodity();
        commodity.setId(1);
        commodity.setName("pencil");
        commodity.setPrice(2.88);

        when(commodityService.addCommodity(commodity)).thenReturn(commodity);

        ResponseEntity<Commodity> response = controller.addCommodity(commodity);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(commodity, response.getBody());
    }

    @Test
    public void testUpdateTodo_PositiveCase() {
        Commodity commodity = new Commodity();
        commodity.setId(2);
        commodity.setName("pen");
        commodity.setPrice(2.88);

        ResponseEntity<Void> response = controller.updateCommodity(2, commodity);

        verify(commodityService, times(1)).updateCommodity(2, commodity);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteTodo_PositiveCase() {
        ResponseEntity<Void> response = controller.deleteCommodity(1);

        verify(commodityService, times(1)).deleteCommodity(1);
        assertEquals(200, response.getStatusCodeValue());
    }
}
