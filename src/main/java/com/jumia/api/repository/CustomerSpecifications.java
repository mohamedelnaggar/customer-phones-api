package com.jumia.api.repository;

import com.jumia.api.entity.Customer;
import com.jumia.api.enumeration.Country;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * A JPA Specification to filter customers
 */
public class CustomerSpecifications {

    public static Specification<Customer> getCustomerSpecifications(final String countryCode, final Boolean validPhone) {
        return (Specification<Customer>) (root, query, criteriaBuilder) -> {
            Predicate singleCountryMatch;
            Path<String> phonePath = root.get("phone");
            List<Predicate> filteredCountriesMatches = new ArrayList<>();
            List<Country> filteredCountries = Country.getCountriesByCode(countryCode);
            for (Country country : filteredCountries) {
                singleCountryMatch = criteriaBuilder.like(phonePath, country.getPhonePrefix() + "%");
                if(validPhone != null) {
                    Pattern regexPattern = Pattern.compile(country.getPhonePattern());
                    Expression<String> phonePatternExpression = criteriaBuilder.literal(regexPattern.pattern());
                    Predicate regexpPredicate = criteriaBuilder.equal(criteriaBuilder.function("REGEXP", Boolean.class, phonePath, phonePatternExpression), validPhone);
                    singleCountryMatch = criteriaBuilder.and(singleCountryMatch, regexpPredicate);
                }
                filteredCountriesMatches.add(singleCountryMatch);
            }
            return criteriaBuilder.or(filteredCountriesMatches.toArray(new Predicate[0]));
        };
    }

}