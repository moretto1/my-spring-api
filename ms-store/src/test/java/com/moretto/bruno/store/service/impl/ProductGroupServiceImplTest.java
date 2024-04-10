package com.moretto.bruno.store.service.impl;

import com.moretto.bruno.store.entity.ProductGroup;
import com.moretto.bruno.store.repository.ProductGroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductGroupServiceImplTest {

    @InjectMocks
    ProductGroupServiceImpl productGroupService;
    @Mock
    ProductGroupRepository productGroupRepository;

    @Test
    public void should_returnBadRequest_when_productGroupAlreadyExists_onCreate() {
        final String name = "someName";

        ProductGroup productGroup = new ProductGroup();
        productGroup.setName(name);

        when(productGroupRepository.existsByName(name)).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () ->
                productGroupService.create(productGroup));

        assertEquals(HttpStatus.BAD_REQUEST.value(), ex.getStatusCode().value());
    }

    @Test
    public void should_returnBadRequest_when_productGroupIdIsnNull_onCreate() {
        ProductGroup productGroup = new ProductGroup();
        productGroup.setId(123L);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () ->
                productGroupService.create(productGroup));

        assertEquals(HttpStatus.BAD_REQUEST.value(), ex.getStatusCode().value());
    }

    @Test
    public void should_successfullyExecute_onCreate() {
        final Long id = 123L;
        final String name = "someName";

        ProductGroup productGroupToSave = new ProductGroup();
        productGroupToSave.setName(name);

        ProductGroup productGroupWithId = new ProductGroup();
        productGroupWithId.setId(id);
        productGroupWithId.setName(name);

        when(productGroupRepository.existsByName(name)).thenReturn(false);
        when(productGroupRepository.save(productGroupToSave)).thenReturn(productGroupWithId);

        ProductGroup productGroup = productGroupService.create(productGroupToSave);

        assertEquals(productGroupWithId.getId(), productGroup.getId());
    }

    @Test
    public void should_returnNotFound_when_productGroupNonExists_onUpdate() {
        final Long id = 123L;
        final String name = "someName";

        ProductGroup productGroup = new ProductGroup();
        productGroup.setId(id);
        productGroup.setName(name);

        when(productGroupRepository.existsById(id)).thenReturn(false);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () ->
                productGroupService.update(productGroup));

        assertEquals(HttpStatus.NOT_FOUND.value(), ex.getStatusCode().value());
    }

    @Test
    public void should_returnNotFound_when_productGroupIdIsNull_onUpdate() {
        final String name = "someName";

        ProductGroup productGroup = new ProductGroup();
        productGroup.setName(name);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () ->
                productGroupService.update(productGroup));

        assertEquals(HttpStatus.NOT_FOUND.value(), ex.getStatusCode().value());
    }

    @Test
    public void should_successfullyExecute_onUpdate() {
        final Long id = 123L;
        final String name = "someName";

        ProductGroup updatedProductGroup = new ProductGroup();
        updatedProductGroup.setId(id);
        updatedProductGroup.setName(name);

        when(productGroupRepository.existsById(id)).thenReturn(true);
        when(productGroupRepository.save(updatedProductGroup)).thenReturn(updatedProductGroup);

        ProductGroup productGroup = productGroupService.update(updatedProductGroup);

        assertEquals(id, productGroup.getId() );
        assertEquals(name, productGroup.getName());
    }

    @Test
    public void should_returnNotFound_when_productGroupDoesntExists_onGetById() {
        final long id = 123L;

        when(productGroupRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> productGroupService.getById(id));

        assertEquals(HttpStatus.NOT_FOUND.value(), ex.getStatusCode().value());
    }

    @Test
    public void should_returnSuccessfully_onGetById() {
        final long id = 123L;
        final String name = "someName";

        ProductGroup productGroupToReturn = new ProductGroup();
        productGroupToReturn.setId(id);
        productGroupToReturn.setName(name);

        when(productGroupRepository.findById(id)).thenReturn(Optional.of(productGroupToReturn));

        ProductGroup productGroup = productGroupService.getById(id);

        assertEquals(productGroupToReturn, productGroup);
    }

    @Test
    public void should_returnSuccessfully_onFindAll() {
        int pageNumber = 0;
        int pageSize = 3;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        List<ProductGroup> productGroups = List.of(new ProductGroup(1L), new ProductGroup(2L), new ProductGroup(3L));
        Page<ProductGroup> page = new PageImpl<>(productGroups, pageable, 3);

        when(productGroupRepository.findAll(pageable)).thenReturn(page);

        Page<ProductGroup> productGroupPage = productGroupService.findAll(pageNumber, pageSize);

        assertEquals(productGroups.size(), productGroupPage.getNumberOfElements());
        assertEquals(2L, productGroups.get(1).getId());
    }

}
