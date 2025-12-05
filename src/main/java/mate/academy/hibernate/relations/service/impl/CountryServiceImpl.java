package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        this.countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        Optional<Country> existing = countryDao.findByName(country.getName());
        return existing.orElseGet(() -> countryDao.add(country));
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElse(null);
    }
}
