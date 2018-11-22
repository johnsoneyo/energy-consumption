/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.repository;

import com.servicehouse.energyconsumption.domainobject.ProfileDO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author johnson3yo
 */
public interface ProfileRepository extends JpaRepository<ProfileDO, Long> {
    List<ProfileDO> findByName(String name);
}
