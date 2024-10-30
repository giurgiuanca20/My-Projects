package org.example.spring1.item;

import org.example.spring1.item.model.Item;
import org.example.spring1.item.model.dto.ItemDTO;
import org.example.spring1.item.model.dto.ItemRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDTO toItemDto(Item item);

    Item toEntity(ItemRequestDTO itemRequestDTO);

    ItemRequestDTO entityToRequestDTO(Item item);
}
