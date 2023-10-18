package fr.mbds.reddit

import grails.gorm.services.Service

@Service(CustomFile)
interface CustomFileService {

    CustomFile get(Serializable id)

    List<CustomFile> list(Map args)

    Long count()

    void delete(Serializable id)

    CustomFile save(CustomFile customFile)

}