package org.example.spring1.item;

import javax.annotation.processing.Generated;
import org.example.spring1.item.model.Item;
import org.example.spring1.item.model.Item.ItemBuilder;
import org.example.spring1.item.model.dto.ItemDTO;
import org.example.spring1.item.model.dto.ItemDTO.ItemDTOBuilder;
import org.example.spring1.item.model.dto.ItemRequestDTO;
import org.example.spring1.item.model.dto.ItemRequestDTO.ItemRequestDTOBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T15:28:31+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDTO toItemDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDTOBuilder itemDTO = ItemDTO.builder();

        itemDTO.id( item.getId() );
        itemDTO.name( item.getName() );
        itemDTO.description( item.getDescription() );
        itemDTO.price( item.getPrice() );

        return itemDTO.build();
    }

    @Override
    public Item toEntity(ItemRequestDTO itemRequestDTO) {
        if ( itemRequestDTO == null ) {
            return null;
        }

        ItemBuilder item = Item.builder();

        item.name( itemRequestDTO.getName() );
        item.description( itemRequestDTO.getDescription() );
        item.price( itemRequestDTO.getPrice() );

        return item.build();
    }

    @Override
    public ItemRequestDTO entityToRequestDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemRequestDTOBuilder itemRequestDTO = ItemRequestDTO.builder();

        itemRequestDTO.name( item.getName() );
        itemRequestDTO.description( item.getDescription() );
        itemRequestDTO.price( item.getPrice() );

        return itemRequestDTO.build();
    }
}
