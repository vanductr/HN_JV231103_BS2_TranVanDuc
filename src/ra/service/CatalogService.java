package ra.service;

import ra.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IGenericService<Catalog, Integer> {
    private static List<Catalog> catalogs = new ArrayList<>();

    public CatalogService() {

    }

    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public void save(Catalog catalog) {
        for (int i = 0; i < catalogs.size(); i++) {
            Catalog catalog1 = catalogs.get(i);
            if (catalog.getCatalogId() == catalog1.getCatalogId()) {
                catalogs.set(i, catalog);
                return; // Khi đã tìm thấy và thay thế catalog, ta có thể thoát vòng lặp
            }
        }
        catalogs.add(catalog);
    }

    @Override
    public Catalog findById(Integer catalogId) {
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == catalogId) {
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer catalogId) {
        Catalog catalogToRemove = null;
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == catalogId) {
                catalogToRemove = catalog;
                break;
            }
        }
        if (catalogToRemove != null) {
            catalogs.remove(catalogToRemove);
        }
    }
}
