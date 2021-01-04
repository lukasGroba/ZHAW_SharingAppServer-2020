package ch.zhaw.mas.sharingAppServer.serverSite.dbImplementation;

import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemModelDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemInterfaceDb extends JpaRepository<ItemModelDb, Integer> {
    boolean existsById(String mail); //Integer because ID is Integer
    List<ItemModelDb> findItemModelDbByOwner_Mail (String mail);

}
