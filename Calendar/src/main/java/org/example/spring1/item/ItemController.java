package org.example.spring1.item;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.example.spring1.global.SingleBodyRequestDTO;
import org.example.spring1.item.model.dto.ItemDTO;
import org.example.spring1.item.model.dto.ItemRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.spring1.UrlMapping.*;

@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDTO> findAll() {
        return itemService.findAll();
    }

    @GetMapping(ID_PART)
    public ResponseEntity<?> getI(@PathVariable Long id) {
        return itemService.get(id);
    }

    @PostMapping()
    public ResponseEntity<ItemDTO> create(@RequestBody ItemRequestDTO dto) {
        return ResponseEntity.ok(itemService.create(dto));
    }

    @DeleteMapping(ID_PART)
    public ResponseEntity<Void> deleteI(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMultiple(@RequestParam List<Long> ids) {
        itemService.deleteMultiple(ids);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(ID_PART)
    public ResponseEntity<ItemDTO> update(@PathVariable Long id, @RequestBody ItemRequestDTO dto) {
        return ResponseEntity.ok(itemService.update(id, dto));
    }
}
