package com.jumia.api.repository;

import com.jumia.api.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerSpecificationsTest {

    private CustomerSpecifications customerSpecifications;

    @Mock
    private CriteriaBuilder criteriaBuilderMock;
    @Mock
    private CriteriaQuery criteriaQueryMock;
    @Mock
    private Root<Customer> customerRootMock;


    @Test
    public void shouldReturnCorrectPredicate_whenCustomerSpecificationCreatedWithEmptyFilters1() {
        Path phonePathMock = mock(Path.class);
        when(customerRootMock.get("phone")).thenReturn(phonePathMock);
        Predicate phoneIsLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilderMock.like(anyObject(), anyString())).thenReturn(phoneIsLikePredicateMock);
        when(criteriaBuilderMock.or(anyObject())).thenAnswer(i -> i.getArguments()[0]);
        Specification<Customer> actualSpecifications = CustomerSpecifications.getCustomerSpecifications(null, null);
        Predicate actualPredicate = actualSpecifications.toPredicate(customerRootMock, criteriaQueryMock, criteriaBuilderMock);
        Predicate expectedPredicate = criteriaBuilderMock.or(phoneIsLikePredicateMock, phoneIsLikePredicateMock, phoneIsLikePredicateMock, phoneIsLikePredicateMock, phoneIsLikePredicateMock);

        verify(customerRootMock, times(1)).get("phone");
        verifyNoMoreInteractions(customerRootMock);
        verify(criteriaBuilderMock, times(5)).like(anyObject(), anyString());
        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    public void shouldReturnCorrectPredicate_whenCustomerSpecificationCreatedWithCountryCodeFiler() {
        Path phonePathMock = mock(Path.class);
        when(customerRootMock.get("phone")).thenReturn(phonePathMock);
        Predicate phoneIsLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilderMock.like(anyObject(), anyString())).thenReturn(phoneIsLikePredicateMock);
        when(criteriaBuilderMock.or(anyObject())).thenAnswer(i -> i.getArguments()[0]);
        Specification<Customer> actualSpecifications = CustomerSpecifications.getCustomerSpecifications("237", null);
        Predicate actualPredicate = actualSpecifications.toPredicate(customerRootMock, criteriaQueryMock, criteriaBuilderMock);
        Predicate expectedPredicate = criteriaBuilderMock.or(phoneIsLikePredicateMock);

        verify(customerRootMock, times(1)).get("phone");
        verifyNoMoreInteractions(customerRootMock);
        verify(criteriaBuilderMock, times(1)).like(anyObject(), anyString());
        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    public void shouldReturnCorrectPredicate_whenCustomerSpecificationCreatedWithPhoneValidFilter() {
        Path phonePathMock = mock(Path.class);
        when(customerRootMock.get("phone")).thenReturn(phonePathMock);


        Predicate phoneIsLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilderMock.like(anyObject(), anyString())).thenReturn(phoneIsLikePredicateMock);

        Expression phoneRegexExpressionMock = mock(Expression.class);
        Predicate phoneRegexPredicateMock = mock(Predicate.class);
        when(criteriaBuilderMock.equal(criteriaBuilderMock.function("REGEXP", Boolean.class, phonePathMock, phoneRegexExpressionMock), true)).thenReturn(phoneRegexPredicateMock);


        when(criteriaBuilderMock.or(anyObject())).thenAnswer(i -> i.getArguments()[0]);
        Specification<Customer> actualSpecifications = CustomerSpecifications.getCustomerSpecifications(null, true);
        Predicate actualPredicate = actualSpecifications.toPredicate(customerRootMock, criteriaQueryMock, criteriaBuilderMock);

        Predicate expectedPredicate = criteriaBuilderMock.or(criteriaBuilderMock.and(phoneIsLikePredicateMock, phoneRegexPredicateMock),
                criteriaBuilderMock.and(phoneIsLikePredicateMock, phoneRegexPredicateMock),
                criteriaBuilderMock.and(phoneIsLikePredicateMock, phoneRegexPredicateMock),
                criteriaBuilderMock.and(phoneIsLikePredicateMock, phoneRegexPredicateMock),
                criteriaBuilderMock.and(phoneIsLikePredicateMock, phoneRegexPredicateMock));

        verify(customerRootMock, times(1)).get("phone");
        verifyNoMoreInteractions(customerRootMock);
        verify(criteriaBuilderMock, times(5)).like(anyObject(), anyString());
        verify(criteriaBuilderMock, times(1)).equal(criteriaBuilderMock.function("REGEXP", Boolean.class, phonePathMock, phoneRegexExpressionMock), true);
        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    public void shouldReturnCorrectPredicate_whenCustomerSpecificationCreatedWithCountryCodeAndPhoneValidFilters() {
        Path phonePathMock = mock(Path.class);
        when(customerRootMock.get("phone")).thenReturn(phonePathMock);


        Predicate phoneIsLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilderMock.like(anyObject(), anyString())).thenReturn(phoneIsLikePredicateMock);

        Expression phoneRegexExpressionMock = mock(Expression.class);
        Predicate phoneRegexPredicateMock = mock(Predicate.class);
        when(criteriaBuilderMock.equal(criteriaBuilderMock.function("REGEXP", Boolean.class, phonePathMock, phoneRegexExpressionMock), true)).thenReturn(phoneRegexPredicateMock);


        when(criteriaBuilderMock.or(anyObject())).thenAnswer(i -> i.getArguments()[0]);
        Specification<Customer> actualSpecifications = CustomerSpecifications.getCustomerSpecifications("237", true);
        Predicate actualPredicate = actualSpecifications.toPredicate(customerRootMock, criteriaQueryMock, criteriaBuilderMock);

        Predicate expectedPredicate = criteriaBuilderMock.or(criteriaBuilderMock.and(phoneIsLikePredicateMock, phoneRegexPredicateMock));

        verify(customerRootMock, times(1)).get("phone");
        verifyNoMoreInteractions(customerRootMock);
        verify(criteriaBuilderMock, times(1)).like(anyObject(), anyString());
        verify(criteriaBuilderMock, times(1)).equal(criteriaBuilderMock.function("REGEXP", Boolean.class, phonePathMock, phoneRegexExpressionMock), true);
        assertEquals(expectedPredicate, actualPredicate);
    }


}