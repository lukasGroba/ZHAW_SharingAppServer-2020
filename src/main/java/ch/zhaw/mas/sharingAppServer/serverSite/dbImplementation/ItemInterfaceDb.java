package ch.zhaw.mas.sharingAppServer.serverSite.dbImplementation;

import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemModelDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInterfaceDb extends JpaRepository<ItemModelDb, String> {

}
