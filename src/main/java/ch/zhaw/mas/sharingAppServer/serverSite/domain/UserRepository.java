/*package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "userdb", path = "userdb")
public interface UserRepository extends CrudRepository<UserModel, Long> {

    List<UserModel> findByUsername(@Param("username") String username);
    UserModel findByMail(String mail);
}

 */
