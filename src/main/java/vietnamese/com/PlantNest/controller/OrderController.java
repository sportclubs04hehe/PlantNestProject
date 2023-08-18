package vietnamese.com.PlantNest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vietnamese.com.PlantNest.dto.OrderDTO;
import vietnamese.com.PlantNest.service.OrderService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api/v1/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController
{
    @Autowired
    private final OrderService orderService;

    @RequestMapping("/list")
    public ResponseEntity<List<OrderDTO>> getAll(){
        List<OrderDTO> orderDTOS = orderService.getAllOrders();
        if(orderDTOS.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(orderDTOS, OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(
            @Valid @RequestBody OrderDTO orderDTO
    ){
        OrderDTO orderDTO1 = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(orderDTO1, CREATED);
    }

}
