package fr.mbds.reddit

import grails.gorm.services.Service

@Service(Community)
interface CommunityService {

    Community get(Serializable id)

    List<Community> list(Map args)

    Long count()

    void delete(Serializable id)

    Community save(Community community)

}