package org.example.spring1.item;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.spring1.item.model.Item;
import org.example.spring1.item.model.dto.ItemDTO;
import org.example.spring1.item.model.dto.ItemRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream().map(itemMapper::toItemDto).toList();
    }

    public ResponseEntity<?> get(Long id) {
        return itemRepository.findById(id)
                .map(itemMapper::toItemDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ItemDTO create(ItemRequestDTO dto) {
        Item item = itemMapper.toEntity(dto);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toItemDto(savedItem);
    }

    public void delete(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Item with ID " + id + " does not exist.");
        }
    }

    public ItemDTO update(Long id, ItemRequestDTO dto) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) {
            throw new IllegalArgumentException("Item with ID " + id + " does not exist.");
        }
        Item existingItem = optionalItem.get();
        existingItem.setName(dto.getName());
        existingItem.setDescription(dto.getDescription());
        existingItem.setPrice(dto.getPrice());
        Item updatedItem = itemRepository.save(existingItem);
        return itemMapper.toItemDto(updatedItem);
    }

    public void deleteMultiple(List<Long> ids) {
        ids.forEach(this::delete);
    }
}
